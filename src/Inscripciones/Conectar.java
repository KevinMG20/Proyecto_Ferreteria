/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inscripciones;

import java.sql.*;

public class Conectar {

    Connection con = null;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/escuela", "root", "");
            System.out.println("Conexion realizada");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en la conexion: " + ex);
        }
        return con;
    }
}
