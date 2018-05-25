package com.hunter.wumpus;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hunter.wumpus.actions.Exit;
import com.hunter.wumpus.actions.Grab;
import com.hunter.wumpus.actions.Move;
import com.hunter.wumpus.actions.Shoot;
import com.hunter.wumpus.actions.TurnLeft;
import com.hunter.wumpus.actions.TurnRight;
import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.model.Position;
import com.hunter.wumpus.model.RoomTypeEnum;
import com.hunter.wumpus.perceptions.BumpToWall;
import com.hunter.wumpus.perceptions.DeathOfFall;
import com.hunter.wumpus.perceptions.GoldGlitter;
import com.hunter.wumpus.perceptions.Perception;
import com.hunter.wumpus.perceptions.Stairs;
import com.hunter.wumpus.perceptions.WumpusDies;
import com.hunter.wumpus.perceptions.WumpusStink;
import com.hunter.wumpus.utils.PlayerSpeaker;

public class GameActionsTest {

    Labyrinth labyrinth;
    Hunter hunter;
    List<Perception> perceptionsAfterMove;
    GameInitializer initializer;

    @Before
    public void init() {
        labyrinth = new Labyrinth(3);
        hunter = new Hunter(2, labyrinth.getWidth());
        perceptionsAfterMove = new ArrayList<>();
        initializer = new GameInitializer(true);        
    }

    @Test
    public void shootWumpusAndMoveOverItTest() {
        labyrinth.setXYRoomTo(RoomTypeEnum.WUMPUS, new Position(2,2));
        initializer.setupInitialPerceptions(labyrinth);

        perceptionsAfterMove = new Move(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        assert !perceptionsAfterMove.isEmpty();
        assert perceptionsAfterMove.contains(new WumpusStink());

        perceptionsAfterMove = new Shoot(hunter, labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        assert perceptionsAfterMove.contains(new WumpusDies());
        assert hunter.getArrowsLeft() == 1;

        perceptionsAfterMove = new Move(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        assert perceptionsAfterMove.size() == 1;
        assert perceptionsAfterMove.contains(new WumpusStink());

    }

    
    @Test
    public void turnLeftAndShootTest() {
        
        perceptionsAfterMove = new TurnLeft(hunter, labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);

        perceptionsAfterMove = new Shoot(hunter, labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        assert perceptionsAfterMove.size() == 1;
        assert perceptionsAfterMove.contains(new BumpToWall());
        assert hunter.getArrowsLeft() == 1;
    }

    @Test
    public void turnRightAndBumpTest() {
        
        perceptionsAfterMove = new TurnRight(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        
        perceptionsAfterMove = new Move(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        assert perceptionsAfterMove.size() > 0;
        assert perceptionsAfterMove.contains(new BumpToWall());
    }

    @Test
    public void grabTheGoldAndWinTest() {
        labyrinth.setXYRoomTo(RoomTypeEnum.GOLD, new Position(1, labyrinth.getWidth()-1));
        
        perceptionsAfterMove = new Move(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        assert perceptionsAfterMove.size() == 1;
        assert perceptionsAfterMove.contains(new GoldGlitter());

        perceptionsAfterMove = new Grab(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);

        perceptionsAfterMove = new TurnLeft(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);

        perceptionsAfterMove = new TurnLeft(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);

        perceptionsAfterMove = new Move(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        assert perceptionsAfterMove.size() == 1;
        assert perceptionsAfterMove.contains(new Stairs());

        perceptionsAfterMove = new Exit(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        assert perceptionsAfterMove.isEmpty();
        assert hunter.isGotMoney();
        assert hunter.isAWinner();
        assert hunter.isAlive();
    }
    
    @Test 
    public void fallIntoPitTest() {
    	labyrinth.setXYRoomTo(RoomTypeEnum.PIT, new Position(1, labyrinth.getWidth()-1));
        perceptionsAfterMove = new Move(hunter,labyrinth).doAction();
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove);
        assert perceptionsAfterMove.size() == 1;
        assert perceptionsAfterMove.contains(new DeathOfFall());
        
        assertTrue(!hunter.isAlive());
        assertTrue(!hunter.isGotMoney());
    }

}

