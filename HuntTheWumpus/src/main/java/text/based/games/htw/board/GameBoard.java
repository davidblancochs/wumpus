/**
 * 
 */
package text.based.games.htw.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import text.based.games.htw.model.Box;
import text.based.games.htw.model.Hole;
import text.based.games.htw.model.Hunter;
import text.based.games.htw.model.Position;
import text.based.games.htw.model.Wumpus;
import text.based.games.htw.util.Constants;
import text.based.games.htw.util.Types;

/**
 * @author Alexis
 *
 */
public class GameBoard {
	
	/**
	 * Número de filas del tablero
	 */
	private int ROWS_NUMBER; 
	
	/**
	 * Número de columnas del tablero
	 */
	private int COLS_NUMBER; 
	
	/**
	 * Número de flechas del cazador
	 */
	private int ARROWS_NUMBER;
	
	/**
	 * Número de agujeros en el tablero
	 */
	private int HOLES_NUMBER;
	
	/**
	 * Objeto que contiene al cazador
	 */
	private Hunter hunter;
	
	/**
	 * Objeto que contiene al wumpus
	 */
	private Wumpus wumpus;
	
	/**
	 * Objeto que contiene el listado de hoyos
	 */
	private List<Hole> holes;
	
	/**
	 * Objeto que contiene las casillas del tablero
	 */
	private Box [][] tablero;
	
	/**
	 * Variable que indica si el juego está activo
	 */
	private boolean gameActive = true;
	
	/**
	 * Contador de movimientos
	 */
	private int movesCounter = 0;
	
	/**
	 * Variable que indica si el juego ha iniciado
	 */
	private boolean isGameStart = false;
	
	
	
	/**
	 * Constructor de la clase Tablero
	 */
	public GameBoard (int rowsNumber, int colsNumber, int arrowsNumber, int holesNumber) {

		if (rowsNumber != -1){
			ROWS_NUMBER = rowsNumber;
		}else{
			ROWS_NUMBER = Constants.X_BOXES_NUMBER;
		}
		
		if (colsNumber != -1){
			COLS_NUMBER = colsNumber;
		}else{
			COLS_NUMBER = Constants.Y_BOXES_NUMBER;
		}
		
		if (arrowsNumber != -1){
			ARROWS_NUMBER = arrowsNumber;
		}else{
			ARROWS_NUMBER = Constants.ARROWS_NUMBER;
		}
		
		if (holesNumber != -1){
			HOLES_NUMBER = holesNumber;
		}else{
			HOLES_NUMBER = Constants.HOLES_NUMBER;
		}
		
		tablero = new Box [ROWS_NUMBER][COLS_NUMBER];
		// Inicializamos matriz de casillas
		for (int row=0; row<tablero.length; row++) {
			for (int col=0; col<tablero[row].length; col++) {
				tablero[row][col] = new Box();
			}
		}
		
		HashMap<Integer,Integer> posiciones = new HashMap<Integer,Integer>();
		
		// Inicializamos personajes del juego.
		
		// El cazador por defecto siempre comienza en la última fila de la primer columna.
		hunter = new Hunter(ARROWS_NUMBER, ROWS_NUMBER-1, 0);
		wumpus = new Wumpus(ThreadLocalRandom.current().nextInt(0, ROWS_NUMBER - 1),
				ThreadLocalRandom.current().nextInt(0, COLS_NUMBER - 1));
		
		holes = new ArrayList<>();
		for (int i=0; i < HOLES_NUMBER; i++){
			
			holes.add(new Hole(ThreadLocalRandom.current().nextInt(0, ROWS_NUMBER - 1),
				ThreadLocalRandom.current().nextInt(0, COLS_NUMBER + -1)));
			
		}
		
		
		//Cargamos casillas del tablero con todos los componentes
		cargarTablero ();

	}// Fin del constructor 

	
	
