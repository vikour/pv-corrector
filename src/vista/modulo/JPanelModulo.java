/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.modulo;

import controlador.CtrAdminModulos;
import controlador.CtrImportacion;
import java.io.File;
import java.io.FileFilter;
import java.util.List;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.FormatoFichero;
import modelo.FormatoFicheroFactory;
import modelo.Importador;
import modelo.Modulo;
import vista.JDialogProcessImport;

import vista.JFramePrincipal;
import vista.ViewAdminModulos;

/**
 *
 * @author Sergio
 */
public class JPanelModulo extends javax.swing.JPanel implements ViewAdminModulos {

    
    
    private JFileChooser fc;
    private JFramePrincipal prin;
    /**
     * Creates new form JPanelModulo
     */
    public JPanelModulo(JFramePrincipal vent) {
        initComponents();
        prin=vent;
        fc = new JFileChooser();
        fc.setDialogTitle("Selecciona el fichero con el módulo");
        String ffCmp = Importador.EXTENSION_COMPRIMIDO;
        FormatoFichero ff = (new FormatoFicheroFactory()).create(FormatoFicheroFactory.FORMATO_MODULO);
        String filterName = "Módulos (." + ffCmp +", " + ff.getExtension() + ")";
        fc.setFileFilter(new FileNameExtensionFilter(filterName, ffCmp, ff.getExtension()));
        fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonImportar = new javax.swing.JButton();
        jButtonExportar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonBorrar = new javax.swing.JButton();
        jButtonCampanyas = new javax.swing.JButton();
        jScrollPaneListaModulos = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        jButtonImportar.setText("Importar");

        jButtonExportar.setText("Exportar");
        jButtonExportar.setEnabled(false);

        jButtonModificar.setText("Modificar");
        jButtonModificar.setEnabled(false);

        jButtonBorrar.setText("Borrar");
        jButtonBorrar.setEnabled(false);

        jButtonCampanyas.setText("Campañas");
        jButtonCampanyas.setEnabled(false);

        jList1.setModel(new ListModelModulo());
        jScrollPaneListaModulos.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCampanyas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneListaModulos, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonBorrar, jButtonCampanyas, jButtonExportar, jButtonImportar, jButtonModificar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneListaModulos)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonImportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBorrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCampanyas)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonBorrar, jButtonCampanyas, jButtonExportar, jButtonImportar, jButtonModificar});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonCampanyas;
    private javax.swing.JButton jButtonExportar;
    private javax.swing.JButton jButtonImportar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPaneListaModulos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrar(List<Modulo> modulos) {
        ListModelModulo model=(ListModelModulo) jList1.getModel();
        
        model.setList(modulos);
        jList1.updateUI();
    }

    @Override
    public void setControlador(CtrAdminModulos controlador) {
        
        
        jButtonBorrar.addActionListener(controlador);
        jButtonBorrar.setActionCommand(ViewAdminModulos.BORRAR);
        
        jButtonCampanyas.addActionListener(controlador);
        jButtonCampanyas.setActionCommand(ViewAdminModulos.CAMPANYAS);
        
        jButtonExportar.addActionListener(controlador);
        jButtonExportar.setActionCommand(ViewAdminModulos.EXPORTAR);
        
        jButtonImportar.addActionListener(controlador);
        jButtonImportar.setActionCommand(ViewAdminModulos.IMPORTAR);
        
        jButtonModificar.addActionListener(controlador);
        jButtonModificar.setActionCommand(ViewAdminModulos.MODIFICAR);
        
        jList1.addListSelectionListener(controlador);
        
        controlador.consultarModulos();
    }
    
   

    @Override
    public void habilitarModificacion(boolean habilitar) {
        jButtonModificar.setEnabled(habilitar);
    }

    @Override
    public void habilitarBorrado(boolean habilitar) {
        jButtonBorrar.setEnabled(habilitar);
    }

    @Override
    public void habilitarExportacion(boolean habilitar) {
        jButtonExportar.setEnabled(habilitar);
    }

    @Override
    public void habilitarVerCampañas(boolean habilitar) {
        jButtonCampanyas.setEnabled(habilitar);
    }

    @Override
    public void mostrarModuloNuevo(Modulo m) {
        ListModelModulo model=(ListModelModulo) jList1.getModel();
        
        model.add(m);
        jList1.updateUI();
    }

    @Override
    public File mostrarSelectorFicheros() {
        int returnVal = fc.showOpenDialog(this);
        File f = null;
        
        if (returnVal == JFileChooser.APPROVE_OPTION)
            f = fc.getSelectedFile();
        
        return f;
    }

    @Override
    public Modulo getModulosSeleccionados(){
        ListModelModulo model= (ListModelModulo) jList1.getModel();
        
        
        return model.getModuloAt(jList1.getSelectedIndex());
        
    }

    @Override
    public void siguienteVista() {
        
        prin.siguientePanel();
    }

    @Override
    public void mostrarVistaImportacion(String format, File f) {
        JDialogProcessImport dImport = new JDialogProcessImport(prin, true);
        CtrImportacion ctrImportacion = new CtrImportacion(dImport);
        dImport.setVisible(true, format, f);
    }
   
   
    
}