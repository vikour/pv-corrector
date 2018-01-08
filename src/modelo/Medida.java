/**
 * @(#) Medida.java
 */

package modelo;

public class Medida
{
   private static final char STR_ESCALA [] = {'p','n','µ','m',' ', 'k'};
   private static final double VAL_ESCALA [] = {10E-12,10E-9,10E-6,10E-3,1,10E3};
   private static final int MULTIPLICAR = 0;
   private static final int DIVIDIR = 1;
   
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
    
    public double getValorInternacional() {
       double result = 0;
       String parts [];
       
       if (magnitud.contains("*")) {
          parts = magnitud.split("*");
          result = valor * operar(parts[0],parts[1],MULTIPLICAR);
       }
       else if (magnitud.contains("/")) {
          parts = magnitud.split("/");
          result = valor * operar(parts[0], parts[1], DIVIDIR);
       }
       else 
          result = valor * multiplicadorEscala(magnitud);
       
       return result;
    }

    @Override
    public String toString() {
        return "" + String.format("%.4f", valor) + " " + magnitud;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Medida) &&
               (((Medida) obj).valor == valor) &&
               (((Medida) obj).magnitud.equals(magnitud));
    }

   private double operar(String a, String b, int operacion) {
      double ma = multiplicadorEscala(a);
      double mb = multiplicadorEscala(b);
      
      if (operacion == MULTIPLICAR)
         ma *= mb;
      else if (operacion == DIVIDIR)
         ma /= mb;
      
      return ma;
   }

   private double multiplicadorEscala(String magnitud) {
      double multiplicador = 1;
      int escala = -1;
      int i = 0;
      
      magnitud = magnitud.trim();
      
      if (magnitud.length() > 1) {
         
         while (escala < 0 && i < STR_ESCALA.length)  {
            
            if (magnitud.charAt(0) == STR_ESCALA[i])
               escala = i;
            
            i++;
         }
         
         if (escala >= 0)
            multiplicador = VAL_ESCALA[escala];
            
      }
      
      return multiplicador;
   }
    
}
