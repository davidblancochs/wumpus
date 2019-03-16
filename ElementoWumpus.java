package wumpus;

public class ElementoWumpus  extends ElementoMovil{
	 
	public static final String class_symbol = "W";
	
	//When the Wumpus is prompted into action, it kills the Hunter
	@Override
	public Game.Event action(Tablero t) {
		if(this.isAlive()) {
			ElementoCazador h = t.getHunter();
			h.kill();
			return Game.Event.HUNTER_KILLED_BY_WUMPUS;
		}else {
			return Game.Event.DEAD_WUMPUS_FOUND;
		}

	}

	//Wumpus can be sensed from adjacent cells
	@Override
	Game.Event indicio() {
		return Game.Event.SENSE_WUMPUS;
	}
	
	@Override
	public Game.Event move(Tablero t) {
		// Unimplemented, but in the future Wumpus can move to make the game more challengings
		return Game.Event.NO_EVENT;
	}
	
	@Override
	public String getClassSymbol(){
		return ElementoWumpus.class_symbol;
	}
}
