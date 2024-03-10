package com.proiect_final.proiect_final.services;

import com.proiect_final.proiect_final.dtos.CustomerPackageRequestDTO;
import com.proiect_final.proiect_final.dtos.ProductRequestDTO;
import com.proiect_final.proiect_final.entities.CustomerPackage;
import com.proiect_final.proiect_final.entities.Event;
import com.proiect_final.proiect_final.entities.Product;
import com.proiect_final.proiect_final.exceptions.ResourceNotFoundException;
import com.proiect_final.proiect_final.repositories.CustomerPackageRepository;
import com.proiect_final.proiect_final.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerPackageService {

    ProductRepository productRepository;
    CustomerPackageRepository customerPackageRepository;

    @Autowired
    public CustomerPackageService(ProductRepository productRepository, CustomerPackageRepository customerPackageRepository) {
        this.productRepository = productRepository;
        this.customerPackageRepository = customerPackageRepository;
    }

    public CustomerPackage createPackage(CustomerPackageRequestDTO customerPackageRequestDTO){
        CustomerPackage customerPackage = new CustomerPackage(customerPackageRequestDTO.getPackageName(), customerPackageRequestDTO.getPrice());
        return customerPackageRepository.save(customerPackage);
    }

    public CustomerPackage addProductToPackage(ProductRequestDTO productRequestDTO, Long customerPackageId) {
        CustomerPackage customerPackage = customerPackageRepository.findById(customerPackageId).orElseThrow(() -> new ResourceNotFoundException("package not found"));
        Product product = productRepository.findByProductName(productRequestDTO.getProductName());
        customerPackage.getProducts().add(product);
        return customerPackageRepository.save(customerPackage);
    }
}
