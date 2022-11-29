/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

import Objetos.Empleados;
import Objetos.Entregas;
import Objetos.Productos;
import Objetos.Proveedores;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
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
public class PnlEntregas extends javax.swing.JPanel {

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    DefaultTableModel tableProductosModel, tableEntregasModel, tableInfoModel;
    ArrayList<String[]> listaDeProductos;
    ArrayList<Proveedores> listaDeProveedores;
    ArrayList<Empleados> listaDeEmpleados;
    Productos productoAux;
    Proveedores proveedorAux;
    Empleados empleadoAux;
    Entregas entregaAux;

    public PnlEntregas() {
        entregaAux = new Entregas();

        initComponents();
        iniciarTablas();
        llenarProductos();
        llenarEmpleados();
        llenarProveedores();
        obtenerEntregasRecientes();

        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        //((JSpinner.DefaultEditor) spnCantidad.getEditor()).getTextField().setEditable(false);
        spnCantidad.getEditor().getComponent(0).setBackground(new Color(215, 215, 215));

        JComponent editor = spnCantidad.getEditor();
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        
        if (!Login.usuario.getPuesto().equals("Gerente")) {
            lblHerramientas.setVisible(false);
            btnModificar.setVisible(false);
            btnEliminar.setVisible(false);            
        }

        calFecha.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    //System.out.println(e.getPropertyName() + ": " + (Date) e.getNewValue());
                    String date = ((JTextField) calFecha.getDateEditor().getUiComponent()).getText();
                    txtFecha.setText(date);
                    //txtFecha.setText((JTextField) calFecha.getDateEditor().getUiComponent()).getText();
                }
            }
        });
    }
    ArrayList<Entregas> listaDeEntregas;
    String folioSeleccionado;

    public void obtenerEntregasRecientes() {
        entregaAux = new Entregas();
        listaDeEntregas = entregaAux.consultarTodasEntregas();
        //Limpiar la tabla
        while (tableEntregasModel.getRowCount() != 0) {
            tableEntregasModel.removeRow(0);
        }
        for (int x = 0; x < listaDeEntregas.size(); x++) {
            tableEntregasModel.addRow(new String[]{listaDeEntregas.get(x).getFolioEntrega(),
                listaDeEntregas.get(x).getFecha(), listaDeEntregas.get(x).getIdEmpleado(),
                listaDeEntregas.get(x).getIdProveedor(), Integer.toString(listaDeEntregas.get(x).getIdProductos().length)});
        }

        tblEntregas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    seleccionandoEntrega = true;
                    folioSeleccionado = tblEntregas.getValueAt(tblEntregas.getSelectedRow(), 0).toString();
                    switch (tblEntregas.getSelectedColumn()) {
                        case 0, 1, 4: //En caso que se seleccione el Folio o el total de productos
                            mostrarProductos(tblEntregas.getSelectedRow());
                            seleccionandoEntrega = true;
                            break;
                        case 2: //En caso del empleado
                            mostrarEmpleado(tblEntregas.getSelectedRow());
                            seleccionandoProducto = false;
                            break;
                        case 3: //En caso del proveedor
                            mostrarProveedor(tblEntregas.getSelectedRow());
                            seleccionandoProducto = false;
                            break;
                        default:
                            break;
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Antes de presionar en buscar, deseleccione la edición de la celda");
                    obtenerEntregasRecientes();
                } catch (Exception ex) {

                }

            }
        });
    }
    boolean seleccionandoEntrega = false;

    public void mostrarProveedor(int item) {
        tableInfoModel = new DefaultTableModel();

        tableInfoModel.addColumn("ID");
        tableInfoModel.addColumn("Nombre");
        tableInfoModel.addColumn("RFC");
        tableInfoModel.addColumn("Telefono");

        tblInfo.setModel(tableInfoModel);

        tblInfo.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblInfo.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblInfo.getColumnModel().getColumn(3).setPreferredWidth(30);

        for (int i = 0; i < 4; i++) {
            tblInfo.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        proveedorAux = new Proveedores(listaDeEntregas.get(item).getIdProveedor());
        proveedorAux = proveedorAux.consultarProveedor(proveedorAux.getIdProveedor());

        tableInfoModel.addRow(new Object[]{proveedorAux.getIdProveedor(), proveedorAux.getNombre(),
            proveedorAux.getRfc(), proveedorAux.getTelefono()});
    }

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
        empleadoAux = new Empleados(listaDeEntregas.get(item).getIdEmpleado());
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
            tableInfoModel.addColumn("Categoria");
            tableInfoModel.addColumn("Marca");
            tableInfoModel.addColumn("Cantidad");

            tblInfo.setModel(tableInfoModel);

            tblInfo.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblInfo.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblInfo.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblInfo.getColumnModel().getColumn(4).setPreferredWidth(30);

            for (int i = 0; i < 5; i++) {
                tblInfo.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            for (int i = 0; i < listaDeEntregas.get(item).getIdProductos().length; i++) {
                productoAux = new Productos(listaDeEntregas.get(item).getIdProductos()[i]);
                productoAux = productoAux.consultarProducto(productoAux);
                tableInfoModel.addRow(new String[]{productoAux.getIdProducto(),
                    productoAux.getNombre(), productoAux.getCategoria(),
                    productoAux.getMarca(), Integer.toString(listaDeEntregas.get(item).getCantidades()[i])});
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

    public void llenarProveedores() {
        listaDeProveedores = new ArrayList<>();
        proveedorAux = new Proveedores();
        listaDeProveedores = proveedorAux.consultarTodosProveedores();
        cbxProveedores.removeAllItems();
        for (int x = 0; x < listaDeProveedores.size(); x++) {
            cbxProveedores.addItem(listaDeProveedores.get(x).getIdProveedor());
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
        tableEntregasModel = new DefaultTableModel();
        tableEntregasModel.addColumn("Folio");
        tableEntregasModel.addColumn("Fecha");
        tableEntregasModel.addColumn("Empleado");
        tableEntregasModel.addColumn("Proveedor");
        tableEntregasModel.addColumn("Productos");
        tblEntregas.setModel(tableEntregasModel);

        tableProductosModel = new DefaultTableModel();
        tableProductosModel.addColumn("ID");
        tableProductosModel.addColumn("Nombre");
        tableProductosModel.addColumn("Marca");
        tableProductosModel.addColumn("Cantidad");
        tblProductos.setModel(tableProductosModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        JTableHeader th = tblProductos.getTableHeader();
        th.setFont(new Font("Segoe UI", Font.BOLD, 15));
        th.setForeground(Color.WHITE);
        tblProductos.getTableHeader().setBackground(new Color(183,52,0));

        th = tblEntregas.getTableHeader();
        th.setFont(new Font("Segoe UI", Font.BOLD, 15));
        th.setForeground(Color.WHITE);
        tblEntregas.getTableHeader().setBackground(new Color(183,52,0));

        th = tblInfo.getTableHeader();
        th.setFont(new Font("Segoe UI", Font.BOLD, 15));
        th.setForeground(Color.WHITE);
        tblInfo.getTableHeader().setBackground(new Color(183,52,0));

        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(25);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(25);
        tblProductos.getColumnModel().getColumn(3).setPreferredWidth(25);

        for (int i = 0; i < 4; i++) {
            tblProductos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblEntregas.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblEntregas.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblEntregas.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblEntregas.getColumnModel().getColumn(4).setPreferredWidth(40);

        for (int i = 0; i < 5; i++) {
            tblEntregas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
        while (tableProductosModel.getRowCount() != 0) {
            tableProductosModel.removeRow(0);
        }
    }

    public void modificarEntrega() {
        entregaAux = listaDeEntregas.get(tblEntregas.getSelectedRow());
        txtFecha.setText(entregaAux.getFecha());
        //calFecha.setDate(new Date(DateFormat(entregaAux.getFecha())));
        cbxEmpleados.setSelectedItem(entregaAux.getIdEmpleado());
        cbxProveedores.setSelectedItem(entregaAux.getIdProveedor());
        productoAux = new Productos();
        String datos[] = new String[4];
        while (tableProductosModel.getRowCount() != 0) {
            tableProductosModel.removeRow(0);
        }
        //Buscamos al producto en la lista de productos
        for (int x = 0; x < entregaAux.getIdProductos().length; x++) {
            for (int y = 0; y < listaDeProductos.size(); y++) {
                if (entregaAux.getIdProductos()[x].equals(listaDeProductos.get(y)[0])) {
                    datos[0] = listaDeProductos.get(y)[0];
                    datos[1] = listaDeProductos.get(y)[1];
                    datos[2] = listaDeProductos.get(y)[3];
                    datos[3] = Integer.toString(entregaAux.getCantidades()[x]);
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

    public void buscarEntrega() {
        try {
            entregaAux = new Entregas(txtBuscar.getText());
            entregaAux = entregaAux.consultarEntrega(entregaAux);
            listaDeEntregas.clear();
            listaDeEntregas.add(entregaAux);
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
            //Limpiar la tabla de entregas
            while (tableEntregasModel.getRowCount() != 0) {
                tableEntregasModel.removeRow(0);
            }
            try {
                tableEntregasModel.addRow(new String[]{entregaAux.getFolioEntrega(), entregaAux.getFecha(),
                    entregaAux.getIdEmpleado(), entregaAux.getIdProveedor(),
                    Integer.toString(entregaAux.getIdProductos().length)});
            } catch (java.lang.NullPointerException e) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna entrega con ese folio");
                return;
            }
            //Buscamos al producto en la lista de productos
            for (int x = 0; x < entregaAux.getIdProductos().length; x++) {
                for (int y = 0; y < listaDeProductos.size(); y++) {
                    if (entregaAux.getIdProductos()[x].equals(listaDeProductos.get(y)[0])) {
                        datos = new String[5];
                        datos[0] = listaDeProductos.get(y)[0];
                        datos[1] = listaDeProductos.get(y)[1];
                        datos[2] = listaDeProductos.get(y)[2];
                        datos[3] = listaDeProductos.get(y)[3];
                        datos[4] = Integer.toString(entregaAux.getCantidades()[x]);
                        tableInfoModel.addRow(datos);
                        break;
                    }
                }
            }
            folioSeleccionado = entregaAux.getFolioEntrega();
        } catch (Exception ex) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        cbxProveedores = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        calFecha = new com.toedter.calendar.JDateChooser();
        txtFecha = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        chbFechaHoy = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cbxProductos = new javax.swing.JComboBox<>();
        btnFinalizar = new javax.swing.JButton();
        lblHerramientas = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbxEmpleados = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        scrlEntregas = new javax.swing.JScrollPane();
        tblEntregas = new javax.swing.JTable();
        scrlProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        scrlInfo = new javax.swing.JScrollPane();
        tblInfo = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnVerTodos = new javax.swing.JButton();

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
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 30, 40, 40));

        cbxProveedores.setBackground(new java.awt.Color(215, 215, 215));
        cbxProveedores.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxProveedores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxProveedores.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 210, -1));

        btnLimpiar.setBackground(new java.awt.Color(211, 211, 211));
        btnLimpiar.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(64, 64, 64));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 580, 110, 33));

        calFecha.setBackground(new java.awt.Color(215, 215, 215));
        calFecha.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 14), new java.awt.Color(0, 102, 153))); // NOI18N
        calFecha.setMinSelectableDate(new java.util.Date(-62135744286000L));
        calFecha.setMinimumSize(new java.awt.Dimension(85, 25));
        calFecha.setPreferredSize(new java.awt.Dimension(85, 25));
        add(calFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 25, -1));

        txtFecha.setBackground(new java.awt.Color(215, 215, 215));
        txtFecha.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        txtFecha.setFocusable(false);
        txtFecha.setPreferredSize(new java.awt.Dimension(64, 25));
        add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 270, -1));

        jLabel16.setBackground(new java.awt.Color(215, 215, 215));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(64, 64, 64));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Entregas recientes");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel16.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel16.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(170, 30));
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 540, -1));

        jLabel17.setBackground(new java.awt.Color(215, 215, 215));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(64, 64, 64));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Nueva entrega");
        jLabel17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel17.setMaximumSize(new java.awt.Dimension(213, 30));
        jLabel17.setMinimumSize(new java.awt.Dimension(213, 30));
        jLabel17.setOpaque(true);
        jLabel17.setPreferredSize(new java.awt.Dimension(213, 30));
        jLabel17.setRequestFocusEnabled(false);
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 500, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Panel de entregas");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 230, -1));

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cantidad");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 170, -1));

        chbFechaHoy.setBackground(new java.awt.Color(215, 215, 215));
        chbFechaHoy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chbFechaHoy.setForeground(new java.awt.Color(64, 64, 64));
        chbFechaHoy.setText("Usar la fecha de hoy ");
        chbFechaHoy.setOpaque(true);
        chbFechaHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbFechaHoyActionPerformed(evt);
            }
        });
        add(chbFechaHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 170, 170, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Fecha");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 300, -1));

        btnAgregar.setBackground(new java.awt.Color(211, 211, 211));
        btnAgregar.setForeground(new java.awt.Color(64, 64, 64));
        btnAgregar.setText("Agregar");
        btnAgregar.setFocusPainted(false);
        btnAgregar.setPreferredSize(new java.awt.Dimension(72, 25));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 100, -1));

        jLabel20.setBackground(new java.awt.Color(51, 51, 51));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("ID del Producto");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 210, -1));

        cbxProductos.setBackground(new java.awt.Color(215, 215, 215));
        cbxProductos.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxProductos.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 210, -1));

        btnFinalizar.setBackground(new java.awt.Color(183, 52, 0));
        btnFinalizar.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 18)); // NOI18N
        btnFinalizar.setForeground(new java.awt.Color(232, 232, 232));
        btnFinalizar.setText("Confirmar");
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        add(btnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 580, 120, 33));

        lblHerramientas.setBackground(new java.awt.Color(215, 215, 215));
        lblHerramientas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblHerramientas.setForeground(new java.awt.Color(64, 64, 64));
        lblHerramientas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHerramientas.setText("Herramientas del Gerente");
        lblHerramientas.setOpaque(true);
        add(lblHerramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, 500, 33));

        btnEliminar.setBackground(new java.awt.Color(183, 52, 0));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(232, 232, 232));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar entrega");
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
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 693, 220, 40));

        btnModificar.setBackground(new java.awt.Color(183, 52, 0));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(232, 232, 232));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEditar.png"))); // NOI18N
        btnModificar.setText("Modificar entrega");
        btnModificar.setToolTipText("Seleccione una de las entregas recientes y presione este boton para modificarla");
        btnModificar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnModificar.setFocusPainted(false);
        btnModificar.setIconTextGap(8);
        btnModificar.setPreferredSize(new java.awt.Dimension(75, 27));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 693, 210, 40));

        txtBuscar.setBackground(new java.awt.Color(215, 215, 215));
        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(142, 142, 142));
        txtBuscar.setText("Folio de la entrega...");
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 16, 1, 1));
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 280, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Buscar");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 35, 70, -1));

        jLabel22.setBackground(new java.awt.Color(51, 51, 51));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("ID del Proveedor");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 210, -1));

        cbxEmpleados.setBackground(new java.awt.Color(215, 215, 215));
        cbxEmpleados.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxEmpleados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxEmpleados.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, 210, -1));

        jLabel23.setBackground(new java.awt.Color(51, 51, 51));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Empleado que recibe");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, 210, -1));

        spnCantidad.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnCantidad.setBorder(null);
        spnCantidad.setPreferredSize(new java.awt.Dimension(64, 25));
        add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 170, 25));

        jLabel18.setBackground(new java.awt.Color(215, 215, 215));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(64, 64, 64));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Información de la entrega");
        jLabel18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jLabel18.setMaximumSize(new java.awt.Dimension(170, 30));
        jLabel18.setMinimumSize(new java.awt.Dimension(170, 30));
        jLabel18.setOpaque(true);
        jLabel18.setPreferredSize(new java.awt.Dimension(170, 30));
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 560, 540, -1));

        scrlEntregas.setBackground(new java.awt.Color(232, 232, 232));
        scrlEntregas.setForeground(new java.awt.Color(64, 64, 64));
        scrlEntregas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        scrlEntregas.setOpaque(false);

        tblEntregas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblEntregas.setRowHeight(25);
        scrlEntregas.setViewportView(tblEntregas);

        add(scrlEntregas, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 540, 390));

        scrlProductos.setBackground(new java.awt.Color(232, 232, 232));
        scrlProductos.setForeground(new java.awt.Color(64, 64, 64));
        scrlProductos.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N

        tblProductos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblProductos.setRowHeight(25);
        scrlProductos.setViewportView(tblProductos);

        add(scrlProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 500, 200));

        scrlInfo.setBackground(new java.awt.Color(232, 232, 232));
        scrlInfo.setForeground(new java.awt.Color(64, 64, 64));
        scrlInfo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        scrlInfo.setOpaque(false);

        tblInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblInfo.setRowHeight(25);
        scrlInfo.setViewportView(tblInfo);

        add(scrlInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 602, 540, 150));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 100, 20, 660));

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
        add(btnVerTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 30, 40, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        String mensaje;
        if (modificando) {
            mensaje = "¿Confirmar la modificación de la entrega?";
        } else {
            mensaje = "¿Confirmar el registro de la entrega?";
        }
        String date;
        if (JOptionPane.showConfirmDialog(null, mensaje, "Confirmar", JOptionPane.YES_OPTION) == 0) {
            date = txtFecha.getText();

            String[] productos = new String[tableProductosModel.getRowCount()];
            int[] cantidades = new int[tableProductosModel.getRowCount()];

            for (int x = 0; x < productos.length; x++) {
                productos[x] = tableProductosModel.getValueAt(x, 0).toString();
                cantidades[x] = Integer.parseInt(tableProductosModel.getValueAt(x, 3).toString());
                //System.out.println("Producto en " + x + ": " + productos[x]);
            }
            try {
                if (productos[0].isBlank() || cantidades[0] == 0) {
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "No se han agregado productos nuevos");
                return;
            }

            entregaAux = new Entregas();
            //System.out.println("Folio: " + entregaAux.generarFolio(date, cbxProveedores.getSelectedItem().toString()));

            if (modificando) {
                entregaAux = new Entregas(folioSeleccionado, date, productos, cantidades,
                        cbxProveedores.getSelectedItem().toString(), cbxEmpleados.getSelectedItem().toString());
                entregaAux.actualizarDatosEntregas(entregaAux);
            } else {
                entregaAux = new Entregas(entregaAux.generarFolio(date, cbxProveedores.getSelectedItem().toString()),
                        date, productos, cantidades, cbxProveedores.getSelectedItem().toString(),
                        cbxEmpleados.getSelectedItem().toString());
                if (date == null || date.equals("")) {
                    JOptionPane.showMessageDialog(null, "Introduzca una fecha válida");
                    return;
                }
                entregaAux.registrarEntrega(entregaAux);
            }
            modificando = false;
            btnAgregar.setEnabled(true);
            cbxProductos.setEnabled(true);
            spnCantidad.setEnabled(true);
            tblProductos.setEnabled(true);
            obtenerEntregasRecientes();
            limpiar();
        }

    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            entregaAux = new Entregas(tblEntregas.getValueAt(tblEntregas.getSelectedRow(), 0).toString());
            if (seleccionandoEntrega && !seleccionandoProducto) { //Entrega seleccionada pero no producto
                entregaAux.eliminarEntrega(entregaAux);
                obtenerEntregasRecientes();
                seleccionandoEntrega = false;
                seleccionandoProducto = false;
            } else if (!seleccionandoEntrega && seleccionandoProducto) { //Producto seleccionado pero no entrega
                entregaAux.eliminarProductoDeEntrega(tblInfo.getValueAt(tblInfo.getSelectedRow(), 0).toString(),
                        folioSeleccionado);

                //Para actualizar la tabla
                for (int i = 0; i < listaDeEntregas.size(); i++) {
                    if (folioSeleccionado.equals(listaDeEntregas.get(i).getFolioEntrega())) {
                        obtenerEntregasRecientes();
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
                    entregaAux.eliminarEntrega(entregaAux);
                    obtenerEntregasRecientes();
                    seleccionandoEntrega = false;
                    seleccionandoProducto = false;
                } else if (opcion == 1) {
                    entregaAux.eliminarProductoDeEntrega(tblInfo.getValueAt(tblInfo.getSelectedRow(), 0).toString(),
                            folioSeleccionado);

                    for (int i = 0; i < listaDeEntregas.size(); i++) {
                        if (folioSeleccionado.equals(listaDeEntregas.get(i).getFolioEntrega())) {
                            obtenerEntregasRecientes();
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
            entregaAux = new Entregas(tblEntregas.getValueAt(tblEntregas.getSelectedRow(), 0).toString());
            if (seleccionandoEntrega && !seleccionandoProducto) { //Entrega seleccionada pero no producto
                modificarEntrega();
                obtenerEntregasRecientes();
            } else if (!seleccionandoEntrega && seleccionandoProducto) { //Producto seleccionado pero no entrega
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto seleccionado"));
                entregaAux.actualizarCantidades(cantidad, tblInfo.getValueAt(tblInfo.getSelectedRow(), 0).toString());
                //Como la seleccion se quita al dar click en un boton, se debe buscar el folio en la lista de entregas
                //Para actualizar la tabla
                for (int i = 0; i < listaDeEntregas.size(); i++) {
                    if (folioSeleccionado.equals(listaDeEntregas.get(i).getFolioEntrega())) {
                        obtenerEntregasRecientes();
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
                    obtenerEntregasRecientes();
                } else if (opcion == 1) {
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto seleccionado"));
                    entregaAux.actualizarCantidades(cantidad, tblInfo.getValueAt(tblInfo.getSelectedRow(), 0).toString());
                    for (int i = 0; i < listaDeEntregas.size(); i++) {
                        if (folioSeleccionado.equals(listaDeEntregas.get(i).getFolioEntrega())) {
                            obtenerEntregasRecientes();
                            mostrarProductos(i);
                        }
                    }
                }
            }
        } catch (Exception ex) { //Ninguno seleccionado
            JOptionPane.showMessageDialog(null, "Primero seleccione un elemento en la tabla de entregas recientes");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void chbFechaHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbFechaHoyActionPerformed
        if (chbFechaHoy.isSelected()) {
            calFecha.setDate(new Date());
            calFecha.setEnabled(false);
        } else {
            calFecha.setEnabled(true);
        }
    }//GEN-LAST:event_chbFechaHoyActionPerformed

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
                        int enLaTabla = Integer.parseInt(tblProductos.getValueAt(i, 3).toString());
                        int enElSpinner = Integer.parseInt(spnCantidad.getValue().toString());
                        tblProductos.setValueAt((enLaTabla + enElSpinner), i, 3);

                    } else if (opcion == 1) {
                        //Corregir
                        tblProductos.setValueAt(Integer.parseInt(spnCantidad.getValue().toString()), i, 3);
                    }
                    return;
                }
            }
            productoAux = new Productos(cbxProductos.getSelectedItem().toString());
            String datos[] = new String[4];
            //System.out.println("Tamano de lista de productos" + listaDeProductos.size());
            for (int x = 0; x < listaDeProductos.size(); x++) {
                //System.out.println("En la lista: " + listaDeProductos.get(x)[0]);                
                if (cbxProductos.getSelectedItem().toString().equals(listaDeProductos.get(x)[0])) {
                    productoAux = productoAux.consultarProducto(productoAux);

                    datos[0] = productoAux.getIdProducto();
                    datos[1] = productoAux.getNombre();
                    datos[2] = productoAux.getMarca();
                    datos[3] = spnCantidad.getValue().toString();
                    tableProductosModel.addRow(datos);
                    break;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("En catch");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarEntrega();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        if (txtBuscar.getText().equals("Folio de la entrega...")) {
            txtBuscar.setText("");
            txtBuscar.setForeground(new Color(64, 64, 64));
        }
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        if (txtBuscar.getText().equals("")) {
            txtBuscar.setText("Folio de la entrega...");
            txtBuscar.setForeground(new Color(142, 142, 142));
        }
    }//GEN-LAST:event_txtBuscarFocusLost

    private void btnVerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosActionPerformed
        obtenerEntregasRecientes();
    }//GEN-LAST:event_btnVerTodosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnFinalizar;
    public static javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVerTodos;
    private com.toedter.calendar.JDateChooser calFecha;
    private static javax.swing.JComboBox<String> cbxEmpleados;
    private static javax.swing.JComboBox<String> cbxProductos;
    private static javax.swing.JComboBox<String> cbxProveedores;
    private javax.swing.JCheckBox chbFechaHoy;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHerramientas;
    private javax.swing.JScrollPane scrlEntregas;
    private javax.swing.JScrollPane scrlInfo;
    private javax.swing.JScrollPane scrlProductos;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblEntregas;
    private javax.swing.JTable tblInfo;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBuscar;
    private static javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}
