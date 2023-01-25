package io.greatgreven.rockpaperscissorapi.model;

import java.util.Optional;

public class Round {
    private final int round;
    private final Player winner;
    private final Player loser;
    private final boolean tie;

    public Round(int round, boolean tie, Player winner, Player loser) {
        this.round = round;
        this.winner = winner;
        this.loser = loser;
        this.tie = tie;
    }

    public int getRound() {
        return round;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    public Optional<Player> getLoser() {
        return Optional.ofNullable(loser);
    }

    public boolean isTie() {
        return tie;
    }
}
