package io.greatgreven.rockpaperscissorapi.controllers;

import io.greatgreven.rockpaperscissorapi.model.Game;
import io.greatgreven.rockpaperscissorapi.model.Player;
import io.greatgreven.rockpaperscissorapi.model.Round;
import io.greatgreven.rockpaperscissorapi.requests.MoveRequest;
import io.greatgreven.rockpaperscissorapi.requests.PlayerRequest;
import io.greatgreven.rockpaperscissorapi.responses.GameCreationResponse;
import io.greatgreven.rockpaperscissorapi.services.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GameController.BASE_URL)
public class GameController {
    @Autowired
    private IGameService gameService;
    public static final String BASE_URL = "/api/games/rochambo";
    
    //create game
    @PostMapping()
    public @ResponseBody ResponseEntity<?> createGame(@RequestBody PlayerRequest body){
        Game game = gameService.createGame(new Player(body.getName()));
        return new ResponseEntity<GameCreationResponse>(
                new GameCreationResponse(
                        String.format("%s/%s/join", BASE_URL, game.getUUID().toString())),
                HttpStatus.OK);
    }

    //join
    @PutMapping("/{id}/join")
    public @ResponseBody ResponseEntity<?> joinGame(@PathVariable String id, @RequestBody PlayerRequest body){
        Game game = gameService.joinGame(id, new Player(body.getName()));
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    //leave
    @PutMapping("/{id}/leave")
    public @ResponseBody ResponseEntity<?> leaveGame (@PathVariable String id, @RequestBody PlayerRequest body){
        Game game = gameService.leaveGame(id, body.getName());
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    //move
    @PutMapping("/{id}/move")
    public @ResponseBody ResponseEntity<?> makeMove(@PathVariable String id, @RequestBody MoveRequest body){
        Player player = new Player(body.getName());
        player.makeMove(body.getMove());
        Game game = gameService.makeMove(id, player);
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    //show round
    @GetMapping("/{id}/round/{roundIndex}")
    public @ResponseBody ResponseEntity<?> showRound(@PathVariable String id, @PathVariable int roundIndex){
        Round round = gameService.showRound(id, roundIndex);
        return new ResponseEntity<Round>(round, HttpStatus.OK);
    }

    //show score
    @GetMapping("/{id}/score")
    public @ResponseBody ResponseEntity<?> showScore(@PathVariable String id){
        Game game = gameService.showScore(id);
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    //show game
    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<?> showGame(@PathVariable String id){
        Game game = gameService.showGame(id);
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }
}
