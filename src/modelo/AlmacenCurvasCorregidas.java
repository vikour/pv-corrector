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


public class AlmacenCurvasCorregidas {
   
   private static AlmacenCurvasCorregidas instance = null;
   private Map<CurvaMedida,List<CurvaCorregida>> medidas;
   private Map<CurvaMedida,Boolean> registroConsultaEntera;
   
   public static AlmacenCurvasCorregidas getInstance() {
      
      if (instance == null)
         instance = new AlmacenCurvasCorregidas();
      
      return instance;
   }

   private AlmacenCurvasCorregidas() {
      medidas = new HashMap<>();
      registroConsultaEntera = new HashMap<>();
   }
   
   public CurvaCorregida nueva(MetodoCorreccion metodo, List<ConfiguracionCorreccion> config, CurvaMedida original) throws Error{
      CurvaCorregida result;
      List<CurvaCorregida> list;

      result = metodo.corregir(config, original);
      list = medidas.get(original);
      
      if (list == null) {
         list = new ArrayList<>();
         list.add(result);
         medidas.put(original, list);
         registroConsultaEntera.put(original, Boolean.FALSE);
      }
      else
         list.add(result);
      
      return result;
   }
   
   public CurvaCorregida buscar(int id) {
      Iterator<List<CurvaCorregida>> it = medidas.values().iterator();
      CurvaCorregida result = buscarMedidaCache(it, id);
      List<CurvaCorregida> list;
      
      
      if (result == null) {
         result = CurvaCorregida.buscar(id);
         
         list = medidas.get(result.getOriginal());
         
         if (list == null) {
            list = new ArrayList<>();
            list.add(result);
            medidas.put(result.getOriginal(), list);
            registroConsultaEntera.put(result.getOriginal(), Boolean.FALSE);
         }
         else
            list.add(result);
         
      }
      
      return result;
   }
   
   public void borrar(CurvaMedida curva) {
      List<CurvaCorregida> list = medidas.get(curva.getCampaña());
      
      CurvaCorregida.borrar(curva.getFecha(), curva.getHora());
      medidas.remove(curva);
   }
   
   public void borrar(String fecha, String hora) {
      CurvaCorregida curva = buscarMedidaCacheClaveCandidata(medidas.values().iterator(), fecha, hora);
      List<CurvaCorregida> list;
      
      if (curva == null)
         CurvaMedida.borrar(fecha, hora);
      else {
         list = medidas.get(curva.getOriginal());
         list.remove(curva);
      }
   }

   private CurvaCorregida buscarMedidaCache(Iterator<List<CurvaCorregida>> it, int id) {
      List<CurvaCorregida> listAux;
      CurvaCorregida result = null;
      
      while (result == null && it.hasNext()) {
         listAux = it.next();
         Iterator<CurvaCorregida> itMedidas = listAux.iterator();
         CurvaCorregida curvaAux;
         
         while (result == null && it.hasNext()) {
            curvaAux = itMedidas.next();
            
            if (curvaAux.getId() == id)
               result = curvaAux;
            
         }
         
      }
      return result;
   }

   private CurvaCorregida buscarMedidaCacheClaveCandidata(Iterator<List<CurvaCorregida>> it, String fecha, String hora) {
      List<CurvaCorregida> listAux;
      CurvaCorregida result = null;
      
      while (result == null && it.hasNext()) {
         listAux = it.next();
         Iterator<CurvaCorregida> itMedidas = listAux.iterator();
         CurvaCorregida curvaAux;
         
         while (result == null && it.hasNext()) {
            curvaAux = itMedidas.next();
            
            if (curvaAux.getFecha().equals(fecha) && curvaAux.getHora().equals(hora))
               result = curvaAux;
            
         }
         
      }
      return result;
   }
   
   public CurvaCorregida [] buscar(CurvaMedida original) {
      List<CurvaCorregida> list;
      Boolean consultaAnterior = registroConsultaEntera.get(original);
      
      if (consultaAnterior == null)
         list = cargarTodasMedidas(original);
      else if (consultaAnterior.equals(Boolean.FALSE))
         list = mezclarMedidasBDCache(original);
      else
         list = medidas.get(original);
      
      return list.toArray(new CurvaCorregida[list.size()]);
   }

   private List<CurvaCorregida> cargarTodasMedidas(CurvaMedida c) {
      List list = CurvaCorregida.buscar(c);
      
      medidas.put(c, list);
      registroConsultaEntera.put(c, Boolean.TRUE);
      
      return list;
   }

   private List<CurvaCorregida> mezclarMedidasBDCache(CurvaMedida c) {
        List list = medidas.get(c);
        
        for (CurvaCorregida medida : CurvaCorregida.buscar(c))
            
            if (!list.contains(medida))
                list.add(medida);
        
        registroConsultaEntera.put(c, Boolean.TRUE);
        
        return list;
   }
   
}
