package com.ProjetJavaApp.data.entities;

import com.ProjetJavaApp.data.enums.StatutPromotionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private Integer dureeReelle;

    @Enumerated(EnumType.STRING)
    private StatutPromotionEnum statut = StatutPromotionEnum.INACTIF;

    // Relation avec PromotionReferentiel pour lister les référentiels associés
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PromotionReferentiel> promotionReferentiels;

}