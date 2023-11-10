package fr.iocean.bestioles.validation;

import fr.iocean.bestioles.repository.SpeciesRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitlecaseValidator implements ConstraintValidator<TitlecaseFormat, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[A-Z]");
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