	/**
	 * Método que mostrará el tablero en la consola 
	 */
	public void displayBoard () {
		
		// Pintamos tablero de forma dinámica
		
		// A la hora de pintar, tenemos en cuenta las posiciones de todos los
		// elementos del juego (cazador, wumpus, hoyos, etc.)
		
		StringBuilder muro;
		StringBuilder perceptionSpace;
		StringBuilder hunterSpace;
		StringBuilder muroInferior = new StringBuilder();
		
		StringBuilder boardString = new StringBuilder();
		
		// Recorremos el número de filas y creamos los casilleros para cada fila
		for (int row=0; row < tablero.length; row++){
			
			muro = new StringBuilder();
			perceptionSpace = new StringBuilder();
			hunterSpace = new StringBuilder();
			muro.append("+");
			perceptionSpace.append("|");
			hunterSpace.append("|");
			
			// Recorremos el número de columnas para crear los casilleros de cada fila
			for (int col=0; col < tablero[row].length; col++){
				
				muro.append("---+");
				
				// Actualizamos la dirección del cazador y descubrimos la casilla actual.
				if (hunter.getPosicion().getX() == row && hunter.getPosicion().getY() == col && hunter.getDirection() != null ){
					
					perceptionSpace.append(getPerceptions(row, col));
					tablero[row][col].setCovered(false);
					
					switch (hunter.getDirection()) {
					case L:
						hunterSpace.append(" < |");
						break;
					case R:
						hunterSpace.append(" > |");
						break;
					case U:
						hunterSpace.append(" ^ |");
						break;
					case D:
						hunterSpace.append(" v |");
						break;
					}
				}else{
					if (!tablero[row][col].isCovered()) {
						//TODO: mostrar percepciones en este espacio, si es que hay.
						perceptionSpace.append(getPerceptions(row, col));
					}else{
						perceptionSpace.append(" ? |");
					}
					hunterSpace.append("   |");
				}
				
				if (col == tablero[row].length - 1) {
					muro.append("\n");
					perceptionSpace.append("\n");
					hunterSpace.append("\n");
				}
				
			}
			
			boardString.append(muro.toString() + perceptionSpace.toString()  + hunterSpace.toString() );
			
			// Añadimos el muro inferios unicamente si se trata de la última fila.
			if (row == tablero.length - 1) {
				
				muroInferior.append("+   +");
				
				// Pintamos el muro inferior que contiene la puerta.
				for (int col=0; col < tablero[row].length -1; col++){
					
					muroInferior.append("---+");
					
				}
				
			}
			
		}
		
		boardString.append(muroInferior.toString());
		
		if (!isGameStart){
			boardString.append("\n  ^");
		}
		
		System.out.println(boardString.toString());
		System.out.println();
		
	}
	
	
	
