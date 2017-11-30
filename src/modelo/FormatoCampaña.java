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
        
        
        try {
            bd.execute("BEGIN TRANSACTION");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-15"));
            
            campaña = leerInfoBasica(br);
            leerInfoBasicaCurva(br, campaña);
            leerCanales(br);
            leerMedidasCurva(br);
        
            bd.execute("COMMIT");
        } 
        catch (PatternSyntaxException | NumberFormatException ex) {
            bd.execute("ROLLBACK");
            notificar.alertFormatoFichero("Formato del fichero incorrecto");
        }
        catch (IOException ex) {
            bd.execute("ROLLBACK");
            ex.printStackTrace();
        }
        
        return campaña;
    }

    private String readNotEmptyLine(BufferedReader br) throws IOException {
        String aux = null;
        
        do {
            aux = br.readLine();
        } while (aux.isEmpty());
        
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

    private void leerCanales(BufferedReader br) throws IOException {
        String line;
        String parts[];
        
        String magnitud, nombreCanal, valorStr;
        double valor;
        
        line = readNotEmptyLine(br);
        
        while (!line.contains("Número de puntos curva IV")) {
            parts = line.split(":");
            nombreCanal = parts[parts.length-2];
            parts = parts[parts.length-1].split("\t");
            valorStr = parts[parts.length-2].replace(",", ".");
            
            if (!valorStr.isEmpty()) {
                valor = Double.valueOf(valorStr);
                magnitud = parts[parts.length-1];
                // Insertar canal.
            }
            
            line = readNotEmptyLine(br);            
        }
 
    }

    private void leerMedidasCurva(BufferedReader br) throws IOException {
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
            line = readNotEmptyLine(br);
        }
        
    }

    private void leerInfoBasicaCurva(BufferedReader br, Campaña campaña) throws IOException {         
        String line;

        // Por hacer, leer fechas.
        line = readNotEmptyLine(br);
        line = readNotEmptyLine(br);
        // fin leer fechas.
        line = readNotEmptyLine(br); // Ignoramos correcciÃ³n.
        
        for (int i = 0 ; i < 6 ; i++)
            line = readNotEmptyLine(br);
        
    }

    private Campaña leerInfoBasica(BufferedReader br) throws IOException {
        String line;
        Modulo modulo = null;
        Campaña Campaña = null;

        line = readNotEmptyLine(br);

        try { // Modulo.
            modulo = new Modulo(extractValue(line));
        } catch (Error ex) {
            modulo = new Modulo(extractValue(line),"");
        }

        try { // CampaÃ±a.
            line = readNotEmptyLine(br);
            Campaña = new Campaña(modulo, extractValue(line), false);
        } catch (Error err) {
            Campaña = new Campaña(modulo, extractValue(line), true);
        } // Si existe la campaÃ±a no hacemos nada.    

        return Campaña;
    }

}

