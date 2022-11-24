/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

import Objetos.Proveedores;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Kevin MG
 */
public class PnlProveedores extends javax.swing.JPanel {

    Proveedores provedAux;
    int panelSeleccionado, anteriorSeleccionado;
    Border bordeVacio = new EmptyBorder(1, 8, 1, 8);
    Border bordeLineal = new LineBorder(Color.BLACK);
    Border pnlBorder = new LineBorder(new Color(2, 62, 138), 4);
    boolean edicion = false;
    String datosTemp[]; //Sirve para almacenar los datos del panel cuando se va a editar, en caso de cancelar la edicion, se restauran los datos guardados
    boolean quiereEliminar = false;
    String proveedorSeleccionado;

    public PnlProveedores() {
        initComponents();
        provedAux = new Proveedores();
        cargarProveedores();
    }

    public void editarProveedor() {
        //System.out.println("Panel seleccionado: " + panelSeleccionado);
        datosTemp = new String[4];
        edicion = true;

        switch (panelSeleccionado) {
            case 1:
                txtRFC.setEditable(true);
                txtNombre.setEditable(true);
                txtTelefono.setEditable(true);
                txtRFC.setBorder(bordeLineal);
                txtNombre.setBorder(bordeLineal);
                txtTelefono.setBorder(bordeLineal);
                anteriorSeleccionado = 1;

                datosTemp[0] = txtID.getText();
                datosTemp[1] = txtRFC.getText();
                datosTemp[2] = txtNombre.getText();
                datosTemp[3] = txtTelefono.getText();
                break;
            case 2:
                txtRFC1.setEditable(true);
                txtNombre1.setEditable(true);
                txtTelefono1.setEditable(true);
                txtRFC1.setBorder(bordeLineal);
                txtNombre1.setBorder(bordeLineal);
                txtTelefono1.setBorder(bordeLineal);
                anteriorSeleccionado = 2;

                datosTemp[0] = txtID1.getText();
                datosTemp[1] = txtRFC1.getText();
                datosTemp[2] = txtNombre1.getText();
                datosTemp[3] = txtTelefono1.getText();
                break;
            case 3:
                txtRFC2.setEditable(true);
                txtNombre2.setEditable(true);
                txtTelefono2.setEditable(true);
                txtRFC2.setBorder(bordeLineal);
                txtNombre2.setBorder(bordeLineal);
                txtTelefono2.setBorder(bordeLineal);
                anteriorSeleccionado = 3;

                datosTemp[0] = txtID2.getText();
                datosTemp[1] = txtRFC2.getText();
                datosTemp[2] = txtNombre2.getText();
                datosTemp[3] = txtTelefono2.getText();
                break;
            case 4:
                txtRFC3.setEditable(true);
                txtNombre3.setEditable(true);
                txtTelefono3.setEditable(true);
                txtRFC3.setBorder(bordeLineal);
                txtNombre3.setBorder(bordeLineal);
                txtTelefono3.setBorder(bordeLineal);
                anteriorSeleccionado = 4;

                datosTemp[0] = txtID3.getText();
                datosTemp[1] = txtRFC3.getText();
                datosTemp[2] = txtNombre3.getText();
                datosTemp[3] = txtTelefono3.getText();
                break;
            case 5:
                txtRFC4.setEditable(true);
                txtNombre4.setEditable(true);
                txtTelefono4.setEditable(true);
                txtRFC4.setBorder(bordeLineal);
                txtNombre4.setBorder(bordeLineal);
                txtTelefono4.setBorder(bordeLineal);
                anteriorSeleccionado = 5;

                datosTemp[0] = txtID4.getText();
                datosTemp[1] = txtRFC4.getText();
                datosTemp[2] = txtNombre4.getText();
                datosTemp[3] = txtTelefono4.getText();
                break;
            case 6:
                txtRFC5.setEditable(true);
                txtNombre5.setEditable(true);
                txtTelefono5.setEditable(true);
                txtRFC5.setBorder(bordeLineal);
                txtNombre5.setBorder(bordeLineal);
                txtTelefono5.setBorder(bordeLineal);
                anteriorSeleccionado = 6;

                datosTemp[0] = txtID5.getText();
                datosTemp[1] = txtRFC5.getText();
                datosTemp[2] = txtNombre5.getText();
                datosTemp[3] = txtTelefono5.getText();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Primero seleccione a un proveedor");
                btnAgregar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoAgregarBlanco.png")));
                btnEditar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoEditarBlanco.png")));
                edicion = false;
                break;
        }

    }

