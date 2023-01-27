package io.greatgreven.rockpaperscissorapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import io.greatgreven.rockpaperscissorapi.exception.MoveMadeException;
import io.greatgreven.rockpaperscissorapi.model.Move;
import io.greatgreven.rockpaperscissorapi.model.Player;

@SpringBootTest
public class PlayerModelTest {
    private Player player;
    @BeforeEach
    public void init(){
        player = new Player();        
    }

    //name
    @Test 
    public void nameAndID(){
        player.setName("John");
        Assert.isTrue(player.getName().equals("John"), "Players name is not 'John'");
        
        String oldID = player.getId().toString();
        player = new Player("Lisa");
        Assert.isTrue(player.getName().equals("Lisa"), "Players name is not 'Lisa'");
        Assert.isTrue(!player.getName().equals("John"), "Players name is 'John'");
        Assert.isTrue(!player.getId().toString().equals(oldID), "New ID (== Old ID) is not new");
    }

    //move
    @Test
    public void move(){
        nameAndID();
        player.makeMove(Move.Rock);
        Assert.isTrue(
            player.getCheckMove().get().isRock(), String.format(
                "%s's move is %s and not %s", player.getName(), player.getCheckMove(), Move.Rock));
        Assert.isTrue(
            !player.getCheckMove().get().isPaper(), String.format(
                "%s's move is %s", player.getName(), player.getCheckMove()));
        Assert.isTrue(
            !player.getCheckMove().get().isScissor(), String.format(
                "%s's move is %s", player.getName(), player.getCheckMove()));

        //Change to paper
        try {
            player.makeMove(Move.Paper);
        } catch (Exception e){
            Assert.isTrue(e instanceof MoveMadeException, String.format("Exception %s is not MoveMadeException.", e.toString()));
        }
    }

}
