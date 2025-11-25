package mx.tecnm.backend.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.list;

import mx.tecnm.backend.api.models.Categoria;

@Repository
public class CategoriaDAO {

    @Autowired
    private JdbcClient jdbcClient;

    public list <Categoria> obtenerCategorias(){
        String sql = "SELECT id, nombre FROM categorias";
        return jdbcClient.sql(sql)
           .query(new CategoriaRM())
            .List();


    }



}
