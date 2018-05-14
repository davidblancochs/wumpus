/**
 * 
 */
package text.based.games.htw.util;

/**
 * Clase que contiene diferentes tipos parametrizados
 * 
 * @author Alexis
 */
public class Types {

	
	/**
	 * Tipos de personajes que hay en una casilla
	 *
	 */
	public enum character {
		HUNTER, WUMPUS, HOLE
	}
	
	
	/**
	 * Tipos de percepciones que hay en una casilla
	 * Nada, Hedor, brisa, resplandor del oro, muro, rujido del Wumpus
	 *
	 */
	public enum perceptions {
		NOTHING, STENCH, BREEZE, GOLD, WALL, ROAR
	}
	
	
	/**
	 * Tipos de direcciones:
	 * L=Izquierda, R=Derecha, U=Arriba, D=Abajo
	 *
	 */
	public enum direction {
		L, R, U, D
	}
	
}
