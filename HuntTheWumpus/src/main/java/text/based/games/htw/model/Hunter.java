/**
 * 
 */
package text.based.games.htw.model;

import text.based.games.htw.util.Types;

/**
 * @author Alexis
 *
 */
public class Hunter extends Character {


	/**
	 * Atributo que indica si el cazador encontr� el oro
	 */
	private boolean findGold;
	
	/**
	 * N�mero de flechas de las que dispone el cazador
	 */
	private int arrows;
	
	/**
	 * Direcci�n a la que apunta el cazador
	 */
	private Types.direction direction;
	
	/**
	 * Indica si el cazador ha muerto
	 */
	private boolean isDead;

	/**
	 * Constructor para crear un cazador con un n�mero de flechas determinado.
	 */
	public Hunter(int arrows) {
		this.arrows = arrows;
		isDead = false;
		findGold = false;
	}

	/**
	 * Constructor para crear al cazador en una posici�n determinada con un n�mero de flechas determinado.
	 * La direcci�n por defecto ser� hacia arriba.
	 */
	public Hunter(int arrows, int x, int y) {
		super(x, y);
		this.arrows = arrows;
		isDead = false;
		findGold = false;
	}


	/**
	 * @return the arrows
	 */
	public int getArrows() {
		return arrows;
	}

	/**
	 * @param arrows
	 *            the arrows to set
	 */
	public void setArrows(int arrows) {
		this.arrows = arrows;
	}

	/**
	 * @return the direction
	 */
	public Types.direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Types.direction direction) {
		this.direction = direction;
	}

	/**
	 * @return the isDead
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * @param isDead the isDead to set
	 */
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	/**
	 * @return the findGold
	 */
	public boolean isFindGold() {
		return findGold;
	}

	/**
	 * @param findGold the findGold to set
	 */
	public void setFindGold(boolean findGold) {
		this.findGold = findGold;
	}
	
}
