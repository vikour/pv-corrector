/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vikour
 */
public class FormatoModulo extends FormatoFichero {
    private IFormatoFicheroNotificable notificar;
    
    private String nombre;
    private String tecnologia;
    private List<Double> list=new ArrayList<>();
    public FormatoModulo(IFormatoFicheroNotificable notificar) {
        super(notificar);
        this.notificar=notificar;
    }

    @Override
    public Object leer(File file) {
        try {
            
            Scanner sc =new Scanner(file);
            sc.useDelimiter("\\s*[\n]\\s*");
            
            nombre=sc.next();
            tecnologia=sc.next();
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add((double)Integer.parseInt(sc.next()));
            list.add((double)Integer.parseInt(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            sc.next();
            list.add(Double.parseDouble(sc.next()));
            sc.next();
            list.add(Double.parseDouble(sc.next()));
            sc.next();
            list.add(Double.parseDouble(sc.next()));
            sc.next();
            list.add(Double.parseDouble(sc.next()));
            sc.next();
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            list.add(Double.parseDouble(sc.next()));
            sc.next();
            
            
            if(sc.hasNext()){
                notificar.alertFormatoFichero("El formato del fichero no es correcto");
            }else{
                Modulo mod=new Modulo(nombre,tecnologia);
                mod= parseModulo(list,mod);
                System.out.println("Importacion Exitosa.");
                return mod;
            }
            
            return null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormatoModulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException | NullPointerException | NoSuchElementException ex){
            notificar.alertFormatoFichero("El formato del fichero no es correcto");
        } catch (DuplicatedObjectException ex){
            Object [] k={nombre};
            if(notificar.confirmSobrescribirFormatoFichero(k)){
               Modulo m= new Modulo(nombre);
               m=parseModulo(list,m);
               return m;
            }
        }
        
        return null;
      }
    
    private Modulo parseModulo(List<Double> d,Modulo mod){
            //mod.setNombre(nombre);
            mod.setTecnologia(tecnologia);
            mod.setTinicial(d.get(0));
            mod.setTintermedia(d.get(1));
            mod.setTfinal(d.get(2));
            mod.setCmaxp(d.get(3));
            mod.setCmaxN(d.get(4));
            mod.setPtramo(d.get(5).intValue());
            mod.setStramo(d.get(6).intValue());
            mod.setPdv(d.get(7));
            mod.setPdc(d.get(8));
            mod.setPdp(d.get(9));
            mod.setAlpha(d.get(10));
            mod.setBeta(d.get(11));
            mod.setGamma(d.get(12));
            mod.setKappa(d.get(13));
            mod.setNoct(d.get(14));
            mod.setIsc(d.get(15));
            mod.setVoc(d.get(16));
            mod.setPmax(d.get(17));
            mod.setIpmax(d.get(18));
            mod.setVpmax(d.get(19));
            mod.setIscn(d.get(20));
            mod.setVocn(d.get(21));
            mod.setPmaxn(d.get(22));
            mod.setIpmaxn(d.get(23));
            mod.setVpmaxn(d.get(24));
            mod.setEta(d.get(25));
            mod.setM(d.get(26));
            mod.setNs(d.get(27));
            mod.setNp(d.get(28));
            mod.setRs(d.get(29));
            mod.setMinIsc(d.get(30));
            mod.setMinVoc(d.get(31));
            mod.setMinPmax(d.get(32));
            mod.setMinFF(d.get(33));
            mod.setMt1(d.get(34));
            
        return mod;
    }
 
    @Override
    public void escribir(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getExtension() {
        return "cls"; 
    }
    
}
