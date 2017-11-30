/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrAdminCampanyas;
import java.io.File;
import java.util.List;
import modelo.Campaña;

/**
 *
 * @author Sergio
 */
public interface ViewAdminCampanya {
    public static final String IMPORTAR="IMPORTAR";
    public static final String EXPORTAR="EXPORTAR";
    
    public static final String BORRAR="BORRAR";
    public static final String MEDIDAS="MEDIDAS";
    public static final String MODULO="MODULO";
    public static final String SELECC_CAMPA="SELEC_CAMPA";
    public static final String IDIOMA="IDIOMA"; //no se si estaría bien aqui tambien
    
   void mostrarCampanyas( List<Campaña> campanyas );
   
   void setControlador( CtrAdminCampanyas controlador );
   
   
   void habilitarBorrado( boolean habilitar );
   
   void habilitarExportacion( boolean habilitar );
   
   void habilitarVerMedidas( boolean habilitar );
   
   //void mostrarModuloNuevo( Campaña m );

   //public File mostrarSelectorFicheros();

   //public void alert(String message);

   //public boolean preguntar(String string);
   
   void muestrate();
   
   
}
