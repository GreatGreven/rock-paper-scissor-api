package io.greatgreven.rockpaperscissorapi.services;

import io.greatgreven.rockpaperscissorapi.dao.IGameDAO;
import io.greatgreven.rockpaperscissorapi.model.Game;
import io.greatgreven.rockpaperscissorapi.model.Player;
import io.greatgreven.rockpaperscissorapi.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService{
    @Autowired
    private IGameDAO gameDAO;

    @Override
    public Game createGame(Player player) {
        return gameDAO.addGame(player);
    }

    @Override
    public Game joinGame(String id, Player player) {
        return gameDAO.findGameById(id).addPlayer(player);
    }

    @Override
    public Game leaveGame(String id, String playerName) {
        return gameDAO.findGameById(id).removePlayer(playerName);
    }

    @Override
    public Game makeMove(String id, Player player) {
        return gameDAO.findGameById(id).makeMove(player);
    }

    @Override
    public Round showRound(String id, int roundIndex) {
        return gameDAO.findGameById(id).getRounds().get(roundIndex);
    }

    @Override
    public Game showScore(String id) {
        return null;
    }

    @Override
    public Game showGame(String id) {
        return gameDAO.findGameById(id);
    }
}
