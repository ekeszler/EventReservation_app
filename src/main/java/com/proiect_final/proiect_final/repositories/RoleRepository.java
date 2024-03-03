package com.proiect_final.proiect_final.repositories;

import com.proiect_final.proiect_final.entities.Role;
import com.proiect_final.proiect_final.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleType(RoleType roleType);
}
