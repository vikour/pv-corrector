/**
 * @(#) NombreValorCurva.java
 */

package modelo;

public enum NombreValorCurva
{
   INTENSIDAD,TENSION;
   
   public static int BD(NombreValorCurva tipo) {
       int value = 0;
       
       if (tipo == TENSION)
           value = 1;

       return value;
   }
   
}
