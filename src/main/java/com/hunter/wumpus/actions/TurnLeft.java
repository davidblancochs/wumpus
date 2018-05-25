package com.hunter.wumpus.actions;

import java.util.List;

import com.hunter.wumpus.model.Direction;
import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.perceptions.Perception;

public class TurnLeft extends Action {

    public TurnLeft(Hunter hunter, Labyrinth labyrinth) {
		super(hunter, labyrinth);
	}

	@Override
    public List<Perception> doAction() {
        perceptionsAfterAction.addAll(findPerceptions(labyrinth, hunter.getActualPosition()));
        Direction newDirection = new Direction(hunter.getDirection().getDirectionToLeft());
        hunter.setDirection(newDirection);
        return perceptionsAfterAction;
    }
}
