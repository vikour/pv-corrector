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
        MedidaCanal medida;
        
        line = readNotEmptyLine(br);
        
        while (!line.contains("mero de puntos curva IV")) {
            parts = line.split(":");
            AlmacenCanales ac=AlmacenCanales.getInstance();
            AlmacenMedidasCanal amc= new AlmacenMedidasCanal(curva);
            if (parts.length >= 2) {
                nombreCanal = parts[parts.length-2];
                parts = parts[parts.length-1].split("\t");
                valorStr = parts[parts.length-2].replace(",", ".");
                
                if (!valorStr.isEmpty()) {
                    valor = Double.valueOf(valorStr);
                    magnitud = parts[parts.length-1];
                    
                    try {
                        //canal = new Canal(nombreCanal, false);
                        canal=ac.buscar(nombreCanal);
                    }
                    catch (Error err) {
                        //canal = new Canal(nombreCanal, true);
                        canal = ac.nuevo(nombreCanal);
                    }
                    
                    try {
                        //medida = new MedidaSensor(valor, magnitud, canal, curva);
                        medida=amc.nuevo(canal, valor, magnitud);
                    }
                    catch (Error err) {
                        //medida = new MedidaSensor(curva, canal);
                        medida=amc.buscar(canal);
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
        
        try {
            curva = new CurvaMedida(campaña, fecha, hora, medidas[0],
                    medidas[1], medidas[2], medidas[3],
                    medidas[4], medidas[5].getValor());
        }
        catch (Error ex) {
            sobreescribir = notificar.confirmSobrescribirFormatoFichero(new Object[] {fecha,hora});
            
            if (sobreescribir) {
                CurvaIV.borrar(fecha, hora);
                curva = new CurvaMedida(campaña, fecha, hora, medidas[0],
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
        modulos.nuevo(value);

        line = readNotEmptyLine(br);
        value = extractValue(line);
        
        /*
        try { // CampaÃ±a.
            //campaña = new Campaña(modulo, value, false);
        } catch (Error err) {
            //campaña = new Campaña(modulo, value, true);
        } // Si existe la campaÃ±a no hacemos nada.    
        */
        
        try {
            campaña = campañas.nueva(modulo, value);
        }
        catch (Error err) {
            campaña = campañas.buscar(modulo, value);
        }

        
        return campaña;
    }

}

