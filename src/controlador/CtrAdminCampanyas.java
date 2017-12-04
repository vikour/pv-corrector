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
import modelo.Modulo;
import vista.ViewAdminCampanya;

/**
 *
 * @author EzequielRodriguez*/
 
public class CtrAdminCampanyas implements ActionListener,ListSelectionListener {
    
    private ViewAdminCampanya vc;
    private CtrAdminModulos ctrant;
    private CtrAdminMedidas ctrs;

    public CtrAdminCampanyas(ViewAdminCampanya v) {
        vc=v;
        vc.setControlador(this);
    }
    
    public void setModulos(Modulo ms){
       List<Campaña> mc=ms.getCampañas();
       
       vc.mostrarCampanyas(mc);
      
        
    }

    public void setCtrAnterior(CtrAdminModulos ctrant) {
        this.ctrant = ctrant;
    }
    
    public  void setCtrSiguiente(CtrAdminMedidas ctrs){
        this.ctrs=ctrs;
    }
    
    
    
    private void campañaSeleccionada() {
      vc.habilitarBorrado(true);
       vc.habilitarExportacion(true);
       vc.habilitarVerMedidas(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case ViewAdminCampanya.SELECC_CAMPA:
                campañaSeleccionada();
                break;
            case ViewAdminCampanya.MODULO:
                vistaAnterior();
                break;
            case ViewAdminCampanya.MEDIDAS:
                verMedidas();
                break;
                       
        }
    }
    
    public void vistaAnterior(){
        vc.vistaAnterior();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            campañaSeleccionada();
        }
    }
    
    public void verMedidas(){
        Campaña c= vc.getCampañaSeleccionada();
        ctrs.setMedidas(c);
        vc.siguienteVista();
        
    }

}
