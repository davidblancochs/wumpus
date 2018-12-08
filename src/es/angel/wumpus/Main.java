package es.angel.wumpus;

public class Main {
	
	public static void main(String[] args) {
		// Recepcion de los parametros del juego
		if (args.length != 3) {
			throw new RuntimeException("Número incorrecto de parametros. Ejecute con wumpus celdas pozos flechas");
		}
		
		int celdas;
		int pozos;
		int flechas;
		try {
			celdas = Integer.parseInt(args[0]);
			pozos = Integer.parseInt(args[1]);
			flechas = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Parametros incorrectos. Ejecute con: wumpus celdas pozos flechas. Ejemplo: wumpus 10 3 3");
		}
		
		// Verificaciones
		if (celdas < 1) {
			throw new RuntimeException("Incorrecto numero de celdas del tablero");
		}
		if (pozos < 1) {
			throw new RuntimeException("Configure al menos 1 pozo");
		}
		// Todas los elementos (cazador, oro, wumbus y pozos tienen que caber en el tablero 
		if (pozos + 3 >= celdas*celdas) {
			throw new RuntimeException("El tablero es demasiado pequeño. Reconfigurelo");
		}
		
		Juego juego = new Juego();
		juego.start(celdas, pozos, flechas);
	}

}
