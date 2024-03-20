package com.events.app.services;

import com.events.app.dtos.PackageRequestDTO;
import com.events.app.dtos.ProductRequestDTO;
import com.events.app.entities.Package;
import com.events.app.entities.Product;
import com.events.app.exceptions.ResourceNotFoundException;
import com.events.app.repositories.PackageRepository;
import com.events.app.repositories.ProductRepository;
import jakarta.transaction.Transactional;
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

    public Package createPackage(PackageRequestDTO packageRequestDTO){
        Package aPackage = new Package(packageRequestDTO.getPackageName(), packageRequestDTO.getPrice());
        return packageRepository.save(aPackage);
    }

    public Package addProductToPackage(ProductRequestDTO productRequestDTO) {
        Package Package = packageRepository.findById(productRequestDTO.getPackageId()).orElseThrow(() -> new ResourceNotFoundException("package not found"));
        Product product = productRepository.findByProductName(productRequestDTO.getProductName()).orElseThrow(() -> new ResourceNotFoundException("product not found"));
        Package.getProducts().add(product);
        product.getCustomerPackages().add(Package);
        return packageRepository.save(Package);
    }

    @Transactional
    public Package updatePackagePrice(Long packageId, double newPrice){
        Package aPackage = packageRepository.findById(packageId).orElseThrow(()->new ResourceNotFoundException("Package was not found"));
        aPackage.setPrice(newPrice);
        return packageRepository.save(aPackage);
    }

    @Transactional
    public void deletePackage(Long packadeId){
        Package aPackage = packageRepository.findById(packadeId).orElseThrow(()->new ResourceNotFoundException("package not found"));
        packageRepository.delete(aPackage);

    }
}