	/**
	 * Método que devuelve true si el cazador puede realizar la acción correctamente
	 * 
	 * @param player Caracter del jugador
	 * @param row fila a la que mueve
	 * @param col columna a la que mueve
	 * @return true si se movio correctamente y false si el movimiento es inválido
	 */
	public boolean makeAction (String action) {
		
		int xHunter;
		int yHunter;
		int newX = 0;
		int newY = 0;
		
		switch (action.toUpperCase()) {
		//Comienzo del juego
		case " ":
			hunter.setDirection(Types.direction.U);
			return true;
		// Cambiar dirección hacia izquierda
		case "A":
			hunter.setDirection(Types.direction.L);
			return true;
		// Cambiar dirección hacia derecha
		case "D":
			hunter.setDirection(Types.direction.R);
			return true;
		// Cambiar dirección hacia arriba
		case "W":
			hunter.setDirection(Types.direction.U);
			return true;
		// Cambiar dirección hacia abajo
		case "S":
			hunter.setDirection(Types.direction.D);
			return true;
		// Mover cazador
		case "M":
			
			xHunter = hunter.getPosicion().getX();
			yHunter = hunter.getPosicion().getY();
			
			switch (hunter.getDirection()) {
			case L:
				newY = yHunter - 1;
				if (newY >= 0) {
					hunter.getPosicion().setY(newY);
					incrementMovesCounter(); // Solo se incrementa si se mueve
				}
				break;
			case R:
				newY = yHunter + 1;
				if (newY < COLS_NUMBER) {
					hunter.getPosicion().setY(newY);
					incrementMovesCounter(); // Solo se incrementa si se mueve
				}
				break;
			case U:
				newX = xHunter - 1;
				if (newX >= 0) {
					hunter.getPosicion().setX(newX);
					incrementMovesCounter(); // Solo se incrementa si se mueve
				}
				break;
			case D:
				newX = xHunter + 1;
				if (newX < ROWS_NUMBER) {
					hunter.getPosicion().setX(newX);
					incrementMovesCounter(); // Solo se incrementa si se mueve
				}
				break;
			}
			
			xHunter = hunter.getPosicion().getX();
			yHunter = hunter.getPosicion().getY();
			
			// Comprobar que la posición del cazador no coincide con el Wumpus o algún hoyo
			if (!isValidBox(xHunter, yHunter)){
				
				hunter.setDead(true);
				gameActive = false; // Terminamos el juego
				
			}else if (tablero[xHunter][yHunter].hasGold()){
				
				hunter.setFindGold(true);
				System.out.println("¡ENHORABUENA! Has encontrado el oro. Regresa a la zona de partida para terminar.");
				
			}else if (tablero[xHunter][yHunter].isStartingPoint() && hunter.isFindGold()) {
				// Si el cazador llega al punto de partida con el oro, termina el juego.
				gameActive = false;
			}
			
			break;
		// Disparar flecha
		case "P":

			if (hunter.getArrows()>0) {
				
				xHunter = hunter.getPosicion().getX();
				yHunter = hunter.getPosicion().getY();
				
				switch (hunter.getDirection()) {
				case L:
					newY = yHunter - 1;
					if (wumpus.getPosicion().matchPosition(xHunter, newY)) {
						wumpus.setDead(true);
					}
					break;
				case R:
					newY = yHunter + 1;
					if (wumpus.getPosicion().matchPosition(xHunter, newY)) {
						wumpus.setDead(true);
					}
					break;
				case U:
					newX = xHunter - 1;
					if (wumpus.getPosicion().matchPosition(newX, yHunter)) {
						wumpus.setDead(true);
					}
					break;
				case D:
					newX = xHunter + 1;
					if (wumpus.getPosicion().matchPosition(newX, yHunter)) {
						wumpus.setDead(true);
					}
					break;
				}
				
				if (wumpus.isDead()) {
					System.out.println("¡ENHORABUENA! Has matado al Wumpus.");
					//Descubrimos la caja del WUMPUS
					tablero[wumpus.getPosicion().getX()][wumpus.getPosicion().getY()].setCovered(false);
				}else{
					System.out.println("¡LO SIENTO! Has malgastado una flecha.");
				}
				
				hunter.setArrows(hunter.getArrows()-1);
			
			} else {
				System.out.println("¡LO SIENTO! Ya no te quedan flechas.");
			}
			
			break;
		//Mostrar instrucciones
		case "H":
			mostrarAyuda();
			break;
		}
		
		
		return false; 
			
	}
	
	
	/**
	 * Método que indica si la partida aun está activa.
	 */
	public boolean isGameActive () {
		
		return gameActive;
		
	}
	
	
	/**
	 * Método para preguntar al usuario a dónde mover. 
	 * 
	 * @param player
	 */
	public String askPlayerMove () {
		
		Scanner keyboard = new Scanner (System.in);
		String accion = "";
		
		do{
			
			if (!isGameStart){
				
				mostrarAyuda();
				
				System.out.println("Presione ESPACIO para comenzar el juego.");
				
				accion = keyboard.nextLine();
				
			} else {
				
				mostrarAyuda();
			
				System.out.println("Indique por favor la acción que desea realizar y presione ENTER: ");
				
				System.out.println("- Cambiar dirección: A=Izquierda, D=Derecha, W=Arriba, S=Abajo");
				System.out.println("- Mover: M ");
				System.out.println("- Disparar: P ");
				System.out.println("- Ayuda: H ");
				
				System.out.println("Número de flechas: "+hunter.getArrows());
				
				accion = keyboard.nextLine();
			
			}
			
		} while (!isValid(accion));
		
		return accion;
		
	}
	
	
	/**
	 * Método que indica si el movimiento es válido o no
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean isValid (String accion) {
		
		if (!isGameStart && " ".equals(accion) ) {
			isGameStart = true;
			return true;
		} else if (isGameStart && Arrays.asList("A", "D", "W", "S", "M", "P", "H").contains(accion.toUpperCase())) {
			return true;
		} else {
			System.out.println("¡ ACCIÓN NO PERMITIDA !");
			return false; 
		}
		
	}

	
	/**
	 * Método que carga el tablero con todos los componentes: personajes, percepciones, oro, etc. 
	 * 
	 */
	public void cargarTablero () {
		
		// Colocamos Wumpus
		tablero[wumpus.getPosicion().getX()][wumpus.getPosicion().getY()].setHasWumpus(true);
		
		// Recorremos posiciones del hedor del Wumpus
		for (Position pos : wumpus.getStenchPositions()) {
			tablero[pos.getX()][pos.getY()].setHasStench(true);
		}
		
		// Recorremos hoyos
		for (Hole hole : holes) {
			
			// Colocamos Hoyo
			tablero[hole.getPosicion().getX()][hole.getPosicion().getY()].setHasHole(true);
			
			// Recorremos posiciones de las brisas de los hoyos
			for (Position pos : hole.getBreezePositions()) {
				tablero[pos.getX()][pos.getY()].setHasBreeze(true);
			}
		}
		
		// Cargamos ORO
		int xGold = ThreadLocalRandom.current().nextInt(0, ROWS_NUMBER - 1);
		int yGold = ThreadLocalRandom.current().nextInt(0, COLS_NUMBER - 1);
		
		tablero[xGold][yGold].setHasGold(true);
		
		// Seteamos punto de partida por defecto
		tablero[ROWS_NUMBER-1][0].setStartingPoint(true);
		
	}
	
