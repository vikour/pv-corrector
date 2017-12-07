
package controlador;

import vista.ViewImportacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import modelo.FormatoFichero;
import modelo.FormatoFicheroFactory;
import modelo.Importador;
import modelo.ImportadorNotificable;


public class CtrImportacion implements ActionListener, ImportadorNotificable, PropertyChangeListener  {
    private ViewImportacion view;
    private int option;
    private Importador imp;
    

    public CtrImportacion(ViewImportacion view) {
        this.view = view;
        view.setControlador(this);
    }
    
    public synchronized void setOption(int option) {
        
        try {
            
            while (this.option != -1) // Not consumed.
                wait();
            
            this.option = option;
            notifyAll();
        }
        catch (InterruptedException ex) {
            System.err.println("Error de concurrencia : CtrImportacion.class");
        }
            
    }
    
    public synchronized int getOption() {
        int value = ImportadorNotificable.OK;
        
        try {
            
            while (option == -1)
                wait();
            
            value = option;
            option = -1; // Release for productor.
            view.limpiar();
            view.notificar("Importación en marcha\nEspere un segundo...");
            notifyAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Error de concurrencia : CtrImportación.class");
        }
        
        return value;
    }
    
    public void iniciar(String format, File file) { // Called by view.
        FormatoFicheroFactory ficheroFactory = new FormatoFicheroFactory();
        FormatoFichero ff = ficheroFactory.create(format);
        view.notificar("Importación en marcha\nEspere un segundo...");
        imp = new Importador(ff, file, this);
        imp.addPropertyChangeListener(this);
        option = -1;
        imp.execute(); // New thread!!
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            
            case ViewImportacion.CMD_OK:
                imp = null;
                view.ocultar();
                break;
                
            case ViewImportacion.CMD_OMITIR:
                setOption(ImportadorNotificable.OK);
                break;
                
            case ViewImportacion.CMD_OMITIR_TODO:
                setOption(ImportadorNotificable.IGNORAR_TODO);
                break;
                
            case ViewImportacion.CMD_SOBRESCRIBIR:
                setOption(ImportadorNotificable.SOBREESCRIBIR);
                break;
                
            case ViewImportacion.CMD_SOBRESCRIBIR_TODO:
                setOption(ImportadorNotificable.SOBREESCRIBIR_TODO);
                break;
                
        }
        
    }

    @Override
    public int notificarErorFormato(File f) { // Called by thread importador.
        view.notificar("Error en el formato del fichero: \n" + f.getName());
        view.setModo(ViewImportacion.MODO_OMITIR);
        return getOption();
    }
    
    @Override
    public int notificarSobreescribir(File f, Object[] key) { // Called by thread importador.
        view.notificar("El contenido del fichero " + f.getName() + " ya existe\n" +
                       "¿Quieres sobrescribirlo?");
        view.setModo(ViewImportacion.MODO_SOBRESCRIBIR);
        return getOption();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        if ("state".equals(evt.getPropertyName()) && "DONE".equals(evt.getNewValue().toString())) {
            view.setModo(ViewImportacion.MODO_MESSAGE);
            view.notificar("!Importación completada!");
        }
        else if ("progress".equals(evt.getPropertyName())) 
            view.setProgress((Integer) evt.getNewValue());
        
    }
    
}
