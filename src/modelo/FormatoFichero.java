
package modelo;

import java.io.File;

public abstract class FormatoFichero {
    
    protected IFormatoFicheroNotificable notificar;

    public FormatoFichero(IFormatoFicheroNotificable notificar) {
        this.notificar = notificar;
    }
    
    public abstract void leer(File file);
    public abstract void escribir(File file);
    
}
