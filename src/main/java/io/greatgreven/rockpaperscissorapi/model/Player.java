package io.greatgreven.rockpaperscissorapi.model;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.greatgreven.rockpaperscissorapi.exception.MoveMadeException;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private UUID id;
    private String name;
    @JsonIgnore
    private Move move;
    private int score;

    public Player(){
        super();
        id = UUID.randomUUID();
    }

    public Player(String name){
        this();
        this.name = name;
    }

    private Player(UUID id, String name, Move move){
        this.id = id;
        this.name = name;
        this.move = move;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void makeMove(Move move) {
        Optional.ofNullable(move).ifPresentOrElse(
                input -> Optional.ofNullable(this.move).ifPresentOrElse(
                        m -> {throw new MoveMadeException(
                                String.format(
                                        "%s has already made %s and can't change it to %s.",
                                        name,
                                        this.move,
                                        move));},
                        () -> setMove(input)),
                () -> setMove(move));
    }

    public void resetMove(){
        this.makeMove(null);
    }

    private void setMove(Move move) {
        this.move = move;
    }

    @JsonIgnore
    public Optional<Move> getCheckMove() {
        return Optional.ofNullable(move);
    }

    public Move getMove() {
        return move;
    }

    public boolean hasMoved(){
        return move != null;
    }

    public void incrementScore(){
        score++;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Player) && ((Player) obj).id.toString().equals(this.id.toString());
    }

    public Player copy(){
        return new Player(id, name, move);
    }
}
