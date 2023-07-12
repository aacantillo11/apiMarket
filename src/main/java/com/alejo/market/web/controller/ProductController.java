package com.alejo.market.web.controller;

import com.alejo.market.domain.Product;
import com.alejo.market.domain.service.IProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

   @Autowired
    private IProductService productService;

   @GetMapping
   @ApiOperation("Get all supermarket products")
   @ApiResponse(code = 200,message = "OK")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

   @GetMapping("/{id}")
   @ApiOperation("Search a product with an ID")
   @ApiResponses({
           @ApiResponse(code = 200, message = "OK"),
           @ApiResponse(code = 404, message = "Product not found"),
   })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7")
                                                 @PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("id") int categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int productId) {

       return productService.deleteProduct(productId);

    }
}
