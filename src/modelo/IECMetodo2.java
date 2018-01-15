
package modelo;

import java.util.List;


public class IECMetodo2 implements MetodoCorreccion {

    private static IECMetodo2 instance = null;
    
    static MetodoCorreccion getInstance() {
        
        if (instance == null)
            instance = new IECMetodo2();
        
        return instance;
    }
    
    private IECMetodo2() {}

    @Override
    public CurvaCorregida corregir(List<ConfiguracionCorreccion> configuracion, CurvaMedida original) {
      CurvaCorregida correccion = null;
      
      // Obtención de medidas.
      ConfiguracionCorreccion config = configuracion.get(0);
      
      double alpha = config.getAlpha() / 10E3;
      double beta = config.getBeta() / 10E3;
      double rs = config.getRs() / 10E3;
      double kappa = config.getKappa() / 10E3;
      double a = config.getA();
      
      // Caso de error.
      if (alpha == 0 || beta == 0 || rs == 0 || kappa == 0 || a == 0)
         throw new RuntimeException("El modulo de la curva no tiene los parámetros necesarios para la corrección");
      
      double isc = original.getIsc().getValor();
      double voc = original.getVoc().getValor();
      double g1 = config.getIrradiancia().getValor();
      double g2 = config.getValorI();
      double t1 = config.getTemperatura().getValor();
      double t2 = config.getValorT();
      
      if (isc == 0 || voc == 0)
          throw new RuntimeException("Se esperaba ISC y VOC distintas de cero.");
      
      double alphaRel = alpha / isc;
      double betaRel = beta / voc;
      
      correccion = new CurvaCorregida(NombreMetodoCorreccion.METODO_2_IEC, original, configuracion);
      List<MedidaCurva> i1 = original.getIntensidades();
      List<MedidaCurva> i2 = correccion.getIntensidades();
      List<MedidaCurva> v1 = original.getTensiones();
      List<MedidaCurva> v2 = correccion.getTensiones();
      
      // Cálculo de valores.
      
      
      for (int i = 0 ; i < i2.size() ; i++) {
         i2.get(i).setValor( i1.get(i).getValor() * (g2/g1) * (1 + alphaRel * (t2 - t1)) );
         v2.get(i).setValor( v1.get(i).getValor() + voc * (betaRel * (t2 - t1) + a * Math.log(g2/g1)) - rs * (i2.get(i).getValor() - i1.get(i).getValor()) - 
                             kappa * i2.get(i).getValor() * (t2 - t1) ) ;
      }
      
      return correccion;
    }
    
}
