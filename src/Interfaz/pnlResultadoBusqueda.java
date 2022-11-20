/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Kevin MG
 */
public class pnlResultadoBusqueda extends javax.swing.JPanel {

    /**
     * Creates new form pnlResultadoBusqueda
     */
    static String sql;

    static PreparedStatement ps;
    static Statement sentencia;
    static ResultSet rs;

    public pnlResultadoBusqueda() {
        initComponents();
        VER1.hide();
        VER2.hide();
        VER3.hide();
        VER4.hide();
        VER5.hide();
        //setVisible(false);
    }

    static JRadioButton rdbMatricula = new JRadioButton("Matricula");
    static JTextField txtBuscarMatricula = new JTextField();

    static JRadioButton rdbNombre = new JRadioButton("Nombre");
    static JTextField txtBuscarNombre = new JTextField();

    static JRadioButton rdbGrado = new JRadioButton("Grado");
    static JTextField txtBuscarGrado = new JTextField();

    static JRadioButton rdbGrupo = new JRadioButton("Turno");
    static JTextField txtBuscarGrupo = new JTextField();

    static JRadioButton rdbBeca = new JRadioButton("Beca");
    static JTextField txtBuscarBeca = new JTextField();

    public static void buscarContacto(String operacion, boolean bandera, int tipoConsulta) {//0 es consulta individual, 1 es consulta grupal
        boolean avanzar = true;
        if (bandera) { //La bandera de usa para hacer una sentencia sql en este metodo o usar una personalizada
            ButtonGroup grupo = new ButtonGroup();

            rdbMatricula.addActionListener(al);

            txtBuscarNombre.setEnabled(false);
            rdbNombre.addActionListener(al);

            txtBuscarGrado.setEnabled(false);
            rdbGrado.addActionListener(al);

            txtBuscarGrupo.setEnabled(false);
            rdbGrupo.addActionListener(al);

            txtBuscarBeca.setEnabled(false);
            rdbBeca.addActionListener(al);

            grupo.add(rdbMatricula);
            grupo.add(rdbNombre);
            grupo.add(rdbGrado);
            grupo.add(rdbGrupo);
            grupo.add(rdbBeca);

            Object[] objeto = null;
            if (tipoConsulta == 0) {
                rdbMatricula.setSelected(true);
                Object[] inputFields = {"Buscar por:\n", rdbMatricula, txtBuscarMatricula, rdbNombre, txtBuscarNombre, "\n" /*rdbGrado, txtBuscarGrado,
                rdbGrupo, txtBuscarGrupo, rdbBeca, txtBuscarBeca*/};
                objeto = inputFields;
            } else {
                rdbGrado.setSelected(true);
                txtBuscarGrado.setEnabled(true);
                Object[] inputFields = {"Buscar por:\n", rdbGrado, txtBuscarGrado, rdbGrupo, txtBuscarGrupo, rdbBeca, txtBuscarBeca, "\n"};
                objeto = inputFields;
            }
            int option = JOptionPane.showConfirmDialog(null, objeto, "Buscar Alumnos Inscritos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

            sql = "SELECT * FROM alumnos WHERE ";
            if (option == JOptionPane.OK_OPTION) {
                if (rdbMatricula.isSelected() && txtBuscarMatricula.getText().length() != 0) {
                    sql += "matricula = '" + txtBuscarMatricula.getText() + "'";
                } else if (rdbNombre.isSelected() && txtBuscarNombre.getText().length() != 0) {
                    sql += "nombre = '" + txtBuscarNombre.getText() + "'";
                } else if (rdbGrado.isSelected() && txtBuscarGrado.getText().length() != 0) {
                    sql += "grado = '" + txtBuscarGrado.getText() + "'";
                } else if (rdbGrupo.isSelected() && txtBuscarGrupo.getText().length() != 0) {
                    sql += "turno = '" + txtBuscarGrupo.getText() + "'";
                } else if (rdbBeca.isSelected() && txtBuscarBeca.getText().length() != 0) {
                    sql += "beca = '" + txtBuscarBeca.getText() + "'";
                } else {
                    JOptionPane.showMessageDialog(null, "Puede que falten datos por ingresar o que estos no sean validos.");
                    avanzar = false;
                }

                if ("Modificar".equals(operacion)) {
                    VER1.setText("Modificar");
                    VER2.setText("Modificar");
                    VER3.setText("Modificar");
                    VER4.setText("Modificar");
                    VER5.setText("Modificar");
                } else if ("Eliminar".equals(operacion)) {
                    VER1.setText("Eliminar");
                    VER2.setText("Eliminar");
                    VER3.setText("Eliminar");
                    VER4.setText("Eliminar");
                    VER5.setText("Eliminar");
                } else if ("Buscar".equals(operacion) || "Mostrar".equals(operacion)) {
                    VER1.setText("Ver");
                    VER2.setText("Ver");
                    VER3.setText("Ver");
                    VER4.setText("Ver");
                    VER5.setText("Ver");
                }

                if (avanzar) {
                    actualizarTabla();
                }
                avanzar = true;
            }
            txtBuscarMatricula.setText("");
            txtBuscarNombre.setText("");
            txtBuscarGrado.setText("");
            txtBuscarGrupo.setText("");
            txtBuscarBeca.setText("");
        } else {
            /*pnlDerecho.remove(Principal.pnlBusqueda);
            pnlDerecho.add(Principal.pnlInscribir);*/
            actualizarTabla();
        }

    }

    static ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent eve) {
            Object objeto = eve.getSource();

            if (objeto == rdbMatricula) {
                txtBuscarMatricula.setEnabled(true);

                txtBuscarNombre.setEnabled(false);
                txtBuscarGrado.setEnabled(false);
                txtBuscarGrupo.setEnabled(false);
                txtBuscarBeca.setEnabled(false);

            } else if (objeto == rdbNombre) {
                txtBuscarMatricula.setEnabled(false);
                txtBuscarNombre.setEnabled(true);

                txtBuscarGrado.setEnabled(false);
                txtBuscarGrupo.setEnabled(false);
                txtBuscarBeca.setEnabled(false);
            } else if (objeto == rdbGrado) {
                txtBuscarMatricula.setEnabled(false);
                txtBuscarNombre.setEnabled(false);
                txtBuscarGrado.setEnabled(true);

                txtBuscarGrupo.setEnabled(false);
                txtBuscarBeca.setEnabled(false);
            } else if (objeto == rdbGrupo) {
                txtBuscarMatricula.setEnabled(false);
                txtBuscarNombre.setEnabled(false);
                txtBuscarGrado.setEnabled(false);
                txtBuscarGrupo.setEnabled(true);

                txtBuscarBeca.setEnabled(false);
            } else if (objeto == rdbBeca) {
                txtBuscarMatricula.setEnabled(false);
                txtBuscarNombre.setEnabled(false);
                txtBuscarGrado.setEnabled(false);
                txtBuscarGrupo.setEnabled(false);

                txtBuscarBeca.setEnabled(true);
            }
        }
    };

    static ArrayList registros = new ArrayList();
    static String registro[];
    static String[] datos;

    static int cima1 = 0;
    static int cima2 = 0;

    public static void actualizarTabla() {
        try {
            if (cima1 == 0) {
                //sentencia = reg.createStatement();
                rs = sentencia.executeQuery(sql);
                cima1 = 0;
                cima2 = 0;
                registros.clear();
                while (rs.next()) {
                    registro = new String[12];
                    registro[0] = rs.getString(2); //Matricula
                    registro[1] = rs.getString(3); //Nombre
                    registro[2] = rs.getString(4); //Apaterno
                    registro[3] = rs.getString(5); //Amaterno
                    registro[4] = rs.getString(6); //Nivel
                    registro[5] = rs.getString(7); //Grado
                    registro[6] = rs.getString(8); //Turno
                    registro[7] = rs.getString(9); //Dia
                    registro[8] = rs.getString(10); //Mes
                    registro[9] = rs.getString(11); //Year
                    registro[10] = rs.getString(12); //Direccion                                                

                    registros.add(registro);
                    cima1++;
                }
            }

            //System.out.println("Cima 1: " + cima1);
            if (registros.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontro ningun registro con los datos ingresados");
            } else {
               /* pnlDerecho.remove(Principal.pnlPrincipal);
                pnlDerecho.add(Principal.pnlBusqueda);*/
            }

            if (cima1 > cima2) {
                if (cima2 < registros.size()) {
                    datos = (String[]) registros.get(cima2);

                    MATRICULA1.setText(datos[0]);
                    NOMBRE1.setText(datos[1] + " " + datos[2] + " " + datos[3]);
                    NIVEL1.setText(datos[4]);
                    GRADO1.setText(datos[5]);
                    GRUPO1.setText(datos[6]);
                    FECHA1.setText(datos[7] + " de " + datos[8] + " del " + datos[9]);
                    DIRECCION1.setText(datos[10]);
                    VER1.show();
                    cima2++;
                    //System.out.println("Se imprimio un registro. Cima 2: " + cima2);
                } else {
                    MATRICULA1.setText("  ");
                    NOMBRE1.setText("  ");
                    NIVEL1.setText("  ");
                    GRADO1.setText("  ");
                    GRUPO1.setText("  ");
                    FECHA1.setText("  ");
                    DIRECCION1.setText("  ");
                    VER1.hide();
                }

                if (cima2 < registros.size()) {
                    datos = (String[]) registros.get(cima2);

                    MATRICULA2.setText(datos[0]);
                    NOMBRE2.setText(datos[1] + " " + datos[2] + " " + datos[3]);
                    NIVEL2.setText(datos[4]);
                    GRADO2.setText(datos[5]);
                    GRUPO2.setText(datos[6]);
                    FECHA2.setText(datos[7] + " de " + datos[8] + " del " + datos[9]);
                    DIRECCION2.setText(datos[10]);
                    VER2.show();
                    cima2++;
                    //System.out.println("Se imprimio un registro. Cima 2: " + cima2);
                } else {
                    MATRICULA2.setText("  ");
                    NOMBRE2.setText("  ");
                    NIVEL2.setText("  ");
                    GRADO2.setText("  ");
                    GRUPO2.setText("  ");
                    FECHA2.setText("  ");
                    DIRECCION2.setText("  ");
                    VER2.hide();
                }

                if (cima2 < registros.size()) {
                    datos = (String[]) registros.get(cima2);

                    MATRICULA3.setText(datos[0]);
                    NOMBRE3.setText(datos[1] + " " + datos[2] + " " + datos[3]);
                    NIVEL3.setText(datos[4]);
                    GRADO3.setText(datos[5]);
                    GRUPO3.setText(datos[6]);
                    FECHA3.setText(datos[7] + " de " + datos[8] + " del " + datos[9]);
                    DIRECCION3.setText(datos[10]);
                    VER3.show();
                    cima2++;
                    //System.out.println("Se imprimio un registro. Cima 2: " + cima2);
                } else {
                    MATRICULA3.setText("  ");
                    NOMBRE3.setText("  ");
                    NIVEL3.setText("  ");
                    GRADO3.setText("  ");
                    GRUPO3.setText("  ");
                    FECHA3.setText("  ");
                    DIRECCION3.setText("  ");
                    VER3.hide();
                }

                if (cima2 < registros.size()) {
                    datos = (String[]) registros.get(cima2);

                    MATRICULA4.setText(datos[0]);
                    NOMBRE4.setText(datos[1] + " " + datos[2] + " " + datos[3]);
                    NIVEL4.setText(datos[4]);
                    GRADO4.setText(datos[5]);
                    GRUPO4.setText(datos[6]);
                    FECHA4.setText(datos[7] + " de " + datos[8] + " del " + datos[9]);
                    DIRECCION4.setText(datos[10]);
                    VER4.show();
                    cima2++;
                    //System.out.println("Se imprimio un registro. Cima 2: " + cima2);
                } else {
                    MATRICULA4.setText("  ");
                    NOMBRE4.setText("  ");
                    NIVEL4.setText("  ");
                    GRADO4.setText("  ");
                    GRUPO4.setText("  ");
                    FECHA4.setText("  ");
                    DIRECCION4.setText("  ");
                    VER4.hide();
                }

                if (cima2 < registros.size()) {
                    datos = (String[]) registros.get(cima2);

                    MATRICULA5.setText(datos[0]);
                    NOMBRE5.setText(datos[1] + " " + datos[2] + " " + datos[3]);
                    NIVEL5.setText(datos[4]);
                    GRADO5.setText(datos[5]);
                    GRUPO5.setText(datos[6]);
                    FECHA5.setText(datos[7] + " de " + datos[8] + " del " + datos[9]);
                    DIRECCION5.setText(datos[10]);
                    VER5.show();
                    cima2++;
                    //System.out.println("Se imprimio un registro. Cima 2: " + cima2);
                } else {
                    MATRICULA5.setText("  ");
                    NOMBRE5.setText("  ");
                    NIVEL5.setText("  ");
                    GRADO5.setText("  ");
                    GRUPO5.setText("  ");
                    FECHA5.setText("  ");
                    DIRECCION5.setText("  ");
                    VER5.hide();
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        VER1 = new javax.swing.JButton();
        MATRICULA1 = new javax.swing.JLabel();
        NOMBRE1 = new javax.swing.JLabel();
        GRADO1 = new javax.swing.JLabel();
        GRUPO1 = new javax.swing.JLabel();
        FECHA1 = new javax.swing.JLabel();
        VER2 = new javax.swing.JButton();
        MATRICULA2 = new javax.swing.JLabel();
        NOMBRE2 = new javax.swing.JLabel();
        GRADO2 = new javax.swing.JLabel();
        GRUPO2 = new javax.swing.JLabel();
        FECHA2 = new javax.swing.JLabel();
        VER3 = new javax.swing.JButton();
        MATRICULA3 = new javax.swing.JLabel();
        NOMBRE3 = new javax.swing.JLabel();
        GRADO3 = new javax.swing.JLabel();
        GRUPO3 = new javax.swing.JLabel();
        FECHA3 = new javax.swing.JLabel();
        VER4 = new javax.swing.JButton();
        MATRICULA4 = new javax.swing.JLabel();
        NOMBRE4 = new javax.swing.JLabel();
        GRADO4 = new javax.swing.JLabel();
        GRUPO4 = new javax.swing.JLabel();
        FECHA4 = new javax.swing.JLabel();
        VER5 = new javax.swing.JButton();
        MATRICULA5 = new javax.swing.JLabel();
        NOMBRE5 = new javax.swing.JLabel();
        GRADO5 = new javax.swing.JLabel();
        GRUPO5 = new javax.swing.JLabel();
        FECHA5 = new javax.swing.JLabel();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        DIRECCION1 = new javax.swing.JLabel();
        DIRECCION5 = new javax.swing.JLabel();
        DIRECCION2 = new javax.swing.JLabel();
        DIRECCION3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        DIRECCION4 = new javax.swing.JLabel();
        NIVEL5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        NIVEL1 = new javax.swing.JLabel();
        NIVEL2 = new javax.swing.JLabel();
        NIVEL3 = new javax.swing.JLabel();
        NIVEL4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(232, 232, 232));
        setMaximumSize(new java.awt.Dimension(1200, 750));
        setMinimumSize(new java.awt.Dimension(1200, 750));
        setPreferredSize(new java.awt.Dimension(1200, 750));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Resultados de la Busqueda");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Matricula");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Nombre Completo");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Grado");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Turno");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Fecha de Nacimiento");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 120, -1, -1));

        VER1.setBackground(new java.awt.Color(0, 204, 255));
        VER1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        VER1.setForeground(new java.awt.Color(0, 0, 0));
        VER1.setText("Ver");
        VER1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VER1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VER1ActionPerformed(evt);
            }
        });
        add(VER1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 165, 120, -1));

        MATRICULA1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        MATRICULA1.setText("11");
        add(MATRICULA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        NOMBRE1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NOMBRE1.setText("11");
        add(NOMBRE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        GRADO1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRADO1.setText("11");
        add(GRADO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 170, -1, -1));

        GRUPO1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRUPO1.setText("11");
        add(GRUPO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 170, -1, -1));

        FECHA1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        FECHA1.setText("11");
        add(FECHA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 170, 160, -1));

        VER2.setBackground(new java.awt.Color(0, 204, 255));
        VER2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        VER2.setForeground(new java.awt.Color(0, 0, 0));
        VER2.setText("Ver");
        VER2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VER2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VER2ActionPerformed(evt);
            }
        });
        add(VER2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 225, 120, -1));

        MATRICULA2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        MATRICULA2.setText("11");
        add(MATRICULA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        NOMBRE2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NOMBRE2.setText("11");
        add(NOMBRE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, -1, -1));

        GRADO2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRADO2.setText("11");
        add(GRADO2, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 230, -1, -1));

        GRUPO2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRUPO2.setText("11");
        add(GRUPO2, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 230, -1, -1));

        FECHA2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        FECHA2.setText("11");
        add(FECHA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 230, 160, -1));

        VER3.setBackground(new java.awt.Color(0, 204, 255));
        VER3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        VER3.setForeground(new java.awt.Color(0, 0, 0));
        VER3.setText("Ver");
        VER3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VER3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VER3ActionPerformed(evt);
            }
        });
        add(VER3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 285, 120, -1));

        MATRICULA3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        MATRICULA3.setText("11");
        add(MATRICULA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        NOMBRE3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NOMBRE3.setText("11");
        add(NOMBRE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, -1, -1));

        GRADO3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRADO3.setText("11");
        add(GRADO3, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 290, -1, -1));

        GRUPO3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRUPO3.setText("11");
        add(GRUPO3, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 290, -1, -1));

        FECHA3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        FECHA3.setText("11");
        add(FECHA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 290, 160, -1));

        VER4.setBackground(new java.awt.Color(0, 204, 255));
        VER4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        VER4.setForeground(new java.awt.Color(0, 0, 0));
        VER4.setText("Ver");
        VER4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VER4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VER4ActionPerformed(evt);
            }
        });
        add(VER4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 345, 120, -1));

        MATRICULA4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        MATRICULA4.setText("11");
        add(MATRICULA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        NOMBRE4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NOMBRE4.setText("11");
        add(NOMBRE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, -1, -1));

        GRADO4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRADO4.setText("11");
        add(GRADO4, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 350, -1, -1));

        GRUPO4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRUPO4.setText("11");
        add(GRUPO4, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 350, -1, -1));

        FECHA4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        FECHA4.setText("11");
        add(FECHA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 350, 160, -1));

        VER5.setBackground(new java.awt.Color(0, 204, 255));
        VER5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        VER5.setForeground(new java.awt.Color(0, 0, 0));
        VER5.setText("Ver");
        VER5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VER5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VER5ActionPerformed(evt);
            }
        });
        add(VER5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 405, 120, -1));

        MATRICULA5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        MATRICULA5.setText("11");
        add(MATRICULA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        NOMBRE5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NOMBRE5.setText("11");
        add(NOMBRE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, -1, -1));

        GRADO5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRADO5.setText("11");
        add(GRADO5, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 410, -1, -1));

        GRUPO5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        GRUPO5.setText("11");
        add(GRUPO5, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 410, -1, -1));

        FECHA5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        FECHA5.setText("11");
        add(FECHA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 410, 160, -1));

        btnAnterior.setBackground(new java.awt.Color(0, 51, 153));
        btnAnterior.setForeground(new java.awt.Color(255, 255, 255));
        btnAnterior.setText("Pagina Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, -1, 45));

        btnSiguiente.setBackground(new java.awt.Color(0, 51, 153));
        btnSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        btnSiguiente.setText("Pagina Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 540, -1, 45));

        DIRECCION1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        DIRECCION1.setText("11");
        add(DIRECCION1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 170, 170, -1));

        DIRECCION5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        DIRECCION5.setText("11");
        add(DIRECCION5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 410, 170, -1));

        DIRECCION2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        DIRECCION2.setText("11");
        add(DIRECCION2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 230, 170, -1));

        DIRECCION3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        DIRECCION3.setText("11");
        add(DIRECCION3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 290, 170, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Direccion");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, -1, -1));

        DIRECCION4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        DIRECCION4.setText("11");
        add(DIRECCION4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, 170, -1));

        NIVEL5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NIVEL5.setText("11");
        add(NIVEL5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Nivel");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        NIVEL1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NIVEL1.setText("11");
        add(NIVEL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));

        NIVEL2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NIVEL2.setText("11");
        add(NIVEL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        NIVEL3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NIVEL3.setText("11");
        add(NIVEL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, -1, -1));

        NIVEL4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        NIVEL4.setText("11");
        add(NIVEL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, -1));
    }// </editor-fold>//GEN-END:initComponents
        //JPanel pnlOperaciones = new pnlNuevaInscripcion();
    private void VER1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VER1ActionPerformed
        sql = "SELECT * FROM alumnos WHERE matricula = '" + MATRICULA1.getText() + "'";
        if ("Modificar".equals(VER1.getText())) {
            buscarContacto("Modificar", false, 0);
        } else if ("Eliminar".equals(VER1.getText())) {
            buscarContacto("Eliminar", false, 0);
        } else {
            buscarContacto("Buscar", false, 0);
        }
        revalidate();
        repaint();
    }//GEN-LAST:event_VER1ActionPerformed

    private void VER2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VER2ActionPerformed
        //Se realiza una consulta con el sql:
        sql = "SELECT * FROM alumnos WHERE matricula = '" + MATRICULA2.getText() + "'";
        if ("Modificar".equals(VER1.getText())) {
            buscarContacto("Modificar", false, 0);
        } else if ("Eliminar".equals(VER1.getText())) {
            buscarContacto("Eliminar", false, 0);
        } else {
            buscarContacto("Buscar", false, 0);
        }
        revalidate();
        repaint();
    }//GEN-LAST:event_VER2ActionPerformed

    private void VER3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VER3ActionPerformed
        //Se realiza una consulta con el sql:
        sql = "SELECT * FROM alumnos WHERE matricula = '" + MATRICULA3.getText() + "'";
        if ("Modificar".equals(VER1.getText())) {
            buscarContacto("Modificar", false, 0);
        } else if ("Eliminar".equals(VER1.getText())) {
            buscarContacto("Eliminar", false, 0);
        } else {
            buscarContacto("Buscar", false, 0);
        }
        revalidate();
        repaint();
    }//GEN-LAST:event_VER3ActionPerformed

    private void VER4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VER4ActionPerformed
        //Se realiza una consulta con el sql:
        sql = "SELECT * FROM alumnos WHERE matricula = '" + MATRICULA4.getText() + "'";
        if ("Modificar".equals(VER1.getText())) {
            buscarContacto("Modificar", false, 0);
        } else if ("Eliminar".equals(VER1.getText())) {
            buscarContacto("Eliminar", false, 0);
        } else {
            buscarContacto("Buscar", false, 0);
        }
        revalidate();
        repaint();
    }//GEN-LAST:event_VER4ActionPerformed

    private void VER5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VER5ActionPerformed
        //Se realiza una consulta con el sql:
        sql = "SELECT * FROM alumnos WHERE matricula = '" + MATRICULA5.getText() + "'";
        if ("Modificar".equals(VER1.getText())) {
            buscarContacto("Modificar", false, 0);
        } else if ("Eliminar".equals(VER1.getText())) {
            buscarContacto("Eliminar", false, 0);
        } else {
            buscarContacto("Buscar", false, 0);
        }
        revalidate();
        repaint();
    }//GEN-LAST:event_VER5ActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        //Mostrar la pagina anterior de la tabla        
        if (cima2 >= 5) {
            if (cima2 % 5 != 0) {
                cima2 = cima2 - ((cima2 % 5) + 5);
                actualizarTabla();
            } else if (cima2 % 5 == 0 && cima2 >= 10) {
                cima2 = cima2 - 10;
                actualizarTabla();
            } else if (cima2 == 5) {
                cima2 = 0;
                JOptionPane.showMessageDialog(null, "Se ha llegado al principio de la tabla");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Se ha llegado al principio de la tabla");
        }

    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        //Mostrar siguiente pagina de la tabla        
        if (cima1 > cima2) {
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Se ha llegado al final de la tabla");
        }

    }//GEN-LAST:event_btnSiguienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel DIRECCION1;
    private static javax.swing.JLabel DIRECCION2;
    private static javax.swing.JLabel DIRECCION3;
    private static javax.swing.JLabel DIRECCION4;
    private static javax.swing.JLabel DIRECCION5;
    private static javax.swing.JLabel FECHA1;
    private static javax.swing.JLabel FECHA2;
    private static javax.swing.JLabel FECHA3;
    private static javax.swing.JLabel FECHA4;
    private static javax.swing.JLabel FECHA5;
    private static javax.swing.JLabel GRADO1;
    private static javax.swing.JLabel GRADO2;
    private static javax.swing.JLabel GRADO3;
    private static javax.swing.JLabel GRADO4;
    private static javax.swing.JLabel GRADO5;
    private static javax.swing.JLabel GRUPO1;
    private static javax.swing.JLabel GRUPO2;
    private static javax.swing.JLabel GRUPO3;
    private static javax.swing.JLabel GRUPO4;
    private static javax.swing.JLabel GRUPO5;
    private static javax.swing.JLabel MATRICULA1;
    private static javax.swing.JLabel MATRICULA2;
    private static javax.swing.JLabel MATRICULA3;
    private static javax.swing.JLabel MATRICULA4;
    private static javax.swing.JLabel MATRICULA5;
    private static javax.swing.JLabel NIVEL1;
    private static javax.swing.JLabel NIVEL2;
    private static javax.swing.JLabel NIVEL3;
    private static javax.swing.JLabel NIVEL4;
    private static javax.swing.JLabel NIVEL5;
    private static javax.swing.JLabel NOMBRE1;
    private static javax.swing.JLabel NOMBRE2;
    private static javax.swing.JLabel NOMBRE3;
    private static javax.swing.JLabel NOMBRE4;
    private static javax.swing.JLabel NOMBRE5;
    private static javax.swing.JButton VER1;
    private static javax.swing.JButton VER2;
    private static javax.swing.JButton VER3;
    private static javax.swing.JButton VER4;
    private static javax.swing.JButton VER5;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
