/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 
public class CtrAdminCampanyas implements ActionListener,ListSelectionListener, IFormatoFicheroNotificable {
    
    private ViewAdminCampanya vc;
    private CtrAdminModulos ctrant;
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

    private void importar() {
       FormatoFicheroFactory fffact = new FormatoFicheroFactory();
       FormatoFichero ff = fffact.create(FormatoFicheroFactory.FORMATO_CAMPAÑA, this);
       Importador importador = null;
       File f = vc.mostrarSelectorFicheros();
       
       if (f != null) try {
           importador = new Importador(ff, f);
           importador.importar();
           consultarCampañas();
           vc.informar("Se ha completado la importación con éxito.");
       }
       catch (IOException ex) {
           vc.alert(ex.getMessage());
       }
       
    }

    @Override
    public void alertFormatoFichero(String mensaje) {
        vc.alert(mensaje);
    }

    @Override
    public boolean confirmSobrescribirFormatoFichero(Object[] key) {
        return vc.preguntar("La campaña " + key [0] + " " + key[1] + " ya está en el sistema. ¿Quieres sobreescribirlo?");
    }

    

    
    
   
    
}
