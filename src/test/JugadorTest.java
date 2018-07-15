package test;


import org.junit.Assert;
import org.junit.Test;

import wumpus.Casilla;
import wumpus.GameException;
import wumpus.Jugador;
import wumpus.Tablero;
import wumpus.data.Percepcion;

public class JugadorTest {
	
	private void limpiarTablero(Tablero t) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				t.getCasillas()[i][j].setHumpus(false);
				t.getCasillas()[i][j].setPozo(false);
				t.getCasillas()[i][j].setLingote(false);
			}
		}
	}
	
	/**
	 * Test para probar varias acciones sobre el Wumpus: Paso por una celda
	 * adyacente, disparar y caer en su casilla
	 * 
	 * @throws GameException
	 */

	@Test
	public void testWumpus() throws GameException {
		Tablero t = new Tablero(4, 3);
		
		//Limpiar Tablero
		limpiarTablero(t);
		
		// Marcar casilla del wumpus
		t.getCasillas()[3][2].setHumpus(true);
		t.setCasillaWumpus(t.getCasillas()[3][2]);
		Jugador j = new Jugador(3, t);
		
		// Avanzar hacia el wumpus
		j.avanzar();
		Assert.assertTrue(j.getPercepciones().contains(Percepcion.HEDOR));
		j.avanzar();
		Assert.assertTrue(j.getPercepciones().contains(Percepcion.WUMPUS));
		//Finaliza el juego
		Assert.assertFalse(j.isEstaVivo());
		
		//Matar al wumpus
		t = new Tablero(4, 3);
		limpiarTablero(t);
		Casilla casillaHumpus = t.getCasillas()[3][2];
		casillaHumpus.setHumpus(true);
		t.setCasillaWumpus(casillaHumpus);
		Jugador j2 = new Jugador(3, t);
		j2.lanzarFlecha();
		Assert.assertTrue(j2.getPercepciones().contains(Percepcion.GRITO));
		// Se mata al wumpus
		Assert.assertTrue(j2.isWumpusRecienMuerto());
		
		// Puede atravesar por donde estaba el humpus sin percepciones
		t.quitarWumpus();
		
		j2.avanzar();
		Assert.assertFalse(j2.getPercepciones().contains(Percepcion.HEDOR));
		j2.avanzar();
		Assert.assertFalse(j2.getPercepciones().contains(Percepcion.WUMPUS));

		Assert.assertTrue(j2.isEstaVivo());
		
	}
	
	/**
	 * Test para probar el paso por un pozo
	 * @throws GameException
	 */
	@Test
	public void testPozo() throws GameException {
		Tablero t = new Tablero(4, 3);
		
		//Limpiar Tablero
		limpiarTablero(t);
		
		// Marcar casilla del wumpus
		t.getCasillas()[3][2].setPozo(true);
		Jugador j = new Jugador(3, t);
		j.avanzar();
		Assert.assertTrue(j.getPercepciones().contains(Percepcion.BRISA));
		// Se cae por el pozo
		j.avanzar();
		Assert.assertFalse(j.isEstaVivo());
		
	}
	
	/**
	 * Test para probar el paso por el lingote y la salida
	 * @throws GameException
	 */
	
	@Test
	public void testLingote() throws GameException {
		Tablero t = new Tablero(4, 3);

		// Limpiar Tablero
		limpiarTablero(t);

		// Marcar casilla del wumpus
		t.getCasillas()[3][2].setLingote(true);
		Jugador j = new Jugador(3, t);
		j.avanzar();
		j.avanzar();
		Assert.assertTrue(j.getPercepciones().contains(Percepcion.BRILLO));
		Assert.assertFalse(j.isSalirPermitido());
		j.girarIzda();
		j.girarIzda();
		j.avanzar();
		j.avanzar();
		Assert.assertTrue(j.isSalirPermitido());
		j.salir();
		Assert.assertTrue(j.getCasilla() == null);
		
	}
	
	/**
	 * Test para probar que tras varios giror el jugador está en la posición esperada
	 * @throws GameException
	 */
	
	@Test
	public void testAccionGirar() throws GameException {
		Tablero t = new Tablero(4, 3);

		// Limpiar Tablero
		limpiarTablero(t);

		Jugador j = new Jugador(3, t);
		j.avanzar();
		j.girarIzda();
		j.avanzar();
		j.girarDrcha();
		j.avanzar();
		j.girarDrcha();
		j.avanzar();
		j.girarDrcha();
		j.avanzar();
		j.avanzar();
		Assert.assertTrue(j.getCasilla().getFila() == 3 && j.getCasilla().getColumna() == 0);
	}

}
