package fr.iocean.bestioles.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsMustMatchValidator implements ConstraintValidator<FieldsMustMatch, Object> {
    private String field1;
    private String field2;


    @Override
    public void initialize(FieldsMustMatch constraintAnnotation) {
        this.field1 = constraintAnnotation.field1();
        this.field2 = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object field1Value = new BeanWrapperImpl(value).getPropertyValue(field1);
        Object field2Value = new BeanWrapperImpl(value).getPropertyValue(field2);

        if (field1Value != null) {
            return field1Value.equals(field2Value);
        } else {
            return field2Value == null;
        }
    }
}
