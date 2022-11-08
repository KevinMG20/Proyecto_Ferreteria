/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Inscripciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static Inscripciones.Principal.reg;
import javax.swing.JComboBox;
import java.sql.SQLException;

/**
 *
 * @author Kevin MG
 */
public class pnlNuevaInscripcion extends javax.swing.JPanel {

    /**
     * Creates new form pnlNuevaInscripcion
     */
    public pnlNuevaInscripcion() {
        initComponents();
    }

    static String sql;
    static Conectar con = new Conectar();

    static PreparedStatement ps;
    static Statement sentencia;
    static ResultSet rs;

    public static void llenarGrado() {
        cbxGrado.removeAllItems();
        if (null != Principal.operacion) {
            switch (Principal.operacion) {
                case "Primaria","Primaria ":
                    //System.out.println("Caso Primaria");
                    cbxGrado.addItem("Primer Grado");
                    cbxGrado.addItem("Segundo Grado");
                    cbxGrado.addItem("Tercer Grado");
                    cbxGrado.addItem("Cuarto Grado");
                    cbxGrado.addItem("Quinto Grado");
                    cbxGrado.addItem("Sexto Grado");
                    break;
                case "Secundaria","Secundaria ":
                    //System.out.println("Caso Secundaria");
                    cbxGrado.addItem("Primer Año");
                    cbxGrado.addItem("Segundo Año");
                    cbxGrado.addItem("Tercer Año");
                    break;
                case "Preparatoria","Preparatoria ":
                    //System.out.println("Caso Preparatoria");
                    cbxGrado.addItem("Primer Semestre");
                    cbxGrado.addItem("Segundo Semestre");
                    cbxGrado.addItem("Tercer Semestre");
                    cbxGrado.addItem("Cuarto Semestre");
                    cbxGrado.addItem("Quinto Semestre");
                    cbxGrado.addItem("Sexto Semestre");
                    break;
                default:
                    System.out.println("Default");
                    break;
            }
        }
    }

    public void inscribirAlumno() {
        sql = "INSERT INTO alumnos (matricula,nombre,apaterno,amaterno,nivel,grado,turno,dia,mes,year,direccion,calificacion,beca,taller,rfcPadre) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = reg.prepareStatement(sql);
            ps.setString(1, txtMatricula.getText());
            ps.setString(2, txtNombreA.getText());
            ps.setString(3, txtApaternoA.getText());
            ps.setString(4, txtAmaternoA.getText());
            ps.setString(5, Principal.operacion);
            ps.setString(6, cbxGrado.getSelectedItem().toString());
            ps.setString(7, cbxTurno.getSelectedItem().toString());
            ps.setString(8, cbxDia.getSelectedItem().toString());
            ps.setString(9, cbxMes.getSelectedItem().toString());
            ps.setString(10, cbxYear.getSelectedItem().toString());
            ps.setString(11, txtDireccion.getText());
            ps.setString(12, txtCalificacion.getText());
            if (cbxBeca.getSelectedIndex() == 0 && Integer.parseInt(txtCalificacion.getText()) < 90) {
                JOptionPane.showMessageDialog(null, "Lo sentimos, el promedio minimo para la beca de excelencia es 90\nSe le inscribira a la beca de manutencion");
                ps.setString(13, "Manutencion");
            } else {
                ps.setString(13, cbxBeca.getSelectedItem().toString());
            }

            JComboBox talleres = new JComboBox();
            talleres.addItem("Cocina");
            talleres.addItem("Electronica");
            talleres.addItem("Mecanica");
            talleres.addItem("Ofimatica");
            talleres.addItem("Informatica");
            talleres.addItem("Musica");
            talleres.addItem("Ajedrez");
            talleres.addItem("Futbol");
            talleres.addItem("Basquetbol");
            talleres.addItem("Voleibol");
            talleres.addItem("Atletismo");
            talleres.addItem("Oratoria");
            Object[] inputFields = {"Los alumnos de preparatoria deben elegir un taller (Este no podra ser cambiado posteriormente):\n", talleres};
            if ("Inscripción a Preparatoria".equals(btnTitulo.getText())) {
                JOptionPane.showConfirmDialog(null, inputFields, "Elegir Taller", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                ps.setString(14, talleres.getSelectedItem().toString());
            } else {
                ps.setString(14, "Ninguno");
            }
            ps.setString(15, txtRFC.getText());
            ps.execute();
            JOptionPane.showMessageDialog(null, "El alumno ha sido inscrito correctamente");
            Principal.cargarInicio();
        } catch (java.sql.SQLException | java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al guardar los datos del alumno, es posible que falten datos o estos no sean validos");
        }

        //Registrar en tabla de registroInscripciones
        sql = "INSERT INTO registroinscripciones (matriculaAlumno, nivel, grado, monto) VALUES ('" + txtMatricula.getText() + "','" + Principal.operacion + "','" + cbxGrado.getSelectedItem().toString()
                + "'";//

        if ("Primaria".equals(Principal.operacion)) {
            switch (cbxGrado.getSelectedItem().toString()) {
                case "Primer Grado", "Segundo Grado", "Tercer Grado": //Se hace un update a la tabla de ingresos por 1500
                    sql += ",'1500')";
                    break;
                case "Cuarto Grado","Quinto Grado","Sexto Grado": //Se hace un update a la tabla de ingresos por 2000
                    sql += ",'2000')";
                    break;
                default:
                    break;
            }
        } else if ("Secundaria".equals(Principal.operacion)) { //Se hace un update a la tabla de ingresos por 2500
            sql += ",'2500')";
        } else if ("Preparatoria".equals(Principal.operacion)) { //Se hace un update a la tabla de ingresos por 3000
            sql += ",'3000')";
        }

        try {
            ps = reg.prepareStatement(sql);
            ps.execute();
        } catch (java.sql.SQLException ex) {

        }

    }

