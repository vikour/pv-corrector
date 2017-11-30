/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.List;
import javax.swing.AbstractListModel;
import modelo.Campaña;

/**
 *
 * @author xserg
 */
public class ListModelCampanya extends AbstractListModel{

    private List<Campaña> model;

    public ListModelCampanya(List<Campaña> model) {
        this.model = model;
    }
    
    @Override
    public int getSize() {
        return model.size();
    }

    @Override
    public Object getElementAt(int index) {
        return model.get(index).getNombre();
    }

    void setList(List<Campaña> c) {
        model=c;
    }
    
}
