package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.DetallePedido;


@Repository
public class DetallePedidoDAO {

    @Autowired
   private JdbcClient conexion;

    public List<DetallePedido> consultarDetallePedidos() {
     String sql = "SELECT id, cantidad, precio FROM detalles_pedido";
     return conexion.sql(sql)
        .query((rs, rowNum) -> new DetallePedido(
            rs.getInt("id"),
            rs.getString("cantidad"),
            rs.getString("precio")))
        .list();

    }
}
