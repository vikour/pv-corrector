/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vikour
 */
public class Importador {
    
    private static final String EXTENSION_COMPRIMIDO = ".zip";
    private FormatoFichero fmt;
    private File file;

    public Importador(FormatoFichero fmt, File file) throws FileNotFoundException {
        this.fmt = fmt;
        this.file = file;
        
        if (!file.exists())
            throw new FileNotFoundException();
        
    }
    
    public List<Object> importar() {
        List<Object> list = new ArrayList<>();
        List<File> files = new ArrayList<>();
        
        if (!file.isDirectory() && file.getName().matches("[a-zA-Z0-9]+."+EXTENSION_COMPRIMIDO))
            descomprimir();
        
        if (file.isDirectory())
            files = leerFicheros();
        else
            files.add(file);
        
        for (File f : files) {
            Object o = fmt.leer(f);
            
            if (o != null)
                list.add(o);
            
        }
        
        return list;
    }
    
    private void descomprimir() {
        System.out.println("Descomprimir");
    }
    
    private List<File> leerFicheros() {
        List<File> files = new ArrayList<>();
        
        for (File f : file.listFiles())
            files.add(f);
        
        return files;
    }
    
}
