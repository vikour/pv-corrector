/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Campaña;
import modelo.FormatoFichero;
import modelo.FormatoFicheroFactory;
import modelo.IFormatoFicheroNotificable;
import modelo.Importador;
import modelo.Modulo;
import vista.ViewAdminCampanya;

/**
 *
 * @author EzequielRodriguez*/
 
public class CtrAdminCampanyas implements ActionListener,ListSelectionListener,MouseListener {
    
    private ViewAdminCampanya vc;
    private CtrAdminModulos ctrant;
    private CtrAdminMedidas ctrs;
    private Modulo ms;

    public CtrAdminCampanyas(ViewAdminCampanya v) {
        vc=v;
        vc.setControlador(this);
    }
    
    public void setModulos(Modulo ms){
       this.ms = ms;
        consultarCampañas();
    }
    
    private void consultarCampañas() {
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
                
            case ViewAdminCampanya.IMPORTAR:
                importar();
                break;
                       
        }
    }
    
    public void vistaAnterior(){
        vc.vistaAnterior();
        ctrant.consultarModulos();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            campañaSeleccionada();
        }
    }
    
    public void verMedidas(){
        Campaña c= vc.getCampañaSeleccionada();
        vc.siguienteVista();
        ctrs.setMedidas(c);        
    }

    private void importar() {
       File f = vc.mostrarSelectorFicheros();
       
       if (f != null) {
           vc.mostrarVistaImportacion(FormatoFicheroFactory.FORMATO_CAMPAÑA, f);
           consultarCampañas();
       }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            verMedidas();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

}
