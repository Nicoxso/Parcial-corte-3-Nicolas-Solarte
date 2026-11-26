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
public class Contratista extends Trabajador {
    private double valorContrato;
    private static final double salarioReferencia = 1400000;
    public Contratista(String documento, String nombre,String fechaIngreso,double valorContrato)
        throws DocumentoInvalidoException,SalarioInvalidoException {
        super(documento, nombre, fechaIngreso);

        if (valorContrato < salarioReferencia) {
            throw new SalarioInvalidoException("El contrato no puede ser menor a $1.400.000.");
        }

        this.valorContrato = valorContrato;
    }

    @Override
    public double calcularPagoBase() {
        double retencion = valorContrato * 0.11;
        return valorContrato - retencion;
    }

    @Override
    public String getTipo() {
        return "Contratista";
    }
}
