/**
 * 
 */
package text.based.games.htw;

import java.io.IOException;
import java.util.Scanner;

import text.based.games.htw.board.GameBoard;

/**
 * @author Alexis
 *
 */
public class Launcher {
	
	/*** Parámetros de la aplicación ***/
	/**
	 * Número de filas del tablero
	 */
	static int rows = -1;
	/**
	 * Número de columnas del tablero
	 */
	static int cols = -1;
	/**
	 * Número de flechas del cazador
	 */
	static int arrows = -1;
	/**
	 * Número de hoyos en el tablero
	 */
	static int holes = -1;
	
	
	/**
	 * Método principal que lanza el juego
	 * 
	 */
	public static void main (String [] args) {
		
		String accion;
		
		// Preguntamos al usuario los parámetros de la aplicación.
		askParams();
		
		// Inicializamos tablero y lo mostramos por pantalla.
		GameBoard board = new GameBoard(rows, cols, arrows, holes);
		board.displayBoard();
		
		// Bucle que indica que el juego está activo.
		while (board.isGameActive()) {
			
			// Preguntamos al usaurio la acción que desea realizar.
			accion = board.askPlayerMove();
			
			// Realizamos accion indicada por el usuario.
			board.makeAction(accion);
				
			// Limpiamos la consola
			clrscr();
			
			// Mostramos tablero con la accion realizada actualizado.
			board.displayBoard();
			
		}
		
		// Mensaje con el resultado de la partida
		if (board.getHunter().isDead()) {
			System.out.println("¡HAS PERDIDO LA PARTIDA...!");
		}else{
			System.out.println("¡ENHORABUENA! HAS GANADO LA PARTIDA EN "+board.getMovesCounter()+" MOVIMIENTOS.");
		}
		
	}
	
	/**
	 * Método para limpiar la consola y que el tablero parezca estático
	 */
	public static void clrscr(){
		
	    try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	    
	}
	
	
	/**
	 * Método para preguntar al usuario los parámetros del juego
	 * 
	 * @param player
	 */
	public static void askParams () {
		
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner (System.in);
		
		try {
		
			do{
					
				System.out.println("¡BIENVENIDO A HUNT THE WUMPUS! ");
				System.out.println("A continuación indique los parámetros de la aplicación, o si desea jugar inmediatamente presione ENTER.");
				
				System.out.println("Seleccione número de filas del tablero: ");
				rows = keyboard.nextInt();
					
				System.out.println("Seleccione número de columnas del tablero: ");
				cols = keyboard.nextInt();
				
				System.out.println("Seleccione número de flechas que dispone el cazador: ");
				arrows = keyboard.nextInt();
				
				System.out.println("Seleccione número de hoyos en el tablero: ");
				holes = keyboard.nextInt();
				
			} while (rows < 0 && cols < 0 && arrows < 0 && holes < 0);
		
		}catch (Exception ex){
			System.out.println("Se cargan parámetros por defecto...");
		}
		
	}
	
}
