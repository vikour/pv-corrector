/**
 * @(#) Campaña.java
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

public class Campaña {

    private Modulo modulo;
    private String nombre;

    /**
     * Inserta una campaña en la base de datos.
     * 
     * @param modulo Referencia al módulo que pertenece la nueva campaña.
     * @param nombre Nombre de la campaña
     * @throws Error Si la campaña existe.
     */
    
    Campaña(Modulo modulo, String nombre) throws Error {
        String insert = "INSERT INTO campanyas VALUES ('" + nombre + "','" + modulo.getNombre() + "')";
        BD bd = BD.getInstance();

        bd.insert(insert);
        this.modulo = modulo;
        this.nombre = nombre;
    }
    
    /**
     * Para construcciones internas.
     */
    
    private Campaña() {
        
    }
    
    static Campaña buscar(Modulo modulo, String nombre) {
        Campaña result = null;
        String select = "SELECT * FROM campanyas WHERE nombre = '" + nombre + "' AND "
                + "modulo = '" + modulo.getNombre() + "';";
        
        if (!BD.getInstance().select(select).isEmpty()) {
            result = new Campaña();
            result.modulo = modulo;
            result.nombre = nombre;
        }
        
        return result;
    }
    
    static List<Campaña> buscar(Modulo modulo) {
        List<Campaña> result = new ArrayList<>();
        String select = "SELECT * FROM campanyas WHERE modulo = '" + modulo.getNombre() + "'";
        Campaña campaña;
        
        for (String tupla [] : BD.getInstance().select(select)) {
            campaña = new Campaña();
            campaña.nombre = tupla[0];
            campaña.modulo = AlmacenModulos.getInstance().buscar(tupla[1]);
            result.add(campaña);
        }
        
        return result;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        String update = "UPDATE campanyas SET modulo = '" + modulo.getNombre() + "' "
                + "WHERE nombre = '" + this.nombre + "' AND "
                + " modulo = '" + this.modulo.getNombre() + "'";

        if (!modulo.equals(this.modulo)) {
            BD.getInstance().update(update);
            this.modulo = modulo;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String update = "UPDATE campanyas SET nombre = '" + nombre + "' "
                + "WHERE nombre = '" + this.nombre + "' AND "
                + " modulo = '" + modulo.getNombre() + "'";

        if (!this.nombre.equals(nombre)) {
            BD.getInstance().update(update);
            this.nombre = nombre;
        }
    }

    public CurvaMedida [] getCurvas() {
       return AlmacenCurvasMedidas.getInstance().buscar(this);
    }

    public List<Canal> getCanales() {

        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "nombre : " + nombre + ", modulo : " + modulo.getNombre();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Campaña)
                && (((Campaña) obj).modulo.equals(modulo))
                && (((Campaña) obj).nombre.equals(nombre));
    }

}
