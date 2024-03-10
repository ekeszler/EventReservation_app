package com.proiect_final.proiect_final.services;

import com.proiect_final.proiect_final.dtos.ProductRequestDTO;
import com.proiect_final.proiect_final.entities.Product;
import com.proiect_final.proiect_final.repositories.ProductRepository;
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
