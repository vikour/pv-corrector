/**
 * @(#) MedidaCurva.java
 */

package modelo;

import java.util.List;

public abstract class MedidaCurva extends MedidaOrdenada {

    protected TipoValorCurva tipo;
    protected int orden;
    protected int idCurva;
    
    public MedidaCurva(CurvaIV curva, int orden, TipoValorCurva tipo) {
       super(orden, 0, "");
        String qq = "SELECT * FROM medidas_curvas WHERE " +
                    "curva_iv = " + curva.getId() + " AND " +
                    "orden = " + orden + " AND " +
                    "tipo = " + TipoValorCurva.BD(tipo);
        BD bd = BD.getInstance();
        List<String[]> result = bd.select(qq);
        String [] medida;
        
        if (result.isEmpty())
            throw new RuntimeException("No se ha encontrado la medida.");
        
        medida = result.get(0);
        valor = Double.valueOf(medida[1]);
        magnitud = medida[2];
        this.orden = Integer.valueOf(medida[3]);
        
        if (Integer.valueOf(medida[4]) == TipoValorCurva.BD(TipoValorCurva.INTENSIDAD))
            this.tipo = TipoValorCurva.INTENSIDAD;
        else 
            this.tipo = TipoValorCurva.TENSION;
        
        idCurva = curva.getId();
    }
    
    protected MedidaCurva(double valor, String magnitud, int orden, int curva, TipoValorCurva tipo) {
        super(orden, valor, magnitud);
        String stm = "INSERT INTO medidas_curvas VALUES (" + curva + ", " + valor + ", " + 
                     "'" + magnitud + "', " + orden + ", " + TipoValorCurva.BD(tipo) + ")";
        BD bd = BD.getInstance();
        
        try {
            bd.insert(stm);
            this.orden = orden;
            idCurva = curva;
            this.tipo = tipo;
        }
        catch (Error err) {
            throw new Error("Medida duplicada");
        }
    }
    
    protected MedidaCurva(double valor, String magnitud, int orden, CurvaIV curva, TipoValorCurva tipo) {
       this(valor,magnitud,orden,curva.getId(),tipo);
    }

    @Override
    public void setMagnitud(String magnitud) {
        String update = "UPDATE medidas_curvas SET magnitud = '" + magnitud + "' WHERE " +
                        "orden = " + orden + " AND curva_iv = " + idCurva + " AND " +
                        "tipo = " + TipoValorCurva.BD(tipo);
        
        if (!magnitud.equals(this.magnitud)) {
            BD.getInstance().update(update);
            super.setMagnitud(magnitud);
        }
    }

    @Override
    public void setValor(double valor) {
        String update = "UPDATE medidas_curvas SET valor = " + valor + " WHERE " +
                        "orden = " + orden + " AND curva_iv = " + idCurva + " AND " +
                        "tipo = " + TipoValorCurva.BD(tipo);
        
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
    
}
