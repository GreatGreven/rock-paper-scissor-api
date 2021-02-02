package io.greatgreven.rockpaperscissorapi.model;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import io.greatgreven.rockpaperscissorapi.exception.MoveMadeException;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String name;
    private Move move;

    public Player(){
        super();
        id = UUID.randomUUID();
    }

    public Player(String name){
        this();
        this.name = name;
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

    private void setMove(Move move) {
        this.move = move;
    }

    public Optional<Move> getMove() {
        return Optional.ofNullable(move);
    }

    public boolean hasMoved(){
        return move != null;
    }

}
