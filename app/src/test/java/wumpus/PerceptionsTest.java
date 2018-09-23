package wumpus;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Cell;
import utils.CellUtils;

public class PerceptionsTest {

	Wumpus wumpus;

	@Before
	public void setUp() {
		wumpus = new Wumpus(5, 2, 2);
		wumpus.setPlayerLocation(new Cell(1, 1));
		wumpus.setFocusPlayer(FocusPlayer.NORTH);

		wumpus.setWumpusLocation(new Cell(2, 2));
		wumpus.setListStenchLocation(CellUtils.locateAdjacentCell(wumpus.getWumpusLocation(), wumpus.getBoxSize()));

		List<Cell> listPits = new ArrayList();
		Cell pitLocation = new Cell(1, 3);
		listPits.add(pitLocation);
		wumpus.setListPitsLocation(listPits);
		wumpus.setListBreezeLocation(CellUtils.locateAdjacentCell(wumpus.getListPitsLocation(), wumpus.getBoxSize()));
	}

	@After
	public void tearDown() {
		wumpus = null;
	}

	@Test
	public void moveNearWumpus() {
		wumpus.play(MovesPlayer.MOVE_STRAIGHT);
		Assert.assertEquals(true, wumpus.isNearWumpus());
	}

	@Test
	public void moveMatchWumpus() {
		wumpus.play(MovesPlayer.MOVE_STRAIGHT);
		wumpus.play(MovesPlayer.TURN_RIGHT);
		Assert.assertEquals(true, wumpus.isMatchWumpus());
	}

	@Test
	public void moveNearPit() {
		wumpus.play(MovesPlayer.MOVE_STRAIGHT);
		Assert.assertEquals(true, wumpus.isNearPit());
	}

	@Test
	public void moveMatchPit() {
		wumpus.play(MovesPlayer.MOVE_STRAIGHT);
		wumpus.play(MovesPlayer.MOVE_STRAIGHT);
		Assert.assertEquals(true, wumpus.isMatchPit());
	}

}
