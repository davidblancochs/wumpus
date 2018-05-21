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
	 * Posici�n en el eje X
	 */
	private int x; 
	
	/**
	 * Posici�n en el eje Y
	 */
	private int y;

	
	/**
	 * Contructor para crear una posici�n en el tablero
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
	 * M�todo que indica si el Wumpus est� en la posici�n pasada como par�metro
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean matchPosition (int x, int y){
		
		return this.getX() == x && this.getY() == y;
		
	}

}
