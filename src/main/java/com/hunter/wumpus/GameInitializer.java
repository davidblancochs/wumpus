package com.hunter.wumpus;


import java.util.Random;

import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.model.Position;
import com.hunter.wumpus.model.RoomTypeEnum;
import com.hunter.wumpus.perceptions.Breeze;
import com.hunter.wumpus.perceptions.Perception;
import com.hunter.wumpus.perceptions.WumpusStink;
import com.hunter.wumpus.utils.PlayerListener;
import com.hunter.wumpus.utils.PlayerSpeaker;

/**
 * Game Initialization Responsible Class
 */
public class GameInitializer extends PlayerSpeaker {

    static boolean SHOW_THE_SECRET = false; // for debug - tell the configuration
    static private Random ran = new Random(); // used many times

    public GameInitializer(boolean isDebug) {
        SHOW_THE_SECRET = isDebug;
    }

    /**
     * Reads necessary parameters and creates labyrinth
     * @return
     */
    public Labyrinth initLabyrinth() {
        PlayerSpeaker.speak("To initialize the game please provide the Following parameters:");
        int labyrinthSize = PlayerListener.readANumber("-> Enter Labyrinth size:", -1);
        int numberOfPits = PlayerListener.readANumber("-> Enter number of pits:", labyrinthSize);
        // random generation
        if (SHOW_THE_SECRET) PlayerSpeaker.speak("  LABYRINTH Rooms:");
        Labyrinth initialLabyrinth = initLabyrinth(labyrinthSize, numberOfPits);
        if (SHOW_THE_SECRET) PlayerSpeaker.speak("  --------------------- ");
        // based on outcome
        setupInitialPerceptions(initialLabyrinth);
        return initialLabyrinth;
    }


    /**
     * Initiates labyrinth with parameters
     * @param size
     * @param pitsCount
     * @return
     */
    private Labyrinth initLabyrinth(int size, int pitsCount) {

        Labyrinth labyrinth = new Labyrinth(size);

        createRoomByType(labyrinth, pitsCount, RoomTypeEnum.PIT);
        createRoomByType(labyrinth, 1, RoomTypeEnum.GOLD);
        createRoomByType(labyrinth, 1, RoomTypeEnum.WUMPUS);

        return labyrinth;
    }

    /**
     * Reads arrows number and initializes hunter on bottom left corner position
     * @param labyrinthSize
     * @return
     */
    public Hunter initHunter(int labyrinthSize) {
        int numberOfArrows= PlayerListener.readANumber("-> Enter number of arrows you wish to give for your hunter:", -1);
        return new Hunter(numberOfArrows, labyrinthSize);
    }

    /**
     * Perceptions of initial labyrinth for PITS and WUMPUS
     * Perceptions of GoldGlitter, Death are added on Move action
     * @param labyrinth
     */
    public void setupInitialPerceptions(Labyrinth labyrinth) {
        for (int i = 0; i < labyrinth.getWidth(); i++) {
            for (int j = 0; j < labyrinth.getWidth(); j++) {
                if(labyrinth.getRoom(i,j).isWUMPUS()) {
                    labyrinth.getRoom(i,j).addPerception(new WumpusStink());
                    addPerceptionToRoomSurroundings(new WumpusStink(), labyrinth, i, j);
                } else if(labyrinth.getRoom(i,j).isPIT()) {
                    addPerceptionToRoomSurroundings(new Breeze(), labyrinth, i, j);
                }
            }
        }
    }

    /**
     * Creates static perceptions for PIT and WUMPUS basically
     * @param perception
     * @param labyrinth
     * @param x
     * @param y
     */
    private void  addPerceptionToRoomSurroundings(Perception perception, Labyrinth labyrinth, int x, int y) {
        if (labyrinth.getRoom(x-1,y) != null) labyrinth.getRoom(x-1,y).addPerception(perception);
        if (labyrinth.getRoom(x+1,y) != null) labyrinth.getRoom(x+1,y).addPerception(perception);
        if (labyrinth.getRoom(x,y-1) != null) labyrinth.getRoom(x,y-1).addPerception(perception);
        if (labyrinth.getRoom(x,y+1) != null) labyrinth.getRoom(x,y+1).addPerception(perception);
    }


    /**
     * Creates room by type in labyrinth on random position
     * @param labyrinth
     * @param roomsCount
     * @param roomType
     */
    private void createRoomByType(Labyrinth labyrinth, int roomsCount, RoomTypeEnum roomType) {
        for (int i = 0; i < roomsCount; i++) {
            Position emptyPos = getRandomEmptyPosition(labyrinth);
            labyrinth.setXYRoomTo(roomType, emptyPos);
            if (SHOW_THE_SECRET) PlayerSpeaker.speak (" ... [" + emptyPos.getX() + ":" + emptyPos.getY() + "] is " + roomType);
        }
    }

    /**
     * Recursion with brutal force
     * @param labyrinth
     * @return
     */
    private Position getRandomEmptyPosition(Labyrinth labyrinth) {
        int randomX = ran.nextInt(labyrinth.getWidth());
        int randomY = ran.nextInt(labyrinth.getWidth());

        if (labyrinth.getRoom(randomX,randomY).isEmpty()) {
            return new Position(randomX, randomY);
        }
        else if (emptyRoomsLeft(labyrinth)) {
            return getRandomEmptyPosition(labyrinth);
        }
        assert false; // never enters here
        return null;
    }


    /**
     * Just to check if is possible to get random room
     * @param labyrinth
     * @return
     */
    private boolean emptyRoomsLeft(Labyrinth labyrinth) {
        for (int i = 0; i<labyrinth.getWidth(); i++) {
            for(int j = 0; j<labyrinth.getWidth(); j++) {
                if (labyrinth.getRoom(i,j).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }




}