	/**
	 * Método que muestra en el tablero las percepciones de la casilla.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public String getPerceptions (int row, int col) {
		
		String perceptionSpace = "";
		
		if (tablero[row][col].hasHole()) {
			perceptionSpace += " O |";
		}else if (tablero[row][col].hasWumpus()) {
			if (wumpus.isDead()){
				if(tablero[row][col].hasBreeze()) {
					perceptionSpace += "B X|";
				}else{
					perceptionSpace += " X |";
				}
			}else{
				if(tablero[row][col].hasBreeze()) {
					perceptionSpace += "B W|";
				}else{
					perceptionSpace += " W |";
				}
			}
		}else if(tablero[row][col].hasBreeze() && tablero[row][col].hasStench() && tablero[row][col].hasGold() ){
			perceptionSpace += "BGS|";
		}else if (tablero[row][col].hasBreeze() && tablero[row][col].hasStench()) {
			perceptionSpace += "B S|";
		}else if (tablero[row][col].hasBreeze()) {
			perceptionSpace += " B |";
		}else if (tablero[row][col].hasStench()) {
			perceptionSpace += " S |";
		}else if (tablero[row][col].hasGold()) {
			perceptionSpace += " G |";
		}else{
			perceptionSpace += "   |";
		}
		
		return perceptionSpace;
		
	}
	
	
	/**
	 * Método que comprueba que el cazador entra en una casilla válida,
	 * es decir, que no encuentra un hoyo o al Wumpus.
	 *
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isValidBox (int x, int y){
		
		// Comprobamos que el cazador no se haya topado con el Wumpus (vivo)
		if (!wumpus.isDead() && wumpus.getPosicion().getX() == x && wumpus.getPosicion().getY() == y){
			return false;
		}
		
		// Comprobamos que el cazador no se haya topado con algún hoyo
		for (Hole h : holes){
			if(h.getPosicion().getX() == x && h.getPosicion().getY() == y){
				return false;
			}
		}
		
		return true;
		
	}
	
	/**
	 * Método que muestra las instrucciones por pantalla.
	 */
	private void mostrarAyuda(){
		System.out.println("Instrucciones: ");
		System.out.println("Cuando se descubre una casilla se puede encontrar lo siguiente: ");
		System.out.println("- La 'B' indica la brisa del hoyo");
		System.out.println("- La 'S' indica el hedor del Wumpus");
		System.out.println("- La 'O' indica un agujero");
		System.out.println("- La 'W' indica que encontraste al Wumpus");
		System.out.println("- La 'X' indica que el Wumpus está muerto");
		System.out.println();
	}

	/**
	 * @return the movesCounter
	 */
	public int getMovesCounter() {
		return movesCounter;
	}

	/**
	 * @param movesCounter the movesCounter to set
	 */
	public void setMovesCounter(int movesCounter) {
		this.movesCounter = movesCounter;
	}
	
	/**
	 * Incrementa el número de movimientos
	 * @return the movesCounter
	 */
	public int incrementMovesCounter() {
		return movesCounter++;
	}



	/**
	 * @return the hunter
	 */
	public Hunter getHunter() {
		return hunter;
	}



	/**
	 * @param hunter the hunter to set
	 */
	public void setHunter(Hunter hunter) {
		this.hunter = hunter;
	}
	
}
