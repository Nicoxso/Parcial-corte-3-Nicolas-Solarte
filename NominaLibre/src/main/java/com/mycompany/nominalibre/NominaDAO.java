/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nominalibre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NominaDAO {

    public void guardarTrabajador(Trabajador t)
            throws SQLException {

        String sql = "INSERT INTO trabajadores "
                + "(documento, nombre, fecha_ingreso, tipo, "
                + "salario_base, valor_contrato, "
                + "horas_trabajadas, valor_hora) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getDocumento());
            ps.setString(2, t.getNombre());
            ps.setDate(3, java.sql.Date.valueOf(
                    t.getFechaDeIngreso()));

            ps.setString(4, t.getTipo());

            if (t instanceof EmpleadoDePlanta) {

                EmpleadoDePlanta p = (EmpleadoDePlanta) t;


                ps.setNull(6, java.sql.Types.DOUBLE);
                ps.setNull(7, java.sql.Types.INTEGER);
                ps.setNull(8, java.sql.Types.DOUBLE);

            } else if (t instanceof Contratista) {

                Contratista c = (Contratista) t;

                ps.setNull(5, java.sql.Types.DOUBLE);
                ps.setDouble(6, c.getValorContrato());
                ps.setNull(7, java.sql.Types.INTEGER);
                ps.setNull(8, java.sql.Types.DOUBLE);

            } else if (t instanceof PorHoras) {

                PorHoras h = (PorHoras) t;

                ps.setNull(5, java.sql.Types.DOUBLE);
                ps.setNull(6, java.sql.Types.DOUBLE);
                ps.setInt(7, h.getHorasTrabajadas());
                ps.setDouble(8, h.getValorHora());
            }

            ps.executeUpdate();
        }
    }
}
