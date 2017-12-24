/**
 * @(#) CurvaCorregida.java
 */

package modelo;

public class CurvaCorregida extends CurvaIV
{
   private MetodoCorrecion metodo;
   
   private CurvaMedida curvas;

    public CurvaCorregida(int id, MetodoCorrecion m, CurvaMedida cm,boolean str) {
       CurvaIV.buscar(this, id);
    }
   
   
}
