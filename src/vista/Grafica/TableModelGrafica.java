/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Grafica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.MedidaCurva;
import modelo.MedidaIntensidad;
import modelo.MedidaTension;

/**
 *
 * @author EzequielRodriguez
 */
public class TableModelGrafica extends AbstractTableModel{
    
    private int i1= 1;
    private List<MedidaCurva> lIntensidad;
    private List<MedidaCurva> lTension;
    
    private static final String [] ROW_NAMES = 
            new String []{"Intensidad","Tension"};
    
    private static final int INTENSIDAD= 0;
    private static final int TENSION = 1;
    
    
    public TableModelGrafica(){
        lIntensidad = new ArrayList<>();
        lTension = new ArrayList<>();
    }
    
    public TableModelGrafica(List<MedidaCurva> intensidad, List<MedidaCurva> ten){
    
            setIntensidad(intensidad);
            setTension(ten);
            
    }   
    
    @Override
    public String getColumnName(int column) {
        
        String name="";
        
        name = Integer.toString(i1);
        i1++;
        
        return name;
      
    }
    
    @Override
    public int getRowCount() {
        return 2;
    }

    @Override
    public int getColumnCount() {
        return lIntensidad.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value ="";
        switch (rowIndex){
            case INTENSIDAD:
                value = lIntensidad.get(columnIndex);
                
                break;
            
            case TENSION:
                value = lTension.get(columnIndex);
                break;
                
            default:
                value = 0;
        }
        
        return value;
    }
    
    public void setIntensidad(List<MedidaCurva> intensidad) {
        this.lIntensidad = intensidad;
        
    }
    
    public void setTension(List<MedidaCurva> tension){
        this.lTension = tension;
    }
    
}
