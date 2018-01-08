/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.ViewAdminCurvaMedida;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.CurvaMedida;
import modelo.Medida;
import modelo.MedidaIntensidad;
import modelo.MedidaTension;
import vista.curvamedida.jFrameCurvas;

/**
 *
 * @author Elias
 */
public class CtrAdminGrafica {
    
    private ViewAdminCurvaMedida vcm;
    private CtrAdminMedidas cam;

    public CtrAdminGrafica(ViewAdminCurvaMedida vcm) {
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
        double ff=c.getFf();
        int id=c.getId();
        List<MedidaTension> tensiones=c.getTensiones();
        List<MedidaIntensidad> intensidades=c.getIntensidades();
        
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
