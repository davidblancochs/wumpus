package es.angel.test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import es.angel.wumpus.Accion;
import es.angel.wumpus.Percepcion;
import es.angel.wumpus.Posicion;
import es.angel.wumpus.Wumpus;

class TestWumpus {

	@Test
	void testPerciboBrisa() {
		// Inicializar el tablero y colocar los elementos
		Wumpus wumpus = new Wumpus(4, 3, 3);
		Posicion posWumpus = new Posicion(2,3);
		Posicion posOro = new Posicion(2,2);
		List<Posicion> listaPosPozos = new ArrayList<>();
		listaPosPozos.add(new Posicion(0,3));
		listaPosPozos.add(new Posicion(3,1));
		listaPosPozos.add(new Posicion(1,1));
		wumpus.init(posWumpus, posOro, listaPosPozos);
		
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.AVANZAR);
		assertTrue(wumpus.obtenerPercepciones().contains(Percepcion.REFLEJO));
	}
	
	@Test
	void testPerciboHedor() {
		// Inicializar el tablero y colocar los elementos
		Wumpus wumpus = new Wumpus(4, 3, 3);
		Posicion posWumpus = new Posicion(2,3);
		Posicion posOro = new Posicion(2,2);
		List<Posicion> listaPosPozos = new ArrayList<>();
		listaPosPozos.add(new Posicion(0,3));
		listaPosPozos.add(new Posicion(3,1));
		listaPosPozos.add(new Posicion(1,1));
		wumpus.init(posWumpus, posOro, listaPosPozos);
		
		wumpus.accion(Accion.IZQUIERDA);
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.DERECHA);
		wumpus.accion(Accion.IZQUIERDA);
		assertTrue(wumpus.obtenerPercepciones().contains(Percepcion.HEDOR));
	}
	
	@Test
	void testPerciboResplandor() {
		// Inicializar el tablero y colocar los elementos
		Wumpus wumpus = new Wumpus(4, 3, 3);
		Posicion posWumpus = new Posicion(2,3);
		Posicion posOro = new Posicion(2,2);
		List<Posicion> listaPosPozos = new ArrayList<>();
		listaPosPozos.add(new Posicion(0,3));
		listaPosPozos.add(new Posicion(3,1));
		listaPosPozos.add(new Posicion(1,1));
		wumpus.init(posWumpus, posOro, listaPosPozos);
		
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.IZQUIERDA);
		wumpus.accion(Accion.AVANZAR);
		assertTrue(wumpus.obtenerPercepciones().contains(Percepcion.ORO));
	}
	
	@Test
	void testMatarWumpus() {
		// Inicializar el tablero y colocar los elementos
		Wumpus wumpus = new Wumpus(4, 3, 3);
		Posicion posWumpus = new Posicion(2,3);
		Posicion posOro = new Posicion(2,2);
		List<Posicion> listaPosPozos = new ArrayList<>();
		listaPosPozos.add(new Posicion(0,3));
		listaPosPozos.add(new Posicion(3,1));
		listaPosPozos.add(new Posicion(1,1));
		wumpus.init(posWumpus, posOro, listaPosPozos);
		
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.IZQUIERDA);
		assertTrue(wumpus.accion(Accion.FLECHA).contains(Percepcion.GRITO));
	}
	
	@Test
	void testMorirWumpus() {
		// Inicializar el tablero y colocar los elementos
		Wumpus wumpus = new Wumpus(4, 3, 3);
		Posicion posWumpus = new Posicion(2,3);
		Posicion posOro = new Posicion(2,2);
		List<Posicion> listaPosPozos = new ArrayList<>();
		listaPosPozos.add(new Posicion(0,3));
		listaPosPozos.add(new Posicion(3,1));
		listaPosPozos.add(new Posicion(1,1));
		wumpus.init(posWumpus, posOro, listaPosPozos);
		
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.IZQUIERDA);
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.AVANZAR);
		assertTrue(wumpus.obtenerPercepciones().contains(Percepcion.WUMPUS));
	}
	
	@Test
	void testMorirPozo() {
		// Inicializar el tablero y colocar los elementos
		Wumpus wumpus = new Wumpus(4, 3, 3);
		Posicion posWumpus = new Posicion(2,3);
		Posicion posOro = new Posicion(2,2);
		List<Posicion> listaPosPozos = new ArrayList<>();
		listaPosPozos.add(new Posicion(0,3));
		listaPosPozos.add(new Posicion(3,1));
		listaPosPozos.add(new Posicion(1,1));
		wumpus.init(posWumpus, posOro, listaPosPozos);
		
		wumpus.accion(Accion.IZQUIERDA);
		wumpus.accion(Accion.DERECHA);
		assertTrue(wumpus.obtenerPercepciones().contains(Percepcion.POZO));
	}
	
	@Test
	void testEncontrarOro() {
		// Inicializar el tablero y colocar los elementos
		Wumpus wumpus = new Wumpus(4, 3, 3);
		Posicion posWumpus = new Posicion(2,3);
		Posicion posOro = new Posicion(2,2);
		List<Posicion> listaPosPozos = new ArrayList<>();
		listaPosPozos.add(new Posicion(0,3));
		listaPosPozos.add(new Posicion(3,1));
		listaPosPozos.add(new Posicion(1,1));
		wumpus.init(posWumpus, posOro, listaPosPozos);
		
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.IZQUIERDA);
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.IZQUIERDA);
		wumpus.accion(Accion.AVANZAR);
		wumpus.accion(Accion.IZQUIERDA);
		wumpus.accion(Accion.AVANZAR);
		assertTrue(wumpus.accion(Accion.SALIR).contains(Percepcion.EXITO));
	}

}
