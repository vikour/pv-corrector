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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
