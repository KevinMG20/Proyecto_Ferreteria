/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inscripciones;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Main PC
 */
public class TarjetaProveedores {
    JPanel pnlTarjeta = new JPanel();
    JLabel lblIconoProveedor = new JLabel();
    JLabel lblIdProveedor = new JLabel();
    JLabel lblNombre = new JLabel();
    JLabel lblRFC = new JLabel();
    JLabel lblTelefono = new JLabel();
    JButton btnCopiarTelefono = new JButton();
    JButton btnEditar = new JButton();
    JButton btnEliminar = new JButton();
    JButton btnAgregar = new JButton();
    
    public TarjetaProveedores() {
        pnlTarjeta.add(btnCopiarTelefono);
    }
}
