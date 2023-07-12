package com.alejo.market.domain.service;

import com.alejo.market.domain.Product;
import org.springframework.http.ResponseEntity;
import java.util.List;


public interface IProductService {

    ResponseEntity<List<Product>>  getAllProducts();
    ResponseEntity<Product> getProduct(int productId);
    ResponseEntity<List<Product>> getProductsByCategory(int categoryId);
    ResponseEntity<Product> saveProduct(Product product);
    ResponseEntity deleteProduct(int productId);
}
