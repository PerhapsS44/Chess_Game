package com.pieces.pieces;

import com.pieces.Move;
import com.pieces.MyObserver;
import com.pieces.Piece;
import com.pieces.Position;
import com.pieces.board.Board;

import java.awt.*;
import java.util.ArrayList;

public abstract class BasicPiece implements Piece, MyObservable {
    public static int WHITE = 1;
    public static int BLACK = 2;

    private ArrayList<Move> moves;
    private Position position;
    private Board board;
    private Image img;
    private int color;

    private boolean king = false;

    private boolean firstMove = true;

    public BasicPiece(Position position, int color) {
        this.position = position;
        this.color = color;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void updatePosition(Position position) {
        // call the update
        Position initialPosition = new Position(this.position);
        setPosition(position);
        notifyObserver(board, initialPosition, position);

    }

    public ArrayList<Move> getCorrectMoves() {
        return board.generateCorrectMoves(getPossibleMoves());
    }

    @Override
    public void notifyObserver(MyObserver observer, Position initialPosition, Position targetPosition) {
        observer.saveUpdate(initialPosition, targetPosition);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "BasicPiece{" +
                "moves=" + moves +
                ", position=" + position +
                '}';
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public abstract void loadImg();

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isKing() {
        return king;
    }

    public void setKing(boolean king) {
        this.king = king;
    }
}
