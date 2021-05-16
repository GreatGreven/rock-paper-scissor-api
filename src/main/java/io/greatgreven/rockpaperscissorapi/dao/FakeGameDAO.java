package io.greatgreven.rockpaperscissorapi.dao;


import io.greatgreven.rockpaperscissorapi.model.Game;
import io.greatgreven.rockpaperscissorapi.model.Player;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FakeGameDAO implements IGameDAO{
    @Override
    public Game insertGame(String id, Player player) {
        return null;
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public Optional<Game> findGameById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Game> updateGameById(String id, Game game) {
        return Optional.empty();
    }

    @Override
    public Optional<Game> replaceGameById(String id, Game game) {
        return Optional.empty();
    }

    @Override
    public Optional<Game> deleteGameById(String id, Game game) {
        return Optional.empty();
    }
}
