package com.ProjetJavaApp.web.dto.requests.notes;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class NoteModuleGroupeDTO {
    @NotNull(message = "L'ID du module est obligatoire")
    private Long moduleId;

    @NotEmpty(message = "La liste des notes ne peut pas être vide")
    @Valid
    private List<NoteApprenantDTO> notes;

    public static class NoteApprenantDTO {
        @NotNull(message = "L'ID de l'apprenant est obligatoire")
        private Long apprenantId;

        @NotNull(message = "La note est obligatoire")
        private Float note;

    }

}
