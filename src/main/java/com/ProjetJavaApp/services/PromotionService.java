package com.ProjetJavaApp.services;

import com.ProjetJavaApp.data.entities.Promotion;
import com.ProjetJavaApp.data.entities.Referentiel;
import com.ProjetJavaApp.data.enums.StatutPromotionEnum;
import com.ProjetJavaApp.web.dto.projections.PromotionStatsProjection;

import java.util.List;
import java.util.Optional;

public interface PromotionService {
    Promotion creerPromotion(Promotion promotion);
    Promotion mettreAJourPromotion(Long id, Promotion promotion);
    void supprimerPromotion(Long id);
    List<Promotion> listerToutesLesPromotions();
    List<Promotion> listerPromotionsParStatut(StatutPromotionEnum statut);
    List<Promotion> listerPromotionsActives();
    Optional<Promotion> recupererPromotionParId(Long id);
    Promotion cloturerPromotion(Long id);
    Promotion ajouterOuRetirerReferentiel(Promotion promotion, Referentiel referentiel, boolean ajouter);
    PromotionStatsProjection getPromotionStats(Long promotionId);
}
