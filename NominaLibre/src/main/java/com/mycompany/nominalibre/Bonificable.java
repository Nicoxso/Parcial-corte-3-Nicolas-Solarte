/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.nominalibre;
import java.time.LocalDate;

/**
 *
 * @author salas
 */
public interface Bonificable {

    double calcularBonificacion(double pago, LocalDate fechaLiquidacion);
}
