/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.CurvaCorregida;
import modelo.CurvaMedida;
import modelo.ExportadorMedidas;
import modelo.FormatoFichero;
import modelo.FormatoFicheroFactory;
import vista.ViewAdminCorreccionesMedida;


public class CtrAdminCorrecionesMedida implements ActionListener, ListSelectionListener,MouseListener {
    
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
        else if (e.getActionCommand().equals(ViewAdminCorreccionesMedida.Cmd.EXPORTAR.toString()))
            exportarMedida();
        else if(e.getActionCommand().equals(ViewAdminCorreccionesMedida.Cmd.GRAFICA.toString()))
            mostrarGraficaSeleccionada();
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        if (!e.getValueIsAdjusting())
            medidaSeleccionada();
        
    }

    private void mostrarMedidas() {
        view.vistaAnterior();
    }

    private void exportarMedida() {
      File f = view.mostrarSelectorFicheroNuevo();
      FormatoFicheroFactory fff = new FormatoFicheroFactory();
      ExportadorMedidas exp = null;
      
      if (f != null) {
         exp = new ExportadorMedidas(fff.create(FormatoFicheroFactory.FORMATO_CAMPAÑA), f);
         exp.exportar(view.getCurvaSeleccionada());
         view.mostrarMensajeSuccess("Medida exportada satisfactoriamente.");
      }
    }

    private void mostrarGraficaSeleccionada() {
        CurvaCorregida cc= view.getCurvaSeleccionada();
        if(cc == null) throw new RuntimeException ("No hay curva seleccionada");
        else view.showCurva(cc);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2) {
            mostrarGraficaSeleccionada();
        }
            
            
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Do nothing
    }
    
}
