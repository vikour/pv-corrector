/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.List;

/**
 *
 * @author Vikour
 */
public class Importador {
    
    private FormatoFichero fmt;
    private File file;

    public Importador(FormatoFichero fmt, File file) {
        this.fmt = fmt;
        this.file = file;
    }
    
    public List<Object> importar() {
        return null;
    }
    
    private void descomprimir() {
        
    }
    
    private List<File> leerFicheros() {
        return null;
    }
    
}
