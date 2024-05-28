package fr.iocean.bestioles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotDeleteEntityException extends RuntimeException {
    public CannotDeleteEntityException() {
        super("L'entité avec cette ID n'a pas été trouvée");
    }
    public CannotDeleteEntityException(String message) {
        super(message);
    }
}
