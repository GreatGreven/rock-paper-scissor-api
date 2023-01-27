package io.greatgreven.rockpaperscissorapi.services;

import io.greatgreven.rockpaperscissorapi.exception.GameNotFoundException;
import io.greatgreven.rockpaperscissorapi.model.Game;
import io.greatgreven.rockpaperscissorapi.model.Player;
import io.greatgreven.rockpaperscissorapi.model.Round;
import org.springframework.http.ResponseEntity;

public interface IGameService {
    Game createGame(Player player);
    Game joinGame(String id, Player player) throws GameNotFoundException;
    Game leaveGame(String id, String playerName);
    Game makeMove(String id, Player player);
    Round showRound(String id, int index);
    Game showScore(String id);
    Game showGame(String id);
}
