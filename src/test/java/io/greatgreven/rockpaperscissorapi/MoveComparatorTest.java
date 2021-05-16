package io.greatgreven.rockpaperscissorapi;

import io.greatgreven.rockpaperscissorapi.controllers.GameController;
import io.greatgreven.rockpaperscissorapi.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import io.greatgreven.rockpaperscissorapi.model.Move;
import io.greatgreven.rockpaperscissorapi.model.MoveComparator;

@SpringBootTest
@AutoConfigureMockMvc
public class MoveComparatorTest {
    MoveComparator compare;

    @BeforeEach
    public void init(){
        compare = new MoveComparator();
    }

    @Test
    public void rockBeatsScissor(){
        Move rock = Move.Rock,
            scissor = Move.Scissor;
        Assert.isTrue(compare.compare(rock, scissor) > 0, "rock: a didn't beat b");
        Assert.isTrue(compare.compare(scissor, rock) < 0, "rock: b didn't beat a");
    }

    @Test
    public void paperBeatsRock(){
        Move rock = Move.Rock,
            paper = Move.Paper;
        Assert.isTrue(compare.compare(paper, rock) > 0, "paper: a didn't beat b");
        Assert.isTrue(compare.compare(rock, paper) < 0, "paper: b didn't beat a");
    }

    @Test
    public void scissorBeatsPaper(){
        Move paper = Move.Paper,
            scissor = Move.Scissor;
        Assert.isTrue(compare.compare(scissor, paper) > 0, "scissor: a didn't beat b");
        Assert.isTrue(compare.compare(paper, scissor) < 0, "scissor: b didn't beat a");
    }

}
