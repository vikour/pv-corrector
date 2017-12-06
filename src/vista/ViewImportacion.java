
package vista;

import controlador.CtrImportacion;

public interface ViewImportacion {

    public static final int MODO_OMITIR = 1;
    public static final int MODO_SOBRESCRIBIR = 2;
    public static final int MODO_MESSAGE = 3;
    
    public static final String CMD_SOBRESCRIBIR_TODO = "SOBRESCRIBIR_TODO";
    public static final String CMD_SOBRESCRIBIR = "SOBRESCRIBIR";
    public static final String CMD_OMITIR = "OMITIR";
    public static final String CMD_OMITIR_TODO = "OMITIR_TODO";
    public static final String CMD_OK = "OK";
    
    public void setControlador(CtrImportacion ctr);
    public void setModo(int modo);
    public void setProgress(int p);
    public void limpiar();
    public void ocultar();
    public void notificar(String msg);
}
