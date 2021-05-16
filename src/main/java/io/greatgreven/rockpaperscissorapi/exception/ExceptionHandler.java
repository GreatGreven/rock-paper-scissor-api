package io.greatgreven.rockpaperscissorapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler( value = {Exception.class})
    public ResponseEntity<Object> handle(Exception e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionObject exceptionObject =
                new ExceptionObject(
                        e.getMessage(),
                        status,
                        ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exceptionObject, status);
    }

}
