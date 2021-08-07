/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.clsConexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.clsAcudiente;


/**
 *
 * @author 
 */
public class frmAcudiente extends javax.swing.JFrame {

    /**
     * Creates new form frmAcudiente
     */
    public frmAcudiente() {
        initComponents();
        objcon.conectar();
        valDoc.setVisible(false);
        valNom.setVisible(false);
        valDir.setVisible(false);
        valEma.setVisible(false);
        valApe.setVisible(false);
        valTel.setVisible(false);
        bloqCampos();
        
    }
    DefaultTableModel tabladatos;
    clsConexion objcon = new clsConexion();
    clsAcudiente objAcu = new clsAcudiente();
    
    public void capturar(){
    objAcu.setDocAcu(txtDocAcu.getText());
    objAcu.setNomAcu(txtNomAcu.getText());
    objAcu.setApeAcu(txtApeAcu.getText());
    objAcu.setDirAcu(txtDirAcu.getText());
    objAcu.setTelAcu(txtTelAcu.getText());
    objAcu.setEmaAcu(txtEmaAcu.getText());
    }
    
    int cont=0;
    public void validar() {
        if (txtDocAcu.getText().isEmpty()) {txtDocAcu.grabFocus();valDoc.setVisible(true); cont++;}
            else{valDoc.setVisible(false);}
        if (txtNomAcu.getText().isEmpty()) {txtNomAcu.grabFocus();valNom.setVisible(true);cont++;}
            else{valNom.setVisible(false);}
        if (txtApeAcu.getText().isEmpty()) {txtApeAcu.grabFocus();valApe.setVisible(true);cont++;}
            else{valApe.setVisible(false);}
        if (txtDirAcu.getText().isEmpty()) {txtDirAcu.grabFocus();valDir.setVisible(true);cont++;}
            else{valDir.setVisible(false);}
        if (txtTelAcu.getText().isEmpty()) {txtTelAcu.grabFocus();valTel.setVisible(true);cont++;}
            else{valTel.setVisible(false);}
        if (txtEmaAcu.getText().isEmpty()) {txtEmaAcu.grabFocus();valEma.setVisible(true);cont++;}
            else{valEma.setVisible(false);}
    }      
    
    public void guardar(){
        cont=0;
        validar();
        if (cont==0) {
            capturar();
            objAcu.guardar();
            limpiar();
            bloqCampos();
        }
    }
    
