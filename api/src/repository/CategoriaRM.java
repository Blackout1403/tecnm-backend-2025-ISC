package mx.tecnm.backend.api.repository;

import mx.tecnm.backend.api.models.Categoria;

import mx.tecnm.backend.api.models.Categoria;
public class CategoriaRM implements RowMapper<Categoria> {

    @Override
    public Categoria mapRow(ResultSet rs, int rowNum) throws java.sql {
     return new Categoria(
            rs.getInt("id"),
            rs.getString("nombre")
        );
    }


}
