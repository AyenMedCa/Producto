package api.producto.controllers;

import api.producto.model.entities.Producto;
import api.producto.services.implemations.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/producto")
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> addProducto(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Producto>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(productoService.getProductoById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        productoService.delete(id);
        return new ResponseEntity<>("El producto se elimino correctamente",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto, @PathVariable Long id) {
        return new ResponseEntity<>(productoService.update(id, producto), HttpStatus.OK);
    }
}
