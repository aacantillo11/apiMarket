package com.alejo.market.domain.service;

import com.alejo.market.domain.Product;
import com.alejo.market.domain.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.getAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Product> getProduct(int productId) {
        return productRepository.getProduct(productId)
                .map(product -> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> getProductsByCategory(int categoryId) {

        List<Product> products = productRepository.getByCategory(categoryId).get();
        return products.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(products);

    }

    @Override
    @Transactional
    public ResponseEntity<Product> saveProduct(Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity deleteProduct(int productId) {
        return productRepository.getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
