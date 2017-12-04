/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrAdminCampanyas;
import java.io.File;
import java.util.ArrayList;

import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Campaña;
import modelo.FormatoFichero;
import modelo.FormatoFicheroFactory;
import modelo.Importador;

/**
 *
 * @author Sergio
 */
public class JPanelCampaña extends javax.swing.JPanel implements ViewAdminCampanya {
    
    private JFramePrincipal prin;
    private JFileChooser fc;
    
    /**
     * Creates new form JPanelCampaña
     */
    public JPanelCampaña(JFramePrincipal p) {
        initComponents();
        prin=p;
        String ffCmp = Importador.EXTENSION_COMPRIMIDO;
        fc = new JFileChooser();
        fc.setDialogTitle("Selecciona el fichero con el módulo");
        FormatoFichero ff = (new FormatoFicheroFactory()).create(FormatoFicheroFactory.FORMATO_CAMPAÑA, null);
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

      jLabel1 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      jComboBox1 = new javax.swing.JComboBox<>();
      jImportarC = new javax.swing.JButton();
      jExportarC = new javax.swing.JButton();
      jBorrarC = new javax.swing.JButton();
      jMedidas = new javax.swing.JButton();
      jModulo = new javax.swing.JButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      jListC = new javax.swing.JList<>();

      jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
      jLabel1.setText("CAMPAÑAS");

      jLabel2.setText("Idioma:");

      jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

      jImportarC.setText("Importar");
      jImportarC.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jImportarCActionPerformed(evt);
         }
      });

      jExportarC.setText("Exportar");
      jExportarC.setEnabled(false);

      jBorrarC.setText("Borrar");
      jBorrarC.setEnabled(false);

      jMedidas.setText("Medidas");
      jMedidas.setEnabled(false);

      jModulo.setText("Módulos");

      jListC.setModel(new ListModelCampanya());
      jScrollPane1.setViewportView(jListC);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jLabel1)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(jLabel2)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jExportarC, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jImportarC, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addComponent(jBorrarC, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jMedidas, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addGap(18, 18, 18)
                  .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel1)
               .addComponent(jLabel2)
               .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(28, 28, 28)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jImportarC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jExportarC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jBorrarC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jMedidas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(64, Short.MAX_VALUE))
      );
   }// </editor-fold>//GEN-END:initComponents

    private void jImportarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jImportarCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jImportarCActionPerformed


   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jBorrarC;
   private javax.swing.JComboBox<String> jComboBox1;
   private javax.swing.JButton jExportarC;
   private javax.swing.JButton jImportarC;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JList<String> jListC;
   private javax.swing.JButton jMedidas;
   private javax.swing.JButton jModulo;
   private javax.swing.JScrollPane jScrollPane1;
   // End of variables declaration//GEN-END:variables

    

    @Override
    public void setControlador(CtrAdminCampanyas controlador) {
        
        
        jBorrarC.addActionListener(controlador);
        jBorrarC.setActionCommand(ViewAdminCampanya.BORRAR);
        
        jExportarC.addActionListener(controlador);
        jExportarC.setActionCommand(ViewAdminCampanya.EXPORTAR);
        
        jImportarC.addActionListener(controlador);
        jImportarC.setActionCommand(ViewAdminCampanya.IMPORTAR);
        
        jMedidas.addActionListener(controlador);
        jMedidas.setActionCommand(ViewAdminCampanya.MEDIDAS);
        
        jModulo.addActionListener(controlador);
        jModulo.setActionCommand(ViewAdminCampanya.MODULO);
        
        jListC.addListSelectionListener(controlador);
        
       // ctr.consultarCampanyas();
    }

    @Override
    public void habilitarBorrado(boolean habilitar) {
        jBorrarC.setEnabled(habilitar);
    }

    @Override
    public void habilitarExportacion(boolean habilitar) {
        jExportarC.setEnabled(habilitar);
    }

    @Override
    public void habilitarVerMedidas(boolean habilitar) {
       jMedidas.setEnabled(habilitar);
    }

    @Override
    public void mostrarCampanyas(List<Campaña> campanyas) {
        ListModelCampanya modelo=(ListModelCampanya) jListC.getModel();
        
        modelo.setList(campanyas);
        
        jListC.updateUI();
    }

    @Override
    public void mostrarModuloNuevo(Campaña m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void alert(String message) {
        JOptionPane.showMessageDialog(this, message, "Ups", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public boolean preguntar(String string) {
        int returnVal = JOptionPane.showConfirmDialog(this, string, "Sobreescribir módulo", JOptionPane.YES_NO_OPTION);
        
        return returnVal == JOptionPane.YES_OPTION;
    }

    @Override
    public void vistaAnterior() {
       prin.anteriorPanel();
    }

    @Override
    public void informar(String msg) {
        JOptionPane.showMessageDialog(this, msg, "", JOptionPane.INFORMATION_MESSAGE);
    }
    public Campaña getCampañaSeleccionada() {
        ListModelCampanya model= (ListModelCampanya) jListC.getModel();
        return model.getCampaña(jListC.getSelectedIndex());
    }

    @Override
    public void siguienteVista() {
        prin.siguientePanel();
    }

}
