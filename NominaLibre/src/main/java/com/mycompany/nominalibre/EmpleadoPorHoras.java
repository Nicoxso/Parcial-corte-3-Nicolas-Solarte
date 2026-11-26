/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nominalibre;

/**
 *
 * @author salas
 */
public class EmpleadoPorHoras extends Trabajador{
    private double salario=30000;
    private int horas;
    
    public EmpleadoPorHoras(int documento, String nombre, String fechaDeIngreso) {
        super(documento, nombre, fechaDeIngreso);
        this.horas=horas;
    }
    @Override
    public double salario(double salario) {
        double pago = horas*salario;
        return pago;
    }
}
