package com.ProjetJavaApp.web.dto.requests.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    private String adresse;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    private String photo;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    @NotNull(message = "L'ID du rôle est obligatoire")
    private Long roleId;

    private Long fonctionId;

}
