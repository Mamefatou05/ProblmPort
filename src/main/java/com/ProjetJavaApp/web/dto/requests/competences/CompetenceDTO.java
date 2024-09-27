package com.ProjetJavaApp.web.dto.requests.competences;

import com.ProjetJavaApp.data.enums.TypeCompEnum;
import com.ProjetJavaApp.validations.TypeCompetence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CompetenceDTO {
    @NotBlank(message = "Le nom de la compétence est obligatoire")
    private String nom;

    @NotBlank(message = "La description de la compétence est obligatoire")
    private String description;

    @NotNull(message = "La durée d'acquisition est obligatoire")
    private Integer dureeAcquisition;

    @TypeCompetence
    @NotNull(message = "Le type de compétence est obligatoire")
    private TypeCompEnum type;

    @Valid
    private List<ModuleDTO> modules;

}
