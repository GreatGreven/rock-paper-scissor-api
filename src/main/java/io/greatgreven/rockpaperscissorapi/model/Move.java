package io.greatgreven.rockpaperscissorapi.model;

public enum Move {
    Rock(0),
    Paper(1),
    Scissor(2);

    private final int value;

    Move(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isRock(){return this.value == Rock.getValue();}
    public boolean isPaper(){return  this.value == Paper.getValue();}
    public boolean isScissor(){return this.value == Scissor.getValue();}

    public static Move toMoveType(int value){
        Move move = null;
        switch (value) {
            case 0:
                move = Move.Rock;
            case 1:
                move = Move.Paper;
            case 2:
                move = Move.Scissor;
        }
        return move;
    }
}
