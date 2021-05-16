package io.greatgreven.rockpaperscissorapi.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ExceptionObject {
    private String message;
    private HttpStatus status;
    private ZonedDateTime timestamp;

    public ExceptionObject(String message, HttpStatus status, ZonedDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
