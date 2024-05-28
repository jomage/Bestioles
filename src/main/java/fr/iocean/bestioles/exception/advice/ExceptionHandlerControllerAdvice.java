package fr.iocean.bestioles.exception.advice;

import fr.iocean.bestioles.exception.CannotDeleteEntityException;
import fr.iocean.bestioles.exception.EntityToCreateHasAnIdException;
import fr.iocean.bestioles.exception.EntityToUpdateHasNoIdException;
import fr.iocean.bestioles.exception.dto.ErrorDto;
import fr.iocean.bestioles.exception.dto.InvalidEntityErrorDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler({
            EntityNotFoundException.class,
            fr.iocean.bestioles.exception.EntityNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleExceptionNotFound(Exception exception, WebRequest request) {
        exception.printStackTrace();
        return new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "L'entité n'a pas été trouvée",
                request.getDescription(false)
        );
    }

    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class
    })
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDto handleExceptionEndpointNotSupported(Exception exception, WebRequest request) {
        exception.printStackTrace();
        return new ErrorDto(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler({
            EntityToCreateHasAnIdException.class,
            EntityToUpdateHasNoIdException.class,
            CannotDeleteEntityException.class
    })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDto handleExceptionBadId(Exception exception, WebRequest request) {
        exception.printStackTrace();
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public InvalidEntityErrorDto handleExceptionArgumentNotValid(
            MethodArgumentNotValidException exception, WebRequest request) {
        exception.printStackTrace();
        return new InvalidEntityErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                getMessagesFromBindingResult(exception.getBindingResult()),
                request.getDescription(false),
                exception.getBindingResult().getGlobalErrors(),
                exception.getBindingResult().getFieldErrors()
        );
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            IllegalStateException.class
    })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDto handleExceptionOther(Exception exception, WebRequest request) {
        exception.printStackTrace();
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }

    private String getMessagesFromBindingResult(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        if (bindingResult.hasGlobalErrors()) {
            sb.append("Erreurs globales :\n");
            for (ObjectError err : bindingResult.getGlobalErrors()) {
                sb.append(err.getObjectName())
                        .append(" : ")
                        .append(err.getDefaultMessage())
                        .append("\n");
            }
        }

        if (bindingResult.hasFieldErrors()) {
            sb.append("Erreurs de champs :\n");
            for (FieldError err : bindingResult.getFieldErrors()) {
                sb.append(err.getField())
                        .append(" : ")
                        .append(err.getDefaultMessage())
                        .append("\n");
            }
        }

        return sb.toString();
    }
}
