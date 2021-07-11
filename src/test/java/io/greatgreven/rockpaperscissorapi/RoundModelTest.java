package io.greatgreven.rockpaperscissorapi;

import io.greatgreven.rockpaperscissorapi.    model.MoveComparator;
import io.greatgreven.rockpaperscissorapi.model.Move;
import io.greatgreven.rockpaperscissorapi.model.Round;
import io.greatgreven.rockpaperscissorapi.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class RoundModelTest {
    MoveComparator comparator;
    Player player1, player2;
    @BeforeEach
    public void init(){
        player1 = new Player("John");
        player2 = new Player("Jane");
        comparator = new MoveComparator();
    }

    // player1 wins
    @Test
    public void player1Wins(){
        player1.makeMove(Move.Rock);
        player2.makeMove(Move.Scissor);
        int result = comparator.compare(player1.getMove().get(), player2.getMove().get());
        Assert.isTrue(result > 0, String.format("Player1: %s didn't beat %s", player1.getName(), player2.getName()));
        Round round = new Round(1, result == 0, result > 0 ? player1 : result < 0 ? player2 : null, result > 0 ? player2 : result < 0 ? player1 : null);
        Assert.state(!round.isTie(), String.format("Round %s is tie", round.getRound()));
        Assert.state(round.getWinner().isPresent() && round.getWinner().get().equals(player1), String.format("%s didn't win round %s", player1.getName(), round.getRound()));
        Assert.state(round.getLoser().isPresent() && round.getLoser().get().equals(player2), String.format("%s didn't lose round %s", player2.getName(), round.getRound()));
    }
    
    // player2 wins
    @Test
    public void player2Wins(){
        player1.makeMove(Move.Paper);
        player2.makeMove(Move.Scissor);
        int result = comparator.compare(player1.getMove().get(), player2.getMove().get());
        Assert.isTrue(result < 0, String.format("Player2: %s didn't beat %s", player2.getName(), player1.getName()));
        Round round = new Round(1, result == 0, result > 0 ? player1 : result < 0 ? player2 : null, result > 0 ? player2 : result < 0 ? player1 : null);
        Assert.state(!round.isTie(), String.format("Round %s is tie", round.getRound()));
        Assert.state(round.getWinner().isPresent() && round.getWinner().get().equals(player2), String.format("%s didn't win round %s", player2.getName(), round.getRound()));
        Assert.state(round.getLoser().isPresent() && round.getLoser().get().equals(player1), String.format("%s didn't lose round %s", player1.getName(), round.getRound()));

    }
    
    // tie
    public void tie(){
        player1.makeMove(Move.Rock);
        player2.makeMove(Move.Rock);
        int result = comparator.compare(player1.getMove().get(), player2.getMove().get());
        Assert.isTrue(result == 0, "Tie: didn't tie");
        Round round = new Round(3, result == 0, result > 0 ? player1 : result < 0 ? player2 : null, result > 0 ? player2 : result < 0 ? player1 : null);
        Assert.state(round.isTie(), String.format("Round %s is not a tie", round.getRound()));
        Assert.state(round.getWinner().isEmpty(), String.format("%s won round %s", round.getWinner().get().getName(), round.getRound()));
        Assert.state(round.getLoser().isEmpty(), String.format("%s lost round %s", round.getLoser().get().getName(), round.getRound()));

    }

}
