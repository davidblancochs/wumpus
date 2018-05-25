package com.hunter.actions;

import com.hunter.model.Hunter;
import com.hunter.model.Labyrinth;
import com.hunter.model.RoomTypeEnum;
import com.hunter.perceptions.Perception;
import com.hunter.utils.PlayerSpeaker;

import java.util.List;

public class Grab extends Action {

    @Override
    public List<Perception> doAction(Hunter hunter, Labyrinth labyrinth) {

        perceptionsAfterAction.addAll(findPerceptions(labyrinth, hunter.getActualPosition()));

        if (labyrinth.getRoom( hunter.getActualPosition()).getRoomType().equals(RoomTypeEnum.GOLD) ) {
            hunter.grabTheGold();
            PlayerSpeaker.speak("Got some GOLD. Now try to EXIT without Dying.");
            labyrinth.setXYRoomTo(RoomTypeEnum._EMPTY, hunter.getActualPosition().getX(), hunter.getActualPosition().getY());
        } else {
            PlayerSpeaker.speak("Can't grab GOLD here. " + (hunter.isGotMoney() ? "Already have it ;)" : ""));
        }

        return perceptionsAfterAction;
    }
}
