package Objetos;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Kevin MG
 */
public class Empleados {

    //Atributos
    String idEmpleado;
    String nombre;
    String puesto;
    String turno;

    //Metodos           
    public String generarID(String nombre) { //Generacion automatica de la ID
        String ID = Character.toString(nombre.charAt(0));
        for (int x = 1; x < nombre.length(); x++) {
            //Verificar si se encuentra un espacio vacio para obtener la segunda letra del ID, una posicion despues del espacio
            if (nombre.charAt(x) == (' ') && (x + 1 < nombre.length())) {
                ID += Character.toString(nombre.charAt(x + 1));
                break;
            }
        }

        if (ID.length() == 1) {
            ID += "X";
        }

        for (int x = 1; x < 1000; x++) {
            if (x < 10) {
                if (!existeRegistro(new Empleados(ID + "00" + x))) {
                    ID += "00" + x;
                    return ID;
                }
            } else if (x < 100) {
                if (!existeRegistro(new Empleados(ID + "0" + x))) {
                    ID += "0" + x;
                    return ID;
                }
            } else {
                if (!existeRegistro(new Empleados(ID + x))) {
                    ID += x;
                    return ID;
                }
            }
        }
        return ID;
    }

    public Empleados registrarEmpleado(Empleados empleado) { //Insercion de nuevo empleado        
        System.out.println("Se registrara un nuevo empleado\n");

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        //Empleados nuevoEmp = new Empleados();
        empleado.setIdEmpleado(generarID(empleado.getNombre()));

        /*JTextField txtIdEmpleado = new JTextField();
        txtIdEmpleado.setFocusable(false);
        txtIdEmpleado.setForeground(Color.GREEN);
        JTextField txtNombre = new JTextField();
        JTextField txtPuesto = new JTextField();
        JTextField txtTurno = new JTextField();

        Object[] inputFields = {"Ingrese los datos que se solicitan\n\nNombre:", txtNombre, "Puesto:", txtPuesto, "Turno:", txtTurno};

        int option = JOptionPane.showConfirmDialog(null,
                inputFields, "Ingreso de Datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            nuevoEmp.setIdEmpleado(generarID(txtNombre.getText()));
            nuevoEmp.setNombre(txtNombre.getText());
            nuevoEmp.setPuesto(txtPuesto.getText());
            nuevoEmp.setTurno(txtTurno.getText());

            System.out.println("Datos del empleado:\n" + nuevoEmp.toString() + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
        }*/
        // </editor-fold>
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("INSERT INTO empleados VALUES (?,?,?,?)");

            ps.setString(1, empleado.getIdEmpleado());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getPuesto());
            ps.setString(4, empleado.getTurno());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro exitoso\nID del Empleado: " + empleado.getIdEmpleado());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return empleado;
    }

    public void actualizarDatosEmpleado(Empleados empleado) { //Actualizacion de empleados  
        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        /*Empleados modificarEmpl = new Empleados(JOptionPane.showInputDialog(null, "Ingrese el ID del empleado que desea modificar"));

        if (!existeRegistro(modificarEmpl)) { //Si no existe el registro, se termina el metodo
            return;
        }*/
        System.out.println("Se actualizara un empleado");

        /*Object[] botones = {"Nombre", "Puesto", "Turno", "Cancelar"};

        int opcion = JOptionPane.showOptionDialog(null, "Seleccione el dato a modificar", "Actualizar Datos",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
        String nuevoDato = "";
        String tipoDato = "";
        switch (opcion) {
            case 0:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del empleado");
                tipoDato = "nombre";
                break;
            case 1:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo puesto del empleado");
                tipoDato = "puesto";
                break;
            case 2:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo turno del empleado");
                tipoDato = "turno";
                break;
            case 3:
                return;
            default:
                break;
        }*/
        // </editor-fold>
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("UPDATE empleados SET nombre='" + empleado.getNombre() + "',puesto='" + empleado.getPuesto() + "',turno='" + empleado.getTurno() + "' WHERE idEmpleado='" + empleado.getIdEmpleado() + "'");
            //PreparedStatement ps = Conexion.con.prepareStatement("UPDATE empleados SET " + tipoDato + "=? WHERE idEmpleado=?");

            System.out.println("Filas afectadas: " + ps.executeUpdate());
            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarEmpleado(String ID) { //Eliminacion de empleados              

        //Empleados eliminarEmpl = new Empleados(JOptionPane.showInputDialog(null, "Ingrese el ID del empleado que desea eliminar"));

        /*if (!existeRegistro(eliminarEmpl)) { //Si no existe el registro, se termina el metodo
            return;
        }*/
        System.out.println("Se eliminara un nuevo empleado\n");

        /*int option = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de eliminar a este empleado?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);*/
        //if (option == JOptionPane.OK_OPTION) {
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("DELETE FROM empleados WHERE idEmpleado='" + ID + "'");

            int filasInsertadas = ps.executeUpdate();
            System.out.println("Eliminacion exitosa.\nRegistros eliminados: " + filasInsertadas);
            JOptionPane.showMessageDialog(null, "Se ha eliminado el empleado");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        //}
    }

    public Empleados consultarEmpleado(Empleados empleado) { //Consulta de un empleado en especifico por id
        ResultSet rs;
        String consultaSQL = "SELECT * FROM empleados WHERE idEmpleado=?";
        Empleados buscarEmpl = new Empleados(empleado.getIdEmpleado());

        try {
            PreparedStatement ps = Conexion.con.prepareStatement(consultaSQL);
            ps.setString(1, buscarEmpl.getIdEmpleado());
            rs = ps.executeQuery();

            if (rs.next()) {
                buscarEmpl.setIdEmpleado(rs.getString("idEmpleado"));
                buscarEmpl.setNombre(rs.getString("nombre"));
                buscarEmpl.setPuesto(rs.getString("puesto"));
                buscarEmpl.setTurno(rs.getString("turno"));

                System.out.println("Empleado encontrado:\n" + buscarEmpl.toString());
                return buscarEmpl;
            } else {
                System.out.println("No se encontró ningún empleado");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public Empleados consultarEmpleadoXNombre(String nombre) { //Consulta de un empleado
        ResultSet rs;
        String consultaSQL = "SELECT * FROM empleados WHERE nombre='" + nombre + "'";
        Empleados buscarEmpl = new Empleados();

        try {
            PreparedStatement ps = Conexion.con.prepareStatement(consultaSQL);
            rs = ps.executeQuery();

            if (rs.next()) {
                buscarEmpl.setIdEmpleado(rs.getString("idEmpleado"));
                buscarEmpl.setNombre(rs.getString("nombre"));
                buscarEmpl.setPuesto(rs.getString("puesto"));
                buscarEmpl.setTurno(rs.getString("turno"));

                System.out.println("Empleado encontrado:\n" + buscarEmpl.toString());
                return buscarEmpl;
            } else {
                System.out.println("No se encontró ningún empleado");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    ArrayList<Empleados> listaDeEmpleados;

    public ArrayList<Empleados> consultarEmpleados() { //Metodo que consulta a todos los empleados de la tabla y los guarda en un vector
        ResultSet rs;
        listaDeEmpleados = new ArrayList<>();
        String consultaSQL = "SELECT * FROM empleados";
        Empleados buscarEmpl;

        try {
            PreparedStatement ps = Conexion.con.prepareStatement(consultaSQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                buscarEmpl = new Empleados();
                buscarEmpl.setIdEmpleado(rs.getString("idEmpleado"));
                buscarEmpl.setNombre(rs.getString("nombre"));
                buscarEmpl.setPuesto(rs.getString("puesto"));
                buscarEmpl.setTurno(rs.getString("turno"));

                listaDeEmpleados.add(buscarEmpl);
            }
            return listaDeEmpleados;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public boolean existeRegistro(Empleados empleado) {
        try { //Una consulta para verificar que el empleado ya exista
            ResultSet rs;
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM empleados WHERE idEmpleado='" + empleado.getIdEmpleado() + "'");
            rs = ps.executeQuery();

            if (!rs.next()) {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    public boolean iniciarSesion(Empleados empleado) {
        try {
            ResultSet rs;
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM empleados WHERE idEmpleado='" + empleado.getIdEmpleado() + "'");
            rs = ps.executeQuery();

            if (rs.next() && empleado.getIdEmpleado().equals(rs.getString("idEmpleado"))) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> consultarPuestos() { //Consultar los puestos de los empleados y ordernarlos 
        ArrayList<String> listaPuestos = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT DISTINCT puesto FROM empleados ORDER BY puesto ASC");
            rs = ps.executeQuery();

            while (rs.next()) {
                listaPuestos.add(rs.getString("puesto"));
            }
            return listaPuestos;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Empleado\nidEmpleado: " + idEmpleado + "\nNombre: " + nombre + "\nPuesto: " + puesto + "\nTurno: " + turno;
    }

    // <editor-fold defaultstate="collapsed" desc=" Getters and Setters ">
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    // </editor-fold>
    //Constructores
    public Empleados() {
    }

    public Empleados(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleados(String idEmpleado, String nombre, String puesto, String turno) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.puesto = puesto;
        this.turno = turno;
    }
}
