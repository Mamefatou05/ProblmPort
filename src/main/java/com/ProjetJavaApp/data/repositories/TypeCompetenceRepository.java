package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.TypeCompetence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeCompetenceRepository extends JpaRepository<TypeCompetence, Integer> {

    TypeCompetence findByLibelle(String libelle);

}
