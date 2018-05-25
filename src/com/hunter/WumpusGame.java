package com.hunter;

import com.hunter.actions.Action;
import com.hunter.model.Hunter;
import com.hunter.model.Labyrinth;
import com.hunter.perceptions.Perception;
import com.hunter.utils.PlayerListener;
import com.hunter.utils.PlayerSpeaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Principal Class with Main() Entry
 */
public class WumpusGame {

    private Labyrinth labyrinth;
    private Hunter hunter;

    private static GameInitializer initializer;

    /**
     * Main
     * @param args
     * @throws Exception
     */
    public static void main(String [] args) throws Exception {
        initializer = new GameInitializer((args.length > 0 && args[0].equals("DEBUG")));

        WumpusGame wg = new WumpusGame();
        wg.init();
        wg.play();
        wg.resume();
        PlayerListener.exit();
    }

    /**
     * Reads parameters and initializes the game
     */
    private void init() {
        PlayerSpeaker.speak("*** Hi There! This is not a game - this is WUMPUS Hunting... \n ");
        labyrinth = initializer.initLabyrinth();
        hunter = initializer.initHunter(labyrinth.getWidth());
    }

    /**
     * Playing
     */
    private void play() {
        PlayerSpeaker.speak("*** Lets begin!");

        Action action = null;
        List<Perception> currentPerceptions = new ArrayList<>();

        while (!hunter.isAWinner() && hunter.isAlive()) {
            // tell me what is happening
            PlayerSpeaker.tellPerceptions(currentPerceptions, hunter, action);

            PlayerSpeaker.speak("Move [M] or turn left [L] or turn right [R] or shoot [S] or grab the gold [G] or exit [E]");
            // take an action
            action = PlayerListener.readAction();

            // perceive
            currentPerceptions = action.doAction(hunter, labyrinth);

        }

        PlayerSpeaker.tellPerceptions(currentPerceptions, hunter, action);

    }


    /**
     * Shows result
     */
    private void resume() {
        if (hunter.isAWinner()) {
            PlayerSpeaker.speak("  You are the winner!");
        } else {
            PlayerSpeaker.speak("  So sorry.. you are died.");
        }
        PlayerSpeaker.speak("*** Game over.");
    }

}
