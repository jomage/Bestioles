package fr.iocean.bestioles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotDeleteEntityException extends RuntimeException {
    public CannotDeleteEntityException() {
        super("Suppression de l'entité impossible");
    }
    public CannotDeleteEntityException(String message) {
        super(message);
    }
}
