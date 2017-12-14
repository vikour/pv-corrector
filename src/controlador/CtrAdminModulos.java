/**
 * @(#) CtrAdminModulos.java
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.FormatoFicheroFactory;
import modelo.Modulo;
import vista.ViewAdminModulos;


public class CtrAdminModulos implements ActionListener,ListSelectionListener, MouseListener {
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
       File f = vm.mostrarSelectorFicheros();
       
       if (f != null) {
           vm.mostrarVistaImportacion(FormatoFicheroFactory.FORMATO_MODULO, f);
           consultarModulos();
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
    public void valueChanged(ListSelectionEvent e) {
       if (!e.getValueIsAdjusting()){
           moduloSeleccionado();
       }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            consultarCampanyas();
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