    public void bloquerEdicionPanel() {
        //System.out.println("Anterior seleccionado: " + anteriorSeleccionado);
        try {
            switch (anteriorSeleccionado) {
                case 1:
                    txtRFC.setEditable(false);
                    txtNombre.setEditable(false);
                    txtTelefono.setEditable(false);
                    txtRFC.setBorder(bordeVacio);
                    txtNombre.setBorder(bordeVacio);
                    txtTelefono.setBorder(bordeVacio);
                    pnlProv1.setBorder(bordeVacio);

                    txtID.setText(datosTemp[0]);
                    txtRFC.setText(datosTemp[1]);
                    txtNombre.setText(datosTemp[2]);
                    txtTelefono.setText(datosTemp[3]);
                    break;
                case 2:
                    txtRFC1.setEditable(false);
                    txtNombre1.setEditable(false);
                    txtTelefono1.setEditable(false);
                    txtRFC1.setBorder(bordeVacio);
                    txtNombre1.setBorder(bordeVacio);
                    txtTelefono1.setBorder(bordeVacio);
                    pnlProv2.setBorder(bordeVacio);

                    txtID1.setText(datosTemp[0]);
                    txtRFC1.setText(datosTemp[1]);
                    txtNombre1.setText(datosTemp[2]);
                    txtTelefono1.setText(datosTemp[3]);
                    break;
                case 3:
                    txtRFC2.setEditable(false);
                    txtNombre2.setEditable(false);
                    txtTelefono2.setEditable(false);
                    txtRFC2.setBorder(bordeVacio);
                    txtNombre2.setBorder(bordeVacio);
                    txtTelefono2.setBorder(bordeVacio);
                    pnlProv3.setBorder(bordeVacio);

                    txtID2.setText(datosTemp[0]);
                    txtRFC2.setText(datosTemp[1]);
                    txtNombre2.setText(datosTemp[2]);
                    txtTelefono2.setText(datosTemp[3]);
                    break;
                case 4:
                    txtRFC3.setEditable(false);
                    txtNombre3.setEditable(false);
                    txtTelefono3.setEditable(false);
                    txtRFC3.setBorder(bordeVacio);
                    txtNombre3.setBorder(bordeVacio);
                    txtTelefono3.setBorder(bordeVacio);
                    pnlProv4.setBorder(bordeVacio);

                    txtID3.setText(datosTemp[0]);
                    txtRFC3.setText(datosTemp[1]);
                    txtNombre3.setText(datosTemp[2]);
                    txtTelefono3.setText(datosTemp[3]);
                    break;
                case 5:
                    txtRFC4.setEditable(false);
                    txtNombre4.setEditable(false);
                    txtTelefono4.setEditable(false);
                    txtRFC4.setBorder(bordeVacio);
                    txtNombre4.setBorder(bordeVacio);
                    txtTelefono4.setBorder(bordeVacio);
                    pnlProv5.setBorder(bordeVacio);

                    txtID4.setText(datosTemp[0]);
                    txtRFC4.setText(datosTemp[1]);
                    txtNombre4.setText(datosTemp[2]);
                    txtTelefono4.setText(datosTemp[3]);
                    break;
                case 6:
                    txtRFC5.setEditable(false);
                    txtNombre5.setEditable(false);
                    txtTelefono5.setEditable(false);
                    txtRFC5.setBorder(bordeVacio);
                    txtNombre5.setBorder(bordeVacio);
                    txtTelefono5.setBorder(bordeVacio);
                    pnlProv6.setBorder(bordeVacio);

                    txtID5.setText(datosTemp[0]);
                    txtRFC5.setText(datosTemp[1]);
                    txtNombre5.setText(datosTemp[2]);
                    txtTelefono5.setText(datosTemp[3]);
                    break;
                default:
            }
        } catch (Exception ex) { //Si da una excepcion sera en la zona de los datos, seguramente NullPointerException, por lo que simplemente ignorarlo

        }
        edicion = false;
    }

