package com.hunter.wumpus;


import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.utils.PlayerListener;
import com.hunter.wumpus.utils.PlayerSpeaker;

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
        PlayerSpeaker.speak("*** Lets begin!");
        labyrinth = initializer.initLabyrinth();
        hunter = initializer.initHunter(labyrinth.getWidth());
    }

    /**
     * Playing
     */
    private void play() {
        PlayerSpeaker.speak("*** Lets Play!");

        while (!hunter.isAWinner() && hunter.isAlive()) { // win or die
        	
        	// where is the hunter?
        	PlayerSpeaker.speak(hunter);

        	// what he does?
            PlayerSpeaker.speak("\n-> Move [M] or turn left [L] or turn right [R] or shoot [S] or grab the gold [G] or exit [E]");
            
            // how it feels?
            PlayerSpeaker.tellPerceptions( PlayerListener.readAction(hunter, labyrinth).doAction() );           
        }        
    }


    /**
     * Shows result
     */
    private void resume() {
    	// where is the hunter?
    	PlayerSpeaker.speak(hunter);
    	    	
        if (hunter.isAWinner()) {
            PlayerSpeaker.speak("*** = You are the winner!");
        } else {
            PlayerSpeaker.speakInRed("*** = So sorry.. you are died.");
        }        
    	
        PlayerSpeaker.speak("*** Game over.");
    }

}
