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

/**
 *
 * @author EzequielRodriguez
 */
public class CtrAdminMedidas implements ActionListener, ListSelectionListener{
    
    private ViewAdminMedidas vm;
    private CtrAdminCampanyas ctra;
    private CtrAdminCurvaMedida ctrcm;
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
    
    public void setCtrSiguiente(CtrAdminCurvaMedida c){
        ctrcm=c;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case ViewAdminMedidas.CAMPAÑA:
                vistaAnterior();
                break;
            
            case ViewAdminMedidas.SELECC_MEDIDA:
                medidaSeleccionada();
                //cm=vm.getCurva();
                System.out.println(cm);
                break;
            
            case ViewAdminMedidas.GRAFICA:
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
        }
    }
    
    public void medidaSeleccionada(){
        //System.out.println("HOLA");
        vm.habilitarBorrar(true);
        vm.habilitarExportar(true);
        vm.habilitarGrafica(true);
    }
    
    public void verGrafica(CurvaMedida c){
        ctrcm.showCurva(c);
        
        
    }
    
    
    
}
