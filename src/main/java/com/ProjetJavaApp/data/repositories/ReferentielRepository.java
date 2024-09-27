package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Referentiel;
import com.ProjetJavaApp.data.enums.StatutReferentielEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferentielRepository extends JpaRepository<Referentiel, Long> {

    List<Referentiel> findByLibelle(String nom);

    List<Referentiel> findByStatut(StatutReferentielEnum statut);

    Referentiel findByCode(String code);
}
