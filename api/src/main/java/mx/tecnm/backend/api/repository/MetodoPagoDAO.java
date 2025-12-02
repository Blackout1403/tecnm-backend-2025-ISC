package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.MetodoPago;

@Repository
public class MetodoPagoDAO {
    
    @Autowired
   private JdbcClient conexion;
   
    public List<MetodoPago> consultarMetodosPago() {
     String sql = "SELECT id, nombre, comision FROM metodos_pago WHERE activo = TRUE";
     return conexion.sql(sql)
        .query((rs, rowNum) -> new mx.tecnm.backend.api.models.MetodoPago(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getDouble("comision")))
        .list();
            
    }

    public MetodoPago agregarMetodoPago(String nombre, double comision) {
        String sql = "INSERT INTO metodos_pago (nombre, comision) VALUES (?,?) RETURNING id, nombre, comision";
        return conexion.sql(sql)
            .param(nombre)
            .param(comision)
            .query((rs, rowNum) -> new MetodoPago(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("comision")))
            .single();
    }

    public MetodoPago actualizarMetodoPago(int id, String nombre, double comision) {
        String sql = "UPDATE metodos_pago SET nombre = (?), comision = (?) WHERE id = ? RETURNING id, nombre, comision";
        return conexion.sql(sql)
            .param(nombre)
            .param(comision)
            .param(id)
            .query((rs, rowNum) -> new MetodoPago(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("comision")))
            .single();
    }


    public MetodoPago obtenerMetodoPagoPorId(int id) {
        String sql = "SELECT id, nombre, comision FROM metodos_pago WHERE id = ? AND activo = TRUE";
        return conexion.sql(sql)
            .param(id)
            .query((rs, rowNum) -> new MetodoPago(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("comision")))
            .single();
    }
}
