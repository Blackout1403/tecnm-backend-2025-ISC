package mx.tecnm.backend.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import mx.tecnm.backend.api.models.MetodoPago;
import mx.tecnm.backend.api.repository.MetodoPagoDAO;

@RestController
@RequestMapping("/metodospago")
public class MetodoPagoController {

    @Autowired
    MetodoPagoDAO repo;

    @GetMapping()
    public ResponseEntity<List<MetodoPago>> obtenerMetodosPago() {
        List<MetodoPago> metodospago = repo.consultarMetodosPago();
       return ResponseEntity.ok(metodospago);
    }

    @PostMapping()
    public ResponseEntity<MetodoPago> agregarMetodoPago(@RequestParam String nombre, @RequestParam double comision) {
        MetodoPago nuevoMetodoPago = repo.agregarMetodoPago(nombre, comision);
        return ResponseEntity.ok(nuevoMetodoPago);
    }

    @PutMapping()
    public ResponseEntity<MetodoPago> actualizarMetodoPago(@RequestParam int id, @RequestParam String nombre, @RequestParam double comision) {
        MetodoPago metodoPagoActualizado = repo.actualizarMetodoPago(id, nombre, comision);
        return ResponseEntity.ok(metodoPagoActualizado);
    }

    @GetMapping("/busquedaid")
    public ResponseEntity<MetodoPago> busquedaID(@RequestParam int id) {    
        MetodoPago metodoPago = repo.obtenerMetodoPagoPorId(id);
        return ResponseEntity.ok(metodoPago);
    }
}
