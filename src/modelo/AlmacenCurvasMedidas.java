/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AlmacenCurvasMedidas {
   
   private static AlmacenCurvasMedidas instance = null;
   private Map<Campaña,List<CurvaMedida>> medidas;
   private Map<Campaña,Boolean> registroConsultaEntera;
   
   public static AlmacenCurvasMedidas getInstance() {
      
      if (instance == null)
         instance = new AlmacenCurvasMedidas();
      
      return instance;
   }

   private AlmacenCurvasMedidas() {
      medidas = new HashMap<>();
      registroConsultaEntera = new HashMap<>();
   }
   
   public CurvaMedida nueva(Campaña c,String fecha, String hora, Medida isc, Medida voc, Medida pmax, Medida imax, Medida vmax, double ff) throws Error{
      CurvaMedida result;
      List<CurvaMedida> list;

      result = new CurvaMedida(c, fecha, hora, isc, voc, pmax, imax, vmax, ff);
      list = medidas.get(c);
      
      if (list == null) {
         list = new ArrayList<>();
         list.add(result);
         medidas.put(c, list);
         registroConsultaEntera.put(c, Boolean.FALSE);
      }
      else
         list.add(result);
      
      return result;
   }
   
   public CurvaMedida buscar(int id) {
      Iterator<List<CurvaMedida>> it = medidas.values().iterator();
      CurvaMedida result = buscarMedidaCache(it, id);
      List<CurvaMedida> list;
      
      
      if (result == null) {
         result = CurvaMedida.buscar(id);
         
         list = medidas.get(result.getCampaña());
         
         if (list == null) {
            medidas.put(result.getCampaña(), list);
            registroConsultaEntera.put(result.getCampaña(), Boolean.FALSE);
         }
         else
            list.add(result);
         
      }
      
      return result;
   }
   
   public void borrar(CurvaMedida curva) {
      List<CurvaMedida> list = medidas.get(curva.getCampaña());
      
      CurvaMedida.borrar(curva.getFecha(), curva.getHora());
      medidas.remove(curva);
   }
   
   public void borrar(String fecha, String hora) {
      CurvaMedida curva = buscarMedidaCacheClaveCandidata(medidas.values().iterator(), fecha, hora);
      List<CurvaMedida> list;
      
      if (curva == null)
         CurvaMedida.borrar(fecha, hora);
      else {
         list = medidas.get(curva.getCampaña());
         list.remove(curva);
      }
   }

   private CurvaMedida buscarMedidaCache(Iterator<List<CurvaMedida>> it, int id) {
      List<CurvaMedida> listAux;
      CurvaMedida result = null;
      
      while (result == null && it.hasNext()) {
         listAux = it.next();
         Iterator<CurvaMedida> itMedidas = listAux.iterator();
         CurvaMedida curvaAux;
         
         while (result == null && it.hasNext()) {
            curvaAux = itMedidas.next();
            
            if (curvaAux.getId() == id)
               result = curvaAux;
            
         }
         
      }
      return result;
   }

   private CurvaMedida buscarMedidaCacheClaveCandidata(Iterator<List<CurvaMedida>> it, String fecha, String hora) {
      List<CurvaMedida> listAux;
      CurvaMedida result = null;
      
      while (result == null && it.hasNext()) {
         listAux = it.next();
         Iterator<CurvaMedida> itMedidas = listAux.iterator();
         CurvaMedida curvaAux;
         
         while (result == null && it.hasNext()) {
            curvaAux = itMedidas.next();
            
            if (curvaAux.getFecha().equals(fecha) && curvaAux.getHora().equals(hora))
               result = curvaAux;
            
         }
         
      }
      return result;
   }
   
   public CurvaMedida [] buscar(Campaña c) {
      List<CurvaMedida> list;
      Boolean consultaAnterior = registroConsultaEntera.get(c);
      
      if (consultaAnterior == null)
         list = cargarTodasMedidas(c);
      else if (consultaAnterior.equals(Boolean.FALSE))
         list = mezclarMedidasBDCache(c);
      else
         list = medidas.get(c);
      
      return list.toArray(new CurvaMedida[list.size()]);
   }

   private List<CurvaMedida> cargarTodasMedidas(Campaña c) {
      List list = CurvaMedida.buscar(c);
      
      medidas.put(c, list);
      registroConsultaEntera.put(c, Boolean.TRUE);
      
      return list;
   }

   private List<CurvaMedida> mezclarMedidasBDCache(Campaña c) {
        List list = medidas.get(c);
        
        for (CurvaMedida medida : CurvaMedida.buscar(c))
            
            if (!list.contains(medida))
                list.add(medida);
        
        registroConsultaEntera.put(c, Boolean.TRUE);
        
        return list;
   }
   
}
