/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import modelo.Modulo;

/**
 *
 * @author Sergio
 */
public class ListModelModulo extends AbstractListModel {
    
    private List <Modulo> m;

    public ListModelModulo(List<Modulo> m) {
        this.m = m;
    }

    public void setList(List<Modulo> m) {
        this.m = m;
    }
    

    public ListModelModulo() {
        m= new ArrayList<>();
    }

    @Override
    public int getSize() {
        return m.size();
    }

    @Override
    public Object getElementAt(int index) {
        return m.get(index).getNombre();
    }

    public void add(Modulo m) {
        this.m.add(m);
    }
    
}
