/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.campanya;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import modelo.Campaña;

/**
 *
 * @author xserg
 */
public class ListModelCampanya extends AbstractListModel{

    private Campaña [] model;

    public ListModelCampanya() {
        model= null;
    }

    public ListModelCampanya(Campaña [] model) {
        this.model = model;
    }
    
    @Override
    public int getSize() {
        return model.length;
    }

    @Override
    public Object getElementAt(int index) {
        return model[index];
    }

    void setList(Campaña [] c) {
        model = c;
    }
    
    public Campaña getCampaña(int index){
        return model[index];
    }
    
}
