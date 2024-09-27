package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Appreciation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppreciationRepository extends JpaRepository<Appreciation, Long> {
}
