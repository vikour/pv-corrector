/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

public interface MetodoCorreccion {
   
   public CurvaCorregida corregir(List<ConfiguracionCorreccion> configuracion, CurvaMedida original);
   
}
