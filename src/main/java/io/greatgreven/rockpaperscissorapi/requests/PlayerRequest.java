package io.greatgreven.rockpaperscissorapi.requests;

import java.io.Serializable;

public class PlayerRequest implements Serializable {
    private final String name;

    public PlayerRequest() {
        this.name = "";
    }

    public PlayerRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
