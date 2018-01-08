/**
 * @(#) MedidaCanal.java
 */

package modelo;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import modelo.BD;
import modelo.CurvaIV;
import modelo.Medida;

public class MedidaCanal extends Medida
{
   private Canal canal;
   private int idc;
   /**
    * @deprecated Usar el metodo {AlmacenMedidasCanal##buscarTodas()}
    * @param c
    * @return 
    */
    public static List<MedidaCanal> listar(CurvaIV c){
        return buscar(c.getId());
    }
    /**
     * Construye una medida y la inserta en la base de datos
     * @param valor El valor de la medida
     * @param magnitud La magnitud de la medida
     * @param canal El canal al que pertenece (Este debe existir en la bd antes de realizar la insercion)
     * @param idc El id de la curva iv a la que se asocia esta medida
     * @throws Error  En caso de que la medida se encuentre en la base de datos
     */
    MedidaCanal(double valor, String magnitud,Canal canal, int idc) throws Error{
        super(valor, magnitud);
        BD bd=BD.getInstance();
        
        bd.insert("INSERT INTO medidas_canal VALUES ("+idc+","+valor+",'"+magnitud+"','"+canal.getNombre()+"');");
        this.idc=idc;
        this.canal=canal;
    }
    /**
     * Constructor para usar las funciones de canal tales como buscar de uso unico en AlmadenMedidasCanal
     */
    MedidaCanal() {
        super(0, null);
    }
   
    
    /**
     * Busca una medida en la base de datos y la devuelve
     * @param c El canal que se quiere buscar 
     * @param id El id de la curva de la que se quiere obtener la medida
     * @return La medida que ha encontrado en la base de datos con los paremtros dados
     */
    
    MedidaCanal buscar(Canal c, int id){
        BD bd=BD.getInstance();
        MedidaCanal res=null;
        List<String[]> l=bd.select("SELECT * FROM medidas_canal WHERE canal='"+c.getNombre()+"' and curva_iv= "+id+" ;");
        if(!l.isEmpty()){
            res=new MedidaCanal();
            String[] aux=l.get(0);
            idc=Integer.parseInt(aux[0]);
            valor=Double.parseDouble(aux[1]);
            magnitud=aux[2];
            canal=AlmacenCanales.getInstance().buscar(aux[3]);
        }
        
        return res;
    }
    
    /**
     * Devuelve una lista de todos los canales asociados a una curva iv.
     * @param idc Id de la curva de la que se quieren obtener los distintos canales.
     * @return Una lista con todas las medidas que tiene la curva identificada con idc
     */
    
    static List<MedidaCanal> buscar(int idc){
        BD bd=BD.getInstance();
        
        List<String[]> l=bd.select("SELECT * FROM medidas_canal WHERE curva_iv= "+idc+" ;");
        List<MedidaCanal>res=new ArrayList<>();
        int i=0;
        while(!l.isEmpty()){
            
            MedidaCanal m=new MedidaCanal();
            String[] aux=l.get(0);
            m.idc=Integer.parseInt(aux[0]);
            m.valor=Double.parseDouble(aux[1]);
            m.magnitud=aux[2];
            m.canal=AlmacenCanales.getInstance().buscar(aux[3]);
            l.remove(0);
            i++;
            
            res.add(m);
        }
        
        return res;
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

   @Override
    public void setValor(double valor) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE medidas_canal SET valor="+valor+" WHERE canal='"+this.canal+"' and curva_iv="+idc+" ");
        this.valor = valor;
    }

   @Override
    public void setMagnitud(String magnitud) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE medidas_canal SET canal='"+magnitud+"' WHERE canal='"+this.canal+"' and curva_iv="+idc+" ");
        this.magnitud = magnitud;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof MedidaCanal){
            MedidaCanal aux=(MedidaCanal)o;
            if(aux.getIdc()==idc){
                return true;
            }
        }
        
        
        return false;
    }
   
   
}
