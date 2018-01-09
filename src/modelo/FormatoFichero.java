
package modelo;

import java.io.File;

public abstract class FormatoFichero {
    
    protected IFormatoFicheroNotificable notificar;
    
    public void setFormatoNotificable(IFormatoFicheroNotificable ctr) {
        notificar = ctr;
    }
    
    public abstract Object leer(File file);
    public abstract void escribir(File file, Object object);
    public abstract String getExtension();
    
}
