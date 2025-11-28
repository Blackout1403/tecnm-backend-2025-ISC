package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.DetalleCarrito;


@Repository
public class DetalleCarritoDAO {

    @Autowired
   private JdbcClient conexion;
    public List<DetalleCarrito> consultarDetalleCarritos() {
     String sql = "SELECT id, cantidad, precio FROM detalles_carrito";
     return conexion.sql(sql)
        .query((rs, rowNum) -> new DetalleCarrito(
            rs.getInt("id"),
            rs.getInt("cantidad"),
            rs.getString("precio")))
        .list();
        }
    }
