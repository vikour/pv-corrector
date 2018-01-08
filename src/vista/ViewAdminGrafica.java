/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrAdminGrafica;
import java.util.List;
import modelo.MedidaCurva;
import modelo.MedidaIntensidad;
import modelo.MedidaTension;

/**
 *
 * @author Elias
 */
public interface ViewAdminGrafica {

    
    void setControlador(CtrAdminGrafica ctr);
    
    void visualizaGrafica(List<MedidaCurva> tensiones, List<MedidaCurva> intensidades, Object[] datos);
    
    void muestrate();
    
}
