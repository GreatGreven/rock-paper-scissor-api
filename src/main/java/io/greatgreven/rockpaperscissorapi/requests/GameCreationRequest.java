package io.greatgreven.rockpaperscissorapi.requests;

import java.io.Serializable;

public class GameCreationRequest implements Serializable {
    private final String name;

    public GameCreationRequest() {
        this.name = "";
    }

    public GameCreationRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
