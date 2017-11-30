/**
 * @(#) Modulo.java
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

public class Modulo
{
   private List<Campaña> campañas;
   
   private String nombre;
   
   private double alpha;
   
   private double beta;
   
   private double gamma;
   
   private double kappa;
   
   public static List<Modulo> listar( )
   {
      List <Modulo> mod=new ArrayList();
      BD bd=BD.getInstance();
      String str="SELECT nombre FROM modulos";
      
      for (String[] tupla : BD.getInstance().select(str)) {
         mod.add(new Modulo(tupla[0]));
      }

      return mod;
   }
   
   public Modulo(String nombre){
       List<String[]> l=new ArrayList();
       BD bd=BD.getInstance();
       
       String str= "SELECT * FROM modulos WHERE nombre='"+nombre+"' ;";
       //Busco en la base de datos
       l=bd.select(str);
       
       
       if(!l.isEmpty()){
           String[] aux=l.get(0);
           this.nombre=aux[0];
           alpha= Double.parseDouble(aux[1]);
           beta= Double.parseDouble(aux[2]);
           gamma= Double.parseDouble(aux[3]);
           kappa= Double.parseDouble(aux[4]);
          
       }
         
   }
   
   public Modulo(String n, double a, double b, double g, double k ){
        BD bd=BD.getInstance();
        nombre=n;
        alpha=a;
        beta=b;
        gamma=g;
        kappa=k;
        
        String select="SELECT * FROM modulos WHERE nombre='"+nombre+"' ;";
        
        
        if(bd.select(select).size()==0){
            String insert="INSERT INTO modulos (nombre, alpha, beta, gamma, kappa) VALUES('"+n+"',"+a+","+b+","+g+","+k+");";
            bd.insert(insert);
        }else{
            throw new RuntimeException("El modulo ya está incluido en el sistema");
        }
   }

    public List<Campaña> getCampañas() {
        if(campañas==null){
            campañas=Campaña.listar(this);
        }
        return campañas;
    }

    public String getNombre() {
        return nombre;
    }

    public double getAlpha() {
        return alpha;
    }

    public double getBeta() {
        return beta;
    }

    public double getGamma() {
        return gamma;
    }

    public double getKappa() {
        return kappa;
    }
    
    public void setNombre(String nombre) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET nombre='"+nombre+"' WHERE nombre='"+this.nombre+"'");
        this.nombre = nombre;
        
    }

    public void setAlpha(double alpha) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET alpha="+alpha+" WHERE nombre='"+this.nombre+"'");
        this.alpha = alpha;
    }

    public void setBeta(double beta) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET beta="+beta+" WHERE nombre='"+this.nombre+"'");
        this.beta = beta;
    }

    public void setGamma(double gamma) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET gamma="+gamma+" WHERE nombre='"+this.nombre+"'");
        this.gamma = gamma;
    }

    public void setKappa(double kappa) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET kappa="+kappa+" WHERE nombre='"+this.nombre+"'");
        this.kappa = kappa;
    }
   
   @Override
   public String toString(){
       return "Nombre: "+nombre+"\nAlpha: "+alpha+"\nBeta: "+beta+"\nGamma: "+gamma+"\nKappa: "+kappa;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Modulo) &&
               (((Modulo) obj).nombre.equals(nombre));
    }
   
}
