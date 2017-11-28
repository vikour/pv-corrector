/**
 * @(#) CurvaIV.java
 */

package modelo;

public abstract class CurvaIV
{
   private MedidaIntensidad[] intensidades;
   
   private MedidaTension[] tensiones;
   
   private String fecha;
   
   private String hora;
   
   private Medida isc;
   
   private Medida voc;
   
   private Medida pmax;
   
   private Medida vmax;
   
   private double ff;
   
   private int id;
   
    public MedidaIntensidad[] getIntensidades() {
        return intensidades;
    }

    public MedidaTension[] getTensiones() {
        return tensiones;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Medida getIsc() {
        return isc;
    }

    public Medida getVoc() {
        return voc;
    }

    public Medida getPmax() {
        return pmax;
    }

    public Medida getVmax() {
        return vmax;
    }

    public double getFf() {
        return ff;
    }

    public int getId() {
        return id;
    }
   
}
