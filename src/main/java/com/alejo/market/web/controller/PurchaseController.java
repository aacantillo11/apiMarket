package com.alejo.market.web.controller;

import com.alejo.market.domain.Purchase;
import com.alejo.market.domain.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Purchase>> getPurchasesByClient(@PathVariable("id") String clientId) {
        return purchaseService.getPurchasesByClient(clientId);
    }

    @PostMapping
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase) {
        return purchaseService.savePurchase(purchase);
    }

}
