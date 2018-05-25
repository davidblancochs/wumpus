package com.hunter.model;

public interface IAbleToMove {

    Position getActualPosition();
    Direction getDirection();
    void setActualPosition(Position actualPosition);

}
