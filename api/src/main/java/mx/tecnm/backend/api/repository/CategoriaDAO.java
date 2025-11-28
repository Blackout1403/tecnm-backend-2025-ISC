package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;


import mx.tecnm.backend.api.models.Categoria;


@Repository

public class CategoriaDAO {

    @Autowired
   private JdbcClient conexion;

    public List<Categoria> consultarCategorias() {
     String sql = "SELECT id, nombre FROM categorias";
     return conexion.sql(sql)
        .query((rs, rowNum) -> new Categoria(
            rs.getInt("id"),
            rs.getString("nombre")))
        .list();

    }

    public Categoria crearcategoria(String nuevaCategoria) {
        String sql = "INSERT INTO categorias (nombre) VALUES (?) RETURNING id, nombre";
       return conexion.sql(sql)
            .param(nuevaCategoria)
            .query((rs, rowNum) -> new Categoria(
                rs.getInt("id"),
                rs.getString("nombre")))
        .single();
           

    }
}




