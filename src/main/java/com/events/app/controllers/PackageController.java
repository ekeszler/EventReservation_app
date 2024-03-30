package com.events.app.controllers;

import com.events.app.dtos.PackageRequestDTO;
import com.events.app.dtos.ProductRequestDTO;
import com.events.app.entities.Package;
import com.events.app.entities.Product;
import com.events.app.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageController {

    PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping
    public ResponseEntity<Package> createPackage(@RequestBody PackageRequestDTO packageRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(packageService.createPackage(packageRequestDTO));
    }
    @PostMapping("/addToPackage")
    public ResponseEntity<?> addProductToPackage(@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(packageService.addProductToPackage(productRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<Package>> showAllPackages(){
        List<Package> packages = packageService.showAllPackages();
        return ResponseEntity.ok(packages);
    }

    @PutMapping("/{packageId}/update-price")
    public ResponseEntity<String> updatePackagePrice(@PathVariable Long packageId, @RequestParam double newPrice){
        try{
            packageService.updatePackagePrice(packageId, newPrice);
            return ResponseEntity.ok("The price of package with id " + packageId + " was updated to " + newPrice + " RON");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. The price was not updated");
        }
    }

    @DeleteMapping("/{packageId}")
    public ResponseEntity<String> deletePackage(@PathVariable Long packageId){
        try{
            packageService.deletePackage(packageId);
            return ResponseEntity.ok("The package with id " + packageId + " was deleted");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, package was not deleted");
        }
    }
}
