package com.proiect_final.proiect_final.controllers;

import com.proiect_final.proiect_final.dtos.ProductRequestDTO;
import com.proiect_final.proiect_final.dtos.UserRequestDTO;
import com.proiect_final.proiect_final.entities.Product;
import com.proiect_final.proiect_final.entities.User;
import com.proiect_final.proiect_final.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
