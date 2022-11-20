/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.sql.*;

public class Conexion {

    public static Connection con = null;

    public Conexion() {
        conectar();
    }

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ferreteria", "root", "");
            System.out.println("Conexion realizada");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en la conexion: " + ex.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (con != null) {
                con.close();

                con = null;

                System.out.println("Conexion finalizada");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
