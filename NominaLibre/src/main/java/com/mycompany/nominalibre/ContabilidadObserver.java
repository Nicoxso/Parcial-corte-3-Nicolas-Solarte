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
public class ContabilidadObserver implements ObservadorPago {

    private double totalLiquidado = 0;

    @Override
    public void actualizar(Pago pago) {
        totalLiquidado += pago.getValor();
    }

    public void mostrarTotal() {

        JOptionPane.showMessageDialog(null,"TOTAL LIQUIDADO EN LA SESION: $"+ String.format("%,.2f", totalLiquidado));
    }
}