    public void buscar(){
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento del estudiante");
            objAcu.setDocAcu(bus);
            objAcu.buscar();
            if (objAcu.datos.next()==true) {
                txtDocAcu.setText(objAcu.datos.getString(1));
                txtNomAcu.setText(objAcu.datos.getString(2));
                txtApeAcu.setText(objAcu.datos.getString(3));
                txtDirAcu.setText(objAcu.datos.getString(4));
                txtTelAcu.setText(objAcu.datos.getString(5));
                txtEmaAcu.setText(objAcu.datos.getString(6));
                txtDocAcu.setEnabled(false);
                txtNomAcu.setEnabled(false);
                txtApeAcu.setEnabled(false);
                txtDirAcu.setEnabled(false);
                txtTelAcu.setEnabled(false);
                txtEmaAcu.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
                btnAgregarEstudiante.setEnabled(true);
                llenartabla();
            } else {
                JOptionPane.showMessageDialog(null, "El acudiente no existe");
                    limpiar();
                    bloqCampos();
            }
        } catch (Exception e) {
                limpiar();
                bloqCampos();
        }   
    }
    
    public void actualizar(){
        cont=0;
        validar();
        if (cont==0) {
            capturar();
            objAcu.actualizar();
            limpiar();
            bloqCampos();
        }
    }
    
    public void comboDocEst(){
        try {
            objAcu.comboDocEst();
            while (objAcu.datosCombo.next()==true) {  
                cbxDocEst.addItem(objAcu.datosCombo.getString(1));
            }
        } catch (Exception ex) {
            System.err.println("Error error "+ex);
        }
    }
    
    public void busNomApe(){
        try {
            objAcu.setDocEst(cbxDocEst.getSelectedItem().toString());
            objAcu.busNomApe(); 
            if (objAcu.datosNomApe.next()==true) {
                txtNomApeEst.setText(objAcu.datosNomApe.getString(1));
            } else {
            }
        } catch (Exception e) {
        }
        
    }
    
    public void capAcuEst(){
        objAcu.setDocAcu(txtDocAcu.getText());
        objAcu.setDocEst(cbxDocEst.getSelectedItem().toString());
    }
    
    public void agregar(){
        capAcuEst();
        objAcu.agregar();
    }
    
    //Para tabla
    public void crearTabla() {
    Object modelodata[][] = new Object[0][0];// El primer valor es la cantidad de filas, igual no interesa porque las filas se adaptan a la cantidad de registros incluidos
 
    Object modelotitulos[] = {"Documento","Nombre", "Apellido","Telefono"};
    tabladatos = new DefaultTableModel(); // tabladatos, es el nombre del modelo que creo, no el de la tabla
    tabladatos = new DefaultTableModel(modelodata, modelotitulos);
    this.tblDocEst.setModel(this.tabladatos); //tabla es el nombre de la tabla en el formulario, no puede ser el mismo del modelo 
    }
    
    public void borrarTabla(){
      while(0<this.tblDocEst.getRowCount()) {
      tblDocEst.setModel(new DefaultTableModel());
      this.crearTabla();
      }    
     }
 
    public void llenartabla() {
        try{
      borrarTabla();  // metodo que permite limpiar la tabla    
      objAcu.camposTabla();//método que tiene el código SQL con la información de la tabla, recuerde que las instrucciones de este método están en la clase lógica.
      while (objAcu.datosTable.next()==true) {
           String Documento= objAcu.datosTable.getString(1);
           String Nombre = objAcu.datosTable.getString(2);
           String Apellido = objAcu.datosTable.getString(3);
           String Telefono = objAcu.datosTable.getString(4);
           Object fila[]={Documento,Nombre,Apellido, Telefono};
           tabladatos.addRow(fila);    // tabladatos Es el nombre del modelo
            }
       } catch(Exception ex) {
              System.out.println("error al llenar la tabla");
        }
    }    
    
    
    public void bloqCampos(){
        txtDocAcu.setEnabled(false);
        txtNomAcu.setEnabled(false);
        txtApeAcu.setEnabled(false);
        txtDirAcu.setEnabled(false);
        txtTelAcu.setEnabled(false);
        txtEmaAcu.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnBuscar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnAgregarEstudiante.setEnabled(false);
        cbxDocEst.setEnabled(false);
        txtNomApeEst.setEnabled(false);
        btnAgregar.setEnabled(false);
    }
    
    public void desBloqCampos(){
        txtDocAcu.setEnabled(true);
        txtNomAcu.setEnabled(true);
        txtApeAcu.setEnabled(true);
        txtDirAcu.setEnabled(true);
        txtTelAcu.setEnabled(true);
        txtEmaAcu.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnAgregarEstudiante.setEnabled(true);
    }
    
    public void limpiar(){
        txtDocAcu.setText(null);
        txtNomAcu.setText(null);
        txtApeAcu.setText(null);
        txtDirAcu.setText(null);
        txtTelAcu.setText(null);
        txtEmaAcu.setText(null);
        txtNomApeEst.setText(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEliminar = new javax.swing.JButton();
        txtDocAcu = new javax.swing.JTextField();
        txtNomAcu = new javax.swing.JTextField();
        txtDirAcu = new javax.swing.JTextField();
        txtApeAcu = new javax.swing.JTextField();
        txtTelAcu = new javax.swing.JTextField();
        txtEmaAcu = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnAgregarEstudiante = new javax.swing.JButton();
        cbxDocEst = new javax.swing.JComboBox<>();
        txtNomApeEst = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocEst = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        valDoc = new javax.swing.JLabel();
        valNom = new javax.swing.JLabel();
        valDir = new javax.swing.JLabel();
        valEma = new javax.swing.JLabel();
        valApe = new javax.swing.JLabel();
        valTel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDocAcu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDocAcu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocAcuKeyPressed(evt);
            }
        });
        getContentPane().add(txtDocAcu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 140, 30));

        txtNomAcu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        getContentPane().add(txtNomAcu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 170, 30));

        txtDirAcu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        getContentPane().add(txtDirAcu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 170, 30));

        txtApeAcu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        getContentPane().add(txtApeAcu, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 150, 30));

        txtTelAcu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTelAcu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelAcuKeyPressed(evt);
            }
        });
        getContentPane().add(txtTelAcu, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 150, 30));

        txtEmaAcu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        getContentPane().add(txtEmaAcu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 170, 30));

        btnNuevo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 110, 30));

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 120, 30));

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 110, 30));

        btnModificar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 120, 30));

        btnActualizar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 120, 30));

        btnRegresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, -1, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 640, 10));

        btnAgregarEstudiante.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarEstudiante.setText("Agregar estudiante");
        btnAgregarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEstudianteActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregarEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 30));

        cbxDocEst.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxDocEst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cbxDocEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDocEstActionPerformed(evt);
            }
        });
        cbxDocEst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDocEstKeyPressed(evt);
            }
        });
        getContentPane().add(cbxDocEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 120, 30));

        txtNomApeEst.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        getContentPane().add(txtNomApeEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 240, 30));

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 100, 30));

        tblDocEst.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblDocEst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Documento", "Nombre", "Apellido", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDocEst);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 590, 100));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Documento");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 20));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Nombres");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Direccion");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Apellidos");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Telefono");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Documento");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Nombres y apellidos");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 140, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Acudiente");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 90, 30));

        valDoc.setForeground(new java.awt.Color(255, 0, 0));
        valDoc.setText(" Obligatorio");
        getContentPane().add(valDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 60, 20));

        valNom.setForeground(new java.awt.Color(255, 0, 0));
        valNom.setText(" Obligatorio");
        getContentPane().add(valNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 60, 20));

        valDir.setForeground(new java.awt.Color(255, 0, 0));
        valDir.setText(" Obligatorio");
        getContentPane().add(valDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 60, 20));

        valEma.setForeground(new java.awt.Color(255, 0, 0));
        valEma.setText(" Obligatorio");
        getContentPane().add(valEma, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 70, 20));

        valApe.setForeground(new java.awt.Color(255, 0, 0));
        valApe.setText(" Obligatorio");
        getContentPane().add(valApe, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 60, 20));

        valTel.setForeground(new java.awt.Color(255, 0, 0));
        valTel.setText(" Obligatorio");
        getContentPane().add(valTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 130, 60, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 640, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
this.guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
//this.eliminar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtDocAcuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocAcuKeyPressed
    txtDocAcu.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c ==  KeyEvent.VK_DELETE)))) {
                getToolkit().beep();
                e.consume();
            }
        }
     });        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocAcuKeyPressed

    private void txtTelAcuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelAcuKeyPressed
    txtTelAcu.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c ==  KeyEvent.VK_DELETE)))) {
                getToolkit().beep();
                e.consume();
            }
        }
     });
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelAcuKeyPressed

    private void cbxDocEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDocEstActionPerformed
        

        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocEstActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
