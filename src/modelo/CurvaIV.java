/**
 * @(#) CurvaIV.java
 */
package modelo;

import java.util.List;
import java.util.Set;

public abstract class CurvaIV {
    
    public static int MEDIDA = 0;
    public static int CORREGIDA = 1;
    
    private List<MedidaIntensidad> intensidades;
    private List<MedidaTension> tensiones;
    private String fecha;
    private String hora;
    private Medida isc;
    private Medida voc;
    private Medida pmax;
    private Medida imax;
    private Medida vmax;
    private double ff;
    private int id;
    private int tipo;

    public CurvaIV( int tipo, String fecha, String hora, Medida isc, Medida voc, Medida pmax, Medida imax, Medida vmax, double ff) {
        this.intensidades = null;
        this.tensiones = null;
        String idbd = BD.getInstance().selectEscalar("SElECT MAX(id) + 1 FROM curvas_iv;");
        
        if (idbd == null)
            id = 0;
        else
            id = Integer.valueOf(idbd);
        
        String ins="INSERT INTO curvas_iv VALUES ("+id+","+tipo+", '"+fecha+"', '"+hora+"', "+isc.getValor()+", '"+isc.getMagnitud()+"', "+voc.getValor()+", '"+voc.getMagnitud()+"', "+pmax.getValor()+", '"+pmax.getMagnitud()+"', "+imax.getValor()+", '"+imax.getMagnitud()+"', "+vmax.getValor()+", '"+vmax.getMagnitud()+"', "+ff+");";
        BD.getInstance().insert(ins);
        
        this.tipo = tipo;
        this.fecha = fecha;
        this.hora = hora;
        this.isc = isc;
        this.voc = voc;
        this.pmax = pmax;
        this.imax = imax;
        this.vmax = vmax;
        this.ff = ff;
    }
    
    protected CurvaIV() {
       
    }
    
    protected static void buscar(CurvaIV medida, int id) {
        String select = "SELECT fecha, hora, isc_v ,isc_m, voc_v, voc_m, pmax_v, pmax_m, vmax_v ,vmax_m, ff_v,imax_v,imax_m FROM curvas_iv WHERE id=" + id + " ;";
        BD bd = BD.getInstance();
        List<String[]> l = bd.select(select);

        if (l.isEmpty()) {
            throw new Error("La curva no existe en la base de datos");
        }
        String[] aux = l.get(0);

        medida.id = id;
        medida.fecha = aux[0];
        medida.hora = aux[1];
        medida.isc = new Medida(Double.parseDouble(aux[2]), aux[3]);
        medida.voc = new Medida(Double.parseDouble(aux[4]), aux[5]);
        medida.pmax = new Medida(Double.parseDouble(aux[6]), aux[7]);
        medida.vmax = new Medida(Double.parseDouble(aux[8]), aux[9]);
        medida.ff = Double.parseDouble(aux[10]);
        medida.imax = new Medida(Double.parseDouble(aux[11]), aux[12]);
        medida.intensidades = null;
        medida.tensiones = null;
    }
    
    public void addPuntoIntensidad(MedidaIntensidad intensidad) {
       
       if (intensidades != null && !intensidades.contains(intensidad))
          intensidades.add(intensidad);
       
    }
    
    public List<MedidaIntensidad> getIntensidades() {
       
       if (intensidades == null) 
          intensidades = MedidaIntensidad.listar(this);

        return intensidades;
    }
    
    public void addPuntoTension(MedidaTension tension) {
       
       if (tensiones != null && !tensiones.contains(tension))
          tensiones.add(tension);
       
    }

    public List<MedidaTension> getTensiones() {
       
       if (tensiones == null)
          tensiones = MedidaTension.listar(this);

        return tensiones;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Medida getIsc() {
        return isc;
    }

    public Medida getVoc() {
        return voc;
    }

    public Medida getPmax() {
        return pmax;
    }

    public Medida getVmax() {
        return vmax;
    }

    public double getFf() {
        return ff;
    }

    public int getId() {
        return id;
    }

    public Medida getImax() {
        return imax;
    }
    
    public void setFecha(String fecha) {
        String up="UPDATE curvas_iv SET fecha="+fecha+" WHERE id="+this.id+" ; ";
        BD bd=BD.getInstance();
        bd.update(up);
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        String up="UPDATE curvas_iv SET hora="+hora+" WHERE id="+this.id+" ; ";
        BD bd=BD.getInstance();
        bd.update(up);
        this.hora = hora;
    }

    public void setIsc(Medida isc) {
        double a= isc.getValor();
        String b=isc.getMagnitud();
        String up="UPDATE curvas_iv SET isc_v="+a+", isc_m='"+b+"' WHERE id="+this.id+" ; ";
        BD bd=BD.getInstance();
        bd.update(up);
        this.isc = isc;
    }

    public void setVoc(Medida voc) {
        double a= voc.getValor();
        String b=voc.getMagnitud();
        String up="UPDATE curvas_iv SET voc_v="+a+", voc_m='"+b+"' WHERE id="+this.id+" ; ";
        BD bd=BD.getInstance();
        bd.update(up);
        this.voc = voc;
    }

    public void setPmax(Medida pmax) {
        double a= pmax.getValor();
        String b=pmax.getMagnitud();
        String up="UPDATE curvas_iv SET pmax_v="+a+", pmax_m='"+b+"' WHERE id="+this.id+" ; ";
        BD bd=BD.getInstance();
        bd.update(up);
        this.pmax = pmax;
    }

    public void setVmax(Medida vmax) {
        double a= vmax.getValor();
        String b=vmax.getMagnitud();
        String up="UPDATE curvas_iv SET vmax_v="+a+", vmax_m='"+b+"' WHERE id="+this.id+" ; ";
        BD bd=BD.getInstance();
        bd.update(up);
        this.vmax = vmax;
    }

    public void setFf(double ff) {
        String up="UPDATE curvas_iv SET ff_v="+ff+" WHERE id="+this.id+" ; ";
        BD bd=BD.getInstance();
        bd.update(up);
        this.ff = ff;
    }
    
    public void setImax(Medida imax) {
        String upd = "UPDATE curva_iv SET imax_v = " + imax.getValor() + ", " +
                     "imax_m = '" + imax.getMagnitud() + "' WHERE " +
                     "id = " + id;
        BD.getInstance().update(upd);
        this.imax = imax;
    }
    
    @Override
    public String toString(){
        return"ID: "+id+"\nFecha: "+fecha+"\nHora: "+hora+"\nISC: "+isc+"\nVOC: "+voc+"\nPMAX: "+pmax+"\nVMAX: "+vmax+"\nFF: "+ff;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof CurvaIV){
            CurvaIV aux=(CurvaIV)o;
            if(aux.id==this.id){
                return true;
            }
        }
        return false;
    }
    
    public static void borrar(String fecha, String hora) {
        String rmv = "DELETE FROM curvas_iv WHERE fecha = '" +fecha+"' AND hora = '" + hora + "'";
        BD.getInstance().delete(rmv);
    }
    
}
