/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nominalibre;
import javax.swing.JOptionPane;

/**
 *
 * @author salas
 */

public class AlertaObserver implements ObservadorPago {

    @Override
    public void actualizar(Pago pago) {

        if (pago.getValor() > 8000000) {

            JOptionPane.showMessageDialog(null,"ALERTA: El pago supera los $8.000.000\n"+ "Trabajador: "+ pago.getTrabajador().getNombre()+ "\nValor: $"+ String.format("%,.2f", pago.getValor()),"Alerta de nomina",JOptionPane.WARNING_MESSAGE);
        }
    }
}
