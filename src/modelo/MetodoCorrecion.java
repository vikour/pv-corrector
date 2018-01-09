/**
 * @(#) MetodoCorrecion.java
 */

package modelo;

public abstract class MetodoCorrecion
{
   private Canal irradiancia;
   
   private double valorIrradiancia;
   
   private Canal temperatura;
   
   private double valorTemperatura;
   
   private CurvaMedida original;

   public MetodoCorrecion() {
      irradiancia=null;
      valorIrradiancia=0.0;
      temperatura=null;
      valorTemperatura=0.0;
      original=null;
   }
   
   public abstract CurvaCorregida aplicarCorreccion();
   

   public Canal getIrradiancia() {
      return irradiancia;
   }

   public void setIrradiancia(Canal irradiancia) {
      this.irradiancia = irradiancia;
   }

   public double getValorIrradiancia() {
      return valorIrradiancia;
   }

   public void setValorIrradiancia(double valorIrradiancia) {
      this.valorIrradiancia = valorIrradiancia;
   }

   public Canal getTemperatura() {
      return temperatura;
   }

   public void setTemperatura(Canal temperatura) {
      this.temperatura = temperatura;
   }

   public double getValorTemperatura() {
      return valorTemperatura;
   }

   public void setValorTemperatura(double valorTemperatura) {
      this.valorTemperatura = valorTemperatura;
   }

   public CurvaMedida getCurvaOriginal() {
      return original;
   }

   public void setCurvaOriginal(CurvaMedida original) {
      this.original = original;
   }
   
   
   
   
   
   
   
   
}
