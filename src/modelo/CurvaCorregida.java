/**
 * @(#) CurvaCorregida.java
 */

package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurvaCorregida extends CurvaIV {
   private static SimpleDateFormat FF_FECHA = new SimpleDateFormat("yyyy-mm-dd");
   private static SimpleDateFormat FF_HORA = new SimpleDateFormat("hh:mm:ss");
   
   
   private MetodoCorrecion metodo;
   private CurvaMedida original;

   public CurvaCorregida(int id) {  // TOCAR!!!
      super(id);
      this.metodo = null;
      this.original = null;
   }

   public CurvaCorregida(MetodoCorrecion metodo, CurvaMedida curvas) {
      super(CurvaIV.CORREGIDA, FF_FECHA.format(new Date()), FF_HORA.format(new Date()),
            curvas.getIsc(), curvas.getVoc(), curvas.getPmax(), curvas.getImax(),
            curvas.getVmax(), curvas.getFf());
      this.metodo = metodo;
      this.original = curvas;
      
      String stm = "INSERT INTO curvas_corregidas VALUES (" + super.getId() + ", '" + metodo.toString() + "')";
      BD.getInstance().insert(stm);
      
      
   }
   
   
   
}
