/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vikour
 */
public class FormatoModulo extends FormatoFichero {

    public FormatoModulo(IFormatoFicheroNotificable notificar) {
        super(notificar);
    }

    @Override
    public Object leer(File file) {
        try {
            Scanner sc =new Scanner(file);
            sc.useDelimiter("\\s*[\n]\\s*");
            Modulo mod=new Modulo(sc.next(),sc.next());
            mod.setTinicial(Double.parseDouble(sc.next()));
            mod.setTintermedia(Double.parseDouble(sc.next()));
            mod.setTfinal(Double.parseDouble(sc.next()));
            mod.setCmaxp(Double.parseDouble(sc.next()));
            mod.setCmaxN(Double.parseDouble(sc.next()));
            mod.setPtramo(Integer.parseInt(sc.next()));
            mod.setStramo(Integer.parseInt(sc.next()));
            mod.setPdv(Double.parseDouble(sc.next()));
            mod.setPdc(Double.parseDouble(sc.next()));
            mod.setPdp(Double.parseDouble(sc.next()));
            int nmed=Integer.parseInt(sc.next());
            mod.setAlpha(Double.parseDouble(sc.next()));
            sc.next();
            mod.setBeta(Double.parseDouble(sc.next()));
            sc.next();
            mod.setGamma(Double.parseDouble(sc.next()));
            sc.next();
            mod.setKappa(Double.parseDouble(sc.next()));
            sc.next();
            mod.setNoct(Double.parseDouble(sc.next()));
            mod.setIsc(Double.parseDouble(sc.next()));
            mod.setVoc(Double.parseDouble(sc.next()));
            mod.setPmax(Double.parseDouble(sc.next()));
            mod.setIpmax(Double.parseDouble(sc.next()));
            mod.setVpmax(Double.parseDouble(sc.next()));
            mod.setIscn(Double.parseDouble(sc.next()));
            mod.setVocn(Double.parseDouble(sc.next()));
            mod.setPmaxn(Double.parseDouble(sc.next()));
            mod.setIpmaxn(Double.parseDouble(sc.next()));
            mod.setVpmaxn(Double.parseDouble(sc.next()));
            mod.setEta(Double.parseDouble(sc.next()));
            mod.setM(Double.parseDouble(sc.next()));
            mod.setNs(Double.parseDouble(sc.next()));
            mod.setNp(Double.parseDouble(sc.next()));
            mod.setRs(Double.parseDouble(sc.next()));
            mod.setMinIsc(Double.parseDouble(sc.next()));
            mod.setMinVoc(Double.parseDouble(sc.next()));
            mod.setMinPmax(Double.parseDouble(sc.next()));
            mod.setMinFF(Double.parseDouble(sc.next()));
            mod.setMt1(Double.parseDouble(sc.next()));
            sc.next();
            if(sc.hasNext()){
                System.out.println("Esto es lo que queda en el sc: ");
                while(sc.hasNext()){
                    System.out.println(sc.next());
                }
            }else{
                System.out.println("Todo bien");
            }
            
            
            
            
            
            
            
            
            
            
            return mod;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormatoModulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
