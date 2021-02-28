package com.pieces;

import java.util.ArrayList;
import java.util.List;

public interface Piece {
    void move(Move action);

    ArrayList<Move> getPossibleMoves();

    List<Move> getCorrectMoves();
}
