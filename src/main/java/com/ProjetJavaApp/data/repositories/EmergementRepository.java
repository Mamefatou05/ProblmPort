package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Apprenant;
import com.ProjetJavaApp.data.entities.Emergement;
import com.ProjetJavaApp.web.dto.projections.EmergementProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmergementRepository extends JpaRepository<Emergement, Long> {
    List<EmergementProjection> findByDateEntree(LocalDateTime dateEntree);

    // Filtrer par jour précis (date uniquement)
    @Query("SELECT e FROM Emergement e WHERE CAST(e.dateSortie AS LocalDate) = :date")
    List<Emergement> findAllByDate(@Param("date") LocalDate date);

    // Filtrer par mois et année
    @Query("SELECT e FROM Emergement e WHERE EXTRACT(MONTH FROM e.dateSortie) = :month AND EXTRACT(YEAR FROM e.dateSortie) = :year")
    List<Emergement> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);

    long countByApprenantAndDateEntree(Apprenant apprenant, Date date);
}

