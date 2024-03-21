package com.events.app.services;

import com.events.app.entities.Product;
import com.events.app.dtos.ProductRequestDTO;
import com.events.app.exceptions.ResourceNotFoundException;
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
        //TODO use product mapper
        Product product = new Product(productRequestDTO.getProductName(), productRequestDTO.getPrice());
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProductPrice(Long productId, double newPrice){
        Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product was not found"));
        product.setPrice(newPrice);
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("product was not found"));
        productRepository.delete(product);

    }
}
