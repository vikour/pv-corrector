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
import modelo.FormatoFichero;
import modelo.FormatoFicheroFactory;
import modelo.IFormatoFicheroNotificable;
import modelo.Importador;
import modelo.Modulo;
import vista.ViewAdminModulos;


public class CtrAdminModulos implements ActionListener, IFormatoFicheroNotificable {
  private ViewAdminModulos vm;

    public CtrAdminModulos(ViewAdminModulos vm) {
        this.vm = vm;
        vm.setControlador(this);
    }

   public void consultarModulos()
   {
       vm.mostrar(Modulo.listar());
   }
   
   public void moduloSeleccionado( )
   {
      
   }
   
   public void importar()   {
       FormatoFicheroFactory fffact = new FormatoFicheroFactory();
       FormatoFichero ff = fffact.create(FormatoFicheroFactory.FORMATO_MODULO, this);
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
   
   
}