    public void prepararParaAgregar() {
        paginaActual = 1;
        indexLista = 0;

        pnlProv2.setVisible(false);
        pnlProv3.setVisible(false);
        pnlProv4.setVisible(false);
        pnlProv5.setVisible(false);
        pnlProv6.setVisible(false);

        txtID.setText("");
        txtRFC.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");

        panelSeleccionado = 1;
        editarProveedor();
    }

    public void agregarProveedor() {
        try {
            provedAux = new Proveedores(provedAux.generarID(txtNombre.getText()), txtNombre.getText(), txtRFC.getText(), txtTelefono.getText());
            provedAux.registrarProveedor(provedAux);
            bloquerEdicionPanel();
            datosTemp = new String[4];
            indexLista = 0;
            cargarProveedores();
            btnAgregar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoAgregarBlanco.png")));
        } catch (Exception ex) {

        }
    }

    public boolean buscarProveedor() {
        provedAux = provedAux.consultarProveedor(txtBuscar.getText());

        if (provedAux != null) {
            paginaActual = 1;
            indexLista = 0;
            totalPaginas = 1;

            pnlProv2.setVisible(false);
            pnlProv3.setVisible(false);
            pnlProv4.setVisible(false);
            pnlProv5.setVisible(false);
            pnlProv6.setVisible(false);

            txtID.setText(provedAux.getIdProveedor());
            txtRFC.setText(provedAux.getRfc());
            txtNombre.setText(provedAux.getNombre());
            txtTelefono.setText(provedAux.getTelefono());

            indexLista++;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado ningún registro con ese ID");
            provedAux = new Proveedores();
            return false;
        }
    }

    ArrayList<Proveedores> listaDeProveedores;
    int indexLista = 0;
    int paginaActual = 1;
    int totalPaginas = 1;

    public void cargarProveedores() {
        listaDeProveedores = new ArrayList<>();
        listaDeProveedores = provedAux.consultarTodosProveedores();

        pnlProv1.setVisible(false);
        pnlProv2.setVisible(false);
        pnlProv3.setVisible(false);
        pnlProv4.setVisible(false);
        pnlProv5.setVisible(false);
        pnlProv6.setVisible(false);

        int tamanoLista = listaDeProveedores.size();

        if (tamanoLista >= 0 && tamanoLista < 7) { //1-6
            totalPaginas = 1;
        } else if (tamanoLista > 6 && tamanoLista < 13) { //7-12
            totalPaginas = 2;
        } else if (tamanoLista > 12 && tamanoLista < 19) { //13-18
            totalPaginas = 3;
        } else if (tamanoLista > 18 && tamanoLista < 25) { //19-24
            totalPaginas = 4;
        } else if (tamanoLista > 24 && tamanoLista < 33) { //25-32
            totalPaginas = 5;
        }
        lblPaginas.setText("Página " + paginaActual + " de " + totalPaginas);
        try {
            if (listaDeProveedores != null) { //Asegurandonos que haya empleados registrados           

                txtID.setText(listaDeProveedores.get(indexLista).getIdProveedor());
                txtRFC.setText(listaDeProveedores.get(indexLista).getRfc());
                txtNombre.setText(listaDeProveedores.get(indexLista).getNombre());
                txtTelefono.setText(listaDeProveedores.get(indexLista).getTelefono());
                pnlProv1.setVisible(true);
                indexLista++;

                txtID1.setText(listaDeProveedores.get(indexLista).getIdProveedor());
                txtRFC1.setText(listaDeProveedores.get(indexLista).getRfc());
                txtNombre1.setText(listaDeProveedores.get(indexLista).getNombre());
                txtTelefono1.setText(listaDeProveedores.get(indexLista).getTelefono());
                pnlProv2.setVisible(true);
                indexLista++;

                txtID2.setText(listaDeProveedores.get(indexLista).getIdProveedor());
                txtRFC2.setText(listaDeProveedores.get(indexLista).getRfc());
                txtNombre2.setText(listaDeProveedores.get(indexLista).getNombre());
                txtTelefono2.setText(listaDeProveedores.get(indexLista).getTelefono());
                pnlProv3.setVisible(true);
                indexLista++;

                txtID3.setText(listaDeProveedores.get(indexLista).getIdProveedor());
                txtRFC3.setText(listaDeProveedores.get(indexLista).getRfc());
                txtNombre3.setText(listaDeProveedores.get(indexLista).getNombre());
                txtTelefono3.setText(listaDeProveedores.get(indexLista).getTelefono());
                pnlProv4.setVisible(true);
                indexLista++;

                txtID4.setText(listaDeProveedores.get(indexLista).getIdProveedor());
                txtRFC4.setText(listaDeProveedores.get(indexLista).getRfc());
                txtNombre4.setText(listaDeProveedores.get(indexLista).getNombre());
                txtTelefono4.setText(listaDeProveedores.get(indexLista).getTelefono());
                pnlProv5.setVisible(true);
                indexLista++;

                txtID5.setText(listaDeProveedores.get(indexLista).getIdProveedor());
                txtRFC5.setText(listaDeProveedores.get(indexLista).getRfc());
                txtNombre5.setText(listaDeProveedores.get(indexLista).getNombre());
                txtTelefono5.setText(listaDeProveedores.get(indexLista).getTelefono());
                pnlProv6.setVisible(true);
                indexLista++;
            }
        } catch (Exception ex) {
            System.out.println("Se llego al final de la lista de proveedores");
        }

    }

