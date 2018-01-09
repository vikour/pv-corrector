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
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.AlmacenCurvasCorregidas;
import javax.swing.table.AbstractTableModel;
import modelo.Campaña;
import modelo.ConfiguracionCorreccion;
import modelo.CurvaMedida;
import modelo.ExportadorMedidas;
import modelo.FormatoFichero;
import modelo.FormatoFicheroFactory;
import modelo.MetodoCorreccion;
import vista.ViewAdminMedidas;
import vista.Grafica.jFrameGrafica;

/**
 *
 * @author EzequielRodriguez
 */
public class CtrAdminMedidas implements ActionListener, ListSelectionListener, MouseListener {

    private ViewAdminMedidas vm;
    private CtrAdminCampanyas ctra;

    private CurvaMedida cm;
    private CtrAdminCorrecionesMedida ctrs;

    public CtrAdminMedidas(ViewAdminMedidas vm) {
        this.vm = vm;
        vm.setControlador(this);
        ctra = null;

    }

    public void setCtrAnterior(CtrAdminCampanyas ctra) {
        this.ctra = ctra;
    }

    public void setCtrSiguiente(CtrAdminCorrecionesMedida ctrs) {
        this.ctrs = ctrs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
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
                mostrarGraficaCurvaSeleccionada();
                break;

            case ViewAdminMedidas.CORREGIR:
                corregirCurva();
                break;

            case ViewAdminMedidas.EXPORTAR:
                exportarCurva();
                break;

            case ViewAdminMedidas.CORRECCIONES:
                mostrarCorrecciones();
                break;

        }
    }

    private void mostrarGraficaCurvaSeleccionada() {
        if (cm == null) {
            cm = vm.getMedidaSeleccionada();
            System.out.println("La curva era null");
        }
        System.out.println(cm);
        vm.showCurva(cm);
    }

    public void setMedidas(Campaña c) {
        CurvaMedida[] model = c.getCurvas();

        vm.mostrarCurvas(model);
    }

    public void vistaAnterior() {
        vm.vistaAnterior();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            medidaSeleccionada();
            cm = vm.getMedidaSeleccionada();
        }
    }

    public void medidaSeleccionada() {

        vm.habilitarBorrar(true);
        vm.habilitarExportar(true);
        vm.habilitarGrafica(true);
        vm.habilitarCorregir(true);
        vm.habilitarCorrecciones(true);
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
                vm.actualizarTabla();
            } catch (RuntimeException ex) {
                vm.error(ex.getMessage());
            }
        }
    }

    private void mostrarCorrecciones() {
        ctrs.mostrarCorrecciones(vm.getMedidaSeleccionada());
        vm.vistaSiguiente();
    }

    private void exportarCurva() {
        File f = vm.mostrarSelectorFicheroNuevo();
        FormatoFicheroFactory fff = new FormatoFicheroFactory();
        ExportadorMedidas exp = null;

        if (f != null) {
            exp = new ExportadorMedidas(fff.create(FormatoFicheroFactory.FORMATO_CAMPAÑA), f);
            exp.exportar(vm.getMedidaSeleccionada());
            vm.mostrarMensajeSuccess("Medida exportada satisfactoriamente.");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2) {
            mostrarGraficaCurvaSeleccionada();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Do nothing
    }

}
