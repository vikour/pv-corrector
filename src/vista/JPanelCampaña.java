/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrAdminCampanyas;
import java.io.File;
import java.util.List;
import modelo.Campaña;

/**
 *
 * @author Sergio
 */
public class JPanelCampaña extends javax.swing.JPanel implements ViewAdminCampanya {
    private CtrAdminCampanyas ctr;
    
    /**
     * Creates new form JPanelCampaña
     */
    public JPanelCampaña() {
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

        jExportarC.setText("Exportar");

        jBorrarC.setText("Borrar");

        jMedidas.setText("Medidas");

        jModulo.setText("Módulos");

        jListC.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListC);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jModulo)
                            .addComponent(jImportarC)
                            .addComponent(jExportarC)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jBorrarC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jMedidas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jImportarC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jExportarC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBorrarC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jMedidas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(jModulo))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


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
        ctr=controlador;
        
        jBorrarC.addActionListener(ctr);
        jBorrarC.setActionCommand(ViewAdminCampanya.BORRAR);
        
        jExportarC.addActionListener(ctr);
        jExportarC.setActionCommand(ViewAdminCampanya.EXPORTAR);
        
        jImportarC.addActionListener(ctr);
        jImportarC.setActionCommand(ViewAdminCampanya.IMPORTAR);
        
        jMedidas.addActionListener(ctr);
        jMedidas.setActionCommand(ViewAdminCampanya.MEDIDAS);
        
        jModulo.addActionListener(ctr);
        jModulo.setActionCommand(ViewAdminCampanya.MODULO);
        
        jListC.addListSelectionListener(ctr);
        
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
    public File mostrarSelectorFicheros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alert(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean preguntar(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarCampanyas(List<Campaña> campanyas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
