/**
 * @(#) NombreValorCurva.java
 */

package modelo;

public enum TipoValorCurva
{
   INTENSIDAD,TENSION;
   
   public static int BD(TipoValorCurva tipo) {
       int value = 0;
       
       if (tipo == TENSION)
           value = 1;

       return value;
   }
   
}
