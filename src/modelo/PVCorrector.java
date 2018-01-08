
package modelo;

public class PVCorrector {

   private static PVCorrector instance;
   
   public AlmacenCampañas campañas;
   public AlmacenModulos modulos;
   public AlmacenCurvasMedidas curvas;
   
   public static PVCorrector getInstance() {
      
      if (instance == null)
         instance = new PVCorrector();
      
      return instance;
   }

   private PVCorrector() {
      campañas = AlmacenCampañas.getInstance();
      modulos = AlmacenModulos.getInstance();
      curvas = AlmacenCurvasMedidas.getInstance();
   }

}
