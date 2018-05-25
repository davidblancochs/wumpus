package com.hunter.wumpus.utils;

import java.util.List;

import com.hunter.wumpus.actions.Action;
import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.perceptions.Perception;

/**
 * For more comfortable printing to Console
 */
public class PlayerSpeaker {

    public static void speak(Object o) {
        System.out.println(o.toString());
    }
    
    public static void speakInRed(Object o) {
        System.err.println(o.toString());
    }
    
    public static void speak(Action action) {
        speak("* Hunter decides to " + action.getName() + "!");
    }

    
    public static void speak(Hunter hunter) {
        speak("* Hunter at " + hunter.getActualPosition().toString() 
        		+ " looking to " + hunter.getDirection().getActualDirection());
    }

    public static void tellPerceptions(List<Perception> currentPerceptions) {		
        currentPerceptions.forEach(p -> speak(p.feelsLike()));
    }
}
