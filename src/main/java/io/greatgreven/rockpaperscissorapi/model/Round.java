package io.greatgreven.rockpaperscissorapi.model;

import java.util.Optional;

public class Round {
    private final int round;
    private final Player winner;
    private final Player loser;
    private final boolean tie;

    public Round(int round, int result, Player player1, Player player2) {
        this.round = round;
        this.winner = result > 0 ? player1 : result < 0 ? player2 : null;
        this.loser = result < 0 ? player1 : result > 0 ? player2 : null;
        this.tie = result == 0;
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
