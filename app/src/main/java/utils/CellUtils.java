package utils;

import java.util.ArrayList;
import java.util.List;

import entity.Cell;

/**
 * 
 * @author robtub
 *
 */
public class CellUtils {

	/**
	 * get a cell list with all adjacent cells
	 * 
	 * @param central
	 *            cell
	 * @return
	 */
	public static List<Cell> locateAdjacentCell(Cell cellLocation, Integer boxSize) {
		List<Cell> listAdjacentCell = new ArrayList<>();

		Cell pitLocation = null;
		if (cellLocation.getY() + 1 <= boxSize) {
			pitLocation = new Cell(cellLocation.getX(), cellLocation.getY() + 1);
			listAdjacentCell.add(pitLocation);
		}
		if (cellLocation.getY() - 1 > 0) {
			pitLocation = new Cell(cellLocation.getX(), cellLocation.getY() - 1);
			listAdjacentCell.add(pitLocation);
		}
		if (cellLocation.getX() + 1 <= boxSize) {
			pitLocation = new Cell(cellLocation.getX() + 1, cellLocation.getY());
			listAdjacentCell.add(pitLocation);
		}
		if (cellLocation.getX() - 1 > 0) {
			pitLocation = new Cell(cellLocation.getX() - 1, cellLocation.getY());
			listAdjacentCell.add(pitLocation);
		}

		return listAdjacentCell;
	}

	/**
	 * list of cells adjacent the cells as param
	 * 
	 * @param listPitsLocation
	 * @return
	 */
	public static List<Cell> locateAdjacentCell(List<Cell> listPitsLocation, Integer boxSize) {
		List<Cell> listAdjacentCell = new ArrayList<>();
		for (Cell cell : listPitsLocation) {
			listAdjacentCell.addAll(locateAdjacentCell(cell, boxSize));
		}
		return listAdjacentCell;
	}

	/**
	 * generate list with the number of Pits
	 * 
	 * @param numberPits
	 * @return
	 */
	public static List<Cell> locatePit(Integer numberPits) {
		List<Cell> listPits = new ArrayList<>();

		// TODO aleatory
		Cell pitLocation = new Cell(1, 4);
		listPits.add(pitLocation);

		pitLocation = new Cell(1, 5);
		listPits.add(pitLocation);

		return listPits;
	}
	
}
