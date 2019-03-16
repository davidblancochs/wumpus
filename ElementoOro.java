package wumpus;

public class ElementoOro extends ElementoCasilla {

	public static final String class_symbol = "G";
	
	//When Gold is activated, Hunter gets gold.
	@Override
	public Game.Event action(Tablero t) {
		ElementoCazador h = t.getHunter();
		if(! h.hasGold()) {
			h.setGold();
			return Game.Event.HUNTER_GOT_GOLD;
		}else {
			return Game.Event.NO_EVENT;
		}
	}
	//Gold can't be sensed from adjacent cells. Therefore this method returns NO_ACTION
	@Override
	public Game.Event indicio() {
		return Game.Event.NO_EVENT;
	}
	
	@Override
	public String getClassSymbol(){
		return ElementoOro.class_symbol;
	}

}
