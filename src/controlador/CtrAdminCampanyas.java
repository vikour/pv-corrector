/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modulo;
import vista.ViewAdminCampanya;

/**
 *
 * @author EzequielRodriguez
 */
public class CtrAdminCampanyas implements ActionListener {
    ViewAdminCampanya vc;
    
    
    public CtrAdminCampanyas(ViewAdminCampanya vc) {
        this.vc = vc;
        
       
    }

    public void getCampanyas(){
        
    }
    
    public void mostrarConModulos(Modulo ms){
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    
}
