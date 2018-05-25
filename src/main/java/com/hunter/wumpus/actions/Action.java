package com.hunter.wumpus.actions;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.IAbleToMove;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.model.Position;
import com.hunter.wumpus.perceptions.Perception;
import com.hunter.wumpus.utils.PlayerSpeaker;

public abstract class Action {

	protected Hunter hunter; 
	protected Labyrinth labyrinth;
	protected List<Perception> perceptionsAfterAction;
    
    public Action(Hunter hunter, Labyrinth labyrinth) {
    	this.hunter = hunter;
    	this.labyrinth = labyrinth;    		
    	this.perceptionsAfterAction = new ArrayList<>();
    	info();
    }

    public abstract List<Perception> doAction();
    
    private void info() {
        PlayerSpeaker.speak(this);
    }

    public String getName() {
        return this.getClass().getSimpleName().toUpperCase();
    }


    /**
     * Maps User input  to Action - ignoring case
     * @param actionCode
     * @param labyrinth 
     * @param hunter 
     * @return
     */
    public static Action findAction(String actionCode, Hunter hunter, Labyrinth labyrinth) {
        Action result = null;
        switch (actionCode.toUpperCase()) {
            case "M":
                result = new Move(hunter, labyrinth);
                break;
            case "L":
                result = new TurnLeft(hunter, labyrinth);
                break;
            case "R":
                result = new TurnRight(hunter, labyrinth);
                break;
            case "S":
                result = new Shoot(hunter, labyrinth);
                break;
            case "G":
                result = new Grab(hunter, labyrinth);
                break;
            case "E":
                result = new Exit(hunter, labyrinth);
                break;
            default:
                PlayerSpeaker.speakInRed("### + -> " + actionCode +  " <- is not a valid action. Please try again...");
                return null;
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
