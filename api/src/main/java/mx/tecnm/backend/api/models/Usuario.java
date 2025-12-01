package mx.tecnm.backend.api.models;

import java.sql.Date;

public record Usuario(int id, String nombre, String email, String telefono, String sexo, Date Fecha_nacimineto, String contrasena, Date fecha_registro) {
    }


