package io.greatgreven.rockpaperscissorapi.requests;

import io.greatgreven.rockpaperscissorapi.model.Move;

public class MoveRequest {
    private final String name;
    private final Move move;

    public MoveRequest(String name, Move move) {
        this.name = name;
        this.move = move;
    }

    public MoveRequest() {
        this.name = "";
        this.move = null;
    }

    public String getName() {
        return name;
    }

    public Move getMove() {
        return move;
    }
}
