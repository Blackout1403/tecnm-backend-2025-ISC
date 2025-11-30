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

import mx.tecnm.backend.api.models.Producto;
import mx.tecnm.backend.api.repository.ProductoDAO;

@RestController
@RequestMapping("/productos")
public class ProductoController {

        @Autowired
        ProductoDAO repo;
      

        
        @GetMapping()
        public ResponseEntity<List<Producto>> obtenerProductos() {
            List<Producto> productos = repo.consultarProductos();
            return ResponseEntity.ok(productos);
        }

        @PostMapping()
        public ResponseEntity<Producto> crearProducto(@RequestParam String nuevoProducto, @RequestParam double precioProducto, @RequestParam String sku, @RequestParam String color, @RequestParam String marca, @RequestParam String descripcion, @RequestParam double peso, @RequestParam double alto, @RequestParam double ancho, @RequestParam double profundidad, @RequestParam int categorias_id ) {
            Producto productocreado = repo.crearProducto (nuevoProducto, precioProducto,sku,color,marca,descripcion,peso,alto,ancho,profundidad,categorias_id );
            return ResponseEntity.created(null).body(productocreado);             
}

      @GetMapping("/busquedaid")
            public ResponseEntity<List<Producto>> busquedaID(@RequestParam int id) {
            List<Producto> productos = repo.busquedaID(id);
            return ResponseEntity.ok(productos);

            }
            
       @PutMapping()
         public ResponseEntity<List<Producto>> actualizarProducto(@RequestParam int id, @RequestParam String nombre, @RequestParam double precioProducto, @RequestParam String sku, @RequestParam String color, @RequestParam String marca, @RequestParam String descripcion, @RequestParam double peso, @RequestParam double alto, @RequestParam double ancho, @RequestParam double profundidad, @RequestParam int categorias_id) {
         List<Producto> Productoactualizado = repo.actualizarProducto(id, nombre, precioProducto, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id);
         return ResponseEntity.ok(Productoactualizado);
         }

         @PutMapping("/desactivar")
         public ResponseEntity<List<Producto>> desactivar(@RequestParam int id, @RequestParam boolean activo) {
             List<Producto> desactivar = repo.desactivar(id, activo);
             return ResponseEntity.ok(desactivar); 

         }
}