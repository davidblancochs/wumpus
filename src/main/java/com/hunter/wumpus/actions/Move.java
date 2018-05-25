package com.hunter.wumpus.actions;


import java.util.List;

import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.model.Position;
import com.hunter.wumpus.perceptions.BumpToWall;
import com.hunter.wumpus.perceptions.DeathByWumpus;
import com.hunter.wumpus.perceptions.DeathOfFall;
import com.hunter.wumpus.perceptions.GoldGlitter;
import com.hunter.wumpus.perceptions.Perception;
import com.hunter.wumpus.perceptions.Stairs;

public class Move extends Action {
	
    public Move(Hunter hunter, Labyrinth labyrinth) {
		super(hunter, labyrinth);
	}

	@Override
    public List<Perception> doAction() {
        Position newPosition = findNextPosition(hunter, labyrinth);

        if (newPosition.equals(hunter.getActualPosition())) { // position did not change
            perceptionsAfterAction.add(new BumpToWall());
        } else {
            hunter.setActualPosition(newPosition);  // move is made
        }

        perceptionsAfterAction.addAll(findPerceptions(labyrinth, hunter.getActualPosition()));

        if (labyrinth.getRoom(hunter.getActualPosition()).isWUMPUS()) {
            if (!hunter.hasBeatenWumpus()) {
                perceptionsAfterAction.clear();
                perceptionsAfterAction.add(new DeathByWumpus());
                hunter.killedByWumpus(); // GAME OVER
            }
        } else if (labyrinth.getRoom(hunter.getActualPosition()).isPIT()) {
            perceptionsAfterAction.clear();
            perceptionsAfterAction.add(new DeathOfFall());
            hunter.fallenIntoDarkness(); // GAME OVER
        } else if (labyrinth.getRoom(hunter.getActualPosition()).isGOLD()) {
            perceptionsAfterAction.add(new GoldGlitter());
        } else if (labyrinth.getRoom(hunter.getActualPosition()).isEXIT()) {
            perceptionsAfterAction.add(new Stairs());
        }

        return perceptionsAfterAction;
    }

}
