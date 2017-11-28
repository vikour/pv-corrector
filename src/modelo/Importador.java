/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.*;

/**
 * @author Víctor Manuel Ortiz Guardeño.
 */

public class Importador {
    
    public static final String EXTENSION_COMPRIMIDO = "zip";
    private FormatoFichero fmt;
    private File file;
    private File tmp;

    public Importador(FormatoFichero fmt, File file) {
        this.fmt = fmt;
        this.file = file;
        this.tmp = null;
    }
    
    public List<Object> importar() throws FileNotFoundException, IOException {
        List<Object> list = new ArrayList<>();
        List<File> files = new ArrayList<>();

        try {
            if (!file.isDirectory() && file.getName().contains("."+EXTENSION_COMPRIMIDO)) {
                files = descomprimir();
            } else if (file.isDirectory()) {
                files = leerFicheros();
            } else {
                files.add(file);
            }

            for (File f : files) {
                Object o = fmt.leer(f);

                if (o != null) {
                    list.add(o);
                }

            }
        } finally {

            if (tmp != null) {
                borrarRecursivamente(tmp);
            }

        }

        return list;
    }
    
    private List<File> descomprimir() throws FileNotFoundException, IOException {
        ZipInputStream zis = null;
        List<File> files = new ArrayList<>();
        
        tmp = File.createTempFile(file.getName().replace("."+EXTENSION_COMPRIMIDO, ""), 
                                    Long.toString(System.nanoTime()));
        
        if (tmp.exists())
            borrarRecursivamente(tmp);
        
        if (!(tmp.mkdir()))
            throw new IOException("No se pudo descomprimir el fichero " + file.getName());
        
        
        try {
            zis = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = null;
            
            while ((entry  = zis.getNextEntry()) != null) {
                File f = new File(tmp.getAbsolutePath() + File.separator + entry.getName());

                if (entry.isDirectory())
                    f.mkdir();
                else {
                    f.createNewFile();
                    files.add(f);
                    descomprimirFichero(zis, f);
                }

            }
            
        }
        finally {
            
            if (zis != null)
                zis.close();
            
        }
        
        return files;
    }
    
    private List<File> leerFicheros() {
        List<File> files = new ArrayList<>();
        
        for (File f : file.listFiles())
            files.add(f);
        
        return files;
    }

    private void descomprimirFichero(ZipInputStream zis, File f) throws FileNotFoundException, IOException {
        byte[] buffer = new byte[1024];
        int leido;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(f);
            
            while (0 < (leido = zis.read(buffer))) {
                fos.write(buffer, 0, leido);
            }

            fos.close();
        }
        finally {
            if (fos != null)
                fos.close();
        }

    }

    private void borrarRecursivamente(File tmp) {
        
        if (tmp.isDirectory()) {
            
            for (File f : tmp.listFiles())
                borrarRecursivamente(f);


        }
        
        tmp.delete();
    }
    
}
