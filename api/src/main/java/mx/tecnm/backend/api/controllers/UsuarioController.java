package mx.tecnm.backend.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import mx.tecnm.backend.api.models.Usuario;
import mx.tecnm.backend.api.repository.UsuarioDAO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioDAO repo;


    @GetMapping()
    public ResponseEntity<List<Usuario>> obtenerUsuario() {
        List<Usuario> usuarios = repo.consultarUsuario();
        return ResponseEntity.ok(usuarios);
    } 

    @PostMapping()
    public ResponseEntity<Usuario> agregarUsuario(Usuario usuario) {
        Usuario nuevoUsuario = repo.agregarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping()
    public ResponseEntity<Usuario> actualizarUsuario(Usuario usuario) {
        usuario = repo.agregarUsuario(usuario);   
        return ResponseEntity.ok(usuario);
    }
}
