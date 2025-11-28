package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Pedido;

@Repository
public class PedidoDAO {

    @Autowired
   private JdbcClient conexion;

    public List<Pedido> consultarPedidos() {
     String sql = "SELECT id, numero, importe_productos FROM pedidos";
     return conexion.sql(sql)
        .query((rs, rowNum) -> new Pedido(
            rs.getInt("id"),
            rs.getString("numero"),
            rs.getString("importe_productos")))
        .list();

    }
}
