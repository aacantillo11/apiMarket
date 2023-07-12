package com.alejo.market.persistence;

import com.alejo.market.domain.Purchase;
import com.alejo.market.domain.repository.IPurchaseRepository;
import com.alejo.market.persistence.crud.ICompraCrudRepository;
import com.alejo.market.persistence.entity.Compra;
import com.alejo.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements IPurchaseRepository {

    @Autowired
    private ICompraCrudRepository repository;
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) repository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return repository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {

        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(repository.save(compra));
    }
}