frmIndex objIndex = new frmIndex();
objIndex.setVisible(true);
this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
desBloqCampos();
limpiar();
borrarTabla();

// TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        txtDocAcu.setEnabled(false);
        txtNomAcu.setEnabled(true);
        txtApeAcu.setEnabled(true);
        txtDirAcu.setEnabled(true);
        txtTelAcu.setEnabled(true);
        txtEmaAcu.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnBuscar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnActualizar.setEnabled(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAgregarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEstudianteActionPerformed
cbxDocEst.setEnabled(true);
comboDocEst();
// TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarEstudianteActionPerformed

    private void cbxDocEstKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDocEstKeyPressed
int code = evt.getKeyCode();
        if( code == KeyEvent.VK_ENTER){
           busNomApe(); 
           btnAgregar.setEnabled(true);
        };        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocEstKeyPressed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
agregar();
llenartabla();
txtApeAcu.setText(null);
// TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

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
            java.util.logging.Logger.getLogger(frmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAcudiente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarEstudiante;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbxDocEst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblDocEst;
    private javax.swing.JTextField txtApeAcu;
    private javax.swing.JTextField txtDirAcu;
    private javax.swing.JTextField txtDocAcu;
    private javax.swing.JTextField txtEmaAcu;
    private javax.swing.JTextField txtNomAcu;
    private javax.swing.JTextField txtNomApeEst;
    private javax.swing.JTextField txtTelAcu;
    private javax.swing.JLabel valApe;
    private javax.swing.JLabel valDir;
    private javax.swing.JLabel valDoc;
    private javax.swing.JLabel valEma;
    private javax.swing.JLabel valNom;
    private javax.swing.JLabel valTel;
    // End of variables declaration//GEN-END:variables
}
