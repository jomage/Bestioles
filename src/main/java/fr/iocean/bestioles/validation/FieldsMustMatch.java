package fr.iocean.bestioles.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldsMustMatchValidator.class)
public @interface FieldsMustMatch {
    String message() default "Les champs doivent correspondre";

    String field1();
    String field2();


    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
