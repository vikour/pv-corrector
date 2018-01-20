/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;

/**
 *
 * @author elias
 */
public class ExportadorModulos {
    
   private FormatoFichero ff;
   private File file;

    public ExportadorModulos(FormatoFichero ff, File file) {
        this.ff = ff;
        this.file = file;
    }
   
   public void exportar(Modulo mod){
       if (!file.getName().contains(ff.getExtension()))
         file = new File(file.getAbsolutePath()+"."+ff.getExtension());
      
      ff.escribir(file, mod);
   }
   
   
   
}
