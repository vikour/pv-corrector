
import java.util.List;
import modelo.Modulo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elias
 */
public class TestModulo {
    public static void main(String []args){
       List<Modulo> mod=Modulo.listar();
       
       for(Modulo m:mod){
           System.out.println(m);
           System.out.println("");
       }
    
    }
}
