package wumpus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {

	private int dimension;
	private int pozos;
	private Casilla[][] casillas;

	private Casilla casillaWumpus;

	public static int FILA_INICIAL = 0;
	public static int COLUMNA_INICIAL = 0;

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public Casilla getCasillaInicial() {
		return casillas[FILA_INICIAL][COLUMNA_INICIAL];
	}

	public Casilla getCasillaWumpus() {
		return casillaWumpus;
	}

	public void setCasillaWumpus(Casilla casillaWumpus) {
		this.casillaWumpus = casillaWumpus;
	}

	public Tablero(int dimension, int pozos) throws GameException {
		this.dimension = dimension;
		this.pozos = pozos;

		// pozos + wumpus + lingote < casillas
		if (pozos + 2 >= dimension * dimension) {
			throw new GameException("Demasiados elementos para el tamanyo del tablero");
		}

		this.casillas = new Casilla[dimension][dimension];

		// Crear casillas vacías
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				casillas[i][j] = new Casilla(i, j, dimension * i + j + 1);
			}
		}

		FILA_INICIAL = dimension - 1;
		COLUMNA_INICIAL = 0;
		// Casilla inicial
		casillas[FILA_INICIAL][COLUMNA_INICIAL].setInicial(true);

		rellenarTablero();

		calcularAdyacentes();
	}

	/**
	 * Calcula la lista de adyacentes de cada casilla y se mantiene en memoria
	 * para un acceso rápido en las acciones del jugador
	 */

	private void calcularAdyacentes() {

		// Se calcula de abajo a arriba
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {

				// Casilla izq
				Casilla casillaIzda = j - 1 >= 0 ? casillas[i][j - 1] : null;
				// Casilla drcha
				Casilla casillaDrcha = j + 1 <= this.dimension - 1 ? casillas[i][j + 1] : null;
				// Casilla arriba
				Casilla casillaArriba = i - 1 >= 0 ? casillas[i - 1][j] : null;
				// Casilla abajo
				Casilla casillaAbajo = i + 1 <= this.dimension - 1 ? casillas[i + 1][j] : null;

				casillas[i][j].setDrcha(casillaDrcha);
				casillas[i][j].setIzda(casillaIzda);
				casillas[i][j].setArriba(casillaArriba);
				casillas[i][j].setAbajo(casillaAbajo);
			}
		}
	}

	/**
	 * Rellena el tablero con los elemenos indicados. Se colocan aleatoriamente
	 * sobre el tablero
	 */

	private void rellenarTablero() {

		int numeroCasillas = Double.valueOf(Math.pow(this.dimension, 2)).intValue();

		// Generar una secuencia aleatoria de (pozos + 2) elementos entre
		// [2...dimension^2]. En la posición inicial no se ubica ningún elemento
		List<Integer> auxList = new ArrayList<Integer>();
		for (int i = 1; i <= numeroCasillas; i++) {
			// No establecer un elemento en la casilla inicial
			if (i != this.getCasillaInicial().getId()) {
				auxList.add(i);
			}
		}

		Collections.shuffle(auxList);

		for (int i = 0; i < 2 + this.pozos; i++) {
			int fila = getFila(auxList.get(i));
			int columna = getColumna(auxList.get(i));
			if (i == 0) {
				// ubicar wumus
				casillas[fila][columna].setHumpus(true);
				casillaWumpus = casillas[fila][columna];
			} else if (i == 1) {
				// ubicar lingote
				casillas[fila][columna].setLingote(true);
			} else {
				// ubicar un pozo
				casillas[fila][columna].setPozo(true);
			}
		}
	}

	/**
	 * A partir del identificador número de la casilla extraer la posición en la
	 * que se encuentra
	 * 
	 * @param id
	 * @return
	 */
	private int getFila(int id) {
		int fila;
		// Corrección para la última posición de la fila (indices de 0 a n-1)
		if (id % this.dimension == 0) {
			fila = id / this.dimension - 1;
		} else {
			fila = id / this.dimension;
		}
		return fila;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private int getColumna(int id) {
		int columna;
		// corrección para la ultima columna
		if (id % this.dimension == 0) {
			columna = this.dimension - 1;
		} else {
			columna = id % this.dimension - 1;
		}
		return columna;
	}

	public void printTablero() {

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				System.out.print(casillas[i][j].toString() + " ");
			}
			System.out.println();
		}

	}

	public void quitarWumpus() {
		casillaWumpus.setHumpus(false);
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

}
