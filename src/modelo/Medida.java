/**
 * @(#) Medida.java
 */

package modelo;

public class Medida
{
   protected double valor;
   protected String magnitud;

    public Medida(double valor, String magnitud) {
        this.valor = valor;
        this.magnitud = magnitud;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    @Override
    public String toString() {
        return "valor : " + valor + ", magnitud : " + magnitud;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Medida) &&
               (((Medida) obj).valor == valor) &&
               (((Medida) obj).magnitud.equals(magnitud));
    }
    
}
