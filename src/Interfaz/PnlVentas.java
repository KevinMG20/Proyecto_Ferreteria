/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

import Objetos.Empleados;
import Objetos.Productos;
import Objetos.Proveedores;
import Objetos.Ventas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Kevin MG
 */
public class PnlVentas extends javax.swing.JPanel {

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    DefaultTableModel tableProductosModel, tableVentasModel, tableInfoModel;
    ArrayList<String[]> listaDeProductos;
    ArrayList<Proveedores> listaDeProveedores;
    ArrayList<Empleados> listaDeEmpleados;
    Productos productoAux;
    Empleados empleadoAux;
    Ventas ventaAux;
    float total = 0;
    DefaultListModel<String> listCantidadModel = new DefaultListModel<>(),
            listDescripcionModel = new DefaultListModel<>(), listImporteModel = new DefaultListModel<>();

    public PnlVentas() {
        ventaAux = new Ventas();

        initComponents();
        iniciarTablas();
        llenarProductos();
        llenarEmpleados();
        obtenerVentasRecientes();

        lstCantidad.setModel(listCantidadModel);
        lstDescripcion.setModel(listDescripcionModel);
        lstImporte.setModel(listImporteModel);

        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        spnCantidad.getEditor().getComponent(0).setBackground(new Color(215, 215, 215));

        JComponent editor = spnCantidad.getEditor();
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        
        if (!Login.usuario.getPuesto().equals("Gerente")) {            
            btnEliminar.setVisible(false);            
        }   

    }
    ArrayList<Ventas> listaDeVentas;
    String folioSeleccionado;

