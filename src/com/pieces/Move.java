package com.pieces;

import com.utils.Constants;

public final class Move {
    private Position initialPosition;
    private Position targetPosition;
    private MovePiece dirrection;
    private int color;

    public Move(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    public Move(Position initialPosition, Position targetPosition, MovePiece dirrection) {
        this.initialPosition = initialPosition;
        this.targetPosition = targetPosition;
        this.dirrection = dirrection;
    }

    public MovePiece getDirrection() {
        return dirrection;
    }

    public void setDirrection(MovePiece dirrection) {
        this.dirrection = dirrection;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    public boolean verifyTargetPosition() {
        if (targetPosition.getX() < 1 || targetPosition.getX() > Constants.NO_CELLS) {
            return targetPosition.getY() >= 1 && targetPosition.getY() <= Constants.NO_CELLS;
        }
        return true;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Move{" +
                "initialPosition=" + initialPosition +
                ", targetPosition=" + targetPosition +
                ", color=" + color +
                '}';
    }
}
