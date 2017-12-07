/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.medidas;

import controlador.CtrAdminMedidas;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import modelo.CurvaMedida;
import vista.JFramePrincipal;

import vista.ViewAdminMedidas;

/**
 *
 * @author EzequielRodriguez
 */
public class JPanelCurvas extends javax.swing.JPanel implements ViewAdminMedidas {
    private JFramePrincipal prin;

    /**
     * Creates new form JPanelCurvas
     */
    public JPanelCurvas(JFramePrincipal p) {
        //jTable1.setRowSelectionAllowed(true);
        initComponents();
        prin=p;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox2 = new javax.swing.JComboBox<>();
        Exportar = new javax.swing.JButton();
        Borrar = new javax.swing.JButton();
        Grafica = new javax.swing.JButton();
        Campañas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jTable1 = new javax.swing.JTable();

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Exportar.setText("Exportar");
        Exportar.setEnabled(false);

        Borrar.setText("Borrar");
        Borrar.setEnabled(false);

        Grafica.setText("Grafica");
        Grafica.setEnabled(false);

        Campañas.setText("Campañas");
        Campañas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampañasActionPerformed(evt);
            }
        });

        jTable1.setModel(new TableModelMedidas());
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Grafica, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campañas))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Exportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Borrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Grafica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Campañas)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CampañasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampañasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampañasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Borrar;
    private javax.swing.JButton Campañas;
    private javax.swing.JButton Exportar;
    private javax.swing.JButton Grafica;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    public void setControlador(CtrAdminMedidas controlador) {
        
        
        Borrar.addActionListener(controlador);
        Borrar.setActionCommand(ViewAdminMedidas.BORRAR);
        
        Exportar.addActionListener(controlador);
        Exportar.setActionCommand(ViewAdminMedidas.EXPORTAR);
        
        
        
        Campañas.addActionListener(controlador);
        Campañas.setActionCommand(ViewAdminMedidas.CAMPAÑA);
        
       
        
        jTable1.getSelectionModel().addListSelectionListener(controlador);
        
       // ctr.consultarCampanyas();
    }

    @Override
    public void mostrarCurvas(List<CurvaMedida> curva) {
        TableModelMedidas model = new TableModelMedidas(curva);
        jTable1.setModel(model);
        
        for (int i = 0 ; i < model.getColumnCount() ; i++)
            jTable1.getColumn(model.getColumnName(i)).setMinWidth(100);
        
        jTable1.updateUI();
    }

    @Override
    public void vistaAnterior() {
        prin.anteriorPanel();
    }

    @Override
    public void habilitarBorrar(boolean habilitar) {
        Borrar.setEnabled(habilitar);
    }

    @Override
    public void habilitarGrafica(boolean habilitar) {
        Grafica.setEnabled(habilitar);
    }

    @Override
    public void habilitarExportar(boolean habilitar) {
        Exportar.setEnabled(habilitar);
    }


}