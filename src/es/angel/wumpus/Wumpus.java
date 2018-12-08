package es.angel.wumpus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Wumpus {
	
	private int maxCeldas;
	private int numeroPozos;
	private int numeroFlechas;
	
	public enum Celda {
		VACIO,
		POZO,
		ORO,
		WUMPUS
	}
	
	private Celda[][] tablero;
	
	private Posicion posCazador;
	
	private boolean encontradoOro;
	private boolean wumpusMuerto;
	private boolean cazadorVivo;
	
	public enum Direccion {
		IZQUIERDA,
		DERECHA,
		ARRIBA,
		ABAJO;
	};
	
	private Direccion direccion;
	 
	public Wumpus(int celdas, int pozos, int flechas) {
		this.maxCeldas = celdas;
		this.numeroPozos = pozos;
		this.numeroFlechas = flechas;
	}
	
	/**
	 * Colocamos al cazador en la posicion 0,0 del tablero y situamos a las demás
	 * elementos aleatoriamente de forma que no coincidan
	 * 
	 * @return Las percepciones iniciales del cazador
	 */
	public List<Percepcion> init() {
		// Inicializamos el tablero con todas las celdas vacias
		tablero = new Celda[maxCeldas][maxCeldas];
		for (int i = 0; i < maxCeldas; i++) {
			for (int j = 0; j < maxCeldas; j++) {
				tablero[i][j] = Celda.VACIO;
			}
		}
		
		// Para que genere aleatoriamente las posiciones de los elementos del tablero
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		
		// Posicion del cazador (inicialmente en la casilla 0,0)
		posCazador = new Posicion();
		
		// Posicion del wumbus
		boolean colocado = false;
		while (!colocado) {
			Posicion posWumpus = new Posicion(random.nextInt(maxCeldas), random.nextInt(maxCeldas));
			if (Celda.VACIO.equals(tablero[posWumpus.getX()][posWumpus.getY()])) {
				tablero[posWumpus.getX()][posWumpus.getY()] = Celda.WUMPUS;
				colocado = true;
			}
		}
		
		// Posicion del oro
		colocado = false;
		while (!colocado) {
			Posicion posOro = new Posicion(random.nextInt(maxCeldas), random.nextInt(maxCeldas));
			if (Celda.VACIO.equals(tablero[posOro.getX()][posOro.getY()])) {
				tablero[posOro.getX()][posOro.getY()] = Celda.ORO;
				colocado = true;
			}
		}
		
		// Posicion de los pozo
		int pozosColocados = 0;
		while (pozosColocados < numeroPozos) {
			colocado = false;
			while (!colocado) {
				Posicion posPozo = new Posicion(random.nextInt(maxCeldas), random.nextInt(maxCeldas));
				if (Celda.VACIO.equals(tablero[posPozo.getX()][posPozo.getY()])) {
					tablero[posPozo.getX()][posPozo.getY()] = Celda.POZO;
					pozosColocados++;
					colocado = true;
				}
			}	
		}
		
		// Inicializamos variables
		encontradoOro = false;
		wumpusMuerto = false;
		cazadorVivo = true;
		direccion = Direccion.DERECHA;
		
		return obtenerPercepciones();
	}
	
	/**
	 * Colocamos al cazador en la posicion 0,0 del tablero 
	 * y situamos a las demás elementos en las posiciones dadas
	 * 
	 * @param posWumpus
	 * @param posOro
	 * @param listaPosPozos
	 */
	public void init(Posicion posWumpus, Posicion posOro, List<Posicion> listaPosPozos) {
		// Inicializamos el tablero con todas las celdas vacias
		tablero = new Celda[maxCeldas][maxCeldas];
		for (int i = 0; i < maxCeldas; i++) {
			for (int j = 0; j < maxCeldas; j++) {
				tablero[i][j] = Celda.VACIO;
			}
		}
		
		// Posicion del cazador (inicialmente en la casilla 0,0)
		posCazador = new Posicion();
		
		// Posicion del wumbus
		tablero[posWumpus.getX()][posWumpus.getY()] = Celda.WUMPUS;
		
		// Posicion del oro
		tablero[posOro.getX()][posOro.getY()] = Celda.ORO;
		
		// Posicion de los pozo
		int pozosColocados = 0;
		while (pozosColocados < numeroPozos) {
			tablero[listaPosPozos.get(pozosColocados).getX()][listaPosPozos.get(pozosColocados).getY()] = Celda.POZO;
			pozosColocados++;
		}
		
		// Inicializamos variables
		encontradoOro = false;
		wumpusMuerto = false;
		cazadorVivo = true;
		direccion = Direccion.DERECHA;
	}
	
	/** 
	 * Obtiene las percepciones del cazador de la posicion actual en la que se encuentra
	 * 
	 * @return
	 */
	public List<Percepcion> obtenerPercepciones() {
		List<Percepcion> listaPercepciones = new ArrayList<>();
		
		// Posicion Actual
		Celda celda = tablero[posCazador.getX()][posCazador.getY()];
		if (Celda.ORO.equals(celda)) {
			listaPercepciones.add(Percepcion.ORO);
			encontradoOro = true;
		}
		else if (Celda.WUMPUS.equals(celda) && !wumpusMuerto) {
			listaPercepciones.add(Percepcion.WUMPUS);
			cazadorVivo = false;
		}
		else if (Celda.POZO.equals(celda)) {
			listaPercepciones.add(Percepcion.POZO);
			cazadorVivo = false;
		}
				
		// Posicion Arriba
		if (posCazador.getY() + 1 < maxCeldas) {
			celda = tablero[posCazador.getX()][posCazador.getY() + 1];
			Percepcion percepcion = obtenerPercepcion(celda);
			if (percepcion != null) {
				listaPercepciones.add(percepcion);
			}
		}
		
		// Posicion Abajo
		if (posCazador.getY() - 1 >= 0) {
			celda = tablero[posCazador.getX()][posCazador.getY() - 1];
			Percepcion percepcion = obtenerPercepcion(celda);
			if (percepcion != null) {
				listaPercepciones.add(percepcion);
			}
		}
		
		// Posicion Derecha
		if (posCazador.getX() + 1 < maxCeldas) {
			celda = tablero[posCazador.getX() + 1][posCazador.getY()];
			Percepcion percepcion = obtenerPercepcion(celda);
			if (percepcion != null) {
				listaPercepciones.add(percepcion);
			}
		}
		
		// Posicion Izquierda
		if (posCazador.getX() - 1 >= 0) {
			celda = tablero[posCazador.getX() - 1][posCazador.getY()];
			Percepcion percepcion = obtenerPercepcion(celda);
			if (percepcion != null) {
				listaPercepciones.add(percepcion);
			}
		}
		
		return listaPercepciones;
	}
	
	/**
	 * Obtiene la percepcion en los cuadros adyacentes
	 * 
	 * @param celda
	 * @return
	 */
	private Percepcion obtenerPercepcion(Celda celda) {
		Percepcion percepcion = null;
		if (Celda.WUMPUS.equals(celda)) {
			percepcion = Percepcion.HEDOR;
		}
		else if (Celda.POZO.equals(celda)) {
			percepcion = Percepcion.REFLEJO;
		}
		return percepcion;
	}
	
	/**
	 * Indica si el cazador está vivo (no esta en la misma casilla que WUMBUS ni POZO)
	 * 
	 * @param listaPercepciones
	 * @return
	 */
	public boolean cazadorVivo() {
		return cazadorVivo;
	}
	
	/**
	 * Indica si el cazador tiene flechas. En caso afirmativo podrá ejecutar accion de Lanzar Flechas
	 * @return
	 */
	public boolean cazadorTieneFlechas() {
		return numeroFlechas > 0;
	}
	
	/**
	 * Indica si el cazador está en la casilla de salida. 
	 * En caso afirmativo podrá ejecutar accion de Salir
	 * @return
	 */
	public boolean cazadorEstaEnCasillaSalida() {
		return posCazador.getX() == 0 && posCazador.getY() == 0;
	}
	
	/**
	 * Ejecuta una accion: Avanzar, Derecha, Izquierda, Lanzar una Flecha
	 * 
	 * @param accion
	 * @return
	 */
	public List<Percepcion> accion(Accion accion) {
		Percepcion percepcion = null;
		if (Accion.AVANZAR.equals(accion)) {
			percepcion = avanzar();
		}
		else if (Accion.DERECHA.equals(accion)) {
			if (Direccion.DERECHA.equals(direccion)) {
				direccion = Direccion.ABAJO;
			}
			else if (Direccion.ABAJO.equals(direccion)) {
				direccion = Direccion.IZQUIERDA;
			}
			else if (Direccion.IZQUIERDA.equals(direccion)) {
				direccion = Direccion.ARRIBA;
			}
			else if (Direccion.ARRIBA.equals(direccion)) {
				direccion = Direccion.DERECHA;
			}
			percepcion = avanzar();
		}
		else if (Accion.IZQUIERDA.equals(accion)) {
			if (Direccion.DERECHA.equals(direccion)) {
				direccion = Direccion.ARRIBA;
			}
			else if (Direccion.ARRIBA.equals(direccion)) {
				direccion = Direccion.IZQUIERDA;
			}
			else if (Direccion.IZQUIERDA.equals(direccion)) {
				direccion = Direccion.ABAJO;
			}
			else if (Direccion.ABAJO.equals(direccion)) {
				direccion = Direccion.DERECHA;
			}
			percepcion = avanzar();
		}
		else if (Accion.FLECHA.equals(accion)) {
			numeroFlechas--;
			if (Direccion.DERECHA.equals(direccion)) {
				for (int i = posCazador.getX() + 1; i<maxCeldas; i++) {
					if (Celda.WUMPUS.equals(tablero[i][posCazador.getY()])) {
						wumpusMuerto = true;
						percepcion = Percepcion.GRITO;
					}
				}
			}
			else if (Direccion.ARRIBA.equals(direccion)) {
				for (int j = posCazador.getY() + 1; j<maxCeldas; j++) {
					if (Celda.WUMPUS.equals(tablero[posCazador.getX()][j])) {
						wumpusMuerto = true;
						percepcion = Percepcion.GRITO;
					}
				}
			}
			else if (Direccion.IZQUIERDA.equals(direccion)) {
				for (int i= posCazador.getX() - 1; i>=0; i--) {
					if (Celda.WUMPUS.equals(tablero[i][posCazador.getY()])) {
						wumpusMuerto = true;
						percepcion = Percepcion.GRITO;
					}
				}
			}
			else if (Direccion.ABAJO.equals(direccion)) {
				for (int j = posCazador.getY() - 1; j>=0; j--) {
					if (Celda.WUMPUS.equals(tablero[posCazador.getX()][j])) {
						wumpusMuerto = true;
						percepcion = Percepcion.GRITO;
					}
				}
			}
		}
		else if (Accion.SALIR.equals(accion)) {
			if (cazadorEstaEnCasillaSalida() && encontradoOro && cazadorVivo) {
				percepcion = Percepcion.EXITO;
			}
		}
		
		// Una vez movido, capta las Percepciones de la nueva posicion
		List<Percepcion> percepciones = obtenerPercepciones();
		if (percepcion != null) {
			percepciones.add(0, percepcion);
		}
		return percepciones;
	}
	
	/**
	 * Avanza una posicion. Devuelve percepcion de MURO si no puede avanzar
	 */
	private Percepcion avanzar() {
		if (Direccion.DERECHA.equals(direccion)) {
			Posicion posNew = new Posicion(posCazador.getX() + 1, posCazador.getY());
			return moverCazador(posNew);
		}
		else if (Direccion.IZQUIERDA.equals(direccion)) {
			Posicion posNew = new Posicion(posCazador.getX() - 1, posCazador.getY());
			return moverCazador(posNew);
		}
		else if (Direccion.ARRIBA.equals(direccion)) {
			Posicion posNew = new Posicion(posCazador.getX(), posCazador.getY() + 1);
			return moverCazador(posNew);
		}
		else if (Direccion.ABAJO.equals(direccion)) {
			Posicion posNew = new Posicion(posCazador.getX(), posCazador.getY() - 1);
			return moverCazador(posNew);
		}
		return null;
	}
	
	/**
	 * Mueve el cazador desde la posicion antigua a la nueva. Puede devolver una percepcion de MURO 
	 * 
	 * @param posNew
	 * @return
	 */
	private Percepcion moverCazador(Posicion posNew) {
		Percepcion percepcion = null;
		if (posNew.getX() == maxCeldas || posNew.getX() == -1 || posNew.getY() == maxCeldas || posNew.getY() == -1) {
			percepcion = Percepcion.MURO;
		}
		else {
			posCazador = posNew;
		}
		return percepcion;
	}
}
