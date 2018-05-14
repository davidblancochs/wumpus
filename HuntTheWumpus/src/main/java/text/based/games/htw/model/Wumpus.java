/**
 * 
 */
package text.based.games.htw.model;

import java.util.ArrayList;
import java.util.List;

import text.based.games.htw.util.Constants;

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
	 * Indica si el wumpus está muerto
	 */
	private boolean isDead;

	/**
	 * Constructor para crear un Wumpus
	 */
	public Wumpus() {
		this.isDead = false;
	}

	/**
	 * Constructor para crear un Wumpus en una posición determinada
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
	 * Método que sirve para calcular las posiciones del Hedor del Wumpus
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
		
		if (this.getPosicion().getX()+1 < Constants.X_BOXES_NUMBER) {
			x = this.getPosicion().getX()+1;
			y = this.getPosicion().getY();
			hPosition = new Position(x, y);
			getStenchPositions().add(hPosition);
		}
		
		if (this.getPosicion().getY()+1 < Constants.Y_BOXES_NUMBER) {
			x = this.getPosicion().getX();
			y = this.getPosicion().getY()+1;
			hPosition = new Position(x, y);
			getStenchPositions().add(hPosition);
		}
		
	}
	
	
}
