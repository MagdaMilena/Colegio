/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.clsProfesor;

/**
 *
 * @author 
 */
public class frmProfesor extends javax.swing.JFrame {

    /**
     * Creates new form frmProfesor
     */
    public frmProfesor() {
        initComponents();
        valDoc.setVisible(false);
        valNom.setVisible(false);
        valApe.setVisible(false);
        valDir.setVisible(false);
        valTel.setVisible(false);
        valEma.setVisible(false);
        valTit.setVisible(false);
        bloqCampos();
        comboNomMat();
        bloqCampMatPro();
    }
    DefaultTableModel tabladatos;
    clsProfesor objPro = new clsProfesor();
    
    public void capturar(){
        objPro.setDocPro(txtDocPro.getText());
        objPro.setNomPro(txtNomPro.getText());
        objPro.setApePro(txtApePro.getText());
        objPro.setDirPro(txtDirPro.getText());
        objPro.setTelPro(txtTelPro.getText());
        objPro.setEmaPro(txtEmaPro.getText());
        objPro.setTitPro(cbxTitPro.getSelectedItem().toString());
    }
    
        int cont=0;
    public void validar() {
        if (txtDocPro.getText().isEmpty()) {txtDocPro.grabFocus();valDoc.setVisible(true); cont++;}
            else{valDoc.setVisible(false);}
        if (txtNomPro.getText().isEmpty()) {txtNomPro.grabFocus();valNom.setVisible(true);cont++;}
            else{valNom.setVisible(false);}
        if (txtApePro.getText().isEmpty()) {txtApePro.grabFocus();valApe.setVisible(true);cont++;}
            else{valApe.setVisible(false);}
        if (txtDirPro.getText().isEmpty()) {txtDirPro.grabFocus();valDir.setVisible(true);cont++;}
            else{valDir.setVisible(false);}
        if (txtTelPro.getText().isEmpty()) {txtTelPro.grabFocus();valTel.setVisible(true);cont++;}
            else{valTel.setVisible(false);}
        if (txtEmaPro.getText().isEmpty()) {txtEmaPro.grabFocus();valEma.setVisible(true);cont++;}
            else{valEma.setVisible(false);}
        if (cbxTitPro.getSelectedItem()=="Seleccione") {valTit.setVisible(true);cont++;}
            else{valTit.setVisible(false);}
    }   
    
    public void guardar(){
        cont=0;
        validar();
        if (cont==0) {
            capturar();
            objPro.guardar();
            limpiar();
            bloqCampos();
        }
    }
    