    public void modificarProveedor() {
        provedAux = new Proveedores();
        switch (panelSeleccionado) {
            case 1:
                provedAux.setIdProveedor(txtID.getText());
                provedAux.setRfc(txtRFC.getText());
                provedAux.setNombre(txtNombre.getText());
                provedAux.setTelefono(txtTelefono.getText());
                break;
            case 2:
                provedAux.setIdProveedor(txtID1.getText());
                provedAux.setRfc(txtRFC1.getText());
                provedAux.setNombre(txtNombre1.getText());
                provedAux.setTelefono(txtTelefono1.getText());
                break;
            case 3:
                provedAux.setIdProveedor(txtID2.getText());
                provedAux.setRfc(txtRFC2.getText());
                provedAux.setNombre(txtNombre2.getText());
                provedAux.setTelefono(txtTelefono2.getText());
                break;
            case 4:
                provedAux.setIdProveedor(txtID3.getText());
                provedAux.setRfc(txtRFC3.getText());
                provedAux.setNombre(txtNombre3.getText());
                provedAux.setTelefono(txtTelefono3.getText());
                break;
            case 5:
                provedAux.setIdProveedor(txtID4.getText());
                provedAux.setRfc(txtRFC4.getText());
                provedAux.setNombre(txtNombre4.getText());
                provedAux.setTelefono(txtTelefono4.getText());
                break;
            case 6:
                provedAux.setIdProveedor(txtID5.getText());
                provedAux.setRfc(txtRFC5.getText());
                provedAux.setNombre(txtNombre5.getText());
                provedAux.setTelefono(txtTelefono5.getText());
                break;

            default:
                break;
        }
        provedAux.actualizarDatosProveedor(provedAux);
        datosTemp[0] = provedAux.getIdProveedor();
        datosTemp[1] = provedAux.getRfc();
        datosTemp[2] = provedAux.getNombre();
        datosTemp[3] = provedAux.getTelefono();
        bloquerEdicionPanel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVerTodos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pnlProv3 = new javax.swing.JPanel();
        Icon2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtID2 = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtNombre2 = new javax.swing.JTextField();
        txtRFC2 = new javax.swing.JTextField();
        pnlProv2 = new javax.swing.JPanel();
        Icon1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtID1 = new javax.swing.JTextField();
        txtTelefono1 = new javax.swing.JTextField();
        txtNombre1 = new javax.swing.JTextField();
        txtRFC1 = new javax.swing.JTextField();
        pnlProv6 = new javax.swing.JPanel();
        Icon5 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtID5 = new javax.swing.JTextField();
        txtTelefono5 = new javax.swing.JTextField();
        txtNombre5 = new javax.swing.JTextField();
        txtRFC5 = new javax.swing.JTextField();
        pnlProv4 = new javax.swing.JPanel();
        Icon3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtID3 = new javax.swing.JTextField();
        txtTelefono3 = new javax.swing.JTextField();
        txtNombre3 = new javax.swing.JTextField();
        txtRFC3 = new javax.swing.JTextField();
        pnlProv5 = new javax.swing.JPanel();
        Icon4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtID4 = new javax.swing.JTextField();
        txtTelefono4 = new javax.swing.JTextField();
        txtNombre4 = new javax.swing.JTextField();
        txtRFC4 = new javax.swing.JTextField();
        pnlProv1 = new javax.swing.JPanel();
        Icon = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtRFC = new javax.swing.JTextField();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblPaginas = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();

        setBackground(new java.awt.Color(232, 232, 232));
        setMaximumSize(new java.awt.Dimension(1200, 780));
        setMinimumSize(new java.awt.Dimension(1200, 780));
        setPreferredSize(new java.awt.Dimension(1200, 780));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVerTodos.setBackground(new java.awt.Color(2, 62, 138));
        btnVerTodos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVerTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerTodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoActualizar.png"))); // NOI18N
        btnVerTodos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnVerTodos.setFocusPainted(false);
        btnVerTodos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVerTodos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVerTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodosActionPerformed(evt);
            }
        });
        add(btnVerTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 40, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Buscar");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 70, -1));

        btnAgregar.setBackground(new java.awt.Color(2, 62, 138));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoAgregarBlanco.png"))); // NOI18N
        btnAgregar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 40, 40));

        btnBuscar.setBackground(new java.awt.Color(2, 62, 138));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoBuscar2.png"))); // NOI18N
        btnBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 40, 40, 40));

        txtBuscar.setBackground(new java.awt.Color(215, 215, 215));
        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(142, 142, 142));
        txtBuscar.setText("ID del Proveedor...");
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 16, 1, 1));
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, 270, 40));

        jLabel7.setBackground(new java.awt.Color(215, 215, 215));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(64, 64, 64));
        jLabel7.setText("Nuestros proveedores");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel7.setOpaque(true);
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 600, 40));

        pnlProv3.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlProv3MouseClicked(evt);
            }
        });
        pnlProv3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        pnlProv3.add(Icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(64, 64, 64));
        jLabel19.setText("Nombre");
        pnlProv3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(64, 64, 64));
        jLabel40.setText("ID");
        pnlProv3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(64, 64, 64));
        jLabel41.setText("RFC");
        pnlProv3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(64, 64, 64));
        jLabel42.setText("Teléfono");
        pnlProv3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtID2.setEditable(false);
        txtID2.setBackground(new java.awt.Color(211, 211, 211));
        txtID2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtID2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtID2.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv3.add(txtID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, -1));

        txtTelefono2.setEditable(false);
        txtTelefono2.setBackground(new java.awt.Color(211, 211, 211));
        txtTelefono2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTelefono2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtTelefono2.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv3.add(txtTelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 210, 240, -1));

        txtNombre2.setEditable(false);
        txtNombre2.setBackground(new java.awt.Color(211, 211, 211));
        txtNombre2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNombre2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtNombre2.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv3.add(txtNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        txtRFC2.setEditable(false);
        txtRFC2.setBackground(new java.awt.Color(211, 211, 211));
        txtRFC2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtRFC2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtRFC2.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv3.add(txtRFC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, -1));

        add(pnlProv3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 360, 255));

        pnlProv2.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlProv2MouseClicked(evt);
            }
        });
        pnlProv2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        pnlProv2.add(Icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(64, 64, 64));
        jLabel18.setText("Nombre");
        pnlProv2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(64, 64, 64));
        jLabel35.setText("ID");
        pnlProv2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(64, 64, 64));
        jLabel37.setText("RFC");
        pnlProv2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(64, 64, 64));
        jLabel39.setText("Teléfono");
        pnlProv2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtID1.setEditable(false);
        txtID1.setBackground(new java.awt.Color(211, 211, 211));
        txtID1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtID1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtID1.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv2.add(txtID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, -1));

        txtTelefono1.setEditable(false);
        txtTelefono1.setBackground(new java.awt.Color(211, 211, 211));
        txtTelefono1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTelefono1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtTelefono1.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv2.add(txtTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 210, 240, -1));

        txtNombre1.setEditable(false);
        txtNombre1.setBackground(new java.awt.Color(211, 211, 211));
        txtNombre1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNombre1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtNombre1.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv2.add(txtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        txtRFC1.setEditable(false);
        txtRFC1.setBackground(new java.awt.Color(211, 211, 211));
        txtRFC1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtRFC1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtRFC1.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv2.add(txtRFC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, -1));

        add(pnlProv2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 360, 255));

        pnlProv6.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlProv6MouseClicked(evt);
            }
        });
        pnlProv6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Icon5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        pnlProv6.add(Icon5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(64, 64, 64));
        jLabel22.setText("Nombre");
        pnlProv6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(64, 64, 64));
        jLabel49.setText("ID");
        pnlProv6.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(64, 64, 64));
        jLabel50.setText("RFC");
        pnlProv6.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(64, 64, 64));
        jLabel51.setText("Teléfono");
        pnlProv6.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtID5.setEditable(false);
        txtID5.setBackground(new java.awt.Color(211, 211, 211));
        txtID5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtID5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtID5.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv6.add(txtID5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, -1));

        txtTelefono5.setEditable(false);
        txtTelefono5.setBackground(new java.awt.Color(211, 211, 211));
        txtTelefono5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTelefono5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtTelefono5.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv6.add(txtTelefono5, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 210, 240, -1));

        txtNombre5.setEditable(false);
        txtNombre5.setBackground(new java.awt.Color(211, 211, 211));
        txtNombre5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNombre5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtNombre5.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv6.add(txtNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        txtRFC5.setEditable(false);
        txtRFC5.setBackground(new java.awt.Color(211, 211, 211));
        txtRFC5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtRFC5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtRFC5.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv6.add(txtRFC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, -1));

        add(pnlProv6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 420, 360, 255));

        pnlProv4.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlProv4MouseClicked(evt);
            }
        });
        pnlProv4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Icon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        pnlProv4.add(Icon3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(64, 64, 64));
        jLabel20.setText("Nombre");
        pnlProv4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(64, 64, 64));
        jLabel43.setText("ID");
        pnlProv4.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(64, 64, 64));
        jLabel44.setText("RFC");
        pnlProv4.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(64, 64, 64));
        jLabel45.setText("Teléfono");
        pnlProv4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtID3.setEditable(false);
        txtID3.setBackground(new java.awt.Color(211, 211, 211));
        txtID3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtID3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtID3.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv4.add(txtID3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, -1));

        txtTelefono3.setEditable(false);
        txtTelefono3.setBackground(new java.awt.Color(211, 211, 211));
        txtTelefono3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTelefono3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtTelefono3.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv4.add(txtTelefono3, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 210, 240, -1));

        txtNombre3.setEditable(false);
        txtNombre3.setBackground(new java.awt.Color(211, 211, 211));
        txtNombre3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNombre3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtNombre3.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv4.add(txtNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        txtRFC3.setEditable(false);
        txtRFC3.setBackground(new java.awt.Color(211, 211, 211));
        txtRFC3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtRFC3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtRFC3.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv4.add(txtRFC3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, -1));

        add(pnlProv4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 360, 255));

        pnlProv5.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlProv5MouseClicked(evt);
            }
        });
        pnlProv5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Icon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        pnlProv5.add(Icon4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(64, 64, 64));
        jLabel21.setText("Nombre");
        pnlProv5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(64, 64, 64));
        jLabel46.setText("ID");
        pnlProv5.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(64, 64, 64));
        jLabel47.setText("RFC");
        pnlProv5.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(64, 64, 64));
        jLabel48.setText("Teléfono");
        pnlProv5.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtID4.setEditable(false);
        txtID4.setBackground(new java.awt.Color(211, 211, 211));
        txtID4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtID4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtID4.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv5.add(txtID4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, -1));

        txtTelefono4.setEditable(false);
        txtTelefono4.setBackground(new java.awt.Color(211, 211, 211));
        txtTelefono4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTelefono4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtTelefono4.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv5.add(txtTelefono4, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 210, 240, -1));

        txtNombre4.setEditable(false);
        txtNombre4.setBackground(new java.awt.Color(211, 211, 211));
        txtNombre4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNombre4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtNombre4.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv5.add(txtNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        txtRFC4.setEditable(false);
        txtRFC4.setBackground(new java.awt.Color(211, 211, 211));
        txtRFC4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtRFC4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtRFC4.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv5.add(txtRFC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, -1));

        add(pnlProv5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 360, 255));

        pnlProv1.setBackground(new java.awt.Color(221, 221, 221));
        pnlProv1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlProv1MouseClicked(evt);
            }
        });
        pnlProv1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        pnlProv1.add(Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(64, 64, 64));
        jLabel17.setText("Nombre");
        pnlProv1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(64, 64, 64));
        jLabel34.setText("ID");
        pnlProv1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(64, 64, 64));
        jLabel36.setText("RFC");
        pnlProv1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(64, 64, 64));
        jLabel38.setText("Teléfono");
        pnlProv1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(211, 211, 211));
        txtID.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtID.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtID.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, -1));

        txtTelefono.setEditable(false);
        txtTelefono.setBackground(new java.awt.Color(211, 211, 211));
        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtTelefono.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 210, 240, -1));

        txtNombre.setEditable(false);
        txtNombre.setBackground(new java.awt.Color(211, 211, 211));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtNombre.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        txtRFC.setEditable(false);
        txtRFC.setBackground(new java.awt.Color(211, 211, 211));
        txtRFC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtRFC.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        txtRFC.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProv1.add(txtRFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, -1));

        add(pnlProv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 360, 255));

        btnAnterior.setBackground(new java.awt.Color(215, 215, 215));
        btnAnterior.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAnterior.setForeground(new java.awt.Color(64, 64, 64));
        btnAnterior.setText("<");
        btnAnterior.setPreferredSize(new java.awt.Dimension(72, 25));
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 720, 140, 30));

        btnSiguiente.setBackground(new java.awt.Color(215, 215, 215));
        btnSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(64, 64, 64));
        btnSiguiente.setText(">");
        btnSiguiente.setPreferredSize(new java.awt.Dimension(72, 25));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 720, 140, 30));

        btnEliminar.setBackground(new java.awt.Color(138, 0, 0));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEliminar.png"))); // NOI18N
        btnEliminar.setToolTipText("");
        btnEliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 40, 40, 40));

        lblPaginas.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lblPaginas.setForeground(new java.awt.Color(0, 0, 0));
        lblPaginas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaginas.setText("Pagina 1 de 1");
        add(lblPaginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 720, 150, -1));

        btnEditar.setBackground(new java.awt.Color(2, 62, 138));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEditarBlanco.png"))); // NOI18N
        btnEditar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEditar.setFocusPainted(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 40, 40, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (!edicion) {
            btnAgregar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoConfirmar.png")));
            prepararParaAgregar();
        } else {
            agregarProveedor();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarProveedor();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void pnlProv1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProv1MouseClicked
        bloquerEdicionPanel();
        pnlProv1.setBorder(pnlBorder);
        //pnlProv1.setBackground(new Color(237, 237, 237));
        panelSeleccionado = 1;
        anteriorSeleccionado = panelSeleccionado;
        proveedorSeleccionado = txtID.getText();
    }//GEN-LAST:event_pnlProv1MouseClicked

    private void pnlProv2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProv2MouseClicked
        bloquerEdicionPanel();
        pnlProv2.setBorder(pnlBorder);
        //pnlProv2.setBackground(new Color(237, 237, 237));
        panelSeleccionado = 2;
        anteriorSeleccionado = panelSeleccionado;
        proveedorSeleccionado = txtID1.getText();
    }//GEN-LAST:event_pnlProv2MouseClicked

    private void pnlProv3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProv3MouseClicked
        bloquerEdicionPanel();
        pnlProv3.setBorder(pnlBorder);
        //pnlProv3.setBackground(new Color(237, 237, 237));
        panelSeleccionado = 3;
        anteriorSeleccionado = panelSeleccionado;
        proveedorSeleccionado = txtID2.getText();
    }//GEN-LAST:event_pnlProv3MouseClicked

    private void pnlProv4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProv4MouseClicked
        bloquerEdicionPanel();
        pnlProv4.setBorder(pnlBorder);
        //pnlProv4.setBackground(new Color(237, 237, 237));
        panelSeleccionado = 4;
        anteriorSeleccionado = panelSeleccionado;
        proveedorSeleccionado = txtID3.getText();
    }//GEN-LAST:event_pnlProv4MouseClicked

    private void pnlProv5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProv5MouseClicked
        bloquerEdicionPanel();
        pnlProv5.setBorder(pnlBorder);
        //pnlProv5.setBackground(new Color(237, 237, 237));
        panelSeleccionado = 5;
        anteriorSeleccionado = panelSeleccionado;
        proveedorSeleccionado = txtID4.getText();
    }//GEN-LAST:event_pnlProv5MouseClicked

    private void pnlProv6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProv6MouseClicked
        bloquerEdicionPanel();
        pnlProv6.setBorder(pnlBorder);
        //pnlProv6.setBackground(new Color(237, 237, 237));
        panelSeleccionado = 6;
        anteriorSeleccionado = panelSeleccionado;
        proveedorSeleccionado = txtID5.getText();
    }//GEN-LAST:event_pnlProv6MouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        /*if (!quiereEliminar) {
            if (buscarProveedor()) { //Si se encuentra a un proveedor
                quiereEliminar = true;
                btnEliminar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoConfirmar.png")));
            }
        } else {*/
            if (provedAux.eliminarProveedor(proveedorSeleccionado)) {

                provedAux = new Proveedores();
                indexLista = 0;
                cargarProveedores();
                //btnEliminar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoEliminar.png")));
                quiereEliminar = false;
                JOptionPane.showMessageDialog(null, "El proveedor ha sido eliminado correctamente");
            }
        //}
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (!edicion) {
            btnEditar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoConfirmar.png")));
            editarProveedor();
        } else {
            btnEditar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoEditarBlanco.png")));
            modificarProveedor();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        if (txtBuscar.getText().equals("ID del Proveedor...")) {
            txtBuscar.setText("");
            txtBuscar.setForeground(new Color(64, 64, 64));
        }
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        if (txtBuscar.getText().equals("")) {
            txtBuscar.setText("ID del Proveedor...");
            txtBuscar.setForeground(new Color(142, 142, 142));
        }
    }//GEN-LAST:event_txtBuscarFocusLost

    private void btnVerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosActionPerformed
        indexLista = 0;
        paginaActual = 1;
        bloquerEdicionPanel();
        cargarProveedores();
        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoAgregarBlanco.png")));
        btnEditar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoEditarBlanco.png")));
    }//GEN-LAST:event_btnVerTodosActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if (paginaActual < totalPaginas) {
            paginaActual++;
            bloquerEdicionPanel();
            cargarProveedores();
        }
        //Cancelar posibles operacion en curso
        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoAgregarBlanco.png")));
        btnEditar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoEditarBlanco.png")));
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        if (paginaActual > 1) {
            paginaActual--;
            indexLista = (int) Math.floor(indexLista / 6); //Dividir para obtener la pagina anterior, ej: 15 / 6 = 2.5 = 2
            indexLista = (indexLista * 6) - 6; //2 * 6 = 12 - 5 = 7; el index comenzara desde el 7 al 12, lo que corresponde a la pagina 2
            bloquerEdicionPanel();
            cargarProveedores();
        }
        //Cancelar posibles operacion en curso
        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoAgregarBlanco.png")));
        btnEditar.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconoEditarBlanco.png")));
    }//GEN-LAST:event_btnAnteriorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel Icon1;
    private javax.swing.JLabel Icon2;
    private javax.swing.JLabel Icon3;
    private javax.swing.JLabel Icon4;
    private javax.swing.JLabel Icon5;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnVerTodos;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblPaginas;
    private javax.swing.JPanel pnlProv1;
    private javax.swing.JPanel pnlProv2;
    private javax.swing.JPanel pnlProv3;
    private javax.swing.JPanel pnlProv4;
    private javax.swing.JPanel pnlProv5;
    private javax.swing.JPanel pnlProv6;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtID1;
    private javax.swing.JTextField txtID2;
    private javax.swing.JTextField txtID3;
    private javax.swing.JTextField txtID4;
    private javax.swing.JTextField txtID5;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtNombre3;
    private javax.swing.JTextField txtNombre4;
    private javax.swing.JTextField txtNombre5;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtRFC1;
    private javax.swing.JTextField txtRFC2;
    private javax.swing.JTextField txtRFC3;
    private javax.swing.JTextField txtRFC4;
    private javax.swing.JTextField txtRFC5;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    private javax.swing.JTextField txtTelefono3;
    private javax.swing.JTextField txtTelefono4;
    private javax.swing.JTextField txtTelefono5;
    // End of variables declaration//GEN-END:variables
}
