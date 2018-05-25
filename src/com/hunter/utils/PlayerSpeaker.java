package com.hunter.utils;

import com.hunter.actions.Action;
import com.hunter.model.Hunter;
import com.hunter.perceptions.Perception;

import java.util.List;

/**
 * For more comfortable printing to Console
 */
public class PlayerSpeaker {

    public static void speak(Object o) {
        System.out.println(o.toString());
    }

    public static void tellPerceptions(List<Perception> currentPerceptions, Hunter hunter, Action a) {
        speak("Hunter on position: " + hunter.getActualPosition().toString()
                + ", " + (a != null ? a.getName() : "")
                + " towards: " + hunter.getDirection().getActualDirection().name());
        currentPerceptions.forEach(p -> speak(p.feelsLike()));
    }
}
