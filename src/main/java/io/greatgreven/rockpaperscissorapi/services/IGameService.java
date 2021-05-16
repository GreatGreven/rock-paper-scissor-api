package io.greatgreven.rockpaperscissorapi.services;

import io.greatgreven.rockpaperscissorapi.exception.GameNotFoundException;
import io.greatgreven.rockpaperscissorapi.model.Game;
import io.greatgreven.rockpaperscissorapi.model.Player;
import org.springframework.http.ResponseEntity;

public interface IGameService {
    Game createGame(Player player);
    Game joinGame(String id, Player player) throws GameNotFoundException;
    Game leaveGame(String id, Player player);
    Game makeMove(String id, Player player);
    Game showRound(String id, int index);
    Game showScore(String id);
    Game showGame(String id);
}
