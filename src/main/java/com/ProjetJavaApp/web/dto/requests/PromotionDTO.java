package com.ProjetJavaApp.web.dto.requests;

import com.ProjetJavaApp.validations.DateFinApresDateDebut;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@DateFinApresDateDebut(message = "La date de fin doit être après la date de début")
public class PromotionDTO {
    @NotBlank(message = "Le libellé est obligatoire")
    @Size(max = 100, message = "Le libellé ne doit pas dépasser 100 caractères")
    private String libelle;

    @NotNull(message = "La date de début est obligatoire")
    @FutureOrPresent(message = "La date de début doit être aujourd'hui ou dans le futur")

    private Date dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    @Future(message = "La date de fin doit être dans le futur")

    private Date dateFin;

    private Integer dureeReelle;

    public void calculateDureeReelle() {
        if (dateDebut != null && dateFin != null) {
            long durationInMillis = dateFin.getTime() - dateDebut.getTime();
            this.dureeReelle = (int) TimeUnit.DAYS.convert(durationInMillis, TimeUnit.MILLISECONDS);
        } else if (dureeReelle != null && dateDebut != null) {
            // Ajuster la date de fin en fonction de la durée réelle
            dateFin = new Date(dateDebut.getTime() + TimeUnit.DAYS.toMillis(dureeReelle));
        }
    }


}
