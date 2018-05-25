package com.hunter;

import com.hunter.actions.*;
import com.hunter.model.Hunter;
import com.hunter.model.Labyrinth;
import com.hunter.model.RoomTypeEnum;
import com.hunter.perceptions.*;
import com.hunter.utils.PlayerSpeaker;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameActionsTest {

    Labyrinth labyrinth;
    Hunter hunter;
    List<Perception> perceptionsAfterMove;

    @Before
    public void init() {
        labyrinth = new Labyrinth(3);
        hunter = new Hunter(2, labyrinth.getWidth());
        perceptionsAfterMove = new ArrayList<>();
    }

    @Test
    public void shootWumpusAndMoveOverItTest() {
        labyrinth.setXYRoomTo(RoomTypeEnum.WUMPUS, 2,2);
        GameInitializer.setupInitialPerceptions(labyrinth);

        Action m1 = new Move();
        perceptionsAfterMove = m1.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, m1);
        assert !perceptionsAfterMove.isEmpty();
        assert perceptionsAfterMove.contains(new WumpusStink());

        Action s1 = new Shoot();
        perceptionsAfterMove = s1.doAction(hunter, labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, s1);
        assert perceptionsAfterMove.contains(new WumpusDies());

        Action m2 = new Move();
        perceptionsAfterMove = m2.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, m2);
        assert perceptionsAfterMove.size() == 1;
        assert perceptionsAfterMove.contains(new WumpusStink());

    }

    @Test
    public void turnLeftAndShootTest() {
        Action turnLeft = new TurnLeft();
        perceptionsAfterMove = turnLeft.doAction(hunter, labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, turnLeft);

        Action shoot = new Shoot();
        perceptionsAfterMove = shoot.doAction(hunter, labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, shoot);
        assert perceptionsAfterMove.size() == 1;
        assert perceptionsAfterMove.contains(new BumpToWall());
        assert hunter.getArrowsLeft() == 1;
    }

    @Test
    public void turnRightAndBumpTest() {
        Action turnRight = new TurnRight();
        perceptionsAfterMove = turnRight.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, turnRight);

        Action move = new Move();
        perceptionsAfterMove = move.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, move);
        assert perceptionsAfterMove.size() > 0;
        assert perceptionsAfterMove.contains(new BumpToWall());
    }

    @Test
    public void grabTheGoldAndWinTest() {
        labyrinth.setXYRoomTo(RoomTypeEnum.GOLD,1, labyrinth.getWidth()-1);

        Action move1 = new Move();
        perceptionsAfterMove = move1.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, move1);
        assert perceptionsAfterMove.size() == 1;
        assert perceptionsAfterMove.contains(new GoldGlitter());

        Action grabTheGold = new Grab();
        perceptionsAfterMove = grabTheGold.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, grabTheGold);

        Action l1 = new TurnLeft();
        perceptionsAfterMove = l1.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, l1);

        Action l2 = new TurnLeft();
        perceptionsAfterMove = l2.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, l2);

        Action move2 = new Move();
        perceptionsAfterMove = move2.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, move2);
        assert perceptionsAfterMove.size() == 1;
        assert perceptionsAfterMove.contains(new Stairs());

        Action exit = new Exit();
        perceptionsAfterMove = exit.doAction(hunter,labyrinth);
        PlayerSpeaker.tellPerceptions(perceptionsAfterMove, hunter, exit);
        assert perceptionsAfterMove.isEmpty();
        assert hunter.isGotMoney();
        assert hunter.isAWinner();
        assert hunter.isAlive();
    }

}

