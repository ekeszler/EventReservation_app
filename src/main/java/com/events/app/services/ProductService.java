package com.events.app.services;

import com.events.app.entities.Package;
import com.events.app.entities.Product;
import com.events.app.dtos.ProductRequestDTO;
import com.events.app.exceptions.ResourceNotFoundException;
import com.events.app.mapper.ProductMapper;
import com.events.app.repositories.PackageRepository;
import com.events.app.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    PackageRepository packageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, PackageRepository packageRepository) {
        this.productRepository = productRepository;
        this.packageRepository = packageRepository;
        this.productMapper = productMapper;
    }
    @Transactional
    public Product createProduct(ProductRequestDTO productRequestDTO){
        Product product = productMapper.mapFromDTO(productRequestDTO);
        Package apackage = packageRepository.findById(productRequestDTO.getPackageId()).orElseThrow(()-> new ResourceNotFoundException("package not found"));
        product.getCustomerPackages().add(apackage);
        return productRepository.save(product);
    }

    @Transactional
    public List<Product> showAllProducts(){
        return productRepository.findAll();
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
