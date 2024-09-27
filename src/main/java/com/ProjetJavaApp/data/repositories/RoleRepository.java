package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
