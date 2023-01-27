package io.greatgreven.rockpaperscissorapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.greatgreven.rockpaperscissorapi.exception.*;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {
    private UUID uuid;
    private static final int PLAYER_LIMIT = 2;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<Player> players;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private final List<Round> rounds;

    public Game() {
        this.uuid = UUID.randomUUID();
        this.players = new ArrayList<>();
        this.rounds = new ArrayList<>();
    }

    public Game(Player player) {
        this();
        this.addPlayer(player);
    }

    public Game(String id, Player player) {
        this(player);
        this.uuid = UUID.fromString(id);
    }

    public Game(UUID uuid, List<Player> players, List<Round> rounds) {
        this.uuid = uuid;
        this.players = players;
        this.rounds = rounds;
    }

    public synchronized Game addPlayer(Player player) {
        if (players.size() >= PLAYER_LIMIT) {
            throw new GameIsFullException(String.format("Game %s is full", uuid.toString()));
        } else if (players.stream().anyMatch(p -> player.getName().equals(p.getName()))) {
            throw new PlayerAlreadyJoinedException(
                    String.format("Player %s already in game", player.getName()));
        }
        players.add(player);
        return this.copy();
    }

    public synchronized Game removePlayer(String playerName) {
        if (players.isEmpty()) {
            throw new GameIsEmptyException(String.format("Game %s is empty", uuid.toString()));
        }
        if (players.stream().noneMatch(p -> playerName.equals(p.getName()))) {
            throw new PlayerNotInGameException(String.format("Player %s not in Game %s", playerName, uuid.toString()));
        }
        for (int i = 0; i < PLAYER_LIMIT; i++) {
            if (playerName.equals(players.get(i).getName())) {
                players.remove(i);
                break;
            }
        }
        return this.copy();
    }

    public synchronized Game makeMove(Player player) {
        Player ourPlayer = players
                .stream().
                filter(pl -> pl.getName().equals(player.getName()))
                .findFirst()
                .orElseThrow(
                        () -> new PlayerNotInGameException(
                                String.format(
                                        "Player %s not in game %s",
                                        player.getName(),
                                        uuid.toString())));
        Move move = player.getCheckMove()
                .orElseThrow(() ->
                        new MoveNotMadeException(
                                String.format(
                                        "Player %s can't make a null move",
                                        player.getName())));
        ourPlayer.makeMove(move);
        if (players.stream().allMatch(p -> p != null && p.hasMoved())) {
            return playRound();
        }
        return this.copy();
    }

    /**
     * @return a copy of the current game-state
     * @precondition players exist
     * @precondition players has moved
     * When round is played, made moves are compared and reset.
     * Then the scores are added and the played round is added to the list of previous rounds.
     */
    private Game playRound() {
        if (players.size() < PLAYER_LIMIT) {
            throw new GameNotFullException(
                    String.format(
                            "Game %s not full, can't play game yet.",
                            uuid.toString()));
        }
        Move player1Move = players.get(0)
                .getCheckMove()
                .orElseThrow(() ->
                        new MoveNotMadeException(
                                String.format(
                                        "Player %s aka %s has not made a move",
                                        players.get(0).getId().toString(),
                                        players.get(0).getName())));
        Move player2Move = players.get(1)
                .getCheckMove()
                .orElseThrow(() ->
                        new MoveNotMadeException(
                                String.format(
                                        "Player %s aka %s has not made a move",
                                        players.get(1).getId().toString(),
                                        players.get(1).getName())));

        //1. compare
        MoveComparator moveComparator = new MoveComparator();
        int result = moveComparator.compare(player1Move, player2Move);

        //2. add round
        this.addRound(players.get(0), players.get(1), result);

        //3. reset playermoves
        players.forEach(Player::resetMove);

        return this.copy();
    }

    private void addRound(Player winner, Player loser, int result) {
        int roundNumber = rounds.size() + 1;
        Round round = new Round(roundNumber, result, winner, loser);
        rounds.add(round);
    }

    private Game copy() {
        return new Game(uuid, players, rounds);
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}
