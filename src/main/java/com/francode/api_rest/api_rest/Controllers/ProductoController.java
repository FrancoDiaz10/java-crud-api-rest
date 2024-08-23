package com.francode.api_rest.api_rest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francode.api_rest.api_rest.Entities.Producto;
import com.francode.api_rest.api_rest.Repositories.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id){
        return productoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("No se encontró el producto"));
    } 

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto recoveredProducto = productoRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("No se encontró el producto"));

        recoveredProducto.setNombre(producto.getNombre());
        recoveredProducto.setPrecio(producto.getPrecio());

        return productoRepository.save(recoveredProducto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Producto recoveredProducto = productoRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("No se encontró el producto"));
        
        productoRepository.delete(recoveredProducto);
        return "El producto fue eliminado con éxito!";
    }

}
