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
public class Productos {

    //Atributos
    String idProducto;
    String nombre;
    String categoria;
    String marca;
    float precio;
    int existencias;

    //Metodos           
    public void registrarProducto() { //Insercion de nuevo producto        
        System.out.println("Se registrara un nuevo producto\n");

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        JTextField txtIdProducto = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtCategoria = new JTextField();
        JTextField txtMarca = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtExistencias = new JTextField();
        Productos nuevoProd = new Productos();

        Object[] inputFields = {"Ingrese los datos que se solicitan\nID del Producto: ", txtIdProducto,
            "Nombre: ", txtNombre, "Categoria: ", txtCategoria, "Marca: ", txtMarca, "Precio: ", txtPrecio,
            "Existencias: ", txtExistencias};

        int option = JOptionPane.showConfirmDialog(null,
                inputFields, "Ingreso de Datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            nuevoProd.setIdProducto(txtIdProducto.getText());
            nuevoProd.setNombre(txtNombre.getText());
            nuevoProd.setCategoria(txtCategoria.getText());
            nuevoProd.setMarca(txtMarca.getText());
            nuevoProd.setPrecio(Float.parseFloat(txtPrecio.getText()));
            nuevoProd.setExistencias(Integer.parseInt(txtExistencias.getText()));

            System.out.println("Datos del producto:\n" + nuevoProd.toString() + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
        }

        // </editor-fold>
        
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("INSERT INTO productos VALUES (?,?,?,?,?,?)");

            ps.setString(1, nuevoProd.getIdProducto());
            ps.setString(2, nuevoProd.getNombre());
            ps.setString(3, nuevoProd.getCategoria());
            ps.setString(4, nuevoProd.getMarca());
            ps.setFloat(5, nuevoProd.getPrecio());
            ps.setInt(6, nuevoProd.getExistencias());

            int filasInsertadas = ps.executeUpdate();
            System.out.println("Insercion exitosa.\nRegistros insertados: " + filasInsertadas);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void actualizarDatosProducto() { //Actualizacion de empleados

        // <editor-fold defaultstate="collapsed" desc=" Ingreso de Datos ">
        Productos modifProd = new Productos(JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea modificar"));

        if (!existeRegistro(modifProd)) { //Si no existe el registro, se termina el metodo
            return;
        }
        System.out.println("Se actualizara un producto");

        Object[] botones = {"Nombre", "Categoria", "Marca", "Precio", "Existencias", "Cancelar"};

        int opcion = JOptionPane.showOptionDialog(null, "Seleccione el dato a modificar", "Actualizar Datos",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
        String nuevoDato = "";
        String tipoDato = "";
        switch (opcion) {
            case 0:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del producto");
                tipoDato = "nombre";
                break;
            case 1:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese la nueva categoria del producto");
                tipoDato = "categoria";
                break;
            case 2:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese la nueva marca del producto");
                tipoDato = "marca";
                break;
            case 3:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese el nuevo precio del producto");
                tipoDato = "precio";
                break;
            case 4:
                nuevoDato = JOptionPane.showInputDialog(null, "Ingrese la nueva existencia del producto");
                tipoDato = "existencias";
                break;
            case 5:
                return;
            default:
                break;
        }

        // </editor-fold>
        try {
            PreparedStatement ps = Conexion.con.prepareStatement("UPDATE productos SET " + tipoDato + "=? WHERE idProducto=?");
            ps.setString(1, nuevoDato);
            ps.setString(2, modifProd.getIdProducto());
            System.out.println("Filas afectadas: " + ps.executeUpdate());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarProductos() { //Eliminacion de productos      

        Productos eliminarProd = new Productos(JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea eliminar"));

        if (!existeRegistro(eliminarProd)) { //Si no existe el registro, se termina el metodo
            return;
        }
        System.out.println("Se eliminara un nuevo producto\n");
        
        int option = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de eliminar a este producto?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                PreparedStatement ps = Conexion.con.prepareStatement("DELETE FROM productos WHERE idProducto=?");

                ps.setString(1, eliminarProd.getIdProducto());

                int filasInsertadas = ps.executeUpdate();
                System.out.println("Eliminacion exitosa.\nRegistros eliminados: " + filasInsertadas);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void consultarProductos() { //Consulta de un empleado
        ResultSet rs;
        String consultaSQL = "SELECT * FROM productos WHERE idProducto=?";
        Productos buscarProd = new Productos(JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea consultar"));

        try {
            PreparedStatement ps = Conexion.con.prepareStatement(consultaSQL);
            ps.setString(1, buscarProd.getIdProducto());
            rs = ps.executeQuery();

            if (rs.next()) {

                buscarProd.setNombre(rs.getString("nombre"));
                buscarProd.setCategoria(rs.getString("categoria"));
                buscarProd.setMarca(rs.getString("marca"));
                buscarProd.setPrecio(rs.getFloat("precio"));
                buscarProd.setExistencias(rs.getInt("existencias"));

                System.out.println("\nProducto encontrado:\n" + buscarProd.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No hay ningun registro con ese ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean existeRegistro(Productos producto) {
        try { //Una consulta para verificar que el producto ya exista
            ResultSet rs;
            PreparedStatement ps = Conexion.con.prepareStatement("SELECT * FROM productos WHERE idProducto='" + producto.getIdProducto() + "'");
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
        return "Productos\nidProducto: " + idProducto + "\nNombre: " + nombre + "\nCategoria: " + categoria
                + "\nMarco: " + marca + "\nPrecio: " + precio + "\nExistencias: " + existencias;
    }

    // <editor-fold defaultstate="collapsed" desc=" Getters and Setters ">
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    // </editor-fold>
    //Constructores
    public Productos() {
    }

    public Productos(String idProducto) {
        this.idProducto = idProducto;
    }

    public Productos(String idProducto, String nombre, String categoria, String marca, float precio, int existencias) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.marca = marca;
        this.precio = precio;
        this.existencias = existencias;
    }

}
