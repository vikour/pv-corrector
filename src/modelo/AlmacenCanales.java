/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Elias
 */
public class AlmacenCanales {
    
    private static AlmacenCanales almacen=null;
    List<Canal> canales;
    
    /*
    *@return Devuelve la instancia del almacen de canales.
    */
    
    public static AlmacenCanales getInstance(){
        
        if(almacen==null){
            almacen=new AlmacenCanales();
        }
        return almacen;        
    }
    
    private AlmacenCanales(){
        canales=new ArrayList<>();
    }
    
    /**
     * Crea un nuevo canal en el sistema.
     * 
     * @param nombre Nombre que recibira el canal que se cree
     * 
     * @throws Error si el canal ya existe en el sistema
     * 
     * @return Devuelve el canal que se ha creado
     * 
     * 
     * 
     */
    public Canal nuevo(String nombre) throws Error{
        Canal n=null;
        
        n=new Canal(nombre);
        canales.add(n);
        
        return n;
    }
    /**
     * Busca un canal por nombre en el sistema y lo devuelve
     * @param nombre El nombre del canal a buscar.
     * @return La referencia al canal.
     */
    public Canal buscar(String nombre){
        Canal c = buscarEnCanales(nombre);
        
        if(c==null){
            c=new Canal();
            c.buscar(nombre);
            canales.add(c);
        }
        return c;
    }
    
    
    public boolean existe(String nombre){
        return buscar(nombre)!=null;
    }
    
    
    
    /**
     * Busca en la lista el canal cuyo nombre se la pasa como parametro
     * @param nombre El nombre del canal que quieres buscar en la lista de canales
     * @return El canal que buscas o null si no esta en la lista
     */
    
    private Canal buscarEnCanales(String nombre){
        Canal ret=null;
        Canal aux=null;
        
        Iterator<Canal> it=canales.iterator();
        
        while(it.hasNext() && ret==null){
            aux=it.next();
            if(aux.getNombre().equals(nombre)){
                ret=aux;
            }
        }
        return ret;
    }
    
    
    
    
    
    
    
}
