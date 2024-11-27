package api.producto.services.interfaces;

import api.producto.model.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    Producto save(Producto producto);
    Optional<Producto> getProductoById(Long id);
    Producto update(Long id, Producto producto);
    void delete(Long id);
    List<Producto> getAllProductos();
    Boolean exist(Long id);
}
