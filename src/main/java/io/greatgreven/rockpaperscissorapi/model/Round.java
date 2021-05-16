package io.greatgreven.rockpaperscissorapi.model;

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

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

    public boolean isTie() {
        return tie;
    }
}
