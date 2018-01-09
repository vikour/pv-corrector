/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.CurvaMedida;
import modelo.Medida;
import modelo.MedidaCurva;
import modelo.MedidaIntensidad;
import modelo.MedidaTension;
import vista.Grafica.jFrameGrafica;
import vista.ViewAdminGrafica;

/**
 *
 * @author Elias
 */
public class CtrAdminGrafica implements ActionListener{
    
    private ViewAdminGrafica vcm;
    private CtrAdminMedidas cam;

    public CtrAdminGrafica(ViewAdminGrafica vcm) {
        this.vcm = vcm;
        vcm.setControlador(this);
        cam=null;
    }
    
    public void setControladorAnterior(CtrAdminMedidas ctr){
        cam=ctr;
    }
    
    
    void showCurva(CurvaMedida c) {
        vcm.showCurva(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       switch(e.getActionCommand()){
           case "CURVA I-V":
               vcm.graficaIV();
               System.out.println("Estoy mostrando la grafica iv");
               break;
           case "CURVA P-V":
               vcm.graficaPV();
               System.out.println("Estoy mostrando la grafica pv");
               break;
       }
           
           
           
    }
    
}
