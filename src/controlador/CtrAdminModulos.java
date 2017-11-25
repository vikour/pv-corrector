/**
 * @(#) CtrAdminModulos.java
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Modulo;
import vista.ViewAdminModulos;

public class CtrAdminModulos
{
  private ViewAdminModulos vm;

    public CtrAdminModulos(ViewAdminModulos vm) {
        this.vm = vm;
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
   
}
