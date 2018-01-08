/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class MedidaOrdenada extends Medida implements Comparable<MedidaOrdenada> {
   
   private int orden;

   public MedidaOrdenada(int orden, double valor, String magnitud) {
      super(valor, magnitud);
      this.orden = orden;
   }

   public int getOrden() {
      return orden;
   }

   public void setOrden(int orden) {
      this.orden = orden;
   }

   @Override
   public int compareTo(MedidaOrdenada o) {
        int i = -1;
        
        if (o instanceof MedidaCurva)
            i = ((MedidaCurva) o).orden - orden;
        
        return i;
   }
   
}
