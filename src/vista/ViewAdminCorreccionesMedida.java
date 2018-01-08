/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrAdminCorrecionesMedida;
import modelo.CurvaCorregida;

/**
 *
 * @author Vikour
 */
public interface ViewAdminCorreccionesMedida {

    public void mostrar(CurvaCorregida[] correcciones);

    public void habilitarBorrar(boolean b);

    public void habilitarGrafica(boolean b);

    public void habilitarExportar(boolean b);
    
    public void setControlador(CtrAdminCorrecionesMedida ctr);

    public void vistaAnterior();
    
    public static enum Cmd {
        EXPORTAR,
        BORRAR,
        GRAFICA,
        MEDIDAS
    };
    
}
