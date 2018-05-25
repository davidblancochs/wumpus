package com.hunter.wumpus.model;

public interface IAbleToMove {

    Position getActualPosition();
    Direction getDirection();
    void setActualPosition(Position actualPosition);

}
