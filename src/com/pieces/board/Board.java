package com.pieces.board;

import com.pieces.Move;
import com.pieces.MovePiece;
import com.pieces.MyObserver;
import com.pieces.Position;
import com.pieces.pieces.BasicPiece;
import com.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board implements MyObserver {
    private final MouseHandler mouseHandler;
    private final ArrayList<Move> moveHistory;
    boolean pieceClicked = false;
    private HashMap<Position, BasicPiece> layout;
    private JFrame frame;
    private JPanel canvas;
    private BasicPiece selectedPiece;
    private int colorTurn = BasicPiece.WHITE;
    private boolean check = false;
    private final boolean mate = false;

    public Board() {
        layout = new HashMap<>();
        mouseHandler = new MouseHandler();
        moveHistory = new ArrayList<>();
    }

    public HashMap<Position, BasicPiece> getLayout() {
        return layout;
    }

    public void setLayout(HashMap<Position, BasicPiece> layout) {
        this.layout = layout;
    }

    public BasicPiece getPiece(Position position) {
        return layout.get(position);
    }

    public boolean existsPiece(Position position) {
        return layout.containsKey(position);
    }

    public void movePiece(Position position, BasicPiece piece) {
        layout.put(position, piece);
    }

    public void addPiece(BasicPiece piece) {
        piece.setBoard(this);
        layout.put(piece.getPosition(), piece);
    }

    private void changeTurn() {
        colorTurn = colorTurn == BasicPiece.WHITE ? BasicPiece.BLACK : BasicPiece.WHITE;
    }

    public void removePiece(Position position) {
        layout.remove(position);
    }

    @Override
    public void saveUpdate(Position initialPosition, Position targetPosition) {
        BasicPiece piece = getPiece(initialPosition);
        removePiece(initialPosition);
        movePiece(targetPosition, piece);

        changeTurn();

        checkGameCondition();

        canvas.repaint();

    }

    /**
     * This method checks if there is a Check or a mate and responds to this
     */
    private void checkGameCondition() {
        ArrayList<Move> allMoves = new ArrayList<>();
        ArrayList<BasicPiece> pieces = new ArrayList<>();
        check = false;
        if (colorTurn == BasicPiece.WHITE)
            System.out.println("White turn");
        else
            System.out.println("Black turn");
        for (Map.Entry<Position, BasicPiece> entry : layout.entrySet()) {
            if (entry.getValue().getColor() != colorTurn) {
                pieces.add(entry.getValue());
            }
        }

        for (BasicPiece piece : pieces) {
            allMoves.addAll(piece.getCorrectMoves());
        }

        for (Move move : allMoves) {
            if (existsPiece(move.getTargetPosition())) {
                if (getPiece(move.getTargetPosition()).isKing()) {
                    // check
                    System.out.println("check!");
                    check = true;
                }
            }
        }
        if (check) {
            pieces.clear();
            allMoves.clear();
            for (Map.Entry<Position, BasicPiece> entry : layout.entrySet()) {
                if (entry.getValue().getColor() == colorTurn) {
                    pieces.add(entry.getValue());
                }
            }
            for (BasicPiece piece : pieces) {
                allMoves.addAll(piece.getCorrectMoves());
            }
            if (allMoves.size() == 0) {
                System.out.println("Game Over!");
            }
        }
    }

    public void printBoard() {
        System.out.println(layout);
    }

    public ArrayList<Move> validateMoves(ArrayList<Move> initialMoves) {
        ArrayList<Move> correctMoves = new ArrayList<>();
        for (Move move : initialMoves) {
            if (move.getTargetPosition().getX() < 1 || move.getTargetPosition().getX() > Constants.NO_CELLS)
                continue;
            if (move.getTargetPosition().getY() < 1 || move.getTargetPosition().getY() > Constants.NO_CELLS)
                continue;
            if (move.getDirrection().compareTo(MovePiece.PAWN) == 0) {
                if (existsPiece(move.getTargetPosition()) &&
                        getPiece(move.getTargetPosition()).getColor() != move.getColor() &&
                        move.getTargetPosition().getX() != move.getInitialPosition().getX()) {
                    correctMoves.add(move);
                } else if (!existsPiece(move.getTargetPosition()) &&
                        move.getTargetPosition().getX() == move.getInitialPosition().getX()) {
                    correctMoves.add(move);
                }
            } else {
                if (move.getDirrection().compareTo(MovePiece.CROSS) == 0 ||
                        move.getDirrection().compareTo(MovePiece.DIAGONALLY) == 0) {
                    Position goingDirection = new Position(move.getTargetPosition().getX() -
                            move.getInitialPosition().getX(), move.getTargetPosition().getY() -
                            move.getInitialPosition().getY());
                    if (goingDirection.getX() > 0) {
                        goingDirection.setX(1);
                    }
                    if (goingDirection.getX() < 0) {
                        goingDirection.setX(-1);
                    }
                    if (goingDirection.getY() > 0) {
                        goingDirection.setY(1);
                    }
                    if (goingDirection.getY() < 0) {
                        goingDirection.setY(-1);
                    }

                    // now we have a normalised direction

                    Position movingPosition = new Position(move.getInitialPosition(), goingDirection.getX(), goingDirection.getY());
                    while (!movingPosition.equals(move.getTargetPosition()) && !existsPiece(movingPosition)) {
                        movingPosition.add(goingDirection);
                    }
                    if (!movingPosition.equals(move.getTargetPosition())) {
                        continue;
                    }
                }
                if (!existsPiece(move.getTargetPosition())) {
                    correctMoves.add(move);
                }
                if (existsPiece(move.getTargetPosition()) && getPiece(move.getTargetPosition()).getColor() != move.getColor()) {
                    correctMoves.add(move);
                }
            }
        }
        return correctMoves;
    }

    public ArrayList<Move> generateCorrectMoves(ArrayList<Move> validatedMoves) {
        ArrayList<Move> correctMoves = new ArrayList<>();
        ArrayList<Move> possibleNewMoves = new ArrayList<>();
        BasicPiece piece;
        HashMap<Position, BasicPiece> copyLayout = new HashMap<>(layout);
        for (Move move : validatedMoves) {
            boolean possibleMate = false;
//            // do sth

            piece = getPiece(move.getInitialPosition());
            piece.setPosition(new Position(move.getTargetPosition()));
            removePiece(move.getInitialPosition());
            movePiece(new Position(move.getTargetPosition()), piece);
//
            for (Map.Entry<Position, BasicPiece> entry : layout.entrySet()) {
                if (entry.getValue().getColor() != colorTurn)
                    possibleNewMoves.addAll(entry.getValue().getPossibleMoves());
            }
            for (Move possibleMove : possibleNewMoves) {
                if (existsPiece(possibleMove.getTargetPosition())) {
                    if (getPiece(possibleMove.getTargetPosition()).isKing()) {
                        possibleMate = true;
                        break;
                    }
                }
            }

            if (!possibleMate) {
                correctMoves.add(move);
            }

            piece.setPosition(new Position(move.getInitialPosition()));
            restoreBoard(copyLayout);


            possibleNewMoves.clear();
//
        }
//        correctMoves.addAll(validatedMoves);
//        if (correctMoves.size() == 0) {
//            //it's mate
//            mate = true;
//        }
        return correctMoves;
    }

    public void restoreBoard(HashMap<Position, BasicPiece> copy) {
        layout.clear();
        layout.putAll(copy);
    }

    public void initialiseDisplay() {
        frame = new JFrame("My Drawing");
        canvas = new Drawing();
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
//        canvas.setSize(Constants.WIDTH, Constants.HEIGHT);
        canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        canvas.addMouseListener(mouseHandler);
    }

    public void addMove(Move move) {
        moveHistory.add(move);
    }

    private class MouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int xCell = e.getX() / (Constants.WIDTH / Constants.NO_CELLS) + 1;
            int yCell = e.getY() / (Constants.HEIGHT / Constants.NO_CELLS) + 1;
            Position p = new Position(xCell, yCell);
            if (pieceClicked) {
                for (Move move : selectedPiece.getCorrectMoves()) {
                    if (move.getTargetPosition().equals(p)) {
                        // acum fac o mutare
                        selectedPiece.move(move);
                        selectedPiece = null;
                        pieceClicked = false;
                        return;
                    }
                }
                selectedPiece = null;
                pieceClicked = false;
                canvas.repaint();
            }
            if (existsPiece(p) && getPiece(p).getColor() == colorTurn) {
                BasicPiece piece = getPiece(p);
                pieceClicked = true;
                selectedPiece = piece;
                canvas.repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class Drawing extends JPanel {

        public Drawing() {
        }

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
//            RenderingHints rh = new RenderingHints(
//                    RenderingHints.KEY_TEXT_ANTIALIASING,
//                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//            g2.setRenderingHints(rh);


            int squareWidth = (Constants.WIDTH / Constants.NO_CELLS);
            int squareHeight = (Constants.HEIGHT / Constants.NO_CELLS);
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

            for (int i = 0; i < Constants.NO_CELLS; i++) {
                for (int j = 0; j < Constants.NO_CELLS; j++) {
                    if ((i + j) % 2 == 0) {
                        g.setColor(new Color(167, 104, 68));
                    } else {
                        g.setColor(new Color(255, 209, 152));
                    }
                    g.fillRect(i * squareWidth, j * squareHeight, squareWidth, squareHeight);

                }
            }

            if (pieceClicked) {
                g.setColor(new Color(26, 255, 161, 100));
                g.fillRect((selectedPiece.getPosition().getX() - 1) * squareWidth, (selectedPiece.getPosition().getY() - 1) * squareHeight, squareWidth, squareHeight);
                for (Move move : selectedPiece.getCorrectMoves()) {
                    g.setColor(new Color(136, 248, 200, 86));
                    g.fillRect((move.getTargetPosition().getX() - 1) * squareWidth, (move.getTargetPosition().getY() - 1) * squareHeight, squareWidth, squareHeight);
                }
            }
            for (Map.Entry<Position, BasicPiece> entry : layout.entrySet()) {
                g.drawImage(entry.getValue().getImg(), (entry.getKey().getX() - 1) * squareWidth, (entry.getKey().getY() - 1) * squareHeight, squareWidth, squareHeight, null);
            }
            g.setColor(new Color(0, 0, 0));
            for (int i = 0; i < Constants.NO_CELLS; i++) {
                g.drawLine(0, squareHeight * i, Constants.WIDTH, squareHeight * i);
                g.drawLine(squareWidth * i, 0, squareWidth * i, Constants.HEIGHT);
            }
        }
    }

}
