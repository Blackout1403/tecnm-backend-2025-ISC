package mx.tecnm.backend.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Producto;


@RequestMapping("/test")
@RestController
public class Test {

    @GetMapping("/hello")
    public String helloworld(){
        return "Hola API rest";
    }

    @GetMapping("/producto")
    public Producto getProducto(){
        Producto p = new Producto();
        p.nombre = "Coca Cola";
        p.precio = 17.2;
        p.codigoBarras= "7501053123606";
        return p;
    }


    @GetMapping
    public Producto[] getProductos(){
        Producto p1 = new Producto();
        p1.nombre = "Coca Cola";
        p1.precio = 18.5 ;
        p1.codigoBarras = "751927392383";



        return new Producto[]{p1}
    }
}
