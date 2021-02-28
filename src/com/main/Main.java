package com.main;

import com.pieces.Position;
import com.pieces.board.Board;
import com.pieces.pieces.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame frame;
    public static Canvas canvas;

    public static void main(String[] args) {
        Board board = new Board();

        initialiseBoard(board);
        board.initialiseDisplay();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

//        bishop.move(bishop.getPossibleMoves().get(0));
//        board.showBoard(frame, canvas);
    }

    public static void initialiseBoard(Board board) {
        King wking = new King(new Position(5, 8), BasicPiece.WHITE);
        Queen wqueen = new Queen(new Position(4, 8), BasicPiece.WHITE);
        Rook wrook1 = new Rook(new Position(1, 8), BasicPiece.WHITE);
        Rook wrook2 = new Rook(new Position(8, 8), BasicPiece.WHITE);
        Bishop wbishop1 = new Bishop(new Position(3, 8), BasicPiece.WHITE);
        Bishop wbishop2 = new Bishop(new Position(6, 8), BasicPiece.WHITE);
        Knight wknight1 = new Knight(new Position(2, 8), BasicPiece.WHITE);
        Knight wknight2 = new Knight(new Position(7, 8), BasicPiece.WHITE);

        Pawn wpawn1 = new Pawn(new Position(1, 7), BasicPiece.WHITE);
        Pawn wpawn2 = new Pawn(new Position(2, 7), BasicPiece.WHITE);
        Pawn wpawn3 = new Pawn(new Position(3, 7), BasicPiece.WHITE);
        Pawn wpawn4 = new Pawn(new Position(4, 7), BasicPiece.WHITE);
        Pawn wpawn5 = new Pawn(new Position(5, 7), BasicPiece.WHITE);
        Pawn wpawn6 = new Pawn(new Position(6, 7), BasicPiece.WHITE);
        Pawn wpawn7 = new Pawn(new Position(7, 7), BasicPiece.WHITE);
        Pawn wpawn8 = new Pawn(new Position(8, 7), BasicPiece.WHITE);


        board.addPiece(wking);
        board.addPiece(wqueen);
        board.addPiece(wrook1);
        board.addPiece(wrook2);
        board.addPiece(wbishop1);
        board.addPiece(wbishop2);
        board.addPiece(wknight1);
        board.addPiece(wknight2);

        board.addPiece(wpawn1);
        board.addPiece(wpawn2);
        board.addPiece(wpawn3);
        board.addPiece(wpawn4);
        board.addPiece(wpawn5);
        board.addPiece(wpawn6);
        board.addPiece(wpawn7);
        board.addPiece(wpawn8);

        King bking = new King(new Position(5, 1), BasicPiece.BLACK);
        Queen bqueen = new Queen(new Position(4, 1), BasicPiece.BLACK);
        Rook brook1 = new Rook(new Position(1, 1), BasicPiece.BLACK);
        Rook brook2 = new Rook(new Position(8, 1), BasicPiece.BLACK);
        Bishop bbishop1 = new Bishop(new Position(3, 1), BasicPiece.BLACK);
        Bishop bbishop2 = new Bishop(new Position(6, 1), BasicPiece.BLACK);
        Knight bknight1 = new Knight(new Position(2, 1), BasicPiece.BLACK);
        Knight bknight2 = new Knight(new Position(7, 1), BasicPiece.BLACK);

        Pawn bpawn1 = new Pawn(new Position(1, 2), BasicPiece.BLACK);
        Pawn bpawn2 = new Pawn(new Position(2, 2), BasicPiece.BLACK);
        Pawn bpawn3 = new Pawn(new Position(3, 2), BasicPiece.BLACK);
        Pawn bpawn4 = new Pawn(new Position(4, 2), BasicPiece.BLACK);
        Pawn bpawn5 = new Pawn(new Position(5, 2), BasicPiece.BLACK);
        Pawn bpawn6 = new Pawn(new Position(6, 2), BasicPiece.BLACK);
        Pawn bpawn7 = new Pawn(new Position(7, 2), BasicPiece.BLACK);
        Pawn bpawn8 = new Pawn(new Position(8, 2), BasicPiece.BLACK);


        board.addPiece(bking);
        board.addPiece(bqueen);
        board.addPiece(brook1);
        board.addPiece(brook2);
        board.addPiece(bbishop1);
        board.addPiece(bbishop2);
        board.addPiece(bknight1);
        board.addPiece(bknight2);

        board.addPiece(bpawn1);
        board.addPiece(bpawn2);
        board.addPiece(bpawn3);
        board.addPiece(bpawn4);
        board.addPiece(bpawn5);
        board.addPiece(bpawn6);
        board.addPiece(bpawn7);
        board.addPiece(bpawn8);
    }
}
