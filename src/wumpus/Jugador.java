package wumpus;

import java.util.ArrayList;
import java.util.List;

import wumpus.data.Accion;
import wumpus.data.Direccion;
import wumpus.data.Percepcion;

public class Jugador {

	private int flechas;
	private Tablero tablero;
	private Casilla casilla;
	private Casilla casillaAnterior;
	private Direccion direccion;
	private Accion accion;
	private boolean lingoteConseguido;
	private boolean estaVivo;
	private boolean wumpusRecienMuerto;

	// Wumpus
	IPercepcionCheck<Casilla> checkHumpus = c -> c != null && c.isHumpus();
	// Pozo
	IPercepcionCheck<Casilla> checkPozo = c -> c != null && c.isPozo();
	// Oro
	IPercepcionCheck<Casilla> checkLingote = c -> c != null && c.isLingote();

	// Choque Derecha
	IPercepcionCheck<Casilla> checkChoqueDrcha = c -> c != null && this.accion == Accion.AVANZAR
			&& this.direccion == Direccion.DRCHA && c.getColumna() == this.tablero.getDimension() - 1;

	// Choque Izquierda
	IPercepcionCheck<Casilla> checkChoqueIzda = c -> c != null && this.accion == Accion.AVANZAR
			&& this.direccion == Direccion.IZDA && c.getColumna() == 0;

	// Choque Arriba
	IPercepcionCheck<Casilla> checkChoqueArriba = c -> c != null && this.accion == Accion.AVANZAR
			&& this.direccion == Direccion.ARRIBA && c.getFila() == 0;

	// Choque Abajo
	IPercepcionCheck<Casilla> checkChoqueAbajo = c -> c != null && this.accion == Accion.AVANZAR
			&& this.direccion == Direccion.ABAJO && c.getFila() == this.tablero.getDimension() - 1;

	IPercepcionCheck<Casilla> checkMuerteWumpus = c -> c != null && this.accion == Accion.FLECHA
			&& compruebaMuerteWumpus();

	private boolean compruebaMuerteWumpus() {

		boolean muerte = false;
		Casilla casillaAux = casilla;

		while (casillaAux != null && !muerte) {
			casillaAux = casillaAux.getAdyacente(direccion);
			muerte = checkHumpus.test(casillaAux);
		}

		return muerte;

	}

	public Jugador(int flechas, Tablero tablero) {
		this.flechas = flechas;
		this.tablero = tablero;
		this.casilla = tablero.getCasillaInicial();
		this.casillaAnterior = null;
		this.direccion = Direccion.DRCHA;
		this.accion = Accion.INICIAL;
		this.lingoteConseguido = false;
		this.estaVivo = true;
	}

	public int getFlechas() {
		return flechas;
	}

	public void setFlechas(int flechas) {
		this.flechas = flechas;
	}

	public Casilla getCasilla() {
		return casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public boolean getLingoteConseguido() {
		return this.lingoteConseguido;
	}
	
	public boolean isEstaVivo() {
		return estaVivo;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}
	
	public boolean isWumpusRecienMuerto() {
		return wumpusRecienMuerto;
	}

	public void setWumpusRecienMuerto(boolean wumpusRecienMuerto) {
		this.wumpusRecienMuerto = wumpusRecienMuerto;
	}

	public void avanzar() {

		casillaAnterior = casilla;
		
		Casilla adyacente = casilla.getAdyacente(direccion);
		
		if (adyacente != null) {
			casilla = adyacente;
		}
		if (!lingoteConseguido && checkLingote.test(casilla)) {
			lingoteConseguido = true;
		}
		accion = Accion.AVANZAR;
		estaVivo = !checkPozo.test(casilla) && !checkHumpus.test(casilla);

	}

	public void girarDrcha() {
		direccion = direccion.getDrcha();
		accion = Accion.GIRO_DRCHA;
	}

	public void girarIzda() {
		direccion = direccion.getIzda();
		accion = Accion.GIRO_IZQ;
	}

	public boolean isLanzarFlechaPermitido() {
		return this.flechas > 0;
	}

	public void lanzarFlecha() {
		this.flechas--;
		accion = Accion.FLECHA;
		wumpusRecienMuerto = checkMuerteWumpus.test(casilla);
	}

	public boolean isSalirPermitido() {
		return casilla.isInicial();
	}

	public void salir() {
		casillaAnterior = casilla;
		casilla = null;
		accion = Accion.SALIR;
	}
	
	/**
	 * Obtiene la lista de percepciones del jugador en función de los elementos
	 * del tablero y de la última acción realizada. Método de lectura, no actaliza variables del jugador
	 * 
	 * @return
	 */

	public List<Percepcion> getPercepciones() {

		List<Percepcion> percepciones = new ArrayList<Percepcion>();	

		if (checkHumpus.test(casilla)) {
			percepciones.add(Percepcion.WUMPUS);
		}

		// Hedor del wumpus
		if (estaVivo && (checkHumpus.test(casilla.getDrcha()) || checkHumpus.test(casilla.getIzda())
				|| checkHumpus.test(casilla.getArriba()) || checkHumpus.test(casilla.getAbajo()))) {
			percepciones.add(Percepcion.HEDOR);
		}

		// Brisa del pozo
		if (estaVivo && (checkPozo.test(casilla.getDrcha()) || checkPozo.test(casilla.getIzda())
				|| checkPozo.test(casilla.getArriba()) || checkPozo.test(casilla.getAbajo()))) {
			percepciones.add(Percepcion.BRISA);
		}

		// Brillo del oro
		if (checkLingote.test(casilla)) {
			percepciones.add(Percepcion.BRILLO);
		}

		// Choque del muro.
		// Comprobación sobre la casilla anterior si avanza porque la acción de
		// avanzar ya se ha producido
		// Si es un giro comprobar sobre la casilla actual
		Casilla casillaComprobar = accion == Accion.AVANZAR ? casillaAnterior : casilla;
		// Cuando avanzas en el limite casillaAnterior == casilla
		if (checkChoqueDrcha.test(casillaComprobar) || checkChoqueIzda.test(casillaComprobar)
				|| checkChoqueArriba.test(casillaComprobar) || checkChoqueAbajo.test(casillaComprobar)) {
			percepciones.add(Percepcion.CHOQUE);
		}

		// Grito del wumpus
		if (checkMuerteWumpus.test(casilla)) {
			percepciones.add(Percepcion.GRITO);
		}

		return percepciones;

	}

}
