package es.angel.wumpus;

import java.io.IOException;
import java.util.List;

public class Juego {
	
	/**
	 * Arranca el juego
	 */
	public void start(int celdas, int pozos, int flechas) {
		Wumpus wumpus = new Wumpus(celdas, pozos, flechas);
		
		mostrarPercepciones(wumpus.init());
		
		int numMaxIteraciones = 100;
		while (numMaxIteraciones > 0) {
			
			mostrarAcciones(wumpus);
			Accion accion = null;
			while (accion == null) {
				accion = leerAccion(wumpus);
			}
			
			List<Percepcion> percepciones = wumpus.accion(accion);
			mostrarPercepciones(percepciones);
			if (!wumpus.cazadorVivo()) {
				System.out.println("El cazador está muerto. FIN");
				System.exit(0);
			}
			if (percepciones != null && percepciones.contains(Percepcion.EXITO)) {
				System.out.println(Percepcion.EXITO.toString());
				System.exit(0);
			}
			
			numMaxIteraciones--;
		}
	}

	/**
	 * Muestra al usuario las percepciones del cazador
	 * 
	 * @param percepciones
	 */
	private void mostrarPercepciones(List<Percepcion> percepciones) {
		if (percepciones == null || percepciones.isEmpty()) {
			System.out.println(Percepcion.VACIA.toString());
		}
		else {
			for (Percepcion percepcion : percepciones) {
				System.out.println(percepcion.toString());
			}
		}
	}

	/**
	 * Muestra las acciones que puede ejecutar el usuario
	 * 
	 * @param wumpus
	 */
	private void mostrarAcciones(Wumpus wumpus) {
		String mensaje = "Acción: A-Avanzar D-Derecha I-Izquierda ";
		if (wumpus.cazadorTieneFlechas()) {
			mensaje += "F-Lanzar Flecha ";
		}
		if (wumpus.cazadorEstaEnCasillaSalida()) {
			mensaje += "S-Salir";
		}
		System.out.println(mensaje);
	}

	/**
	 * Lee la accion que ha elegido el usuario
	 * 
	 * @param wumpus
	 * @return Accion a ejecutar o null si accion no valida
	 */
	private Accion leerAccion(Wumpus wumpus) {
		try {
			char c = (char) System.in.read();
			if (c == 'A' || c == 'a') {
				return Accion.AVANZAR;
			}
			else if (c == 'D' || c == 'd') {
				return Accion.DERECHA;
			}
			else if (c == 'I' || c == 'i') {
				return Accion.IZQUIERDA;
			}
			else if ((c == 'F' || c == 'f') && wumpus.cazadorTieneFlechas()) {
				return Accion.FLECHA;
			}
			else if ((c == 'S' || c == 's') && wumpus.cazadorEstaEnCasillaSalida()) {
				return Accion.SALIR;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
