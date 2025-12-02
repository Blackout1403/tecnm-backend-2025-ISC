package mx.tecnm.backend.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.DetalleCarrito;
import mx.tecnm.backend.api.repository.DetalleCarritoDAO;


@RestController
@RequestMapping("/detallecarrito")
public class DetalleCarritoController {

    @Autowired
    DetalleCarritoDAO repo;

    @GetMapping
    ResponseEntity<List<DetalleCarrito>> consultarcarrito(@RequestParam int usuario_id)
    {
        List<DetalleCarrito> resultado = repo.consultarcarrito(usuario_id);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping()
    ResponseEntity<DetalleCarrito> agregarAlCarrito(@RequestParam int usuario_id, @RequestParam int producto_id)
    {
        DetalleCarrito busqueda = repo.BuscarProductoCarrito(usuario_id, producto_id);
        DetalleCarrito resultado = null;
        if (busqueda == null)
        {
            resultado = repo.agregarAlCarrito(usuario_id, producto_id, 1);
        }
        else
        {
            resultado = repo.actualizarCantidadEnCarrito(busqueda.id(), busqueda.cantidad() + 1);
        }

        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping()
    ResponseEntity<Void> borrarDelCarrito(@RequestParam int usuario_id, @RequestParam int producto_id)
    {
        boolean exito = repo.borrarDelCarrito(usuario_id, producto_id);
        if (exito) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/realizarpedido") // Metodo para realizar pedido
    ResponseEntity<Void> realizarpedido(@RequestParam int usuario_id, @RequestParam int metodo_pago_id)
    {
        boolean exito = repo.realizarpedido(usuario_id, metodo_pago_id);
        if (exito) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(500).build();
        }
    }
    
}
