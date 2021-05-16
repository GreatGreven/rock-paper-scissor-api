package io.greatgreven.rockpaperscissorapi.exception;

public class PlayerNotInGameException extends RuntimeException {
    public PlayerNotInGameException() {
    }

    public PlayerNotInGameException(String message) {
        super(message);
    }

    public PlayerNotInGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerNotInGameException(Throwable cause) {
        super(cause);
    }
}
