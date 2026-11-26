/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nominalibre;
import java.time.YearMonth;

/**
 *
 * @author salas
 */
public class Pago {

    private Trabajador trabajador;
    private YearMonth mes;
    private double valor;

    public Pago(Trabajador trabajador,YearMonth mes, double valor) {
        this.trabajador = trabajador;
        this.mes = mes;
        this.valor = valor;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public YearMonth getMes() {
        return mes;
    }

    public double getValor() {
        return valor;
    }
}
