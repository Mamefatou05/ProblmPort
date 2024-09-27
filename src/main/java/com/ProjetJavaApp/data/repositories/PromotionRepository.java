package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Promotion;
import com.ProjetJavaApp.data.enums.StatutPromotionEnum;
import com.ProjetJavaApp.web.dto.projections.PromotionStatsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Promotion findByLibelle(String libelle);

    // Cette méthode retourne une liste de promotions pour un statut donné
    List<Promotion>  findByStatut(StatutPromotionEnum statut);


    // Cette méthode retourne une seule promotion pour un statut donné  (la première trouvée s'il sont plusieur)
    Optional<Promotion> findFirstByStatut(StatutPromotionEnum statut);

    @Query("SELECT p.id AS promotionId, p.libelle AS libelle, "
            + "COUNT(DISTINCT a.id) AS nombreApprenants, "
            + "SUM(CASE WHEN a.statut = 'ACTIF' THEN 1 ELSE 0 END) AS nombreApprenantsActifs, "
            + "SUM(CASE WHEN a.statut = 'INACTIF' THEN 1 ELSE 0 END) AS nombreApprenantsInactifs, "
            + "r.id AS referentielId, r.libelle AS referentielLibelle, COUNT(a.id) AS referentielApprenants "
            + "FROM Promotion p "
            + "JOIN p.promotionReferentiels pr "
            + "JOIN pr.referentiel r "
            + "LEFT JOIN Apprenant a ON a.promotionReferentiel = pr "
            + "WHERE p.id = :promotionId "
            + "AND r.statut = 'ACTIF' "
            + "GROUP BY p.id, r.id")
    PromotionStatsProjection getPromotionStatsById(@Param("promotionId") Long promotionId);

}
