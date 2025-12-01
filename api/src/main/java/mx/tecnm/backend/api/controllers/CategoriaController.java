package mx.tecnm.backend.api.controllers;



import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

import mx.tecnm.backend.api.models.Categoria;

import mx.tecnm.backend.api.repository.CategoriaDAO;

@RestController
@RequestMapping("/categorias")

public class CategoriaController {
    @Autowired
    CategoriaDAO repo;

    @GetMapping()
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        List<Categoria> categorias = repo.consultarCategorias();
        return ResponseEntity.ok(categorias);
    } 

    @PostMapping()
    public ResponseEntity<Categoria> crearcategoria(@RequestParam String nuevaCategoria) {
        Categoria categoriacreada = repo.crearcategoria (nuevaCategoria);
        return ResponseEntity.created(null).body(categoriacreada);
    }

    @GetMapping("/buscarid")
    public ResponseEntity<List<Categoria>> busquedaID(@RequestParam int id) {
        List<Categoria> categorias = repo.busquedaID(id);
        return ResponseEntity.ok(categorias);
}

    @PutMapping()
    public ResponseEntity<Categoria> actualizarCategoria(@RequestParam int id, @RequestParam String Nuevonombre) {
        Categoria categoriaActualizada = repo.actualizarCategoria(id, Nuevonombre);
        return ResponseEntity.ok(categoriaActualizada);


}
    @DeleteMapping()
    public ResponseEntity<List<Categoria>> desactivar(@RequestParam int id) {
        List<Categoria> desactivar = repo.desactivar(id);
        return ResponseEntity.ok(desactivar); 

    }
}
