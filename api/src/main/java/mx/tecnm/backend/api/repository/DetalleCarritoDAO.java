package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.DetalleCarrito;
import mx.tecnm.backend.api.models.DetallePedido;
import mx.tecnm.backend.api.models.Pedido;


@Repository
public class DetalleCarritoDAO {
    @Autowired
    private JdbcClient conexion;
    
    @Autowired
    private ProductoDAO productorepo;

    @Autowired
    private PedidoDAO pedidorepo;


    public List<DetalleCarrito> consultarcarrito(int usuario_id) {
        String sql = "SELECT id, cantidad, precio, productos_id FROM detalles_carrito WHERE usuarios_id = ?";

        return conexion.sql(sql)
                .param(usuario_id)
                .query((rs, rowNum) -> new DetalleCarrito(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getInt("productos_id"),
                        usuario_id))
                .list();
    }

    public DetalleCarrito BuscarProductoCarrito(int usuario_id, int producto_id) {
        String sql = "SELECT id, cantidad, precio FROM detalles_carrito WHERE usuarios_id = ? AND productos_id = ?";

        return conexion.sql(sql)
                .param(usuario_id)
                .param(producto_id)
                .query((rs, rowNum) -> new DetalleCarrito(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        producto_id,
                        usuario_id))
                .optional()
                .orElse(null);
    }

    public DetalleCarrito agregarAlCarrito(int usuario_id, int producto_id, int cantidad) {
        String sql = "INSERT INTO detalles_carrito (cantidad, precio, productos_id, usuarios_id) VALUES (?, ?, ?, ?) RETURNING id";

        double precioUnitario = productorepo.busquedaID(producto_id).get(0).precio();
        double precioTotal = precioUnitario * cantidad;

        return conexion.sql(sql)
                .param(cantidad)
                .param(precioTotal)
                .param(producto_id)
                .param(usuario_id)
                .query((rs, rowNum) -> new DetalleCarrito(
                        rs.getInt("id"),
                        cantidad,
                        precioTotal,
                        producto_id,
                        usuario_id))
                .single();
    }

    public DetalleCarrito actualizarCantidadEnCarrito(int detalle_carrito_id, int nueva_cantidad) {
        String sql = "UPDATE detalles_carrito SET cantidad = ?, precio = ? WHERE id = ? RETURNING usuarios_id, productos_id";

        
        DetalleCarrito detalleActual = conexion.sql("SELECT productos_id, usuarios_id FROM detalles_carrito WHERE id = ?")
                .param(detalle_carrito_id)
                .query((rs, rowNum) -> new DetalleCarrito(
                        detalle_carrito_id,
                        0, 
                        0.0, 
                        rs.getInt("productos_id"),
                        rs.getInt("usuarios_id")))
                .single();

        double precioUnitario = productorepo.busquedaID(detalleActual.producto_id()).get(0).precio();
        double nuevoPrecioTotal = precioUnitario * nueva_cantidad;

        return conexion.sql(sql)
                .param(nueva_cantidad)
                .param(nuevoPrecioTotal)
                .param(detalle_carrito_id)
                .query((rs, rowNum) -> new DetalleCarrito(
                        detalle_carrito_id,
                        nueva_cantidad,
                        nuevoPrecioTotal,
                        detalleActual.producto_id(),
                        detalleActual.usuario_id()))
                .single();
    }

    public boolean borrarDelCarrito(int usuario_id, int producto_id) {
        DetalleCarrito detalle = BuscarProductoCarrito(usuario_id, producto_id);
        if (detalle == null) {
            return false; 
        }

        int nuevaCantidad = detalle.cantidad();
        if (nuevaCantidad > 0) {
            
            actualizarCantidadEnCarrito(detalle.id(), nuevaCantidad);
        } else {
           
            String sql = "DELETE FROM detalles_carrito WHERE id = ?";
            conexion.sql(sql)
                    .param(detalle.id())
                    .update();
        }
        return true;
    }

    public boolean realizarpedido(int usuario_id, int metodo_pago_id) {
 List<DetalleCarrito> lista = consultarcarrito(usuario_id);

        if (lista.isEmpty())
        {
            return false; 
        }

        Pedido pedidoNuevo = pedidorepo.crearPedido(
            lista.stream().mapToDouble(DetalleCarrito::precio).sum(),
            100.0,
            metodo_pago_id,
            usuario_id
        );

        for (DetalleCarrito detalle : lista)
        {
             String sqldetalle = "INSERT INTO detalles_pedido (cantidad, precio, productos_id, pedidos_id) VALUES (?, ?, ?, ?) RETURNING id, cantidad, precio, productos_id, pedidos_id";

        conexion.sql(sqldetalle)
                .params(detalle.cantidad(), detalle.precio(), detalle.producto_id(), pedidoNuevo.int1())
                .query((rs, rowNum) -> new DetallePedido(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getInt("productos_id"),
                        rs.getInt("pedidos_id")))
                .single();
            
        }

       

        String sql = "DELETE FROM detalles_carrito WHERE usuarios_id = ?";
        conexion.sql(sql)
                .param(usuario_id)
                .update();
        return true;
    }

  
    }
