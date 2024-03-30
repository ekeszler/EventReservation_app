package com.events.app.controllers;

import com.events.app.entities.Product;
import com.events.app.dtos.ProductRequestDTO;
import com.events.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<Product>> showAllProducts(){
        List<Product> products = productService.showAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{productId}/update-price")
    public ResponseEntity<String> updateProductPrice(@PathVariable Long productId, @RequestParam double newPrice){
        try{
            productService.updateProductPrice(productId, newPrice);
            return ResponseEntity.ok("The price of product with id " + productId + " was updated to " + newPrice + "RON");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. The price was not updated");
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok("The product with id " + productId + " was deleted");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong and the product was not deleted");
        }
    }
}
