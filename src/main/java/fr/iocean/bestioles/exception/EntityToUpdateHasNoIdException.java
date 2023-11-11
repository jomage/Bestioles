package fr.iocean.bestioles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityToUpdateHasNoIdException extends RuntimeException {
    public EntityToUpdateHasNoIdException() {
        super("L'entité à modifier ne possède pas d'ID.");
    }
}
