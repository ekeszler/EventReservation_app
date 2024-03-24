package com.events.app.mapper;

import com.events.app.dtos.ProductRequestDTO;
import com.events.app.entities.Product;
import com.events.app.entities.User;
import com.events.app.repositories.PackageRepository;
import com.events.app.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProductMapper {

    PackageRepository packageRepository;

    public Product mapFromDTO(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setProductName(productRequestDTO.getProductName());
        product.setPrice(productRequestDTO.getPrice());
        product.setCustomerPackages(packageRepository.findById(productRequestDTO.getPackageId()).stream().toList());
        return product;
    }
}

