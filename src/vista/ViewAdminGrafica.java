/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrAdminGrafica;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.CurvaCorregida;
import modelo.CurvaIV;
import modelo.CurvaMedida;
import modelo.MedidaCurva;
import modelo.MedidaIntensidad;
import modelo.MedidaTension;

/**
 *
 * @author Elias
 */
public interface ViewAdminGrafica {

    
    void setControlador(ActionListener ctr);
    
    void visualizaGrafica(List<MedidaCurva> tensiones, List<MedidaCurva> intensidades, Object[] datos);
    
    public void muestraGraficaCorregida(CurvaCorregida c);

    public void showCurva(CurvaIV c);

    public void graficaIV();

    public void graficaPV();
    
}
