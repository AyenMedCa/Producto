    package api.producto.controllers;
    
    import api.producto.model.entities.Producto;
    import api.producto.services.implemations.ProductoService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;
    
    @RestController
    @RequiredArgsConstructor
    @RequestMapping("producto")
    public class ProductoController {
        private final ProductoService productoService;
    
        @PostMapping
        @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
        public ResponseEntity<Producto> addProducto(@RequestBody Producto producto) {
            return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
        }
    
        @GetMapping("/{id}")
        @PreAuthorize("hasAnyRole('SELLER', 'ADMIN','USER')")
        public ResponseEntity<Optional<Producto>> getById(@PathVariable Long id) {
            return new ResponseEntity<>(productoService.getProductoById(id), HttpStatus.OK);
        }
        @DeleteMapping("/{id}")
        @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
        public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
            productoService.delete(id);
            return new ResponseEntity<>("El producto se elimino correctamente",HttpStatus.OK);
        }
    
        @PutMapping("/{id}")
        @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
        public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto, @PathVariable Long id) {
            return new ResponseEntity<>(productoService.update(id, producto), HttpStatus.OK);
        }
    
        @GetMapping()
        @PreAuthorize("hasAnyRole('SELLER', 'ADMIN', 'USER')")
        public ResponseEntity<List<Producto>> index() {
            List<Producto> productos = productoService.getAllProductos();
            if (productos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(productos, HttpStatus.OK);
            }
        }

        @GetMapping ("{id}/exist")
        @PreAuthorize("hasAnyRole('SELLER', 'ADMIN', 'USER')")
        public ResponseEntity<Boolean> exist(@PathVariable Long id) {
            return new ResponseEntity<>(productoService.exist(id), HttpStatus.OK);
        }
    }
