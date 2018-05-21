/**
 * 
 */
package text.based.games.htw.model;

/**
 * @author Alexis
 *
 */
public class Position {
	
	/**
	 * Posición en el eje X
	 */
	private int x; 
	
	/**
	 * Posición en el eje Y
	 */
	private int y;

	
	/**
	 * Contructor para crear una posición en el tablero
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}


	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	
	/**
	 * Método que indica si el Wumpus está en la posición pasada como parámetro
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean matchPosition (int x, int y){
		
		return this.getX() == x && this.getY() == y;
		
	}

}
