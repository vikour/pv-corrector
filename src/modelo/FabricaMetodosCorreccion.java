
package modelo;

public class FabricaMetodosCorreccion {
   
   private static FabricaMetodosCorreccion instance = null;
   
   public static FabricaMetodosCorreccion getInstance() {
      
      if (instance == null)
         return new FabricaMetodosCorreccion();
      
      return instance;
   }

   private FabricaMetodosCorreccion() {
   }
   
   
   public MetodoCorreccion create(NombreMetodoCorreccion metodo) {
      MetodoCorreccion result = null;
      
      switch (metodo) {
         
         case METODO_1_IEC:
            result = IECMetodo1.getInstance();
         
      }
      
      return result;
   }
   
   
}
