/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Objetos.Empleados;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author Main PC
 */
public class Frame_Empleados extends javax.swing.JFrame {
    
    String operacion;
    Empleados empleado;

    public Frame_Empleados() {
        initComponents();
    }

    //Cuando se recibe un empleado como parametro, significa que se va a hacer una operacion con el, ya sea eliminar o modificar sus datos
    public Frame_Empleados(Empleados empl, String op) {
        initComponents();
        rellenarDatos(empl);
        operacion = op;
        empleado = empl;
    }

    public void rellenarDatos(Empleados empleado) {
        txtIdEmpleado.setText(empleado.getIdEmpleado());
        txtNombreEmpleado.setText(empleado.getNombre());
        rellenarPuestos(empleado);
        cbxPuestoEmpleado.setSelectedItem(empleado.getPuesto());
        cbxTurnoEmpleado.setSelectedItem(empleado.getTurno());       
    }

    public void rellenarPuestos(Empleados empleado) {
        //Consulta para obtener todos los puestos de la base de datos y rellenar el combobox con ellos
        ArrayList<String> listaPuestos = empleado.consultarPuestos();
        for (int x = 0; x < listaPuestos.size(); x++) {
            cbxPuestoEmpleado.addItem(listaPuestos.get(x));
        }
      
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatosOperaciones = new javax.swing.JPanel();
        lblTituloOperacion = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnCancelarOperacion = new javax.swing.JButton();
        cbxTurnoEmpleado = new javax.swing.JComboBox<>();
        btnConfirmarOperacion = new javax.swing.JButton();
        txtIdEmpleado = new javax.swing.JTextField();
        txtNombreEmpleado = new javax.swing.JTextField();
        cbxPuestoEmpleado = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(232, 232, 232));
        setUndecorated(true);

        pnlDatosOperaciones.setBackground(new java.awt.Color(232, 232, 232));
        pnlDatosOperaciones.setMinimumSize(new java.awt.Dimension(460, 549));
        pnlDatosOperaciones.setPreferredSize(new java.awt.Dimension(460, 549));
        pnlDatosOperaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTituloOperacion.setBackground(new java.awt.Color(215, 215, 215));
        lblTituloOperacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTituloOperacion.setForeground(new java.awt.Color(64, 64, 64));
        lblTituloOperacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloOperacion.setText("Modificar Empleado");
        lblTituloOperacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        lblTituloOperacion.setMaximumSize(new java.awt.Dimension(213, 30));
        lblTituloOperacion.setMinimumSize(new java.awt.Dimension(213, 30));
        lblTituloOperacion.setOpaque(true);
        lblTituloOperacion.setPreferredSize(new java.awt.Dimension(213, 30));
        lblTituloOperacion.setRequestFocusEnabled(false);
        pnlDatosOperaciones.add(lblTituloOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 400, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(64, 64, 64));
        jLabel12.setText("ID");
        pnlDatosOperaciones.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(64, 64, 64));
        jLabel13.setText("Nombre");
        pnlDatosOperaciones.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(64, 64, 64));
        jLabel14.setText("Puesto");
        pnlDatosOperaciones.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(64, 64, 64));
        jLabel15.setText("Turno");
        pnlDatosOperaciones.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        btnCancelarOperacion.setBackground(new java.awt.Color(215, 215, 215));
        btnCancelarOperacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCancelarOperacion.setForeground(new java.awt.Color(64, 64, 64));
        btnCancelarOperacion.setText("Cancelar");
        btnCancelarOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarOperacionActionPerformed(evt);
            }
        });
        pnlDatosOperaciones.add(btnCancelarOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 110, 30));

        cbxTurnoEmpleado.setBackground(new java.awt.Color(215, 215, 215));
        cbxTurnoEmpleado.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxTurnoEmpleado.setForeground(new java.awt.Color(64, 64, 64));
        cbxTurnoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino" }));
        cbxTurnoEmpleado.setBorder(null);
        cbxTurnoEmpleado.setOpaque(true);
        cbxTurnoEmpleado.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlDatosOperaciones.add(cbxTurnoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 270, -1));

        btnConfirmarOperacion.setBackground(new java.awt.Color(2, 62, 138));
        btnConfirmarOperacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnConfirmarOperacion.setForeground(new java.awt.Color(232, 232, 232));
        btnConfirmarOperacion.setText("Confirmar");
        btnConfirmarOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarOperacionActionPerformed(evt);
            }
        });
        pnlDatosOperaciones.add(btnConfirmarOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 120, 30));

        txtIdEmpleado.setBackground(new java.awt.Color(215, 215, 215));
        txtIdEmpleado.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        txtIdEmpleado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 1));
        txtIdEmpleado.setFocusable(false);
        txtIdEmpleado.setPreferredSize(new java.awt.Dimension(64, 25));
        txtIdEmpleado.setSelectionColor(new java.awt.Color(0, 102, 153));
        pnlDatosOperaciones.add(txtIdEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 270, -1));

        txtNombreEmpleado.setBackground(new java.awt.Color(215, 215, 215));
        txtNombreEmpleado.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtNombreEmpleado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 1));
        txtNombreEmpleado.setPreferredSize(new java.awt.Dimension(64, 25));
        txtNombreEmpleado.setSelectionColor(new java.awt.Color(0, 102, 153));
        pnlDatosOperaciones.add(txtNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 270, -1));

        cbxPuestoEmpleado.setBackground(new java.awt.Color(215, 215, 215));
        cbxPuestoEmpleado.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxPuestoEmpleado.setForeground(new java.awt.Color(64, 64, 64));
        cbxPuestoEmpleado.setBorder(null);
        cbxPuestoEmpleado.setOpaque(true);
        cbxPuestoEmpleado.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlDatosOperaciones.add(cbxPuestoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 270, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDatosOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDatosOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarOperacionActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarOperacionActionPerformed

    private void btnConfirmarOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarOperacionActionPerformed
        switch (operacion) {
            case "Modificar": //Solo se pueden modificar nombre, puesto y turno, el ID no
                empleado.setNombre(txtNombreEmpleado.getText());
                empleado.setPuesto(cbxPuestoEmpleado.getSelectedItem().toString());
                empleado.setTurno(cbxTurnoEmpleado.getSelectedItem().toString());
                empleado.actualizarDatosEmpleado(empleado);                              
                dispose();
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_btnConfirmarOperacionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Frame_Empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnCancelarOperacion;
    public static javax.swing.JButton btnConfirmarOperacion;
    private static javax.swing.JComboBox<String> cbxPuestoEmpleado;
    private static javax.swing.JComboBox<String> cbxTurnoEmpleado;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    public javax.swing.JLabel lblTituloOperacion;
    private javax.swing.JPanel pnlDatosOperaciones;
    private static javax.swing.JTextField txtIdEmpleado;
    private static javax.swing.JTextField txtNombreEmpleado;
    // End of variables declaration//GEN-END:variables
}
