package com.ProjetJavaApp.web.dto.requests.emargements;

import com.ProjetJavaApp.data.enums.TypeEmargEnum;
import com.ProjetJavaApp.validations.TypeCompetence;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmargementDTO {

    @NotNull(message = "L'ID de l'apprenant est obligatoire")
    private Long apprenantId;

    private Date dateEmarg ;

    @NotNull(message = "Le type d'émargement est obligatoire")
    @TypeCompetence
    public TypeEmargEnum typeEmargement;
}
