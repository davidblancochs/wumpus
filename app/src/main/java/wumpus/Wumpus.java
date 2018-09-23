package wumpus;

import java.util.List;

import entity.Cell;
import utils.CellUtils;
import utils.Utils;

public class Wumpus {

	private Boolean playing = true;
	private Boolean gameOver = false;

	private Integer boxSize;
	private Integer numberArrow;
	private Integer numberPits;

	// where is looking the player
	private FocusPlayer focusPlayer;
	private Cell playerLocation;

	private Cell wumpusLocation;
	private List<Cell> listStenchLocation;

	private List<Cell> listPitsLocation;
	private List<Cell> listBreezeLocation;

	private Cell treasureLocation;

	/**
	 * Configure the board
	 * 
	 * @param boxSize
	 * @param numberArrow
	 * @param numberPits
	 */
	public Wumpus(Integer boxSize, Integer numberArrow, Integer numberPits) {
		this.boxSize = boxSize;
		this.numberArrow = numberArrow;
		this.numberPits = numberPits;

		setFocusPlayer(FocusPlayer.NORTH);
		setPlayerLocation(new Cell(1, 1));
		// TODO generate aleatory the coordinates from all cells with
		// elements(Wumpus,pit,treasure), except playerLocation
		setWumpusLocation(new Cell(3, 3));
		setListStenchLocation(CellUtils.locateAdjacentCell(wumpusLocation, boxSize));
		setListPitsLocation(CellUtils.locatePit(numberPits));
		setListBreezeLocation(CellUtils.locateAdjacentCell(listPitsLocation, boxSize));
		setTreasureLocation(new Cell(2, 4));

		Utils.showMessage("Current player situation:" + Utils.showCell(playerLocation) + " " + focusPlayer);
	}

	public Boolean isPlaying() {
		return playing;
	}

	public void isPlaying(Boolean playing) {
		this.playing = playing;
	}

	public Boolean isGameOver() {
		return gameOver;
	}