    public void buscar(){
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento del profesor");
            objPro.setDocPro(bus);
            objPro.buscar();
            if (objPro.datos.next()==true) {
                txtDocPro.setText(objPro.datos.getString(1));
                txtNomPro.setText(objPro.datos.getString(2));
                txtApePro.setText(objPro.datos.getString(3));
                txtDirPro.setText(objPro.datos.getString(4));
                txtTelPro.setText(objPro.datos.getString(5));
                txtEmaPro.setText(objPro.datos.getString(6));
                cbxTitPro.setSelectedItem(objPro.datos.getString(7));
                txtDocPro.setEnabled(false);
                txtNomPro.setEnabled(false);
                txtApePro.setEnabled(false);
                txtDirPro.setEnabled(false);
                txtTelPro.setEnabled(false);
                txtEmaPro.setEnabled(false);
                cbxTitPro.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "El profesor no existe");
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
            objPro.actualizar();
            limpiar();
            bloqCampos();
        }
    }
    
    public void bloqCampos(){
        txtDocPro.setEnabled(false);
        txtNomPro.setEnabled(false);
        txtApePro.setEnabled(false);
        txtDirPro.setEnabled(false);
        txtTelPro.setEnabled(false);
        txtEmaPro.setEnabled(false);
        cbxTitPro.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnActualizar.setEnabled(false);
    }
    
    public void desBloqCampos(){
        txtDocPro.setEnabled(true);
        txtNomPro.setEnabled(true);
        txtApePro.setEnabled(true);
        txtDirPro.setEnabled(true);
        txtTelPro.setEnabled(true);
        txtEmaPro.setEnabled(true);
        cbxTitPro.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnActualizar.setEnabled(false);
    }
    
    public void limpiar(){
        txtDocPro.setText(null);
        txtNomPro.setText(null);
        txtApePro.setText(null);
        txtDirPro.setText(null);
        txtTelPro.setText(null);
        txtEmaPro.setText(null);
        cbxTitPro.setSelectedIndex(0);
    }
    
    ///////////////// Profesor x materia  ////////////// 
    
    public void comboNomMat(){
        try {
            objPro.comboNomMat();
            while (objPro.datosCombo.next()==true) {  
                cbxNomMat.addItem(objPro.datosCombo.getString(2));
            }
        } catch (Exception ex) {
            System.err.println("Error error "+ex);
        }
    }
    
    public void buscarCodMat(){
        try {
            objPro.setNomMat(cbxNomMat.getSelectedItem().toString());
            objPro.buscarCodMat();
            if (objPro.datosCodMat.next()==true) {
                txtCodMat.setText(objPro.datosCodMat.getString(1));
                txtGraMat.setText(objPro.datosCodMat.getString(2));
            } else {
            }
        } catch (Exception e) {
        }
    }
    
    public void capturarMatPro(){
        objPro.setCodMat(Integer.parseInt(txtCodMat.getText()));
        objPro.setDocPro(txtDocPro.getText());
        objPro.setGraMat(txtGraMat.getText());
    }
    
    public void guardarMP(){
        capturarMatPro();
        objPro.guardarMP();
        limpiarMatPro();
    }
    
    
    
    
    //Para tabla
    public void crearTabla() {
    Object modelodata[][] = new Object[0][0];// El primer valor es la cantidad de filas, igual no interesa porque las filas se adaptan a la cantidad de registros incluidos
 
    Object modelotitulos[] = {"Nombre","Grado"};
    tabladatos = new DefaultTableModel(); // tabladatos, es el nombre del modelo que creo, no el de la tabla
    tabladatos = new DefaultTableModel(modelodata, modelotitulos);
    this.tabMatPro.setModel(this.tabladatos); //tabla es el nombre de la tabla en el formulario, no puede ser el mismo del modelo 
    }
    
    public void borrarTabla(){
      while(0<this.tabMatPro.getRowCount()) {
      tabMatPro.setModel(new DefaultTableModel());
      this.crearTabla();
      }    
     }
 
    public void llenartabla() {
        try{
      borrarTabla();  // metodo que permite limpiar la tabla    
      objPro.camposTabla();//método que tiene el código SQL con la información de la tabla, recuerde que las instrucciones de este método están en la clase lógica.
      while (objPro.datosTable.next()==true) {
           String Nombre= objPro.datosTable.getString(1);
           String Grado = objPro.datosTable.getString(2);
           Object fila[]={Nombre,Grado};
           tabladatos.addRow(fila);    // tabladatos Es el nombre del modelo
            }
       } catch(Exception ex) {
              System.out.println("error al llenar la tabla");
        }
    }
    
    
    
    public void bloqCampMatPro(){
        cbxNomMat.setEnabled(false);
        txtGraMat.setEnabled(false);
        btGuardar.setEnabled(false);
        btBuscar.setEnabled(true);
    }
    
    public void desBloqCampMatPro(){
        cbxNomMat.setEnabled(true);
        btGuardar.setEnabled(true);
        btBuscar.setEnabled(true);
    }
    
    public void limpiarMatPro(){
        txtCodMat.setText(null);
        cbxNomMat.setSelectedIndex(0);
        txtGraMat.setText(null);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtDocPro = new javax.swing.JTextField();
        txtNomPro = new javax.swing.JTextField();
        txtApePro = new javax.swing.JTextField();
        txtDirPro = new javax.swing.JTextField();
        txtTelPro = new javax.swing.JTextField();
        txtEmaPro = new javax.swing.JTextField();
        cbxTitPro = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        valDoc = new javax.swing.JLabel();
        valNom = new javax.swing.JLabel();
        valApe = new javax.swing.JLabel();
        valDir = new javax.swing.JLabel();
        valTel = new javax.swing.JLabel();
        valEma = new javax.swing.JLabel();
        valTit = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbxNomMat = new javax.swing.JComboBox<>();
        txtGraMat = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btGuardar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btNuevo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabMatPro = new javax.swing.JTable();
        txtCodMat = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnEliminar.setText("Eliminar");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDocPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDocPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocProKeyPressed(evt);
            }
        });
        jPanel1.add(txtDocPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 160, 30));

        txtNomPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel1.add(txtNomPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 160, 30));

        txtApePro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel1.add(txtApePro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 160, 30));

        txtDirPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel1.add(txtDirPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 160, 30));

        txtTelPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTelPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelProKeyPressed(evt);
            }
        });
        jPanel1.add(txtTelPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 160, 30));

        txtEmaPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel1.add(txtEmaPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 160, 30));

        cbxTitPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxTitPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Tecnico", "Tecnologo", "Profesional", "Especialista", "Magister", "Doctor" }));
        jPanel1.add(cbxTitPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 160, 30));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Documento");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Nombres");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 60, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Apellidos");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Direccion");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Telefono");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Email");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Titulo");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 90, 30));

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 90, 30));

        btnActualizar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 100, 30));

        btnModificar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 90, 30));

        btnNuevo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 80, 30));

        valDoc.setForeground(new java.awt.Color(204, 0, 0));
        valDoc.setText("Obligatorio");
        jPanel1.add(valDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 100, 30));

        valNom.setForeground(new java.awt.Color(204, 0, 0));
        valNom.setText("Obligatorio");
        jPanel1.add(valNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 70, 30));

        valApe.setForeground(new java.awt.Color(204, 0, 0));
        valApe.setText("Obligatorio");
        jPanel1.add(valApe, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 90, 30));

        valDir.setForeground(new java.awt.Color(204, 0, 0));
        valDir.setText("Obligatorio");
        jPanel1.add(valDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 80, 30));

        valTel.setForeground(new java.awt.Color(204, 0, 0));
        valTel.setText("Obligatorio");
        jPanel1.add(valTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 80, 30));

        valEma.setForeground(new java.awt.Color(204, 0, 0));
        valEma.setText("Obligatorio");
        jPanel1.add(valEma, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 90, 30));

        valTit.setForeground(new java.awt.Color(204, 0, 0));
        valTit.setText("Obligatorio");
        jPanel1.add(valTit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 80, 30));

        btnRegresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 100, 30));

        jTabbedPane1.addTab("Profesor", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxNomMat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxNomMat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cbxNomMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxNomMatKeyPressed(evt);
            }
        });
        jPanel2.add(cbxNomMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 66, 160, 30));

        txtGraMat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtGraMat.setEnabled(false);
        jPanel2.add(txtGraMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 160, 30));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Materia");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Grado");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        btGuardar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btGuardar.setText("Guardar");
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 90, 30));

        btBuscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 90, 30));

        btnImprimir.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnImprimir.setText("Imprimir");
        jPanel2.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 90, 30));

        btNuevo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btNuevo.setText("Nuevo");
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 80, 30));

        tabMatPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tabMatPro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Grado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabMatPro);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 440, 110));

        txtCodMat.setEnabled(false);
        txtCodMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodMatActionPerformed(evt);
            }
        });
        jPanel2.add(txtCodMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 90, 30));

        jTabbedPane1.addTab("Materias Profesor", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
desBloqCampos();
limpiar();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        txtDocPro.setEnabled(false);
        txtNomPro.setEnabled(true);
        txtApePro.setEnabled(true);
        txtDirPro.setEnabled(true);
        txtTelPro.setEnabled(true);
        txtEmaPro.setEnabled(true);
        cbxTitPro.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnBuscar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnActualizar.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
frmIndex objIndex = new frmIndex();
objIndex.setVisible(true);
this.setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void txtDocProKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocProKeyPressed
txtDocPro.addKeyListener(new KeyAdapter() {

public void keyTyped(KeyEvent e) {
     char c = e.getKeyChar();
if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c ==  KeyEvent.VK_DELETE)))) {
                getToolkit().beep();
                e.consume();
            }
        }
    });
