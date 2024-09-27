package com.ProjetJavaApp.validations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TypeCompetenceImpl.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeCompetence {
    String message() default "Le type de compétence doit être BACKEND ou FRONTEND";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
