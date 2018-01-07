/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FormatoCampaña extends FormatoFichero{

    private boolean sobreescribir;

    @Override
    public Object leer(File file) {
        BufferedReader br = null;
        BD bd = BD.getInstance();
        Campaña campaña = null;
        CurvaMedida curva = null;
        boolean rollback = false;
        
        sobreescribir = true;
        
        try {
            bd.execute("BEGIN TRANSACTION");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-15"));
            
            campaña = leerInfoBasica(br);
            curva = leerInfoBasicaCurva(br, campaña);
            
            if (sobreescribir) {
                leerCanales(br, curva);
                leerMedidasCurva(br, curva);
                bd.execute("COMMIT");
            }
            else
                rollback = true;
        } 
        catch (IOException ex) {
            rollback = true;
            ex.printStackTrace();
        }
        catch (Exception ex) {
            rollback = true;
            notificar.alertFormatoFichero("Formato del fichero incorrecto");
        }
        finally {
            
            if (rollback || curva == null || campaña == null)
                bd.execute("ROLLBACK");
            
            sobreescribir = false;
        }
        
        return campaña;
    }

    private String readNotEmptyLine(BufferedReader br) throws IOException {
        String aux = null;
        
        do {
            aux = br.readLine();
        } while (aux !=null && aux.isEmpty());
        
        return aux;
    }

    private String extractValue(String line) {
        line = line.trim();
        return line.split("\t")[1];
    }

    @Override
    public void escribir(File file, Object objeto) {
       BufferedWriter bw = null;
       CurvaMedida medida = (CurvaMedida) objeto;
       
       try {
          bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "ISO-8859-15"));
          
          bw.write("Módulo:\t"+medida.getModulo().getNombre()+"\n");
          bw.write("Campaña:\t"+medida.getCampaña().getNombre()+"\n");
          bw.write("Fecha:\t"+medida.getFecha() +"\n");
          bw.write("Hora:\t"+medida.getHora()+"\n\n");
          
          bw.write("Corrección:\tninguna\n"); // Duda, si tiene ¿qué pongo aqui?.
          bw.write("Isc:\t"+String.format("%.4f",medida.getIsc().getValor())+"\t"+medida.getIsc().getMagnitud()+"\n");
          bw.write("Voc:\t"+String.format("%.4f", medida.getVoc().getValor())+"\t"+medida.getVoc().getMagnitud()+"\n");
          bw.write("Pmax:\t"+String.format("%.4f",medida.getPmax().getValor())+"\t"+medida.getPmax().getMagnitud()+"\n");
          bw.write("IPmax:\t"+String.format("%.4f", medida.getImax().getValor())+"\t"+medida.getImax().getMagnitud()+"\n");
          bw.write("VPmax:\t"+String.format("%.4f",medida.getVmax().getValor())+"\t"+medida.getVmax().getMagnitud()+"\n");
          bw.write("FF:\t"+String.format("%.4f", medida.getFF())+"\t%\n");
          for (MedidaCanal medidaCanal : medida.getMedidasCanal())
             bw.write(medidaCanal.getCanal().getNombre()+":\t"+
                      String.format("%.4f", medidaCanal.getValor())+"\t"+medidaCanal.getMagnitud()+"\n");
          bw.write("\n");
          
          List<MedidaCurva> tensiones = medida.getTensiones();
          List<MedidaCurva> intensidades = medida.getIntensidades();
          List<MedidaOrdenada> potencia = medida.getPotencias();

          bw.write("Número de puntos curva IV:\t"+tensiones.size()+"\n");
          bw.write("N\ttensión(V)\tcorriente(A)\tpotencia(W)\n");
         
          for (int i = 0 ; i < tensiones.size() ; i++)
             bw.write(tensiones.get(i).getOrden() + "\t" +
                      String.format("%.4f", tensiones.get(i).getValor()) + "\t" +
                      String.format("%.4f",intensidades.get(i).getValor()) + "\t" +
                      String.format("%.4f",potencia.get(i).getValor()) + "\n");

          
       } catch (IOException ex) {
          System.out.println("da");
       }
       finally {
          
          if (bw != null)
             try {
                bw.close();
             }
             catch (IOException ex) {
                System.out.println("Error");
             }
          
       }
       
    }

    @Override
    public String getExtension() {
        return "xls";
    }

    private void leerCanales(BufferedReader br, CurvaMedida curva) throws IOException {
        String line;
        String parts[];
        
        String magnitud, nombreCanal, valorStr;
        double valor;
        
        Canal canal;
        MedidaCanal medida;
        
        line = readNotEmptyLine(br);
        
        while (!line.contains("mero de puntos curva IV")) {
            parts = line.split(":");
            AlmacenCanales ac=AlmacenCanales.getInstance();
            if (parts.length >= 2) {
                nombreCanal = parts[parts.length-2];
                parts = parts[parts.length-1].split("\t");
                valorStr = parts[parts.length-2].replace(",", ".");
                
                if (!valorStr.isEmpty()) {
                    valor = Double.valueOf(valorStr);
                    magnitud = parts[parts.length-1];
                    
                    canal = ac.buscar(nombreCanal);
                    
                    if (canal == null)
                       canal = ac.nuevo(nombreCanal);
                    
                    try {
                        //medida = new MedidaSensor(valor, magnitud, canal, curva);
                        curva.addMedidaCanal(canal, valor, magnitud);
                    }
                    catch (Error err) {
                        //medida = new MedidaSensor(curva, canal);
                        medida = curva.getMedidaCanal(canal);
                        medida.setValor(valor);
                        medida.setMagnitud(magnitud);
                    } // Si existe no importa.
                    
                }
            }
            
            line = readNotEmptyLine(br);            
        }
 
    }

    private void leerMedidasCurva(BufferedReader br, CurvaMedida curva) throws IOException {
        String line;
        int numero;
        String intensidadStr;
        String tensionStr;
        String parts[];
        
        line = readNotEmptyLine(br); //saltamos la cabecera.
        line = readNotEmptyLine(br);        
        
        while (line != null) {
            parts = line.split("\t");
            
            if (parts.length == 4) {
                numero = Integer.valueOf(parts[0]);
                tensionStr = parts[1].replace(",", ".");
                intensidadStr = parts[2].replace(",", ".");
                try {
                    new MedidaIntensidad(Double.parseDouble(intensidadStr), "A", numero, curva);
                }
                catch (Error err) { // si existe, se actualiza.
                    MedidaIntensidad mi = new MedidaIntensidad(curva, numero);
                    mi.setValor(Double.parseDouble(intensidadStr));
                }
                
                try {
                    new MedidaTension(Double.parseDouble(tensionStr), "V", numero, curva);
                }
                catch (Error err) { // si existe, se actualiza
                    MedidaTension mt = new MedidaTension(curva, numero);
                    mt.setValor(Double.parseDouble(tensionStr));
                }
            }
            line = readNotEmptyLine(br);
        }
        
    }

    private CurvaMedida leerInfoBasicaCurva(BufferedReader br, Campaña campaña) throws IOException {         
        String line, parts[];
        String fecha, hora;
        CurvaMedida curva = null;
        String value, magnitud;
        Medida [] medidas = new Medida[6];

        // Por hacer, leer fechas.
        line = readNotEmptyLine(br);
        fecha = extractValue(line);
        line = readNotEmptyLine(br);
        hora = extractValue(line);
        
        // fin leer fechas.
        line = readNotEmptyLine(br); // Ignoramos corrección.
        line = readNotEmptyLine(br);
        
        for (int i = 0 ; i < 6 ; i++) {
            
            parts = line.split("\t");
            value = parts[1].replace(",", ".");
            magnitud = parts[2];
            medidas[i] = new Medida(Double.valueOf(value), magnitud);
            line = readNotEmptyLine(br);
        }
        
        AlmacenCurvasMedidas almacenMedidas = AlmacenCurvasMedidas.getInstance();
        
        try {
            curva = almacenMedidas.nueva(campaña, fecha, hora, medidas[0],
                    medidas[1], medidas[2], medidas[3],
                    medidas[4], medidas[5].getValor());
        }
        catch (Error ex) {
            sobreescribir = notificar.confirmSobrescribirFormatoFichero(new Object[] {fecha,hora});
            
            if (sobreescribir) {
               almacenMedidas.borrar(fecha, hora);
               curva = almacenMedidas.nueva(campaña, fecha, hora, medidas[0],
                    medidas[1], medidas[2], medidas[3],
                    medidas[4], medidas[5].getValor());
            }
            
        }

        return curva;
    }

    private Campaña leerInfoBasica(BufferedReader br) throws IOException {
        String line;
        Modulo modulo = null;
        Campaña campaña = null;
        String value;
        AlmacenModulos modulos = AlmacenModulos.getInstance();
        AlmacenCampañas campañas = AlmacenCampañas.getInstance();

        line = readNotEmptyLine(br);
        
        if (!line.contains("Módulo"))
            throw new RuntimeException("Error de formato");
        
        value = extractValue(line);
        modulo = modulos.buscar(value);
        
        if (modulo == null)
           modulo = modulos.nuevo(value);

        line = readNotEmptyLine(br);
        value = extractValue(line);
        
        campaña = campañas.buscar(modulo, value);
        
        if (campaña == null)
           campaña = campañas.nueva(modulo, value);
        
        return campaña;
    }

}

