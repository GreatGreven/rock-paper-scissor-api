package io.greatgreven.rockpaperscissorapi;

import io.greatgreven.rockpaperscissorapi.model.Move;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class MoveModelTest {

    //create or set
    @Test
    public void createMove(){
        Move move1 = Move.Rock,
             move2 = Move.Paper,
             move3 = Move.Scissor;
        //ROCK
        Assert.isTrue(move1.isRock(),"Move1 is Rock");
        Assert.isTrue(!move1.isPaper(),"Move1 is not Paper");
        Assert.isTrue(!move1.isScissor(),"Move1 is not Scissor");

        //PAPER
        Assert.isTrue(!move2.isRock(),"Move2 is not Rock");
        Assert.isTrue(move2.isPaper(),"Move2 is Paper");
        Assert.isTrue(!move2.isScissor(),"Move2 is not Scissor");

        //ROCK
        Assert.isTrue(!move3.isRock(),"Move3 is not Rock");
        Assert.isTrue(!move3.isPaper(),"Move3 is not Paper");
        Assert.isTrue(move3.isScissor(),"Move3 is Scissor");
    }
}
