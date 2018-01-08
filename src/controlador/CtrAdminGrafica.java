/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.CurvaMedida;
import modelo.Medida;
import modelo.MedidaCurva;
import modelo.MedidaIntensidad;
import modelo.MedidaTension;
import vista.curvamedida.jFrameCurvas;
import vista.ViewAdminGrafica;

/**
 *
 * @author Elias
 */
public class CtrAdminGrafica {
    
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
        //System.out.println("HOLAAA A A A A ");
        
        Medida isc,voc,pmax,vmax,imax;
        String fecha,hora;
        double ff=c.getFF();
        int id=c.getId();
        List<MedidaCurva> tensiones=c.getTensiones();
        List<MedidaCurva> intensidades=c.getIntensidades();
        
        fecha=c.getFecha();
        hora=c.getHora();
        isc=c.getIsc();
        voc=c.getVoc();
        pmax=c.getPmax();
        vmax=c.getVmax();
        imax=c.getImax();
        Object[] datos={isc,voc,pmax,vmax,imax,fecha,hora,ff,id};
        vcm.visualizaGrafica(tensiones,intensidades,datos);
        vcm.muestrate();
    
    }
}
