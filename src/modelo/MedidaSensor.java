/**
 * @(#) MedidaSensor.java
 */

package modelo;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class MedidaSensor extends Medida
{
   private Canal canal;
   private int idc;
   
    public static List<MedidaSensor> listar(CurvaIV c){
        List<MedidaSensor>l=new ArrayList<>();
        String sel="SELECT canal FROM medidas_canal WHERE curva_iv="+c.getId()+" ";
        BD bd=BD.getInstance();
        List<String[]>aux=bd.select(sel);
        for(String[] s:aux){
            l.add(new MedidaSensor(c,new Canal(s[0],false)));
        }
        return l;
    }
   
    public MedidaSensor(double valor, String magnitud, Canal c, CurvaIV cur) {
        super(valor, magnitud);
        
        String ins="INSERT INTO medidas_canal VALUES ("+cur.getId()+","+valor+",'"+magnitud+"','"+c.getNombre()+"')";
        
        BD bd=BD.getInstance();
        try{
            bd.insert(ins);
            canal=c;
            idc=cur.getId();
        }catch(Error err){
            throw new Error("Medida duplicada");
        }
        
    }
    
    public MedidaSensor(CurvaIV c, Canal can){
        super(0,"");
        String sel="SELECT valor, magnitud FROM medidas_canal WHERE curva_iv="+c.getId()+" and canal='"+can.getNombre()+"';";
        BD bd= BD.getInstance();
        List<String[]> list=bd.select(sel);
        
        if(list.isEmpty()){
            throw new RuntimeException("La medida no existe");
        }
        
        String[] medida=list.get(0);
        this.setValor(Double.parseDouble(medida[0]));
        this.setMagnitud(medida[1]);
        this.canal=can;
        this.idc=c.getId();
    }

    public Canal getCanal() {
        return canal;
    }

    public int getIdc() {
        return idc;
    }

    public void setCanal(Canal canal) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE medidas_canal SET canal='"+canal+"' WHERE canal='"+this.canal+"' and curva_iv="+idc+" ");
        this.canal = canal;
    }

    public void setIdc(int idc) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE medidas_canal SET idc="+idc+" WHERE canal='"+this.canal+"' and curva_iv="+this.idc+" ");
        this.idc = idc;
    }

    public void setValor(double valor) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE medidas_canal SET valor="+valor+" WHERE canal='"+this.canal+"' and curva_iv="+idc+" ");
        this.valor = valor;
    }

    public void setMagnitud(String magnitud) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE medidas_canal SET canal='"+magnitud+"' WHERE canal='"+this.canal+"' and curva_iv="+idc+" ");
        this.magnitud = magnitud;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof MedidaSensor){
            MedidaSensor aux=(MedidaSensor)o;
            if(aux.getIdc()==idc){
                return true;
            }
        }
        
        
        return false;
    }
   
   
}
