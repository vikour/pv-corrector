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
import modelo.AlmacenCurvasCorregidas;
import modelo.Campaña;
import modelo.ConfiguracionCorreccion;
import modelo.CurvaMedida;
import modelo.MetodoCorreccion;
import vista.ViewAdminMedidas;

/**
 *
 * @author EzequielRodriguez
 */
public class CtrAdminMedidas implements ActionListener, ListSelectionListener{
    
    private ViewAdminMedidas vm;
    private CtrAdminCampanyas ctra;
   
    public CtrAdminMedidas(ViewAdminMedidas vm) {
        this.vm = vm;
        vm.setControlador(this);
        ctra=null;
       
       
    }

    public void setCtrAnterior(CtrAdminCampanyas ctra) {
        this.ctra = ctra;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case ViewAdminMedidas.CAMPAÑA:
                vistaAnterior();
                break;
            
            case ViewAdminMedidas.SELECC_MEDIDA:
                medidaSeleccionada();
                break;
                
            case ViewAdminMedidas.CORREGIR:
               corregirCurva();
               break;
            
        }
    }

    public void setMedidas(Campaña c) {
        CurvaMedida [] model=c.getCurvas();
        
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
        vm.habilitarBorrar(true);
        vm.habilitarExportar(true);
        vm.habilitarGrafica(true);
        vm.habilitarCorregir(true);
    }

   private void corregirCurva() {
      CurvaMedida seleccionada = vm.getMedidaSeleccionada();
      List<ConfiguracionCorreccion> config;
      MetodoCorreccion metodo;
      
      vm.mostrarVistaCorreccion(seleccionada);
      config = vm.getConfiguracionCorreccion();
      metodo = vm.getMetodoCorreccion();
      
      if (config != null && metodo != null) {
         
         try {
            AlmacenCurvasCorregidas.getInstance().nueva(metodo, config, seleccionada);
         }
         catch (RuntimeException ex) {
            vm.error("El modulo de la curva no tiene los parámetros necesarios para la corrección");
         }
      }
   }
   
}
