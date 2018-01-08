/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.CurvaCorregida;
import modelo.CurvaMedida;
import modelo.FormatoFichero;
import modelo.FormatoFicheroFactory;
import vista.ViewAdminCorreccionesMedida;


public class CtrAdminCorrecionesMedida implements ActionListener, ListSelectionListener {
    
    private CtrAdminMedidas ctrAnterior;
    private ViewAdminCorreccionesMedida view;

    public CtrAdminCorrecionesMedida(ViewAdminCorreccionesMedida view) {
        this.ctrAnterior = null;
        this.view = view;
        view.setControlador(this);
    }

    public void setCtrAnterior(CtrAdminMedidas ctrAnterior) {
        this.ctrAnterior = ctrAnterior;
    }
    
    public void mostrarCorrecciones(CurvaMedida curva) {
        CurvaCorregida [] correcciones = curva.getCorrecciones();
        
        view.mostrar(correcciones);
    }

    private void medidaSeleccionada() {
        view.habilitarExportar(true);
        view.habilitarGrafica(true);
        view.habilitarBorrar(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals(ViewAdminCorreccionesMedida.Cmd.MEDIDAS.toString()))
            mostrarMedidas();
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        if (!e.getValueIsAdjusting())
            medidaSeleccionada();
        
    }

    private void mostrarMedidas() {
        view.vistaAnterior();
    }
    
}
