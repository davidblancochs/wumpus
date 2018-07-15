package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import wumpus.Casilla;
import wumpus.GameException;
import wumpus.Tablero;

public class TableroTest {

	
	@Test
	public void testTableroDimensionCorrecta() {
		
		// correctas
		try {
			int n = 4;
			new Tablero(n, n*n - 3);
			new Tablero(n, n*n - 4);
			new Tablero(n, 0);
			n = 8;
			new Tablero(n, n*n - 4);
			new Tablero(n, n*n - 5);
		} catch (GameException ex) {
			fail();
		}

		//Incorrectas
		try {
			new Tablero(4, 16);
			fail();
		} catch (GameException ex) {
		}
		
		try {
			new Tablero(8, 63);
			fail();
		} catch (GameException ex) {
		}
		
	}
	
	@Test
	public void testCondicionesTablero() throws GameException {
		
		Tablero t = null;
		for (int i = 4; i < 8; i++) {
			try {
				t = new Tablero(i, 2*i);
			} catch (GameException ex) {
				fail();
			}
			compruebaTablero(t, i);	
		}
	}

	private void compruebaTablero(Tablero t, int n) {
		// Dimension nxn
		Assert.assertEquals(t.getCasillas().length, n);
		for (int i = 0; i < n; i++) {
			Assert.assertEquals(t.getCasillas()[i].length, n);
		}
		
		//Adyacentes para posiciones [0,0], [n,n], [1,1]
		Casilla casilla0 = t.getCasillas()[0][0];
		Assert.assertTrue(casilla0.getDrcha() != null 
				&& casilla0.getAbajo() != null
				&& casilla0.getArriba() == null
				&& casilla0.getIzda() == null);
		
		Casilla casilla1 = t.getCasillas()[1][1];
		Assert.assertTrue(casilla1.getDrcha() != null 
				&& casilla1.getAbajo() != null
				&& casilla1.getArriba() != null
				&& casilla1.getIzda() != null);
		
		Casilla casillaN = t.getCasillas()[n-1][n-1];
		Assert.assertTrue(casillaN.getDrcha() == null 
				&& casillaN.getAbajo() == null
				&& casillaN.getArriba() != null
				&& casillaN.getIzda() != null);
		
		//Comprobar que hay 2*n pozos, 1 lingote y el Wumpus
		int pozos = 0, lingotes = 0, wumpus = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (t.getCasillas()[i][j].isPozo()) {
					pozos++;
				}
				if (t.getCasillas()[i][j].isLingote()) {
					lingotes++;
				}
				if (t.getCasillas()[i][j].isHumpus()) {
					wumpus++;
				}
			}
		}
		Assert.assertEquals(pozos, 2*n);
		Assert.assertEquals(lingotes, 1);
		Assert.assertEquals(wumpus, 1);
	}


}
