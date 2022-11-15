/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inscripciones;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
    
    JPanel pnlContainer = new JPanel();
    JPanel pnlIcon = new JPanel();
    JPanel pnlDatos = new JPanel();
    JPanel pnlBotones = new JPanel();   
    
    JLabel lblIconoProveedor = new JLabel();
    
    JLabel lblIdProveedor = new JLabel("ID proveedor");
    JLabel lblNombre = new JLabel("Nombre proveedor");
    JLabel lblRFC = new JLabel("RFC proveedor");
    JLabel lblTelefono = new JLabel("Telefono proveedor");
    
    JButton btnCopiarTelefono = new JButton("Copiar Telefono");
    JButton btnEditar = new JButton("Editar");
    JButton btnEliminar = new JButton("Eliminar");
    JButton btnAgregar = new JButton("Agregar");
    
    GridBagConstraints constraints = new GridBagConstraints();
    
    public TarjetaProveedores() {
        pnlIcon.setSize(320, 320);
        pnlIcon.setBackground(Color.red);
        
        
        pnlDatos.setSize(320, 320);
        pnlDatos.setBackground(Color.yellow);
        
        pnlBotones.setSize(320, 320);
        pnlBotones.setBackground(Color.blue);
        
        pnlContainer.setLayout(new GridBagLayout());
        
        //El icono es proveido por la base de datos
        lblIconoProveedor.setIcon(new ImageIcon(getClass().getResource("/Recursos/retrato (2).png")));
        pnlIcon.add(lblIconoProveedor);
        
        pnlDatos.setLayout(new GridBagLayout());
        
        constraints.gridx = 0;  
        constraints.gridy = 0; 
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weighty = 1;
        pnlDatos.add(lblIdProveedor, constraints);
        
        constraints.gridx = 0;    
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;        
        pnlDatos.add(lblNombre, constraints);
        
        constraints.gridx = 0;       
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        pnlDatos.add(lblRFC, constraints);
        
        constraints.gridx = 0;     
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        pnlDatos.add(lblTelefono, constraints);
        
        
        pnlBotones.add(btnCopiarTelefono);
        pnlBotones.add(btnEditar);
        
        pnlContainer.setSize(1140, 320);
        
        constraints.gridx = 0;
        constraints.gridy = 0; 
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
          constraints.fill = GridBagConstraints.HORIZONTAL;
        
        pnlContainer.add(pnlIcon, constraints);
        constraints.gridx = 1;
        pnlContainer.add(pnlDatos, constraints);
        constraints.gridx = 2;
        pnlContainer.add(pnlBotones, constraints);
        
        /*
        pnlTarjeta.add(lblIconoProveedor);
        
        pnlTarjeta.add(lblIdProveedor);
        pnlTarjeta.add(lblNombre);
        pnlTarjeta.add(lblRFC);
        pnlTarjeta.add(lblTelefono);
        pnlTarjeta.add(btnCopiarTelefono);
        pnlTarjeta.add(btnEditar);
        pnlTarjeta.add(btnEliminar);
        pnlTarjeta.add(btnAgregar);
        
        pnlTarjeta.setBackground(Color.red);*/
        
        ventana.setLayout(new GridBagLayout());
        
        ventana.setSize(1240, 420); //+100 en ambas coordenadas
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setContentPane(pnlContainer);
        ventana.setBackground(Color.yellow);
        ventana.setVisible(true);
    }
    
    public static void main(String[] args) {
        TarjetaProveedores nuevo = new TarjetaProveedores();
    }
}
