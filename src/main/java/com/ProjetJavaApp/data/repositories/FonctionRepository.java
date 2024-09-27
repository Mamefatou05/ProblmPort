package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FonctionRepository extends JpaRepository<Fonction, Integer> {

    Fonction findByLibelle(String libelle);
}