    public void obtenerVentasRecientes() {
        ventaAux = new Ventas();
        listaDeVentas = ventaAux.consultarTodasVentas();        
        //Limpiar la tabla
        while (tableVentasModel.getRowCount() != 0) {
            tableVentasModel.removeRow(0);
        }
        for (int x = 0; x < listaDeVentas.size(); x++) {
            tableVentasModel.addRow(new String[]{listaDeVentas.get(x).getFolioVenta(), Float.toString(listaDeVentas.get(x).getImporte()),
                listaDeVentas.get(x).getIdEmpleado(), Integer.toString(listaDeVentas.get(x).getIdProductos().length)});
        }

        tblVentas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    seleccionandoEntrega = true;
                    folioSeleccionado = tblVentas.getValueAt(tblVentas.getSelectedRow(), 0).toString();
                    switch (tblVentas.getSelectedColumn()) {
                        case 0, 1, 3: //En caso que se seleccione el Folio o el total de productos
                            mostrarProductos(tblVentas.getSelectedRow());
                            seleccionandoEntrega = true;
                            break;
                        case 2: //En caso del empleado
                            mostrarEmpleado(tblVentas.getSelectedRow());
                            seleccionandoProducto = false;
                            break;
                        default:
                            break;
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Antes de presionar en buscar, deseleccione la edición de la celda");
                    obtenerVentasRecientes();
                } catch (Exception ex) {

                }

            }
        });
    }
    boolean seleccionandoEntrega = false;

    public void mostrarEmpleado(int item) {
        tableInfoModel = new DefaultTableModel();

        tableInfoModel.addColumn("ID");
        tableInfoModel.addColumn("Nombre");
        tableInfoModel.addColumn("Puesto");
        tableInfoModel.addColumn("Turno");

        tblInfo.setModel(tableInfoModel);

        tblInfo.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblInfo.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblInfo.getColumnModel().getColumn(3).setPreferredWidth(40);

        for (int i = 0; i < 4; i++) {
            tblInfo.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        empleadoAux = new Empleados(listaDeVentas.get(item).getIdEmpleado());
        //System.out.println(listaDeEntregas.get(item).getIdEmpleado() + "asd");
        empleadoAux = empleadoAux.consultarEmpleado(empleadoAux);
        tableInfoModel.addRow(new String[]{empleadoAux.getIdEmpleado(), empleadoAux.getNombre(),
            empleadoAux.getPuesto(), empleadoAux.getTurno()});
    }

    public void mostrarProductos(int item) {
        try {
            tableInfoModel = new DefaultTableModel();

            tableInfoModel.addColumn("ID");
            tableInfoModel.addColumn("Nombre");
            tableInfoModel.addColumn("Precio");
            tableInfoModel.addColumn("Cantidad");

            tblInfo.setModel(tableInfoModel);

            tblInfo.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblInfo.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblInfo.getColumnModel().getColumn(3).setPreferredWidth(20);

            for (int i = 0; i < 4; i++) {
                tblInfo.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            for (int i = 0; i < listaDeVentas.get(item).getIdProductos().length; i++) {
                productoAux = new Productos(listaDeVentas.get(item).getIdProductos()[i]);
                productoAux = productoAux.consultarProducto(productoAux);
                tableInfoModel.addRow(new String[]{productoAux.getIdProducto(),
                    productoAux.getNombre(), Float.toString(productoAux.getPrecio()),
                    Integer.toString(listaDeVentas.get(item).getCantidades()[i])});
            }
        } catch (Exception ex) {//Si no hay un item seleccionado, que se vacie la tabla
            tableInfoModel = new DefaultTableModel();
            tblInfo.setModel(tableInfoModel);
        }
    }

    public void llenarProductos() {
        listaDeProductos = new ArrayList<>();
        productoAux = new Productos();
        listaDeProductos = productoAux.consultarTodosLosProductos();
        cbxProductos.removeAllItems();
        for (int x = 0; x < listaDeProductos.size(); x++) {
            cbxProductos.addItem(listaDeProductos.get(x)[0]);
        }
    }

    public void llenarEmpleados() {
        listaDeEmpleados = new ArrayList<>();
        empleadoAux = new Empleados();
        listaDeEmpleados = empleadoAux.consultarEmpleados();
        cbxEmpleados.removeAllItems();
        for (int x = 0; x < listaDeEmpleados.size(); x++) {
            cbxEmpleados.addItem(listaDeEmpleados.get(x).getIdEmpleado());
        }
    }

    public void iniciarTablas() {
        tableVentasModel = new DefaultTableModel();
        tableVentasModel.addColumn("Folio");
        tableVentasModel.addColumn("Importe");
        tableVentasModel.addColumn("Empleado");
        tableVentasModel.addColumn("Productos");
        tblVentas.setModel(tableVentasModel);

        tableProductosModel = new DefaultTableModel();
        tableProductosModel.addColumn("ID");
        tableProductosModel.addColumn("Nombre");
        tableProductosModel.addColumn("Marca");
        tableProductosModel.addColumn("Precio");
        tableProductosModel.addColumn("Cantidad");
        tblProductos.setModel(tableProductosModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        JTableHeader th = tblProductos.getTableHeader();
        th.setFont(new Font("Segoe UI", Font.BOLD, 15));
        th.setForeground(Color.WHITE);
        tblProductos.getTableHeader().setBackground(new Color(183,52,0));

        th = tblVentas.getTableHeader();
        th.setFont(new Font("Segoe UI", Font.BOLD, 15));
        th.setForeground(Color.WHITE);
        tblVentas.getTableHeader().setBackground(new Color(183,52,0));

        th = tblInfo.getTableHeader();
        th.setFont(new Font("Segoe UI", Font.BOLD, 15));
        th.setForeground(Color.WHITE);
        tblInfo.getTableHeader().setBackground(new Color(183,52,0));

        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(25);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(25);
        tblProductos.getColumnModel().getColumn(3).setPreferredWidth(25);
        tblProductos.getColumnModel().getColumn(4).setPreferredWidth(25);

        for (int i = 0; i < 5; i++) {
            tblProductos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblVentas.getColumnModel().getColumn(1).setPreferredWidth(25);
        tblVentas.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblVentas.getColumnModel().getColumn(3).setPreferredWidth(25);
        //tblEntregas.getColumnModel().getColumn(4).setPreferredWidth(40);

        for (int i = 0; i < 4; i++) {
            tblVentas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        //Cuando se da click en un elemento de la tabla de entregas, se colocan sus datos para modificar
        tblProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    cbxProductos.setSelectedItem(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0));
                    spnCantidad.setValue(Integer.parseInt(tblProductos.getValueAt(tblProductos.getSelectedRow(), 3).toString()));
                } catch (Exception ex) {

                }
            }
        });

        tblInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (tableInfoModel.getColumnName(2).equals("Categoria")) {
                    seleccionandoProducto = true;
                } else {
                    seleccionandoProducto = false;
                }
            }
        });
    }
    boolean seleccionandoProducto = false;

    public void limpiar() {
        spnCantidad.setValue(1);
        txtTotal.setText("$ 0");
        lblTotalTicket.setText("$ 0");
        while (tableProductosModel.getRowCount() != 0) {
            tableProductosModel.removeRow(0);
        }
        listCantidadModel.removeAllElements();
        listDescripcionModel.removeAllElements();
        listImporteModel.removeAllElements();
    }

    public void modificarEntrega() {
        ventaAux = listaDeVentas.get(tblVentas.getSelectedRow());
        cbxEmpleados.setSelectedItem(ventaAux.getIdEmpleado());
        txtTotal.setText("$ " + Float.toString(ventaAux.getImporte()));
        txtTotal.setEditable(true);
        productoAux = new Productos();
        String datos[] = new String[5];
        while (tableProductosModel.getRowCount() != 0) {
            tableProductosModel.removeRow(0);
        }
        //Buscamos al producto en la lista de productos
        for (int x = 0; x < ventaAux.getIdProductos().length; x++) {
            for (int y = 0; y < listaDeProductos.size(); y++) {
                if (ventaAux.getIdProductos()[x].equals(listaDeProductos.get(y)[0])) {
                    datos[0] = listaDeProductos.get(y)[0];
                    datos[1] = listaDeProductos.get(y)[1];
                    datos[2] = listaDeProductos.get(y)[3];
                    datos[3] = listaDeProductos.get(y)[4];
                    datos[4] = Integer.toString(ventaAux.getCantidades()[x]);
                    tableProductosModel.addRow(datos);
                    break;
                }
            }
        }
        modificando = true;
        btnAgregar.setEnabled(false);
        cbxProductos.setEnabled(false);
        spnCantidad.setEnabled(false);
        tblProductos.setEnabled(false);
    }

    public void buscarVenta() {
        try {
            ventaAux = new Ventas(txtBuscar.getText());
            ventaAux = ventaAux.consultarVenta(ventaAux);
            listaDeVentas.clear();
            listaDeVentas.add(ventaAux);
            productoAux = new Productos();
            String datos[] = new String[5];
            //Limpiar la tabla de info
            if (tableInfoModel != null) {
                while (tableInfoModel.getRowCount() != 0) {
                    tableInfoModel.removeRow(0);
                }
            } else {
                tableInfoModel = new DefaultTableModel();
                tblInfo.setModel(tableInfoModel);
            }
            //Limpiar la tabla de ventas
            while (tableVentasModel.getRowCount() != 0) {
                tableVentasModel.removeRow(0);
            }
            try {
                tableVentasModel.addRow(new String[]{ventaAux.getFolioVenta(), Float.toString(ventaAux.getImporte()), ventaAux.getIdEmpleado(),
                    Integer.toString(ventaAux.getIdProductos().length)});
            } catch (java.lang.NullPointerException e) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna venta con ese folio");
                return;
            }
            //Buscamos al producto en la lista de productos
            for (int x = 0; x < ventaAux.getIdProductos().length; x++) {
                for (int y = 0; y < listaDeProductos.size(); y++) {
                    if (ventaAux.getIdProductos()[x].equals(listaDeProductos.get(y)[0])) {
                        datos = new String[4];
                        datos[0] = listaDeProductos.get(y)[0];
                        datos[1] = listaDeProductos.get(y)[1];
                        datos[2] = listaDeProductos.get(y)[4];
                        datos[3] = Integer.toString(ventaAux.getCantidades()[x]);
                        tableInfoModel.addRow(datos);
                        break;
                    }
                }
            }
            folioSeleccionado = ventaAux.getFolioVenta();
        } catch (Exception ex) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cbxProductos = new javax.swing.JComboBox<>();
        btnFinalizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbxEmpleados = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        scrlVentas = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        scrlProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        scrlInfo = new javax.swing.JScrollPane();
        tblInfo = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnVerTodos = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblTotalTicket = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstImporte = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstDescripcion = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstCantidad = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(232, 232, 232));
        setMinimumSize(new java.awt.Dimension(1156, 750));
        setPreferredSize(new java.awt.Dimension(1366, 676));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setBackground(new java.awt.Color(183, 52, 0));
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
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, 40, 40));

        btnLimpiar.setBackground(new java.awt.Color(211, 211, 211));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(64, 64, 64));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 110, 33));

        jLabel16.setBackground(new java.awt.Color(215, 215, 215));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(64, 64, 64));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Ventas recientes");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel16.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel16.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(170, 30));
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 400, -1));

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
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 750, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Panel de ventas");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 230, -1));

        btnAgregar.setBackground(new java.awt.Color(211, 211, 211));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(64, 64, 64));
        btnAgregar.setText("Agregar");
        btnAgregar.setFocusPainted(false);
        btnAgregar.setPreferredSize(new java.awt.Dimension(72, 25));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 160, 100, -1));

        jLabel20.setBackground(new java.awt.Color(51, 51, 51));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("ID del Producto");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 200, -1));

        cbxProductos.setBackground(new java.awt.Color(215, 215, 215));
        cbxProductos.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxProductos.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 200, -1));

        btnFinalizar.setBackground(new java.awt.Color(183, 52, 0));
        btnFinalizar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFinalizar.setForeground(new java.awt.Color(232, 232, 232));
        btnFinalizar.setText("Confirmar");
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        add(btnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 120, 33));

        btnEliminar.setBackground(new java.awt.Color(138, 0, 0));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(232, 232, 232));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEliminar.png"))); // NOI18N
        btnEliminar.setToolTipText("Seleccione una de las entregas recientes y presione este boton para eliminarla");
        btnEliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setIconTextGap(8);
        btnEliminar.setPreferredSize(new java.awt.Dimension(97, 30));
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 30, 40, 40));

        btnModificar.setBackground(new java.awt.Color(183, 52, 0));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(232, 232, 232));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEditar.png"))); // NOI18N
        btnModificar.setToolTipText("Seleccione una de las entregas recientes y presione este boton para modificarla");
        btnModificar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnModificar.setFocusPainted(false);
        btnModificar.setMargin(new java.awt.Insets(2, 1, 2, 14));
        btnModificar.setPreferredSize(new java.awt.Dimension(75, 27));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, 40, 40));

        txtBuscar.setBackground(new java.awt.Color(215, 215, 215));
        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(142, 142, 142));
        txtBuscar.setText("Folio de la venta...");
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 16, 1, 1));
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 290, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Buscar");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 70, -1));

        cbxEmpleados.setBackground(new java.awt.Color(215, 215, 215));
        cbxEmpleados.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxEmpleados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxEmpleados.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 180, -1));

        jLabel23.setBackground(new java.awt.Color(51, 51, 51));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Empleado que atiende");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 180, -1));

        spnCantidad.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnCantidad.setBorder(null);
        spnCantidad.setPreferredSize(new java.awt.Dimension(64, 25));
        add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 180, 25));

        jLabel18.setBackground(new java.awt.Color(215, 215, 215));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(64, 64, 64));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Vista previa del ticket");
        jLabel18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel18.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel18.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel18.setOpaque(true);
        jLabel18.setPreferredSize(new java.awt.Dimension(170, 30));
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 310, -1));

        scrlVentas.setBackground(new java.awt.Color(232, 232, 232));
        scrlVentas.setForeground(new java.awt.Color(64, 64, 64));
        scrlVentas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        scrlVentas.setOpaque(false);

        tblVentas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblVentas.setRowHeight(25);
        scrlVentas.setViewportView(tblVentas);

        add(scrlVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 400, 210));

        scrlProductos.setBackground(new java.awt.Color(232, 232, 232));
        scrlProductos.setForeground(new java.awt.Color(64, 64, 64));
        scrlProductos.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N

        tblProductos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblProductos.setRowHeight(25);
        scrlProductos.setViewportView(tblProductos);

        add(scrlProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 750, 210));

        scrlInfo.setBackground(new java.awt.Color(232, 232, 232));
        scrlInfo.setForeground(new java.awt.Color(64, 64, 64));
        scrlInfo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        scrlInfo.setOpaque(false);

        tblInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblInfo.setRowHeight(25);
        scrlInfo.setViewportView(tblInfo);

        add(scrlInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, 330, 210));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 20, 660));

        btnVerTodos.setBackground(new java.awt.Color(183, 52, 0));
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
        add(btnVerTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 30, 40, 40));

        jLabel19.setBackground(new java.awt.Color(215, 215, 215));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(64, 64, 64));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Información de la venta");
        jLabel19.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel19.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel19.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel19.setOpaque(true);
        jLabel19.setPreferredSize(new java.awt.Dimension(170, 30));
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 330, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Cantidad");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 180, -1));

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Total:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, 50, -1));

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(215, 215, 215));
        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(64, 64, 64));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setBorder(null);
        add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 430, 160, 25));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/LogoTicket2.png"))); // NOI18N
        jLabel1.setOpaque(true);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, 100, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(64, 64, 64));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¡Gracias Por Su Compra!");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 710, 270, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(64, 64, 64));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Construye Fácil");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 190, 130, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(64, 64, 64));
        jLabel4.setText("___________________");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 190, 140, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(64, 64, 64));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Vizcaya, 34080 Durango, Dgo.");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 300, 250, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(64, 64, 64));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Blvd. Felipe Pescador 1830, Nueva");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 270, 250, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(64, 64, 64));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tel. 618 871 49 72");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 230, 130, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(64, 64, 64));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Importe");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 340, 60, 30));

        lblTotalTicket.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalTicket.setForeground(new java.awt.Color(64, 64, 64));
        lblTotalTicket.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalTicket.setText("$ 0");
        add(lblTotalTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 670, 200, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(64, 64, 64));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Descripción");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 340, 140, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(64, 64, 64));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Cant");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 340, 50, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(64, 64, 64));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Total:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 670, 50, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(64, 64, 64));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Ferreteria");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 170, 90, -1));

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lstImporte.setBackground(new java.awt.Color(255, 255, 255));
        lstImporte.setBorder(null);
        lstImporte.setForeground(new java.awt.Color(64, 64, 64));
        lstImporte.setFixedCellHeight(25);
        jScrollPane4.setViewportView(lstImporte);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 370, 60, 290));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lstDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        lstDescripcion.setBorder(null);
        lstDescripcion.setForeground(new java.awt.Color(64, 64, 64));
        lstDescripcion.setFixedCellHeight(25);
        jScrollPane3.setViewportView(lstDescripcion);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 370, 140, 290));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        lstCantidad.setBackground(new java.awt.Color(255, 255, 255));
        lstCantidad.setBorder(null);
        lstCantidad.setForeground(new java.awt.Color(64, 64, 64));
        lstCantidad.setFixedCellHeight(25);
        jScrollPane2.setViewportView(lstCantidad);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 370, 30, 290));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel15.setOpaque(true);
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, 310, 610));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        String mensaje;
        if (modificando) {
            mensaje = "¿Confirmar la modificación de la venta?";
        } else {
            mensaje = "¿Confirmar el registro de la venta?";
        }
        if (JOptionPane.showConfirmDialog(null, mensaje, "Confirmar", JOptionPane.YES_OPTION) == 0) {

            String[] productos = new String[tableProductosModel.getRowCount()];
            int[] cantidades = new int[tableProductosModel.getRowCount()];

            for (int x = 0; x < productos.length; x++) {
                productos[x] = tableProductosModel.getValueAt(x, 0).toString();
                cantidades[x] = Integer.parseInt(tableProductosModel.getValueAt(x, 4).toString());
            }
            try {
                if (productos[0].isBlank() || cantidades[0] == 0) {
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "No se han agregado productos nuevos");
                return;
            }

            ventaAux = new Ventas();
            //System.out.println("Folio: " + entregaAux.generarFolio(date, cbxProveedores.getSelectedItem().toString()));

            if (modificando) {
                ventaAux = new Ventas(folioSeleccionado, productos, cantidades,
                        Float.parseFloat(txtTotal.getText().substring(2)), cbxEmpleados.getSelectedItem().toString());
                ventaAux.actualizarDatosVentas(ventaAux);
            } else {
                ventaAux = new Ventas(ventaAux.generarFolio(productos.length), productos, cantidades, Float.parseFloat(txtTotal.getText().substring(2)),
                        cbxEmpleados.getSelectedItem().toString());
                ventaAux.registrarVenta(ventaAux);
            }
            modificando = false;
            txtTotal.setEditable(false);
            btnAgregar.setEnabled(true);
            cbxProductos.setEnabled(true);
            spnCantidad.setEnabled(true);
            tblProductos.setEnabled(true);
            obtenerVentasRecientes();
            limpiar();
        }

    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            ventaAux = new Ventas(tblVentas.getValueAt(tblVentas.getSelectedRow(), 0).toString());
            if (seleccionandoEntrega && !seleccionandoProducto) { //Entrega seleccionada pero no producto
                ventaAux.eliminarVenta(ventaAux);
                obtenerVentasRecientes();
                seleccionandoEntrega = false;
                seleccionandoProducto = false;
            } else if (!seleccionandoEntrega && seleccionandoProducto) { //Producto seleccionado pero no entrega
                ventaAux.eliminarProductoDeVenta(tblInfo.getValueAt(tblInfo.getSelectedRow(), 0).toString(),
                        folioSeleccionado);

                //Para actualizar la tabla
                for (int i = 0; i < listaDeVentas.size(); i++) {
                    if (folioSeleccionado.equals(listaDeVentas.get(i).getFolioVenta())) {
                        obtenerVentasRecientes();
                        mostrarProductos(i);
                    }
                }
                seleccionandoProducto = false;
            } else if (seleccionandoEntrega && seleccionandoProducto) { //Ambos seleccionados, se pregunta
                Object[] botones = {"Entrega", "Producto"};

                int opcion = JOptionPane.showOptionDialog(null, "Se seleccionaron campos en dos tablas distintas\n"
                        + "¿Desea eliminar los datos de la entrega? O ¿Desea eliminar el producto seleccionado?", "Operaciones",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);

                if (opcion == 0) {
                    ventaAux.eliminarVenta(ventaAux);
                    obtenerVentasRecientes();
                    seleccionandoEntrega = false;
                    seleccionandoProducto = false;
                } else if (opcion == 1) {
                    ventaAux.eliminarProductoDeVenta(tblInfo.getValueAt(tblInfo.getSelectedRow(), 0).toString(),
                            folioSeleccionado);

                    for (int i = 0; i < listaDeVentas.size(); i++) {
                        if (folioSeleccionado.equals(listaDeVentas.get(i).getFolioVenta())) {
                            obtenerVentasRecientes();
                            mostrarProductos(i);
                        }
                    }
                    seleccionandoProducto = false;
                }
            }
            seleccionandoProducto = false;
        } catch (Exception ex) { //Ninguno seleccionado
            JOptionPane.showMessageDialog(null, "Primero seleccione un elemento en la tabla de entregas o uno de sus productos");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    boolean modificando;
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            ventaAux = new Ventas(tblVentas.getValueAt(tblVentas.getSelectedRow(), 0).toString());
            if (seleccionandoEntrega && !seleccionandoProducto) { //Entrega seleccionada pero no producto
                modificarEntrega();
                obtenerVentasRecientes();
            } else if (!seleccionandoEntrega && seleccionandoProducto) { //Producto seleccionado pero no entrega
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto seleccionado"));
                ventaAux.actualizarCantidades(cantidad, tblInfo.getValueAt(tblInfo.getSelectedRow(), 0).toString());
                //Como la seleccion se quita al dar click en un boton, se debe buscar el folio en la lista de entregas
                //Para actualizar la tabla
                for (int i = 0; i < listaDeVentas.size(); i++) {
                    if (folioSeleccionado.equals(listaDeVentas.get(i).getFolioVenta())) {
                        obtenerVentasRecientes();
                        mostrarProductos(i);
                    }
                }
            } else if (seleccionandoEntrega && seleccionandoProducto) { //Ambos seleccionados, se pregunta
                Object[] botones = {"Entrega", "Producto"};

                int opcion = JOptionPane.showOptionDialog(null, "Se seleccionaron campos en dos tablas distintas\n"
                        + "¿Desea modificar los datos de la entrega? O ¿Desea modificar la cantidad del producto seleccionado?", "Operaciones",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);

                if (opcion == 0) {
                    modificarEntrega();
                    obtenerVentasRecientes();
                } else if (opcion == 1) {
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto seleccionado"));
                    ventaAux.actualizarCantidades(cantidad, tblInfo.getValueAt(tblInfo.getSelectedRow(), 0).toString());
                    for (int i = 0; i < listaDeVentas.size(); i++) {
                        if (folioSeleccionado.equals(listaDeVentas.get(i).getFolioVenta())) {
                            obtenerVentasRecientes();
                            mostrarProductos(i);
                        }
                    }
                }
            }
        } catch (Exception ex) { //Ninguno seleccionado
            JOptionPane.showMessageDialog(null, "Primero seleccione un elemento en la tabla de ventas recientes");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            //Primero recorremos la tabla para evitar que hayan repetidos
            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (cbxProductos.getSelectedItem().toString().equals(tblProductos.getValueAt(i, 0))) {
                    Object[] botones = {"Sumar", "Sobreescribir"};
                    int opcion = JOptionPane.showOptionDialog(null, "Ya se han agregado unidades de este producto\n\n"
                            + "¿Desea sumar las cantidades? O ¿Desea que se sobreescriba la cantidad anterior?", "Operaciones",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);

                    if (opcion == 0) {
                        //Sumar
                        int enLaTabla = Integer.parseInt(tblProductos.getValueAt(i, 4).toString());
                        int enElSpinner = Integer.parseInt(spnCantidad.getValue().toString());
                        tblProductos.setValueAt((enLaTabla + enElSpinner), i, 4);

                    } else if (opcion == 1) {
                        //Corregir
                        tblProductos.setValueAt(Integer.parseInt(spnCantidad.getValue().toString()), i, 4);
                    }
                    total = 0;
                    for (int x = 0; x < tableProductosModel.getRowCount(); x++) {
                        total += Float.parseFloat(tblProductos.getValueAt(x, 4).toString()) * (Float.parseFloat(tblProductos.getValueAt(x, 3).toString()));
                        txtTotal.setText("$ " + total);
                        lblTotalTicket.setText("$ " + total);
                    }
                    return;
                }
            }
            productoAux = new Productos(cbxProductos.getSelectedItem().toString());
            String datos[] = new String[5];
            //System.out.println("Tamano de lista de productos" + listaDeProductos.size());
            for (int x = 0; x < listaDeProductos.size(); x++) {
                //System.out.println("En la lista: " + listaDeProductos.get(x)[0]);                
                if (cbxProductos.getSelectedItem().toString().equals(listaDeProductos.get(x)[0])) {
                    productoAux = productoAux.consultarProducto(productoAux);

                    datos[0] = productoAux.getIdProducto();
                    datos[1] = productoAux.getNombre();
                    datos[2] = productoAux.getMarca();
                    datos[3] = Float.toString(productoAux.getPrecio());
                    datos[4] = spnCantidad.getValue().toString();
                    tableProductosModel.addRow(datos);
                    break;
                }
            }
            total = 0;
            for (int x = 0; x < tableProductosModel.getRowCount(); x++) {
                total += Float.parseFloat(tblProductos.getValueAt(x, 4).toString()) * (Float.parseFloat(tblProductos.getValueAt(x, 3).toString()));
                txtTotal.setText("$ " + total);
                lblTotalTicket.setText("$ " + total);
            }

            if (productoAux.getNombre().length() > 23) {
                listDescripcionModel.addElement(productoAux.getNombre().substring(0, 23) + "...");
            } else {
                listDescripcionModel.addElement(productoAux.getNombre());
            }
            listCantidadModel.addElement(spnCantidad.getValue().toString() + ".");

            listImporteModel.addElement("$ " + Float.toString(Float.parseFloat(spnCantidad.getValue().toString()) * productoAux.getPrecio()));

        } catch (Exception ex) {
            ex.printStackTrace();            
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarVenta();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        if (txtBuscar.getText().equals("Folio de la venta...")) {
            txtBuscar.setText("");
            txtBuscar.setForeground(new Color(64, 64, 64));
        }
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        if (txtBuscar.getText().equals("")) {
            txtBuscar.setText("Folio de la venta...");
            txtBuscar.setForeground(new Color(142, 142, 142));
        }
    }//GEN-LAST:event_txtBuscarFocusLost

    private void btnVerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosActionPerformed
        obtenerVentasRecientes();
    }//GEN-LAST:event_btnVerTodosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnFinalizar;
    public static javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVerTodos;
    private static javax.swing.JComboBox<String> cbxEmpleados;
    private static javax.swing.JComboBox<String> cbxProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTotalTicket;
    private javax.swing.JList<String> lstCantidad;
    private javax.swing.JList<String> lstDescripcion;
    private javax.swing.JList<String> lstImporte;
    private javax.swing.JScrollPane scrlInfo;
    private javax.swing.JScrollPane scrlProductos;
    private javax.swing.JScrollPane scrlVentas;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblInfo;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
