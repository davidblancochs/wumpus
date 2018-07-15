package wumpus;

import java.util.List;
import java.util.Scanner;

import wumpus.data.Percepcion;

public class WumpusGame {
	
	private final int dimension;
	private final int pozos;
	private final int flechas;
	
	public WumpusGame(int dimension, int pozos, int flechas) throws GameException {
		
		this.dimension = dimension;
		this.pozos = pozos;
		this.flechas = flechas;
		
	}

	public static void main(String[] args) throws GameException {
		
		if (args.length != 3) {
			System.out.println("Número de parametros incorrectos: P1=dimension, P2=pozos, P3=flechas");
			System.exit(1);
		}
		int dimension = 0, pozos = 0, flechas = 0;
		
		try {
			dimension = Integer.valueOf(args[0]);
			pozos = Integer.valueOf(args[1]);
			flechas = Integer.valueOf(args[2]);
		} catch (NumberFormatException e) {
			System.out.println("Tipo de parametros incorrectos. Tipo Numerico");
			System.exit(1);
		}
		
		WumpusGame game = new WumpusGame(dimension, pozos, flechas);
		game.start();
		
	}
	
	public void start() throws GameException {
		
		System.out.println("\n¡¡¡BIENVENIDO AL JUEGO DEL WUMPUS!!!\n\n");
		
		Tablero tablero = new Tablero(this.dimension, this.pozos);
		
		Jugador jugador = new Jugador(this.flechas, tablero);
		
		imprimeOpciones();
		
		tablero.printTablero();
		
		jugar(jugador, tablero);
		
	}
	
	
	private void imprimeOpciones() {
		System.out.println("ACCIONES DISPONIBLES:");
		System.out.println("  -> Avanzar: A");
		System.out.println("  -> Girar Drcha: D");
		System.out.println("  -> Girar Izda: I:");
		System.out.println("  -> Lanzar flecha: F");
		System.out.println("  -> Salir: S");
	}
	
	private void jugar(Jugador jugador, Tablero tablero) {

		Scanner scanner = new Scanner(System.in);
		
		bucle: while (true) {

			// Percepciones
			imprimePercepciones(jugador);
			
			if (!jugador.isEstaVivo()) {
				finJuego(scanner, false);
				break bucle;
			}
			
			// Tras obtener las percepciones verificamos si se ha matado el wumpus en la anterior acción
			if (jugador.isWumpusRecienMuerto()) {
				tablero.quitarWumpus();
			}
			
			// Accion
			System.out.println("\nIndica Acción");
			String opcion = scanner.nextLine();
			
			switch (opcion.toUpperCase()) {
			case "A":
				jugador.avanzar();
				break;
			case "D":
				jugador.girarDrcha();
				break;
			case "I":
				jugador.girarIzda();
				break;
			case "F":
				if (jugador.isLanzarFlechaPermitido()) {
					jugador.lanzarFlecha();
				} else {
					System.out.println("No permitido Lanzar Flecha");
				}
				break;
			case "S":
				if (jugador.isSalirPermitido()) {
					jugador.salir();
					finJuego(scanner, jugador.getLingoteConseguido());
					break bucle;
				} else {
					System.out.println("No permitido Salir");
				}
				break;
			default:
				System.out.println("Opción Incorrecta");
				break;
			}
		}
	}

	private void imprimePercepciones(Jugador jugador) {
		List<Percepcion> percepciones = jugador.getPercepciones();
		if (percepciones.isEmpty()) {
			System.out.println("\nSin Percepciones");
		} else {
			System.out.println("\nPercepciones: ");
			for (Percepcion p: percepciones) {
				System.out.print("	" + p);
			}
			System.out.println();
		}
	}

	private void finJuego(Scanner scanner, boolean exito) {
		scanner.close();
		System.out.println("JUEGO FINALIZADO " + (exito ? "CON EL OBJETIVO" : "SIN EL OBJETIVO"));
	}
	
}
