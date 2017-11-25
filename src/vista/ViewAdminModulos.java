/**
 * @(#) ViewAdminModulos.java
 */

package vista;

import controlador.CtrAdminModulos;
import modelo.Modulo;

public interface ViewAdminModulos
{
   void mostrar( Modulo[] modulos );
   
   void setControlador( CtrAdminModulos controlador );
   
   void habilitarModificacion( boolean habilitar );
   
   void habilitarBorrado( boolean habilitar );
   
   void habilitarExportacion( boolean habilitar );
   
   void habilitarVerCampañas( boolean habilitar );
   
   void mostrarModuloNuevo( Modulo m );
   
   
}
