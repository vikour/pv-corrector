/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.CurvaMedida;

/**
 *
 * @author Sergio
 */
public class TableModelMedidas extends AbstractTableModel{
    
    private List<CurvaMedida> modelo;
    
    private List<String> columName;

    @Override
    public String getColumnName(int column) {
        return columName.get(column); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getRowCount() {
        return modelo.size();
    }

    @Override
    public int getColumnCount() {
        return columName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
    
    public CurvaMedida getMedida(int selectedRow){
        return modelo.get(selectedRow);
    }
}
