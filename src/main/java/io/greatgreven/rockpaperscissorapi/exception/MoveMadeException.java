package io.greatgreven.rockpaperscissorapi.exception;

public class MoveMadeException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public MoveMadeException(String message) {
        super(message);
    }    
}
