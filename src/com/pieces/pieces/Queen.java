package com.pieces.pieces;

import com.pieces.Move;
import com.pieces.MovePiece;
import com.pieces.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Queen extends BasicPiece {

    public Queen(Position position, int color) {
        super(position, color);
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

        Position dirrection = new Position(1, 1);
        Position movePos = new Position(getPosition());
        for (int i = 0; i < 8; i++) {
            getMoves().add(new Move(getPosition(), new Position(movePos, dirrection.getX(), dirrection.getY()), MovePiece.DIAGONALLY));
            movePos.add(dirrection);
        }

        dirrection = new Position(1, -1);
        movePos = new Position(getPosition());
        for (int i = 0; i < 8; i++) {
            getMoves().add(new Move(getPosition(), new Position(movePos, dirrection.getX(), dirrection.getY()), MovePiece.DIAGONALLY));
            movePos.add(dirrection);
        }

        dirrection = new Position(-1, 1);
        movePos = new Position(getPosition());
        for (int i = 0; i < 8; i++) {
            getMoves().add(new Move(getPosition(), new Position(movePos, dirrection.getX(), dirrection.getY()), MovePiece.DIAGONALLY));
            movePos.add(dirrection);
        }

        dirrection = new Position(-1, -1);
        movePos = new Position(getPosition());
        for (int i = 0; i < 8; i++) {
            getMoves().add(new Move(getPosition(), new Position(movePos, dirrection.getX(), dirrection.getY()), MovePiece.DIAGONALLY));
            movePos.add(dirrection);
        }

        dirrection = new Position(0, 1);
        movePos = new Position(getPosition());
        for (int i = 0; i < 8; i++) {
            getMoves().add(new Move(getPosition(), new Position(movePos, dirrection.getX(), dirrection.getY()), MovePiece.CROSS));
            movePos.add(dirrection);
        }

        dirrection = new Position(0, -1);
        movePos = new Position(getPosition());
        for (int i = 0; i < 8; i++) {
            getMoves().add(new Move(getPosition(), new Position(movePos, dirrection.getX(), dirrection.getY()), MovePiece.CROSS));
            movePos.add(dirrection);
        }

        dirrection = new Position(1, 0);
        movePos = new Position(getPosition());
        for (int i = 0; i < 8; i++) {
            getMoves().add(new Move(getPosition(), new Position(movePos, dirrection.getX(), dirrection.getY()), MovePiece.CROSS));
            movePos.add(dirrection);
        }

        dirrection = new Position(-1, 0);
        movePos = new Position(getPosition());
        for (int i = 0; i < 8; i++) {
            getMoves().add(new Move(getPosition(), new Position(movePos, dirrection.getX(), dirrection.getY()), MovePiece.CROSS));
            movePos.add(dirrection);
        }

        for (Move move : getMoves()) {
            move.setColor(getColor());
        }

//        System.out.println(getMoves());

        setMoves(getBoard().validateMoves(getMoves()));

        return getMoves();
    }

    @Override
    public String toString() {
        return "Queen";
    }

    @Override
    public void loadImg() {
        setImg(null);
        try {
            if (getColor() == WHITE)
                setImg(ImageIO.read(new File("./src/com/pieces/resources/Chess_qlt60.png")));
            else if (getColor() == BLACK) {
                setImg(ImageIO.read(new File("./src/com/pieces/resources/Chess_qdt60.png")));
            }
        } catch (IOException e) {
//            File directory = new File("./");
//            System.out.println(directory.getAbsolutePath());
//            System.out.println("nu am gaasit calea!");
        }
    }
}
