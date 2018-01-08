/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import modelo.Campaña;
import modelo.CurvaMedida;
import vista.ViewAdminMedidas;
import vista.curvamedida.jFrameCurvas;

/**
 *
 * @author EzequielRodriguez
 */
public class CtrAdminMedidas implements ActionListener, ListSelectionListener{
    
    private ViewAdminMedidas vm;
    private CtrAdminCampanyas ctra;
    private CtrAdminGrafica ctrcm;
    private CurvaMedida cm;
    public CtrAdminMedidas(ViewAdminMedidas vm) {
        this.vm = vm;
        vm.setControlador(this);
        ctra=null;
        ctrcm=null;
       
    }

    public void setCtrAnterior(CtrAdminCampanyas ctra) {
        this.ctra = ctra;
    }
    
    public void setCtrSiguiente(CtrAdminGrafica c){
        ctrcm=c;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case ViewAdminMedidas.CAMPAÑA:
                vistaAnterior();
                break;
            
            case ViewAdminMedidas.SELECC_MEDIDA: //ESTO ESTA MAL
                //System.out.println("HE CC_MEDIDA: //ESTO ESTA MAL
                //System.out.println("SELECCIONADO UNA CURVA");
                //medidaSeleccionada();
                //System.out.println("HE PASADO POR MEDIDA SELECCIOADA");
                //cm=vm.getCurva();
                //System.out.println(cm);
                break;
            
            case ViewAdminMedidas.GRAFICA:
                if(cm==null){
                    cm=vm.getCurva();
                    System.out.println("La curva era null");
                }
                System.out.println(cm);
                verGrafica(cm);
                break;
        }
    }

    public void setMedidas(Campaña c) {
        List<CurvaMedida> model=c.getCurvas();
        
        vm.mostrarCurvas(model);
    }
    
    public void vistaAnterior(){
        vm.vistaAnterior();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            medidaSeleccionada();
            cm=vm.getCurva();
        }
    }
    
    public void medidaSeleccionada(){
        
        vm.habilitarBorrar(true);
        vm.habilitarExportar(true);
        vm.habilitarGrafica(true);
    }
    
    public void verGrafica(CurvaMedida c){
        
        ctrcm.showCurva(c);
        
        
    }
    
    
    
}
