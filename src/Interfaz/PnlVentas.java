/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Kevin MG
 */
public class PnlVentas extends javax.swing.JPanel {

    /**
     * Creates new form pnlNuevaInscripcion
     */
    public PnlVentas() {
        initComponents();
    }

    public static void bloquearEdicion() {             

    }

    public static void desbloquearEdicion() {
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbxYear = new javax.swing.JComboBox<>();
        btnOperacion = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        txtDireccion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jLabel20 = new javax.swing.JLabel();
        cbxYear1 = new javax.swing.JComboBox<>();
        btnOperacion1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnBajas1 = new javax.swing.JButton();
        btnModificar1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel22 = new javax.swing.JLabel();
        txtDireccion4 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList6 = new javax.swing.JList<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(232, 232, 232));
        setMinimumSize(new java.awt.Dimension(1156, 750));
        setPreferredSize(new java.awt.Dimension(1366, 676));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxYear.setBackground(new java.awt.Color(232, 232, 232));
        cbxYear.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018" }));
        cbxYear.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxYear.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 200, -1));

        btnOperacion.setBackground(new java.awt.Color(211, 211, 211));
        btnOperacion.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 18)); // NOI18N
        btnOperacion.setForeground(new java.awt.Color(64, 64, 64));
        btnOperacion.setText("Limpiar");
        btnOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperacionActionPerformed(evt);
            }
        });
        add(btnOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 110, 30));

        jDateChooser1.setBackground(new java.awt.Color(215, 215, 215));
        jDateChooser1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 14), new java.awt.Color(0, 102, 153))); // NOI18N
        jDateChooser1.setMinSelectableDate(new java.util.Date(-62135744286000L));
        jDateChooser1.setMinimumSize(new java.awt.Dimension(85, 25));
        jDateChooser1.setPreferredSize(new java.awt.Dimension(85, 25));
        add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 25, -1));

        txtDireccion.setBackground(new java.awt.Color(215, 215, 215));
        txtDireccion.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        txtDireccion.setFocusable(false);
        txtDireccion.setPreferredSize(new java.awt.Dimension(64, 25));
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 270, -1));

        jLabel16.setBackground(new java.awt.Color(215, 215, 215));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(64, 64, 64));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Vista previa del Ticket");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel16.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel16.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(170, 30));
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 510, -1));

        jLabel17.setBackground(new java.awt.Color(215, 215, 215));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(64, 64, 64));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Nueva venta");
        jLabel17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel17.setMaximumSize(new java.awt.Dimension(213, 30));
        jLabel17.setMinimumSize(new java.awt.Dimension(213, 30));
        jLabel17.setOpaque(true);
        jLabel17.setPreferredSize(new java.awt.Dimension(213, 30));
        jLabel17.setRequestFocusEnabled(false);
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 480, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Panel de ventas");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 230, -1));

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cantidad");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 170, -1));

        jCheckBox1.setBackground(new java.awt.Color(215, 215, 215));
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(64, 64, 64));
        jCheckBox1.setText("Usar la fecha de hoy ");
        jCheckBox1.setOpaque(true);
        add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 160, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Fecha");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 300, -1));

        jButton4.setBackground(new java.awt.Color(211, 211, 211));
        jButton4.setForeground(new java.awt.Color(64, 64, 64));
        jButton4.setText("Agregar");
        jButton4.setPreferredSize(new java.awt.Dimension(72, 25));
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 80, -1));

        jList2.setBackground(new java.awt.Color(232, 232, 232));
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 60, 160));

        jList3.setBackground(new java.awt.Color(232, 232, 232));
        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 70, 160));

        jList4.setBackground(new java.awt.Color(232, 232, 232));
        jList4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList4);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 190, 160));

        jLabel20.setBackground(new java.awt.Color(51, 51, 51));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("ID del Producto");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 210, -1));

        cbxYear1.setBackground(new java.awt.Color(232, 232, 232));
        cbxYear1.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxYear1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018" }));
        cbxYear1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxYear1.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxYear1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 210, -1));

        btnOperacion1.setBackground(new java.awt.Color(2, 62, 138));
        btnOperacion1.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 18)); // NOI18N
        btnOperacion1.setForeground(new java.awt.Color(232, 232, 232));
        btnOperacion1.setText("Finalizar");
        btnOperacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperacion1ActionPerformed(evt);
            }
        });
        add(btnOperacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 550, 110, 30));

        jLabel11.setBackground(new java.awt.Color(215, 215, 215));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(64, 64, 64));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Herramientas del Gerente");
        jLabel11.setOpaque(true);
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, 480, 33));

        btnBajas1.setBackground(new java.awt.Color(1, 50, 112));
        btnBajas1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBajas1.setForeground(new java.awt.Color(232, 232, 232));
        btnBajas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEliminar.png"))); // NOI18N
        btnBajas1.setText("Eliminar");
        btnBajas1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBajas1.setFocusPainted(false);
        btnBajas1.setIconTextGap(8);
        btnBajas1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBajas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajas1ActionPerformed(evt);
            }
        });
        add(btnBajas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 670, 180, 30));

        btnModificar1.setBackground(new java.awt.Color(2, 62, 138));
        btnModificar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar1.setForeground(new java.awt.Color(232, 232, 232));
        btnModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEditar.png"))); // NOI18N
        btnModificar1.setText("Modificar");
        btnModificar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnModificar1.setFocusPainted(false);
        btnModificar1.setIconTextGap(8);
        btnModificar1.setPreferredSize(new java.awt.Dimension(75, 27));
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });
        add(btnModificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, 180, 30));

        jTextField1.setBackground(new java.awt.Color(215, 215, 215));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(142, 142, 142));
        jTextField1.setText("Folio de la venta...");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 16, 1, 1));
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, 270, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Buscar");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 35, 70, -1));

        jScrollPane6.setBackground(new java.awt.Color(232, 232, 232));
        jScrollPane6.setOpaque(false);

        jList1.setBackground(new java.awt.Color(232, 232, 232));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jList1);

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 540, 510, 210));

        jLabel22.setBackground(new java.awt.Color(51, 51, 51));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Empleado que atiende");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 200, -1));

        txtDireccion4.setBackground(new java.awt.Color(232, 232, 232));
        txtDireccion4.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtDireccion4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtDireccion4.setPreferredSize(new java.awt.Dimension(64, 25));
        txtDireccion4.setSelectionColor(new java.awt.Color(0, 102, 153));
        add(txtDireccion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 170, -1));

        jList5.setBackground(new java.awt.Color(232, 232, 232));
        jList5.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jList5);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 60, 160));

        jList6.setBackground(new java.awt.Color(232, 232, 232));
        jList6.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jList6);

        add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 60, 160));

        jLabel18.setBackground(new java.awt.Color(215, 215, 215));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(64, 64, 64));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Vista previa del Ticket");
        jLabel18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel18.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel18.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel18.setOpaque(true);
        jLabel18.setPreferredSize(new java.awt.Dimension(170, 30));
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 510, -1));

        jLabel19.setBackground(new java.awt.Color(215, 215, 215));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(64, 64, 64));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Ventas recientes");
        jLabel19.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel19.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel19.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel19.setOpaque(true);
        jLabel19.setPreferredSize(new java.awt.Dimension(170, 30));
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 510, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 250, 300));
    }// </editor-fold>//GEN-END:initComponents

    private void btnOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperacionActionPerformed
               
        revalidate();
        repaint();
    }//GEN-LAST:event_btnOperacionActionPerformed

    private void btnOperacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperacion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOperacion1ActionPerformed

    private void btnBajas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBajas1ActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBajas1;
    private javax.swing.JButton btnModificar1;
    public static javax.swing.JButton btnOperacion;
    public static javax.swing.JButton btnOperacion1;
    private static javax.swing.JComboBox<String> cbxYear;
    private static javax.swing.JComboBox<String> cbxYear1;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JList<String> jList4;
    private javax.swing.JList<String> jList5;
    private javax.swing.JList<String> jList6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private static javax.swing.JTextField txtDireccion;
    private static javax.swing.JTextField txtDireccion4;
    // End of variables declaration//GEN-END:variables
}
