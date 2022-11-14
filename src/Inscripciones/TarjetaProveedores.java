/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inscripciones;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Main PC
 */
public class TarjetaProveedores {
    JFrame ventana = new JFrame();
    
    JPanel pnlTarjeta = new JPanel();
    JLabel lblIconoProveedor = new JLabel("Icono");
    JLabel lblIdProveedor = new JLabel("ID proveedor");
    JLabel lblNombre = new JLabel("Nombre proveedor");
    JLabel lblRFC = new JLabel("RFC proveedor");
    JLabel lblTelefono = new JLabel("Telefono proveedor");
    JButton btnCopiarTelefono = new JButton("Copiar Telefono");
    JButton btnEditar = new JButton("Editar");
    JButton btnEliminar = new JButton("Eliminar");
    JButton btnAgregar = new JButton("Agregar");
    
    public TarjetaProveedores() {
        pnlTarjeta.setSize(1140, 320);
        
        pnlTarjeta.add(lblIconoProveedor);
        pnlTarjeta.add(lblIdProveedor);
        pnlTarjeta.add(lblNombre);
        pnlTarjeta.add(lblRFC);
        pnlTarjeta.add(lblTelefono);
        pnlTarjeta.add(btnCopiarTelefono);
        pnlTarjeta.add(btnEditar);
        pnlTarjeta.add(btnEliminar);
        pnlTarjeta.add(btnAgregar);
        
        pnlTarjeta.setBackground(Color.red);
        
        ventana.setLayout(new GridBagLayout());
        
        ventana.setSize(1240, 420); //+100 en ambas coordenadas
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setContentPane(pnlTarjeta);
        ventana.setBackground(Color.yellow);
        ventana.setVisible(true);
    }
    
    public static void main(String[] args) {
        TarjetaProveedores nuevo = new TarjetaProveedores();
    }
}
