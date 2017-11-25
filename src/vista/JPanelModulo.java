/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrAdminModulos;
import java.awt.Dimension;
import java.util.List;
import modelo.Modulo;

/**
 *
 * @author Sergio
 */
public class JPanelModulo extends javax.swing.JPanel implements ViewAdminModulos {

    /**
     * Creates new form JPanelModulo
     */
    public JPanelModulo() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonImportar = new javax.swing.JButton();
        jButtonExportar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonBorrar = new javax.swing.JButton();
        jButtonCampanyas = new javax.swing.JButton();
        jScrollPaneListaModulos = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jComboBoxIdioma = new javax.swing.JComboBox<>();
        jLabelIdioma = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("MÓDULOS");

        jButtonImportar.setText("Importar");

        jButtonExportar.setText("Exportar");
        jButtonExportar.setEnabled(false);

        jButtonModificar.setText("Modiificar");
        jButtonModificar.setEnabled(false);

        jButtonBorrar.setText("Borrar");
        jButtonBorrar.setEnabled(false);

        jButtonCampanyas.setText("Campañas");
        jButtonCampanyas.setEnabled(false);

        jList1.setModel(new ListModelModulo());
        jScrollPaneListaModulos.setViewportView(jList1);

        jComboBoxIdioma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelIdioma.setText("Idioma:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCampanyas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonImportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneListaModulos, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelIdioma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIdioma))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonImportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonExportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonBorrar)
                        .addGap(11, 11, 11)
                        .addComponent(jButtonCampanyas))
                    .addComponent(jScrollPaneListaModulos))
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonCampanyas;
    private javax.swing.JButton jButtonExportar;
    private javax.swing.JButton jButtonImportar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JComboBox<String> jComboBoxIdioma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelIdioma;
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
    
}
