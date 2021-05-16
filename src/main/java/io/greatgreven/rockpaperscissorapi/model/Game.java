package io.greatgreven.rockpaperscissorapi.model;

import io.greatgreven.rockpaperscissorapi.exception.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Game implements Serializable {
    private UUID uuid;
    private static final int PLAYER_LIMIT = 2;
    private final Optional<Player>[] players;
    private List<Round> rounds;

    public Game() {
        this.uuid = UUID.randomUUID();
        this.players = new Optional[PLAYER_LIMIT];
        Arrays.stream(players).forEach(player -> player = Optional.empty());
    }

    public Game(Player player) {
        this.players = new Optional[PLAYER_LIMIT];
        this.addPlayer(player);
    }

    public Game(String id, Player player) {
        this(player);
        this.uuid = UUID.fromString(id);
    }

    public Game(UUID uuid, Optional<Player>[] players, List<Round> rounds) {
        this.uuid = uuid;
        this.players = players;
        this.rounds = rounds;
    }

    public synchronized Game addPlayer(Player player) {
        if (players[0].isEmpty()) {
            players[0] = Optional.ofNullable(player);
        } else if (players[1].isEmpty()) {
            players[1] = Optional.ofNullable(player);
        } else {
            throw new GameIsFullException(String.format("Game %s is full", uuid.toString()));
        }
        return this.copy();
    }

    public synchronized Game removePlayer(Player player) {
        if (Arrays.stream(players).allMatch(Optional::isEmpty)) {
            throw new GameIsEmptyException(String.format("Game %s is empty", uuid.toString()));
        }
        if (Arrays.stream(players).noneMatch(p -> p.isPresent() && player.equals(p.get()))) {
            throw new PlayerNotInGameException(String.format("Player %s aka %s not in Game %s", player.getId().toString(), player.getName(), uuid.toString()));
        }
        for (Optional<Player> p : players) {
            if (p.isPresent() && p.get().equals(player)) {
                p = Optional.empty();
                break;
            }
        }
        return this.copy();
    }

    public synchronized Game makeMove(Player player) {
        if (Arrays.stream(players).noneMatch(p -> p.isPresent() && player.equals(p.get()))) {
            throw new PlayerNotInGameException(
                    String.format(
                            "Player %s aka %s not in Game %s",
                            player.getId().toString(),
                            player.getName(),
                            uuid.toString()));
        }
        Move move = player.getMove()
                .orElseThrow(() ->
                        new MoveNotMadeException(
                                String.format(
                                        "Player %s aka %s has not made a move",
                                        player.getId().toString(),
                                        player.getName())));
        player.makeMove(move);
        if (Arrays.stream(players).allMatch(p -> p.isPresent() && p.get().hasMoved())) {
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
        Move player1Move = players[0].orElseThrow(() ->
                new GameNotFullException(
                        String.format(
                                "Game %s not full, can't play game yet.",
                                uuid.toString())))
                .getMove()
                .orElseThrow(() ->
                        new MoveNotMadeException(
                                String.format(
                                        "Player %s aka %s has not made a move",
                                        players[0].get().getId().toString(),
                                        players[0].get().getName())));
        Move player2Move = players[1].orElseThrow(() ->
                new GameNotFullException(
                        String.format(
                                "Game %s not full, can't play game yet.",
                                uuid.toString())))
                .getMove()
                .orElseThrow(() ->
                        new MoveNotMadeException(
                                String.format(
                                        "Player %s aka %s has not made a move",
                                        players[1].get().getId().toString(),
                                        players[1].get().getName())));

        //1. compare
        MoveComparator moveComparator = new MoveComparator();
        int result = moveComparator.compare(player1Move, player2Move);

        //2. add round
        if (result > 0) { //player1 wins
            this.addRound(
                    players[0].orElseThrow(() ->
                            new GameNotFullException(
                                    String.format(
                                            "Game %s not full, can't play game yet.",
                                            uuid.toString()))),
                    players[1].orElseThrow(() ->
                            new GameNotFullException(
                                    String.format(
                                            "Game %s not full, can't play game yet.",
                                            uuid.toString()))),
                    false);
        } else if (result < 0) { //player2 wins
            this.addRound(players[1].orElseThrow(() ->
                    new GameNotFullException(
                            String.format(
                                    "Game %s not full, can't play game yet.",
                                    uuid.toString()))),
                    players[0].orElseThrow(() ->
                            new GameNotFullException(
                                    String.format(
                                            "Game %s not full, can't play game yet.",
                                            uuid.toString()))),
                    false);
        } else { //tie
            this.addRound(null, null, true);
        }

        //3. reset playermoves
        Arrays.stream(players).forEach(player -> player.ifPresent(Player::resetMove));

        return this.copy();
    }

    private void addRound(Player winner, Player loser, boolean tie) {
        int roundNumber = rounds.size() + 1;
        Round round = new Round(roundNumber, tie, winner, loser);
        rounds.add(round);
    }

    private Game copy() {
        return new Game(uuid, players, rounds);
    }

}
