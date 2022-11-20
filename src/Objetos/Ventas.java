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
public class Ventas {

    //Atributos
    String folioVenta;
    String idProducto;
    int cantidad;
    float importe;
    String idEmpleado;

    //Metodos
    public void registrarVenta() { //Insercion de una nueva venta        
        System.out.println("Se registrara una nueva venta\n");

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        JTextField txtFolioVenta = new JTextField();
        JTextField txtIdProducto = new JTextField();
        JTextField txtCantidad = new JTextField();
        JTextField txtImporte = new JTextField();
        JTextField txtIdEmpleado = new JTextField();
        Ventas nuevaVenta = new Ventas();

        Object[] inputFields = {"Ingrese los datos que se solicitan\nFolio de Venta: ", txtFolioVenta,
            "ID del Producto:", txtIdProducto, "Cantidad:", txtCantidad, "Importe:", txtImporte, "ID del Empleado:", txtIdEmpleado};

        int option = JOptionPane.showConfirmDialog(null,
                inputFields, "Ingreso de Datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            nuevaVenta.setFolioVenta(txtFolioVenta.getText());
            nuevaVenta.setIdProducto(txtIdProducto.getText());
            nuevaVenta.setCantidad(Integer.parseInt(txtCantidad.getText()));
            nuevaVenta.setImporte(Float.parseFloat(txtImporte.getText()));
            nuevaVenta.setIdEmpleado(txtIdEmpleado.getText());

            System.out.println("Datos de la venta:\n" + nuevaVenta.toString() + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
        }

        // </editor-fold>
        
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("INSERT INTO ventas VALUES (?,?,?,?,?)");

            ps.setString(1, nuevaVenta.getFolioVenta());
            ps.setString(2, nuevaVenta.getIdProducto());
            ps.setInt(3, nuevaVenta.getCantidad());
            ps.setFloat(4, nuevaVenta.getImporte());
            ps.setString(5, nuevaVenta.getIdEmpleado());

            int filasInsertadas = ps.executeUpdate();
            System.out.println("Insercion exitosa.\nRegistros insertados: " + filasInsertadas);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void actualizarDatosVentas() { //Actualizacion de ventas

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        Ventas modificarVenta = new Ventas(JOptionPane.showInputDialog(null, "Ingrese el folio de la venta que desea modificar"));

        if (!existeRegistro(modificarVenta)) { //Si no existe el registro, se termina el metodo
            return;
        }
        System.out.println("Se actualizara un registro de venta");

        Object[] botones = {"ID Producto", "Cantidad", "Importe", "ID Empleado", "Cancelar"};

        int opcion = JOptionPane.showOptionDialog(null, "Seleccione el dato a modificar", "Actualizar Datos",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);

        String nuevoDato = "";
        String tipoDato = "";
        switch (opcion) {
            case 0:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo id del producto de la venta");
                tipoDato = "idProducto";
                break;
            case 1:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad del producto de la venta");
                tipoDato = "cantidad";
                break;
            case 2:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo importe de la venta");
                tipoDato = "importe";
                break;
            case 3:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo id del empledado encargado de la venta");
                tipoDato = "idEmpleado";
                break;
            case 4:
                return;
            default:
                break;
        }

        // </editor-fold>
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("UPDATE ventas SET " + tipoDato + "=? WHERE folioVenta=?");
            ps.setString(1, nuevoDato);
            ps.setString(2, modificarVenta.getFolioVenta());
            System.out.println("Filas afectadas: " + ps.executeUpdate());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarVenta() { //Eliminacion de ventas              

        Ventas eliminarVenta = new Ventas(JOptionPane.showInputDialog(null, "Ingrese el folio de la venta que desea eliminar"));

        if (!existeRegistro(eliminarVenta)) { //Si no existe el registro, se termina el metodo
            return;
        }
        System.out.println("Se eliminara una venta\n");

        int option = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de eliminar esta venta?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                PreparedStatement ps = Conexion.con.prepareStatement("DELETE FROM ventas WHERE folioVenta=?");

                ps.setString(1, eliminarVenta.getFolioVenta());

                int filasInsertadas = ps.executeUpdate();
                System.out.println("Eliminacion exitosa.\nRegistros eliminados: " + filasInsertadas);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void consultarEmpleados() { //Consulta de un empleado
        ResultSet rs;
        String consultaSQL = "SELECT * FROM ventas WHERE folioVenta=?";
        Ventas buscarVentas = new Ventas(JOptionPane.showInputDialog(null, "Ingrese el folio de la venta que desea consultar"));

        try {
            PreparedStatement ps = Conexion.con.prepareStatement(consultaSQL);
            ps.setString(1, buscarVentas.getFolioVenta());
            rs = ps.executeQuery();

            if (rs.next()) {
                buscarVentas.setIdProducto(rs.getString("idProducto"));
                buscarVentas.setCantidad(rs.getInt("cantidad"));
                buscarVentas.setImporte(rs.getFloat("importe"));
                buscarVentas.setIdEmpleado(rs.getString("idEmpleado"));

                System.out.println("Venta encontrado:\n" + buscarVentas.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No hay ningun registro con ese folio");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean existeRegistro(Ventas venta) {
        try { //Una consulta para verificar que el empleado ya exista
            ResultSet rs;
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM ventas WHERE folioVenta='" + venta.getFolioVenta() + "'");
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

    @Override
    public String toString() {
        return "Ventas\nfolioVenta: " + folioVenta + "\nidProducto: " + idProducto + "\nCantidad: " + cantidad + "\nImporte: " + importe + "\nidEmpleado: " + idEmpleado;
    }

        // <editor-fold defaultstate="collapsed" desc=" Getters and Setters ">
    public String getFolioVenta() {
        return folioVenta;
    }

    public void setFolioVenta(String folioVenta) {
        this.folioVenta = folioVenta;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    // </editor-fold>
    //Constructores
    public Ventas() {
    }

    public Ventas(String folioVenta) {
        this.folioVenta = folioVenta;
    }

    public Ventas(String folioVenta, String idProducto, int cantidad, float importe, String idEmpleado) {
        this.folioVenta = folioVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.importe = importe;
        this.idEmpleado = idEmpleado;
    }

}
