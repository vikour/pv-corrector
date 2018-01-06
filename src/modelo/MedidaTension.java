/**
 * @(#) MedidaTension.java
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

public class MedidaTension extends MedidaCurva
{
    
    public static List<MedidaCurva> listar(CurvaIV curva) {
        List<MedidaCurva> result = new ArrayList<>();
        String qq = "SELECT orden FROM medidas_curvas " +
                    "WHERE curva_iv = " + curva.getId() + " AND tipo = " +
                    TipoValorCurva.BD(TipoValorCurva.TENSION);
        
        for (String [] tupla : BD.getInstance().select(qq))
            result.add(new MedidaTension(curva, Integer.valueOf(tupla[0])));
        
        return result;
    }

    public MedidaTension(double valor, String magnitud, int orden, CurvaIV curva) {
        super(valor, magnitud, orden, curva, TipoValorCurva.TENSION);
        curva.addPuntoTension(this);
    }
   
    public MedidaTension(CurvaIV curva, int orden) {
        super(curva, orden, TipoValorCurva.TENSION);
    }
}
