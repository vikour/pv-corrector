/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.medidas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Canal;
import modelo.CurvaIV;
import modelo.CurvaMedida;
import modelo.MedidaSensor;

/**
 *
 * @author Sergio
 */
public class TableModelMedidas extends AbstractTableModel{
    
    private List<CurvaMedida> curvas;
    private List<Canal> canales;
    
    private static final String [] COLUMN_NAMES =
            new String []{"Fecha", "Hora", "ISC", "VOC", "PMAX", "IMAX", "VMAX", "FF"};
    private static final int FECHA = 0;
    private static final int HORA = 1;
    private static final int ISC = 2;
    private static final int VOC = 3;
    private static final int PMAX = 4;
    private static final int IMAX = 5;
    private static final int VMAX = 6;
    private static final int FF = 7;

    public TableModelMedidas() {
        curvas = new ArrayList<>();
        canales = new ArrayList<>();
    }

    public TableModelMedidas(List<CurvaMedida> curvas) {
        setCurvas(curvas);
    }
    
    @Override
    public String getColumnName(int column) {
        String name = "";
        
        if (column >= COLUMN_NAMES.length)
            name = canales.get(column - COLUMN_NAMES.length).getNombre();
        else
            name = COLUMN_NAMES[column];
            
        return name;
        //return COLUMN_NAMES[column];
    }
    
    @Override
    public int getRowCount() {
        return curvas.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length + canales.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        
        switch (columnIndex) {
            
            case HORA:
                value = curvas.get(rowIndex).getHora();
                break;
                
            case FECHA:
                value = curvas.get(rowIndex).getFecha();
                break;
                
            case ISC:
                value = curvas.get(rowIndex).getIsc().getValor();
                break;
                
            case VOC:
                value = curvas.get(rowIndex).getVoc().getValor();
                break;
                
            case IMAX:
                value = curvas.get(rowIndex).getImax().getValor();
                break;
                
            case PMAX:
                value = curvas.get(rowIndex).getPmax().getValor();
                break;
                
            case VMAX:
                value = curvas.get(rowIndex).getVmax().getValor();
                break;
                
            case FF:
                value = curvas.get(rowIndex).getFf();
                break;
            
            default:
                CurvaMedida curvaIV = curvas.get(rowIndex);
                value = curvaIV.getMedidaCanal(canales.get(columnIndex -FF -1)).getValor();
               
        }
        
        return value;
    }
    
    public void setCurvas(List<CurvaMedida> curvas) {
        this.curvas = curvas;
        canales = curvas.get(0).getCanales();
    }
    
    public CurvaMedida getMedida(int selectedRow){
        return curvas.get(selectedRow);
    }
}