	public void isGameOver(Boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Cell getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(Cell playerLocation) {
		this.playerLocation = playerLocation;
	}

	public Cell getWumpusLocation() {
		return wumpusLocation;
	}

	public void setWumpusLocation(Cell wumpusLocation) {
		this.wumpusLocation = wumpusLocation;
	}

	public List<Cell> getListPitsLocation() {
		return listPitsLocation;
	}

	public void setListPitsLocation(List<Cell> listPitsLocation) {
		this.listPitsLocation = listPitsLocation;
	}

	public Cell getTreasureLocation() {
		return treasureLocation;
	}

	public void setTreasureLocation(Cell treasureLocation) {
		this.treasureLocation = treasureLocation;
	}

	public Integer getBoxSize() {
		return boxSize;
	}

	public void setBoxSize(Integer boxSize) {
		this.boxSize = boxSize;
	}

	public Integer getNumberArrow() {
		return numberArrow;
	}

	public void setNumberArrow(Integer numberArrow) {
		this.numberArrow = numberArrow;
	}

	public Integer getNumberPits() {
		return numberPits;
	}

	public void setNumberPits(Integer numberPits) {
		this.numberPits = numberPits;
	}

	public FocusPlayer getFocusPlayer() {
		return focusPlayer;
	}

	public void setFocusPlayer(FocusPlayer focusPlayer) {
		this.focusPlayer = focusPlayer;
	}

	public List<Cell> getListBreezeLocation() {
		return listBreezeLocation;
	}

	public void setListBreezeLocation(List<Cell> listBreezeLocation) {
		this.listBreezeLocation = listBreezeLocation;
	}

	public List<Cell> getListStenchLocation() {
		return listStenchLocation;
	}

	public void setListStenchLocation(List<Cell> listStenchLocation) {
		this.listStenchLocation = listStenchLocation;
	}


	/**
	 * execute the new move selected by player
	 * 
	 * @param movePlayer
	 */
	public void play(MovesPlayer movePlayer) {
		Cell newPlayerCell = Cell.newInstance(playerLocation);
		Boolean movementCorrect = false;
		// Comments to show the coordinates of important cells
		// Utils.showMessage("old:"+ Utils.showCell(newPlayerCell) + " " +
		// getFocusPlayer());
		// if (wumpusLocation!= null)Utils.showMessage("wumpus:" +
		// Utils.showCell(wumpusLocation));
		// if (listStenchLocation!=
		// null)Utils.showMessage("listStenchLocation"+Utils.showListCell(listStenchLocation));
		// if (listPitsLocation!=
		// null)Utils.showMessage("listPitsLocation"+Utils.showListCell(listPitsLocation));
		// if (listBreezeLocation!=
		// null)Utils.showMessage("listBreezeLocation"+Utils.showListCell(listBreezeLocation));
		// if (treasureLocation!= null) Utils.showMessage("treasure:" +
		// Utils.showCell(treasureLocation));

		if (movePlayer.equals(MovesPlayer.MOVE_STRAIGHT) || movePlayer.equals(MovesPlayer.TURN_LEFT)
				|| movePlayer.equals(MovesPlayer.TURN_RIGHT)) {
			movementCorrect = calculateCellDestination(movePlayer, newPlayerCell);
		} else {
			movementCorrect = launchArrow();
		}

		if (movementCorrect) {
			playerLocation = newPlayerCell;
			checkPerceptions();
		} else {
			Utils.showMessage("Invalid movement. You cannot leave the board. Try again");
		}

		if (playerLocation.getX() == 1 && playerLocation.getY() == 1 && treasureLocation == null) {
			Utils.showMessage("You Win!!!");
			isGameOver(true);
		}

		// Comments to show the coordinates of important cells
		Utils.showMessage("Current player situation:" + Utils.showCell(playerLocation) + " " + getFocusPlayer());
	}

	/**
	 * Check the perceptions that happen where the player get a new cell
	 */
	private void checkPerceptions() {
		if (shockWall()) {
			Utils.showMessage("You have hit with the wall");
		}
		if (findTreasure()) {
			treasureLocation = null;
			Utils.showMessage("Congratulations. Treasure located");
		} else if (isMatchWumpus()) {
			Utils.showMessage("You lost. Wumpus find you");
			Utils.showMessage("Game over");
			isGameOver(true);
		} else if (isMatchPit()) {
			Utils.showMessage("You lost. Fall in Pit");
			Utils.showMessage("Game over");
			isGameOver(true);
		} else if (isNearWumpus()) {
			Utils.showMessage("Danger. Wumpus stench");
		} else if (isNearPit()) {
			Utils.showMessage("Danger. Pit breeze");
		}
	}

	public boolean isNearPit() {
		return listBreezeLocation.stream()
				.anyMatch(cell -> cell.getX() == playerLocation.getX() && cell.getY() == playerLocation.getY());
	}

	public boolean isNearWumpus() {
		return wumpusLocation != null && listStenchLocation.stream()
				.anyMatch(cell -> cell.getX() == playerLocation.getX() && cell.getY() == playerLocation.getY());
	}

	public boolean isMatchPit() {
		return listPitsLocation.stream()
				.anyMatch(cell -> cell.getX() == playerLocation.getX() && cell.getY() == playerLocation.getY());
	}

	public boolean isMatchWumpus() {
		return wumpusLocation != null && wumpusLocation.getX() == playerLocation.getX()
				&& wumpusLocation.getY() == playerLocation.getY();
	}

	public boolean findTreasure() {
		return treasureLocation != null && treasureLocation.getX() == playerLocation.getX()
				&& treasureLocation.getY() == playerLocation.getY();
	}

	public boolean shockWall() {
		return playerLocation.getX() == 1 || playerLocation.getX() == boxSize || playerLocation.getY() == 1
				|| playerLocation.getY() == boxSize;
	}

	/**
	 * Calculate the effect to launch an arrow
	 */
	public Boolean launchArrow() {
		Boolean result = false;
		if (wumpusLocation != null) {
			if (numberArrow > 0) {
				if (getFocusPlayer().equals(FocusPlayer.NORTH) && getWumpusLocation().getX() == playerLocation.getX()
						&& getWumpusLocation().getY() > playerLocation.getY()) {
					wumpusLocation = null;
				} else if (getFocusPlayer().equals(FocusPlayer.SOUTH)
						&& getWumpusLocation().getX() == playerLocation.getX()
						&& getWumpusLocation().getY() < playerLocation.getY()) {
					wumpusLocation = null;
				} else if (getFocusPlayer().equals(FocusPlayer.EAST)
						&& getWumpusLocation().getY() == playerLocation.getY()
						&& getWumpusLocation().getX() > playerLocation.getX()) {
					wumpusLocation = null;
				} else if (getFocusPlayer().equals(FocusPlayer.WEST)
						&& getWumpusLocation().getY() == playerLocation.getY()
						&& getWumpusLocation().getX() < playerLocation.getX()) {
					wumpusLocation = null;
				}
				if (wumpusLocation == null) {
					Utils.showMessage("Wumpus hunted");
				}
				numberArrow--;
				result = true;
			} else {
				Utils.showMessage("Invalid movement. You dont have any arrow.");
				result = false;
			}
		}else {
			Utils.showMessage("Invalid movement. The Wumpus has already been hunted.");
			result = false;
		}
		return result;
	}

	/**
	 * Calculate the cell where will move the player
	 * 
	 * @param command
	 *            where move the player (up, left, right)
	 * @param newPlayerCell
	 *            cell origin to move
	 * @return
	 */
	public Boolean calculateCellDestination(MovesPlayer command, Cell newPlayerCell) {
		Boolean result = false;
		switch (command) {
		case MOVE_STRAIGHT: // 1
			if (getFocusPlayer().equals(FocusPlayer.NORTH)) {
				newPlayerCell.movey(1);
			} else if (getFocusPlayer().equals(FocusPlayer.SOUTH)) {
				newPlayerCell.movey(-1);
			} else if (getFocusPlayer().equals(FocusPlayer.EAST)) {
				newPlayerCell.movex(1);
			} else if (getFocusPlayer().equals(FocusPlayer.WEST)) {
				newPlayerCell.movex(-1);
			}
			result = true;
			break;
		case TURN_LEFT: // 2
			if (getFocusPlayer().equals(FocusPlayer.NORTH)) {
				newPlayerCell.movex(-1);
				setFocusPlayer(FocusPlayer.WEST);
			} else if (getFocusPlayer().equals(FocusPlayer.SOUTH)) {
				newPlayerCell.movex(1);
				setFocusPlayer(FocusPlayer.EAST);
			} else if (getFocusPlayer().equals(FocusPlayer.EAST)) {
				newPlayerCell.movey(1);
				setFocusPlayer(FocusPlayer.NORTH);
			} else if (getFocusPlayer().equals(FocusPlayer.WEST)) {
				newPlayerCell.movey(-1);
				setFocusPlayer(FocusPlayer.SOUTH);
			}
			result = true;
			break;
		case TURN_RIGHT: // 3
			if (getFocusPlayer().equals(FocusPlayer.NORTH)) {
				newPlayerCell.movex(1);
				setFocusPlayer(FocusPlayer.EAST);
			} else if (getFocusPlayer().equals(FocusPlayer.SOUTH)) {
				newPlayerCell.movex(-1);
				setFocusPlayer(FocusPlayer.WEST);
			} else if (getFocusPlayer().equals(FocusPlayer.EAST)) {
				newPlayerCell.movey(-1);
				setFocusPlayer(FocusPlayer.SOUTH);
			} else if (getFocusPlayer().equals(FocusPlayer.WEST)) {
				newPlayerCell.movey(1);
				setFocusPlayer(FocusPlayer.NORTH);
			}
			result = true;
			break;
		default:
			result = false;
		}
		return result && !(newPlayerCell.getY() < 1 || newPlayerCell.getY() > boxSize || newPlayerCell.getX() < 1
				|| newPlayerCell.getX() > boxSize);

	}

}
