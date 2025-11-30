package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;

import mx.tecnm.backend.api.models.Producto;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoDAO {


     @Autowired
   private JdbcClient conexion;

    public List<Producto> consultarProductos() {
     String sql = "SELECT * FROM productos";
     return conexion.sql(sql)
        .query((rs, rowNum) -> new mx.tecnm.backend.api.models.Producto(
          rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
            rs.getString("sku"),
            rs.getString("color"),
            rs.getString("marca"),
            rs.getString("descripcion"),
            rs.getDouble("peso"),
            rs.getDouble("alto"),
            rs.getDouble("ancho"),
            rs.getDouble("profundidad"),
            rs.getInt("categorias_id")))
        .list();
            
    }

    public Producto crearProducto(String nuevoProducto, double precioProducto, String sku, String color, String marca, String descripcion, double peso, double alto, double ancho, double profundidad, int categorias_id) {
        String sql = "INSERT INTO productos (nombre, precio,sku,color,marca,descripcion,peso,alto,ancho,profundidad,categorias_id) VALUES (?, ?,?,?,?,?,?,?,?,?,?) RETURNING id, nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id";
       return conexion.sql(sql)
            .param(nuevoProducto)
            .param(precioProducto)
            .param(sku)
            .param(color)
            .param(marca)
            .param(descripcion)
            .param(peso)
            .param(alto)
            .param(ancho)
            .param(profundidad)
            .param(categorias_id)
            .query((rs, rowNum) -> new Producto(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
            rs.getString("sku"),
            rs.getString("color"),
            rs.getString("marca"),
            rs.getString("descripcion"),
            rs.getDouble("peso"),
            rs.getDouble("alto"),
            rs.getDouble("ancho"),
            rs.getDouble("profundidad"),
            rs.getInt("categorias_id")))
        .single();
           

     
    }

    public List<Producto> busquedaID(int id) {
     String sql = "SELECT * FROM productos WHERE id = (?)";
     return conexion.sql(sql)
        .param(id)
        .query((rs, rowNum) -> new mx.tecnm.backend.api.models.Producto(
          rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
            rs.getString("sku"),
            rs.getString("color"),
            rs.getString("marca"),
            rs.getString("descripcion"),
            rs.getDouble("peso"),
            rs.getDouble("alto"),
            rs.getDouble("ancho"),
            rs.getDouble("profundidad"),
            rs.getInt("categorias_id")))
        .list();

}

    public List<Producto> actualizarProducto(int id, String nombre, double precioProducto, String sku, String color, String marca, String descripcion, double peso, double alto, double ancho, double profundidad, int categorias_id) {
        String sql = "UPDATE productos SET nombre = (?), precio = (?), sku = (?), color = (?), marca = (?), descripcion = (?), peso = (?), alto = (?), ancho = (?), profundidad = (?), categorias_id = (?) WHERE id = (?) RETURNING id, nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id";
       return conexion.sql(sql)
            .param(nombre)
            .param(precioProducto)
            .param(sku)
            .param(color)
            .param(marca)
            .param(descripcion)
            .param(peso)
            .param(alto)
            .param(ancho)
            .param(profundidad)
            .param(categorias_id)
            .param(id)
            .<Producto>query((rs, rowNum) -> new Producto(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
            rs.getString("sku"),
            rs.getString("color"),
            rs.getString("marca"),
            rs.getString("descripcion"),
            rs.getDouble("peso"),
            rs.getDouble("alto"),
            rs.getDouble("ancho"),
            rs.getDouble("profundidad"),
            rs.getInt("categorias_id")))
        .list();
           

    }
    public List<Producto> desactivar(int id, boolean activo) {
        String sql = "UPDATE productos SET activo = (?) WHERE id = (?) returning id, nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id, activo";
       return conexion.sql(sql)
            .param(activo)
            .param(id)
            .<Producto>query((rs, rowNum) -> new Producto(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
            rs.getString("sku"),
            rs.getString("color"),
            rs.getString("marca"),
            rs.getString("descripcion"),
            rs.getDouble("peso"),
            rs.getDouble("alto"),
            rs.getDouble("ancho"),
            rs.getDouble("profundidad"),
            rs.getInt("categorias_id")))
        .list();
           

    }

}