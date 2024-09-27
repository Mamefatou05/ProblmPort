package com.ProjetJavaApp.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFinApresDateDebutImpl.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFinApresDateDebut {
    String message() default "La date de fin doit être après la date de début";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
