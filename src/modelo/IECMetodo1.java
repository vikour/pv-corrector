/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import vista.medidas.JPanelCurvas;

/**
 *
 * @author Vikour
 */
public class IECMetodo1 extends MetodoCorrecion {

   public static IECMetodo1 instance = null;
   
   public static IECMetodo1 getInstance() {
      
      if (instance == null)
         instance = new IECMetodo1();
      
      return instance;
   }
   
   private IECMetodo1() {
      super();
   }

   @Override
   public CurvaCorregida aplicarCorreccion() {
      CurvaCorregida curvaCorregida = new CurvaCorregida(this, getCurvaOriginal());
      CurvaMedida original = getCurvaOriginal();
      
      if(getCurvaOriginal()==null || getIrradiancia()==null || getTemperatura()==null){
         throw new NullPointerException("Valores de corrección no válidos");
      }
      
      // 1. calcular los puntos.
      double alpha = original.getModulo().getAlpha();
      double beta = original.getModulo().getBeta();
      double rs = original.getModulo().getRs();
      double k = original.getModulo().getKappa();
      double t1 = original.getMedidaCanal(getTemperatura()).getValor();
      double t2 = getValorTemperatura();
      double g1 = original.getMedidaCanal(getIrradiancia()).getValor();
      double g2 = getValorIrradiancia();
      double isc = original.getModulo().getIsc();
      
      
      // I'[i] = I[i] + Isc(1) * (g2/g1 - 1) + alpha * (t2 - t1)
      double inc = isc * (g2/g1 - 1) + alpha *(t2 - t1);
      String stm_corr_intensidad = "INSERT INTO medidas_curvas " + 
                                   "SELECT " + curvaCorregida.getId() + ", "
                                             + "valor + " + inc + ", magnitud, orden, tipo " +
                                   "FROM medidas_curvas WHERE curva_iv = " + original.getId() + " AND " +
                                         "tipo = " + NombreValorCurva.BD(NombreValorCurva.INTENSIDAD);
      
      BD.getInstance().insert(stm_corr_intensidad);
      
      // V'[i] = V[i] - Rs * (I'[i] - I[i]) - k * I'[i] * (t2 - t1) + beta * (t2 - t1)
      String stm_corr_tension = "INSERT INTO medidas_curvas " +
             "SELECT " + curvaCorregida.getId() + ", old.valor - " + rs + " * (new.valor - oldi.valor) - " + k + " * new.valor * " + (t2 - t1) + " + " + (beta * (t2-t1)) + ", "
                   + "old.magnitud, old.orden, " + NombreValorCurva.BD(NombreValorCurva.TENSION) + " " + 
             "FROM medidas_curvas old, medidas_curvas new, medidas_curvas oldi " +
             "WHERE old.curva_iv = " + original.getId() + " AND old.tipo = " + NombreValorCurva.BD(NombreValorCurva.TENSION) + " AND " +
                   "new.curva_iv = " + curvaCorregida.getId() + " AND new.tipo = " + NombreValorCurva.BD(NombreValorCurva.INTENSIDAD) + "  AND " +
                   "new.orden = old.orden AND new.orden = oldi.orden AND oldi.curva_iv = " + original.getId() + " AND oldi.tipo = " + NombreValorCurva.BD(NombreValorCurva.INTENSIDAD);
      
      BD.getInstance().insert(stm_corr_tension);
      
      
      
      return curvaCorregida;
   }

   @Override
   public String toString() {
      return "IEC-60891 Método 1";
   }

  
   
}
