/**
 * @(#) Campaña.java
 */
package modelo;

import java.util.List;

public class Campaña {

    static List<Campaña> listar(Modulo aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Modulo modulo;
    private String nombre;
    private List<CurvaMedida> curvas;
    private List<Canal> canales;

    /**
     * @param modulo Referencia al modulo que pertenece la nueva campaña
     * @param nombre Nombre de la nueva campaña.
     * @param store Si store = true, signfica que es de insercción, false es de
     * consulta.
     */
    public Campaña(Modulo modulo, String nombre, boolean store) throws RuntimeException {
        String insert = "INSERT INTO campanyas VALUES ('" + nombre + "','" + modulo.getNombre() + "')";
        String select = "SELECT * FROM campanyas WHERE nombre = '" + nombre + "' AND "
                + "modulo = '" + modulo.getNombre() + "';";
        BD bd = BD.getInstance();

        if (store) {
            bd.insert(insert);
        } else if (bd.select(select).isEmpty()) {
            throw new Error("No existe la campaña con módulo = " + modulo.getNombre() + " y nomre = " + nombre);
        }

        // No hago mas con el select, porque no tiene por ahora más propiedades campaña.
        this.modulo = modulo;
        this.nombre = nombre;
        curvas = null; // Lazy links.
        canales = null;
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

    public List<CurvaMedida> getCurvas() {

        if (curvas == null) {
            curvas = CurvaMedida.listar(this);
        }

        return curvas;
    }

    public List<Canal> getCanales() {

        if (canales == null) {
            canales = Canal.listar(this);
        }

        return canales;
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
