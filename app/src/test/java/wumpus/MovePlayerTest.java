package wumpus;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Cell;

public class MovePlayerTest {

	Wumpus wumpus;

	@Before
	public void setUp() {
		wumpus = new Wumpus(5, 2, 2);
		wumpus.setPlayerLocation(new Cell(2, 2));
		wumpus.setFocusPlayer(FocusPlayer.NORTH);
	}

	@After
	public void tearDown() {
		wumpus = null;
	}

	@Test
	public void calculateNextMoveStraight() {
		Cell newPlayerCell = Cell.newInstance(wumpus.getPlayerLocation());
		wumpus.calculateCellDestination(MovesPlayer.MOVE_STRAIGHT, wumpus.getPlayerLocation());
		Assert.assertNotEquals(newPlayerCell, wumpus.getPlayerLocation());
		Assert.assertEquals(wumpus.getPlayerLocation().getY(), new Cell(2, 3).getY());
	}

	@Test
	public void calculateNextTurnLeft() {
		Cell newPlayerCell = Cell.newInstance(wumpus.getPlayerLocation());
		wumpus.calculateCellDestination(MovesPlayer.TURN_LEFT, wumpus.getPlayerLocation());
		Assert.assertNotEquals(newPlayerCell, wumpus.getPlayerLocation());
		Assert.assertEquals(wumpus.getPlayerLocation().getX(), new Cell(1, 2).getX());
	}

	@Test
	public void calculateNextTurnRight() {
		Cell newPlayerCell = Cell.newInstance(wumpus.getPlayerLocation());
		wumpus.calculateCellDestination(MovesPlayer.TURN_RIGHT, wumpus.getPlayerLocation());
		Assert.assertNotEquals(newPlayerCell, wumpus.getPlayerLocation());
		Assert.assertEquals(wumpus.getPlayerLocation().getX(), new Cell(3, 2).getX());
	}
	
	@Test
	public void moveTowardsWall() {
		wumpus.calculateCellDestination(MovesPlayer.TURN_LEFT, wumpus.getPlayerLocation());
		Cell newPlayerCell = Cell.newInstance(wumpus.getPlayerLocation());
		wumpus.calculateCellDestination(MovesPlayer.MOVE_STRAIGHT, wumpus.getPlayerLocation());
		Boolean result = wumpus.calculateCellDestination(MovesPlayer.MOVE_STRAIGHT, wumpus.getPlayerLocation());
		Assert.assertNotEquals(newPlayerCell, wumpus.getPlayerLocation());
		Assert.assertEquals(result, false);
	}

}
