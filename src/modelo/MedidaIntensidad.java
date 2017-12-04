/**
 * @(#) MedidaIntensidad.java
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

public class MedidaIntensidad extends MedidaCurva
{
    
    public static List<MedidaIntensidad> listar(CurvaIV curva) {
        List<MedidaIntensidad> result = new ArrayList<>();
        String qq = "SELECT orden FROM medidas_curvas " +
                    "WHERE curva_iv = " + curva.getId() + " AND tipo = " +
                    NombreValorCurva.BD(NombreValorCurva.INTENSIDAD);
        
        for (String [] tupla : BD.getInstance().select(qq))
            result.add(new MedidaIntensidad(curva, Integer.valueOf(tupla[0])));
        
        return result;
    }

    public MedidaIntensidad(double valor, String magnitud, int orden, CurvaIV curva) {
        super(valor, magnitud, orden, curva, NombreValorCurva.INTENSIDAD);
    }
   
    public MedidaIntensidad(CurvaIV curva, int orden) {
        super(curva, orden, NombreValorCurva.INTENSIDAD);
    }
    
}
