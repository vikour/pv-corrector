/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class FormatoFicheroFactory {
    
    public static final String FORMATO_MODULO = "FORMATO1";
    public static final String FORMATO_CAMPAÑA = "FORMATO2";
    
    public FormatoFichero create(String formato) {
        FormatoFichero ff = null;
        
        switch (formato) {
            case FORMATO_CAMPAÑA:
                ff = new FormatoCampaña();
                break;
                
            case FORMATO_MODULO:
                ff = new FormatoModulo();
                break;
                
        }
        
        return ff;
    }
    
}
