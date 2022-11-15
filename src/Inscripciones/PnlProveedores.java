/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Inscripciones;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;

/**
 *
 * @author Kevin MG
 */
public class PnlProveedores extends javax.swing.JPanel {

    /**
     * Creates new form pnlFinanzas
     */
    public PnlProveedores() {
        initComponents();
    }

    static String sql;
    static Conectar con = new Conectar();
    static PreparedStatement ps;
    static Statement sentencia;
    static ResultSet rs;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        btnVerEmpleados1 = new javax.swing.JButton();
        btnVerEmpleados2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pnlProv1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        pnlProv2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        pnlProv3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        pnlProv4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        pnlProv5 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        pnlProv6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();

        setBackground(new java.awt.Color(232, 232, 232));
        setMaximumSize(new java.awt.Dimension(1200, 780));
        setMinimumSize(new java.awt.Dimension(1200, 780));
        setPreferredSize(new java.awt.Dimension(1200, 780));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Buscar");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 70, -1));

        btnVerEmpleados1.setBackground(new java.awt.Color(2, 62, 138));
        btnVerEmpleados1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVerEmpleados1.setForeground(new java.awt.Color(255, 255, 255));
        btnVerEmpleados1.setText("E");
        btnVerEmpleados1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnVerEmpleados1.setFocusPainted(false);
        btnVerEmpleados1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVerEmpleados1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVerEmpleados1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerEmpleados1ActionPerformed(evt);
            }
        });
        add(btnVerEmpleados1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 40, 40, 40));

        btnVerEmpleados2.setBackground(new java.awt.Color(2, 62, 138));
        btnVerEmpleados2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVerEmpleados2.setForeground(new java.awt.Color(255, 255, 255));
        btnVerEmpleados2.setText("A");
        btnVerEmpleados2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnVerEmpleados2.setFocusPainted(false);
        btnVerEmpleados2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVerEmpleados2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVerEmpleados2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerEmpleados2ActionPerformed(evt);
            }
        });
        add(btnVerEmpleados2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 40, 40, 40));

        jTextField1.setBackground(new java.awt.Color(215, 215, 215));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(142, 142, 142));
        jTextField1.setText("ID del Proveedor...");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 16, 1, 1));
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 270, 40));

        jLabel7.setBackground(new java.awt.Color(215, 215, 215));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nuestros proveedores");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel7.setOpaque(true);
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 600, 40));

        pnlProv1.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 4.png"))); // NOI18N
        pnlProv1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel33.setBackground(new java.awt.Color(211, 211, 211));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(64, 64, 64));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("  ");
        jLabel33.setOpaque(true);
        pnlProv1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(64, 64, 64));
        jLabel17.setText("Nombre");
        pnlProv1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(64, 64, 64));
        jLabel34.setText("ID");
        pnlProv1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel35.setBackground(new java.awt.Color(211, 211, 211));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(64, 64, 64));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("  ");
        jLabel35.setOpaque(true);
        pnlProv1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 130, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(64, 64, 64));
        jLabel36.setText("RFC");
        pnlProv1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel37.setBackground(new java.awt.Color(211, 211, 211));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(64, 64, 64));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("  ");
        jLabel37.setOpaque(true);
        pnlProv1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 130, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(64, 64, 64));
        jLabel38.setText("Teléfono");
        pnlProv1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel39.setBackground(new java.awt.Color(211, 211, 211));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(64, 64, 64));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("  ");
        jLabel39.setOpaque(true);
        pnlProv1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 170, -1));

        jButton4.setBackground(new java.awt.Color(211, 211, 211));
        jButton4.setForeground(new java.awt.Color(64, 64, 64));
        jButton4.setText("Copy");
        jButton4.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlProv1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 60, -1));

        add(pnlProv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 360, 255));

        pnlProv2.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 4.png"))); // NOI18N
        pnlProv2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel66.setBackground(new java.awt.Color(211, 211, 211));
        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(64, 64, 64));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("  ");
        jLabel66.setOpaque(true);
        pnlProv2.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(64, 64, 64));
        jLabel14.setText("Nombre");
        pnlProv2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(64, 64, 64));
        jLabel67.setText("ID");
        pnlProv2.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel68.setBackground(new java.awt.Color(211, 211, 211));
        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(64, 64, 64));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("  ");
        jLabel68.setOpaque(true);
        pnlProv2.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 130, -1));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(64, 64, 64));
        jLabel69.setText("RFC");
        pnlProv2.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel70.setBackground(new java.awt.Color(211, 211, 211));
        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(64, 64, 64));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("  ");
        jLabel70.setOpaque(true);
        pnlProv2.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 130, -1));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(64, 64, 64));
        jLabel71.setText("Teléfono");
        pnlProv2.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel72.setBackground(new java.awt.Color(211, 211, 211));
        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(64, 64, 64));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("  ");
        jLabel72.setOpaque(true);
        pnlProv2.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 170, -1));

        jButton8.setBackground(new java.awt.Color(211, 211, 211));
        jButton8.setForeground(new java.awt.Color(64, 64, 64));
        jButton8.setText("Copy");
        jButton8.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlProv2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 60, -1));

        add(pnlProv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 360, 255));

        pnlProv3.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 4.png"))); // NOI18N
        pnlProv3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel73.setBackground(new java.awt.Color(211, 211, 211));
        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(64, 64, 64));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("  ");
        jLabel73.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel73.setOpaque(true);
        pnlProv3.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(64, 64, 64));
        jLabel15.setText("Nombre");
        pnlProv3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(64, 64, 64));
        jLabel74.setText("ID");
        pnlProv3.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel75.setBackground(new java.awt.Color(211, 211, 211));
        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(64, 64, 64));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("  ");
        jLabel75.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel75.setOpaque(true);
        pnlProv3.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 130, -1));

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(64, 64, 64));
        jLabel76.setText("RFC");
        pnlProv3.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel77.setBackground(new java.awt.Color(211, 211, 211));
        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(64, 64, 64));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("  ");
        jLabel77.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel77.setOpaque(true);
        pnlProv3.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 130, -1));

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(64, 64, 64));
        jLabel78.setText("Teléfono");
        pnlProv3.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel79.setBackground(new java.awt.Color(211, 211, 211));
        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(64, 64, 64));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("  ");
        jLabel79.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel79.setOpaque(true);
        pnlProv3.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 170, -1));

        jButton9.setBackground(new java.awt.Color(211, 211, 211));
        jButton9.setForeground(new java.awt.Color(64, 64, 64));
        jButton9.setText("Copy");
        jButton9.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlProv3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 60, -1));

        add(pnlProv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 360, 255));

        pnlProv4.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 4.png"))); // NOI18N
        pnlProv4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel18.setBackground(new java.awt.Color(211, 211, 211));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(64, 64, 64));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("  ");
        jLabel18.setOpaque(true);
        pnlProv4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(64, 64, 64));
        jLabel13.setText("Nombre");
        pnlProv4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(64, 64, 64));
        jLabel19.setText("ID");
        pnlProv4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel20.setBackground(new java.awt.Color(211, 211, 211));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(64, 64, 64));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("  ");
        jLabel20.setOpaque(true);
        pnlProv4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 130, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(64, 64, 64));
        jLabel21.setText("RFC");
        pnlProv4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel22.setBackground(new java.awt.Color(211, 211, 211));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(64, 64, 64));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("  ");
        jLabel22.setOpaque(true);
        pnlProv4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 130, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(64, 64, 64));
        jLabel23.setText("Teléfono");
        pnlProv4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel24.setBackground(new java.awt.Color(211, 211, 211));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(64, 64, 64));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("  ");
        jLabel24.setOpaque(true);
        pnlProv4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 170, -1));

        jButton2.setBackground(new java.awt.Color(211, 211, 211));
        jButton2.setForeground(new java.awt.Color(64, 64, 64));
        jButton2.setText("Copy");
        jButton2.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlProv4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 60, -1));

        add(pnlProv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 360, 255));

        pnlProv5.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 4.png"))); // NOI18N
        pnlProv5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel87.setBackground(new java.awt.Color(211, 211, 211));
        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(64, 64, 64));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("  ");
        jLabel87.setOpaque(true);
        pnlProv5.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(64, 64, 64));
        jLabel26.setText("Nombre");
        pnlProv5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(64, 64, 64));
        jLabel88.setText("ID");
        pnlProv5.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel89.setBackground(new java.awt.Color(211, 211, 211));
        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(64, 64, 64));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("  ");
        jLabel89.setOpaque(true);
        pnlProv5.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 130, -1));

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(64, 64, 64));
        jLabel90.setText("RFC");
        pnlProv5.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel91.setBackground(new java.awt.Color(211, 211, 211));
        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(64, 64, 64));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("  ");
        jLabel91.setOpaque(true);
        pnlProv5.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 130, -1));

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(64, 64, 64));
        jLabel92.setText("Teléfono");
        pnlProv5.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel93.setBackground(new java.awt.Color(211, 211, 211));
        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(64, 64, 64));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("  ");
        jLabel93.setOpaque(true);
        pnlProv5.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 170, -1));

        jButton11.setBackground(new java.awt.Color(211, 211, 211));
        jButton11.setForeground(new java.awt.Color(64, 64, 64));
        jButton11.setText("Copy");
        jButton11.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlProv5.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 60, -1));

        add(pnlProv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 360, 255));

        pnlProv6.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1x/Recurso 4.png"))); // NOI18N
        pnlProv6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel80.setBackground(new java.awt.Color(211, 211, 211));
        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(64, 64, 64));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("  ");
        jLabel80.setOpaque(true);
        pnlProv6.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(64, 64, 64));
        jLabel16.setText("Nombre");
        pnlProv6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel81.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(64, 64, 64));
        jLabel81.setText("ID");
        pnlProv6.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel82.setBackground(new java.awt.Color(211, 211, 211));
        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(64, 64, 64));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("  ");
        jLabel82.setOpaque(true);
        pnlProv6.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 130, -1));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(64, 64, 64));
        jLabel83.setText("RFC");
        pnlProv6.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel84.setBackground(new java.awt.Color(211, 211, 211));
        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(64, 64, 64));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("  ");
        jLabel84.setOpaque(true);
        pnlProv6.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 130, -1));

        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(64, 64, 64));
        jLabel85.setText("Teléfono");
        pnlProv6.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel86.setBackground(new java.awt.Color(211, 211, 211));
        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(64, 64, 64));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("  ");
        jLabel86.setOpaque(true);
        pnlProv6.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 170, -1));

        jButton12.setBackground(new java.awt.Color(211, 211, 211));
        jButton12.setForeground(new java.awt.Color(64, 64, 64));
        jButton12.setText("Copy");
        jButton12.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlProv6.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 60, -1));

        add(pnlProv6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 420, 360, 255));

        btnAnterior.setBackground(new java.awt.Color(215, 215, 215));
        btnAnterior.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAnterior.setForeground(new java.awt.Color(64, 64, 64));
        btnAnterior.setText("<");
        btnAnterior.setPreferredSize(new java.awt.Dimension(72, 25));
        add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 710, 50, 50));

        btnSiguiente.setBackground(new java.awt.Color(215, 215, 215));
        btnSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(64, 64, 64));
        btnSiguiente.setText(">");
        btnSiguiente.setPreferredSize(new java.awt.Dimension(72, 25));
        add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 710, 50, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerEmpleados1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEmpleados1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerEmpleados1ActionPerformed

    private void btnVerEmpleados2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEmpleados2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerEmpleados2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnVerEmpleados1;
    private javax.swing.JButton btnVerEmpleados2;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel pnlProv1;
    private javax.swing.JPanel pnlProv2;
    private javax.swing.JPanel pnlProv3;
    private javax.swing.JPanel pnlProv4;
    private javax.swing.JPanel pnlProv5;
    private javax.swing.JPanel pnlProv6;
    // End of variables declaration//GEN-END:variables
}
