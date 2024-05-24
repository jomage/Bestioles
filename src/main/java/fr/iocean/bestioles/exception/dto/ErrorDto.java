package fr.iocean.bestioles.exception.dto;

import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * DTO qui sera renvoyé au client lors d'une exception
 */
@Schema(
    description = "DTO qui sera renvoyé au client lors d'une exception",
    discriminatorProperty = "errorType",
    discriminatorMapping = {
            @DiscriminatorMapping(value = "InvalidEntityErrorDto", schema = InvalidEntityErrorDto.class)
    }
)
public class ErrorDto {
    private final int statusCode;
    private final LocalDateTime localDateTime;
    private final String message;
    private final String description;

    public ErrorDto(
            int statusCode,
            String message,
            String description
    ) {
        this.statusCode = statusCode;
        this.localDateTime = LocalDateTime.now();
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



}
