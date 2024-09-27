package com.ProjetJavaApp.web.dto.requests.notes;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class NoteApprenantDTO {
    @NotNull(message = "L'ID de l'apprenant est obligatoire")
    private Long apprenantId;

    @NotEmpty(message = "La liste des notes ne peut pas être vide")
    @Valid
    private List<NoteModuleDTO> notes;

    public static class NoteModuleDTO {
        @NotNull(message = "L'ID du module est obligatoire")
        private Long moduleId;

        @NotNull(message = "La note est obligatoire")
        private Float note;

    }

}