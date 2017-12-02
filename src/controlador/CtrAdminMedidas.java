/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Campaña;
import modelo.CurvaMedida;
import vista.ViewAdminMedidas;

/**
 *
 * @author EzequielRodriguez
 */
public class CtrAdminMedidas implements ActionListener, ListSelectionListener{
    
    private ViewAdminMedidas vm;
    private CtrAdminCampanyas ctra;

   
    public CtrAdminMedidas(ViewAdminMedidas vm) {
        this.vm = vm;
        vm.setControlador(this);
        ctra=null;
       
       
    }

    public void setCtrAnterior(CtrAdminCampanyas ctra) {
        this.ctra = ctra;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setMedidas(Campaña c) {
        List<CurvaMedida> model=c.getCurvas();
        
        vm.mostrarCurvas(model);
    }
    
    public void vistaAnterior(){
        vm.vistaAnterior();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            medidaSeleccionada();
        }
    }
    
    public void medidaSeleccionada(){
        
    }
    
   
    
    
    
}
