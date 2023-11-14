package fr.iocean.bestioles.exception.dto;

import java.time.LocalDateTime;

/**
 * DTO qui sera renvoyé au client lors d'une exception
 */
public class ErrorDto {
    private final int statusCode;
    private final LocalDateTime localDateTime;
    private final String message;
    private final String description;

    private final String test = "coucou";

    public ErrorDto(
            int statusCode,
            LocalDateTime localDateTime,
            String message,
            String description
    ) {
        this.statusCode = statusCode;
        this.localDateTime = localDateTime;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public String getTest() {
        return test;
    }
}
