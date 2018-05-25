package com.hunter.model;

public class Arrow implements IAbleToMove {

    private Direction direction;
    private Position actualPosition;

    public Arrow(Direction d, Position p) {
        this.direction = d;
        this.actualPosition = p;
    }


    public Position getActualPosition() {
        return actualPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setActualPosition(Position p) {
        actualPosition = p;
    }
}