    public void registrarPadre() {
        sql = "INSERT INTO padres (rfc,nombre,apaterno,amaterno,telefono) VALUES(?,?,?,?,?)";
        try {
            ps = reg.prepareStatement(sql);
            ps.setString(1, txtRFC.getText());
            ps.setString(2, txtNombreB.getText());
            ps.setString(3, txtApaternoB.getText());
            ps.setString(4, txtAmaternoB.getText());
            ps.setString(5, txtTelefono.getText());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se ha guardado el padre o tutor de alumno");
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ya existe un registro de este padre");
            //Logger.getLogger(nuevoContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static String[] datosAlumno = new String[15];
    static String[] datosPadre = new String[5];
    static String matriculaAnterior;

    public static void cargarDatos(String busqueda) {
        try {
            sentencia = reg.createStatement();
            rs = sentencia.executeQuery(busqueda);

            while (rs.next()) {
                datosAlumno[0] = rs.getString(2); //Matricula
                datosAlumno[1] = rs.getString(3); //Nombre
                datosAlumno[2] = rs.getString(4); //Apaterno
                datosAlumno[3] = rs.getString(5); //Amaterno
                datosAlumno[4] = rs.getString(6); //Nivel
                datosAlumno[5] = rs.getString(7); //Grado
                datosAlumno[6] = rs.getString(8); //Turno
                datosAlumno[7] = rs.getString(9); //Dia
                datosAlumno[8] = rs.getString(10); //Mes
                datosAlumno[9] = rs.getString(11); //Year
                datosAlumno[10] = rs.getString(12); //Direccion
                datosAlumno[11] = rs.getString(13); //Calificacion
                datosAlumno[12] = rs.getString(14); //Beca
                datosAlumno[13] = rs.getString(15); //Taller
                datosAlumno[14] = rs.getString(16); //rfcPadre
            }

            txtMatricula.setText(datosAlumno[0]);
            matriculaAnterior = txtMatricula.getText();

            txtNombreA.setText(datosAlumno[1]);
            txtApaternoA.setText(datosAlumno[2]);
            txtAmaternoA.setText(datosAlumno[3]);
            Principal.operacion = datosAlumno[4];
            Principal.taller = datosAlumno[13];
            llenarGrado();
            cbxGrado.setSelectedItem((String) datosAlumno[5]);
            cbxTurno.setSelectedItem((String) datosAlumno[6]);
            cbxDia.setSelectedItem((String) datosAlumno[7]);
            cbxMes.setSelectedItem((String) datosAlumno[8]);
            cbxYear.setSelectedItem((String) datosAlumno[9]);
            txtDireccion.setText(datosAlumno[10]);
            txtCalificacion.setText(datosAlumno[11]);
            cbxBeca.setSelectedItem((String) datosAlumno[12]);

            sql = "SELECT * FROM padres WHERE rfc = '" + datosAlumno[14] + "'";
            rs = sentencia.executeQuery(sql);

            while (rs.next()) {
                datosPadre[0] = rs.getString(1);
                datosPadre[1] = rs.getString(2);
                datosPadre[2] = rs.getString(3);
                datosPadre[3] = rs.getString(4);
                datosPadre[4] = rs.getString(5);
            }
            txtRFC.setText(datosPadre[0]);
            txtNombreB.setText(datosPadre[1]);
            txtApaternoB.setText(datosPadre[2]);
            txtAmaternoB.setText(datosPadre[3]);
            txtTelefono.setText(datosPadre[4]);

        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al cargar los datos\nEx: " + ex);
        }
    }

    public void editarRegistro() {
        System.out.println("Matricula anterior: " + matriculaAnterior);
        sql = "UPDATE alumnos SET matricula='" + txtMatricula.getText() + "',nombre='" + txtNombreA.getText() + "',apaterno='" + txtApaternoA.getText()
                + "',amaterno='" + txtAmaternoA.getText() + "',grado='" + cbxGrado.getSelectedItem().toString()
                + "',turno='" + cbxTurno.getSelectedItem().toString() + "',dia='" + cbxDia.getSelectedItem().toString()
                + "',mes='" + cbxMes.getSelectedItem().toString() + "',year='" + cbxYear.getSelectedItem().toString()
                + "',direccion='" + txtDireccion.getText() + "',calificacion='" + txtCalificacion.getText()
                + "',beca='" + cbxBeca.getSelectedItem().toString() + "',rfcPadre='" + txtRFC.getText()
                + "' WHERE matricula='" + matriculaAnterior + "'";
        try {
            ps = reg.prepareStatement(sql);
            ps.execute();
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al editar los datos del alumno, es posible que falten datos o estos no sean validos");
        }

        sql = "UPDATE padres SET nombre='" + txtNombreB.getText() + "',apaterno='" + txtApaternoB.getText()
                + "',amaterno='" + txtAmaternoB.getText() + "',telefono='" + txtTelefono.getText()
                + "' WHERE rfc='" + txtRFC.getText() + "'";
        try {
            ps = reg.prepareStatement(sql);
            ps.execute();
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al editar los datos del contacto, es posible que falten datos o estos no sean validos");
        }
        JOptionPane.showMessageDialog(null, "Modificación realizada");        
    }

    public static void bloquearEdicion() {
        txtMatricula.setEditable(false);
        txtNombreA.setEditable(false);
        txtApaternoA.setEditable(false);
        txtAmaternoA.setEditable(false);

        cbxGrado.setEnabled(false);
        cbxTurno.setEnabled(false);
        cbxDia.setEnabled(false);
        cbxMes.setEnabled(false);
        cbxYear.setEnabled(false);
        txtDireccion.setEditable(false);
        txtCalificacion.setEditable(false);
        cbxBeca.setEnabled(false);

        txtRFC.setEditable(false);
        txtNombreB.setEditable(false);
        txtApaternoB.setEditable(false);
        txtAmaternoB.setEditable(false);
        txtTelefono.setEditable(false);

    }

    public static void desbloquearEdicion() {
        txtMatricula.setEditable(true);
        txtNombreA.setEditable(true);
        txtApaternoA.setEditable(true);
        txtAmaternoA.setEditable(true);

        cbxGrado.setEnabled(true);
        cbxTurno.setEnabled(true);
        cbxDia.setEnabled(true);
        cbxMes.setEnabled(true);
        cbxYear.setEnabled(true);
        txtDireccion.setEditable(true);
        txtCalificacion.setEditable(true);
        cbxBeca.setEnabled(true);

        txtRFC.setEditable(true);
        txtNombreB.setEditable(true);
        txtApaternoB.setEditable(true);
        txtAmaternoB.setEditable(true);
        txtTelefono.setEditable(true);

    }

    public void darDeBaja() {
        /*Se crea una copia del alumno en la tabla de bajas, la cual es identica a la de alumnos, con un atributo extra para indicar 
        el tipo de baja*/
        JComboBox cbxTipoBaja = new JComboBox();
        cbxTipoBaja.addItem("Temporal");
        cbxTipoBaja.addItem("Permanente");

        Object[] inputFields = {"Seleccione el tipo de baja: ", cbxTipoBaja};

        JOptionPane.showConfirmDialog(null, inputFields, "Seleccionar Tipo de Baja", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        String tipoBaja = cbxTipoBaja.getSelectedItem().toString();
        System.out.println("Tipo baja: " + tipoBaja);

        try {
            //sql = "INSERT INTO bajas SELECT * FROM alumnos WHERE matricula = '" + txtMatricula.getText() + "'";
            sql = "INSERT INTO bajas (matricula,nombre,apaterno,amaterno,nivel,grado,turno,dia,mes,year,direccion,calificacion,beca,taller,rfcPadre,tipoBaja)"
                    + "VALUES('" + txtMatricula.getText() + "','" + txtNombreA.getText() + "','" + txtApaternoA.getText() + "','" + txtAmaternoA.getText() + "','" + Principal.operacion
                    + "','" + cbxGrado.getSelectedItem().toString() + "','" + cbxTurno.getSelectedItem().toString() + "','" + cbxDia.getSelectedItem().toString()
                    + "','" + cbxMes.getSelectedItem().toString() + "','" + cbxYear.getSelectedItem().toString() + "','" + txtDireccion.getText()
                    + "','" + txtCalificacion.getText() + "','" + cbxBeca.getSelectedItem().toString() + "','" + Principal.taller + "','" + txtRFC.getText() + "','"
                    + tipoBaja + "')";

            ps = reg.prepareStatement(sql);
            ps.executeUpdate();
        } catch (java.sql.SQLException | java.lang.NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al clonar el registro del alumno hacia la tabla bajas\nEx " + ex);
        }

        try {
            sql = "UPDATE bajas SET tipoBaja = '" + tipoBaja + "' WHERE matricula = '" + txtMatricula.getText() + "'";
            ps = reg.prepareStatement(sql);
            ps.execute();
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al editar los datos del alumno de tabla bajas");

        }

        try {
            sql = "DELETE FROM ALUMNOS WHERE matricula = '" + txtMatricula.getText() + "'";
            ps = reg.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El alumno ha sido dado de baja correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al eliminar los datos del contacto");
        }

        try {
            sql = "DELETE FROM PADRES WHERE rfc = '" + txtRFC.getText() + "'";
            ps = reg.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El padre del alumno ha sido dado de eliminado correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error al eliminar los datos del contacto");
        }        
    }

    public boolean verificarDatos() {
        if (txtMatricula.getText().length() == 0 || txtNombreA.getText().length() == 0 || txtApaternoA.getText().length() == 0
                || txtAmaternoA.getText().length() == 0 || txtDireccion.getText().length() == 0 || txtCalificacion.getText().length() == 0
                || txtRFC.getText().length() == 0 || txtNombreB.getText().length() == 0 || txtApaternoB.getText().length() == 0
                || txtAmaternoB.getText().length() == 0 || txtTelefono.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Faltan datos por ingresar");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombreA = new javax.swing.JTextField();
        txtApaternoA = new javax.swing.JTextField();
        txtAmaternoA = new javax.swing.JTextField();
        cbxGrado = new javax.swing.JComboBox<>();
        cbxDia = new javax.swing.JComboBox<>();
        cbxMes = new javax.swing.JComboBox<>();
        cbxYear = new javax.swing.JComboBox<>();
        txtMatricula = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtNombreB = new javax.swing.JTextField();
        txtRFC = new javax.swing.JTextField();
        txtApaternoB = new javax.swing.JTextField();
        txtAmaternoB = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        btnTitulo = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnOperacion = new javax.swing.JButton();
        cbxTurno = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        txtCalificacion = new javax.swing.JTextField();
        cbxBeca = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(232, 232, 232));
        setMinimumSize(new java.awt.Dimension(1156, 750));
        setPreferredSize(new java.awt.Dimension(1366, 676));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreA.setBackground(new java.awt.Color(232, 232, 232));
        txtNombreA.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtNombreA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        txtNombreA.setMinimumSize(new java.awt.Dimension(64, 25));
        add(txtNombreA, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 178, 25));

        txtApaternoA.setBackground(new java.awt.Color(232, 232, 232));
        txtApaternoA.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtApaternoA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApaternoA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        txtApaternoA.setPreferredSize(new java.awt.Dimension(103, 25));
        add(txtApaternoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 149, -1));

        txtAmaternoA.setBackground(new java.awt.Color(232, 232, 232));
        txtAmaternoA.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtAmaternoA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAmaternoA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        txtAmaternoA.setPreferredSize(new java.awt.Dimension(107, 25));
        add(txtAmaternoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 140, -1));

        cbxGrado.setBackground(new java.awt.Color(232, 232, 232));
        cbxGrado.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxGrado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxGrado.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 310, -1));

        cbxDia.setBackground(new java.awt.Color(232, 232, 232));
        cbxDia.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cbxDia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxDia.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, -1, -1));

        cbxMes.setBackground(new java.awt.Color(232, 232, 232));
        cbxMes.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cbxMes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxMes.setPreferredSize(new java.awt.Dimension(95, 25));
        add(cbxMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 117, -1));

        cbxYear.setBackground(new java.awt.Color(232, 232, 232));
        cbxYear.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018" }));
        cbxYear.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxYear.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 80, -1));

        txtMatricula.setBackground(new java.awt.Color(232, 232, 232));
        txtMatricula.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMatricula.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        txtMatricula.setMinimumSize(new java.awt.Dimension(64, 25));
        txtMatricula.setPreferredSize(new java.awt.Dimension(64, 25));
        add(txtMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 120, -1));

        txtDireccion.setBackground(new java.awt.Color(232, 232, 232));
        txtDireccion.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        txtDireccion.setPreferredSize(new java.awt.Dimension(64, 25));
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 310, -1));

        txtNombreB.setBackground(new java.awt.Color(232, 232, 232));
        txtNombreB.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtNombreB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreB.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12))); // NOI18N
        txtNombreB.setMinimumSize(new java.awt.Dimension(64, 25));
        txtNombreB.setPreferredSize(new java.awt.Dimension(73, 25));
        add(txtNombreB, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 178, -1));

        txtRFC.setBackground(new java.awt.Color(232, 232, 232));
        txtRFC.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtRFC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRFC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12))); // NOI18N
        txtRFC.setMinimumSize(new java.awt.Dimension(64, 25));
        txtRFC.setPreferredSize(new java.awt.Dimension(64, 25));
        add(txtRFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 120, -1));

        txtApaternoB.setBackground(new java.awt.Color(232, 232, 232));
        txtApaternoB.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtApaternoB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApaternoB.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12))); // NOI18N
        txtApaternoB.setMinimumSize(new java.awt.Dimension(64, 25));
        txtApaternoB.setPreferredSize(new java.awt.Dimension(103, 25));
        add(txtApaternoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 149, -1));

        txtAmaternoB.setBackground(new java.awt.Color(232, 232, 232));
        txtAmaternoB.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtAmaternoB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAmaternoB.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12))); // NOI18N
        txtAmaternoB.setMinimumSize(new java.awt.Dimension(64, 25));
        txtAmaternoB.setPreferredSize(new java.awt.Dimension(107, 25));
        add(txtAmaternoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 280, 149, -1));

        txtTelefono.setBackground(new java.awt.Color(232, 232, 232));
        txtTelefono.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12))); // NOI18N
        txtTelefono.setMinimumSize(new java.awt.Dimension(64, 25));
        txtTelefono.setPreferredSize(new java.awt.Dimension(64, 25));
        add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 310, -1));

        btnTitulo.setBackground(new java.awt.Color(215, 215, 215));
        btnTitulo.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 24)); // NOI18N
        btnTitulo.setForeground(new java.awt.Color(64, 64, 64));
        btnTitulo.setText("Inscripción a");
        btnTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnTitulo.setBorderPainted(false);
        btnTitulo.setFocusPainted(false);
        add(btnTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 720, -1));

        jButton5.setBackground(new java.awt.Color(215, 215, 215));
        jButton5.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(64, 64, 64));
        jButton5.setText("Datos del Alumno");
        jButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton5.setBorderPainted(false);
        jButton5.setFocusPainted(false);
        add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 310, -1));

        jButton6.setBackground(new java.awt.Color(215, 215, 215));
        jButton6.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(64, 64, 64));
        jButton6.setText("Datos del Padre o Tutor");
        jButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton6.setBorderPainted(false);
        jButton6.setFocusPainted(false);
        add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 310, -1));

        btnOperacion.setBackground(new java.awt.Color(2, 62, 138));
        btnOperacion.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 18)); // NOI18N
        btnOperacion.setForeground(new java.awt.Color(232, 232, 232));
        btnOperacion.setText("Finalizar Inscripcion");
        btnOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperacionActionPerformed(evt);
            }
        });
        add(btnOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 610, 720, 51));

        cbxTurno.setBackground(new java.awt.Color(232, 232, 232));
        cbxTurno.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino" }));
        cbxTurno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxTurno.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 520, 310, -1));

        jButton7.setBackground(new java.awt.Color(215, 215, 215));
        jButton7.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(64, 64, 64));
        jButton7.setText("Becas");
        jButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton7.setBorderPainted(false);
        jButton7.setFocusPainted(false);
        add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 310, -1));

        txtCalificacion.setBackground(new java.awt.Color(232, 232, 232));
        txtCalificacion.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        txtCalificacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCalificacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        txtCalificacion.setMinimumSize(new java.awt.Dimension(64, 25));
        txtCalificacion.setPreferredSize(new java.awt.Dimension(64, 25));
        add(txtCalificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 310, -1));

        cbxBeca.setBackground(new java.awt.Color(232, 232, 232));
        cbxBeca.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        cbxBeca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Excelencia", "Manutencion", "Foraneo", "Ninguna" }));
        cbxBeca.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic Semilight", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        cbxBeca.setPreferredSize(new java.awt.Dimension(72, 25));
        add(cbxBeca, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 520, 310, -1));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Calificacion del Ultimo Grado Cursado");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 430, 310, -1));

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Telefono");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 310, -1));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Apellido Paterno");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 149, -1));

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Apellido Materno");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 250, 149, -1));

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Becas disponibles para aplicacion");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 310, -1));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nombre (s)");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 178, -1));

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("RFC");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, 120, -1));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Nombre (s)");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 178, -1));

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Matricula");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 120, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Apellido Paterno");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 149, -1));

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Apellido Materno");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 149, -1));

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Fecha de Nacimiento");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 310, -1));

        jLabel13.setBackground(new java.awt.Color(51, 51, 51));
        jLabel13.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Direccion");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 310, -1));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Grado a cursar");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, 310, -1));

        jLabel15.setBackground(new java.awt.Color(51, 51, 51));
        jLabel15.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Horario");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 310, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperacionActionPerformed
        if ("Finalizar Inscripcion".equals(btnOperacion.getText())) {
            if (verificarDatos()) {
                inscribirAlumno();
                registrarPadre();
            }
        } else if ("Modificar Registro".equals(btnOperacion.getText())) {
            if (verificarDatos()) {
                editarRegistro();
            }
        } else if ("Confirmar Baja".equals(btnOperacion.getText())) { //Se hace modificacion
            if (verificarDatos()) {
                darDeBaja();
            }
        }        
        revalidate();
        repaint();
    }//GEN-LAST:event_btnOperacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnOperacion;
    public static javax.swing.JButton btnTitulo;
    private static javax.swing.JComboBox<String> cbxBeca;
    private static javax.swing.JComboBox<String> cbxDia;
    private static javax.swing.JComboBox<String> cbxGrado;
    private static javax.swing.JComboBox<String> cbxMes;
    private static javax.swing.JComboBox<String> cbxTurno;
    private static javax.swing.JComboBox<String> cbxYear;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private static javax.swing.JTextField txtAmaternoA;
    private static javax.swing.JTextField txtAmaternoB;
    private static javax.swing.JTextField txtApaternoA;
    private static javax.swing.JTextField txtApaternoB;
    private static javax.swing.JTextField txtCalificacion;
    private static javax.swing.JTextField txtDireccion;
    private static javax.swing.JTextField txtMatricula;
    private static javax.swing.JTextField txtNombreA;
    private static javax.swing.JTextField txtNombreB;
    private static javax.swing.JTextField txtRFC;
    private static javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
