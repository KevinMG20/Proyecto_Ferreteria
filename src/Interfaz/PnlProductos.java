package Interfaz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
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

        //verTabla();
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
    static PreparedStatement ps;
    static Statement sentencia;
    static ResultSet rs;
    String[] datos;
    DefaultTableModel miModelo;

    /*public void verTabla() {
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
    }*/
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
        pnlProducto.removeAll();       
        pnlProducto.add(jLabel1);
        pnlProducto.add(cPanel);
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCategorias = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        pnlProducto = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        pnlTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(232, 232, 232));
        setMaximumSize(new java.awt.Dimension(1200, 780));
        setMinimumSize(new java.awt.Dimension(1200, 780));
        setPreferredSize(new java.awt.Dimension(1200, 780));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCategorias.setBackground(new java.awt.Color(232, 232, 232));
        pnlCategorias.setMaximumSize(new java.awt.Dimension(330, 390));
        pnlCategorias.setMinimumSize(new java.awt.Dimension(330, 390));
        pnlCategorias.setPreferredSize(new java.awt.Dimension(330, 390));
        pnlCategorias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(232, 232, 232));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 6.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setFocusPainted(false);
        pnlCategorias.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 120, 120));

        jButton4.setBackground(new java.awt.Color(232, 232, 232));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 7.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setFocusPainted(false);
        pnlCategorias.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 120, 120));

        jButton5.setBackground(new java.awt.Color(232, 232, 232));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 10.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setFocusPainted(false);
        pnlCategorias.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 245, 120, 120));

        jButton6.setBackground(new java.awt.Color(232, 232, 232));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 9.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setFocusPainted(false);
        pnlCategorias.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 245, 120, 120));

        jButton7.setBackground(new java.awt.Color(232, 232, 232));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 8.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setFocusPainted(false);
        pnlCategorias.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 245, 120, 120));

        jLabel2.setBackground(new java.awt.Color(215, 215, 215));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(64, 64, 64));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Miscelaneos");
        jLabel2.setOpaque(true);
        pnlCategorias.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, 121, -1));

        jLabel3.setBackground(new java.awt.Color(215, 215, 215));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(64, 64, 64));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Equipamento");
        jLabel3.setOpaque(true);
        pnlCategorias.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 121, -1));

        jLabel4.setBackground(new java.awt.Color(215, 215, 215));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(64, 64, 64));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Accesorios");
        jLabel4.setOpaque(true);
        pnlCategorias.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 121, -1));

        jLabel22.setBackground(new java.awt.Color(215, 215, 215));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(64, 64, 64));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Categorias");
        jLabel22.setOpaque(true);
        pnlCategorias.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 590, 33));

        jLabel5.setBackground(new java.awt.Color(215, 215, 215));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(64, 64, 64));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Herramientas");
        jLabel5.setOpaque(true);
        pnlCategorias.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 195, 121, -1));

        jLabel7.setBackground(new java.awt.Color(215, 215, 215));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(64, 64, 64));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Medición");
        jLabel7.setOpaque(true);
        pnlCategorias.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 195, 121, -1));

        jButton8.setBackground(new java.awt.Color(232, 232, 232));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 5.png"))); // NOI18N
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setFocusPainted(false);
        pnlCategorias.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 123, 123));

        jLabel14.setBackground(new java.awt.Color(215, 215, 215));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(64, 64, 64));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Escaleras");
        jLabel14.setOpaque(true);
        pnlCategorias.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 195, 121, -1));

        add(pnlCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 410));

        pnlProducto.setBackground(new java.awt.Color(232, 232, 232));
        pnlProducto.setMaximumSize(new java.awt.Dimension(870, 390));
        pnlProducto.setMinimumSize(new java.awt.Dimension(870, 390));
        pnlProducto.setPreferredSize(new java.awt.Dimension(870, 390));
        pnlProducto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("                         ");
        pnlProducto.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel21.setBackground(new java.awt.Color(215, 215, 215));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(64, 64, 64));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Datos del Producto");
        jLabel21.setOpaque(true);
        pnlProducto.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 440, 33));

        jButton2.setBackground(new java.awt.Color(232, 232, 232));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 5.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        pnlProducto.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 123, 123));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(64, 64, 64));
        jLabel8.setText("Categoria");
        pnlProducto.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(64, 64, 64));
        jLabel9.setText("ID");
        pnlProducto.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(64, 64, 64));
        jLabel10.setText("Existencias");
        pnlProducto.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(64, 64, 64));
        jLabel11.setText("Marca");
        pnlProducto.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(64, 64, 64));
        jLabel12.setText("Nombre");
        pnlProducto.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(64, 64, 64));
        jLabel13.setText("Precio");
        pnlProducto.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jLabel6.setBackground(new java.awt.Color(215, 215, 215));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(64, 64, 64));
        jLabel6.setText("  ");
        jLabel6.setOpaque(true);
        pnlProducto.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 340, -1));

        jLabel15.setBackground(new java.awt.Color(215, 215, 215));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(64, 64, 64));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("  ");
        jLabel15.setOpaque(true);
        pnlProducto.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 240, -1));

        jLabel17.setBackground(new java.awt.Color(215, 215, 215));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(64, 64, 64));
        jLabel17.setText("  ");
        jLabel17.setOpaque(true);
        pnlProducto.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 340, -1));

        jLabel18.setBackground(new java.awt.Color(215, 215, 215));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(64, 64, 64));
        jLabel18.setText("  ");
        jLabel18.setOpaque(true);
        pnlProducto.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 340, -1));

        jLabel19.setBackground(new java.awt.Color(215, 215, 215));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(64, 64, 64));
        jLabel19.setText("  ");
        jLabel19.setOpaque(true);
        pnlProducto.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 210, -1));

        add(pnlProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 530, 410));

        pnlTabla.setBackground(new java.awt.Color(232, 232, 232));
        pnlTabla.setMaximumSize(new java.awt.Dimension(1200, 360));
        pnlTabla.setMinimumSize(new java.awt.Dimension(1200, 360));
        pnlTabla.setPreferredSize(new java.awt.Dimension(1200, 360));
        pnlTabla.setLayout(null);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(1200, 360));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1200, 360));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1200, 360));

        jTable1.setBackground(new java.awt.Color(215, 215, 215));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Categoria", "Marca", "Precio", "Existencias"
            }
        ));
        jTable1.setRowHeight(25);
        jTable1.setRowMargin(5);
        jScrollPane1.setViewportView(jTable1);

        pnlTabla.add(jScrollPane1);
        jScrollPane1.setBounds(30, 60, 1140, 280);

        jLabel20.setBackground(new java.awt.Color(215, 215, 215));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(64, 64, 64));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Productos Disponibles");
        jLabel20.setOpaque(true);
        pnlTabla.add(jLabel20);
        jLabel20.setBounds(30, 10, 1140, 33);

        add(pnlTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 1200, 370));
    }// </editor-fold>//GEN-END:initComponents
    String turnoActual;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel pnlCategorias;
    private javax.swing.JPanel pnlProducto;
    private javax.swing.JPanel pnlTabla;
    // End of variables declaration//GEN-END:variables
}
