/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Inscripciones;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import static Inscripciones.Principal.reg;
import java.awt.Font;
import javax.swing.JOptionPane;
import org.jfree.chart.plot.PiePlot;

/**
 *
 * @author Kevin MG
 */
public class pnlFinanzas extends javax.swing.JPanel {

    /**
     * Creates new form pnlFinanzas
     */
    public pnlFinanzas() {
        initComponents();
        inicializar();
    }

    public void inicializar() {
        actualizarBD();
        obtenerDatos();
        generarGrafica();
        cargarConteos();
    }

    public void actualizarBD() {
        try {//Actualizar numero de inscripciones
            ps = reg.prepareStatement("UPDATE ingresos SET numInsPrimaria = ((SELECT COUNT(*) FROM registroinscripciones WHERE nivel = 'Primaria' AND grado = 'Primer Grado') "
                    + "+ (SELECT COUNT(*) FROM registroinscripciones WHERE nivel = 'Primaria' AND grado = 'Segundo Grado') + (SELECT COUNT(*) FROM registroinscripciones WHERE nivel = 'Primaria' AND grado = 'Tercer Grado'))"
                    + ", numInsPrimaria2 = ((SELECT COUNT(*) FROM registroinscripciones WHERE nivel = 'Primaria' AND grado = 'Cuarto Grado') + (SELECT COUNT(*) FROM registroinscripciones WHERE nivel = 'Primaria' AND grado = 'Quinto Grado') "
                    + "+ (SELECT COUNT(*) FROM registroinscripciones WHERE nivel = 'Primaria' AND grado = 'Sexto Grado')),numInsSecundaria = (SELECT COUNT(*) FROM registroinscripciones WHERE nivel = 'Secundaria'), numInsPreparatoria = (SELECT COUNT(*) "
                    + "FROM registroinscripciones WHERE nivel = 'Preparatoria') WHERE registro = 0;");
            ps.execute();
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al editar los datos del contacto, es posible que falten datos o estos no sean validos");
        }

        try {//Actualizar ganancias
            ps = reg.prepareStatement("UPDATE ingresos SET ingresosPrim = ((SELECT numInsPrimaria FROM ingresos WHERE registro = 0)*1500) + (SELECT numInsPrimaria2 FROM ingresos WHERE registro = 0)*2000, ingresosSecu = (SELECT numInsSecundaria FROM ingresos "
                    + "WHERE registro = 0)*2500, ingresosPrepa = (SELECT numInsPreparatoria FROM ingresos WHERE registro = 0)*3000 WHERE registro = 0;");
            ps.execute();
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al editar los datos del contacto, es posible que falten datos o estos no sean validos");
        }
        
        try {//Actualizar total
            ps = reg.prepareStatement("UPDATE ingresos SET total = `ingresosPrim` + `ingresosSecu` + `ingresosPrepa` WHERE registro = 0;");
            ps.execute();
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al editar los datos del contacto, es posible que falten datos o estos no sean validos");
        }

    }

