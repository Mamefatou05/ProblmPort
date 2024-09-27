package com.ProjetJavaApp.web.dto.requests.apprenants;

import com.ProjetJavaApp.web.dto.requests.users.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ApprenantDTO {
    @NotBlank(message = "Le nom du tuteur est obligatoire")
    private String nomTuteur;

    @NotBlank(message = "Le prénom du tuteur est obligatoire")
    private String prenomTuteur;

    @NotBlank(message = "Le contact du tuteur est obligatoire")
    private String contactTuteur;

    @NotBlank(message = "Le matricule est obligatoire")
    private String matricule;

    private String photoCouverture;

    @NotNull(message = "L'ID de la promotion-référentiel est obligatoire")
    private Long promoReferentielId;

    @NotNull(message = "Les informations de l'utilisateur sont obligatoires")
    private UserDTO user;
}
