/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class ConfiguracionCorreccion {
   
   private MedidaCanal irradiancia;
   private MedidaCanal temperatura;
   private double valorI;
   private double valorT;

   public ConfiguracionCorreccion(MedidaCanal irradiancia, MedidaCanal temperatura, double valorI, double valorT, CurvaIV referencia) {
      this.irradiancia = irradiancia;
      this.temperatura = temperatura;
      this.valorI = valorI;
      this.valorT = valorT;
   }

   public MedidaCanal getIrradiancia() {
      return irradiancia;
   }

   public MedidaCanal getTemperatura() {
      return temperatura;
   }

   public double getValorI() {
      return valorI;
   }

   public double getValorT() {
      return valorT;
   }

   public CurvaMedida getReferencia() {
      return AlmacenCurvasMedidas.getInstance().buscar(irradiancia.getIdc());
   }
   
}
