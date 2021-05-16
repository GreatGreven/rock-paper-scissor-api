package io.greatgreven.rockpaperscissorapi.exception;

public class GameNotFullException extends RuntimeException {
    public GameNotFullException() {
    }

    public GameNotFullException(String message) {
        super(message);
    }

    public GameNotFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameNotFullException(Throwable cause) {
        super(cause);
    }
}
