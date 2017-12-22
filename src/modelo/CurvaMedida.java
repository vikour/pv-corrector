/**
 * @(#) CurvaMedida.java
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

public class CurvaMedida extends CurvaIV {

    static List<CurvaMedida> listar(Campaña campaña) {
        List<CurvaMedida> result = new ArrayList<>();
        String qq = "SELECT id FROM curvas_medidas WHERE campanya = '" + campaña.getNombre() + "' " +
                    "AND modulo = '" + campaña.getModulo().getNombre() + "'";
        
        for (String [] tupla : BD.getInstance().select(qq))
            result.add(new CurvaMedida(Integer.parseInt(tupla[0])));
        
        return result;
    }

    private Campaña campaña;
    private List<MedidaCanal> medidasCanal;
    private CurvaCorregida correcciones;

    public CurvaMedida(int id) {
        super(id);
        String qq = "SELECT modulo,campanya FROM curvas_medidas WHERE id = " + id;
        String tupla [] = BD.getInstance().select(qq).get(0);
        

        //campaña = new Campaña(new Modulo(tupla[0]), tupla[1], false);
        campaña = AlmacenCampañas.getInstance().nueva(tupla[0], tupla[1]);
        medidasCanal = null;
        correcciones = null;
    }

    public CurvaMedida(Campaña c, String fecha, String hora, Medida isc, Medida voc, Medida pmax, Medida imax, Medida vmax, double ff ) {
        super(CurvaIV.MEDIDA, fecha, hora, isc, voc, pmax, imax, vmax, ff);
        String stm = "INSERT INTO curvas_medidas VALUES ("+getId()+", '" + c.getModulo().getNombre() + "', '" + c.getNombre() + "')";
        
        this.campaña = c;
        this.medidasCanal = null;
        this.correcciones = null;
        
        BD.getInstance().insert(stm);
    }
    
    public Campaña getCampaña() {
        return campaña;
    }
    
    public MedidaCanal getMedidaCanal(Canal canal) {
        boolean founded = false;
        int i = 0;
        MedidaCanal result = null;
        
        if (medidasCanal == null)
            getMedidasCanal();
        
        while (!founded && i < medidasCanal.size()) {
            
            if (canal.equals(medidasCanal.get(i).getCanal()))
                result = medidasCanal.get(i);
            
            i++;
        }
        
        return result;
    }

    public List<MedidaCanal> getMedidasCanal() {
        String sel="SELECT canal, valor, magnitud FROM medidas_canal WHERE curva_iv="+this.getId()+" ;";
        BD bd=BD.getInstance();
        List<String[]> l=bd.select(sel);
        
        List<MedidaCanal> ms=new ArrayList();
        AlmacenMedidasCanal amc=new AlmacenMedidasCanal(this);
        AlmacenCanales ac=AlmacenCanales.getInstance();
        for(String [] e  : l  ){
            //ms.add(new MedidaCanal(this,new Canal(e[0])));
            ms.add(amc.nuevo(ac.buscar(e[0]),Double.parseDouble(e[1]), e[2]));
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

    public void setMedidasCanal(List<MedidaCanal> medidasCanal) {
        BD bd=BD.getInstance();
        for(MedidaCanal ms:medidasCanal){
            try{
                bd.insert("INSERT INTO medidas_canal VALUES ("+ms.getIdc()+", "+ms.getValor()+", "+ms.getMagnitud()+", "+ms.getCanal().getNombre()+") ;");
        
            }catch(Error e){
                bd.update("UPDATE medidas_canal SET curva_iv="+ms.getIdc()+", valor="+ms.getValor()+", magnitud='"+ms.getMagnitud()+"', canal='"+ms.getCanal().getNombre()+"' ");
            }
        }
        this.medidasCanal = medidasCanal;
    }
    
    public Canal[] getCanales() {
        Canal[] result;
        BD bd = BD.getInstance();
        String qq = "SELECT canal FROM medidas_canal WHERE curva_iv = " + getId();
        AlmacenMedidasCanal amc=new AlmacenMedidasCanal(this);
        result=amc.buscarCanales();
//        for (String [] tupla : bd.select(qq))
//            result.add(new Canal(tupla[0], false));
        
        return result;
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
