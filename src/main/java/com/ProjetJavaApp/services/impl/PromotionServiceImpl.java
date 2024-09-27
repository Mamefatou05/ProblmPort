package com.ProjetJavaApp.services.impl;

import com.ProjetJavaApp.data.entities.Promotion;
import com.ProjetJavaApp.data.entities.PromotionReferentiel;
import com.ProjetJavaApp.data.entities.Referentiel;
import com.ProjetJavaApp.data.enums.StatutPromotionEnum;
import com.ProjetJavaApp.data.enums.StatutReferentielEnum;
import com.ProjetJavaApp.data.enums.StatutUserEnum;
import com.ProjetJavaApp.data.repositories.PromotionRepository;
import com.ProjetJavaApp.exceptions.ServiceException;
import com.ProjetJavaApp.services.PromotionService; // Import your new interface
import com.ProjetJavaApp.web.dto.projections.PromotionStatsProjection;
import com.ProjetJavaApp.web.dto.projections.ReferentielStatsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service  // Cette annotation est toujours nécessaire pour que Spring gère ce bean
public class PromotionServiceImpl  implements PromotionService {

    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }
    // Création d'une promotion
    public Promotion creerPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    // Mise à jour d'une promotion
    public Promotion mettreAJourPromotion(Long id, Promotion promotion) {
        Optional<Promotion> promotionExistante = promotionRepository.findById(id);
        if (promotionExistante.isPresent()) {
            Promotion p = promotionExistante.get();
            p.setLibelle(promotion.getLibelle());
            p.setDateDebut(promotion.getDateDebut());
            p.setDateFin(promotion.getDateFin());
            p.setDureeReelle(promotion.getDureeReelle());
            return promotionRepository.save(p);
        }
        return null;
    }

    // Supprimer une promotion avec soft delete
    public void supprimerPromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    // Récupérer toutes les promotions
    public List<Promotion> listerToutesLesPromotions() {
        return promotionRepository.findAll();
    }

    // Récupérer les promotions actives
    public List<Promotion> listerPromotionsActives() {
        return promotionRepository.findByStatut(StatutPromotionEnum.ACTIF);
    }
    @Override
    public List<Promotion> listerPromotionsParStatut(StatutPromotionEnum statut) {
        return promotionRepository.findByStatut(statut);
    }

//    public PromotionStatsProjection getPromotionStats() {
//        // On récupère le promotion active
//        Promotion promotion = promotionRepository.findFirstByStatut(StatutPromotionEnum.ACTIF)
//                .orElseThrow(() -> new RuntimeException("Aucune promotion active trouvée"));
//
//        int nombreApprenants = promotion.getPromotionReferentiels().stream()
//                .mapToInt(ref -> ref.getApprenants().size())  // Parcourir les apprenants via PromotionReferentiel
//                .sum();
//
//        int nombreApprenantsActifs = promotion.getPromotionReferentiels().stream()
//                .flatMap(ref -> ref.getApprenants().stream()
//                        .filter(a -> a.getUser().getStatut() == StatutUserEnum.ACTIF))  // Filtrer les actifs
//                .mapToInt(a -> 1)
//                .sum();
//
//        int nombreApprenantsInactifs = nombreApprenants - nombreApprenantsActifs;
//
//        List<PromotionStatsProjection.ReferentielStatsProjection> referentiels = promotion.getPromotionReferentiels().stream()
//                .filter(ref -> ref.getReferentiel().getStatut() == StatutReferentielEnum.ACTIF)
//                .map(ref -> new PromotionStatsProjection.ReferentielStatsProjection() {
//                    @Override
//                    public Long getId() {
//                        return ref.getReferentiel().getId();
//                    }
//
//                    @Override
//                    public String getLibelle() {
//                        return ref.getReferentiel().getLibelle();
//                    }
//
//                    @Override
//                    public int getNombreApprenants() {
//                        return ref.getApprenants().size();
//                    }
//                }).collect(Collectors.toList());
//
//        return new PromotionStatsProjection() {
//            @Override
//            public Long getPromotionId() {
//                return promotion.getId();
//            }
//
//            @Override
//            public String getLibelle() {
//                return promotion.getLibelle();
//            }
//
//            @Override
//            public int getNombreApprenants() {
//                return nombreApprenants;
//            }
//
//            @Override
//            public int getNombreApprenantsActifs() {
//                return nombreApprenantsActifs;
//            }
//
//            @Override
//            public int getNombreApprenantsInactifs() {
//                return nombreApprenantsInactifs;
//            }
//
//            @Override
//            public List<PromotionStatsProjection.ReferentielStatsProjection> getReferentielsActifs() {
//                return referentiels;
//            }
//        };
//    }
//




    // Récupérer une promotion par ID
    public Optional<Promotion> recupererPromotionParId(Long id) {
        return promotionRepository.findById(id);
    }

    // Clôturer une promotion
    public Promotion cloturerPromotion(Long id) {
        Optional<Promotion> promotionExistante = promotionRepository.findById(id);
        if (promotionExistante.isPresent()) {
            Promotion promotion = promotionExistante.get();
            if (promotion.getDateFin().before(new Date())) {
                promotion.setStatut(StatutPromotionEnum.CLOTURER);
                return promotionRepository.save(promotion);
            }
        }
        return null;
    }


    public Promotion ajouterOuRetirerReferentiel(Promotion promotion, Referentiel referentiel, boolean ajouter) {
        if (ajouter) {
            boolean exists = promotion.getPromotionReferentiels().stream()
                    .anyMatch(pr -> pr.getReferentiel().equals(referentiel));
            if (!exists) {
                PromotionReferentiel promotionReferentiel = new PromotionReferentiel();
                promotionReferentiel.setPromotion(promotion);
                promotionReferentiel.setReferentiel(referentiel);
                promotion.getPromotionReferentiels().add(promotionReferentiel);
            }
        } else {
            promotion.getPromotionReferentiels().removeIf(pr -> pr.getReferentiel().equals(referentiel));
        }
        return promotionRepository.save(promotion);
    }


    public PromotionStatsProjection getPromotionStats(Long promotionId) {
        Promotion promotion;

        if (promotionId == null) {
            // Si l'ID n'est pas fourni, récupérer la promotion active
            promotion = promotionRepository.findFirstByStatut(StatutPromotionEnum.ACTIF)
                    .orElseThrow(() -> new ServiceException("No active promotion found"));
        } else {
            // Sinon, essayer de récupérer la promotion par son ID
            promotion = promotionRepository.findById(promotionId)
                    .orElseThrow(() -> new ServiceException("Promotion not found with id: " + promotionId));
        }

        // Récupération des statistiques pour la promotion trouvée
        return promotionRepository.getPromotionStatsById(promotion.getId());
    }
}
