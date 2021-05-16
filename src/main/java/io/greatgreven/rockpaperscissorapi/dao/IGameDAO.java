package io.greatgreven.rockpaperscissorapi.dao;

import io.greatgreven.rockpaperscissorapi.model.Game;
import io.greatgreven.rockpaperscissorapi.model.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IGameDAO {
    Game insertGame(String id, Player player);

    default Game addGame(Player player){
        return insertGame(UUID.randomUUID().toString(), player);
    }

    List<Game> getAllGames();

    Optional<Game> findGameById(String id);

    Optional<Game> updateGameById(String id, Game game);

    Optional<Game> replaceGameById(String id, Game game);

    Optional<Game> deleteGameById(String id, Game game);

}
