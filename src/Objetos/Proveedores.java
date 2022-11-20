package Objetos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Kevin MG
 */
public class Proveedores {

    //Atributos
    String idProveedor;
    String nombre;
    String rfc;
    String telefono;

    //Metodos
    public void registrarProveedor() { //Insercion de nuevo Proveedor        
        System.out.println("Se registrara un nuevo Proveedor\n");

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        JTextField txtIdProveedor = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtRFC = new JTextField();
        JTextField txtTelefono = new JTextField();
        Proveedores nuevoProv = new Proveedores();

        Object[] inputFields = {"Ingrese los datos que se solicitan\nID del Proveedor: ", txtIdProveedor,
            "Nombre:", txtNombre, "RFC:", txtRFC, "Telefono:", txtTelefono};

        int option = JOptionPane.showConfirmDialog(null,
                inputFields, "Ingreso de Datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            nuevoProv.setIdProveedor(txtIdProveedor.getText());
            nuevoProv.setNombre(txtNombre.getText());
            nuevoProv.setRfc(txtRFC.getText());
            nuevoProv.setTelefono(txtTelefono.getText());

            System.out.println("Datos del Proveedor:\n" + nuevoProv.toString() + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
        }

        // </editor-fold>
        
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("INSERT INTO proveedores VALUES (?,?,?,?)");

            ps.setString(1, nuevoProv.getIdProveedor());
            ps.setString(2, nuevoProv.getNombre());
            ps.setString(3, nuevoProv.getRfc());
            ps.setString(4, nuevoProv.getTelefono());

            int filasInsertadas = ps.executeUpdate();
            System.out.println("Insercion exitosa.\nRegistros insertados: " + filasInsertadas);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void actualizarDatosProveedor() { //Actualizacion de proveedores

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        Proveedores modificarProv = new Proveedores(JOptionPane.showInputDialog(null, "Ingrese el ID del proveedor que desea modificar"));

        if (!existeRegistro(modificarProv)) { //Si no existe el registro, se termina el metodo
            return;
        }
        System.out.println("Se actualizara un proveedor");

        Object[] botones = {"Nombre", "RFC", "Telefono", "Cancelar"};

        int opcion = JOptionPane.showOptionDialog(null, "Seleccione el dato a modificar", "Actualizar Datos",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
        String nuevoDato = "";
        String tipoDato = "";
        switch (opcion) {
            case 0:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del proveedor");
                tipoDato = "nombre";
                break;
            case 1:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo rfc del proveedor");
                tipoDato = "rfc";
                break;
            case 2:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo telefono del proveedor");
                tipoDato = "telefono";
                break;
            case 3:
                return;
            default:
                break;
        }

        // </editor-fold>
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("UPDATE proveedores SET " + tipoDato + "=? WHERE idProveedor=?");
            ps.setString(1, nuevoDato);
            ps.setString(2, modificarProv.getIdProveedor());
            System.out.println("Filas afectadas: " + ps.executeUpdate());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarProveedor() { //Eliminacion de proveedoress              

        Proveedores eliminarProv = new Proveedores(JOptionPane.showInputDialog(null, "Ingrese el ID del proveedor que desea eliminar"));

        if (!existeRegistro(eliminarProv)) { //Si no existe el registro, se termina el metodo
            return;
        }
        System.out.println("Se eliminara un nuevo proveedor\n");

        int option = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de eliminar a este proveedor?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                PreparedStatement ps = Conexion.con.prepareStatement("DELETE FROM proveedores WHERE idProveedor=?");

                ps.setString(1, eliminarProv.getIdProveedor());

                int filasInsertadas = ps.executeUpdate();
                System.out.println("Eliminacion exitosa.\nRegistros eliminados: " + filasInsertadas);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void consultarProveedor() { //Consulta de un proveedor
        ResultSet rs;
        String consultaSQL = "SELECT * FROM proveedores WHERE idProveedor=?";
        Proveedores buscarProv = new Proveedores(JOptionPane.showInputDialog(null, "Ingrese el ID del proveedor que desea consultar"));

        try {
            PreparedStatement ps = Conexion.con.prepareStatement(consultaSQL);
            ps.setString(1, buscarProv.getIdProveedor());
            rs = ps.executeQuery();

            if (rs.next()) {
                buscarProv.setNombre(rs.getString("nombre"));
                buscarProv.setRfc(rs.getString("rfc"));
                buscarProv.setTelefono(rs.getString("telefono"));

                System.out.println("Proveedor encontrado:\n" + buscarProv.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No hay ningun registro con ese ID");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean existeRegistro(Proveedores proveedor) {
        try { //Una consulta para verificar que el proveedor ya exista
            ResultSet rs;
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM proveedores WHERE idProveedor='" + proveedor.getIdProveedor() + "'");
            rs = ps.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No hay ningun registro con ese ID");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" Getters y Setters ">
    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // </editor-fold>
    @Override
    public String toString() {
        return "Proveedores\nidProveedor: " + idProveedor + "\nNombre: " + nombre + "\nRFC: " + rfc + "\nTelefono: " + telefono;
    }

    //Constructores
    public Proveedores() {
    }

    public Proveedores(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedores(String idProveedor, String nombre, String rfc, String telefono) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.rfc = rfc;
        this.telefono = telefono;
    }

}
