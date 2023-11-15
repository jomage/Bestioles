package fr.iocean.bestioles.exception.dto;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO qui sera renvoyé au client lors d'une exception
 */
public class InvalidEntityErrorDto extends ErrorDto {

    private List<ObjectError> globalErrors;
    private List<FieldError> fieldErrors;

    public InvalidEntityErrorDto(
            int statusCode,
            String message,
            String description,
            List<ObjectError> globalErrors,
            List<FieldError> fieldErrors
    ) {
        super(statusCode, message, description);
        this.globalErrors = globalErrors;
        this.fieldErrors = fieldErrors;
    }

    public List<ObjectError> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<ObjectError> globalErrors) {
        this.globalErrors = globalErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
