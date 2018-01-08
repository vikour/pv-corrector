/**
 * @(#) CurvaMedida.java
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

public class CurvaMedida extends CurvaIV {
    
    protected static List<CurvaMedida> buscar(Campaña campaña) {
        List<CurvaMedida> result = new ArrayList<>();
        String qq = "SELECT id FROM curvas_medidas WHERE campanya = '" + campaña.getNombre() + "' " +
                    "AND modulo = '" + campaña.getModulo().getNombre() + "'";
        
        for (String [] tupla : BD.getInstance().select(qq))
            result.add(buscar(Integer.parseInt(tupla[0])));
        
        return result;
    }
    
    protected static CurvaMedida buscar(int id) {
       CurvaMedida curva = new CurvaMedida();
       
       CurvaIV.buscar(curva, id);
        String qq = "SELECT modulo,campanya FROM curvas_medidas WHERE id = " + id;
        String tupla [] = BD.getInstance().select(qq).get(0);
        

        //campaña = new Campaña(new Modulo(tupla[0]), tupla[1], false);
        curva.campaña = AlmacenCampañas.getInstance().buscar(tupla[0], tupla[1]);
        curva.medidasCanal = new AlmacenMedidasCanal(curva);
       
       return curva;
    }

    private Campaña campaña;
    private AlmacenMedidasCanal medidasCanal;

    private CurvaMedida() {
       super();
    }

    protected CurvaMedida(Campaña c, String fecha, String hora, Medida isc, Medida voc, Medida pmax, Medida imax, Medida vmax, double ff ) {
        super(CurvaIV.MEDIDA, fecha, hora, isc, voc, pmax, imax, vmax, ff);
        String stm = "INSERT INTO curvas_medidas VALUES ("+getId()+", '" + c.getModulo().getNombre() + "', '" + c.getNombre() + "')";
        
        this.campaña = c;
        this.medidasCanal = new AlmacenMedidasCanal(this);
        
        BD.getInstance().insert(stm);
    }
    
    public Campaña getCampaña() {
        return campaña;
    }
    
    public MedidaCanal getMedidaCanal(Canal canal) {
       return medidasCanal.buscar(canal);
    }

    public MedidaCanal [] getMedidasCanal() {
       return medidasCanal.buscarTodas();
    }
    
    public MedidaCanal addMedidaCanal(Canal canal, double value, String magnitud) {
       return medidasCanal.nuevo(canal, value, magnitud);
    }

    public CurvaCorregida [] getCorrecciones() {
       return AlmacenCurvasCorregidas.getInstance().buscar(this);
    }

    public void setCampaña(Campaña campaña) {
        String update= "UPDATE curvas_medidas SET campanya='"+campaña+"' WHERE id="+this.getId()+" ;";
        BD bd= BD.getInstance();
        bd.update(update);
        this.campaña = campaña;
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

   Modulo getModulo() {
      return campaña.getModulo();
   }

}
