/**
 * @(#) CtrAdminModulos.java
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.stage.FileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Campaña;
import modelo.FormatoFichero;
import modelo.FormatoFicheroFactory;
import modelo.IFormatoFicheroNotificable;
import modelo.Importador;
import modelo.Modulo;
import vista.ViewAdminCampanya;
import vista.ViewAdminModulos;


public class CtrAdminModulos implements ActionListener, IFormatoFicheroNotificable,ListSelectionListener {
  private ViewAdminModulos vm;
  private CtrAdminCampanyas ctrs;

    public CtrAdminModulos(ViewAdminModulos vm) {
        this.vm = vm;
        vm.setControlador(this);
        ctrs=null;
       
       
    }

   public void consultarModulos()
   {
       vm.mostrar(Modulo.listar());
   }
   
   public void consultarCampanyas(){
      Modulo m= vm.getModulosSeleccionados();
      ctrs.setModulos(m);
      vm.siguienteVista();
      
      
    }

    public void setCtrSiguiente(CtrAdminCampanyas ctrs) {
        this.ctrs = ctrs;
    }
   
   
   
   public void moduloSeleccionado( )
   {
       
      
      vm.habilitarBorrado(true);
      vm.habilitarExportacion(true);
      vm.habilitarModificacion(true);
      vm.habilitarVerCampañas(true);
   }
   
   public void importar()   {
       FormatoFicheroFactory fffact = new FormatoFicheroFactory();
       FormatoFichero ff = fffact.create(FormatoFicheroFactory.FORMATO_MODULO);
       Importador importador = null;
       File f = vm.mostrarSelectorFicheros();
       
       if (f != null) try {
           importador = new Importador(ff, f);
           importador.importar();
           consultarModulos();
       }
       catch (IOException ex) {
           vm.alert(ex.getMessage());
       }
       
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
            
            case ViewAdminModulos.IMPORTAR:
                importar();
                break;
            case ViewAdminModulos.SELECC_MODULO:
                moduloSeleccionado();
                break;
            
            case ViewAdminModulos.CAMPANYAS:
                consultarCampanyas();
                break;
        }
        
    }

    @Override
    public void alertFormatoFichero(String mensaje) {
        vm.alert(mensaje);
    }

    @Override
    public boolean confirmSobrescribirFormatoFichero(Object[] key) {
        return vm.preguntar("El modulo " + key [0] + " ya está en el sistema. ¿Quieres sobreescribirlo?");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
       if (!e.getValueIsAdjusting()){
           moduloSeleccionado();
       }
    }
    
    

   
   
   
}
