/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.correccion;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.ConfiguracionCorreccion;
import modelo.CurvaMedida;
import modelo.FabricaMetodosCorreccion;
import modelo.MetodoCorreccion;
import modelo.NombreMetodoCorreccion;

/**
 *
 * @author Vikour
 */
public class JDialogConfiguracionCorreccion extends javax.swing.JDialog implements WindowListener{

   private CurvaMedida curva;
   private Map<NombreMetodoCorreccion,JPanelConfigCorreccion> panelCorreccion;
   private JPanelConfigCorreccion panelMostrado;
   private boolean configuracionIntroducida;
   
   public JDialogConfiguracionCorreccion(java.awt.Frame parent, boolean modal, CurvaMedida curva) {
      super(parent,modal);
      initComponents();
      this.curva = curva;
      configuracionIntroducida = true;
      
      JPanelCorreccionMetodo1 panelMetodo1 = new JPanelCorreccionMetodo1();
      JPanelCorreccionMetodo2 panelMetodo2 = new JPanelCorreccionMetodo2();
      
      panelMetodo2.setCurva(curva);
      panelMetodo1.setCurva(curva);
      
      panelCorreccion = new HashMap<>();
      panelCorreccion.put(NombreMetodoCorreccion.METODO_1_IEC, panelMetodo1);
      panelCorreccion.put(NombreMetodoCorreccion.METODO_2_IEC, panelMetodo2);
      
      jPanelConfigCorreccion.setLayout(new BorderLayout());
      
      jComboBox1.removeAllItems();
      
      jComboBox1.addItem("IEC Método 1");
      jComboBox1.addItem("IEC Método 2");
      
      this.addWindowListener(this);
   }
   
   public List<ConfiguracionCorreccion> getConfiguracionesCorreccion() {
      List<ConfiguracionCorreccion> result = null;
      
      if (configuracionIntroducida)
         result = panelMostrado.getConfiguracionCorreccion();
      
      return result;
   }
   
   public MetodoCorreccion getMetodoCorreccion() {
      MetodoCorreccion result = null;
      
      if (configuracionIntroducida)
         result = FabricaMetodosCorreccion.getInstance().create(NombreMetodoCorreccion.values()[jComboBox1.getSelectedIndex()]);
      
      return  result;
   }

   /**
    * This method is called from within the constructor to initialize the form. WARNING: Do NOT
    * modify this code. The content of this method is always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanelConfigCorreccion = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setFocusable(false);

        jLabel1.setText("Método : ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelConfigCorreccionLayout = new javax.swing.GroupLayout(jPanelConfigCorreccion);
        jPanelConfigCorreccion.setLayout(jPanelConfigCorreccionLayout);
        jPanelConfigCorreccionLayout.setHorizontalGroup(
            jPanelConfigCorreccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanelConfigCorreccionLayout.setVerticalGroup(
            jPanelConfigCorreccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        jButton1.setText("Corregir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelConfigCorreccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelConfigCorreccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
      int selectedIndex = jComboBox1.getSelectedIndex();
      
      if (selectedIndex >= 0) {
         NombreMetodoCorreccion metodo = NombreMetodoCorreccion.values()[selectedIndex];
         jPanelConfigCorreccion.removeAll();
         panelMostrado = panelCorreccion.get(metodo);
         jPanelConfigCorreccion.add(panelMostrado);
         jPanelConfigCorreccion.updateUI();
      }
   }//GEN-LAST:event_jComboBox1ActionPerformed

   private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
      try {
         getConfiguracionesCorreccion();
         dispose();
      }
      catch (NumberFormatException ex) {
         JOptionPane.showMessageDialog(rootPane, "Se esperaban parámetros numéricos");
      }
   }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelConfigCorreccion;
    // End of variables declaration//GEN-END:variables

   @Override
   public void windowOpened(WindowEvent e) {
      configuracionIntroducida = true;
   }

   @Override
   public void windowClosing(WindowEvent e) {
      configuracionIntroducida = false;
      System.out.println("Cerrada");
   }

   @Override
   public void windowClosed(WindowEvent e) {
      configuracionIntroducida = false;
      System.out.println("Cerrada");
   }

   @Override
   public void windowIconified(WindowEvent e) {
      // Do nothing
   }

   @Override
   public void windowDeiconified(WindowEvent e) {
      // Do nothing
   }

   @Override
   public void windowActivated(WindowEvent e) {
      // Do nothing
   }

   @Override
   public void windowDeactivated(WindowEvent e) {
      // Do nothing
   }
}
