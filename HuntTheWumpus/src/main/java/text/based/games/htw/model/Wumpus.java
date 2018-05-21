/**
 * 
 */
package text.based.games.htw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexis
 *
 */
public class Wumpus extends Character {

	/**
	 * Posiciones del Hedor del Wumpus
	 */
	private List<Position> stenchPositions;

	/**
	 * Indica si el wumpus est� muerto
	 */
	private boolean isDead;

	/**
	 * Constructor para crear un Wumpus
	 */
	public Wumpus() {
		this.isDead = false;
	}

	/**
	 * Constructor para crear un Wumpus en una posici�n determinada
	 */
	public Wumpus(int x, int y) {
		super(x, y);
		calculateStenchPositions();
		this.isDead = false;
	}

	/**
	 * @return the stenchPositions
	 */
	public List<Position> getStenchPositions() {
		if (stenchPositions == null) {
			stenchPositions = new ArrayList<>();
		}
		return stenchPositions;
	}

	/**
	 * @param stenchPositions
	 *            the stenchPositions to set
	 */
	public void setStenchPositions(List<Position> stenchPositions) {
		this.stenchPositions = stenchPositions;
	}

	/**
	 * @return the isDead
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * @param isDead
	 *            the isDead to set
	 */
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	
	/**
	 * M�todo que sirve para calcular las posiciones del Hedor del Wumpus
	 * 
	 */
	public void calculateStenchPositions() {

		int x;
		int y;
		Position hPosition;
		
		if (this.getPosicion().getX()-1 >= 0) {
			x = this.getPosicion().getX()-1;
			y = this.getPosicion().getY();
			hPosition = new Position(x, y);
			getStenchPositions().add(hPosition);
		}
		
		if (this.getPosicion().getY()-1 >= 0) {
			x = this.getPosicion().getX();
			y = this.getPosicion().getY()-1;
			hPosition = new Position(x, y);
			getStenchPositions().add(hPosition);
		}
		
		x = this.getPosicion().getX()+1;
		y = this.getPosicion().getY();
		hPosition = new Position(x, y);
		getStenchPositions().add(hPosition);
	
		x = this.getPosicion().getX();
		y = this.getPosicion().getY()+1;
		hPosition = new Position(x, y);
		getStenchPositions().add(hPosition);
		
	}
	
	
}
