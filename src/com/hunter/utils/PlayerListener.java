package com.hunter.utils;

import com.hunter.actions.Action;

import java.io.IOException;
import java.util.Scanner;

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
                    speak("Entered number is out of limit. Has to be greater than 0 and less than: " + maxValue);
                }
            } else {
                speak("Not a valid Number");
                scanner.next();
            }
        }
        return aNumber;
    }

    public static Action readAction() {
        Action action = null;
        while (action == null) {
            if (scanner.hasNext()) {
                action = Action.findAction( scanner.next() );
            }
        }
        return action;
    }


    public static void exit() {
        speak("\n\n....hit any button to exit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
