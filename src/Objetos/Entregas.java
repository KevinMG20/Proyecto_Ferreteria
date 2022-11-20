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
public class Entregas {

    //Atributos
    String folioEntrega;
    String fecha;
    String idProducto;
    int cantidad;
    String idProveedor;
    String idEmpleado;

    //Metodos
    public void registrarEntrega() { //Insercion de nueva entrega  
        System.out.println("Se registrara una nueva entrega\n");

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        JTextField txtFolioEntrega = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextField txtIdProducto = new JTextField();
        JTextField txtCantidad = new JTextField();
        JTextField txtIdProveedor = new JTextField();
        JTextField txtIdEmpleado = new JTextField();
        Entregas nuevaEntrega = new Entregas();

        Object[] inputFields = {"Ingrese los datos que se solicitan\nFolio de la Entrega: ", txtFolioEntrega,
            "Fecha:", txtFecha, "ID del Producto:", txtIdProducto, "Cantidad:", txtCantidad,
            "ID del Proveedor:", txtIdProveedor, "ID del Empleado:", txtIdEmpleado};

        int option = JOptionPane.showConfirmDialog(null,
                inputFields, "Ingreso de Datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            nuevaEntrega.setFolioEntrega(txtFolioEntrega.getText());
            nuevaEntrega.setFecha(txtFecha.getText());
            nuevaEntrega.setIdProducto(txtIdProducto.getText());
            nuevaEntrega.setCantidad(Integer.parseInt(txtCantidad.getText()));
            nuevaEntrega.setIdProveedor(txtIdProveedor.getText());
            nuevaEntrega.setIdEmpleado(txtIdEmpleado.getText());

            System.out.println("Datos de la entrega:\n" + nuevaEntrega.toString() + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
        }

        // </editor-fold>
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("INSERT INTO entregas VALUES (?,?,?,?,?,?)");

            ps.setString(1, nuevaEntrega.getFolioEntrega());
            ps.setString(2, nuevaEntrega.getFecha());
            ps.setString(3, nuevaEntrega.getIdProducto());
            ps.setInt(4, nuevaEntrega.getCantidad());
            ps.setString(5, nuevaEntrega.getIdProveedor());
            ps.setString(6, nuevaEntrega.getIdEmpleado());

            int filasInsertadas = ps.executeUpdate();
            System.out.println("Insercion exitosa.\nRegistros insertados: " + filasInsertadas);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void actualizarDatosEntregas() { //Actualizacion de entregas

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        Entregas modificarEntrega = new Entregas(JOptionPane.showInputDialog(null, "Ingrese el folio de la entrega que desea modificar"));

        if (!existeRegistro(modificarEntrega)) { //Si no existe el registro, se termina el metodo
            return;
        }
        System.out.println("Se actualizara una entrega");

        Object[] botones = {"Fecha", "ID del Producto", "Cantidad del Producto", "ID del Proveedor", "ID del Empleado", "Cancelar"};

        int opcion = JOptionPane.showOptionDialog(null, "Seleccione el dato a modificar", "Actualizar Datos",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
        String nuevoDato = "";
        String tipoDato = "";
        switch (opcion) {
            case 0:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese la nueva fecha de la entrega");
                tipoDato = "fecha";
                break;
            case 1:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo ID del producto");
                tipoDato = "idProducto";
                break;
            case 2:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad de producto");
                tipoDato = "cantidad";
                break;
            case 3:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo ID del proveedor");
                tipoDato = "idProveedor";
                break;
            case 4:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo ID del empleado");
                tipoDato = "idEmpleado";
                break;
            case 5:
                return;
            default:
                break;
        }

        // </editor-fold>
        
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("UPDATE entregas SET " + tipoDato + "=? WHERE folioEntrega=?");
            ps.setString(1, nuevoDato);
            ps.setString(2, modificarEntrega.getFolioEntrega());
            System.out.println("Filas afectadas: " + ps.executeUpdate());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarEntrega() { //Eliminacion de entregas              

        Entregas eliminarEntr = new Entregas(JOptionPane.showInputDialog(null, "Ingrese el folio de la entrega que desea eliminar"));

        if (!existeRegistro(eliminarEntr)) { //Si no existe el registro, se termina el metodo
            return;
        }
        System.out.println("Se eliminara una nueva entrega\n");

        int option = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de eliminar esta entrega?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                PreparedStatement ps = Conexion.con.prepareStatement("DELETE FROM entregas WHERE folioEntrega=?");

                ps.setString(1, eliminarEntr.getFolioEntrega());

                int filasInsertadas = ps.executeUpdate();
                System.out.println("Eliminacion exitosa.\nRegistros eliminados: " + filasInsertadas);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void consultarEmpleados() { //Consulta de una entrega
        ResultSet rs;
        String consultaSQL = "SELECT * FROM entregas WHERE folioEntrega=?";
        Entregas buscarEntr = new Entregas(JOptionPane.showInputDialog(null, "Ingrese el folio de la entrega que desea consultar"));

        try {
            PreparedStatement ps = Conexion.con.prepareStatement(consultaSQL);
            ps.setString(1, buscarEntr.getFolioEntrega());
            rs = ps.executeQuery();

            if (rs.next()) {
                buscarEntr.setFecha(rs.getString("fecha"));
                buscarEntr.setIdProducto(rs.getString("idProducto"));
                buscarEntr.setCantidad(rs.getInt("cantidad"));
                buscarEntr.setIdProveedor(rs.getString("idProveedor"));
                buscarEntr.setIdEmpleado(rs.getString("idEmpleado"));

                System.out.println("\nRegistro encontrado:\n" + buscarEntr.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No hay ningun registro con ese ID");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean existeRegistro(Entregas entrega) {
        try { //Una consulta para verificar que el empleado ya exista
            ResultSet rs;
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM entregas WHERE folioEntrega='" + entrega.getFolioEntrega() + "'");
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
        return "Entregas\nFolio Entrega: " + folioEntrega + "\nFecha: " + fecha + "\nID Producto: " + idProducto + "\nCantidad: " + cantidad + "\nID Proveedor " + idProveedor + "\nID Empleado: " + idEmpleado;
    }

    // <editor-fold defaultstate="collapsed" desc=" Getters and Setters ">
    public String getFolioEntrega() {
        return folioEntrega;
    }

    public void setFolioEntrega(String folioEntrega) {
        this.folioEntrega = folioEntrega;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    // </editor-fold>

    //Constructores
    public Entregas() {
    }

    public Entregas(String folioEntrega) {
        this.folioEntrega = folioEntrega;
    }        

    public Entregas(String folioEntrega, String fecha, String idProducto, int cantidad, String idProveedor, String idEmpleado) {
        this.folioEntrega = folioEntrega;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idProveedor = idProveedor;
        this.idEmpleado = idEmpleado;
    }

}
