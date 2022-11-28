package Objetos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Kevin MG
 */
public class Ventas {

    //Atributos
    String folioVenta;
    String idProductos[];
    int cantidades[];
    float importe;
    String idEmpleado;

    //Metodos     
    public String generarFolio(int noProductos) {
        String folioV = "VNT_";

        Date fecha = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyy");
        System.out.println(dateFormat.format(fecha));
        if (dateFormat.format(fecha).length() < 8) { //Para los primeros 10 dias del mes
            folioV += "0";
            folioV += dateFormat.format(fecha).substring(0, 4);
            folioV += dateFormat.format(fecha).substring(5);
        } else {
            folioV += dateFormat.format(fecha).substring(0, 5);
            folioV += dateFormat.format(fecha).substring(6);
        }
        folioV += "_";

        for (int x = 1; x < 1000; x++) {
            if (x < 10) {
                if (!existeRegistro(new Ventas(folioV + "00" + x))) {
                    folioV += "00" + x;
                    break;
                }
            } else if (x < 100) {
                if (!existeRegistro(new Ventas(folioV + "0" + x))) {
                    folioV += "0" + x;
                    break;
                }
            } else {
                if (!existeRegistro(new Ventas(folioV + x))) {
                    folioV += x;
                    break;
                }
            }
        }

        folioV = folioV.toUpperCase();
        return folioV;
    }

