package com.ProjetJavaApp.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promotion_referentiels")
public class PromotionReferentiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "promo_id")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "referentiel_id")
    private Referentiel referentiel;

}