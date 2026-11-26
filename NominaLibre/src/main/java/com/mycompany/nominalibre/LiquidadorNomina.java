/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nominalibre;

/**
 *
 * @author salas
 */

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class LiquidadorNomina {

    private List<Trabajador> trabajadores;
    private List<Pago> pagos;
    private List<ObservadorPago> observadores;

    public LiquidadorNomina() {

        trabajadores = new ArrayList<>();
        pagos = new ArrayList<>();
        observadores = new ArrayList<>();
    }

    // Registrar observadores

    public void agregarObservador(ObservadorPago observador) {
        observadores.add(observador);
    }

    // Notificar observadores

    private void notificar(Pago pago) {

        for (ObservadorPago observador : observadores) {
            observador.actualizar(pago);
        }
    }

    // Registrar trabajadores

    public void registrarTrabajador(Trabajador trabajador)
            throws DocumentoInvalidoException {

        for (Trabajador t : trabajadores) {

            if (t.getDocumento().equals(
                    trabajador.getDocumento())) {

                throw new DocumentoInvalidoException(
                    "El documento ya se encuentra registrado."
                );
            }
        }

        trabajadores.add(trabajador);
    }

    // Buscar trabajador

    public Trabajador buscarTrabajador(String documento) {

        for (Trabajador t : trabajadores) {

            if (t.getDocumento().equals(documento)) {
                return t;
            }
        }

        return null;
    }

    // Liquidar pago

    public Pago liquidarPago(String documento, YearMonth mes) {

        Trabajador trabajador = buscarTrabajador(documento);

        if (trabajador == null) {
            throw new IllegalArgumentException(
                "No existe un trabajador con ese documento."
            );
        }

        for (Pago p : pagos) {

            if (p.getTrabajador().getDocumento().equals(documento)
                    && p.getMes().equals(mes)) {

                throw new IllegalArgumentException(
                    "El trabajador ya tiene un pago liquidado en ese mes."
                );
            }
        }

        double valor = trabajador.calcularPago(
            mes.atEndOfMonth()
        );

        Pago pago = new Pago(trabajador, mes, valor);

        pagos.add(pago);

        // Notificar a los observadores
        notificar(pago);

        return pago;
    }

    // R4: Total de nómina por tipo de trabajador

    public void consultarTotalNomina(YearMonth mes) {

        double totalPlanta = 0;
        double totalContratista = 0;
        double totalPorHoras = 0;

        for (Pago p : pagos) {

            if (p.getMes().equals(mes)) {

                String tipo = p.getTrabajador().getTipo();

                switch (tipo) {

                    case "Planta":
                        totalPlanta += p.getValor();
                        break;

                    case "Contratista":
                        totalContratista += p.getValor();
                        break;

                    case "Por horas":
                        totalPorHoras += p.getValor();
                        break;
                }
            }
        }

        double totalGeneral = totalPlanta
                + totalContratista
                + totalPorHoras;

        System.out.println("\n===== TOTAL DE NOMINA =====");
        System.out.println("Mes: " + mes);
        System.out.printf("Planta: $%,.2f%n", totalPlanta);
        System.out.printf("Contratistas: $%,.2f%n",
                totalContratista);
        System.out.printf("Por horas: $%,.2f%n",
                totalPorHoras);
        System.out.printf("TOTAL GENERAL: $%,.2f%n",
                totalGeneral);
    }

    // R9: Trabajadores sin pago liquidado en un mes

    public List<Trabajador> consultarSinLiquidar(YearMonth mes) {

        List<Trabajador> sinLiquidar = new ArrayList<>();

        for (Trabajador t : trabajadores) {

            boolean encontrado = false;

            for (Pago p : pagos) {

                if (p.getTrabajador().getDocumento().equals(
                        t.getDocumento())
                        && p.getMes().equals(mes)) {

                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                sinLiquidar.add(t);
            }
        }

        return sinLiquidar;
    }

    // Mostrar trabajadores sin liquidar

    public void mostrarSinLiquidar(YearMonth mes) {

        List<Trabajador> lista = consultarSinLiquidar(mes);

        System.out.println("\n===== SIN LIQUIDAR =====");
        System.out.println("Mes: " + mes);

        if (lista.isEmpty()) {

            System.out.println(
                "Todos los trabajadores tienen pago liquidado."
            );

        } else {

            for (Trabajador t : lista) {

                System.out.println(
                    "Documento: " + t.getDocumento()
                    + " | Nombre: " + t.getNombre()
                    + " | Tipo: " + t.getTipo()
                );
            }
        }
    }

    // Mostrar trabajadores registrados

    public void mostrarTrabajadores() {

        System.out.println("\n===== TRABAJADORES =====");

        for (Trabajador t : trabajadores) {

            System.out.println(
                "Documento: " + t.getDocumento()
                + " | Nombre: " + t.getNombre()
                + " | Tipo: " + t.getTipo()
            );
        }
    }
}
