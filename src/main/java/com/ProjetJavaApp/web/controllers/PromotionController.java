package com.ProjetJavaApp.web.controllers;

import com.ProjetJavaApp.data.entities.Promotion;
import com.ProjetJavaApp.web.dto.requests.PromotionDTO;
import com.ProjetJavaApp.data.enums.StatutPromotionEnum;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PromotionController {
    ResponseEntity<PromotionDTO> creerPromotion(PromotionDTO promotionDTO);
    ResponseEntity<PromotionDTO> mettreAJourPromotion(Long id, PromotionDTO promotionDTO);
    ResponseEntity<Void> supprimerPromotion(Long id);
    ResponseEntity<List<PromotionDTO>> listerToutesLesPromotions();
    ResponseEntity<PromotionDTO> recupererPromotionParId(Long id);
    ResponseEntity<PromotionDTO> cloturerPromotion(Long id);
    ResponseEntity<List<PromotionDTO>> listerPromotionsParStatut(StatutPromotionEnum statut);
    ResponseEntity<PromotionDTO> ajouterOuRetirerReferentiel(Long id, List<Long> referentielIds, boolean ajouter);
}
