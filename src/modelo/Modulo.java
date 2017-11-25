/**
 * @(#) Modulo.java
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

public class Modulo
{
   private Campaña[] campañas;
   
   private String nombre;
   
   private double alpha;
   
   private double beta;
   
   private double gamma;
   
   private double kappa;
   
   public static List<Modulo> listar( )
   {
      List <Modulo> mod=new ArrayList();
      BD bd=BD.getInstance();
      String str="SELECT * FROM modulos";
      List<String[]> l = bd.select(str);
      for(String [] e: l){
          
          mod.add(new Modulo(e[0],Double.parseDouble(e[1]),Double.parseDouble(e[2]),Double.parseDouble(e[3]),Double.parseDouble(e[4])));
      }
      return mod;
   }
   
   public static Modulo importar( String pathfile )
   {
      return null;
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
        }
   }
   
   
   @Override
   public String toString(){
       return "Nombre: "+nombre+"\nAlpha: "+alpha+"\nBeta: "+beta+"\nGamma: "+gamma+"\nKappa: "+kappa;
    }
   
   
}
