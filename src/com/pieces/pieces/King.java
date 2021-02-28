package com.pieces.pieces;

import com.pieces.Move;
import com.pieces.MovePiece;
import com.pieces.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class King extends BasicPiece {

    public King(Position position, int color) {
        super(position, color);
        setKing(true);
        loadImg();
    }

    @Override
    public void move(Move action) {
        getBoard().addMove(action);
        updatePosition(new Position(action.getTargetPosition()));
    }

    @Override
    public ArrayList<Move> getPossibleMoves() {
        setMoves(new ArrayList<>());
        getMoves().add(new Move(getPosition(), new Position(getPosition(), 1, 0), MovePiece.KING));
        getMoves().add(new Move(getPosition(), new Position(getPosition(), 1, 1), MovePiece.KING));
        getMoves().add(new Move(getPosition(), new Position(getPosition(), 0, 1), MovePiece.KING));
        getMoves().add(new Move(getPosition(), new Position(getPosition(), 0, -1), MovePiece.KING));
        getMoves().add(new Move(getPosition(), new Position(getPosition(), -1, -1), MovePiece.KING));
        getMoves().add(new Move(getPosition(), new Position(getPosition(), -1, 0), MovePiece.KING));
        getMoves().add(new Move(getPosition(), new Position(getPosition(), -1, 1), MovePiece.KING));
        getMoves().add(new Move(getPosition(), new Position(getPosition(), 1, -1), MovePiece.KING));

        for (Move move : getMoves()) {
            move.setColor(getColor());
        }

        setMoves(getBoard().validateMoves(getMoves()));

        return getMoves();
    }

    @Override
    public String toString() {
        return "King";
    }

    @Override
    public void loadImg() {
        setImg(null);
        try {
            if (getColor() == WHITE)
                setImg(ImageIO.read(new File("./src/com/pieces/resources/Chess_klt60.png")));
            else if (getColor() == BLACK) {
                setImg(ImageIO.read(new File("./src/com/pieces/resources/Chess_kdt60.png")));
            }
        } catch (IOException e) {
//            File directory = new File("./");
//            System.out.println(directory.getAbsolutePath());
//            System.out.println("nu am gaasit calea!");
        }
    }
}