    public void registrarVenta(Ventas nuevaVenta) { //Insercion de nueva venta  
        System.out.println("Se registrara una nueva venta\n");

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
            PreparedStatement insercionVenta = Conexion.con.prepareStatement("INSERT INTO ventas VALUES (?,?,?)");

            insercionVenta.setString(1, nuevaVenta.getFolioVenta());
            insercionVenta.setFloat(2, nuevaVenta.getImporte());
            insercionVenta.setString(3, nuevaVenta.getIdEmpleado());

            insercionVenta.execute();

            PreparedStatement insercionProductos;

            for (int x = 0; x < nuevaVenta.getIdProductos().length; x++) {
                insercionProductos = Conexion.con.prepareStatement("INSERT INTO productosdeventa VALUES (?,?,?)");
                insercionProductos.setString(1, nuevaVenta.getFolioVenta());
                insercionProductos.setString(2, nuevaVenta.getIdProductos()[x]);
                insercionProductos.setInt(3, nuevaVenta.getCantidades()[x]);
                insercionProductos.execute();
            }

            Conexion.con.commit();
            Conexion.con.setAutoCommit(true);

            JOptionPane.showMessageDialog(null, "Venta registrada exitosamente");

            //int filasInsertadas = ps.executeUpdate();
            //System.out.println("Insercion exitosa.\nRegistros insertados: " + filasInsertadas);
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                Conexion.con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizarDatosVentas(Ventas modificarVenta) { //Actualizacion de ventas

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
            PreparedStatement ps = Conexion.con.prepareStatement("UPDATE ventas SET importe='" + modificarVenta.getImporte()
                    + "', idEmpleado='" + modificarVenta.getIdEmpleado() + "' WHERE folioVenta='" + modificarVenta.getFolioVenta() + "'");

            System.out.println("Filas afectadas: " + ps.executeUpdate());
            JOptionPane.showMessageDialog(null, "Se modificaron existosamente los datos de la venta");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void actualizarCantidades(int cantidad, String idProducto) {
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("UPDATE productosdeventa SET cantidad='" + cantidad
                    + "' WHERE idProducto='" + idProducto + "'");
            System.out.println("Filas afectadas: " + ps.executeUpdate());
            JOptionPane.showMessageDialog(null, "Se modificó la cantidad exitosamente");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarVenta(Ventas eliminarVenta) { //Eliminacion de ventas              
        System.out.println("Se eliminara una nueva venta\n");

        int option = JOptionPane.showConfirmDialog(null, "Eliminar una venta significa también eliminar todos los productos que se"
                + "vendieron en ella\n¿Estás seguro de eliminar esta venta y todos sus productos?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                Conexion.con.setAutoCommit(false);
                PreparedStatement psVenta = Conexion.con.prepareStatement("DELETE FROM ventas WHERE folioVenta=?");
                psVenta.setString(1, eliminarVenta.getFolioVenta());
                psVenta.execute();

                PreparedStatement psProductos = Conexion.con.prepareStatement("DELETE FROM productosdeventa WHERE folioVenta=?");
                psProductos.setString(1, eliminarVenta.getFolioVenta());
                psProductos.execute();

                Conexion.con.commit();
                Conexion.con.setAutoCommit(true);

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
    }

    public void eliminarProductoDeVenta(String idProducto, String folioVenta) { //Eliminacion de Ventas                            
        int option = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de eliminar el pruducto de la venta?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                PreparedStatement ps = Conexion.con.prepareStatement("DELETE FROM productosdeventa WHERE folioVenta=? AND idProducto=?");

                ps.setString(1, folioVenta);
                ps.setString(2, idProducto);

                int filasInsertadas = ps.executeUpdate();
                System.out.println("Eliminacion exitosa.\nRegistros eliminados: " + filasInsertadas);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

        }
    }

    /*public void consultarEmpleados() { //Consulta de un empleado
        ResultSet rs;
        String consultaSQL = "SELECT * FROM ventas WHERE folioVenta=?";
        Ventas buscarVentas = new Ventas(JOptionPane.showInputDialog(null, "Ingrese el folio de la venta que desea consultar"));

        try {
            PreparedStatement ps = Conexion.con.prepareStatement(consultaSQL);
            ps.setString(1, buscarVentas.getFolioVenta());
            rs = ps.executeQuery();

            if (rs.next()) {
                buscarVentas.setIdProductos(rs.getString("idProducto"));
                buscarVentas.setCantidades(rs.getInt("cantidad"));
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
    }*/
    public Ventas consultarVenta(Ventas ventaAux) { //Consulta de una venta
        ResultSet resultVenta, resultProductos;
        int rows = 0;
        String[] productoDeVenta;
        int[] cantidadesDeProductos;
        int x = 0;

        try {
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM ventas WHERE folioVenta='" + ventaAux.getFolioVenta() + "'");
            resultVenta = ps.executeQuery();

            if (resultVenta.next()) {
                ventaAux.setFolioVenta(resultVenta.getString("folioVenta"));
                ventaAux.setImporte(Float.parseFloat(resultVenta.getString("importe")));
                ventaAux.setIdEmpleado(resultVenta.getString("idEmpleado"));

                //Posteriormente, de cada venta, se consulta sus productos en la tabla foranea
                ps = Conexion.con.prepareStatement("SELECT * FROM productosdeventa WHERE folioVenta='"
                        + ventaAux.getFolioVenta() + "'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultProductos = ps.executeQuery();
                //Obtener la cantidad de productos de la venta
                if (resultProductos.last()) {
                    rows = resultProductos.getRow();
                    resultProductos.beforeFirst();
                }
                productoDeVenta = new String[rows];
                cantidadesDeProductos = new int[rows];

                //Se guardan todos los productos y sus cantidades en arrays
                while (resultProductos.next()) {
                    productoDeVenta[x] = resultProductos.getString("idProducto");
                    cantidadesDeProductos[x] = resultProductos.getInt("cantidad");
                    x++;
                }
                //Y se asignan al objeto
                ventaAux.setIdProductos(productoDeVenta);
                ventaAux.setCantidades(cantidadesDeProductos);

                return ventaAux;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Ventas> consultarTodasVentas() { //Consulta de todas las entregas
        ResultSet resultVenta, resultProductos;
        //String consultaEntrega = "SELECT * FROM entregas";
        //String consultaProductos = "SELECT * FROM productosdeentrega";
        ArrayList<Ventas> listaDeVentas = new ArrayList<>();
        Ventas buscarVenta;
        int rows = 0;
        String[] productoDeVenta;
        int[] cantidadesDeProductos;
        int x = 0;

        try {
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM ventas");
            resultVenta = ps.executeQuery();

            while (resultVenta.next()) {
                buscarVenta = new Ventas();

                buscarVenta.setFolioVenta(resultVenta.getString("folioVenta"));
                buscarVenta.setImporte(resultVenta.getFloat("importe"));
                buscarVenta.setIdEmpleado(resultVenta.getString("idEmpleado"));

                //Posteriormente, de cada venta, se consulta sus productos en la tabla foranea
                ps = Conexion.con.prepareStatement("SELECT * FROM productosdeventa WHERE folioVenta='"
                        + buscarVenta.getFolioVenta() + "'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultProductos = ps.executeQuery();
                //Obtener la cantidad de productos de la venta
                if (resultProductos.last()) {
                    rows = resultProductos.getRow();
                    resultProductos.beforeFirst();
                }
                productoDeVenta = new String[rows];
                cantidadesDeProductos = new int[rows];

                //Se guardan todos los productos y sus cantidades en arrays
                while (resultProductos.next()) {
                    productoDeVenta[x] = resultProductos.getString("idProducto");
                    cantidadesDeProductos[x] = resultProductos.getInt("cantidad");
                    x++;
                }
                //Y se asignan al objeto
                buscarVenta.setIdProductos(productoDeVenta);
                buscarVenta.setCantidades(cantidadesDeProductos);
                x = 0;
                listaDeVentas.add(buscarVenta);
            }
            return listaDeVentas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public boolean existeRegistro(Ventas venta) {
        try { //Una consulta para verificar que el empleado ya exista
            ResultSet rs;
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM ventas WHERE folioVenta='" + venta.getFolioVenta() + "'");
            rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Folio " + venta.getFolioVenta() + ". Disponible para su uso");
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
        return "Ventas\nfolioVenta: " + folioVenta + "\nidProducto: " + idProductos + "\nCantidad: " + cantidades + "\nImporte: " + importe + "\nidEmpleado: " + idEmpleado;
    }

    // <editor-fold defaultstate="collapsed" desc=" Getters and Setters ">
    public String getFolioVenta() {
        return folioVenta;
    }

    public void setFolioVenta(String folioVenta) {
        this.folioVenta = folioVenta;
    }

    public String[] getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(String idProductos[]) {
        this.idProductos = idProductos;
    }

    public int[] getCantidades() {
        return cantidades;
    }

    public void setCantidades(int cantidades[]) {
        this.cantidades = cantidades;
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

    public Ventas(String folioVenta, String idProductos[], int cantidades[], float importe, String idEmpleado) {
        this.folioVenta = folioVenta;
        this.idProductos = idProductos;
        this.cantidades = cantidades;
        this.importe = importe;
        this.idEmpleado = idEmpleado;
    }

}
