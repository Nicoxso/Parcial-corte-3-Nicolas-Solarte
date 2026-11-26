/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nominalibre;
import java.time.LocalDate;
/**
 *
 * @author salas
 */
public class PorHoras extends Trabajador implements Bonificable {

    private int horasTrabajadas;
    private double valorHora;
    private int años;

    public PorHoras(String documento, String nombre,String fechaIngreso,int horasTrabajadas, double valorHora)
        throws DocumentoInvalidoException,HorasInvalidasException {

        super(documento, nombre, fechaIngreso);
        this.años = años;

        if (horasTrabajadas < 0 || horasTrabajadas > 300) {
            throw new HorasInvalidasException("Las horas deben estar entre 0 y 300.");
        }

        if (valorHora <= 0) {
            throw new HorasInvalidasException("El valor de la hora debe ser mayor que cero.");
        }

        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    }

    @Override
    public double calcularPagoBase() {

        double pago;

        if (horasTrabajadas <= 160) {

            pago = horasTrabajadas * valorHora;

        } else {

            int horasNormales = 160;
            int horasExtras = horasTrabajadas - 160;

            pago = (horasNormales * valorHora)+ (horasExtras * valorHora * 1.25);
        }
        return pago;
    }

    @Override
    public double calcularBonificacion(double pago, LocalDate fechaLiquidacion) {
        this.años=años;
        int porcentaje = Math.min(años, 10);
        return pago * porcentaje / 100.0;
    }

    @Override
    public String getTipo() {
        return "Por horas";
    }
}
