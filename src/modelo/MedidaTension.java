/**
 * @(#) MedidaTension.java
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

public class MedidaTension extends MedidaCurva
{
    
    public static List<MedidaIntensidad> listar(CurvaIV curva) {
        List<MedidaIntensidad> result = new ArrayList<>();
        String qq = "SELECT orden FROM medidas_curvas " +
                    "WHERE curva_iv = " + curva.getId() + " AND tipo = " +
                    NombreValorCurva.BD(NombreValorCurva.TENSION);
        
        for (String [] tupla : BD.getInstance().select(qq))
            result.add(new MedidaIntensidad(curva, Integer.valueOf(tupla[0])));
        
        return result;
    }

    public MedidaTension(double valor, String magnitud, int orden, CurvaIV curva) {
        super(valor, magnitud, orden, curva, NombreValorCurva.TENSION);
    }
   
    public MedidaTension(CurvaIV curva, int orden) {
        super(curva, orden, NombreValorCurva.TENSION);
    }
}
