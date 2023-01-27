package io.greatgreven.rockpaperscissorapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.greatgreven.rockpaperscissorapi.filters.TieConditionFilter;

import java.util.Optional;

public class Round {
    private final int round;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String winner;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String loser;
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = TieConditionFilter.class)
    private final Boolean tie;

    public Round(int round, int result, Player player1, Player player2) {
        this.round = round;
        this.winner = result > 0 ? player1.getName() : result < 0 ? player2.getName() : "";
        this.loser = result < 0 ? player1.getName() : result > 0 ? player2.getName() : "";
        this.tie = result == 0;
    }

    public int getRound() {
        return round;
    }

    public String getWinner() {
        return winner;
    }

    public String getLoser() {
        return loser;
    }

    public boolean isTie() {
        return tie;
    }
}
