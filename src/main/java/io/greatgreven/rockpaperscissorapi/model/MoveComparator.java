package io.greatgreven.rockpaperscissorapi.model;

import java.util.Comparator;

/**
 * Class that compare Moves and calculates the winner
 */
public class MoveComparator implements Comparator<Move>{
    private int OPTIONS = Move.values().length;

    /**
     * Compares two moves based on the amount of options available,
     * in other words it's comparable for expansion of moves.
     * @param a move to be compared
     * @param b move to be compared
     * @return 0 == tie, 1 == a wins, -1 b wins 
     */
    @Override
    public int compare(Move a, Move b) {
        int res = (OPTIONS + a.getValue() - b.getValue()) % OPTIONS;
        res = res % 2 == 0 && res != 0 ? -1 : res;
        return res;
    }
}
