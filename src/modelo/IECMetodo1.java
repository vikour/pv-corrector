
package modelo;

import java.util.List;

public class IECMetodo1 implements MetodoCorreccion {

   private static IECMetodo1 instance = null;
   
   public static IECMetodo1 getInstance() {
      
      if (instance == null)
         instance = new IECMetodo1();
      
      return instance;
   }
   
   @Override
   public CurvaCorregida corregir(List<ConfiguracionCorreccion> configuracion, CurvaMedida original) {
      CurvaCorregida correccion = null;
      
      // Obtención de medidas.
      Modulo modulo = original.getModulo();
      
      double alpha = modulo.getAlpha() * 10E3;
      double beta = modulo.getBeta() * 10E3;
      double rs = modulo.getRs();
      double kappa = modulo.getKappa() * 10E3;
      
      // Caso de error.
      if (alpha == 0 || beta == 0 || rs == 0 || kappa == 0)
         throw new RuntimeException();
      
      ConfiguracionCorreccion config = configuracion.get(0);
      double isc = original.getIsc().getValor();
      double g1 = config.getIrradiancia().getValor();
      double g2 = config.getValorI();
      double t1 = config.getTemperatura().getValor();
      double t2 = config.getValorT();
      
      correccion = new CurvaCorregida(NombreMetodoCorreccion.METODO_1_IEC, original, configuracion);
      List<MedidaCurva> i1 = original.getIntensidades();
      List<MedidaCurva> i2 = correccion.getIntensidades();
      List<MedidaCurva> v1 = original.getTensiones();
      List<MedidaCurva> v2 = correccion.getTensiones();
      
      
      // Cálculo de valores.
      double incremento = isc * (g2/g1 - 1) + alpha * (t2 - t1);
      
      for (int i = 0 ; i < i2.size() ; i++) {
         i2.get(i).setValor(i1.get(i).getValor() + incremento);
         v2.get(i).setValor( v1.get(i).getValor() - rs*(i2.get(i).getValor() - i1.get(i).getValor()) - 
                             kappa * i2.get(i).getValor() * (t2 - t1) + beta * (t2 - t1) );
      }
      
      return correccion;
   }

   
}
