package com.hunter.wumpus.utils;

import java.io.IOException;
import java.util.Scanner;

import com.hunter.wumpus.actions.Action;
import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;

/**
 * Listens to a player and scans numbers as Parameters or characters as Actions
 */
public class PlayerListener extends PlayerSpeaker {

    private static Scanner scanner = new Scanner(System.in);

    public static int readANumber(String forWhat, int maxValue) {
        int aNumber = 0;
        while (aNumber == 0) {
            speak(forWhat);
            if (scanner.hasNextInt()) {
                int inputValue = scanner.nextInt();
                if ( (maxValue < 0 && inputValue > 0) || (inputValue > 0 && inputValue < maxValue)) {
                    aNumber = inputValue;
                } else {
                    speakInRed("### Entered number is out of limit. Has to be greater than 0 and less than: " + maxValue);
                }
            } else {
                speakInRed("### Not a valid Number. Please Try again...");
                scanner.next();
            }
        }
        return aNumber;
    }

    public static Action readAction(Hunter hunter, Labyrinth labyrinth) {
        Action action = null;
        while (action == null) {
            if (scanner.hasNext()) {
                action = Action.findAction( scanner.next(), hunter, labyrinth );
            }
        }
        return action;
    }


    public static void exit() {
        speak("\n\n....hit ENTER to exit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
