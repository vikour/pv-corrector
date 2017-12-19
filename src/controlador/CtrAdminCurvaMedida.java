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
import modelo.MedidaIntensidad;
import modelo.MedidaTension;

/**
 *
 * @author Elias
 */
class CtrAdminCurvaMedida {
    
    ViewAdminCurvaMedida vcm;

    public CtrAdminCurvaMedida(ViewAdminCurvaMedida vcm) {
        this.vcm = vcm;
    }
    
    void showCurva(CurvaMedida c) {
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
    
    }
}
