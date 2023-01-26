package io.greatgreven.rockpaperscissorapi.responses;

import java.io.Serializable;

public class GameCreationResponse implements Serializable {
    private final String invitation;

    public GameCreationResponse() {
        this.invitation = "";
    }

    public GameCreationResponse(String invitation) {
        this.invitation = invitation;
    }

    public String getInvitation() {
        return invitation;
    }
}
