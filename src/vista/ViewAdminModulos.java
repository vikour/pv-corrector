/**
 * @(#) ViewAdminModulos.java
 */

package vista;

import controlador.CtrAdminModulos;
import java.io.File;
import java.util.List;

import modelo.Modulo;

public interface ViewAdminModulos
{
    public static final String IMPORTAR="IMPORTAR";
    public static final String EXPORTAR="EXPORTAR";
    public static final String MODIFICAR="MODIFICAR";
    public static final String BORRAR="BORRAR";
    public static final String CAMPANYAS="CAMPANYAS";
    public static final String SELECC_MODULO="SELEC_MODULO";
    public static final String IDIOMA="IDIOMA";
    
   void mostrar( List<Modulo> modulos );
   
   void setControlador( CtrAdminModulos controlador );
   
   void habilitarModificacion( boolean habilitar );
   
   void habilitarBorrado( boolean habilitar );
   
   void habilitarExportacion( boolean habilitar );
   
   void habilitarVerCampañas( boolean habilitar );
   
   void mostrarModuloNuevo( Modulo m );
   
   Modulo getModulosSeleccionados();

   public File mostrarSelectorFicheros();

   public void alert(String message);

   public boolean preguntar(String string);
   
   
}
