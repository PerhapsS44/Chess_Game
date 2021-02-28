package com.pieces.pieces;

import com.pieces.MyObserver;
import com.pieces.Position;
import com.pieces.board.Board;

public interface MyObservable {
    void notifyObserver(MyObserver observer, Position initialPosition, Position targetPosition);

    void setBoard(Board board);
}
