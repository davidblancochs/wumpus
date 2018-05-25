package com.hunter.actions;

import com.hunter.model.*;
import com.hunter.perceptions.*;
import com.hunter.perceptions.Stairs;

import java.util.List;

public class Move extends Action {

    @Override
    public List<Perception> doAction(Hunter hunter, Labyrinth labyrinth) {
        Position newPosition = findNextPosition(hunter, labyrinth);

        if (newPosition.equals(hunter.getActualPosition())) { // position did not change
            perceptionsAfterAction.add(new BumpToWall());
        } else {
            hunter.setActualPosition(newPosition);  // move is made
        }

        perceptionsAfterAction.addAll(findPerceptions(labyrinth, hunter.getActualPosition()));

        if (labyrinth.getRoom(hunter.getActualPosition()).getRoomType().equals(RoomTypeEnum.WUMPUS)) {
            if (!hunter.hasBeatenWumpus()) {
                perceptionsAfterAction.clear();
                perceptionsAfterAction.add(new DeathByWumpus());
                hunter.killedByWumpus(); // GAME OVER
            }
        } else if (labyrinth.getRoom(hunter.getActualPosition()).getRoomType().equals(RoomTypeEnum.PIT)) {
            perceptionsAfterAction.clear();
            perceptionsAfterAction.add(new DeathOfFall());
            hunter.fallenIntoDarkness(); // GAME OVER
        } else if (labyrinth.getRoom(hunter.getActualPosition()).getRoomType().equals(RoomTypeEnum.GOLD)) {
            perceptionsAfterAction.add(new GoldGlitter());
        } else if (labyrinth.getRoom(hunter.getActualPosition()).getRoomType().equals(RoomTypeEnum.EXIT)) {
            perceptionsAfterAction.add(new Stairs());
        }

        return perceptionsAfterAction;
    }

}
