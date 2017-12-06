
package modelo;

import java.io.File;

public interface ImportadorNotificable {
    
     public static int OK = 0;
     public static int IGNORAR_TODO = 1;
     public static int SOBREESCRIBIR = 2;
     public static int SOBREESCRIBIR_TODO = 3;
     
     int notificarErorFormato(File f);
     int notificarSobreescribir(File f, Object [] key);
    
}
