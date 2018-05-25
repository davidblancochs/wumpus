package com.hunter.wumpus.actions;


import java.util.List;

import com.hunter.wumpus.model.Arrow;
import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.model.Position;
import com.hunter.wumpus.perceptions.BumpToWall;
import com.hunter.wumpus.perceptions.NoArrowsLeft;
import com.hunter.wumpus.perceptions.Perception;
import com.hunter.wumpus.perceptions.WumpusDies;

public class Shoot extends Action {

    public Shoot(Hunter hunter, Labyrinth labyrinth) {
		super(hunter, labyrinth);
	}

	@Override
    public List<Perception> doAction() {
        perceptionsAfterAction.addAll(findPerceptions(labyrinth, hunter.getActualPosition()));

        if (hunter.getArrowsLeft() > 0) {
            hunter.setArrowsLeft(hunter.getArrowsLeft()-1);

            if (!hunter.hasBeatenWumpus()) {
                Arrow arrow = new Arrow(hunter.getDirection(), hunter.getActualPosition());
                Position newPosition = findNextPosition(arrow, labyrinth);

                while (!perceptionsAfterAction.contains(new BumpToWall())
                        && !hunter.hasBeatenWumpus() ) {
                    if (arrow.getActualPosition().equals(newPosition)) {
                        perceptionsAfterAction.add(new BumpToWall());
                    } else if (labyrinth.getRoom(newPosition).isWUMPUS()) {
                        perceptionsAfterAction.add(new WumpusDies());
                        hunter.setHasBeatenWumpus(true);
                    } else {
                        arrow.setActualPosition(newPosition);
                        newPosition = findNextPosition(arrow, labyrinth);
                    }
                }
            }

        } else {
            perceptionsAfterAction.add(new NoArrowsLeft());
        }

        return perceptionsAfterAction;
    }
}
