package mx.tecnm.backend.api.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Usuario;

@Repository
public class UsuarioDAO {

    @Autowired
   private JdbcClient conexion;

    public List<Usuario> consultarUsuario() {
     String sql = "SELECT id, nombre, email, telefono, sexo, Fecha_nacimiento, contrasena, fecha_registro FROM usuarios WHERE activo = TRUE";
     return conexion.sql(sql)
        .query((rs, rowNum) -> new mx.tecnm.backend.api.models.Usuario(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("email"),
            rs.getString("telefono"),
            rs.getString("sexo"),
            rs.getDate("Fecha_nacimiento"),
            rs.getString("contrasena"),
            rs.getDate("fecha_registro")))

        .list();
            
    }

    public Usuario agregarUsuario(int id, String nombre, String email, String telefono, String sexo, java.sql.Date Fecha_nacimiento, String contrasena, java.sql.Date fecha_registro) {
        String sql = "INSERT INTO usuarios (nombre, email, telefono, sexo, Fecha_nacimiento, contrasena, fecha_registro) VALUES (?,?,?,?,?,?,?) RETURNING id, nombre, email, telefono, sexo, Fecha_nacimiento, contrasena, fecha_registro";
        return conexion.sql(sql)
            .param(nombre)
            .param(email)
            .param(telefono)
            .param(sexo)
            .param(Fecha_nacimiento)
            .param(contrasena)
            .param(fecha_registro)
            .query((rs, rowNum) -> new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getString("sexo"),
                rs.getDate("Fecha_nacimineto"),
                rs.getString("contrasena"),
                rs.getDate("fecha_registro")))
            .single();
    }

    public Usuario actualizarUsuario(int id, String nombre, String email, String telefono, String sexo, java.sql.Date Fecha_nacimiento, String contrasena, java.sql.Date fecha_registro) {
        String sql = "UPDATE usuarios SET nombre = (?), email = (?), telefono = (?), sexo = (?), fecha_nacimiento = (?), contrasena = (?), fecha_registro = (?) WHERE id = ? RETURNING id, nombre, email, telefono, sexo, Fecha_nacimiento, contrasena, fecha_registro";
        return conexion.sql(sql)
            .param(id)
            .param(nombre)
            .param(email)
            .param(telefono)
            .param(sexo)
            .param(Fecha_nacimiento)
            .param(contrasena)
            .param(fecha_registro)
            .query((rs, rowNum) -> new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getString("sexo"),
                rs.getDate("Fecha_nacimiento"),
                rs.getString("contrasena"),
                rs.getDate("fecha_registro")))
            .single();
    }

    public List<Usuario> busquedaID(int id) {
     String sql = "SELECT * FROM usuarios WHERE id = (?) AND activo = TRUE";
     return conexion.sql(sql)
        .param(id)
        .query((rs, rowNum) -> new mx.tecnm.backend.api.models.Usuario(
          rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getString("sexo"),
                rs.getDate("Fecha_nacimiento"),
                rs.getString("contrasena"),
                rs.getDate("fecha_registro")))
        .list();
    }

    public List<Usuario> eliminarUsuario(int id) {
        String sql = "UPDATE usuarios SET activo = FALSE WHERE id = (?) RETURNING id, nombre, email, telefono, sexo, Fecha_nacimiento, contrasena, fecha_registro";
       return conexion.sql(sql)
            .param(id)
            .query((rs, rowNum) -> new mx.tecnm.backend.api.models.Usuario(
              rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getString("sexo"),
                rs.getDate("Fecha_nacimiento"),
                rs.getString("contrasena"),
                rs.getDate("fecha_registro")))
            .list();
            }
}
