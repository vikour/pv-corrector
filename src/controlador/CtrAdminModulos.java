/**
 * @(#) CtrAdminModulos.java
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Modulo;
import vista.ViewAdminModulos;


public class CtrAdminModulos implements ActionListener {
  private ViewAdminModulos vm;

    public CtrAdminModulos(ViewAdminModulos vm) {
        this.vm = vm;
        vm.setControlador(this);
    }

   public void consultarModulos()
   {
      List<Modulo>m = Modulo.listar();
      vm.mostrar(m);
   }
   
   public void moduloSeleccionado( )
   {
      
   }
   
   public void importar( )
   {

   }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
