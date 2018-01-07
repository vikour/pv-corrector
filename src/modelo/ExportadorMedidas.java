/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ExportadorMedidas {
   
   private FormatoFichero ff;
   private File file;

   public ExportadorMedidas(FormatoFichero ff, File file) {
      this.ff = ff;
      this.file = file;
   }
   

   public void exportar(CurvaMedida medida) {
      
      if (!file.getName().contains(ff.getExtension()))
         file = new File(file.getAbsolutePath()+"."+ff.getExtension());
      
      ff.escribir(file, medida);
   }
   
   public void exportar(Campaña campaña) throws IOException {

      file.mkdir();
      FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + ".zip");
      ZipOutputStream zipOut = new ZipOutputStream(fos);

      exportarFicherosCampaña(campaña);
      comprimirFicherosMedidas(zipOut);

      zipOut.close();
      fos.close();
      borrarRecursivamente(file);

   }

   private void exportarFicherosCampaña(Campaña campaña) {
      
      for (CurvaMedida medida : campaña.getCurvas()) {
         File fMedFile = new File(nombeFicheroMedida(file, medida));
         ff.escribir(fMedFile, medida);
      }
      
   }

   private void comprimirFicherosMedidas(ZipOutputStream zipOut) throws IOException {
      
      for (File f : file.listFiles()) {
         FileInputStream fis = new FileInputStream(f);
         ZipEntry zipEntry = new ZipEntry(f.getName());
         zipOut.putNextEntry(zipEntry);
         byte[] bytes = new byte[1024];
         int length;

         while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
         }

         fis.close();
      }
      
   }

   private String nombeFicheroMedida(File dir, CurvaMedida medida) {
      StringBuilder sb = new StringBuilder(dir.getAbsolutePath());
      
      sb.append(File.separator).append("IV_")
        .append(medida.getModulo().getNombre()).append("_")
        .append(medida.getFecha().replace("\\", "").replace("/", "").replace("-", "")).append("_")
        .append(medida.getHora().replace(":", "")).append("."+ff.getExtension());
      
      return sb.toString();
   }

    private void borrarRecursivamente(File tmp) {
        
        if (tmp.isDirectory()) {
            
            for (File f : tmp.listFiles())
                borrarRecursivamente(f);


        }
        
        tmp.delete();
    }
   
}
