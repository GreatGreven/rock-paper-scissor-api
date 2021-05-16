package io.greatgreven.rockpaperscissorapi.exception;

public class GameIsEmptyException extends RuntimeException {
    public GameIsEmptyException() {
        super();
    }

    public GameIsEmptyException(String message) {
        super(message);
    }

    public GameIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameIsEmptyException(Throwable cause) {
        super(cause);
    }

    protected GameIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
