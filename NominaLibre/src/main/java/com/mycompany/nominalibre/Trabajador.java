/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nominalibre;

/**
 *
 * @author salas
 */
public abstract class Trabajador {
    private int documento;
    private String nombre;
    private String fechaDeIngreso;
   
    public Trabajador(int documento,String nombre,String fechaDeIngreso) {
       this.documento=documento;
       this.nombre = nombre;
       this.fechaDeIngreso=fechaDeIngreso;
    }
    

    public double salario(double salario) {
        return 0;
    }
}
