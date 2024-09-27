package com.ProjetJavaApp.web.controllers.impl;

import com.ProjetJavaApp.data.entities.Promotion;
import com.ProjetJavaApp.data.enums.StatutPromotionEnum;
import com.ProjetJavaApp.services.ExportImport.ExportImportService;
import com.ProjetJavaApp.services.PromotionService;
import com.ProjetJavaApp.web.controllers.PromotionController;
import com.ProjetJavaApp.web.dto.mappers.PromotionMapper;
import com.ProjetJavaApp.web.dto.projections.PromotionStatsProjection;
import com.ProjetJavaApp.web.dto.requests.PromotionDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionControllerImpl implements PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionMapper promotionMapper;

    private ExportImportService exportImportService;


    // Créer une promotion
    @Operation(summary = "Créer une nouvelle promotion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<PromotionDTO> creerPromotion(@Valid @RequestBody PromotionDTO promotionDTO) {
        // Calculer la durée réelle avant de créer l'entité
        promotionDTO.calculateDureeReelle();

        // Mapper le DTO vers l'entité
        Promotion promotion = promotionMapper.toEntity(promotionDTO);

        // Créer la nouvelle promotion
        Promotion nouvellePromotion = promotionService.creerPromotion(promotion);

        // Retourner la réponse avec le DTO de la nouvelle promotion
        return ResponseEntity.ok(promotionMapper.toDto(nouvellePromotion));
    }

    // Mettre à jour une promotion
    @PatchMapping("/{id}")
    public ResponseEntity<PromotionDTO> mettreAJourPromotion(@PathVariable Long id, @Valid @RequestBody PromotionDTO promotionDTO) {
        Promotion promotion = promotionMapper.toEntity(promotionDTO);
        Promotion promotionMiseAJour = promotionService.mettreAJourPromotion(id, promotion);
        if (promotionMiseAJour != null) {
            return ResponseEntity.ok(promotionMapper.toDto(promotionMiseAJour));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une promotion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPromotion(@PathVariable Long id) {
        promotionService.supprimerPromotion(id);
        return ResponseEntity.noContent().build();
    }

    // Lister toutes les promotions

    @GetMapping
    public ResponseEntity<List<PromotionDTO>> listerToutesLesPromotions() {
        try {
            List<Promotion> promotions = promotionService.listerToutesLesPromotions();

            List<String> fieldNames = List.of( "libelle", "dateDebut", "dateFin","dureeReelle"); // Remplacez par les noms de champs appropriés

            // Exporter les données
//            byte[] exportedData = exportImportService.exportData(Promotion.class, fieldNames, promotions);

            // Conversion des promotions en DTO
            List<PromotionDTO> promotionDTOs = promotionMapper.toDtoList(promotions);

            return ResponseEntity.ok(promotionDTOs);
        } catch (Exception e) {
            // Log the exception for debugging
            // logger.error("Error listing promotions", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList()); // You can return a message here if needed
        }
    }




    // Récupérer une promotion par ID
    @GetMapping("/{id}")
    public ResponseEntity<PromotionDTO> recupererPromotionParId(@PathVariable Long id) {
        return promotionService.recupererPromotionParId(id)
                .map(promotion -> ResponseEntity.ok(promotionMapper.toDto(promotion)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Clôturer une promotion
        @PatchMapping("/{id}/cloturer")
    public ResponseEntity<PromotionDTO> cloturerPromotion(@PathVariable Long id) {
        Promotion promotionCloturee = promotionService.cloturerPromotion(id);
        if (promotionCloturee != null) {
            return ResponseEntity.ok(promotionMapper.toDto(promotionCloturee));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<PromotionDTO>> listerPromotionsParStatut(@PathVariable StatutPromotionEnum statut) {
        List<Promotion> promotions = promotionService.listerPromotionsParStatut(statut);
        return ResponseEntity.ok(promotionMapper.toDtoList(promotions));
    }


    // Ajouter ou retirer un référentiel à une promotion
    @PatchMapping("/{id}/referentiels")
    public ResponseEntity<PromotionDTO> ajouterOuRetirerReferentiel(
            @PathVariable Long id, @RequestBody List<Long> referentielIds, @RequestParam boolean ajouter) {

        Promotion promotion = promotionService.recupererPromotionParId(id).orElseThrow();
    return ResponseEntity.ok(promotionMapper.toDto(promotion));
    }

    @GetMapping("/stats")
    public ResponseEntity<PromotionStatsProjection> getPromotionStats(
            @RequestParam(value = "id", required = false) Long id) {
        PromotionStatsProjection promotionStats = promotionService.getPromotionStats(id);
        return ResponseEntity.ok(promotionStats);
    }


}
