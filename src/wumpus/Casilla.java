package wumpus;

import wumpus.data.Direccion;

public class Casilla {
	
	private final int id;
	private final int fila;
	private final int columna;


	private boolean inicial = false;
	private boolean humpus = false;
	private boolean pozo = false;
	private boolean lingote = false;

	private Casilla izda;
	private Casilla drcha;
	private Casilla arriba;
	private Casilla abajo;
	
	public Casilla(int fila, int columna, int id) {
		this.id = id;
		this.fila = fila;
		this.columna = columna;
	}

	public int getId() {
		return id;
	}
	
	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}
	
	public boolean isInicial() {
		return inicial;
	}

	public void setInicial(boolean inicial) {
		this.inicial = inicial;
	}

	public boolean isHumpus() {
		return humpus;
	}

	public void setHumpus(boolean humpus) {
		this.humpus = humpus;
	}

	public boolean isPozo() {
		return pozo;
	}

	public void setPozo(boolean pozo) {
		this.pozo = pozo;
	}
	
	public boolean isLingote() {
		return lingote;
	}

	public void setLingote(boolean lingote) {
		this.lingote = lingote;
	}
	
	public Casilla getAdyacente(Direccion direccion) {
		Casilla ret = null;
		switch (direccion.getId()) {
		case Direccion.ID_DRCHA:
			ret = getDrcha();
			break;
		case Direccion.ID_IZDA:
			ret = getIzda();
			break;
		case Direccion.ID_ARRIBA:
			ret = getArriba();
			break;
		case Direccion.ID_ABAJO:
			ret = getAbajo();
			break;
		}
		return ret;
	}
	
	public Casilla getIzda() {
		return izda;
	}

	public void setIzda(Casilla izda) {
		this.izda = izda;
	}

	public Casilla getDrcha() {
		return drcha;
	}

	public void setDrcha(Casilla drcha) {
		this.drcha = drcha;
	}

	public Casilla getArriba() {
		return arriba;
	}

	public void setArriba(Casilla arriba) {
		this.arriba = arriba;
	}

	public Casilla getAbajo() {
		return abajo;
	}

	public void setAbajo(Casilla abajo) {
		this.abajo = abajo;
	}
	
	public String toString() {
		
		if (humpus) {
			return "W";
		} else if (lingote) {
			return "L";
		} else if (pozo) {
			return "P";
		} else return "O";
		
	}	

}
