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
   private double alpha;
   private double beta;
   private double rs;
   private double kappa;
   private double a;

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

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getRs() {
        return rs;
    }

    public void setRs(double rs) {
        this.rs = rs;
    }

    public double getKappa() {
        return kappa;
    }

    public void setKappa(double kappa) {
        this.kappa = kappa;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }
   
   
}
