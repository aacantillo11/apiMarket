package com.alejo.market.persistence.crud;

import com.alejo.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Implementando Query Methods
    //Para obtener los productos por categoria realizamos lo siguiente
    List<Producto> findByIdCategoria(int idCategoria);

    //Para obtener los productos por categoria y ordenados de manera alfabetica realizamos lo siguiente
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Para consultar los productos escasos que estan por debajo de la cantidad indicada y que se estan vendiendo
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidad, boolean estado);
}
