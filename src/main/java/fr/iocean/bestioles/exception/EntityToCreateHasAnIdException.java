package fr.iocean.bestioles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityToCreateHasAnIdException extends RuntimeException {
    public EntityToCreateHasAnIdException() {
        super("L'entité à créer possède une ID.");
    }
}
