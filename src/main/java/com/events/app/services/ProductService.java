package com.events.app.services;

import com.events.app.entities.Product;
import com.events.app.dtos.ProductRequestDTO;
import com.events.app.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional
    public Product createProduct(ProductRequestDTO productRequestDTO){
        Product product = new Product(productRequestDTO.getProductName(), productRequestDTO.getPrice());
        return productRepository.save(product);
    }
}
