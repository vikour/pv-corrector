/**
 * @(#) Canal.java
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.BD;
import modelo.Campaña;

public class Canal {


    
    /**
    * 
    * @deprecated Usar el método {@link AlmacenMedidasCanal##buscarCanales()}
    * @param c Campaña de la que queremos extraer los canales
    * @return null
    */
    

    public static List<Canal> listar(Campaña c) {
        return null;
    }
    
    
    private String nombre;

    
    /**
    * @param nombre Nombre del canal que queremos crear
    * @return 
    */
    
    Canal(String nombre) throws Error{
        BD bd=BD.getInstance();
        
        bd.insert("INSERT INTO canales VALUES ('"+nombre+"')");
        this.nombre=nombre;
       
    }
    
    Canal() { }

    

    /**
    * @param nombre El nombre del canal a buscar
    * @return Devuelve un canal nuevo con el nombre que  
    */
    static Canal buscar(String nombre){
        BD bd=BD.getInstance();
        Canal c =null;
        List<String[]>l= bd.select("SELECT * FROM canales WHERE nombre='"+nombre+"'");
        if(!l.isEmpty()){
            c=new Canal();
            c.setNombre( l.get(0)[0]);
        }
        return c;
    }

    
    /**
    * 
    * @return Devuelve el nombre del canal. 
    */
    public String getNombre() {
        return nombre;

    }
    
    /**
    * @param nombre El nuevo nombre del canal
    * @return 
    */
    public void setNombre(String nombre) {
        BD bd=BD.getInstance();
        bd.update("UPDATE canales SET nombre='"+nombre+"' WHERE nombre='"+this.nombre+"' ;");
        this.nombre = nombre;
    }


    @Override
    public boolean equals(Object o){
        
        if(o instanceof Canal){
            Canal aux= (Canal) o;
            if(this.nombre.equals(((Canal) o).nombre)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }


}
    
    
    

