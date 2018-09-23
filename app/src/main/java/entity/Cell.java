package entity;

/**
 * 
 * @author robtub
 *
 */
public class Cell {
	private Integer x;
	
	private Integer y;

	public Cell(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Cell(Cell cell) {
		this.x = cell.x;
		this.y = cell.y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	public void movex(Integer value) {
		this.x = this.x + value;
	}
	
	public void movey(Integer value) {
		this.y = this.y + value;
	}
	
	public static Cell newInstance(Cell cell) {
		return new Cell(cell);
	}
	
}
