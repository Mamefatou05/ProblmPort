package com.ProjetJavaApp.web.dto.requests.referentiels;

import com.ProjetJavaApp.web.dto.requests.competences.CompetenceDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


public class ReferentielDTO {
    @NotBlank(message = "Le libellé est obligatoire")
    private String libelle;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    private String photoCouverture;

    @Valid
    private List<CompetenceDTO> competences;  // Compétences ne sont plus obligatoires

}
