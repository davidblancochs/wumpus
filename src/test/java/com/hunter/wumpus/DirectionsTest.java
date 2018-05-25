package com.hunter.wumpus;

import org.junit.Test;

import com.hunter.wumpus.model.Direction;
import com.hunter.wumpus.model.DirectionEnum;

public class DirectionsTest {

    @Test
    public void directionsToRihtTest() {
        Direction direction = new Direction(DirectionEnum.EAST);

        direction.setActualDirection(direction.getDirectionToRight());
        assert direction.getActualDirection() == DirectionEnum.SOUTH;

        direction.setActualDirection(direction.getDirectionToRight());
        assert direction.getActualDirection() == DirectionEnum.WEST;

        direction.setActualDirection(direction.getDirectionToRight());
        assert direction.getActualDirection() == DirectionEnum.NORTH;

        direction.setActualDirection(direction.getDirectionToRight());
        assert direction.getActualDirection() == DirectionEnum.EAST;

        direction.setActualDirection(direction.getDirectionToRight());
        assert direction.getActualDirection() == DirectionEnum.SOUTH;
    }

    @Test
    public void directionsToLeftTest() {
        Direction direction = new Direction(DirectionEnum.EAST);

        direction.setActualDirection(direction.getDirectionToLeft());
        assert direction.getActualDirection() == DirectionEnum.NORTH;

        direction.setActualDirection(direction.getDirectionToLeft());
        assert direction.getActualDirection() == DirectionEnum.WEST;

        direction.setActualDirection(direction.getDirectionToLeft());
        assert direction.getActualDirection() == DirectionEnum.SOUTH;

        direction.setActualDirection(direction.getDirectionToLeft());
        assert direction.getActualDirection() == DirectionEnum.EAST;

        direction.setActualDirection(direction.getDirectionToLeft());
        assert direction.getActualDirection() == DirectionEnum.NORTH;
    }

}
