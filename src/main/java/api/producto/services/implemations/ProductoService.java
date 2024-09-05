package api.producto.services.implemations;

import api.producto.model.entities.Producto;
import api.producto.repositories.ProductoRepository;
import api.producto.services.interfaces.IProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {
    private final ProductoRepository productoRepository;
    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto update(Long id, Producto producto) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (!productoOptional.isPresent()) {
            throw new EntityNotFoundException("Producto con ID " + id + " no encontrado");
        }
        Producto existingProducto = productoOptional.get();
        if (producto.getName() != null) {
            existingProducto.setName(producto.getName());
        }
        if (producto.getDescription() != null) {
            existingProducto.setDescription(producto.getDescription());
        }
        if (producto.getPrice() != null) {
            existingProducto.setPrice(producto.getPrice());
        }
        if (producto.getStock() != null) {
            existingProducto.setStock(producto.getStock());
        }
        return productoRepository.save(existingProducto);
    }

    @Override
    public void delete(Long id) {
        Producto producto = productoRepository.findById(id).get();
        productoRepository.delete(producto);
    }
}
