package wumpus;

public class ElementoPozo extends ElementoCasilla{
	public static final String class_symbol = "O";
	
	//A Well kills the Hunter when it is activated
	@Override
	public Game.Event action(Tablero t) {
		ElementoCazador h = t.getHunter();
		h.kill();
		return Game.Event.HUNTER_KILLED_BY_WELL;
	}

	//Wells can be sensed from adjacent cells
	@Override
	public Game.Event indicio() {
		return Game.Event.SENSE_WELL;
	}
	@Override
	public String getClassSymbol(){
		return ElementoPozo.class_symbol;
	}
}
