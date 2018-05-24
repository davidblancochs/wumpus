package com.hunter.model;

import java.util.Arrays;
import java.util.List;

public class Direction {

    private final List<DirectionEnum> availableDirections = Arrays.asList(DirectionEnum.EAST, DirectionEnum.SOUTH, DirectionEnum.WEST, DirectionEnum.NORTH);

    private DirectionEnum actualDirection;

    public Direction(DirectionEnum d) {
        this.actualDirection = d;
    }


    public DirectionEnum getActualDirection() {
        return actualDirection;
    }

    public void setActualDirection(DirectionEnum actualDirection) {
        this.actualDirection = actualDirection;
    }

    public DirectionEnum getDirectionToRight() {
        return  availableDirections.get( (availableDirections.indexOf(actualDirection) + 1)
                % availableDirections.size());
    }

    public DirectionEnum getDirectionToLeft() {
        return  availableDirections.get( ((availableDirections.indexOf(actualDirection) - 1) + availableDirections.size())
                % availableDirections.size());
    }


}
