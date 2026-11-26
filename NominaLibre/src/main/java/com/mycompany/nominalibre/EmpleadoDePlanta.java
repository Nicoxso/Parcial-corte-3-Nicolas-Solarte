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
public class EmpleadoDePlanta extends Trabajador implements Bonificable{
     private double salarioBase;

    private static final double salarioReferencia = 1400000;
    private static final double auxilio = 200000;
    private int años;

    public EmpleadoDePlanta(String documento, String nombre,String fechaIngreso, double salarioBase)throws DocumentoInvalidoException,SalarioInvalidoException {

        super(documento, nombre, fechaIngreso);

        if (salarioBase < salarioReferencia) {
            throw new SalarioInvalidoException("El salario de planta no puede ser menor a $1.400.000.");
        }
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularPagoBase() {

        double pago = salarioBase;

        if (salarioBase <= salarioReferencia * 2) {
            pago += auxilio;
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
        return "Planta";
    }
}
