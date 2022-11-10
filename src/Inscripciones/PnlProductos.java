package Inscripciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static Inscripciones.Principal.reg;
import java.awt.Color;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Kevin MG
 */
public class PnlProductos extends javax.swing.JPanel {

    /**
     * Creates new form pnlGrupos
     */
    public PnlProductos() {
        initComponents();
        inicializar();
        turnoActual = cbxTurno.getSelectedItem().toString();
        sql = "SELECT * FROM alumnos WHERE nivel = '" + cbxNivel.getSelectedItem().toString() + "' AND grado = '"
                + cbxGrado.getSelectedItem().toString() + "' AND turno = '" + cbxTurno.getSelectedItem().toString() + "'";

        verTabla();
        //pnlGrafica.setLayout(new BoxLayout(pnlGrafica, BoxLayout.PAGE_AXIS));
    }

    public void inicializar() {
        DefaultTableModel miModelo = new DefaultTableModel();
        miModelo.addColumn("Matricula");
        miModelo.addColumn("Nombre");
        miModelo.addColumn("Apellido Paterno");
        miModelo.addColumn("Apellido Materno");
        miModelo.addColumn("Dia Nacimiento");
        miModelo.addColumn("Mes Nacimiento");
        miModelo.addColumn("Año Nacimiento");
        miModelo.addColumn("Calificacion");
        jTable1.setModel(miModelo);
    }

    static String sql;
    static Conectar con = new Conectar();
    static PreparedStatement ps;
    static Statement sentencia;
    static ResultSet rs;
    String[] datos;
    DefaultTableModel miModelo;

