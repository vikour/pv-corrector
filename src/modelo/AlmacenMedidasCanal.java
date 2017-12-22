/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.CurvaMedida;

/**
 *
 * @author Elias
 */
public class AlmacenMedidasCanal {
    private List<MedidaCanal> medidas;
    private int idc;
    private boolean todas;
    
    /**
     * Constructor del almacen.
     * @param c Curva Medida que se usara para todas las operaciones del almacen.
     */
    public AlmacenMedidasCanal(CurvaMedida c){
        idc=c.getId();
        todas=false;
        medidas=new ArrayList<>();
    }
    
    
    /**
     * Crea una nueva medida y la almacena en la lista de medidas
     * @param c Canal indicado para la medida
     * @param valor Valor de la medida.
     * @param magnitud Magnitud de la medida.
     * @return La medida construida
     * @throws Error  En caso de que la medida exista en la base de datos.
     */
    public MedidaCanal nuevo(Canal c, double valor, String magnitud) throws Error{
        MedidaCanal res=null;
        
        res=new MedidaCanal(valor, magnitud, c, idc);
        //System.out.println(res.toString());
        medidas.add(res);
        
        return res;
    }
    
    /**
     * Buisca una medida de la curva asociada.
     * @param c Canal del que se quiere obtener la medida
     * @return La medida obtenida, o null en caso de que no se encuentre.
     */
    public MedidaCanal buscar(Canal c){
        MedidaCanal mc /*men*/ =buscarEnMedidas(c);
        if(mc==null){
            mc= new MedidaCanal();
            mc.buscar(c, idc);
            medidas.add(mc);
        }
        return mc /*men*/;
    }
    
    /**
     * Busca todas las medidas, con los canales asociados a la curva identificada por idc
     * @return Un array con todas las medidas de los canales obtenidas.
     */
    public MedidaCanal[] buscarTodas(){
        
        if(!todas){
            medidas=MedidaCanal.buscar(idc);
            todas=true;
        }
        return medidas.toArray(new MedidaCanal[medidas.size()]);
    }

    /**
     * Busca en la lista de medidas si existe la que tiene por canal el pasado por parametro, es un precaching, antes de acceder a la base de datos
     * @param c Canal del que se quiere obtener la medida
     * @return La medida, o null si no la encuentra
     */
    private MedidaCanal buscarEnMedidas(Canal c) {
        Iterator<MedidaCanal> it=medidas.iterator();
        MedidaCanal aux=null;
        MedidaCanal res=null;
        
        while(it.hasNext() && res==null){
            
            aux=it.next();
            if(aux.getCanal().equals(c)){
                res=aux;
            }
        }
        return res;
    }
    
    /**
     * Devuelve todos los canales asociados a la curva asociada al objeto
     * @return Una lista con todos los canales de la curva
     */
    public Canal[] buscarCanales(){
        Canal[] c;
        if(!todas){
            buscarTodas();
        }
        c=new Canal[medidas.size()];
        int i=0;
        for(MedidaCanal mc : medidas){
            c[i]=mc.getCanal();
            i++;
        }
        return c;
    }
    
    
    
}
