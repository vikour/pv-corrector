/**
 * @(#) CurvaCorregida.java
 */

package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurvaCorregida extends CurvaIV
{
   private NombreMetodoCorreccion metodo;
   private CurvaMedida original;
   private List<ConfiguracionCorreccion> configuracionCorreccion;
   
   private static final SimpleDateFormat FMT_FECHA = new SimpleDateFormat("dd-mm-yyyy");
   private static final SimpleDateFormat FMT_HORA = new SimpleDateFormat("hh:mm:ss");
   

   protected static CurvaCorregida buscar(int id) {
      CurvaCorregida result = new CurvaCorregida();
      String qq = "SELECT * FROM curvas_corregidas WHERE id = " + id;
      
      CurvaIV.buscar(result, id);
      String [] tuplas = BD.getInstance().select(qq).get(0);
      
      result.metodo = NombreMetodoCorreccion.valueOf(tuplas[1]);
      result.original = AlmacenCurvasMedidas.getInstance().buscar(Integer.valueOf(tuplas[2]));

      return result;
   }

   protected static List<CurvaCorregida> buscar(CurvaMedida c) {
      List<CurvaCorregida> result = new ArrayList<>();
      String qq = "SELECT * FROM curvas_corregidas WHERE original = " + c.getId();
      
      for (String tupla [] : BD.getInstance().select(qq)) {
         CurvaCorregida cc = new CurvaCorregida();
         CurvaIV.buscar(cc, Integer.valueOf(tupla[0]));
         cc.metodo = NombreMetodoCorreccion.valueOf(tupla[1]);
         cc.original = AlmacenCurvasMedidas.getInstance().buscar(Integer.valueOf(tupla[2]));
         result.add(cc);
      }
      
      return result;
   }
      
   public CurvaCorregida(NombreMetodoCorreccion metodo, CurvaMedida original, List<ConfiguracionCorreccion> configuracionCorreccion) {
      super(CurvaIV.CORREGIDA, FMT_FECHA.format(new Date()), FMT_HORA.format(new Date()), original.getIsc(),
            original.getVoc(), original.getPmax(), original.getImax(), original.getVmax(), original.getFF());
      
      this.metodo = metodo;
      this.original = original;
      this.configuracionCorreccion = configuracionCorreccion;
      setTensiones(new ArrayList<>());
      setIntensidades(new ArrayList<>());
      
      // hacer los fors para introducirlos en la base de datos.
      for (MedidaCurva mt : original.getTensiones())
         new MedidaTension(mt.getValor(), mt.getMagnitud(), mt.getOrden(), this);
      
      for (MedidaCurva mi : original.getIntensidades())
         new MedidaIntensidad(mi.getValor(), mi.getMagnitud(), mi.getOrden(), this);
      
      BD.getInstance().insert("INSERT INTO curvas_corregidas VALUES (" + getId() + ", '" + metodo.toString() + "', " + original.getId() + ")");
      
   }
   
   private CurvaCorregida() {}

   public CurvaMedida getOriginal() {
      return original;
   }

   public NombreMetodoCorreccion getMetodo() {
      return metodo;
   }
   
}
