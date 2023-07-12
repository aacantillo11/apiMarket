package com.alejo.market.persistence.mapper;

import com.alejo.market.domain.Purchase;
import com.alejo.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {


    @Mapping(source = "idCliente", target = "clientId")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "medioPago", target = "paymentMethod")
    @Mapping(source = "comentario", target = "comment")
    @Mapping(source = "estado", target = "state")
    @Mapping(source = "productos", target = "items")
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);
}
