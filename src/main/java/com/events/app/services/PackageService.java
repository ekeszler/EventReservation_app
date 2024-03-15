package com.events.app.services;

import com.events.app.dtos.CustomerPackageRequestDTO;
import com.events.app.dtos.ProductPackageRequestDTO;
import com.events.app.entities.Package;
import com.events.app.entities.Product;
import com.events.app.exceptions.ResourceNotFoundException;
import com.events.app.dtos.ProductRequestDTO;
import com.events.app.repositories.PackageRepository;
import com.events.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService {

    ProductRepository productRepository;
    PackageRepository packageRepository;

    @Autowired
    public PackageService(ProductRepository productRepository, PackageRepository packageRepository) {
        this.productRepository = productRepository;
        this.packageRepository = packageRepository;
    }

    public Package createPackage(CustomerPackageRequestDTO customerPackageRequestDTO){
        Package aPackage = new Package(customerPackageRequestDTO.getPackageName(), customerPackageRequestDTO.getPrice());
        return packageRepository.save(aPackage);
    }

    public Package addProductToPackage(ProductPackageRequestDTO productRequestDTO) {
        Package aPackage = packageRepository.findById(productRequestDTO.getPackageId()).orElseThrow(() -> new ResourceNotFoundException("package not found"));
        Product product = productRepository.findByProductName(productRequestDTO.getProductName()).orElseThrow(() -> new ResourceNotFoundException("product not found"));
        aPackage.getProducts().add(product);
        product.getCustomerPackages().add(aPackage);
        return packageRepository.save(aPackage);
    }
}
