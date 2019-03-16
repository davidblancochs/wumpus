package wumpus;

abstract class ElementoMovil extends ElementoCasilla {
	boolean alive;
	
	public ElementoMovil() {
		super();
		this.alive = true;
	}
	abstract Game.Event move(Tablero t);
	void kill() {
		this.alive = false;
	}
	public boolean isAlive() {
		return this.alive;
	}
	public boolean isDead() {
		return !this.alive;
	}
}
