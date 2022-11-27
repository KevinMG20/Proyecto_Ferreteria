package Interfaz;

import Objetos.Productos;

import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Kevin MG
 */
public class PnlProductos extends javax.swing.JPanel {

    Productos productoAux = new Productos();
    SpinnerNumberModel spnModel = new SpinnerNumberModel();

    public PnlProductos() {
        initComponents();
        productoAux = new Productos();
        iniciarTabla();
        actualizarCategorias();
        llenarMarcas();

        ((JLabel) cbxCats.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) cbxMarca.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        spnModel.setStepSize(0);
        spnExistencias.setModel(spnModel);
        ((JSpinner.DefaultEditor) spnExistencias.getEditor()).getTextField().setEditable(false);
        spnExistencias.getEditor().getComponent(0).setBackground(new Color(215, 215, 215));

        JComponent editor = spnExistencias.getEditor();
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);

        rellenarTabla();
    }

    boolean editable = false;

    public void bloquearEdicion() {
        if (editable) {
            lblID.setBorder(BorderFactory.createEmptyBorder());
            txtNombre.setBorder(BorderFactory.createEmptyBorder());
            txtPrecio.setBorder(BorderFactory.createEmptyBorder());
            spnExistencias.setBorder(BorderFactory.createEmptyBorder());
            ((JSpinner.DefaultEditor) spnExistencias.getEditor()).getTextField().setEditable(false);
            spnModel.setStepSize(0);
            spnExistencias.setModel(spnModel);

            txtNombre.setEditable(false);
            txtPrecio.setEditable(false);
            editable = false;
        }
    }

    public void desbloquearEdicion() {
        if (!editable) {
            lblID.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            txtNombre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            txtPrecio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            spnExistencias.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            ((JSpinner.DefaultEditor) spnExistencias.getEditor()).getTextField().setEditable(true);
            spnModel.setStepSize(1);
            spnExistencias.setModel(spnModel);

            txtNombre.setEditable(true);
            txtPrecio.setEditable(true);
            editable = true;
        }
    }

    ArrayList<String> listaDeMarcas;

    public void llenarMarcas() {
        //productoAux = new Productos();
        listaDeMarcas = productoAux.consultarMarcas();
        //Actualizar el combobox de marcas
        cbxMarca.removeAllItems();
        cbxMarca.addItem("Nueva Marca");
        for (int x = 0; x < listaDeMarcas.size(); x++) {
            cbxMarca.addItem(listaDeMarcas.get(x));
        }
    }

    ArrayList<String> listaDeCategorias;
    int indexLista = 0;
    int paginaActual = 1;
    int totalPaginas = 1;

    public void actualizarCategorias() {
        listaDeCategorias = new ArrayList<>();
        //productoAux = new Productos();

        btnCat1.setVisible(false);
        lblCat1.setVisible(false);

        btnCat2.setVisible(false);
        lblCat2.setVisible(false);

        btnCat3.setVisible(false);
        lblCat3.setVisible(false);

        btnCat4.setVisible(false);
        lblCat4.setVisible(false);

        btnCat5.setVisible(false);
        lblCat5.setVisible(false);

        btnCat6.setVisible(false);
        lblCat6.setVisible(false);

        listaDeCategorias = productoAux.consultarCategorias();

        int tamanoLista = listaDeCategorias.size();

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

        try {
            if (listaDeCategorias != null) { //Asegurandonos que haya empleados registrados           

                lblCat1.setText(listaDeCategorias.get(indexLista));
                lblCat1.setVisible(true);
                asignarIconoCats(btnCat1, lblCat1.getText());
                btnCat1.setVisible(true);
                indexLista++;

                lblCat2.setText(listaDeCategorias.get(indexLista));
                lblCat2.setVisible(true);
                asignarIconoCats(btnCat2, lblCat2.getText());
                btnCat2.setVisible(true);
                indexLista++;

                lblCat3.setText(listaDeCategorias.get(indexLista));
                lblCat3.setVisible(true);
                asignarIconoCats(btnCat3, lblCat3.getText());
                btnCat3.setVisible(true);
                indexLista++;

                lblCat4.setText(listaDeCategorias.get(indexLista));
                lblCat4.setVisible(true);
                asignarIconoCats(btnCat4, lblCat4.getText());
                btnCat4.setVisible(true);
                indexLista++;

                lblCat5.setText(listaDeCategorias.get(indexLista));
                lblCat5.setVisible(true);
                asignarIconoCats(btnCat5, lblCat5.getText());
                btnCat5.setVisible(true);
                indexLista++;

                lblCat6.setText(listaDeCategorias.get(indexLista));
                lblCat6.setVisible(true);
                asignarIconoCats(btnCat6, lblCat6.getText());
                btnCat6.setVisible(true);
                indexLista++;
            }
        } catch (Exception ex) {
            System.out.println("Se llego al final de la lista de categorias de productos");
        }
        //Actualizar el combobox de categorias
        cbxCats.removeAllItems();
        cbxCats.addItem("Nueva Categoria");
        for (int x = 0; x < listaDeCategorias.size(); x++) {
            cbxCats.addItem(listaDeCategorias.get(x));
        }
        if (!"".equals(lblID.getText()) && lblID.getText() != null) {
            cbxCats.setSelectedItem(productoAux.getCategoria());
        }
    }

    public void asignarIconoCats(JButton btnCat, String cat) {
        //AsignarIcono        
        try {
            btnCat.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/Cat" + cat + ".png")));
        } catch (Exception e) {
            btnCat.setIcon(new ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatNoIcon.png")));
        }
    }

    public void iniciarTabla() {
        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Categoria");
        tableModel.addColumn("Marca");
        tableModel.addColumn("Precio");
        tableModel.addColumn("Existencias");

        tblProductos.setModel(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        JTableHeader th = tblProductos.getTableHeader();
        th.setFont(new Font("Segoe UI", Font.BOLD, 15));
        th.setForeground(Color.WHITE);
        tblProductos.getTableHeader().setBackground(new Color(2, 62, 138));

        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(25);
        tblProductos.getColumnModel().getColumn(4).setPreferredWidth(25);
        tblProductos.getColumnModel().getColumn(5).setPreferredWidth(25);

        tblProductos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblProductos.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblProductos.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblProductos.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblProductos.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblProductos.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        tblProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    productoAux = new Productos(tableModel.getValueAt(tblProductos.getSelectedRow(), 0).toString());
                    productoAux = productoAux.consultarProducto(productoAux);
                    cargarProducto(productoAux);
                    System.out.println("En listener");
                } catch (Exception ex) {
                    cargarProducto(null);
                }
                bloquearEdicion();
            }
        });

        /*tblProductos.getSelectionModel().addListSelectionListener((ListSelectionEvent ev) -> {
            try {
                productoAux = new Productos(tableModel.getValueAt(tblProductos.getSelectedRow(), 0).toString());
                productoAux = productoAux.consultarProducto(productoAux);
                cargarProducto(productoAux);
                System.out.println("En listener");
            } catch (Exception ex) {
                cargarProducto(null);
            }
            bloquearEdicion();
        });*/
    }

    public void cargarProducto(Productos datos) {
        if (datos != null) {
            System.out.println("Cargando objeto");
            lblID.setText(datos.getIdProducto());
            cbxMarca.setSelectedItem(datos.getMarca());
            txtNombre.setText(datos.getNombre());
            txtPrecio.setText(Float.toString(datos.getPrecio()));
            spnExistencias.setValue(datos.getExistencias());
            cbxCats.setSelectedItem(datos.getCategoria());
            asignarIconoCats(btnCatProducto, datos.getCategoria());
        } else { //Cargar vacio
            System.out.println("Cargando vacio");
            lblID.setText("");
            cbxMarca.setSelectedIndex(0);
            cbxCats.setSelectedIndex(0);
            txtNombre.setText("");
            txtPrecio.setText("");
            spnExistencias.setValue(0);
            asignarIconoCats(btnCatProducto, "Herramientas");
        }
    }

    ArrayList<String[]> listaDeProductos;
    String[] datos;
    DefaultTableModel tableModel;

    public void rellenarTabla() {
        //datos = new String[6];
        listaDeProductos = productoAux.consultarTodosLosProductos();
        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }

        for (int x = 0; x < listaDeProductos.size(); x++) {
            tableModel.addRow(listaDeProductos.get(x));
        }
    }

    public void rellenarConCategoria(String categoria) {
        listaDeProductos = productoAux.consultarTodosLosProductos();

        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }
        for (int x = 0; x < listaDeProductos.size(); x++) {
            if (categoria.equals(listaDeProductos.get(x)[2])) {
                tableModel.addRow(listaDeProductos.get(x));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCategorias = new javax.swing.JPanel();
        btnSiguiente = new javax.swing.JButton();
        btnSiguiente1 = new javax.swing.JButton();
        btnCat2 = new javax.swing.JButton();
        btnCat3 = new javax.swing.JButton();
        btnCat4 = new javax.swing.JButton();
        btnCat5 = new javax.swing.JButton();
        btnCat6 = new javax.swing.JButton();
        lblCat6 = new javax.swing.JLabel();
        lblCat4 = new javax.swing.JLabel();
        lblCat5 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblCat1 = new javax.swing.JLabel();
        lblCat3 = new javax.swing.JLabel();
        btnCat1 = new javax.swing.JButton();
        lblCat2 = new javax.swing.JLabel();
        pnlProducto = new javax.swing.JPanel();
        btnEditarDatos = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnCatProducto = new javax.swing.JButton();
        lblCategoria = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        spnExistencias = new javax.swing.JSpinner();
        txtPrecio = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        cbxCats = new javax.swing.JComboBox<>();
        cbxMarca = new javax.swing.JComboBox<>();
        pnlTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnVerTodos = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btnNuevoProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

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

        btnSiguiente.setBackground(new java.awt.Color(211, 211, 211));
        btnSiguiente.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(64, 64, 64));
        btnSiguiente.setText("<");
        btnSiguiente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSiguiente.setFocusPainted(false);
        btnSiguiente.setPreferredSize(new java.awt.Dimension(72, 25));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlCategorias.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 33));

        btnSiguiente1.setBackground(new java.awt.Color(211, 211, 211));
        btnSiguiente1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnSiguiente1.setForeground(new java.awt.Color(64, 64, 64));
        btnSiguiente1.setText(">");
        btnSiguiente1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSiguiente1.setFocusPainted(false);
        btnSiguiente1.setPreferredSize(new java.awt.Dimension(72, 25));
        btnSiguiente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguiente1ActionPerformed(evt);
            }
        });
        pnlCategorias.add(btnSiguiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 120, 33));

        btnCat2.setBackground(new java.awt.Color(232, 232, 232));
        btnCat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        btnCat2.setBorder(null);
        btnCat2.setFocusPainted(false);
        btnCat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCat2ActionPerformed(evt);
            }
        });
        pnlCategorias.add(btnCat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 120, 120));

        btnCat3.setBackground(new java.awt.Color(232, 232, 232));
        btnCat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        btnCat3.setBorder(null);
        btnCat3.setBorderPainted(false);
        btnCat3.setFocusPainted(false);
        btnCat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCat3ActionPerformed(evt);
            }
        });
        pnlCategorias.add(btnCat3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 120, 120));

        btnCat4.setBackground(new java.awt.Color(232, 232, 232));
        btnCat4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        btnCat4.setBorder(null);
        btnCat4.setBorderPainted(false);
        btnCat4.setFocusPainted(false);
        btnCat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCat4ActionPerformed(evt);
            }
        });
        pnlCategorias.add(btnCat4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 245, 120, 120));

        btnCat5.setBackground(new java.awt.Color(232, 232, 232));
        btnCat5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        btnCat5.setBorder(null);
        btnCat5.setBorderPainted(false);
        btnCat5.setFocusPainted(false);
        btnCat5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCat5ActionPerformed(evt);
            }
        });
        pnlCategorias.add(btnCat5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 245, 120, 120));

        btnCat6.setBackground(new java.awt.Color(232, 232, 232));
        btnCat6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        btnCat6.setBorder(null);
        btnCat6.setBorderPainted(false);
        btnCat6.setFocusPainted(false);
        btnCat6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCat6ActionPerformed(evt);
            }
        });
        pnlCategorias.add(btnCat6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 245, 120, 120));

        lblCat6.setBackground(new java.awt.Color(215, 215, 215));
        lblCat6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCat6.setForeground(new java.awt.Color(64, 64, 64));
        lblCat6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCat6.setText("Miscelaneos");
        lblCat6.setOpaque(true);
        pnlCategorias.add(lblCat6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, 121, -1));

        lblCat4.setBackground(new java.awt.Color(215, 215, 215));
        lblCat4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCat4.setForeground(new java.awt.Color(64, 64, 64));
        lblCat4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCat4.setText("Equipamento");
        lblCat4.setOpaque(true);
        pnlCategorias.add(lblCat4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 121, -1));

        lblCat5.setBackground(new java.awt.Color(215, 215, 215));
        lblCat5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCat5.setForeground(new java.awt.Color(64, 64, 64));
        lblCat5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCat5.setText("Accesorios");
        lblCat5.setOpaque(true);
        pnlCategorias.add(lblCat5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 121, -1));

        jLabel22.setBackground(new java.awt.Color(215, 215, 215));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(64, 64, 64));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Categorias");
        jLabel22.setOpaque(true);
        pnlCategorias.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 600, 33));

        lblCat1.setBackground(new java.awt.Color(215, 215, 215));
        lblCat1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCat1.setForeground(new java.awt.Color(64, 64, 64));
        lblCat1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCat1.setText("Herramientas");
        lblCat1.setOpaque(true);
        pnlCategorias.add(lblCat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 195, 121, -1));

        lblCat3.setBackground(new java.awt.Color(215, 215, 215));
        lblCat3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCat3.setForeground(new java.awt.Color(64, 64, 64));
        lblCat3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCat3.setText("Medición");
        lblCat3.setOpaque(true);
        pnlCategorias.add(lblCat3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 195, 121, -1));

        btnCat1.setBackground(new java.awt.Color(232, 232, 232));
        btnCat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        btnCat1.setBorder(null);
        btnCat1.setBorderPainted(false);
        btnCat1.setFocusPainted(false);
        btnCat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCat1ActionPerformed(evt);
            }
        });
        pnlCategorias.add(btnCat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 123, 123));

        lblCat2.setBackground(new java.awt.Color(215, 215, 215));
        lblCat2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCat2.setForeground(new java.awt.Color(64, 64, 64));
        lblCat2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCat2.setText("Escaleras");
        lblCat2.setOpaque(true);
        pnlCategorias.add(lblCat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 195, 121, -1));

        add(pnlCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 410));

        pnlProducto.setBackground(new java.awt.Color(232, 232, 232));
        pnlProducto.setMaximumSize(new java.awt.Dimension(870, 390));
        pnlProducto.setMinimumSize(new java.awt.Dimension(870, 390));
        pnlProducto.setPreferredSize(new java.awt.Dimension(870, 390));
        pnlProducto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditarDatos.setBackground(new java.awt.Color(211, 211, 211));
        btnEditarDatos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditarDatos.setForeground(new java.awt.Color(64, 64, 64));
        btnEditarDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEditar2.png"))); // NOI18N
        btnEditarDatos.setToolTipText("Desbloquear edicion");
        btnEditarDatos.setPreferredSize(new java.awt.Dimension(72, 25));
        btnEditarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDatosActionPerformed(evt);
            }
        });
        pnlProducto.add(btnEditarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 33, 33));

        btnBuscar.setBackground(new java.awt.Color(211, 211, 211));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(64, 64, 64));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoBuscar.png"))); // NOI18N
        btnBuscar.setPreferredSize(new java.awt.Dimension(72, 25));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlProducto.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 33, 33));

        jLabel1.setText("                         ");
        pnlProducto.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel21.setBackground(new java.awt.Color(215, 215, 215));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(64, 64, 64));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Datos del Producto");
        jLabel21.setOpaque(true);
        pnlProducto.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 360, 33));

        btnCatProducto.setBackground(new java.awt.Color(232, 232, 232));
        btnCatProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        btnCatProducto.setBorder(null);
        btnCatProducto.setBorderPainted(false);
        btnCatProducto.setContentAreaFilled(false);
        btnCatProducto.setFocusPainted(false);
        pnlProducto.add(btnCatProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 123, 123));

        lblCategoria.setBackground(new java.awt.Color(215, 215, 215));
        lblCategoria.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(64, 64, 64));
        lblCategoria.setText("Categoria");
        lblCategoria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlProducto.add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 110, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(64, 64, 64));
        jLabel9.setText("ID");
        pnlProducto.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(64, 64, 64));
        jLabel10.setText("Existencias");
        pnlProducto.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(64, 64, 64));
        jLabel11.setText("Marca");
        pnlProducto.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(64, 64, 64));
        jLabel12.setText("Nombre");
        pnlProducto.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(64, 64, 64));
        jLabel13.setText("Precio");
        pnlProducto.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        lblID.setBackground(new java.awt.Color(215, 215, 215));
        lblID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblID.setForeground(new java.awt.Color(64, 64, 64));
        lblID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblID.setText("  ");
        lblID.setOpaque(true);
        pnlProducto.add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 270, 30));

        spnExistencias.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        spnExistencias.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnExistencias.setBorder(null);
        spnExistencias.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProducto.add(spnExistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 340, 30));

        txtPrecio.setEditable(false);
        txtPrecio.setBackground(new java.awt.Color(215, 215, 215));
        txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtPrecio.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProducto.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 340, 30));

        txtNombre.setEditable(false);
        txtNombre.setBackground(new java.awt.Color(215, 215, 215));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNombre.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlProducto.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 340, 30));

        cbxCats.setBackground(new java.awt.Color(215, 215, 215));
        cbxCats.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbxCats.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbxCats.setPreferredSize(new java.awt.Dimension(72, 30));
        pnlProducto.add(cbxCats, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 340, -1));

        cbxMarca.setBackground(new java.awt.Color(215, 215, 215));
        cbxMarca.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbxMarca.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbxMarca.setPreferredSize(new java.awt.Dimension(72, 30));
        pnlProducto.add(cbxMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 240, -1));

        add(pnlProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 530, 430));

        pnlTabla.setBackground(new java.awt.Color(232, 232, 232));
        pnlTabla.setMaximumSize(new java.awt.Dimension(1200, 360));
        pnlTabla.setMinimumSize(new java.awt.Dimension(1200, 360));
        pnlTabla.setPreferredSize(new java.awt.Dimension(1200, 360));
        pnlTabla.setLayout(null);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(1200, 360));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1200, 360));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1200, 360));

        tblProductos.setBackground(new java.awt.Color(232, 232, 232));
        tblProductos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblProductos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Categoria", "Marca", "Precio", "Existencias"
            }
        ));
        tblProductos.setRowHeight(30);
        tblProductos.setRowMargin(5);
        jScrollPane1.setViewportView(tblProductos);

        pnlTabla.add(jScrollPane1);
        jScrollPane1.setBounds(30, 80, 1120, 260);

        btnVerTodos.setBackground(new java.awt.Color(211, 211, 211));
        btnVerTodos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnVerTodos.setForeground(new java.awt.Color(64, 64, 64));
        btnVerTodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoElementos.png"))); // NOI18N
        btnVerTodos.setToolTipText("Mostrar todos los productos");
        btnVerTodos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnVerTodos.setFocusPainted(false);
        btnVerTodos.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnVerTodos.setPreferredSize(new java.awt.Dimension(72, 25));
        btnVerTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodosActionPerformed(evt);
            }
        });
        pnlTabla.add(btnVerTodos);
        btnVerTodos.setBounds(600, 30, 33, 33);

        jLabel20.setBackground(new java.awt.Color(215, 215, 215));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(64, 64, 64));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Productos Disponibles");
        jLabel20.setOpaque(true);
        pnlTabla.add(jLabel20);
        jLabel20.setBounds(30, 30, 600, 33);

        btnNuevoProducto.setBackground(new java.awt.Color(211, 211, 211));
        btnNuevoProducto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnNuevoProducto.setForeground(new java.awt.Color(64, 64, 64));
        btnNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoAgregar.png"))); // NOI18N
        btnNuevoProducto.setText("Registrar");
        btnNuevoProducto.setIconTextGap(8);
        btnNuevoProducto.setMargin(new java.awt.Insets(2, 0, 2, 14));
        btnNuevoProducto.setPreferredSize(new java.awt.Dimension(72, 25));
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });
        pnlTabla.add(btnNuevoProducto);
        btnNuevoProducto.setBounds(710, 30, 130, 33);

        btnEliminarProducto.setBackground(new java.awt.Color(211, 211, 211));
        btnEliminarProducto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEliminarProducto.setForeground(new java.awt.Color(64, 64, 64));
        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEliminar2.png"))); // NOI18N
        btnEliminarProducto.setText("Eliminar");
        btnEliminarProducto.setIconTextGap(8);
        btnEliminarProducto.setMargin(new java.awt.Insets(2, 0, 2, 14));
        btnEliminarProducto.setPreferredSize(new java.awt.Dimension(72, 25));
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        pnlTabla.add(btnEliminarProducto);
        btnEliminarProducto.setBounds(1020, 30, 130, 33);

        btnModificar.setBackground(new java.awt.Color(211, 211, 211));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(64, 64, 64));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEditar3.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setIconTextGap(8);
        btnModificar.setMargin(new java.awt.Insets(2, 0, 2, 14));
        btnModificar.setPreferredSize(new java.awt.Dimension(72, 25));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlTabla.add(btnModificar);
        btnModificar.setBounds(860, 30, 140, 33);

        add(pnlTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 1200, 370));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguiente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguiente1ActionPerformed
        if (paginaActual < totalPaginas) {
            paginaActual++;
            actualizarCategorias();
        }
    }//GEN-LAST:event_btnSiguiente1ActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if (paginaActual > 1) {
            paginaActual--;
            indexLista = (int) Math.floor(indexLista / 6); //Dividir para obtener la pagina anterior, ej: 15 / 6 = 2.5 = 2
            indexLista = (indexLista * 6) - 6; //2 * 6 = 12 - 5 = 7; el index comenzara desde el 7 al 12, lo que corresponde a la pagina 2
            actualizarCategorias();
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        try {
            productoAux = new Productos(productoAux.generarID(txtNombre.getText()), txtNombre.getText(),
                    cbxCats.getSelectedItem().toString(), cbxMarca.getSelectedItem().toString(), Float.parseFloat(txtPrecio.getText()),
                    Integer.parseInt(spnExistencias.getValue().toString()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Verifique que se hayan ingresado correctamente todos los datos");
        }

        if (cbxMarca.getSelectedIndex() == 0) { //Si es una nueva marca se pide por JOptionaPane
            while (productoAux.getMarca() != null && productoAux.getMarca().equals(cbxMarca.getSelectedItem().toString())) {
                productoAux.setMarca(JOptionPane.showInputDialog("Ingrese el nombre de la marca del producto"));
            }
        }
        if (cbxCats.getSelectedIndex() == 0) { //Si es una nueva categoria se pide por JOptionaPane
            while (productoAux.getCategoria() != null && productoAux.getCategoria().equals(cbxCats.getSelectedItem().toString())) {
                productoAux.setCategoria(JOptionPane.showInputDialog("Ingrese el nombre de la nueva categoria de productos"));
            }
        }

        if (productoAux.getMarca() == null || productoAux.getCategoria() == null) { //En caso que no se hayan ingresado datos
            return;
        }

        productoAux.registrarProducto(productoAux);

        lblID.setText(productoAux.getIdProducto());
        String marcaTemp = productoAux.getMarca();
        String catTemp = productoAux.getCategoria();

        bloquearEdicion();
        indexLista = 0;

        actualizarCategorias();
        llenarMarcas();
        cbxMarca.setSelectedItem(marcaTemp);
        cbxCats.setSelectedItem(catTemp);

        rellenarTabla();
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void btnEditarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDatosActionPerformed
        lblID.setText("");
        cbxMarca.setSelectedIndex(0);
        cbxCats.setSelectedIndex(0);
        txtNombre.setText("");
        txtPrecio.setText("");
        spnExistencias.setValue(0);
        asignarIconoCats(btnCatProducto, "Herramientas");

        desbloquearEdicion();
    }//GEN-LAST:event_btnEditarDatosActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        if (!"".equals(lblID.getText()) && lblID.getText() != null) {
            productoAux = new Productos(lblID.getText());
            //productoAux = productoAux.consultarProducto(productoAux);            
            productoAux.eliminarProductos(lblID.getText());

            cargarProducto(null);
            indexLista = 0;
            actualizarCategorias();
            rellenarTabla();
            productoAux = new Productos();
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (!editable) {
            desbloquearEdicion();
        } else if (!"".equals(lblID.getText()) && lblID.getText() != null) {
            System.out.println("Preparando actualizacion de datos");
            productoAux = new Productos(lblID.getText());

            productoAux.setNombre(txtNombre.getText());
            productoAux.setMarca(cbxMarca.getSelectedItem().toString());
            productoAux.setCategoria(cbxCats.getSelectedItem().toString());
            productoAux.setPrecio(Float.parseFloat(txtPrecio.getText()));
            productoAux.setExistencias(Integer.parseInt(spnExistencias.getValue().toString()));

            productoAux.actualizarDatosProducto(productoAux);
            productoAux = productoAux.consultarProducto(productoAux);
            cargarProducto(productoAux);
            indexLista = 0;
            actualizarCategorias();
            rellenarTabla();
            bloquearEdicion();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        productoAux = new Productos(JOptionPane.showInputDialog("Ingrese el ID del Producto que desea buscar"));
        productoAux = productoAux.consultarProducto(productoAux);
        if (productoAux != null) {
            cargarProducto(productoAux);
        } else {
            JOptionPane.showMessageDialog(null, "No se hay ningún producto registrado con esa ID");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCat1ActionPerformed
        rellenarConCategoria(lblCat1.getText());
    }//GEN-LAST:event_btnCat1ActionPerformed

    private void btnCat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCat2ActionPerformed
        rellenarConCategoria(lblCat2.getText());
    }//GEN-LAST:event_btnCat2ActionPerformed

    private void btnCat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCat3ActionPerformed
        rellenarConCategoria(lblCat3.getText());
    }//GEN-LAST:event_btnCat3ActionPerformed

    private void btnCat4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCat4ActionPerformed
        rellenarConCategoria(lblCat4.getText());
    }//GEN-LAST:event_btnCat4ActionPerformed

    private void btnCat5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCat5ActionPerformed
        rellenarConCategoria(lblCat5.getText());
    }//GEN-LAST:event_btnCat5ActionPerformed

    private void btnCat6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCat6ActionPerformed
        rellenarConCategoria(lblCat6.getText());
    }//GEN-LAST:event_btnCat6ActionPerformed

    private void btnVerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosActionPerformed
        rellenarTabla();
    }//GEN-LAST:event_btnVerTodosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCat1;
    private javax.swing.JButton btnCat2;
    private javax.swing.JButton btnCat3;
    private javax.swing.JButton btnCat4;
    private javax.swing.JButton btnCat5;
    private javax.swing.JButton btnCat6;
    private javax.swing.JButton btnCatProducto;
    private javax.swing.JButton btnEditarDatos;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnSiguiente1;
    private javax.swing.JButton btnVerTodos;
    private javax.swing.JComboBox<String> cbxCats;
    private javax.swing.JComboBox<String> cbxMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCat1;
    private javax.swing.JLabel lblCat2;
    private javax.swing.JLabel lblCat3;
    private javax.swing.JLabel lblCat4;
    private javax.swing.JLabel lblCat5;
    private javax.swing.JLabel lblCat6;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblID;
    private javax.swing.JPanel pnlCategorias;
    private javax.swing.JPanel pnlProducto;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JSpinner spnExistencias;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
