package wumpus;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Cell;

public class LaunchArrowTest {

	Wumpus wumpus;

	@Before
	public void setUp() {
		wumpus = new Wumpus(5, 2, 2);
		wumpus.setNumberArrow(1);
		wumpus.setWumpusLocation(new Cell(3, 3));
		wumpus.setFocusPlayer(FocusPlayer.NORTH);
	}

	@After
	public void tearDown() {
		wumpus = null;
	}

	@Test
	public void huntedWumpus() {
		wumpus.setPlayerLocation(new Cell(3, 1));
		wumpus.launchArrow();
		int numberArrows = wumpus.getNumberArrow();
		Assert.assertNull(wumpus.getWumpusLocation());
	}

	@Test
	public void failHuntedWumpus() {
		wumpus.setPlayerLocation(new Cell(2, 2));
		wumpus.launchArrow();
		Assert.assertNotNull(wumpus.getWumpusLocation());
	}

	@Test
	public void enoughNumberArrows() {
		wumpus.setPlayerLocation(new Cell(3, 1));
		int numberArrows = wumpus.getNumberArrow();
		wumpus.launchArrow();
		int numberArrows2 = wumpus.getNumberArrow();
		Assert.assertNotEquals(numberArrows, numberArrows2);
	}

	@Test
	public void ceroArrow() {
		wumpus.setPlayerLocation(new Cell(3, 1));
		wumpus.setNumberArrow(0);
		wumpus.launchArrow();
		Assert.assertNotNull(wumpus.getWumpusLocation());
	}
	
	@Test
	public void arrowFinish() {
		wumpus.setPlayerLocation(new Cell(3, 1));
		int numberArrows = wumpus.getNumberArrow();
		Assert.assertEquals(numberArrows, 1);
		wumpus.launchArrow();
		int numberArrows2 = wumpus.getNumberArrow();
		Assert.assertEquals(numberArrows2, 0);
	}

}
