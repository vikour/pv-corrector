/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
    
    private String nombre;
    private String tecnologia;
    private boolean sobreescribir;

    @Override
    public Object leer(File file) {
        BufferedReader br=null;
        BD bd= BD.getInstance();
        Modulo m=null;
        sobreescribir=true;
        
        try {
            bd.execute("BEGIN TRANSACTION");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-15"));
            m=construirMod(br);
            if(sobreescribir){
                filetoMod(br,m);
                System.out.println( file.getName()+" : Modulo importado con exito");
                bd.execute("COMMIT");
            }else{
                bd.execute("ROLLBACK");
            }
            

        } catch (FileNotFoundException ex) {
            notificar.alertFormatoFichero("El formato del fichero no es correcto");
            Logger.getLogger(FormatoModulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException | NullPointerException | NoSuchElementException ex){
            bd.execute("ROLLBACK");
            notificar.alertFormatoFichero("El formato del fichero no es correcto");
        } catch (UnsupportedEncodingException ex) {
            bd.execute("ROLLBACK");
            Logger.getLogger(FormatoModulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            bd.execute("ROLLBACK");
            Logger.getLogger(FormatoModulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return m;
      }
    
    private String readNotEmptyLine(BufferedReader br) throws IOException {
        String aux = null;
        
        do {
            aux = br.readLine();
        } while (aux !=null && aux.isEmpty());
        
        return aux;
    }
    
    
    private Modulo construirMod(BufferedReader br) throws IOException{
        AlmacenModulos modulos = AlmacenModulos.getInstance();
        
        nombre=readNotEmptyLine(br);
        tecnologia=readNotEmptyLine(br);
        Modulo mod=null;
        
        
        try {
            mod = modulos.nuevo(nombre);
        }
        catch (Error ex) {
            sobreescribir=notificar.confirmSobrescribirFormatoFichero(new Object[] {nombre});
            mod = modulos.buscar(nombre);
        } 
        
        mod.setTecnologia(tecnologia);
        
        return mod;
    }
    
    private void filetoMod(BufferedReader br,Modulo mod) throws IOException{
        
            mod.setTinicial( Double.parseDouble(readNotEmptyLine(br)) );
            mod.setTintermedia(Double.parseDouble(readNotEmptyLine(br)));
            mod.setTfinal(Double.parseDouble(readNotEmptyLine(br)));
            mod.setCmaxp(Double.parseDouble(readNotEmptyLine(br)));
            mod.setCmaxN(Double.parseDouble(readNotEmptyLine(br)));
            mod.setPtramo(Integer.parseInt(readNotEmptyLine(br)));
            mod.setStramo(Integer.parseInt(readNotEmptyLine(br)));
            mod.setPdv(Double.parseDouble(readNotEmptyLine(br)));
            mod.setPdc(Double.parseDouble(readNotEmptyLine(br)));
            mod.setPdp(Double.parseDouble(readNotEmptyLine(br)));
            mod.setAlpha(Double.parseDouble(readNotEmptyLine(br)));
            mod.setBeta(Double.parseDouble(readNotEmptyLine(br)));
            mod.setGamma(Double.parseDouble(readNotEmptyLine(br)));
            mod.setKappa(Double.parseDouble(readNotEmptyLine(br)));
            mod.setNoct(Double.parseDouble(readNotEmptyLine(br)));
            mod.setIsc(Double.parseDouble(readNotEmptyLine(br)));
            mod.setVoc(Double.parseDouble(readNotEmptyLine(br)));
            mod.setPmax(Double.parseDouble(readNotEmptyLine(br)));
            mod.setIpmax(Double.parseDouble(readNotEmptyLine(br)));
            mod.setVpmax(Double.parseDouble(readNotEmptyLine(br)));
            mod.setIscn(Double.parseDouble(readNotEmptyLine(br)));
            mod.setVocn(Double.parseDouble(readNotEmptyLine(br)));
            mod.setPmaxn(Double.parseDouble(readNotEmptyLine(br)));
            mod.setIpmaxn(Double.parseDouble(readNotEmptyLine(br)));
            mod.setVpmaxn(Double.parseDouble(readNotEmptyLine(br)));
            mod.setEta(Double.parseDouble(readNotEmptyLine(br)));
            mod.setM(Double.parseDouble(readNotEmptyLine(br)));
            mod.setNs(Double.parseDouble(readNotEmptyLine(br)));
            mod.setNp(Double.parseDouble(readNotEmptyLine(br)));
            mod.setRs(Double.parseDouble(readNotEmptyLine(br)));
            mod.setMinIsc(Double.parseDouble(readNotEmptyLine(br)));
            mod.setMinVoc(Double.parseDouble(readNotEmptyLine(br)));
            mod.setMinPmax(Double.parseDouble(readNotEmptyLine(br)));
            mod.setMinFF(Double.parseDouble(readNotEmptyLine(br)));
            mod.setMt1(Double.parseDouble(readNotEmptyLine(br)));
            
        
    }
    
    
 
    @Override
    public void escribir(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getExtension() {
        return "dat"; 
    }
    
}
