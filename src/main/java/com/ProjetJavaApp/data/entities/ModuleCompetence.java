package com.ProjetJavaApp.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "module_competences")
public class ModuleCompetence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

}