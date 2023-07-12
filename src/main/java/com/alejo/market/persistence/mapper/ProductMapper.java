package com.alejo.market.persistence.mapper;

import com.alejo.market.domain.Product;
import com.alejo.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(source = "id",target = "productId")
    @Mapping(source = "nombre",target = "name")
    @Mapping(source = "idCategoria",target = "categoryId")
    @Mapping(source = "precioVenta",target = "price")
    @Mapping(source = "cantidadStock",target = "stock")
    @Mapping(source = "categoria",target = "category")
    @Mapping(source = "estado",target = "active")
    Product productoToProduct(Producto producto);
    List<Product> productosToProducts(List<Producto> productos);

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras",ignore = true)
    @Mapping(target = "compras",ignore = true)
    Producto productToProducto(Product product);
}
