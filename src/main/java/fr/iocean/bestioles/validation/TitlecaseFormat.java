package fr.iocean.bestioles.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TitlecaseValidator.class)
public @interface TitlecaseFormat {
    String message() default "La première lettre doit être une majuscule";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
