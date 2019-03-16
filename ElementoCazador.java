package wumpus;
public class ElementoCazador extends ElementoMovil{
	public static final String class_symbol = "H";
	private boolean hasGold;
	private boolean exited;
	private int arrows;
	private Tablero.Direction direction;
	
	public ElementoCazador(int arrows) {
		this.hasGold = false;
		this.exited = false;
		this.arrows = arrows;
		this.direction = Tablero.Direction.UP; //Default value
	}
	
	//Hunter is not activated passively only for being into a cell; therefore, this method is empty
	@Override
	public Game.Event action(Tablero t) {
		return Game.Event.NO_EVENT;
	}
	
	//This obviously returns NO_EVENT
	@Override
	public  Game.Event indicio() {
		return Game.Event.NO_EVENT;
	}
	
	//Hunter can move to adjacent cells upon User request. He doesn't move if he hits a wall (end of the map)
	@Override
	public Game.Event move(Tablero t) {
		int newx = this.getX();
		int newy = this.getY();
		Tablero.Direction dir = this.getDirection();
		
		if(t.pointingExit(newx, newy, dir)) {
			return this.exit();
		}
		else {
			switch(dir) {
			case UP:
				newx++;
				break;
			case DOWN:
				newx--;
				break;
			case RIGHT:
				newy++;
				break;
			case LEFT:
				newy--;
				break;
			default:
				break;
			}
			if(t.moveObjectToCell(this, newx, newy)) {
				return Game.Event.HUNTER_MOVED;
			}else {
				return Game.Event.HUNTER_HIT_WALL;
			}
		}
		
	}
	
	//When Hunter finds the Gold, hasGold becomes true
	public void setGold() {
		this.hasGold = true;
	}
	//When Hunter exits the cave
	public boolean hasExited() {
		return this.exited;
	}
	public boolean hasGold() {
		return this.hasGold;
	}
	public int getArrows() {
		return this.arrows;
	}
	public Tablero.Direction getDirection(){
		return this.direction;
	}
	//Hunter turns direction upon User request. Subsequently, arrows and movements will be directed towards the new direction.
	public void turn(Tablero.Direction d) {
		this.direction = d;
	}
	//Hunter throws arrow in the direction he is facing. Arrows are only stopped by walls, not by wells. 
	public Game.Event shootArrow(Tablero t) {
		if(this.arrows <= 0) {
			return Game.Event.NO_ARROWS_LEFT;
		}else {
			this.arrows--;
			ElementoWumpus w = t.getWumpus();
			int wx = w.getX();
			int wy = w.getY();
			int x = this.getX();
			int y = this.getY();
			Tablero.Direction direction = this.getDirection();
			// if the arrow goes in the direction of the wumpus
			if((x == wx && y < wy && direction == Tablero.Direction.RIGHT) | 
					(x == wx && y > wy && direction == Tablero.Direction.LEFT) | 
					(y == wy && x < wx && direction == Tablero.Direction.UP) | 
					(y == wy && x > wx && direction == Tablero.Direction.DOWN)) {
				if(w.isAlive()) {
					w.kill();
					return Game.Event.WUMPUS_KILLED;
				}else {
					return Game.Event.WUMPUS_ALREADY_DEAD;
				}
			}else {
				return Game.Event.ARROW_DIDNT_HIT;
			}
		}		
	}
	public Game.Event exit(){
		this.exited = true;	
		return Game.Event.EXITED;
	}
	//String representation of the state of the Hunter (number of arrows left, current direction, etc)
	public String toString() {
		String s = "";
		if(this.isAlive()) {			
			s = s + "Still alive... Looking " + this.direction.getCardinalDir() + "\n" + Integer.toString(this.getArrows()) + " arrows left. ";
			if(this.hasGold()) {
				s = s + "And I got the Gold! Let's find the exit.";
			}else {
				s = s + "Now I have to find the gold.";
			}				
		}else {
			s = "You are dead.";
			if(this.hasGold()) {
				s = s + " At least you found the gold...";
			}
		}
		return s;
	}
	@Override
	public String getClassSymbol(){
		return ElementoCazador.class_symbol;
	}

}
