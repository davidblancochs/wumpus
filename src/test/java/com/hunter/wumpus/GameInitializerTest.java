package com.hunter.wumpus;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

import com.hunter.wumpus.model.Hunter;
import com.hunter.wumpus.model.Labyrinth;
import com.hunter.wumpus.model.Position;
import com.hunter.wumpus.model.RoomTypeEnum;
import com.hunter.wumpus.perceptions.Breeze;
import com.hunter.wumpus.perceptions.WumpusStink;

public class GameInitializerTest {

    GameInitializer initializer = new GameInitializer(true);

    @Test
    @Ignore // ignored because input is not being shared correctly with mainApp test
    public void initializeLabyrinthAndHunterTest() throws Exception {
        String input = " 5\n 4\n 6\n \n";
        System.out.println("- Parameters: \n" + input);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Labyrinth lab = initializer.initLabyrinth();
        Hunter hunter = initializer.initHunter(lab.getWidth());

        assert lab != null;
        assert lab.getWidth() == 5;
        assert hunter.getArrowsLeft() == 6;

    }

    @Test
    public void setupPerceptionsOfPitsTest() {
        Labyrinth labyrinth = new Labyrinth(5);

        labyrinth.setXYRoomTo(RoomTypeEnum.PIT, new Position(0,0));
        labyrinth.setXYRoomTo(RoomTypeEnum.PIT, new Position(0,4));
        labyrinth.setXYRoomTo(RoomTypeEnum.PIT, new Position(4,0));
        labyrinth.setXYRoomTo(RoomTypeEnum.PIT, new Position(4,4));
        labyrinth.setXYRoomTo(RoomTypeEnum.PIT, new Position(2,2));

        initializer.setupInitialPerceptions(labyrinth);

        assert labyrinth.getRoom(1,0).getPerceptionsInHere().contains(new Breeze());
        assert labyrinth.getRoom(0,1).getPerceptionsInHere().contains(new Breeze());

        assert labyrinth.getRoom(3,0).getPerceptionsInHere().contains(new Breeze());
        assert labyrinth.getRoom(4,1).getPerceptionsInHere().contains(new Breeze());

        assert labyrinth.getRoom(0,3).getPerceptionsInHere().contains(new Breeze());
        assert labyrinth.getRoom(1,4).getPerceptionsInHere().contains(new Breeze());

        assert labyrinth.getRoom(4,3).getPerceptionsInHere().contains(new Breeze());
        assert labyrinth.getRoom(3,4).getPerceptionsInHere().contains(new Breeze());

        assert labyrinth.getRoom(2,1).getPerceptionsInHere().contains(new Breeze());
        assert labyrinth.getRoom(1,2).getPerceptionsInHere().contains(new Breeze());
        assert labyrinth.getRoom(3,2).getPerceptionsInHere().contains(new Breeze());
        assert labyrinth.getRoom(2,3).getPerceptionsInHere().contains(new Breeze());
    }

    @Test
    public void setupPerceptionsOfWumpusTest() {
        Labyrinth labyrinth = new Labyrinth(5);

        labyrinth.setXYRoomTo(RoomTypeEnum.WUMPUS, new Position(2, 2));

        initializer.setupInitialPerceptions(labyrinth);

        assert labyrinth.getRoom(2,2).getPerceptionsInHere().contains(new WumpusStink());
        assert labyrinth.getRoom(2,1).getPerceptionsInHere().contains(new WumpusStink());
        assert labyrinth.getRoom(1,2).getPerceptionsInHere().contains(new WumpusStink());
        assert labyrinth.getRoom(3,2).getPerceptionsInHere().contains(new WumpusStink());
        assert labyrinth.getRoom(2,3).getPerceptionsInHere().contains(new WumpusStink());
    }

    @Test
    public void setupPerceptionsOfPitAndWumpusTest() {
        Labyrinth labyrinth = new Labyrinth(5);

        labyrinth.setXYRoomTo(RoomTypeEnum.WUMPUS, new Position(0,0));
        labyrinth.setXYRoomTo(RoomTypeEnum.PIT, new Position(1,1));

        initializer.setupInitialPerceptions(labyrinth);

        assert labyrinth.getRoom(1, 0).getPerceptionsInHere().size() == 2;
        assert labyrinth.getRoom(1, 0).getPerceptionsInHere().contains(new WumpusStink());
        assert labyrinth.getRoom(1, 0).getPerceptionsInHere().contains(new Breeze());

    }
}
