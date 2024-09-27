package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    Module findByLibelle(String name);
}
