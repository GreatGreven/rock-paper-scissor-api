package io.greatgreven.rockpaperscissorapi.dao;


import io.greatgreven.rockpaperscissorapi.exception.GameNotFoundException;
import io.greatgreven.rockpaperscissorapi.model.Game;
import io.greatgreven.rockpaperscissorapi.model.Player;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeGameDAO implements IGameDAO{
    private static final List<Game> fakeDatabase = new ArrayList<>();

    @Override
    public Game insertGame(String id, Player player) {
        Game game = new Game(id, player);
        fakeDatabase.add(game);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        return fakeDatabase;
    }

    @Override
    public Game findGameById(String id) {
        return fakeDatabase
                .stream()
                .filter(game -> game.getUUID().toString().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new GameNotFoundException(
                                String.format("Game %s not found", id))
                );
    }

    @Override
    public synchronized Optional<Game> updateGameById(String id, Game game) {
        return Optional.empty();
    }

    @Override
    public synchronized Optional<Game> replaceGameById(String id, Game game) {
        return Optional.empty();
    }

    @Override
    public Optional<Game> deleteGameById(String id, Game game) {
        return Optional.empty();
    }
}
