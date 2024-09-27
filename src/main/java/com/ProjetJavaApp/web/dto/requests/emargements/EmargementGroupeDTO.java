package com.ProjetJavaApp.web.dto.requests.emargements;

import com.ProjetJavaApp.data.enums.TypeEmargEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmargementGroupeDTO {

    @NotNull(message = "La liste des apprenants est obligatoire")
    private List<Long> apprenantIds;

    @NotNull(message = "Le type d'émargement est obligatoire")
    private TypeEmargEnum typeEmargement;
}
