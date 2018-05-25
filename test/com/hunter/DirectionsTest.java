package com.hunter;

import com.hunter.model.Direction;
import com.hunter.model.DirectionEnum;
import org.junit.Assert;
import org.junit.Test;


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
