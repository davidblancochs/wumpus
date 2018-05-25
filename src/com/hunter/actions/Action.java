package com.hunter.actions;

import com.hunter.model.Hunter;
import com.hunter.model.IAbleToMove;
import com.hunter.model.Labyrinth;
import com.hunter.model.Position;
import com.hunter.perceptions.Perception;
import com.hunter.utils.PlayerSpeaker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Action {

    List<Perception> perceptionsAfterAction = new ArrayList<>();

    public abstract List<Perception> doAction(Hunter hunter, Labyrinth labyrinth);

    public String getName() {
        return this.getClass().getSimpleName();
    }


    /**
     * Maps User input  to Action - ignoring case
     * @param actionCode
     * @return
     */
    public static Action findAction(String actionCode) {
        Action result = null;
        switch (actionCode.toUpperCase()) {
            case "M":
                result = new Move();
                break;
            case "L":
                result = new TurnLeft();
                break;
            case "R":
                result = new TurnRight();
                break;
            case "S":
                result = new Shoot();
                break;
            case "G":
                result = new Grab();
                break;
            case "E":
                result = new Exit();
                break;
            default:
                PlayerSpeaker.speak("Not a valid action. Please try again...");
        }
        return result;
    }


    /**
     * Find next possible position in labyrinth. Prevents Out Of Bounds Exception
     * @param mover
     * @param labyrinth
     * @return
     */
    protected Position findNextPosition(IAbleToMove mover, Labyrinth labyrinth) {
        Position newPosition = new Position(mover.getActualPosition().getX(), mover.getActualPosition().getY());

        switch (mover.getDirection().getActualDirection()) {
            case EAST:
                if (newPosition.getX() < labyrinth.getWidth()-1) newPosition.addToX(1);
                break;
            case SOUTH:
                if (newPosition.getY() < labyrinth.getWidth()-1) newPosition.addToY(1);
                break;
            case WEST:
                if (newPosition.getX() > 0) newPosition.addToX(-1);
                break;
            case NORTH:
                if (newPosition.getY() > 0) newPosition.addToY(-1);
                break;
        }
        return newPosition;
    }

    /**
     *
     * @param labyrinth
     * @param pos
     * @return
     */
    protected Collection<? extends Perception> findPerceptions(Labyrinth labyrinth, Position pos) {
        return labyrinth.getRoom(pos).getPerceptionsInHere();
    }
}
