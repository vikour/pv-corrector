
package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Esta clase sirve para crear campañas en el sistema. Desde esta clase se crean y
 * almacenan las campañas que hay en memoria, manteniendo la consistencia.
 * 
 * @author Víctor Manuel Ortiz Guardeño
 */

public class AlmacenCampañas {

    private static AlmacenCampañas instancia = null;
    public Map<Modulo,List<Campaña>> campañas;
    public Map<Modulo,Boolean> registroConsultaEntera;
    
    public static AlmacenCampañas getInstance() {
        
        if (instancia == null)
            instancia = new AlmacenCampañas();
        
        return instancia;
    }
    
    private AlmacenCampañas() {
        campañas = new HashMap<>();
        registroConsultaEntera = new HashMap<>();
    }
    
    
    /**
     * Permite buscar en el sistema todas las campañas de un módulo.
     * 
     * @param modulo Referencia al módulo.
     * 
     * @return Un array con las referencias a las campañas del módulo pasado como argumento.
     */
    
    
    public Campaña [] buscar(Modulo modulo) {
        Boolean consultaAnterior = registroConsultaEntera.get(modulo);
        List<Campaña> list;
        
        if (consultaAnterior == null) 
            list = cargarTodasCampañasBD(modulo);
        else if (consultaAnterior.equals(Boolean.FALSE)) 
            list = mezclarCampañasBDCache(modulo);
        else 
            list = campañas.get(modulo);
        
        
        return list.toArray(new Campaña[list.size()]);
    }
    
    /**
     * Permite buscar en el sistema una campaña por su referencia a un modulo y nombre.
     * 
     * @param modulo Referencia al módulo.
     * @param nombre Nombre de la campaña.
     * @return Referencia a la campaña con el modulo y nombre pasados como argumento.
     */
    
    public Campaña buscar(Modulo modulo, String nombre) {
        List<Campaña> list = campañas.get(modulo);
        Campaña result;
        
        if (list == null) {
            list = new ArrayList<>();
            campañas.put(modulo, list);
            registroConsultaEntera.put(modulo, Boolean.FALSE);
        }

        result = buscarCampañaCacheado(list, nombre);

        if (result == null) {
            result = Campaña.buscar(modulo, nombre);
            
            if (result != null)
                list.add(result);
            
        }
            
        
        return result;
    }
    
    /**
     * Permite buscar en el sistema una campaña por el nombre de un modulo y campaña.
     * 
     * @param nModulo Nombre del módulo.
     * @param nCampaña Nombre de la campaña.
     * 
     * @return Referencia a la campaña con el modulo y nombre pasados como argumento.
     */
    
    public Campaña buscar(String nModulo, String nCampaña) {
        Modulo modulo = Modulo.buscar(nModulo);
        Campaña result = null;
        
        if (modulo != null)
            result = buscar(modulo, nCampaña);
        
        return result;
    }
    
    /**
     * Crea una nueva campaña en el sistema a partir de el módulo y el nombre de
     * la campaña.
     * 
     * @param modulo  Referencia al módulo.
     * @param nombre  Nombre de la campaña.
     * @return  Referencia a la nueva campaña.
     * 
     * @throws Error Si la campaña ya existía.
     */
    
    public Campaña nueva(Modulo modulo, String nombre) throws Error {
        Campaña result;
        List<Campaña> list;
        
        result = new Campaña(modulo, nombre);
        list = campañas.get(modulo);
        
        if (list == null) {
            list =new ArrayList<>();
            list.add(result);
            campañas.put(modulo, list);
            registroConsultaEntera.put(modulo, Boolean.FALSE);
        }
        else
            list.add(result);
        
        
        return result;
    }
    
    /**
     * Crea una nueva campaña en el sistema a partir del nombre del módulo y de la campaña.
     * 
     * @param nModulo  Nombre del módulo.
     * @param nCampaña  Nombre de la campaña.
     * @return  Referencia a la nueva campaña.
     * 
     * @throws Error Si la campaña ya existía.
     */
    
    public Campaña nueva(String nModulo, String nCampaña) throws Error {
        Modulo m = AlmacenModulos.getInstance().buscar(nModulo);
        Campaña result = null;
        
        if (m != null)
            result = nueva(m,nCampaña);
        
        return result;
    }
    
    /**
     * Es llamado cuando se borra un módulo. Elimina el módulo del diccionario junto
     * con sus campañas.
     * 
     * @param modulo Módulo que se ha borrado.
     */
    
    void moduloBorrado(Modulo modulo) {
        
        if (campañas.get(modulo) != null) {
            campañas.put(modulo, new ArrayList<>()); // ahora sabemos que no tiene ninguna campaña
        }
        
    }

    private List<Campaña> cargarTodasCampañasBD(Modulo modulo) {
        List list = Campaña.buscar(modulo);
        
        campañas.put(modulo, list);
        registroConsultaEntera.put(modulo, Boolean.TRUE);
        
        return list;
    }

    private List<Campaña> mezclarCampañasBDCache(Modulo modulo) {
        List list = campañas.get(modulo);
        
        for (Campaña campaña : Campaña.buscar(modulo))
            
            if (!list.contains(campaña))
                list.add(campaña);
        
        registroConsultaEntera.put(modulo, Boolean.TRUE);
        
        return list;
    }
    

    private Campaña buscarCampañaCacheado(List<Campaña> campañas, String nombre) {
        Campaña result = null;
        Campaña aux = null;
        Iterator<Campaña> it = campañas.iterator();
        
        while (it.hasNext() && result == null) {
            aux = it.next();
            
            if (aux.getNombre().equals(nombre))
                result = aux;
            
        }
        
        return result;
    }
    
}
