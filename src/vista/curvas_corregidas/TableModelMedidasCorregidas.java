/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.curvas_corregidas;

import vista.medidas.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Canal;
import modelo.CurvaCorregida;
import modelo.CurvaIV;
import modelo.CurvaMedida;
import modelo.Medida;
import modelo.MedidaCanal;

/**
 *
 * @author Sergio
 */
public class TableModelMedidasCorregidas extends AbstractTableModel{
    
    private CurvaCorregida [] curvas;
    
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

    public TableModelMedidasCorregidas() {
        curvas = new CurvaCorregida[0];
    }

    public TableModelMedidasCorregidas(CurvaCorregida [] curvas) {
        setCurvas(curvas);
    }
    
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
    @Override
    public int getRowCount() {
        return curvas.length;
    }

    @Override
    public int getColumnCount() {
       return COLUMN_NAMES.length;
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
                value = String.format("%.2f", curvas[rowIndex].getFF()) + "   %";
                
        }
        
        return value;
    }
    
    public void setCurvas(CurvaCorregida [] curvas) {
        this.curvas = curvas;
    }
    
    public CurvaCorregida getMedida(int selectedRow){
        return curvas[selectedRow];
    }
}
