
package modelo;

import java.io.File;

public abstract class FormatoFichero {
    
    protected IFormatoFicheroNotificable notificar;

    public FormatoFichero(IFormatoFicheroNotificable notificar) {
        this.notificar = notificar;
    }
    
    public abstract Object leer(File file);
    public abstract void escribir(File file);
    
}
