package com.ProjetJavaApp.data.entities;

import com.ProjetJavaApp.data.enums.StatutReferentielEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "referentiels")
@SQLDelete(sql = "UPDATE referentiels SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Referentiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatutReferentielEnum statut = StatutReferentielEnum.INACTIF;

    private String photoCouverture;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt = null;

}