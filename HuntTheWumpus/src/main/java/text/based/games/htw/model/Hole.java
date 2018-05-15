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
public class Hole extends Character {

	/**
	 * Posiciones de las brisas del hoyo
	 */
	private List<Position> breezePositions;

	/**
	 * Constructor para crear un Wumpus
	 */
	public Hole() {

	}
	
	/**
	 * Constructor para crear un hoyo en una posición determinada
	 */
	public Hole(int x, int y) {
		super(x, y);
		calculateBreezePositions();
	}


	/**
	 * @return the breezePositions
	 */
	public List<Position> getBreezePositions() {
		if (breezePositions == null) {
			breezePositions = new ArrayList<>();
		}
		return breezePositions;
	}

	/**
	 * @param breezePositions
	 *            the breezePositions to set
	 */
	public void setBreezePositions(List<Position> breezePositions) {
		this.breezePositions = breezePositions;
	}
	
	
	/**
	 * Método que sirve para calcular las posiciones de las brisas
	 * 
	 */
	public void calculateBreezePositions() {

		int x;
		int y;
		Position bPosition;
		
		if (this.getPosicion().getX()-1 >= 0) {
			x = this.getPosicion().getX()-1;
			y = this.getPosicion().getY();
			bPosition = new Position(x, y);
			getBreezePositions().add(bPosition);
		}
		
		if (this.getPosicion().getY()-1 >= 0) {
			x = this.getPosicion().getX();
			y = this.getPosicion().getY()-1;
			bPosition = new Position(x, y);
			getBreezePositions().add(bPosition);
		}
		
		x = this.getPosicion().getX()+1;
		y = this.getPosicion().getY();
		bPosition = new Position(x, y);
		getBreezePositions().add(bPosition);
	
		x = this.getPosicion().getX();
		y = this.getPosicion().getY()+1;
		bPosition = new Position(x, y);
		getBreezePositions().add(bPosition);
		
	}

}
