package com.proiect_final.proiect_final.controllers;

import com.proiect_final.proiect_final.dtos.CustomerPackageRequestDTO;
import com.proiect_final.proiect_final.dtos.ProductRequestDTO;
import com.proiect_final.proiect_final.entities.CustomerPackage;
import com.proiect_final.proiect_final.entities.Product;
import com.proiect_final.proiect_final.entities.RoleType;
import com.proiect_final.proiect_final.services.CustomerPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerpackage")
public class CustomerPackageController {

    CustomerPackageService customerPackageService;

    @Autowired
    public CustomerPackageController(CustomerPackageService customerPackageService) {
        this.customerPackageService = customerPackageService;
    }

    @PostMapping
    public ResponseEntity<CustomerPackage> createPackage(@RequestBody CustomerPackageRequestDTO customerPackageRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerPackageService.createPackage(customerPackageRequestDTO));
    }
    @PostMapping("/{productRequestDTO},/customerPackage/{customerPackageId}")
    public ResponseEntity<?> addProductToPackage(@PathVariable ProductRequestDTO productRequestDTO, @PathVariable Long customerPackageId){
        return ResponseEntity.ok(customerPackageService.addProductToPackage(productRequestDTO,customerPackageId));
    }
}
