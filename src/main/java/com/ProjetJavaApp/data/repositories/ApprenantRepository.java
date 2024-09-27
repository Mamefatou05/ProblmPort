package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Apprenant;
import com.ProjetJavaApp.data.enums.StatutApprenantEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

    List<Apprenant> findByStatut(StatutApprenantEnum status);

}
