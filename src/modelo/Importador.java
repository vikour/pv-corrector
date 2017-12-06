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
import java.util.logging.Logger;
import java.util.zip.*;

/**
 * @author Víctor Manuel Ortiz Guardeño.
 */

public class Importador implements IFormatoFicheroNotificable {
    
    public static final String EXTENSION_COMPRIMIDO = "zip";
    
    private ImportadorNotificable importadorNotificable;
    private FormatoFichero fmt;
    private File file;
    private File tmp;
    private File fileProccess; //Fichero que se esta procesando actualmente.
    private boolean sobreescribir_todo;
    private boolean ignorar_todo;
    private boolean terminar;

    public Importador(FormatoFichero fmt, File file) {
        this(fmt, file, null);
    }
    
    public Importador(FormatoFichero fmt, File file, ImportadorNotificable importadorNotificable) {
        this.fmt = fmt;
        this.file = file;
        this.tmp = null;
        fmt.setFormatoNotificable(this);
        this.importadorNotificable = importadorNotificable;
    }

    public void setImportadorNotificable(ImportadorNotificable importadorNotificable) {
        this.importadorNotificable = importadorNotificable;
    }
    
    public List<Object> importar() throws FileNotFoundException, IOException {
        List<Object> list = new ArrayList<>();
        List<File> files = new ArrayList<>();

        ignorar_todo = sobreescribir_todo = terminar= false;

        
        try {
            if (!file.isDirectory() && file.getName().contains("."+EXTENSION_COMPRIMIDO)) {
                files = descomprimir();
            } else if (file.isDirectory()) {
                files = leerFicheros();
            } else {
                files.add(file);
            }
            
            for (File f : files) {
                fileProccess = f;
                Object o = fmt.leer(f);

                if (o != null) {
                    list.add(o);
                }

            }
        } finally {

            if (tmp != null) {
                borrarRecursivamente(tmp);
            }

            ignorar_todo = sobreescribir_todo = terminar= false;

        }

        return list;
    }
    
    private List<File> descomprimir() throws FileNotFoundException, IOException {
        ZipInputStream zis = null;
        List<File> files = new ArrayList<>();
        
        tmp = File.createTempFile("tmp", "12345");
        
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
            
            if (!f.isDirectory())
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

    @Override
    public void alertFormatoFichero(String mensaje) {
        int value = ImportadorNotificable.OK;
        
        if (!ignorar_todo) {
            value = importadorNotificable.notificarErorFormato(fileProccess);
            ignorar_todo = value == ImportadorNotificable.IGNORAR_TODO;
        }
        
        
            
    }

    @Override
    public boolean confirmSobrescribirFormatoFichero(Object[] key) {
        boolean result = false;
        int value = ImportadorNotificable.OK;
        
        if (sobreescribir_todo)
            result = true;
        else {
            value = importadorNotificable.notificarSobreescribir(fileProccess, key);
            sobreescribir_todo = value == ImportadorNotificable.SOBREESCRIBIR_TODO;
            result = value == importadorNotificable.SOBREESCRIBIR || sobreescribir_todo;
        }
        
        return result;
    }
    
}
