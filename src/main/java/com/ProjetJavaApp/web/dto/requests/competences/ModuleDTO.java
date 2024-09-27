package com.ProjetJavaApp.web.dto.requests.competences;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModuleDTO {
    @NotBlank(message = "Le nom du module est obligatoire")
    private String nom;

    @NotBlank(message = "La description du module est obligatoire")
    private String description;

    @NotNull(message = "La durée d'acquisition du module est obligatoire")
    private Integer dureeAcquisition;
}
