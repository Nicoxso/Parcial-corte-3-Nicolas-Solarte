package com.mycompany.nominalibre;

import java.time.LocalDate;
import java.time.YearMonth;
import javax.swing.JOptionPane;

public class Principal {

    private static final LiquidadorNomina liquidador = new LiquidadorNomina();
    private static final NominaDAO dao = new NominaDAO();

    public static void main(String[] args) {
        int opcion = 0;

        do {
            String menu = """
                    SISTEMA DE NÓMINA

                    1. Registrar trabajador
                    2. Liquidar pago mensual
                    3. Salir

                    Seleccione una opción:
                    """;

            try {
                String entrada = JOptionPane.showInputDialog(menu);
                if (entrada == null) break;

                opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1 -> registrarTrabajador();
                    case 2 -> liquidarPago();
                    case 3 -> JOptionPane.showMessageDialog(null, "Programa finalizado.");
                    default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            } catch (Exception e) {
                opcion = 0;
                JOptionPane.showMessageDialog(
                        null,
                        "No fue posible realizar la operación:\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } while (opcion != 3);
    }

    private static void registrarTrabajador() throws Exception {
        String[] tipos = {"Planta", "Contratista", "Por horas"};

        String tipo = (String) JOptionPane.showInputDialog(
                null, "Seleccione el tipo de trabajador:",
                "Registrar trabajador", JOptionPane.QUESTION_MESSAGE,
                null, tipos, tipos[0]);

        if (tipo == null) return;

        String documento = solicitarTexto("Documento (6 a 10 dígitos):");
        if (documento == null) return;

        String nombre = solicitarTexto("Nombre completo:");
        if (nombre == null) return;

        String fechaTexto = solicitarTexto("Fecha de ingreso (AAAA-MM-DD):");
        if (fechaTexto == null) return;

        LocalDate fechaIngreso = LocalDate.parse(fechaTexto);
        Trabajador trabajador;

        switch (tipo) {
            case "Planta" -> {
                double salarioBase = solicitarDouble("Salario base mensual:");
                trabajador = new Planta(documento, nombre, fechaIngreso, salarioBase);
            }
            case "Contratista" -> {
                double valorContrato = solicitarDouble("Valor mensual del contrato:");
                trabajador = new Contratista(documento, nombre, fechaIngreso, valorContrato);
            }
            case "Por horas" -> {
                int horas = solicitarEntero("Horas trabajadas en el mes (0 a 300):");
                double valorHora = solicitarDouble("Valor de cada hora:");
                trabajador = new PorHoras(documento, nombre, fechaIngreso, horas, valorHora);
            }
            default -> {
                return;
            }
        }

        dao.guardarTrabajador(trabajador);
        liquidador.registrarTrabajador(trabajador);

        JOptionPane.showMessageDialog(null, "Trabajador registrado correctamente.");
    }

    private static void liquidarPago() throws Exception {
        String documento = solicitarTexto("Documento del trabajador que desea liquidar:");
        if (documento == null) return;

        String mesTexto = solicitarTexto("Mes de liquidación (AAAA-MM):");
        if (mesTexto == null) return;

        YearMonth mes = YearMonth.parse(mesTexto);
        Pago pago = liquidador.liquidarPago(documento, mes);

        dao.guardarPago(pago);

        JOptionPane.showMessageDialog(
                null,
                "Pago liquidado correctamente.\n"
                + "Trabajador: " + pago.getTrabajador().getNombre() + "\n"
                + "Mes: " + mes + "\n"
                + "Valor: $" + String.format("%,.2f", pago.getValor())
        );
    }

    private static String solicitarTexto(String mensaje) {
        String valor = JOptionPane.showInputDialog(mensaje);
        if (valor == null) return null;
        valor = valor.trim();
        if (valor.isEmpty()) {
            throw new IllegalArgumentException("Este campo no puede quedar vacío.");
        }
        return valor;
    }

    private static double solicitarDouble(String mensaje) {
        String valor = solicitarTexto(mensaje);
        if (valor == null) throw new IllegalArgumentException("Operación cancelada.");
        return Double.parseDouble(valor.replace(",", "."));
    }

    private static int solicitarEntero(String mensaje) {
        String valor = solicitarTexto(mensaje);
        if (valor == null) throw new IllegalArgumentException("Operación cancelada.");
        return Integer.parseInt(valor);
    }
}