    public void cargarConteos() {
        try {
            rs = sentencia.executeQuery("SELECT numInsPrimaria + numInsPrimaria2 FROM ingresos");
            rs.next();
            btnConteoPrim.setText(rs.getString(1));

            rs = sentencia.executeQuery("SELECT numInsSecundaria FROM ingresos");
            rs.next();
            btnConteoSecu.setText(rs.getString(1));

            rs = sentencia.executeQuery("SELECT numInsPreparatoria FROM ingresos");
            rs.next();
            btnConteoPrepa.setText(rs.getString(1));

            rs = sentencia.executeQuery("SELECT total FROM ingresos");
            rs.next();
            lblIngresos.setText("$" + rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(pnlFinanzas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static String sql;
    static Conectar con = new Conectar();
    static PreparedStatement ps;
    static Statement sentencia;
    static ResultSet rs;

    public void obtenerDatos() {
        try {
            sentencia = reg.createStatement();
            rs = sentencia.executeQuery("SELECT ingresosPrim FROM ingresos");
            rs.next();
            dato1 = rs.getInt(1);

            rs = sentencia.executeQuery("SELECT ingresosSecu FROM ingresos");
            rs.next();
            dato2 = rs.getInt(1);

            rs = sentencia.executeQuery("SELECT ingresosPrepa FROM ingresos");
            rs.next();
            dato3 = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(pnlFinanzas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    DefaultCategoryDataset datosGrafica = new DefaultCategoryDataset();
    int dato1, dato2, dato3;

    public void generarGrafica() {
        JFreeChart grafico = null;

        datosGrafica.addValue(dato1, "Grafica 1", "Primaria");
        datosGrafica.addValue(dato2, "Grafica 1", "Secundaria");
        datosGrafica.addValue(dato3, "Grafica 1", "Preparatoria");
        //datos.addValue(dato4, "Grafica 1", "Cuatro");

        String tipoGrafica = "Pastel";

        if (tipoGrafica.equals("Pastel")) {
            DefaultPieDataset datosPie = new DefaultPieDataset();
            datosPie.setValue(("Primaria: $" + dato1), dato1);
            datosPie.setValue(("Secundaria: $" + dato2), dato2);
            datosPie.setValue(("Preparatoria: $" + dato3), dato3);
            //datosPie.setValue("Cuatro", dato4);
            grafico = ChartFactory.createPieChart("Distribución de Ingresos por Nivel", datosPie, true, true, false);
        }
        grafico.setBackgroundPaint(new Color(232, 232, 232));
        PiePlot plot = (PiePlot) grafico.getPlot();
        plot.setLabelBackgroundPaint(new Color(255, 255, 255));
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setBackgroundPaint(new Color(232, 232, 232));

        ChartPanel cPanel = new ChartPanel(grafico);
        //cPanel.setBackground(new Color(232, 232, 232));
        pnlSuperior.removeAll();
        pnlSuperior.add(cPanel);
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

        pnlSuperior = new javax.swing.JPanel();
        pnlInferior = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        btnTituloG1 = new javax.swing.JButton();
        btnBuscar3 = new javax.swing.JButton();
        btnBuscar4 = new javax.swing.JButton();
        btnConteoSecu = new javax.swing.JButton();
        btnConteoPrim = new javax.swing.JButton();
        btnConteoPrepa = new javax.swing.JButton();
        btnTituloG2 = new javax.swing.JButton();
        lblIngresos = new javax.swing.JButton();

        setBackground(new java.awt.Color(232, 232, 232));
        setMaximumSize(new java.awt.Dimension(1200, 750));
        setMinimumSize(new java.awt.Dimension(1200, 750));
        setPreferredSize(new java.awt.Dimension(1200, 750));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSuperior.setBackground(new java.awt.Color(232, 232, 232));
        pnlSuperior.setLayout(new javax.swing.BoxLayout(pnlSuperior, javax.swing.BoxLayout.PAGE_AXIS));
        add(pnlSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 1110, 500));

        pnlInferior.setBackground(new java.awt.Color(232, 232, 232));
        pnlInferior.setMaximumSize(new java.awt.Dimension(1200, 210));

        btnBuscar.setBackground(new java.awt.Color(255, 0, 51));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Primaria");
        btnBuscar.setFocusPainted(false);

        btnTituloG1.setBackground(new java.awt.Color(215, 215, 215));
        btnTituloG1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTituloG1.setForeground(new java.awt.Color(64, 64, 64));
        btnTituloG1.setText("Recuento de Inscripciones");
        btnTituloG1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnTituloG1.setBorderPainted(false);
        btnTituloG1.setFocusPainted(false);
        btnTituloG1.setMaximumSize(new java.awt.Dimension(820, 30));
        btnTituloG1.setMinimumSize(new java.awt.Dimension(820, 30));
        btnTituloG1.setPreferredSize(new java.awt.Dimension(820, 30));

        btnBuscar3.setBackground(new java.awt.Color(61, 61, 255));
        btnBuscar3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnBuscar3.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar3.setText("Secundaria");
        btnBuscar3.setFocusPainted(false);

        btnBuscar4.setBackground(new java.awt.Color(0, 209, 0));
        btnBuscar4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnBuscar4.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar4.setText("Preparatoria");
        btnBuscar4.setFocusPainted(false);

        btnConteoSecu.setBackground(new java.awt.Color(0, 0, 198));
        btnConteoSecu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnConteoSecu.setForeground(new java.awt.Color(255, 255, 255));
        btnConteoSecu.setText("42");
        btnConteoSecu.setFocusPainted(false);

        btnConteoPrim.setBackground(new java.awt.Color(188, 0, 37));
        btnConteoPrim.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnConteoPrim.setForeground(new java.awt.Color(255, 255, 255));
        btnConteoPrim.setText("99");
        btnConteoPrim.setFocusPainted(false);

        btnConteoPrepa.setBackground(new java.awt.Color(0, 163, 0));
        btnConteoPrepa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnConteoPrepa.setForeground(new java.awt.Color(255, 255, 255));
        btnConteoPrepa.setText("323");
        btnConteoPrepa.setFocusPainted(false);

        btnTituloG2.setBackground(new java.awt.Color(215, 215, 215));
        btnTituloG2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTituloG2.setForeground(new java.awt.Color(64, 64, 64));
        btnTituloG2.setText("Ingresos Totales");
        btnTituloG2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnTituloG2.setBorderPainted(false);
        btnTituloG2.setFocusPainted(false);
        btnTituloG2.setMaximumSize(new java.awt.Dimension(820, 30));
        btnTituloG2.setMinimumSize(new java.awt.Dimension(820, 30));
        btnTituloG2.setPreferredSize(new java.awt.Dimension(820, 30));

        lblIngresos.setBackground(new java.awt.Color(0, 51, 153));
        lblIngresos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblIngresos.setForeground(new java.awt.Color(255, 255, 255));
        lblIngresos.setText("$ INGRESOS");
        lblIngresos.setFocusPainted(false);
        lblIngresos.setFocusable(false);
        lblIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblIngresosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInferiorLayout = new javax.swing.GroupLayout(pnlInferior);
        pnlInferior.setLayout(pnlInferiorLayout);
        pnlInferiorLayout.setHorizontalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInferiorLayout.createSequentialGroup()
                .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlInferiorLayout.createSequentialGroup()
                        .addContainerGap(52, Short.MAX_VALUE)
                        .addComponent(btnTituloG1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnTituloG2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInferiorLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInferiorLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btnConteoPrim, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(btnConteoSecu, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99)
                                .addComponent(btnConteoPrepa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInferiorLayout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)))
                .addGap(48, 48, 48))
        );
        pnlInferiorLayout.setVerticalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInferiorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTituloG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTituloG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInferiorLayout.createSequentialGroup()
                        .addComponent(lblIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInferiorLayout.createSequentialGroup()
                        .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConteoSecu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConteoPrim, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConteoPrepa, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61))))
        );

        add(pnlInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void lblIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblIngresosActionPerformed
        inicializar();
    }//GEN-LAST:event_lblIngresosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar3;
    private javax.swing.JButton btnBuscar4;
    private javax.swing.JButton btnConteoPrepa;
    private javax.swing.JButton btnConteoPrim;
    private javax.swing.JButton btnConteoSecu;
    private javax.swing.JButton btnTituloG1;
    private javax.swing.JButton btnTituloG2;
    private javax.swing.JButton lblIngresos;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JPanel pnlSuperior;
    // End of variables declaration//GEN-END:variables
}
