
package modelo;

public enum NombreMetodoCorreccion {
   METODO_1_IEC, METODO_2_IEC;
   
   @Override   
   public String toString() {
      String result = "";
      
      switch (this) {
         
         case METODO_1_IEC:
            result = "IEC Método 1";
            break;
            
         case METODO_2_IEC:
            result = "IEC Método 2";
            break;
            
      }
      
      return result;
   }
}
