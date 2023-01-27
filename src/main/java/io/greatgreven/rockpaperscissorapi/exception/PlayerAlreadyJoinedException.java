package io.greatgreven.rockpaperscissorapi.exception;

public class PlayerAlreadyJoinedException extends RuntimeException{
    public PlayerAlreadyJoinedException() {
        super();
    }

    public PlayerAlreadyJoinedException(String message) {
        super(message);
    }

    public PlayerAlreadyJoinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerAlreadyJoinedException(Throwable cause) {
        super(cause);
    }
}
