package com.hunter.actions;

import com.hunter.model.Direction;
import com.hunter.model.Hunter;
import com.hunter.model.Labyrinth;
import com.hunter.perceptions.Perception;

import java.util.List;

public class TurnRight extends Action{

    @Override
    public List<Perception> doAction(Hunter hunter, Labyrinth labyrinth) {
        perceptionsAfterAction.addAll(findPerceptions(labyrinth, hunter.getActualPosition()));
        Direction newDirection = new Direction(hunter.getDirection().getDirectionToRight());
        hunter.setDirection(newDirection);
        return perceptionsAfterAction;
    }
}