    public void verTabla() {
        miModelo = new DefaultTableModel();
        datos = new String[8];
        while (miModelo.getRowCount() != 0) {
            miModelo.removeRow(0);
        }

        miModelo.addColumn("Matricula");
        miModelo.addColumn("Nombre");
        miModelo.addColumn("Apellido Paterno");
        miModelo.addColumn("Apellido Materno");
        miModelo.addColumn("Dia Nacimiento");
        miModelo.addColumn("Mes Nacimiento");
        miModelo.addColumn("Año Nacimiento");
        miModelo.addColumn("Calificacion");

        jTable1.setModel(miModelo);

        try {
            sentencia = reg.createStatement();
            rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(2);
                datos[1] = rs.getString(3);
                datos[2] = rs.getString(4);
                datos[3] = rs.getString(5);

                datos[4] = rs.getString(9);
                datos[5] = rs.getString(10);
                datos[6] = rs.getString(11);
                datos[7] = rs.getString(13);
                miModelo.addRow(datos);
            }
            jTable1.setModel(miModelo);
        } catch (SQLException ex) {
            Logger.getLogger(PnlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            sentencia = reg.createStatement();
            rs = sentencia.executeQuery("SELECT COUNT(*) FROM alumnos WHERE nivel = 'Primaria' AND turno = '"
                    + cbxTurno.getSelectedItem().toString() + "'");
            rs.next();
            dato1 = rs.getInt(1);
            //System.out.println("Dato1: " + dato1);

            rs = sentencia.executeQuery("SELECT COUNT(*) FROM alumnos WHERE nivel = 'Secundaria' AND turno = '"
                    + cbxTurno.getSelectedItem().toString() + "'");
            rs.next();
            dato2 = Integer.parseInt(rs.getString(1));
            //System.out.println("Dato2: " + dato2);

            rs = sentencia.executeQuery("SELECT COUNT(*) FROM alumnos WHERE nivel = 'Preparatoria' AND turno = '"
                    + cbxTurno.getSelectedItem().toString() + "'");
            rs.next();
            dato3 = Integer.parseInt(rs.getString(1));
            //System.out.println("Dato3: " + dato3);

        } catch (SQLException ex) {
            Logger.getLogger(PnlProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        generarGrafica();
    }
    DefaultCategoryDataset datosGrafica = new DefaultCategoryDataset();
    int dato1, dato2, dato3;

    public void generarGrafica() {
        JFreeChart grafico = null;

        //dato1 = 10;//Integer.parseInt(jTextField1.getText());
        //dato2 = 13;//Integer.parseInt(jTextField1.getText());
        //dato3 = 15;//Integer.parseInt(jTextField1.getText());
        //int dato4 = 5; Integer.parseInt(jTextField1.getText());
        datosGrafica.addValue(dato1, "Grafica 1", "Primaria");
        datosGrafica.addValue(dato2, "Grafica 1", "Secundaria");
        datosGrafica.addValue(dato3, "Grafica 1", "Preparatoria");
        //datos.addValue(dato4, "Grafica 1", "Cuatro");

        String tipoGrafica = "Barras";
        if (tipoGrafica.equals("Barras")) {
            grafico = ChartFactory.createBarChart(("Mostrando Alumnos por Nivel en el Turno " + turnoActual), "Niveles", "Alumnos", datosGrafica, PlotOrientation.VERTICAL, false, true, false);
        }
        if (tipoGrafica.equals("Lineal")) {
            grafico = ChartFactory.createLineChart("Grafica Prueba", "Eje X", "Eje Y", datosGrafica, PlotOrientation.VERTICAL, true, true, false);
        }
        if (tipoGrafica.equals("Pastel")) {
            DefaultPieDataset datosPie = new DefaultPieDataset();
            datosPie.setValue("Uno", dato1);
            datosPie.setValue("Dos", dato2);
            datosPie.setValue("Tres", dato3);
            //datosPie.setValue("Cuatro", dato4);
            grafico = ChartFactory.createPieChart("Grafica Prueba", datosPie, true, true, false);
        }
        grafico.setBackgroundPaint(new Color(232, 232, 232));

        ChartPanel cPanel = new ChartPanel(grafico);
        //cPanel.setBackground(new Color(232, 232, 232));
        pnlGrafica.removeAll();
        pnlGrafica.add(btnTituloG);
        pnlGrafica.add(jLabel1);
        pnlGrafica.add(cPanel);
        revalidate();
        repaint();

        /*JFrame informacion = new JFrame("Papa");
        informacion.getContentPane().add(cPanel);
        informacion.pack();
        informacion.setVisible(true);*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton12 = new javax.swing.JButton();
        pnlBusqueda = new javax.swing.JPanel();
        cbxNivel = new javax.swing.JComboBox<>();
        cbxGrado = new javax.swing.JComboBox<>();
        cbxTurno = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        chbTurno = new javax.swing.JCheckBox();
        chbBajas = new javax.swing.JCheckBox();
        chbNivel = new javax.swing.JCheckBox();
        chbGrado = new javax.swing.JCheckBox();
        jButton14 = new javax.swing.JButton();
        pnlGrafica = new javax.swing.JPanel();
        btnTituloG = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pnlTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(232, 232, 232));
        setMaximumSize(new java.awt.Dimension(1200, 750));
        setMinimumSize(new java.awt.Dimension(1200, 750));
        setPreferredSize(new java.awt.Dimension(1200, 750));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton12.setBackground(new java.awt.Color(215, 215, 215));
        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton12.setForeground(new java.awt.Color(64, 64, 64));
        jButton12.setText("Resultados de la Busqueda");
        jButton12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton12.setBorderPainted(false);
        jButton12.setFocusPainted(false);
        add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 1140, 30));

        pnlBusqueda.setBackground(new java.awt.Color(232, 232, 232));
        pnlBusqueda.setMaximumSize(new java.awt.Dimension(330, 390));
        pnlBusqueda.setMinimumSize(new java.awt.Dimension(330, 390));
        pnlBusqueda.setPreferredSize(new java.awt.Dimension(330, 390));
        pnlBusqueda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primaria", "Secundaria", "Preparatoria" }));
        cbxNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNivelActionPerformed(evt);
            }
        });
        pnlBusqueda.add(cbxNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 150, -1));

        cbxGrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primer Grado", "Segundo Grado", "Tercer Grado", "Cuarto Grado", "Quinto Grado", "Sexto Grado" }));
        pnlBusqueda.add(cbxGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 150, -1));

        cbxTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino" }));
        pnlBusqueda.add(cbxTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 150, -1));

        btnBuscar.setBackground(new java.awt.Color(2, 62, 138));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlBusqueda.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 240, 40));

        chbTurno.setBackground(new java.awt.Color(232, 232, 232));
        chbTurno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chbTurno.setSelected(true);
        chbTurno.setText(" Turno");
        chbTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbTurnoActionPerformed(evt);
            }
        });
        pnlBusqueda.add(chbTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 70, -1));

        chbBajas.setBackground(new java.awt.Color(232, 232, 232));
        chbBajas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chbBajas.setText(" Buscar Alumnos Eliminados");
        chbBajas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbBajasActionPerformed(evt);
            }
        });
        pnlBusqueda.add(chbBajas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 240, -1));

        chbNivel.setBackground(new java.awt.Color(232, 232, 232));
        chbNivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chbNivel.setSelected(true);
        chbNivel.setText(" Nivel");
        chbNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbNivelActionPerformed(evt);
            }
        });
        pnlBusqueda.add(chbNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 70, -1));

        chbGrado.setBackground(new java.awt.Color(232, 232, 232));
        chbGrado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chbGrado.setSelected(true);
        chbGrado.setText(" Grado");
        chbGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbGradoActionPerformed(evt);
            }
        });
        pnlBusqueda.add(chbGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 70, -1));

        jButton14.setBackground(new java.awt.Color(215, 215, 215));
        jButton14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton14.setForeground(new java.awt.Color(64, 64, 64));
        jButton14.setText("Buscar Grupos");
        jButton14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton14.setBorderPainted(false);
        jButton14.setFocusPainted(false);
        pnlBusqueda.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 240, 30));

        add(pnlBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 390));

        pnlGrafica.setBackground(new java.awt.Color(232, 232, 232));
        pnlGrafica.setMaximumSize(new java.awt.Dimension(870, 390));
        pnlGrafica.setMinimumSize(new java.awt.Dimension(870, 390));
        pnlGrafica.setPreferredSize(new java.awt.Dimension(870, 390));
        pnlGrafica.setLayout(new javax.swing.BoxLayout(pnlGrafica, javax.swing.BoxLayout.PAGE_AXIS));

        btnTituloG.setBackground(new java.awt.Color(215, 215, 215));
        btnTituloG.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTituloG.setForeground(new java.awt.Color(64, 64, 64));
        btnTituloG.setText("Gráfica de la Busqueda");
        btnTituloG.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnTituloG.setBorderPainted(false);
        btnTituloG.setFocusPainted(false);
        btnTituloG.setMaximumSize(new java.awt.Dimension(820, 30));
        btnTituloG.setMinimumSize(new java.awt.Dimension(820, 30));
        btnTituloG.setPreferredSize(new java.awt.Dimension(820, 30));
        pnlGrafica.add(btnTituloG);

        jLabel1.setText("                         ");
        pnlGrafica.add(jLabel1);

        add(pnlGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 830, 370));

        pnlTabla.setBackground(new java.awt.Color(232, 232, 232));
        pnlTabla.setMaximumSize(new java.awt.Dimension(1200, 360));
        pnlTabla.setMinimumSize(new java.awt.Dimension(1200, 360));
        pnlTabla.setPreferredSize(new java.awt.Dimension(1200, 360));
        pnlTabla.setLayout(null);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(1200, 360));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1200, 360));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1200, 360));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(25);
        jTable1.setRowMargin(5);
        jScrollPane1.setViewportView(jTable1);

        pnlTabla.add(jScrollPane1);
        jScrollPane1.setBounds(30, 70, 1140, 260);

        add(pnlTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 1200, 360));
    }// </editor-fold>//GEN-END:initComponents

    private void cbxNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNivelActionPerformed
        cbxGrado.removeAllItems();
        switch (cbxNivel.getSelectedIndex()) {
            case 0:
                cbxGrado.addItem("Primer Grado");
                cbxGrado.addItem("Segundo Grado");
                cbxGrado.addItem("Tercer Grado");
                cbxGrado.addItem("Cuarto Grado");
                cbxGrado.addItem("Quinto Grado");
                cbxGrado.addItem("Sexto Grado");
                break;
            case 1:
                cbxGrado.addItem("Primer Año");
                cbxGrado.addItem("Segundo Año");
                cbxGrado.addItem("Tercer Año");
                break;
            default:
                cbxGrado.addItem("Primer Semestre");
                cbxGrado.addItem("Segundo Semestre");
                cbxGrado.addItem("Tercer Semestre");
                cbxGrado.addItem("Cuarto Semestre");
                cbxGrado.addItem("Quinto Semestre");
                cbxGrado.addItem("Sexto Semestre");
                break;
        }
    }//GEN-LAST:event_cbxNivelActionPerformed
    String turnoActual;
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        if (chbBajas.isSelected()) {
            sql = "SELECT * FROM bajas";
        } else {
            sql = "SELECT * FROM alumnos";
        }
        sql += " WHERE ";

        if (chbNivel.isSelected()) { //Para nivel
            sql += "nivel = '" + cbxNivel.getSelectedItem().toString() + "'";
        }

        if (chbNivel.isSelected() && chbGrado.isSelected()) { //Para grado
            sql += " AND grado = '" + cbxGrado.getSelectedItem().toString() + "'";

        } else if (!chbNivel.isSelected() && chbGrado.isSelected()) {
            sql += "grado = '" + cbxGrado.getSelectedItem().toString() + "'";
        }

        if ((chbNivel.isSelected() || chbGrado.isSelected()) && chbTurno.isSelected()) { //Para turno
            sql += " AND turno = '" + cbxTurno.getSelectedItem().toString() + "'";
            turnoActual = cbxTurno.getSelectedItem().toString();
        } else if (chbTurno.isSelected()) {
            sql += "turno = '" + cbxTurno.getSelectedItem().toString() + "'";
            turnoActual = cbxTurno.getSelectedItem().toString();
        }
        if (!chbTurno.isSelected()) {
            turnoActual = "Ninguno";
        }

        if (!chbNivel.isSelected() && !chbGrado.isSelected() && !chbTurno.isSelected()) {
            JOptionPane.showMessageDialog(null, "Tienes que seleccionar por lo menos 1 filtro", "Error", 0);
        } else {
            verTabla();
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void chbNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbNivelActionPerformed
        if (chbNivel.isSelected()) {
            cbxNivel.setEnabled(true);
        } else {
            cbxNivel.setEnabled(false);
        }

    }//GEN-LAST:event_chbNivelActionPerformed

    private void chbGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbGradoActionPerformed
        if (chbGrado.isSelected()) {
            cbxGrado.setEnabled(true);
        } else {
            cbxGrado.setEnabled(false);
        }
    }//GEN-LAST:event_chbGradoActionPerformed

    private void chbTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbTurnoActionPerformed
        if (chbTurno.isSelected()) {
            cbxTurno.setEnabled(true);
        } else {
            cbxTurno.setEnabled(false);
        }
    }//GEN-LAST:event_chbTurnoActionPerformed

    private void chbBajasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbBajasActionPerformed

    }//GEN-LAST:event_chbBajasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnTituloG;
    private javax.swing.JComboBox<String> cbxGrado;
    private javax.swing.JComboBox<String> cbxNivel;
    private javax.swing.JComboBox<String> cbxTurno;
    private javax.swing.JCheckBox chbBajas;
    private javax.swing.JCheckBox chbGrado;
    private javax.swing.JCheckBox chbNivel;
    private javax.swing.JCheckBox chbTurno;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlGrafica;
    private javax.swing.JPanel pnlTabla;
    // End of variables declaration//GEN-END:variables
}
