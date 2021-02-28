package com.pieces.pieces;

import com.pieces.Move;
import com.pieces.MovePiece;
import com.pieces.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Pawn extends BasicPiece {

    public Pawn(Position position, int color) {
        super(position, color);
        loadImg();
    }

    @Override
    public void move(Move action) {
        getBoard().addMove(action);
        updatePosition(new Position(action.getTargetPosition()));
        setFirstMove(false);
    }

    @Override
    public ArrayList<Move> getPossibleMoves() {
        setMoves(new ArrayList<>());
        if (getColor() == WHITE) {
            getMoves().add(new Move(getPosition(), new Position(getPosition(), 0, -1), MovePiece.PAWN));
            getMoves().add(new Move(getPosition(), new Position(getPosition(), -1, -1), MovePiece.PAWN));
            getMoves().add(new Move(getPosition(), new Position(getPosition(), 1, -1), MovePiece.PAWN));
            if (isFirstMove()) {
                getMoves().add(new Move(getPosition(), new Position(getPosition(), 0, -2), MovePiece.PAWN));
            }
        } else {
            getMoves().add(new Move(getPosition(), new Position(getPosition(), 0, 1), MovePiece.PAWN));
            getMoves().add(new Move(getPosition(), new Position(getPosition(), -1, 1), MovePiece.PAWN));
            getMoves().add(new Move(getPosition(), new Position(getPosition(), 1, 1), MovePiece.PAWN));
            if (isFirstMove()) {
                getMoves().add(new Move(getPosition(), new Position(getPosition(), 0, 2), MovePiece.PAWN));
            }
        }

        for (Move move : getMoves()) {
            move.setColor(getColor());
        }
        setMoves(getBoard().validateMoves(getMoves()));

        return getMoves();
    }

    @Override
    public String toString() {
        return "Pawn";
    }

    @Override
    public void loadImg() {
        setImg(null);
        try {
            if (getColor() == WHITE)
                setImg(ImageIO.read(new File("./src/com/pieces/resources/Chess_plt60.png")));
            else if (getColor() == BLACK) {
                setImg(ImageIO.read(new File("./src/com/pieces/resources/Chess_pdt60.png")));
            }
        } catch (IOException e) {
//            File directory = new File("./");
//            System.out.println(directory.getAbsolutePath());
//            System.out.println("nu am gaasit calea!");
        }
    }
}
