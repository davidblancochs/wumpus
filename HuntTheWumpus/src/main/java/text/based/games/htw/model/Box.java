/**
 * 
 */
package text.based.games.htw.model;

/**
 * @author Alexis
 *
 */
public class Box {

	/**
	 * Atributo que indica si la casilla está descubierta
	 */
	private boolean covered;
	
	/**
	 * Atributo que indica si está el Wumpus en la casilla
	 */
	private boolean hasWumpus;
	
	/**
	 * Atributo que indica si hay hedor en la casilla
	 */
	private boolean hasStench;
	
	/**
	 * Atributo que indica si hay un hoyo en la casilla
	 */
	private boolean hasHole;
	
	/**
	 * Atributo que indica si hay brisa en la casilla
	 */
	private boolean hasBreeze;
	
	/**
	 * Atributo que indica si está el ORO en la casilla
	 */
	private boolean hasGold;
	
	/**
	 * Atributo que indica si la casilla es el punto de partida
	 */
	private boolean isStartingPoint;
	
	/**
	 * Constructor para crear una casilla cubierta por defecto
	 */
	public Box() {
		covered = true;
		isStartingPoint = false;
	}

	/**
	 * Constructor para crear una casilla indicando si está descubierda o no
	 */
	public Box(boolean covered) {
		this.covered = covered;
		isStartingPoint = false;
	}
	
	/**
	 * @return the covered
	 */
	public boolean isCovered() {
		return covered;
	}

	/**
	 * @param covered the covered to set
	 */
	public void setCovered(boolean covered) {
		this.covered = covered;
	}

	/**
	 * @return the hasWumpus
	 */
	public boolean hasWumpus() {
		return hasWumpus;
	}

	/**
	 * @param hasWumpus the hasWumpus to set
	 */
	public void setHasWumpus(boolean hasWumpus) {
		this.hasWumpus = hasWumpus;
	}

	/**
	 * @return the hasHole
	 */
	public boolean hasHole() {
		return hasHole;
	}

	/**
	 * @param hasHole the hasHole to set
	 */
	public void setHasHole(boolean hasHole) {
		this.hasHole = hasHole;
	}

	/**
	 * @return the hasGold
	 */
	public boolean hasGold() {
		return hasGold;
	}

	/**
	 * @param hasGold the hasGold to set
	 */
	public void setHasGold(boolean hasGold) {
		this.hasGold = hasGold;
	}

	/**
	 * @return the hasStench
	 */
	public boolean hasStench() {
		return hasStench;
	}

	/**
	 * @param hasStench the hasStench to set
	 */
	public void setHasStench(boolean hasStench) {
		this.hasStench = hasStench;
	}

	/**
	 * @return the hasBreeze
	 */
	public boolean hasBreeze() {
		return hasBreeze;
	}

	/**
	 * @param hasBreeze the hasBreeze to set
	 */
	public void setHasBreeze(boolean hasBreeze) {
		this.hasBreeze = hasBreeze;
	}

	/**
	 * @return the isStartingPoint
	 */
	public boolean isStartingPoint() {
		return isStartingPoint;
	}

	/**
	 * @param isStartingPoint the isStartingPoint to set
	 */
	public void setStartingPoint(boolean isStartingPoint) {
		this.isStartingPoint = isStartingPoint;
	}
	
}
