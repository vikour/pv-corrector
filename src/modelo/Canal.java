/**
 * @(#) Canal.java
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

public class Canal {

    public static List<Canal> listar(Campaña c) {
        BD bd= BD.getInstance();
        List<Canal> l=new ArrayList<>();
        String select="SELECT c.nombre FROM canales c, medidas_canal m, curvas_medidas cur , campanyas cam WHERE c.nombre=m.canal and m.curva_iv=cur.id and cur.campanya=cam.nombre and cam.nombre='"+c.getNombre()+"' ;";
        List<String[]> aux=bd.select(select);
        for(int i=0; i<aux.size();i++){
            l.add(new Canal(aux.get(i)[0],false));
        }
        return l;
    }
    private String nombre;

    private List<MedidaSensor> medidas;

    private List<Campaña> campaña;
    
    public Canal(String nombre, boolean store){
        BD bd=BD.getInstance();
        List<String[]> l =bd.select("SELECT * FROM canales WHERE nombre='"+nombre+"'");
        
        if(l.isEmpty() && store){
            bd.insert("INSERT INTO canales VALUES ('"+nombre+"')");
            this.nombre=nombre;
        }else if(!store && !l.isEmpty()){
            this.nombre=l.get(0)[0];
        }else{
            //esto luego se controla en la importacion de campaña
            throw new Error("El canal ya se encuentra en el sistema");
        } 
    }

    public String getNombre() {
        return nombre;
    }

    public List<MedidaSensor> getMedidas(CurvaIV c) {
        BD bd=BD.getInstance();
        String sel="SELECT valor, magnitud FROM medidas_canal WHERE canal='"+this.nombre+"' curva_iv="+c.getId()+";";
        List<String[] > aux= bd.select(sel);
        List<MedidaSensor> l=new ArrayList<>();
        
        for(int i=0; i<aux.size();i++){
            l.add(new MedidaSensor(Double.parseDouble(aux.get(i)[0]), aux.get(i)[1],this,c) );
        }
        medidas=l;
        return medidas;
        
    }

    public List<Campaña> getCampaña() {
        BD bd=BD.getInstance();
        String sel="SELECT c.modulo, c-campaña  FROM medidas_canal m, curvas_medidas c WHERE m.curva_iv=c.id and m.canal='"+this.nombre+"'";
        List<String[] > aux= bd.select(sel);
        List<Campaña> l=new ArrayList<>();
        AlmacenCampañas campañas = AlmacenCampañas.getInstance();
        
        for(int i=0; i<aux.size();i++){
            //l.add(new Campaña(new Modulo(aux.get(i)[0]), aux.get(i)[1], false ) );
            l.add(campañas.buscar(aux.get(i)[0], aux.get(i)[1]));

        }
        campaña=l;
        return campaña;
    }

    public void setNombre(String nombre) {
        BD bd=BD.getInstance();
        bd.update("UPDATE canal SET nombre='"+nombre+"' WHERE nombre='"+this.nombre+"' ;");
        this.nombre = nombre;
    }

    public void setMedidas(List<MedidaSensor> medidas) {     
        this.medidas = medidas;
    }

    public void setCampaña(List<Campaña> campaña) {
        this.campaña = campaña;
    }

    @Override
    public boolean equals(Object o){
        
        if(o instanceof Canal){
            Canal aux= (Canal) o;
            if(this.nombre.equals(((Canal) o).nombre)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Nombre: "+this.nombre;
    }


}
    
    
    

