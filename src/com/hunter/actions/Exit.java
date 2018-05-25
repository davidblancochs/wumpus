package com.hunter.actions;

import com.hunter.model.Hunter;
import com.hunter.model.Labyrinth;
import com.hunter.model.RoomTypeEnum;
import com.hunter.perceptions.Perception;
import com.hunter.utils.PlayerSpeaker;

import java.util.List;

public class Exit extends Action{

    @Override
    public List<Perception> doAction(Hunter hunter, Labyrinth labyrinth) {
        perceptionsAfterAction.addAll(findPerceptions(labyrinth, hunter.getActualPosition()));

        if (labyrinth.getRoom(hunter.getActualPosition()).getRoomType().equals(RoomTypeEnum.EXIT)) {
            if (hunter.isGotMoney()) {
                perceptionsAfterAction.clear();
                hunter.setWinner(); // Victory!
            } else {
                PlayerSpeaker.speak("Can't exit without grabbing peace of GOLD... Sorry, the rules ;)");
            }
        }

        return perceptionsAfterAction;
    }
}
