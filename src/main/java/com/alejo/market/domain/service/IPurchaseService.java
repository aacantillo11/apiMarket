package com.alejo.market.domain.service;

import com.alejo.market.domain.Purchase;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPurchaseService {

    ResponseEntity<List<Purchase>> getAllPurchases();
    ResponseEntity<List<Purchase>> getPurchasesByClient(String clientId);
    ResponseEntity<Purchase> savePurchase(Purchase purchase);

}
