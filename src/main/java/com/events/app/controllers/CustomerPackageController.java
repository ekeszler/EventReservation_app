package com.events.app.controllers;

import com.events.app.dtos.CustomerPackageRequestDTO;
import com.events.app.dtos.ProductPackageRequestDTO;
import com.events.app.entities.Package;
import com.events.app.dtos.ProductRequestDTO;
import com.events.app.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerpackage")
public class CustomerPackageController {

    PackageService packageService;

    @Autowired
    public CustomerPackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping
    public ResponseEntity<Package> createPackage(@RequestBody CustomerPackageRequestDTO customerPackageRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(packageService.createPackage(customerPackageRequestDTO));
    }
    @PostMapping("/addToPackage")
    public ResponseEntity<?> addProductToPackage(@RequestBody ProductPackageRequestDTO productRequestDTO){
        return ResponseEntity.ok(packageService.addProductToPackage(productRequestDTO));
    }
}
