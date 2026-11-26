/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nominalibre;

/**
 *
 * @author salas
 */
public class EmpleadoDePlanta extends Trabajador{
    private double salario=1400000;
    
    public EmpleadoDePlanta(int documento, String nombre, String fechaDeIngreso) {
        super(documento, nombre, fechaDeIngreso);
        this.salario=salario;
    }
    
     @Override
    public double salario(double salario) {
        double pago = salario;
        return pago;
    }
}
