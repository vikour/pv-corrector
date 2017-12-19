/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.MedidaIntensidad;
import modelo.MedidaTension;

/**
 *
 * @author Elias
 */
public interface ViewAdminCurvaMedida {

    public void visualizaGrafica(List<MedidaTension> tensiones, List<MedidaIntensidad> intensidades, Object[] datos);
    
    
}
