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
public abstract class Trabajador {

    protected String documento;
    protected String nombre;
    protected String fechaDeIngreso;

    public Trabajador(String documento, String nombre,String fechaDeIngreso)throws DocumentoInvalidoException {

        if (documento == null ||!documento.matches("\\d{6,10}")) {
            throw new DocumentoInvalidoException("El documento debe tener entre 6 y 10 digitos.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {throw new IllegalArgumentException( "El nombre no puede estar vacio.");
        }

        this.documento = documento;
        this.nombre = nombre;
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public abstract double calcularPagoBase();
    public double calcularPago(LocalDate fechaLiquidacion) {
        double pago = calcularPagoBase();
        if (this instanceof Bonificable) {
            Bonificable b = (Bonificable) this;
            pago += b.calcularBonificacion(pago, fechaLiquidacion);
        }
        return pago;
    }
    public String getDocumento() {
        return documento;
    }
    public String getNombre() {
        return nombre;
    }
    public String getFechaDeIngreso() {
        return fechaDeIngreso;
    }
    public abstract String getTipo();
}