// TODO add your handling code here:
    }//GEN-LAST:event_txtDocProKeyPressed

    private void txtTelProKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelProKeyPressed
txtTelPro.addKeyListener(new KeyAdapter() {

public void keyTyped(KeyEvent e) {
     char c = e.getKeyChar();
if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c ==  KeyEvent.VK_DELETE)))) {
                getToolkit().beep();
                e.consume();
            }
        }
    });
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelProKeyPressed

    private void cbxNomMatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxNomMatKeyPressed
int code = evt.getKeyCode();
        if( code == KeyEvent.VK_ENTER){
           buscarCodMat(); 
        };
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxNomMatKeyPressed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
guardarMP();
llenartabla();
bloqCampMatPro();
        // TODO add your handling code here:
    }//GEN-LAST:event_btGuardarActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
buscar();
llenartabla();
// TODO add your handling code here:
    }//GEN-LAST:event_btBuscarActionPerformed

    private void txtCodMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodMatActionPerformed

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
desBloqCampMatPro();
// TODO add your handling code here:
    }//GEN-LAST:event_btNuevoActionPerformed

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
            java.util.logging.Logger.getLogger(frmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbxNomMat;
    private javax.swing.JComboBox<String> cbxTitPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabMatPro;
    private javax.swing.JTextField txtApePro;
    private javax.swing.JTextField txtCodMat;
    private javax.swing.JTextField txtDirPro;
    private javax.swing.JTextField txtDocPro;
    private javax.swing.JTextField txtEmaPro;
    private javax.swing.JTextField txtGraMat;
    private javax.swing.JTextField txtNomPro;
    private javax.swing.JTextField txtTelPro;
    private javax.swing.JLabel valApe;
    private javax.swing.JLabel valDir;
    private javax.swing.JLabel valDoc;
    private javax.swing.JLabel valEma;
    private javax.swing.JLabel valNom;
    private javax.swing.JLabel valTel;
    private javax.swing.JLabel valTit;
    // End of variables declaration//GEN-END:variables
}
