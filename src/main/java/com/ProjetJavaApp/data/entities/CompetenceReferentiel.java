package com.ProjetJavaApp.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "competence_referentiels")
public class CompetenceReferentiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @ManyToOne
    @JoinColumn(name = "referentiel_id")
    private Referentiel referentiel;
}