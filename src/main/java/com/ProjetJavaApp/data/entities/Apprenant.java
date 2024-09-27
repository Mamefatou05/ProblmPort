package com.ProjetJavaApp.data.entities;

import com.ProjetJavaApp.data.enums.StatutApprenantEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apprenants")
public class Apprenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomTuteur;

    private String prenomTuteur;

    private String contactTuteur;

    @Column(unique = true)
    private String matricule;

    private String cniFile;

    private String extraitFile;

    private String diplomeFile;

    private String casierFile;

    private String photoCouverture;

    @Enumerated(EnumType.STRING)
    private StatutApprenantEnum statut = null;

    private String motif;

    @ManyToOne
    @JoinColumn(name = "promo_ref_id")
    private PromotionReferentiel promotionReferentiel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}