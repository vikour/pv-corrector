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
import modelo.Medida;
import modelo.MedidaCanal;

/**
 *
 * @author Sergio
 */
public class TableModelMedidas extends AbstractTableModel{
    
    private CurvaMedida [] curvas;
    private Canal [] canales;
    
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
        curvas = new CurvaMedida[0];
        canales = new Canal[0];
    }

    public TableModelMedidas(CurvaMedida [] curvas) {
        setCurvas(curvas);
    }
    
    @Override
    public String getColumnName(int column) {
        String name = "";
        
        if (column >= COLUMN_NAMES.length)
            name = canales[column - COLUMN_NAMES.length].getNombre();
        else
            name = COLUMN_NAMES[column];
            
        return name;
        //return COLUMN_NAMES[column];
    }
    
    
    
    @Override
    public int getRowCount() {
        return curvas.length;
    }

    @Override
    public int getColumnCount() {
       int count = COLUMN_NAMES.length;
       
       if (canales != null)
          count += canales.length;

       return count;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        
        switch (columnIndex) {
            
            case HORA:
                value = curvas[rowIndex].getHora();
                break;
                
            case FECHA:
                value = curvas[rowIndex].getFecha();
                break;
                
            case ISC:
                value = curvas[rowIndex].getIsc().getValor();
                break;
                
            case VOC:
                value = curvas[rowIndex].getVoc().getValor();
                break;
                
            case IMAX:
                value = curvas[rowIndex].getImax().getValor();
                break;
                
            case PMAX:
                value = curvas[rowIndex].getPmax().getValor();
                break;
                
            case VMAX:
                value = curvas[rowIndex].getVmax().getValor();
                break;
                
            case FF:
                value = curvas[rowIndex].getFF() + "   %";
                break;
            
            default:
                CurvaMedida curvaIV = curvas[rowIndex];
                MedidaCanal medida = curvaIV.getMedidaCanal(canales[columnIndex -FF -1]);
                value = "" + medida.getValor() + "   " +medida.getMagnitud();
                value = medida.toString();
               
        }
        
        return value;
    }
    
    public void setCurvas(CurvaMedida [] curvas) {
        this.curvas = curvas;
        
        if (curvas.length != 0)
           canales = curvas[0].getCanales();
    }
    
    public CurvaMedida getMedida(int selectedRow){
        return curvas[selectedRow];
    }
}
