package Interfaz;

import Objetos.Conexion;
import Objetos.Empleados;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kevin MG
 */
public class Principal extends javax.swing.JFrame implements FocusListener {

    //Variables
    Conexion con = new Conexion();
    Empleados usuario, empleadoModificar, empleadoConsultar, empleadoInsertar;

    ArrayList<Empleados> listaDeEmpleados;
    int indexLista = 0;
    int paginaActual = 1;
    int totalPaginas = 1;
    public String operacion = "";
    JPanel pnlVentas = new PnlVentas();
    JPanel pnlEntregas = new PnlEntregas();
    JPanel pnlGrupos = new PnlProductos();
    JPanel pnlInscribir = new PnlEntregas();
    JPanel pnlFinanzas = new PnlProveedores();
    JPanel pnlDerecho = new JPanel();
    JPanel pnlIzquierdo = new JPanel();

    //Metodos
    public void limpiar() {
        txtIdEmpleado.setText("");
        txtNombreEmpleado.setText("");
        /*cbxPuestoEmpleado.setSelectedIndex(0);
        cbxTurnoEmpleado.setSelectedIndex(0);*/
    }

    public void actualizarEmpleados() {
        listaDeEmpleados = new ArrayList<>();
        lblEmpleado1.setVisible(false);
        btnEmpleado1.setVisible(false);

        lblEmpleado2.setVisible(false);
        btnEmpleado2.setVisible(false);

        lblEmpleado3.setVisible(false);
        btnEmpleado3.setVisible(false);

        lblEmpleado4.setVisible(false);
        btnEmpleado4.setVisible(false);

        lblEmpleado5.setVisible(false);
        btnEmpleado5.setVisible(false);

        lblEmpleado6.setVisible(false);
        btnEmpleado6.setVisible(false);

        listaDeEmpleados = usuario.consultarEmpleados();

        int tamanoLista = listaDeEmpleados.size();

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
            if (listaDeEmpleados != null) { //Asegurandonos que haya empleados registrados           

                lblEmpleado1.setText(listaDeEmpleados.get(indexLista).getNombre());
                lblEmpleado1.setVisible(true);
                btnEmpleado1.setVisible(true);
                indexLista++;

                lblEmpleado2.setText(listaDeEmpleados.get(indexLista).getNombre());
                lblEmpleado2.setVisible(true);
                btnEmpleado2.setVisible(true);
                indexLista++;

                lblEmpleado3.setText(listaDeEmpleados.get(indexLista).getNombre());
                lblEmpleado3.setVisible(true);
                btnEmpleado3.setVisible(true);
                indexLista++;

                lblEmpleado4.setText(listaDeEmpleados.get(indexLista).getNombre());
                lblEmpleado4.setVisible(true);
                btnEmpleado4.setVisible(true);
                indexLista++;

                lblEmpleado5.setText(listaDeEmpleados.get(indexLista).getNombre());
                lblEmpleado5.setVisible(true);
                btnEmpleado5.setVisible(true);
                indexLista++;

                lblEmpleado6.setText(listaDeEmpleados.get(indexLista).getNombre());
                lblEmpleado6.setVisible(true);
                btnEmpleado6.setVisible(true);
                indexLista++;

            }
        } catch (Exception ex) {
            System.out.println("Se llego al final de la lista de empleados");
        }
    }

    public void actualizarTodo() {
        usuario = usuario.consultarEmpleado(usuario);
        iniciarDatos();
        txtIdOperacion.setText("");
        lblInfoEmpleado.setText("  ");
        pnlHerramientas.setVisible(true);
        pnlOperaciones.setVisible(false);
        pnlDatosOperaciones.setVisible(false);
        pnlFamilia.setVisible(true);
    }

    public void iniciarDatos() {
        lblBienvenida.setText("Bienvenido " + usuario.getNombre());
        lblFecha.setText(obtenerFechaActual());

        showID.setText(usuario.getIdEmpleado());
        showNombre.setText(usuario.getNombre());
        showPuestoTXT.setText(usuario.getPuesto());
        showTurnoTXT.setText(usuario.getTurno());
        indexLista = 0;
        paginaActual = 1;
        actualizarEmpleados();
    }

    public void rellenarDatosDeOperacion(Empleados empleado) {
        txtIdEmpleado.setText(empleado.getIdEmpleado());
        txtNombreEmpleado.setText(empleado.getNombre());
        rellenarPuestos(empleado);
        cbxPuestoEmpleado.setSelectedItem(empleado.getPuesto());
        cbxTurnoEmpleado.setSelectedItem(empleado.getTurno());
    }

    public void rellenarPuestos(Empleados empleado) {
        //Consulta para obtener todos los puestos de la base de datos y rellenar el combobox con ellos
        ArrayList<String> listaPuestos = empleado.consultarPuestos();
        cbxPuestoEmpleado.removeAllItems();
        showPuestoCBX.removeAllItems();
        for (int x = 0; x < listaPuestos.size(); x++) {
            cbxPuestoEmpleado.addItem(listaPuestos.get(x));
            showPuestoCBX.addItem(listaPuestos.get(x));
        }

    }

    public static String obtenerFechaActual() {
        String fechaActual = "";

        //Obtener dia de la semana
        Date diaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(diaActual);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                fechaActual += "Domingo ";
                break;
            case 2:
                fechaActual += "Lunes ";
                break;
            case 3:
                fechaActual += "Martes ";
                break;
            case 4:
                fechaActual += "Miércoles ";
                break;
            case 5:
                fechaActual += "Jueves ";
                break;
            case 6:
                fechaActual += "Viernes ";
                break;
            case 7:
                fechaActual += "Sábado ";
                break;
            default:
                break;
        }
        //Obtener dia del mes
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        fechaActual += dateFormat.format(diaActual) + " de ";

        //Obtener mes actual
        Month mesActual = LocalDate.now().getMonth();
        //Obtener el nombre del mes actual
        String nombreDelMes = mesActual.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        //Convertir la primera letra a mayusculas
        String primeraLetra = nombreDelMes.substring(0, 1);
        primeraLetra = primeraLetra.toUpperCase();
        nombreDelMes = primeraLetra + nombreDelMes.substring(1);
        fechaActual += nombreDelMes;

        fechaActual += " de " + LocalDate.now().getYear();

        return fechaActual;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnEmpleados = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnEntregas = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pnlPrincipal = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCancelarPerfil = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblBienvenida = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnModificar1 = new javax.swing.JButton();
        btnListo = new javax.swing.JButton();
        lblHerramientasDelGerente = new javax.swing.JLabel();
        pnlFamilia = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        btnEmpleado3 = new javax.swing.JButton();
        lblEmpleado3 = new javax.swing.JLabel();
        lblEmpleado2 = new javax.swing.JLabel();
        btnEmpleado2 = new javax.swing.JButton();
        btnEmpleado1 = new javax.swing.JButton();
        lblEmpleado1 = new javax.swing.JLabel();
        btnEmpleado4 = new javax.swing.JButton();
        lblEmpleado4 = new javax.swing.JLabel();
        btnEmpleado5 = new javax.swing.JButton();
        lblEmpleado5 = new javax.swing.JLabel();
        btnEmpleado6 = new javax.swing.JButton();
        lblEmpleado6 = new javax.swing.JLabel();
        btnNextPage = new javax.swing.JButton();
        lblPaginas = new javax.swing.JLabel();
        btnLastPage = new javax.swing.JButton();
        pnlDatosOperaciones = new javax.swing.JPanel();
        lblTitulo_PnlDatosOperaciones = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnCancelarOperacion1 = new javax.swing.JButton();
        cbxTurnoEmpleado = new javax.swing.JComboBox<>();
        btnConfirmarOperacion1 = new javax.swing.JButton();
        txtIdEmpleado = new javax.swing.JTextField();
        txtNombreEmpleado = new javax.swing.JTextField();
        cbxPuestoEmpleado = new javax.swing.JComboBox<>();
        pnlHerramientas = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnInsertarEmpleado = new javax.swing.JButton();
        btnEditarEmpleados = new javax.swing.JButton();
        btnEliminarEmpleados = new javax.swing.JButton();
        pnlOperaciones = new javax.swing.JPanel();
        lblTitulo_PnlOperaciones = new javax.swing.JLabel();
        lblInfoEmpleado = new javax.swing.JLabel();
        txtIdOperacion = new javax.swing.JTextField();
        btnCancelarOperacion = new javax.swing.JButton();
        btnConfirmarOperacion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        showTurnoTXT = new javax.swing.JTextField();
        showNombre = new javax.swing.JTextField();
        showPuestoTXT = new javax.swing.JTextField();
        showID = new javax.swing.JTextField();
        showTurnoCBX = new javax.swing.JComboBox<>();
        showPuestoCBX = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(187, 187, 187));
        setMinimumSize(new java.awt.Dimension(1410, 780));
        setUndecorated(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnlMenu.setBackground(new java.awt.Color(183, 52, 0));
        pnlMenu.setMaximumSize(new java.awt.Dimension(210, 781));
        pnlMenu.setMinimumSize(new java.awt.Dimension(210, 781));
        pnlMenu.setPreferredSize(new java.awt.Dimension(210, 780));
        pnlMenu.setRequestFocusEnabled(false);
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEmpleados.setBackground(new java.awt.Color(183, 52, 0));
        btnEmpleados.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        btnEmpleados.setForeground(new java.awt.Color(232, 230, 230));
        btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEmpleados.png"))); // NOI18N
        btnEmpleados.setText("Empleados");
        btnEmpleados.setAlignmentY(0.0F);
        btnEmpleados.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEmpleados.setFocusPainted(false);
        btnEmpleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmpleados.setIconTextGap(15);
        btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 210, 80));

        btnProductos.setBackground(new java.awt.Color(183, 52, 0));
        btnProductos.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        btnProductos.setForeground(new java.awt.Color(232, 230, 230));
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoProductos.png"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProductos.setFocusPainted(false);
        btnProductos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProductos.setIconTextGap(15);
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 210, 80));

        btnProveedores.setBackground(new java.awt.Color(183, 52, 0));
        btnProveedores.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        btnProveedores.setForeground(new java.awt.Color(232, 230, 230));
        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoProveedores.png"))); // NOI18N
        btnProveedores.setText("Proveedores");
        btnProveedores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProveedores.setFocusPainted(false);
        btnProveedores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProveedores.setIconTextGap(15);
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        pnlMenu.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 210, 80));

        btnSalir.setBackground(new java.awt.Color(183, 52, 0));
        btnSalir.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(232, 230, 230));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoSalir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir.setFocusPainted(false);
        btnSalir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSalir.setIconTextGap(15);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlMenu.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 210, 80));

        btnEntregas.setBackground(new java.awt.Color(183, 52, 0));
        btnEntregas.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        btnEntregas.setForeground(new java.awt.Color(232, 230, 230));
        btnEntregas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEntregas.png"))); // NOI18N
        btnEntregas.setText("Entregas");
        btnEntregas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEntregas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEntregas.setFocusPainted(false);
        btnEntregas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEntregas.setIconTextGap(15);
        btnEntregas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregasActionPerformed(evt);
            }
        });
        pnlMenu.add(btnEntregas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 210, 80));

        btnVentas.setBackground(new java.awt.Color(183, 52, 0));
        btnVentas.setFont(new java.awt.Font("Segoe UI Semilight", 1, 16)); // NOI18N
        btnVentas.setForeground(new java.awt.Color(232, 230, 230));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoFinanzas.png"))); // NOI18N
        btnVentas.setText("Ventas");
        btnVentas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVentas.setFocusPainted(false);
        btnVentas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVentas.setIconTextGap(15);
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        pnlMenu.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 210, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/Logo.png"))); // NOI18N
        pnlMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 200));

        getContentPane().add(pnlMenu);

        pnlPrincipal.setBackground(new java.awt.Color(232, 232, 232));
        pnlPrincipal.setMaximumSize(new java.awt.Dimension(1200, 780));
        pnlPrincipal.setMinimumSize(new java.awt.Dimension(1200, 780));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(1200, 780));
        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Turno");
        pnlPrincipal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 50, -1));

        btnCancelarPerfil.setBackground(new java.awt.Color(215, 215, 215));
        btnCancelarPerfil.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCancelarPerfil.setForeground(new java.awt.Color(64, 64, 64));
        btnCancelarPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEliminar.png"))); // NOI18N
        btnCancelarPerfil.setText("Cancelar");
        btnCancelarPerfil.setFocusPainted(false);
        btnCancelarPerfil.setIconTextGap(8);
        btnCancelarPerfil.setMargin(new java.awt.Insets(2, 1, 2, 14));
        btnCancelarPerfil.setPreferredSize(new java.awt.Dimension(75, 27));
        btnCancelarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPerfilActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnCancelarPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 470, 130, 40));

        btnPDF.setBackground(new java.awt.Color(183, 52, 0));
        btnPDF.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoPDF.png"))); // NOI18N
        btnPDF.setText("Generar Reportes");
        btnPDF.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPDF.setBorderPainted(false);
        btnPDF.setFocusPainted(false);
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 290, 50));

        lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(102, 102, 102));
        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha.setText("Fecha");
        pnlPrincipal.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 400, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombre");
        pnlPrincipal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 70, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoTurno.png"))); // NOI18N
        pnlPrincipal.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 405, 30, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoCandado.png"))); // NOI18N
        pnlPrincipal.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 165, 30, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoNombre.png"))); // NOI18N
        pnlPrincipal.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 245, 30, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoPuesto.png"))); // NOI18N
        pnlPrincipal.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 325, 30, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/retrato (2).png"))); // NOI18N
        pnlPrincipal.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 130, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("ID");
        pnlPrincipal.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 20, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlPrincipal.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, -1, 640));

        lblBienvenida.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblBienvenida.setForeground(new java.awt.Color(0, 0, 0));
        lblBienvenida.setText("Bienvenido NOMBRE");
        pnlPrincipal.add(lblBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 650, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Puesto");
        pnlPrincipal.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 50, -1));

        jLabel9.setBackground(new java.awt.Color(215, 215, 215));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(64, 64, 64));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Acciones del Perfil");
        jLabel9.setOpaque(true);
        pnlPrincipal.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 650, 33));

        jLabel20.setBackground(new java.awt.Color(215, 215, 215));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(64, 64, 64));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Datos del Perfil");
        jLabel20.setOpaque(true);
        pnlPrincipal.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 650, 33));

        btnModificar1.setBackground(new java.awt.Color(183, 52, 0));
        btnModificar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar1.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEditar.png"))); // NOI18N
        btnModificar1.setText("Modificar Datos");
        btnModificar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnModificar1.setFocusPainted(false);
        btnModificar1.setIconTextGap(8);
        btnModificar1.setPreferredSize(new java.awt.Dimension(75, 27));
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnModificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 610, 290, 50));

        btnListo.setBackground(new java.awt.Color(183, 52, 0));
        btnListo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnListo.setForeground(new java.awt.Color(255, 255, 255));
        btnListo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEditar.png"))); // NOI18N
        btnListo.setText("Confirmar");
        btnListo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnListo.setFocusPainted(false);
        btnListo.setIconTextGap(8);
        btnListo.setPreferredSize(new java.awt.Dimension(75, 27));
        btnListo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListoActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnListo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, 140, 40));

        lblHerramientasDelGerente.setBackground(new java.awt.Color(215, 215, 215));
        lblHerramientasDelGerente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblHerramientasDelGerente.setForeground(new java.awt.Color(64, 64, 64));
        lblHerramientasDelGerente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHerramientasDelGerente.setText("Herramientas del Gerente");
        lblHerramientasDelGerente.setOpaque(true);
        pnlPrincipal.add(lblHerramientasDelGerente, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 500, 400, 33));

        pnlFamilia.setBackground(new java.awt.Color(232, 232, 232));
        pnlFamilia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setBackground(new java.awt.Color(215, 215, 215));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(64, 64, 64));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Familia Ferreteria");
        jLabel23.setOpaque(true);
        pnlFamilia.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 400, 33));

        btnEmpleado3.setBackground(new java.awt.Color(232, 232, 232));
        btnEmpleado3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEmpleado3.setForeground(new java.awt.Color(64, 64, 64));
        btnEmpleado3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/usuario (2).png"))); // NOI18N
        btnEmpleado3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 1, 1));
        btnEmpleado3.setBorderPainted(false);
        btnEmpleado3.setFocusPainted(false);
        btnEmpleado3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmpleado3.setIconTextGap(0);
        btnEmpleado3.setMargin(new java.awt.Insets(8, 0, 2, 14));
        btnEmpleado3.setPreferredSize(new java.awt.Dimension(64, 64));
        btnEmpleado3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleado3ActionPerformed(evt);
            }
        });
        pnlFamilia.add(btnEmpleado3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        lblEmpleado3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEmpleado3.setForeground(new java.awt.Color(0, 0, 0));
        lblEmpleado3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpleado3.setText("Empleado 3");
        pnlFamilia.add(lblEmpleado3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 130, -1));

        lblEmpleado2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEmpleado2.setForeground(new java.awt.Color(0, 0, 0));
        lblEmpleado2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpleado2.setText("Empleado 2");
        pnlFamilia.add(lblEmpleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 140, 130, -1));

        btnEmpleado2.setBackground(new java.awt.Color(232, 232, 232));
        btnEmpleado2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEmpleado2.setForeground(new java.awt.Color(64, 64, 64));
        btnEmpleado2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/usuario (2).png"))); // NOI18N
        btnEmpleado2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 1, 1));
        btnEmpleado2.setBorderPainted(false);
        btnEmpleado2.setFocusPainted(false);
        btnEmpleado2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmpleado2.setIconTextGap(0);
        btnEmpleado2.setMargin(new java.awt.Insets(8, 0, 2, 14));
        btnEmpleado2.setPreferredSize(new java.awt.Dimension(64, 64));
        btnEmpleado2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleado2ActionPerformed(evt);
            }
        });
        pnlFamilia.add(btnEmpleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 50, -1, -1));

        btnEmpleado1.setBackground(new java.awt.Color(232, 232, 232));
        btnEmpleado1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEmpleado1.setForeground(new java.awt.Color(64, 64, 64));
        btnEmpleado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/usuario (2).png"))); // NOI18N
        btnEmpleado1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 1, 1));
        btnEmpleado1.setBorderPainted(false);
        btnEmpleado1.setFocusPainted(false);
        btnEmpleado1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmpleado1.setIconTextGap(0);
        btnEmpleado1.setMargin(new java.awt.Insets(8, 0, 2, 14));
        btnEmpleado1.setPreferredSize(new java.awt.Dimension(64, 64));
        btnEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleado1ActionPerformed(evt);
            }
        });
        pnlFamilia.add(btnEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        lblEmpleado1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEmpleado1.setForeground(new java.awt.Color(0, 0, 0));
        lblEmpleado1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpleado1.setText("Empleado 1");
        pnlFamilia.add(lblEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 130, -1));

        btnEmpleado4.setBackground(new java.awt.Color(232, 232, 232));
        btnEmpleado4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEmpleado4.setForeground(new java.awt.Color(64, 64, 64));
        btnEmpleado4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/usuario (2).png"))); // NOI18N
        btnEmpleado4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 1, 1));
        btnEmpleado4.setBorderPainted(false);
        btnEmpleado4.setFocusPainted(false);
        btnEmpleado4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmpleado4.setIconTextGap(0);
        btnEmpleado4.setMargin(new java.awt.Insets(8, 0, 2, 14));
        btnEmpleado4.setPreferredSize(new java.awt.Dimension(64, 64));
        btnEmpleado4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleado4ActionPerformed(evt);
            }
        });
        pnlFamilia.add(btnEmpleado4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        lblEmpleado4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEmpleado4.setForeground(new java.awt.Color(0, 0, 0));
        lblEmpleado4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpleado4.setText("Empleado 4");
        pnlFamilia.add(lblEmpleado4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 130, -1));

        btnEmpleado5.setBackground(new java.awt.Color(232, 232, 232));
        btnEmpleado5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEmpleado5.setForeground(new java.awt.Color(64, 64, 64));
        btnEmpleado5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/usuario (2).png"))); // NOI18N
        btnEmpleado5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 1, 1));
        btnEmpleado5.setBorderPainted(false);
        btnEmpleado5.setFocusPainted(false);
        btnEmpleado5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmpleado5.setIconTextGap(0);
        btnEmpleado5.setMargin(new java.awt.Insets(8, 0, 2, 14));
        btnEmpleado5.setPreferredSize(new java.awt.Dimension(64, 64));
        btnEmpleado5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleado5ActionPerformed(evt);
            }
        });
        pnlFamilia.add(btnEmpleado5, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 190, -1, -1));

        lblEmpleado5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEmpleado5.setForeground(new java.awt.Color(0, 0, 0));
        lblEmpleado5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpleado5.setText("Empleado 5");
        pnlFamilia.add(lblEmpleado5, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 280, 130, -1));

        btnEmpleado6.setBackground(new java.awt.Color(232, 232, 232));
        btnEmpleado6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEmpleado6.setForeground(new java.awt.Color(64, 64, 64));
        btnEmpleado6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/usuario (2).png"))); // NOI18N
        btnEmpleado6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 1, 1));
        btnEmpleado6.setBorderPainted(false);
        btnEmpleado6.setFocusPainted(false);
        btnEmpleado6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmpleado6.setIconTextGap(0);
        btnEmpleado6.setMargin(new java.awt.Insets(8, 0, 2, 14));
        btnEmpleado6.setPreferredSize(new java.awt.Dimension(64, 64));
        btnEmpleado6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleado6ActionPerformed(evt);
            }
        });
        pnlFamilia.add(btnEmpleado6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, -1, -1));

        lblEmpleado6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEmpleado6.setForeground(new java.awt.Color(0, 0, 0));
        lblEmpleado6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpleado6.setText("Empleado 6");
        pnlFamilia.add(lblEmpleado6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 130, -1));

        btnNextPage.setBackground(new java.awt.Color(183, 52, 0));
        btnNextPage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNextPage.setForeground(new java.awt.Color(255, 255, 255));
        btnNextPage.setText(">");
        btnNextPage.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNextPage.setFocusPainted(false);
        btnNextPage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNextPage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageActionPerformed(evt);
            }
        });
        pnlFamilia.add(btnNextPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 100, 30));

        lblPaginas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPaginas.setForeground(new java.awt.Color(64, 64, 64));
        lblPaginas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaginas.setText("Página 1 de 1");
        pnlFamilia.add(lblPaginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 345, 140, -1));

        btnLastPage.setBackground(new java.awt.Color(183, 52, 0));
        btnLastPage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLastPage.setForeground(new java.awt.Color(255, 255, 255));
        btnLastPage.setText("<");
        btnLastPage.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnLastPage.setFocusPainted(false);
        btnLastPage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLastPage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageActionPerformed(evt);
            }
        });
        pnlFamilia.add(btnLastPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 100, 30));

        pnlPrincipal.add(pnlFamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 450, 390));

        pnlDatosOperaciones.setBackground(new java.awt.Color(232, 232, 232));
        pnlDatosOperaciones.setMinimumSize(new java.awt.Dimension(460, 549));
        pnlDatosOperaciones.setPreferredSize(new java.awt.Dimension(460, 549));
        pnlDatosOperaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo_PnlDatosOperaciones.setBackground(new java.awt.Color(215, 215, 215));
        lblTitulo_PnlDatosOperaciones.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lblTitulo_PnlDatosOperaciones.setForeground(new java.awt.Color(64, 64, 64));
        lblTitulo_PnlDatosOperaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo_PnlDatosOperaciones.setText("Modificar Empleado");
        lblTitulo_PnlDatosOperaciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        lblTitulo_PnlDatosOperaciones.setMaximumSize(new java.awt.Dimension(213, 30));
        lblTitulo_PnlDatosOperaciones.setMinimumSize(new java.awt.Dimension(213, 30));
        lblTitulo_PnlDatosOperaciones.setOpaque(true);
        lblTitulo_PnlDatosOperaciones.setPreferredSize(new java.awt.Dimension(213, 33));
        lblTitulo_PnlDatosOperaciones.setRequestFocusEnabled(false);
        pnlDatosOperaciones.add(lblTitulo_PnlDatosOperaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 400, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(64, 64, 64));
        jLabel16.setText("ID");
        pnlDatosOperaciones.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(64, 64, 64));
        jLabel18.setText("Nombre");
        pnlDatosOperaciones.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(64, 64, 64));
        jLabel19.setText("Puesto");
        pnlDatosOperaciones.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(64, 64, 64));
        jLabel21.setText("Turno");
        pnlDatosOperaciones.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        btnCancelarOperacion1.setBackground(new java.awt.Color(215, 215, 215));
        btnCancelarOperacion1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCancelarOperacion1.setForeground(new java.awt.Color(64, 64, 64));
        btnCancelarOperacion1.setText("Cancelar");
        btnCancelarOperacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarOperacion1ActionPerformed(evt);
            }
        });
        pnlDatosOperaciones.add(btnCancelarOperacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 110, 30));

        cbxTurnoEmpleado.setBackground(new java.awt.Color(215, 215, 215));
        cbxTurnoEmpleado.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxTurnoEmpleado.setForeground(new java.awt.Color(64, 64, 64));
        cbxTurnoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino" }));
        cbxTurnoEmpleado.setBorder(null);
        cbxTurnoEmpleado.setOpaque(true);
        cbxTurnoEmpleado.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlDatosOperaciones.add(cbxTurnoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 270, -1));

        btnConfirmarOperacion1.setBackground(new java.awt.Color(183, 52, 0));
        btnConfirmarOperacion1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnConfirmarOperacion1.setForeground(new java.awt.Color(232, 232, 232));
        btnConfirmarOperacion1.setText("Confirmar");
        btnConfirmarOperacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarOperacion1ActionPerformed(evt);
            }
        });
        pnlDatosOperaciones.add(btnConfirmarOperacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 120, 30));

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

        pnlPrincipal.add(pnlDatosOperaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, -1, 420));

        pnlHerramientas.setBackground(new java.awt.Color(232, 232, 232));
        pnlHerramientas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconosFerreteria/CatHerramientas.png"))); // NOI18N
        pnlHerramientas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        btnInsertarEmpleado.setBackground(new java.awt.Color(183, 52, 0));
        btnInsertarEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnInsertarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        btnInsertarEmpleado.setText("Nuevo Trabajador");
        btnInsertarEmpleado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInsertarEmpleado.setFocusPainted(false);
        btnInsertarEmpleado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInsertarEmpleado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnInsertarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarEmpleadoActionPerformed(evt);
            }
        });
        pnlHerramientas.add(btnInsertarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 240, 35));

        btnEditarEmpleados.setBackground(new java.awt.Color(183, 52, 0));
        btnEditarEmpleados.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEditarEmpleados.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarEmpleados.setText("Modificar empleados");
        btnEditarEmpleados.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEditarEmpleados.setFocusPainted(false);
        btnEditarEmpleados.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditarEmpleados.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEmpleadosActionPerformed(evt);
            }
        });
        pnlHerramientas.add(btnEditarEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 240, 35));

        btnEliminarEmpleados.setBackground(new java.awt.Color(183, 52, 0));
        btnEliminarEmpleados.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminarEmpleados.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarEmpleados.setText("Dar de baja empleados");
        btnEliminarEmpleados.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEliminarEmpleados.setFocusPainted(false);
        btnEliminarEmpleados.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminarEmpleados.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpleadosActionPerformed(evt);
            }
        });
        pnlHerramientas.add(btnEliminarEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 390, 35));

        pnlPrincipal.add(pnlHerramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 560, 400, 190));

        pnlOperaciones.setBackground(new java.awt.Color(232, 232, 232));
        pnlOperaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo_PnlOperaciones.setBackground(new java.awt.Color(215, 215, 215));
        lblTitulo_PnlOperaciones.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo_PnlOperaciones.setForeground(new java.awt.Color(64, 64, 64));
        lblTitulo_PnlOperaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo_PnlOperaciones.setText("Operación");
        lblTitulo_PnlOperaciones.setOpaque(true);
        lblTitulo_PnlOperaciones.setPreferredSize(new java.awt.Dimension(88, 30));
        pnlOperaciones.add(lblTitulo_PnlOperaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 370, -1));

        lblInfoEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblInfoEmpleado.setForeground(java.awt.Color.red);
        lblInfoEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfoEmpleado.setText("  ");
        pnlOperaciones.add(lblInfoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 110, 310, -1));

        txtIdOperacion.setBackground(new java.awt.Color(232, 232, 232));
        txtIdOperacion.setForeground(new java.awt.Color(64, 64, 64));
        txtIdOperacion.setPreferredSize(new java.awt.Dimension(64, 25));
        pnlOperaciones.add(txtIdOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 90, 310, -1));

        btnCancelarOperacion.setBackground(new java.awt.Color(215, 215, 215));
        btnCancelarOperacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCancelarOperacion.setForeground(new java.awt.Color(64, 64, 64));
        btnCancelarOperacion.setText("Cancelar");
        btnCancelarOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarOperacionActionPerformed(evt);
            }
        });
        pnlOperaciones.add(btnCancelarOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        btnConfirmarOperacion.setBackground(new java.awt.Color(183, 52, 0));
        btnConfirmarOperacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnConfirmarOperacion.setForeground(new java.awt.Color(232, 232, 232));
        btnConfirmarOperacion.setText("Confirmar");
        btnConfirmarOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarOperacionActionPerformed(evt);
            }
        });
        pnlOperaciones.add(btnConfirmarOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(64, 64, 64));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ingrese el ID del empleado");
        pnlOperaciones.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 60, 310, -1));

        pnlPrincipal.add(pnlOperaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 550, 420, 210));

        showTurnoTXT.setBackground(new java.awt.Color(215, 215, 215));
        showTurnoTXT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        showTurnoTXT.setForeground(new java.awt.Color(64, 64, 64));
        showTurnoTXT.setText("Turno");
        showTurnoTXT.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        showTurnoTXT.setFocusable(false);
        pnlPrincipal.add(showTurnoTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 400, 495, 40));

        showNombre.setBackground(new java.awt.Color(215, 215, 215));
        showNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        showNombre.setForeground(new java.awt.Color(64, 64, 64));
        showNombre.setText("Nombre");
        showNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        showNombre.setFocusable(false);
        pnlPrincipal.add(showNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 320, 495, 40));

        showPuestoTXT.setBackground(new java.awt.Color(215, 215, 215));
        showPuestoTXT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        showPuestoTXT.setForeground(new java.awt.Color(64, 64, 64));
        showPuestoTXT.setText("Puesto");
        showPuestoTXT.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        showPuestoTXT.setFocusable(false);
        pnlPrincipal.add(showPuestoTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 350, 40));

        showID.setBackground(new java.awt.Color(215, 215, 215));
        showID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        showID.setForeground(new java.awt.Color(64, 64, 64));
        showID.setText("ID");
        showID.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        showID.setFocusable(false);
        pnlPrincipal.add(showID, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 350, 40));

        showTurnoCBX.setBackground(new java.awt.Color(215, 215, 215));
        showTurnoCBX.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        showTurnoCBX.setForeground(new java.awt.Color(64, 64, 64));
        showTurnoCBX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino" }));
        showTurnoCBX.setBorder(null);
        showTurnoCBX.setOpaque(true);
        showTurnoCBX.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlPrincipal.add(showTurnoCBX, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 400, 495, 40));

        showPuestoCBX.setBackground(new java.awt.Color(215, 215, 215));
        showPuestoCBX.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        showPuestoCBX.setForeground(new java.awt.Color(64, 64, 64));
        showPuestoCBX.setBorder(null);
        showPuestoCBX.setOpaque(true);
        showPuestoCBX.setPreferredSize(new java.awt.Dimension(72, 25));
        pnlPrincipal.add(showPuestoCBX, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 350, 40));

        getContentPane().add(pnlPrincipal);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int salir = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir?", "Salir", JOptionPane.YES_OPTION);
        if (salir == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed
        pnlDerecho.removeAll();
        pnlDerecho.add(pnlPrincipal);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnEmpleadosActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        pnlDerecho.removeAll();
        pnlDerecho.add(pnlFinanzas);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnEditarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEmpleadosActionPerformed
        pnlHerramientas.setVisible(false);
        operacion = "Modificar";
        lblTitulo_PnlOperaciones.setText("Modificar Datos del Empleado");
        pnlOperaciones.setVisible(true);
    }//GEN-LAST:event_btnEditarEmpleadosActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        pnlDerecho.removeAll();
        pnlDerecho.add(pnlGrupos);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        JComboBox cbxReporte = new JComboBox(new Object[]{"Empleados", "Productos", "Proveedores", "Entregas", "Ventas"});
        Object[] objetos = {"¿Qué elementos debe incluir el reporte?", cbxReporte};

        if (JOptionPane.showConfirmDialog(null, objetos, "Generar un nuevo reporte", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE) != JOptionPane.OK_OPTION) {
            return;
        }
        String elemento = cbxReporte.getSelectedItem().toString();
        try {
            InputStream archivo = getClass().getResourceAsStream("../Reportes/" + elemento + ".jrxml");
            JasperDesign jd = JRXmlLoader.load(archivo);

            java.util.Map<String, Object> params = new HashMap<>();
            params.put("Logo", "Recursos/IconosFerreteria/Logo.png");

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, Conexion.con);

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "Reporte" + elemento + ".pdf");

            exporter.exportReport();

            JasperViewer.viewReport(jp);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnEntregasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregasActionPerformed
        pnlDerecho.removeAll();
        pnlDerecho.add(pnlEntregas);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnEntregasActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        pnlDerecho.removeAll();
        pnlDerecho.add(pnlVentas);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnInsertarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarEmpleadoActionPerformed
        pnlFamilia.setVisible(false);
        rellenarPuestos(usuario);
        operacion = "Insertar";
        lblTitulo_PnlDatosOperaciones.setText("Registrar a un nuevo Empleado");

        pnlDatosOperaciones.setVisible(true);
        txtNombreEmpleado.setEditable(true);
        btnCancelarOperacion1.setVisible(true);
        btnConfirmarOperacion1.setText("Confirmar");
        limpiar();
    }//GEN-LAST:event_btnInsertarEmpleadoActionPerformed

    private void btnCancelarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPerfilActionPerformed
        showNombre.setText(usuario.getNombre());
        showPuestoTXT.setText(usuario.getPuesto());
        showTurnoTXT.setText(usuario.getTurno());

        showNombre.setFocusable(false);
        showPuestoTXT.setVisible(true);
        showTurnoTXT.setVisible(true);
        showPuestoCBX.setVisible(false);
        showTurnoCBX.setVisible(false);
        btnListo.setVisible(false);
        btnCancelarPerfil.setVisible(false);
    }//GEN-LAST:event_btnCancelarPerfilActionPerformed

    private void btnNextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageActionPerformed
        if (paginaActual < totalPaginas) {
            paginaActual++;
            actualizarEmpleados();
        }
    }//GEN-LAST:event_btnNextPageActionPerformed

    private void btnEliminarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpleadosActionPerformed
        pnlHerramientas.setVisible(false);
        operacion = "Eliminar";
        lblTitulo_PnlOperaciones.setText("Dar de baja a un Empleado");
        pnlOperaciones.setVisible(true);
    }//GEN-LAST:event_btnEliminarEmpleadosActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        showNombre.setFocusable(true);

        rellenarPuestos(usuario);

        showPuestoCBX.setSelectedItem(usuario.getPuesto());
        showTurnoCBX.setSelectedItem(usuario.getTurno());

        showPuestoCBX.setVisible(true);
        showTurnoCBX.setVisible(true);

        showPuestoTXT.setVisible(false);
        showTurnoTXT.setVisible(false);

        btnListo.setVisible(true);
        btnCancelarPerfil.setVisible(true);

    }//GEN-LAST:event_btnModificar1ActionPerformed

    private void btnListoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListoActionPerformed
        empleadoModificar = new Empleados(showID.getText(), showNombre.getText(), showPuestoCBX.getSelectedItem().toString(), showTurnoCBX.getSelectedItem().toString());
        /*empleadoModificar.setIdEmpleado(showID.getText());
        empleadoModificar.setNombre(showNombre.getText());
        empleadoModificar.setPuesto(showPuesto.getText());
        empleadoModificar.setTurno(showTurno.getText());*/
        //Confirmacion de cambios, en caso negativo, no se hace nada y se tiene que presionar a cancelar para deshacer
        if (JOptionPane.showConfirmDialog(null, "¿Realmente deseas confirmar los cambios en tu perfil?",
                "Confirmar cambios", JOptionPane.YES_OPTION) == 0) {
            empleadoModificar.actualizarDatosEmpleado(empleadoModificar);
            actualizarTodo();
            //JOptionPane.showMessageDialog(null, "Se han actualizado los cambios en tu perfil");

            showNombre.setFocusable(false);
            showPuestoTXT.setVisible(true);
            showTurnoTXT.setVisible(true);
            showPuestoCBX.setVisible(false);
            showTurnoCBX.setVisible(false);
            btnListo.setVisible(false);
            btnCancelarPerfil.setVisible(false);
        }
    }//GEN-LAST:event_btnListoActionPerformed

    private void btnLastPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastPageActionPerformed
        if (paginaActual > 1) {
            paginaActual--;
            indexLista = (paginaActual * 6) - 6;
            if (indexLista < 0) {
                indexLista = 0;
            }
            actualizarEmpleados();
        }
    }//GEN-LAST:event_btnLastPageActionPerformed

    private void btnConfirmarOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarOperacionActionPerformed
        //Se hace una consulta para verificar existencia del empleado
        if (usuario.existeRegistro(new Empleados(txtIdOperacion.getText()))) {
            lblInfoEmpleado.setText("  ");
            empleadoModificar = usuario.consultarEmpleado(new Empleados(txtIdOperacion.getText()));
            pnlFamilia.setVisible(false);
            pnlDatosOperaciones.setVisible(true);
            lblTitulo_PnlDatosOperaciones.setText(lblTitulo_PnlOperaciones.getText());
            rellenarDatosDeOperacion(empleadoModificar);
            if (operacion.equals("Modificar")) {
                txtNombreEmpleado.setEditable(true);
                btnCancelarOperacion1.setVisible(true);
                btnConfirmarOperacion1.setText("Confirmar");
            } else if (operacion.equals("Eliminar")) {
                btnConfirmarOperacion1.setText("Confirmar");
                btnCancelarOperacion1.setVisible(true);
                txtNombreEmpleado.setEditable(false);
            }

        } else {
            lblInfoEmpleado.setText("No se ha encontrado ningún empleado");
        }

    }//GEN-LAST:event_btnConfirmarOperacionActionPerformed

    private void btnCancelarOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarOperacionActionPerformed
        txtIdOperacion.setText("");
        lblInfoEmpleado.setText("  ");
        pnlHerramientas.setVisible(true);
        pnlOperaciones.setVisible(false);
        pnlDatosOperaciones.setVisible(false);
        pnlFamilia.setVisible(true);
    }//GEN-LAST:event_btnCancelarOperacionActionPerformed

    private void btnCancelarOperacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarOperacion1ActionPerformed
        pnlDatosOperaciones.setVisible(false);
        pnlFamilia.setVisible(true);
        pnlOperaciones.setVisible(false);
        txtIdOperacion.setText("");
        lblInfoEmpleado.setText("  ");
        pnlHerramientas.setVisible(true);
    }//GEN-LAST:event_btnCancelarOperacion1ActionPerformed

    private void btnConfirmarOperacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarOperacion1ActionPerformed
        switch (operacion) {
            case "Modificar": //Solo se pueden modificar nombre, puesto y turno, el ID no
                empleadoModificar.setNombre(txtNombreEmpleado.getText());
                empleadoModificar.setPuesto(cbxPuestoEmpleado.getSelectedItem().toString());
                empleadoModificar.setTurno(cbxTurnoEmpleado.getSelectedItem().toString());
                empleadoModificar.actualizarDatosEmpleado(empleadoModificar);
                actualizarTodo();
                break;
            case "Consultar":
                pnlDatosOperaciones.setVisible(false);
                pnlFamilia.setVisible(true);
                break;
            case "Insertar":
                empleadoInsertar = new Empleados();
                empleadoInsertar.setNombre(txtNombreEmpleado.getText());
                empleadoInsertar.setPuesto(cbxPuestoEmpleado.getSelectedItem().toString());
                empleadoInsertar.setTurno(cbxTurnoEmpleado.getSelectedItem().toString());
                empleadoInsertar = usuario.registrarEmpleado(empleadoInsertar);
                indexLista = 0;
                paginaActual = 1;
                actualizarEmpleados();

                pnlDatosOperaciones.setVisible(false);
                pnlFamilia.setVisible(true);
                break;
            case "Eliminar":
                if (JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar permanentemente a este empleado?",
                        "Confirmar eliminación", JOptionPane.YES_OPTION) == 0) {
                    usuario.eliminarEmpleado(txtIdEmpleado.getText());
                    pnlDatosOperaciones.setVisible(false);
                    pnlFamilia.setVisible(true);
                    txtIdOperacion.setText("");
                    pnlOperaciones.setVisible(false);
                    pnlHerramientas.setVisible(true);
                    indexLista = 0;
                    paginaActual = 1;
                    actualizarEmpleados();
                }

                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnConfirmarOperacion1ActionPerformed

    private void btnEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleado1ActionPerformed
        empleadoConsultar = usuario.consultarEmpleadoXNombre(lblEmpleado1.getText());
        cargarPanelConsulta(empleadoConsultar);
    }//GEN-LAST:event_btnEmpleado1ActionPerformed

    private void btnEmpleado2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleado2ActionPerformed
        empleadoConsultar = usuario.consultarEmpleadoXNombre(lblEmpleado2.getText());
        cargarPanelConsulta(empleadoConsultar);
    }//GEN-LAST:event_btnEmpleado2ActionPerformed

    private void btnEmpleado3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleado3ActionPerformed
        empleadoConsultar = usuario.consultarEmpleadoXNombre(lblEmpleado3.getText());
        cargarPanelConsulta(empleadoConsultar);
    }//GEN-LAST:event_btnEmpleado3ActionPerformed

    private void btnEmpleado4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleado4ActionPerformed
        empleadoConsultar = usuario.consultarEmpleadoXNombre(lblEmpleado4.getText());
        cargarPanelConsulta(empleadoConsultar);
    }//GEN-LAST:event_btnEmpleado4ActionPerformed

    private void btnEmpleado5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleado5ActionPerformed
        empleadoConsultar = usuario.consultarEmpleadoXNombre(lblEmpleado5.getText());
        cargarPanelConsulta(empleadoConsultar);
    }//GEN-LAST:event_btnEmpleado5ActionPerformed

    private void btnEmpleado6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleado6ActionPerformed
        empleadoConsultar = usuario.consultarEmpleadoXNombre(lblEmpleado6.getText());
        cargarPanelConsulta(empleadoConsultar);
    }//GEN-LAST:event_btnEmpleado6ActionPerformed

    public void cargarPanelConsulta(Empleados empleadoConsultar) {
        operacion = "Consultar";
        pnlFamilia.setVisible(false);
        pnlDatosOperaciones.setVisible(true);
        rellenarDatosDeOperacion(empleadoConsultar);

        txtNombreEmpleado.setEditable(false);
        btnConfirmarOperacion1.setText("Listo");
        btnCancelarOperacion1.setVisible(false);
        lblTitulo_PnlDatosOperaciones.setText("Ver datos del Empleado");
    }

    public void cargarInicio() {
        pnlDerecho.removeAll();
        pnlDerecho.add(pnlPrincipal);
    }

    /**
     * @param args the command line arguments
     */
    //public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
 /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
 /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());

                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Principal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                Empleados usuario = new Empleados("KG001", "Kevin Garcia", "Gerente", "Matutino");
                new Principal(usuario).setVisible(true);
            }
        });
    }*/
    @Override
    public void focusGained(FocusEvent evt) {
        Component c = evt.getComponent();
        if (c == btnEmpleados) {
            btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEmpleadosActivado.png")));
            btnEmpleados.setBackground(new Color(147, 44, 0));
        } else if (c == btnProductos) {
            btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoProductosActivado.png")));
            btnProductos.setBackground(new Color(147, 44, 0));
        } else if (c == btnProveedores) {
            btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoProveedoresActivado.png")));
            btnProveedores.setBackground(new Color(147, 44, 0));
        } else if (c == btnEntregas) {
            btnEntregas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEntregasActivado.png")));
            btnEntregas.setBackground(new Color(147, 44, 0));
        } else if (c == btnVentas) {
            btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoFinanzasActivado.png")));
            btnVentas.setBackground(new Color(147, 44, 0));
        } else if (c == btnSalir) {
            btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoSalirActivado.png")));
            btnSalir.setBackground(new Color(147, 44, 0));
        }
    }

    @Override
    public void focusLost(FocusEvent evt) {
        Component c = evt.getComponent();
        if (c == btnEmpleados) {
            btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEmpleados.png")));
            btnEmpleados.setBackground(new Color(183, 52, 0));
        } else if (c == btnProductos) {
            btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoProductos.png")));
            btnProductos.setBackground(new Color(183, 52, 0));
        } else if (c == btnProveedores) {
            btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoProveedores.png")));
            btnProveedores.setBackground(new Color(183, 52, 0));
        } else if (c == btnEntregas) {
            btnEntregas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoEntregas.png")));
            btnEntregas.setBackground(new Color(183, 52, 0));
        } else if (c == btnVentas) {
            btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoFinanzas.png")));
            btnVentas.setBackground(new Color(183, 52, 0));
        } else if (c == btnSalir) {
            btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/IconoSalir.png")));
            btnSalir.setBackground(new Color(183, 52, 0));
        }
    }

    MouseListener ml = new MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            Component c = evt.getComponent();
            c.setBackground(new Color(147, 44, 0));
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            Component c = evt.getComponent();
            if (!c.hasFocus()) {
                c.setBackground(new Color(183, 52, 0));
            }
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            Component c = evt.getComponent();
            c.setBackground(new Color(234, 112, 63));
        }

    };

    public Principal(Empleados empleado) {
        usuario = empleado;
        initComponents();
        iniciarDatos();

        if (!Login.usuario.getPuesto().equals("Gerente")) {
            pnlHerramientas.setVisible(false);
            pnlOperaciones.setVisible(false);
            lblHerramientasDelGerente.setVisible(false);
            btnModificar1.setVisible(false);
        }

        btnEmpleados.addMouseListener(ml);
        btnEmpleados.addFocusListener(this);

        btnProductos.addMouseListener(ml);
        btnProductos.addFocusListener(this);

        btnProveedores.addMouseListener(ml);
        btnProveedores.addFocusListener(this);

        btnEntregas.addMouseListener(ml);
        btnEntregas.addFocusListener(this);

        btnVentas.addMouseListener(ml);
        btnVentas.addFocusListener(this);

        btnSalir.addMouseListener(ml);
        btnSalir.addFocusListener(this);

        getContentPane().removeAll();

        pnlIzquierdo.setLayout(new BoxLayout(pnlIzquierdo, BoxLayout.PAGE_AXIS));
        pnlIzquierdo.add(pnlMenu);
        add(pnlIzquierdo);

        pnlDerecho.setLayout(new BoxLayout(pnlDerecho, BoxLayout.PAGE_AXIS));
        pnlDerecho.add(pnlPrincipal);
        add(pnlDerecho);

        pnlOperaciones.setVisible(false);
        pnlDatosOperaciones.setVisible(false);
        btnListo.setVisible(false);
        btnCancelarPerfil.setVisible(false);
        showPuestoCBX.setVisible(false);
        showTurnoCBX.setVisible(false);

        revalidate();
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarOperacion;
    public static javax.swing.JButton btnCancelarOperacion1;
    private javax.swing.JButton btnCancelarPerfil;
    private javax.swing.JButton btnConfirmarOperacion;
    public static javax.swing.JButton btnConfirmarOperacion1;
    private javax.swing.JButton btnEditarEmpleados;
    private javax.swing.JButton btnEliminarEmpleados;
    private javax.swing.JButton btnEmpleado1;
    private javax.swing.JButton btnEmpleado2;
    private javax.swing.JButton btnEmpleado3;
    private javax.swing.JButton btnEmpleado4;
    private javax.swing.JButton btnEmpleado5;
    private javax.swing.JButton btnEmpleado6;
    private javax.swing.JButton btnEmpleados;
    private javax.swing.JButton btnEntregas;
    private javax.swing.JButton btnInsertarEmpleado;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnListo;
    private javax.swing.JButton btnModificar1;
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVentas;
    private static javax.swing.JComboBox<String> cbxPuestoEmpleado;
    private static javax.swing.JComboBox<String> cbxTurnoEmpleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblEmpleado1;
    private javax.swing.JLabel lblEmpleado2;
    private javax.swing.JLabel lblEmpleado3;
    private javax.swing.JLabel lblEmpleado4;
    private javax.swing.JLabel lblEmpleado5;
    private javax.swing.JLabel lblEmpleado6;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHerramientasDelGerente;
    private javax.swing.JLabel lblInfoEmpleado;
    private javax.swing.JLabel lblPaginas;
    public javax.swing.JLabel lblTitulo_PnlDatosOperaciones;
    private javax.swing.JLabel lblTitulo_PnlOperaciones;
    private javax.swing.JPanel pnlDatosOperaciones;
    private javax.swing.JPanel pnlFamilia;
    private javax.swing.JPanel pnlHerramientas;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlOperaciones;
    public static javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField showID;
    private javax.swing.JTextField showNombre;
    private static javax.swing.JComboBox<String> showPuestoCBX;
    private javax.swing.JTextField showPuestoTXT;
    private static javax.swing.JComboBox<String> showTurnoCBX;
    private javax.swing.JTextField showTurnoTXT;
    private static javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtIdOperacion;
    private static javax.swing.JTextField txtNombreEmpleado;
    // End of variables declaration//GEN-END:variables
}
