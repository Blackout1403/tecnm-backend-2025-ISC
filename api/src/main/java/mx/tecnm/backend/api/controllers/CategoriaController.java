package mx.tecnm.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")

public class CategoriaController {

   @Autowired
   categoriaDAO repo;  //Transforma y hace de lenguaje para la categoria en este caso
    
    @GetMapping()
    public ResponseEntity<List<Categoria>> obtenerCategorias(){
        List<Categoria> resultado = repo.obtenerCategorias();

    }

}
