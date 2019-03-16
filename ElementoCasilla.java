package wumpus;
abstract class ElementoCasilla {

	private int x, y; //Coordinates representing current location in the map
	
	public ElementoCasilla(){
		this.x = 0;
		this.y = 0;			
	}
	
	public ElementoCasilla(int x, int y) {
		this.x = x;
		this.y = y;		
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;		
	}
	
	abstract Game.Event action(Tablero t); //When Hunter sets foot into a cell, all ElementoCasilla objects in it (such as Wells or the Wumpus) will call their version of this method
	abstract Game.Event indicio(); // An ElementoCasilla object that can be sensed from adjacent cells will use this method to send "clues" to Hunter
	abstract String getClassSymbol(); //This will be useful for drawing the map, and can be used for future GUI implementation as well
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getCoord(int coord) {
		if(coord == 0) {
			return this.x;
		}else {
			return this.y;
		}
	}
	
}
