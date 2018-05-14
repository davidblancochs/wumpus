/**
 * 
 */
package text.based.games.htw.model;

/**
 * @author Alexis
 *
 */
public class Character {
	
	/**
	 * Posición del personaje en el tablero
	 */
	private Position posicion;
	
	
	/**
	 * Constructor vacío
	 */
	public Character() {
		
	}

	/**
	 * Constructor para crear un personaje en una posición determinada.
	 */
	public Character(int x, int y) {
		this.posicion = new Position(x, y);
	}

	/**
	 * @return the posicion
	 */
	public Position getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(Position posicion) {
		this.posicion = posicion;
	}
	
}