package io.greatgreven.rockpaperscissorapi.exception;

public class MoveNotMadeException extends RuntimeException{
    public MoveNotMadeException() {
        super();
    }

    public MoveNotMadeException(String message) {
        super(message);
    }

    public MoveNotMadeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoveNotMadeException(Throwable cause) {
        super(cause);
    }
}
