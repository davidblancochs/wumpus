package com.hunter.wumpus.actions;

import java.util.List;

import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.perceptions.Perception;
import com.hunter.wumpus.utils.PlayerSpeaker;

public class Exit extends Action{

    public Exit(Hunter hunter, Labyrinth labyrinth) {
		super(hunter, labyrinth);
	}

	@Override
    public List<Perception> doAction() {		
        perceptionsAfterAction.addAll(findPerceptions(labyrinth, hunter.getActualPosition()));

        if (labyrinth.getRoom(hunter.getActualPosition()).isEXIT()) {
            if (hunter.isGotMoney()) {
                perceptionsAfterAction.clear();
                hunter.setWinner(); // Victory!
            } else {
                PlayerSpeaker.speakInRed("### Can't exit without grabbing peace of GOLD... Sorry, the rules ;)");
            }
        }

        return perceptionsAfterAction;
    }
}
