
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Esta clase sirve para crear los módulos del sistema. Desde esta clase se crean y
 * almacenan los módulos que hay en memoria.
 * 
 * @author Víctor Manuel Ortiz Guardeño
 */

public class AlmacenModulos {
    
    private static AlmacenModulos instance = null;
    private List<Modulo> modulos;
    private boolean todosCargados;

    public static AlmacenModulos getInstance() {
        
        if (instance == null)
            instance = new AlmacenModulos();
        
        return instance;
    }
    
    private AlmacenModulos() {
        modulos = new ArrayList<>();
        todosCargados = false;
    }
    
    /**
     * Crea un nuevo modulo en el sistema.
     * 
     * @param nombre Nombre del módulo.
     * 
     * @throws Error si el módulo ya existe.
     * 
     * @return Referencia al módulo.
     */
    
    public Modulo nuevo(String nombre) throws Error{
        Modulo result;
        
        result = new Modulo(nombre); // throws error ya existe.
        modulos.add(result);
        
        return result;
    }
    
    /**
     * Busca un módulo por su nombre en el sistema y devuelve su referencia.
     * 
     * @param nombre Nombre del módulo.
     * @return Referencia al módulo.
     */
    
    public Modulo buscar(String nombre) {
        Modulo result = buscarModuloCacheado(nombre);

        if (result == null) {
            result = Modulo.buscar(nombre);
            
            if (result != null)
               modulos.add(result);
        }
        
        return result;
    }
    
    /**
     * Sirve para preguntar si un módulo existe en el sistema.
     * 
     * @param nombre Nombre del módulo buscado.
     * 
     * @return Verdadero si existe, falso si no.
     */
    
    public boolean existe(String nombre) {
        return buscar(nombre) != null;
    }
    
    /**
     * Elimina el módulo en la memoria y base de datos. El borrado de un módulo
     * también elimina sus campañas, curvas corregidas o medidas.
     * 
     * @param nombre Nombre del módulo
     * 
     * @throws Error Si el módulo no existe.
     */
    
    public void eliminar(String nombre) {
        Modulo m = buscar(nombre);
        
        if (m == null)
            throw new Error("El módulo con nombre " + nombre + " no existe en el sistema.");
        
        Modulo.Borrar(nombre);
        modulos.remove(modulos.indexOf(m));
        AlmacenCampañas.getInstance().moduloBorrado(m);
    }
    
    /**
     * Busca todas las instancias de módulos en el sistema.
     * 
     * @return Una lista de módulos.
     */
    
    public Modulo [] buscarTodos() {
        List<Modulo> aux;
        
        if (!todosCargados) {
            
            if (!modulos.isEmpty())
                mezclarModulosBDCache();
            else
                modulos = Modulo.buscarTodos();
            
            todosCargados = true;
        }
        
        return modulos.toArray(new Modulo[modulos.size()]);
    }

    private void mezclarModulosBDCache() {
        
        for (Modulo m : Modulo.buscarTodos())
            
            if (!modulos.contains(m))
                modulos.add(m);
        
    }

    private Modulo buscarModuloCacheado(String nombre) {
        Modulo result = null;
        Modulo aux = null;
        Iterator<Modulo> it = modulos.iterator();
        
        while (it.hasNext() && result == null) {
            aux = it.next();
            
            if (aux.getNombre().equals(nombre))
                result = aux;
            
        }
        
        return result;
    }
    
}
