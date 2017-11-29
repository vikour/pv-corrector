/**
 * @(#) MedidaCurva.java
 */

package modelo;

import java.util.List;

public abstract class MedidaCurva extends Medida implements Comparable {

    protected NombreValorCurva tipo;
    protected int orden;
    protected int idCurva;
    
    public MedidaCurva(CurvaIV curva, int orden, NombreValorCurva tipo) {
        super(0,"");
        String qq = "SELECT * FROM medidas_curvas WHERE " +
                    "curva_iv = " + curva.getId() + " AND " +
                    "orden = " + orden + " AND " +
                    "tipo = " + NombreValorCurva.BD(tipo);
        BD bd = BD.getInstance();
        List<String[]> result = bd.select(qq);
        String [] medida;
        
        if (result.isEmpty())
            throw new RuntimeException("No se ha encontrado la medida.");
        
        medida = result.get(0);
        valor = Double.valueOf(medida[1]);
        magnitud = medida[2];
        this.orden = Integer.valueOf(medida[3]);
        
        if (Integer.valueOf(medida[4]) == NombreValorCurva.BD(NombreValorCurva.INTENSIDAD))
            this.tipo = NombreValorCurva.INTENSIDAD;
        else 
            this.tipo = NombreValorCurva.TENSION;
        
        idCurva = curva.getId();
    }
    
    protected MedidaCurva(double valor, String magnitud, int orden, CurvaIV curva, NombreValorCurva tipo) {
        super(valor, magnitud);
        String stm = "INSERT INTO medidas_curvas VALUES (" + curva.getId() + ", " + valor + ", " + 
                     "'" + magnitud + "', " + orden + ", " + NombreValorCurva.BD(tipo) + ")";
        BD bd = BD.getInstance();
        
        try {
            bd.insert(stm);
            this.orden = orden;
            idCurva = curva.getId();
            tipo = tipo;
        }
        catch (Error err) {
            throw new RuntimeException("Medida duplicada");
        }
    }

    @Override
    public void setMagnitud(String magnitud) {
        String update = "UPDATE medidas_curvas SET magnitud = '" + magnitud + "' WHERE " +
                        "orden = " + orden + " AND curva_iv = " + idCurva + " AND " +
                        "tipo = " + NombreValorCurva.BD(tipo);
        
        if (!magnitud.equals(this.magnitud)) {
            BD.getInstance().update(update);
            super.setMagnitud(magnitud);
        }
    }

    @Override
    public void setValor(double valor) {
        String update = "UPDATE medidas_curvas SET valor = " + valor + " WHERE " +
                        "orden = " + orden + " AND curva_iv = " + idCurva + " AND " +
                        "tipo = " + NombreValorCurva.BD(tipo);
        
        if (valor != this.valor) {
            BD.getInstance().update(update);
            super.setValor(valor);
        }
    }
    
    public int getOrden() {
        return orden;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof MedidaCurva) &&
               (((MedidaCurva) obj).orden == orden) &&
               (((MedidaCurva) obj).idCurva == idCurva) &&
               (((MedidaCurva) obj).tipo.equals(tipo));
    }

    @Override
    public int compareTo(Object o) {
        int i = -1;
        
        if (o instanceof MedidaCurva)
            i = ((MedidaCurva) o).orden - orden;
        
        return i;
    }
    
}
