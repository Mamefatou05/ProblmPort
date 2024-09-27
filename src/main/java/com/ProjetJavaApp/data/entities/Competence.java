package com.ProjetJavaApp.data.entities;

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
@Table(name = "competences")
@SQLDelete(sql = "UPDATE competences SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;

    private String description;

    @ManyToOne
    @JoinColumn(name = "type_competence_id")
    private TypeCompetence typeCompetence;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt = null;

}