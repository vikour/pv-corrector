/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrAdminMedidas;
import java.util.List;

import modelo.CurvaMedida;

/**
 *
 * @author Sergio
 */
public interface ViewAdminMedidas {
    public static final String EXPORTAR="EXPORTAR";
    public static final String BORRAR="BORRAR";
    public static final String CAMPAÑA="CAMPAÑA";
    public static final String GRAFICA="GRAFICA";
    public static final String SELECC_MEDIDA="SELECC_MEDIDA";
    
    void habilitarBorrar(boolean habilitar);
    
    void habilitarGrafica(boolean habilitar);
    
    void habilitarExportar(boolean habilitar);
    
    void setControlador(CtrAdminMedidas ctr);
    
    void mostrarCurvas(CurvaMedida [] curva);
    
    void vistaAnterior();
    
}
