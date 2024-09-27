package com.ProjetJavaApp.validations;

import com.ProjetJavaApp.data.enums.TypeCompEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TypeCompetenceImpl implements ConstraintValidator<TypeCompetence, TypeCompEnum> {

    @Override
    public boolean isValid(TypeCompEnum type, ConstraintValidatorContext context) {
        if (type == null) {
            return false; // Type non spécifié
        }
        return type == TypeCompEnum.BACKEND || type == TypeCompEnum.FRONTEND; // Vérifie que le type est valide
    }

}
