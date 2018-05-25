package com.hunter.actions;

import com.hunter.model.*;
import com.hunter.perceptions.BumpToWall;
import com.hunter.perceptions.NoArrowsLeft;
import com.hunter.perceptions.Perception;
import com.hunter.perceptions.WumpusDies;

import java.util.List;

public class Shoot extends Action {

    @Override
    public List<Perception> doAction(Hunter hunter, Labyrinth labyrinth) {
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
                    } else if (labyrinth.getRoom(newPosition).getRoomType().equals(RoomTypeEnum.WUMPUS)) {
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
