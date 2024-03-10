package com.proiect_final.proiect_final.repositories;

import com.proiect_final.proiect_final.entities.CustomerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CustomerPackageRepository extends JpaRepository<CustomerPackage, Long> {

    Optional<CustomerPackage> findByPackageName(String packageName);
}
