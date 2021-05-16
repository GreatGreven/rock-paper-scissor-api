package io.greatgreven.rockpaperscissorapi.exception;

public class GameIsFullException extends RuntimeException {
    public GameIsFullException() {
    }

    public GameIsFullException(String message) {
        super(message);
    }

    public GameIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameIsFullException(Throwable cause) {
        super(cause);
    }
}
