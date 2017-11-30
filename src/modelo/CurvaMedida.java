/**
 * @(#) CurvaMedida.java
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

public class CurvaMedida extends CurvaIV {

    static List<CurvaMedida> listar(Campaña aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Campaña campaña;

    private List<MedidaSensor> medidasCanal;

    private CurvaCorregida correcciones;

    public CurvaMedida(int id, Campaña c) {
        super(id);
        String select= "SELECT campanya FROM curvas_medidas WHERE id="+id+" ;";
        BD bd= BD.getInstance();
        List<String[]> l=bd.select(select);
        
        if(l.isEmpty()){
           throw new RuntimeException("No existe la campaña"); 
        }
        campaña=c;
    }

    public CurvaMedida(int id, Campaña c, String fecha, String hora, Medida isc, Medida voc, Medida pmax, Medida vmax, double ff ) {
        super(fecha, hora, isc, voc, pmax, vmax, ff);
        this.campaña = null;
        this.medidasCanal = null;
        this.correcciones = null;
        String select= "SELECT campanya FROM curvas_medidas WHERE id="+id+" ;";
        BD bd= BD.getInstance();
        List<String[]> l=bd.select(select);
        
        if(l.isEmpty()){
           throw new RuntimeException("No existe la campaña"); 
        }
        campaña=c;
    }
    
    
    
    
    public Campaña getCampaña() {
        return campaña;
    }

    public List<MedidaSensor> getMedidasCanal() {
        
        String sel="SELECT canal FROM medidas_canal WHERE curva_iv="+this.getId()+" ;";
        BD bd=BD.getInstance();
        List<String[]> l=bd.select(sel);
        List<MedidaSensor> ms=new ArrayList();
        for(String [] e  : l  ){
            ms.add(new MedidaSensor(this,new Canal(e[0],false)));
        }
        
        medidasCanal=ms;
        return medidasCanal;
    }

    public CurvaCorregida getCorrecciones() {
        return correcciones;
    }

    public void setCampaña(Campaña campaña) {
        String update= "UPDATE curvas_medidas SET campanya='"+campaña+"' WHERE id="+this.getId()+" ;";
        BD bd= BD.getInstance();
        bd.update(update);
        this.campaña = campaña;
    }

    public void setMedidasCanal(List<MedidaSensor> medidasCanal) {
        BD bd=BD.getInstance();
        for(MedidaSensor ms:medidasCanal){
            try{
                bd.insert("INSERT INTO medidas_canal VALUES ("+ms.getIdc()+", "+ms.getValor()+", "+ms.getMagnitud()+", "+ms.getCanal().getNombre()+") ;");
        
            }catch(Error e){
                bd.update("UPDATE medidas_canal SET curva_iv="+ms.getIdc()+", valor="+ms.getValor()+", magnitud='"+ms.getMagnitud()+"', canal='"+ms.getCanal().getNombre()+"' ");
            }
        }
        this.medidasCanal = medidasCanal;
    }

    
    @Override
    public boolean equals(Object o){
        if(o instanceof CurvaMedida){
            CurvaMedida aux=(CurvaMedida)o;
            if(aux.getId()==this.getId()){
                return true;
            }
        }
        
        
        return false;
    }
    
    @Override
    public String toString(){
        return "ID: "+this.getId()+"\nCampaña: "+campaña.getNombre();
    }
    
    
    

}
