package com.alejo.market.persistence;

import com.alejo.market.domain.Product;
import com.alejo.market.domain.repository.IProductRepository;
import com.alejo.market.persistence.crud.IProductoCrudRepository;
import com.alejo.market.persistence.entity.Producto;
import com.alejo.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements IProductRepository {

    @Autowired
    private IProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.productosToProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository
                                    .findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.productosToProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        return productoCrudRepository
                .findByCantidadStockLessThanAndEstado(quantity,true)
                .map(productos -> mapper.productosToProducts(productos));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto ->
                mapper.productoToProduct(producto));

    }


    @Override
    public Product save(Product product){
        Producto producto = mapper.productToProducto(product);
        return mapper.productoToProduct(productoCrudRepository.save(producto));
    }

    @Override
     public void delete(int productId){
        productoCrudRepository.deleteById(productId);
     }


}
