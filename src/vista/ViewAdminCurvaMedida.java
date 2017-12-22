/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrAdminCurvaMedida;
import java.util.List;
import modelo.MedidaIntensidad;
import modelo.MedidaTension;

/**
 *
 * @author Elias
 */
public interface ViewAdminCurvaMedida {

    
    void setControlador(CtrAdminCurvaMedida ctr);
    
    void visualizaGrafica(List<MedidaTension> tensiones, List<MedidaIntensidad> intensidades, Object[] datos);
    
    
}
