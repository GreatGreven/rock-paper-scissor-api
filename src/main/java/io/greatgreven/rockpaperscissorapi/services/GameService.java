package io.greatgreven.rockpaperscissorapi.services;

import io.greatgreven.rockpaperscissorapi.dao.IGameDAO;
import io.greatgreven.rockpaperscissorapi.exception.GameNotFoundException;
import io.greatgreven.rockpaperscissorapi.model.Game;
import io.greatgreven.rockpaperscissorapi.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        HttpStatus status = HttpStatus.OK;
        Game game = gameDAO.findGameById(id)
                .orElseThrow(() ->
                        new GameNotFoundException(
                                String.format("Game %s not found", id)))
                .addPlayer(player);
        return game;
    }

    @Override
    public Game leaveGame(String id, Player player) {
        Game game = gameDAO.findGameById(id)
                .orElseThrow(() ->
                        new GameNotFoundException(
                                String.format("Game %s not found", id)))
                .removePlayer(player);
        return game;
    }

    @Override
    public Game makeMove(String id, Player player) {
        return null;
    }

    @Override
    public Game showRound(String id, int roundIndex) {
        return null;
    }

    @Override
    public Game showScore(String id) {
        return null;
    }

    @Override
    public Game showGame(String id) {
        return null;
    }
}
