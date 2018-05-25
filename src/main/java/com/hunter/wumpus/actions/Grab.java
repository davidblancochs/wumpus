package com.hunter.wumpus.actions;



import java.util.List;

import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.model.RoomTypeEnum;
import com.hunter.wumpus.perceptions.Perception;
import com.hunter.wumpus.utils.PlayerSpeaker;

public class Grab extends Action {
	
	
    public Grab(Hunter hunter, Labyrinth labyrinth) {
		super(hunter, labyrinth);
	}

	@Override
    public List<Perception> doAction() {

        perceptionsAfterAction.addAll(findPerceptions(labyrinth, hunter.getActualPosition()));

        if (labyrinth.getRoom( hunter.getActualPosition()).isGOLD() ) {
            hunter.grabTheGold();
            PlayerSpeaker.speak("$$$ Got some GOLD. Now try to EXIT without Dying. $$$");
            labyrinth.setXYRoomTo(RoomTypeEnum._EMPTY, hunter.getActualPosition());
        } else {
            PlayerSpeaker.speakInRed("### Can't grab GOLD here. It is not here." 
            		+ (hunter.isGotMoney() ? " You already have it ;) $$$" : ""));
        }

        return perceptionsAfterAction;
    }
}
