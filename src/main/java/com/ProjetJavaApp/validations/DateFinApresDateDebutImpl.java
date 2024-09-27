package com.ProjetJavaApp.validations;

import com.ProjetJavaApp.web.dto.requests.PromotionDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateFinApresDateDebutImpl implements ConstraintValidator<DateFinApresDateDebut, PromotionDTO> {

    @Override
    public void initialize(DateFinApresDateDebut constraintAnnotation) {
        // Aucun traitement nécessaire
    }

    @Override
    public boolean isValid(PromotionDTO promotion, ConstraintValidatorContext context) {
        if (promotion.getDateDebut() == null || promotion.getDateFin() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Les dates ne doivent pas être nulles")
                    .addConstraintViolation();
            return false;
        }
        return promotion.getDateFin().after(promotion.getDateDebut());
    }
}
