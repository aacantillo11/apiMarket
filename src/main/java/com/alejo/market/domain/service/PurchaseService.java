package com.alejo.market.domain.service;

import com.alejo.market.domain.Purchase;
import com.alejo.market.domain.repository.IPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseService implements IPurchaseService{

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        return ResponseEntity.ok(purchaseRepository.getAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Purchase>> getPurchasesByClient(String clientId) {
        List<Purchase> purchases = purchaseRepository.getByClient(clientId).get();
        return purchases.isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(purchases);
    }

    @Override
    @Transactional
    public ResponseEntity<Purchase> savePurchase(Purchase purchase) {
        return new ResponseEntity<>(purchaseRepository.save(purchase), HttpStatus.CREATED);
    }
}
