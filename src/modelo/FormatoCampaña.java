/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FormatoCampaña extends FormatoFichero{

    public FormatoCampaña(IFormatoFicheroNotificable notificar) {
        super(notificar);
    }

    @Override
    public Object leer(File file) {
        BufferedReader br = null;
        BD bd = BD.getInstance();
        Campaña campaña = null;
        CurvaMedida curva = null;
        
        
        try {
            //bd.execute("BEGIN TRANSACTION");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-15"));
            
            campaña = leerInfoBasica(br);
            curva = leerInfoBasicaCurva(br, campaña);
            leerCanales(br, curva);
            leerMedidasCurva(br, curva);
        
            //bd.execute("COMMIT");
        } 
        catch (PatternSyntaxException | NumberFormatException ex) {
            //bd.execute("ROLLBACK");
            notificar.alertFormatoFichero("Formato del fichero incorrecto");
        }
        catch (IOException ex) {
            //bd.execute("ROLLBACK");
            ex.printStackTrace();
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
    public void escribir(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        MedidaSensor medida;
        
        line = readNotEmptyLine(br);
        
        while (!line.contains("Número de puntos curva IV")) {
            parts = line.split(":");
            nombreCanal = parts[parts.length-2];
            parts = parts[parts.length-1].split("\t");
            valorStr = parts[parts.length-2].replace(",", ".");
            
            if (!valorStr.isEmpty()) {
                valor = Double.valueOf(valorStr);
                magnitud = parts[parts.length-1];
                
                try {
                    canal = new Canal(nombreCanal, false);
                }
                catch (Error err) {
                    canal = new Canal(nombreCanal, true);
                }
                
                medida = new MedidaSensor(valor, magnitud, canal, curva);

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
            numero = Integer.valueOf(parts[0]);
            tensionStr = parts[1].replace(",", ".");
            intensidadStr = parts[2].replace(",", ".");
            new MedidaIntensidad(Double.parseDouble(intensidadStr), "A", numero, curva);
            new MedidaTension(Double.parseDouble(tensionStr), "V", numero, curva);
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
        
        curva = new CurvaMedida(campaña, fecha, hora, medidas[0], 
                                medidas[1], medidas[2], medidas[3],
                                medidas[4], medidas[5].getValor());

        return curva;
    }

    private Campaña leerInfoBasica(BufferedReader br) throws IOException {
        String line;
        Modulo modulo = null;
        Campaña Campaña = null;
        String value;

        line = readNotEmptyLine(br);
        value = extractValue(line);
        try { // Modulo.
            modulo = new Modulo(value);
        } catch (Error ex) {
            modulo = new Modulo(value,"");
        }

        line = readNotEmptyLine(br);
        value = extractValue(line);
        try { // CampaÃ±a.
            Campaña = new Campaña(modulo, value, false);
        } catch (Error err) {
            Campaña = new Campaña(modulo, value, true);
        } // Si existe la campaÃ±a no hacemos nada.    

        return Campaña;
    }

}

