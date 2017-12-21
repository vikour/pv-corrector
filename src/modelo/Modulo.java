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
   private String tecnologia;
   private double isc;
   private double pmax;
   private double voc;
   private double ipmax;
   private double vpmax;
   private double iscn;
   private double pmaxn;
   private double noct;
   private double vocn;
   private double ipmaxn;
   private double vpmaxn;
   private double mt1; //maxima tension en el primer punto
   private double pdv; //precision del voltaje
   private double pdc; //precision de corriente
   private double pdp; //precision de potencia
   private double tinicial; // tension inicial.
   private double tintermedia;
   private double tfinal; 
   private int ptramo; //primer y segundo tramo
   private int stramo;
   private double cmaxp; //coriente maxima positiva
   private double cmaxN; //corriente maxima negativa.
   private double ns; //celilas en serie
   private double eta; 
   private double m;
   private double rs;
   private double np;
   private double minIsc;
   private double minPmax;
   private double minVoc;
   private double minFF;
   
   
   static void Borrar(String nombre){
       BD bd=BD.getInstance();
       bd.delete("DELETE FROM modulos WHERE nombre='"+nombre+"'");
   }
   
   /**
    * @deprecated Usar el método {@link #buscarTodos() }
    * 
    * @return 
    */
   
   public static List<Modulo> listar( )
   {
      return buscarTodos();
   }
   
   
   
   static List<Modulo> buscarTodos()
   {
      List <Modulo> mod=new ArrayList();
      BD bd=BD.getInstance();
      String str="SELECT nombre FROM modulos";
      
      for (String[] tupla : BD.getInstance().select(str)) {
         mod.add(buscar(tupla[0]));
      }

      return mod;
   }
   
   
   
   /**
    * Crea en la base de datos el módulo con nombre pasado como argumento. 
    * 
    * @param nombre Nombre del nuevo módulo.
    * 
    * @throws Error Si existe un modulo con el nombre pasado como argumento
    *                      en la base de datos.
    */
    
   Modulo(String nombre) throws Error {
        BD bd=BD.getInstance();
        String insert="INSERT INTO modulos (nombre) VALUES('"+nombre+"');";
        this.nombre=nombre;
        bd.insert(insert);
   }
   
   
   /**
    * Constructor privado para construcciones de búsqueda.
    */
   
   private Modulo() { }
   
   
   
   static Modulo buscar (String nombre){
       Modulo modulo = null;
       List<String[]> l=new ArrayList();
       BD bd=BD.getInstance();
       
       String str= "SELECT * FROM modulos WHERE nombre='"+nombre+"' ;";
       //Busco en la base de datos
       l=bd.select(str);
       
       if(!l.isEmpty()){
           modulo = new Modulo();
           String[] aux=l.get(0);
           modulo.nombre=aux[0];
           modulo.alpha= Double.parseDouble(aux[1]);
           modulo.beta= Double.parseDouble(aux[2]);
           modulo.gamma= Double.parseDouble(aux[3]);
           modulo.kappa= Double.parseDouble(aux[4]);
           modulo.tecnologia=aux[5];
           modulo.isc=Double.parseDouble(aux[6]);
           modulo.pmax=Double.parseDouble(aux[7]);
           modulo.voc=Double.parseDouble(aux[8]);
           modulo.ipmax=Double.parseDouble(aux[9]);
           modulo.vpmax=Double.parseDouble(aux[10]);
           modulo.iscn=Double.parseDouble(aux[11]);
           modulo.pmaxn=Double.parseDouble(aux[12]);
           modulo.noct=Double.parseDouble(aux[13]);
           modulo.vocn=Double.parseDouble(aux[14]);
           modulo.ipmaxn=Double.parseDouble(aux[15]);
           modulo.vpmaxn=Double.parseDouble(aux[16]);
           modulo.mt1=Double.parseDouble(aux[17]); //maxima tension en el primer punto
           modulo.pdv=Double.parseDouble(aux[18]); //precision del voltaje
           modulo.pdc=Double.parseDouble(aux[19]); //precision de corriente
           modulo.pdp=Double.parseDouble(aux[20]); //precision de potencia
           modulo.tinicial=Double.parseDouble(aux[21]); // tension inicial.
           modulo.tintermedia=Double.parseDouble(aux[22]);
           modulo.tfinal=Double.parseDouble(aux[23]); 
           modulo.ptramo=Integer.parseInt(aux[24]); //primer y segundo tramo
           modulo.stramo=Integer.parseInt(aux[25]);
           modulo.cmaxp=Double.parseDouble(aux[26]); //coriente maxima positiva
           modulo.cmaxN=Double.parseDouble(aux[27]); //corriente maxima negativa.
           modulo.ns=Double.parseDouble(aux[28]); //celilas en serie
           modulo.eta=Double.parseDouble(aux[29]); 
           modulo.m=Double.parseDouble(aux[30]);
           modulo.rs=Double.parseDouble(aux[31]);
           modulo.np=Double.parseDouble(aux[32]);
           modulo.minIsc=Double.parseDouble(aux[33]);
           modulo.minPmax=Double.parseDouble(aux[34]);
           modulo.minVoc=Double.parseDouble(aux[35]);
           modulo.minFF=Double.parseDouble(aux[36]);
       }
         
       return modulo;
   }
  
    
    public void setNombre(String nombre) {
        BD bd=BD.getInstance();
        //UPDATE modulos SET alpha=100 WHERE nombre = 'HABER SI ME MUERO'
        bd.update("UPDATE modulos SET nombre='"+nombre+"' WHERE nombre = '"+this.nombre+"' ;");
        this.nombre = nombre;
        
    }

    public void setAlpha(double alpha) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET alpha="+alpha+" WHERE nombre='"+this.nombre+"';");
        this.alpha = alpha;
    }

    public void setBeta(double beta) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET beta="+beta+" WHERE nombre='"+this.nombre+"';");
        this.beta = beta;
    }

    public void setGamma(double gamma) {
        BD bd=BD.getInstance();
        
        
        bd.update("UPDATE modulos SET gamma="+gamma+" WHERE nombre='"+this.nombre+"';");
        this.gamma = gamma;
    }

    public void setKappa(double kappa) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET kappa="+kappa+" WHERE nombre='"+this.nombre+"';");
        this.kappa = kappa;
    }

    public void setCampañas(List<Campaña> campañas) {
        this.campañas = Campaña.listar(this);
    }
    
    public void setMinIsc(double minIsc) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET minIsc="+minIsc+" WHERE nombre='"+this.nombre+"';");
        this.minIsc = minIsc;
    }

    public void setMinPmax(double minPmax) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET minPmax="+minPmax+" WHERE nombre='"+this.nombre+"';");
        this.minPmax = minPmax;
    }

    public void setMinVoc(double minVoc) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET minVoc="+minVoc+" WHERE nombre='"+this.nombre+"';");
        this.minVoc = minVoc;
    }

    public void setMinFF(double minFF) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET minFF="+minFF+" WHERE nombre='"+this.nombre+"';");
        this.minFF = minFF;
    }

    public void setTecnologia(String tecnologia) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET tecnologia='"+tecnologia+"' WHERE nombre='"+this.nombre+"';");
        this.tecnologia = tecnologia;
    }

    public void setIsc(double isc) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET isc="+isc+" WHERE nombre='"+this.nombre+"';");
        this.isc = isc;
    }

    public void setPmax(double pmax) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET pmax="+pmax+" WHERE nombre='"+this.nombre+"';");
        this.pmax = pmax;
    }

    public void setVoc(double voc) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET voc="+voc+" WHERE nombre='"+this.nombre+"';");
        this.voc = voc;
    }

    public void setIpmax(double ipmax) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET ipmax="+ipmax+" WHERE nombre='"+this.nombre+"';");
        this.ipmax = ipmax;
    }

    public void setVpmax(double vpmax) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET vpmax="+vpmax+" WHERE nombre='"+this.nombre+"';");
        this.vpmax = vpmax;
    }

    public void setIscn(double iscn) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET iscn="+iscn+" WHERE nombre='"+this.nombre+"';");
        this.iscn = iscn;
    }

    public void setPmaxn(double pmaxn) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET pmaxn="+pmaxn+" WHERE nombre='"+this.nombre+"';");
        this.pmaxn = pmaxn;
    }

    public void setNoct(double noct) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET noct="+noct+" WHERE nombre='"+this.nombre+"';");
        this.noct = noct;
    }

    public void setVocn(double vocn) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET vocn="+vocn+" WHERE nombre='"+this.nombre+"';");
        this.vocn = vocn;
    }

    public void setIpmaxn(double ipmaxn) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET ipmaxn="+ipmaxn+" WHERE nombre='"+this.nombre+"';");
        this.ipmaxn = ipmaxn;
    }

    public void setVpmaxn(double vpmaxn) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET vpmaxn="+vpmaxn+" WHERE nombre='"+this.nombre+"';");
        this.vpmaxn = vpmaxn;
    }

    public void setMt1(double mt1) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET mt1="+mt1+" WHERE nombre='"+this.nombre+"';");
        this.mt1 = mt1;
    }

    public void setPdv(double pdv) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET pdv="+pdv+" WHERE nombre='"+this.nombre+"';");
        this.pdv = pdv;
    }

    public void setPdc(double pdc) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET pdc="+pdc+" WHERE nombre='"+this.nombre+"';");
        this.pdc = pdc;
    }

    public void setPdp(double pdp) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET pdp="+pdp+" WHERE nombre='"+this.nombre+"';");
        this.pdp = pdp;
    }

    public void setTinicial(double tinicial) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET tinicial="+tinicial+" WHERE nombre='"+this.nombre+"';");
        this.tinicial = tinicial;
    }

    public void setTintermedia(double tintermedia) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET tintermedia="+tintermedia+" WHERE nombre='"+this.nombre+"';");
        this.tintermedia = tintermedia;
    }

    public void setTfinal(double tfinal) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET tfinal="+tfinal+" WHERE nombre='"+this.nombre+"';");
        this.tfinal = tfinal;
    }

    public void setPtramo(int ptramo) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET ptramo="+ptramo+" WHERE nombre='"+this.nombre+"';");
        this.ptramo = ptramo;
    }

    public void setStramo(int stramo) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET stramo="+stramo+" WHERE nombre='"+this.nombre+"';");
        this.stramo = stramo;
    }

    public void setCmaxp(double cmaxp) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET cmaxp="+cmaxp+" WHERE nombre='"+this.nombre+"';");
        this.cmaxp = cmaxp;
    }

    public void setCmaxN(double cmaxN) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET cmaxN="+cmaxN+" WHERE nombre='"+this.nombre+"';");
        this.cmaxN = cmaxN;
    }

    public void setNs(double ns) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET ns="+ns+" WHERE nombre='"+this.nombre+"';");
        this.ns = ns;
    }

    public void setEta(double eta) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET eta="+eta+" WHERE nombre='"+this.nombre+"';");
        this.eta = eta;
    }

    public void setM(double m) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET m="+m+" WHERE nombre='"+this.nombre+"';");
        this.m = m;
    }

    public void setRs(double rs) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET rs="+rs+" WHERE nombre='"+this.nombre+"';");
        this.rs = rs;
    }

    public void setNp(double np) {
        BD bd=BD.getInstance();
        
        bd.update("UPDATE modulos SET np="+np+" WHERE nombre='"+this.nombre+"';");
        this.np = np;
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

    public String getTecnologia() {
        return tecnologia;
    }

    public double getIsc() {
        return isc;
    }

    public double getPmax() {
        return pmax;
    }

    public double getVoc() {
        return voc;
    }

    public double getIpmax() {
        return ipmax;
    }

    public double getVpmax() {
        return vpmax;
    }

    public double getIscn() {
        return iscn;
    }

    public double getPmaxn() {
        return pmaxn;
    }

    public double getNoct() {
        return noct;
    }

    public double getVocn() {
        return vocn;
    }

    public double getIpmaxn() {
        return ipmaxn;
    }

    public double getVpmaxn() {
        return vpmaxn;
    }

    public double getMt1() {
        return mt1;
    }

    public double getPdv() {
        return pdv;
    }

    public double getPdc() {
        return pdc;
    }

    public double getPdp() {
        return pdp;
    }

    public double getTinicial() {
        return tinicial;
    }

    public double getTintermedia() {
        return tintermedia;
    }

    public double getTfinal() {
        return tfinal;
    }

    public int getPtramo() {
        return ptramo;
    }

    public int getStramo() {
        return stramo;
    }

    public double getCmaxp() {
        return cmaxp;
    }

    public double getCmaxN() {
        return cmaxN;
    }

    public double getNs() {
        return ns;
    }

    public double getEta() {
        return eta;
    }

    public double getM() {
        return m;
    }

    public double getRs() {
        return rs;
    }

    public double getNp() {
        return np;
    }

    public double getMinIsc() {
        return minIsc;
    }

    public double getMinPmax() {
        return minPmax;
    }

    public double getMinVoc() {
        return minVoc;
    }

    public double getMinFF() {
        return minFF;
    }


    
    
       @Override
    public String toString() {
        return "Nombre: " + nombre + "\nAlpha: " + alpha + "\nBeta: " + beta + "\nGamma: " + gamma + "\nKappa: " + kappa;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Modulo)
                && (((Modulo) obj).nombre.equals(nombre));
    }

}
