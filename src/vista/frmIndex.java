/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author 
 */
public class frmIndex extends javax.swing.JFrame {

    /**
     * Creates new form frmIndex
     */
    public frmIndex() {
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

        btnEstudiante = new javax.swing.JButton();
        btnProfesor = new javax.swing.JButton();
        btnAcudiante = new javax.swing.JButton();
        btnMaterias = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEstudiante.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEstudiante.setText("Estudiante");
        btnEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstudianteActionPerformed(evt);
            }
        });
        getContentPane().add(btnEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 110, 50));

        btnProfesor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnProfesor.setText("Profesor");
        btnProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfesorActionPerformed(evt);
            }
        });
        getContentPane().add(btnProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 110, 50));

        btnAcudiante.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAcudiante.setText("Acudiente");
        btnAcudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcudianteActionPerformed(evt);
            }
        });
        getContentPane().add(btnAcudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 110, 50));

        btnMaterias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnMaterias.setText("Materias");
        btnMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMateriasActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaterias, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 110, 50));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 380, 20));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Menu principal");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 180, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstudianteActionPerformed
frmEstudiante objEstudiante = new frmEstudiante();
objEstudiante.setVisible(true);
this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEstudianteActionPerformed

    private void btnProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfesorActionPerformed
frmProfesor objProfesor = new frmProfesor();
objProfesor.setVisible(true);
this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProfesorActionPerformed

    private void btnAcudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcudianteActionPerformed
frmAcudiente objAcudiente = new frmAcudiente();
objAcudiente.setVisible(true);
this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAcudianteActionPerformed

    private void btnMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMateriasActionPerformed
frmMateria objMateria = new frmMateria();
objMateria.setVisible(true);
this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMateriasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmIndex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcudiante;
    private javax.swing.JButton btnEstudiante;
    private javax.swing.JButton btnMaterias;
    private javax.swing.JButton btnProfesor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
