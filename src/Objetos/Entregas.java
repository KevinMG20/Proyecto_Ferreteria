package Objetos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    String[] idProductos;
    int[] cantidades;
    String idProveedor;
    String idEmpleado;

    //Metodos
    public String generarFolio(String fecha, String idProv) {
        try {
            fecha = fecha.toUpperCase();
            String folioEntrega = fecha.substring(0, 2);
            folioEntrega += fecha.substring(3, 8);
            folioEntrega += fecha.substring(10) + "_";
            folioEntrega += idProv + "_";

            for (int x = 1; x < 100; x++) {
                if (x < 10) {
                    if (!existeRegistro(new Entregas(folioEntrega + "0" + x))) {
                        folioEntrega += "0" + x;
                        return folioEntrega;
                    }
                } else if (x < 100) {
                    if (!existeRegistro(new Entregas(folioEntrega + x))) {
                        folioEntrega += "0" + x;
                        return folioEntrega;
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Introduzca una fecha que sea válida");
        }

        return null;
    }

    public void registrarEntrega(Entregas nuevaEntrega) { //Insercion de nueva entrega  
        System.out.println("Se registrara una nueva entrega\n");

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        /*JTextField txtFolioEntrega = new JTextField();
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
        }*/
        // </editor-fold>
        try {
            Conexion.con.setAutoCommit(false);
            PreparedStatement insercionEntrega = Conexion.con.prepareStatement("INSERT INTO entregas VALUES (?,?,?,?)");

            insercionEntrega.setString(1, nuevaEntrega.getFolioEntrega());
            insercionEntrega.setString(2, nuevaEntrega.getFecha());
            insercionEntrega.setString(3, nuevaEntrega.getIdProveedor());
            insercionEntrega.setString(4, nuevaEntrega.getIdEmpleado());

            insercionEntrega.execute();

            PreparedStatement insercionProductos;

            for (int x = 0; x < nuevaEntrega.getIdProductos().length; x++) {
                insercionProductos = Conexion.con.prepareStatement("INSERT INTO productosdeentrega VALUES (?,?,?)");
                insercionProductos.setString(1, nuevaEntrega.getFolioEntrega());
                insercionProductos.setString(2, nuevaEntrega.getIdProductos()[x]);
                insercionProductos.setInt(3, nuevaEntrega.getCantidades()[x]);
                insercionProductos.execute();
            }

            Conexion.con.commit();
            Conexion.con.setAutoCommit(true);

            //int filasInsertadas = ps.executeUpdate();
            //System.out.println("Insercion exitosa.\nRegistros insertados: " + filasInsertadas);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            try {
                Conexion.con.rollback();
            } catch (SQLException e) {
                ex.printStackTrace();
            }
        }
    }

    public void actualizarDatosEntregas(Entregas modificarEntrega) { //Actualizacion de entregas

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        /*Entregas modificarEntrega = new Entregas(JOptionPane.showInputDialog(null, "Ingrese el folio de la entrega que desea modificar"));

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
        }*/
        // </editor-fold>
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("UPDATE entregas SET fecha='" + modificarEntrega.getFecha()
                    + "', idProveedor='" + modificarEntrega.getIdProveedor() + "', idEmpleado='" + modificarEntrega.getIdEmpleado()
                    + "' WHERE folioEntrega='" + modificarEntrega.getFolioEntrega() + "'");

            System.out.println("Filas afectadas: " + ps.executeUpdate());
            JOptionPane.showMessageDialog(null, "Se modificaron existosamente los datos de la entrega");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void actualizarCantidades(int cantidad, String idProducto) {
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("UPDATE productosdeentrega SET cantidad='" + cantidad
                    + "' WHERE idProducto='" + idProducto + "'");
            System.out.println("Filas afectadas: " + ps.executeUpdate());
            JOptionPane.showMessageDialog(null, "Se modificó la cantidad exitosamente");
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

    public ArrayList<Entregas> consultarTodasEntregas() { //Consulta de una entrega
        ResultSet resultEntrega, resultProductos;
        //String consultaEntrega = "SELECT * FROM entregas";
        //String consultaProductos = "SELECT * FROM productosdeentrega";
        ArrayList<Entregas> listaDeEntregas = new ArrayList<>();
        Entregas buscarEntr;
        int rows = 0;
        String[] productoDeEntrega;
        int[] cantidadesDeProductos;
        int x = 0;

        try {
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM entregas");
            //ps.setString(1, buscarEntr.getFolioEntrega());
            //Lo primero es obtener todas las entregas que hay y almacenarlas
            resultEntrega = ps.executeQuery();

            while (resultEntrega.next()) {
                buscarEntr = new Entregas();

                buscarEntr.setFolioEntrega(resultEntrega.getString("folioEntrega"));
                buscarEntr.setFecha(resultEntrega.getString("fecha"));

                //Posteriormente, de cada entrega, se consulta sus productos en la tabla foranea
                ps = Conexion.con.prepareStatement("SELECT * FROM productosdeentrega WHERE folioEntrega='"
                        + buscarEntr.getFolioEntrega() + "'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultProductos = ps.executeQuery();
                //Obtener la cantidad de productos de la entrega
                if (resultProductos.last()) {
                    rows = resultProductos.getRow();
                    resultProductos.beforeFirst();
                }
                productoDeEntrega = new String[rows];
                cantidadesDeProductos = new int[rows];

                //Se guardan todos los productos y sus cantidades en arrays
                while (resultProductos.next()) {
                    productoDeEntrega[x] = resultProductos.getString("idProducto");
                    cantidadesDeProductos[x] = resultProductos.getInt("cantidad");
                    x++;
                }
                //Y se asignan al objeto
                buscarEntr.setIdProductos(productoDeEntrega);
                buscarEntr.setCantidades(cantidadesDeProductos);

                buscarEntr.setIdProveedor(resultEntrega.getString("idProveedor"));
                buscarEntr.setIdEmpleado(resultEntrega.getString("idEmpleado"));

                x = 0;
                listaDeEntregas.add(buscarEntr);
            }
            return listaDeEntregas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public boolean existeRegistro(Entregas entrega) {
        try { //Una consulta para verificar que el empleado ya exista
            ResultSet rs;
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM entregas WHERE folioEntrega='" + entrega.getFolioEntrega() + "'");
            rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("No hay ningun registro con ese ID");
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
        return "Entregas\nFolio Entrega: " + folioEntrega + "\nFecha: " + fecha + "\nID Producto: " + idProductos + "\nCantidad: " + cantidades + "\nID Proveedor " + idProveedor + "\nID Empleado: " + idEmpleado;
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

    public String[] getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(String[] idProducto) {
        this.idProductos = idProducto;
    }

    public int[] getCantidades() {
        return cantidades;
    }

    public void setCantidades(int[] cantidad) {
        this.cantidades = cantidad;
    }

    /*public String getIdProducto() {
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
    }*/
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

    public Entregas(String folioEntrega, String fecha, String[] idProductos, int[] cantidades, String idProveedor, String idEmpleado) {
        this.folioEntrega = folioEntrega;
        this.fecha = fecha;
        this.idProductos = idProductos;
        this.cantidades = cantidades;
        this.idProveedor = idProveedor;
        this.idEmpleado = idEmpleado;
    }

}
