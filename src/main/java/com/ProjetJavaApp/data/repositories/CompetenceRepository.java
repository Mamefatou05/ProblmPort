package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    Competence findByLibelle(String libelle);
}
