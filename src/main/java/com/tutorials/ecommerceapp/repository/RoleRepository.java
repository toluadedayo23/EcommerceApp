package com.tutorials.ecommerceapp.repository;

import com.tutorials.ecommerceapp.model.ERole;
import com.tutorials.ecommerceapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(ERole role);
}
