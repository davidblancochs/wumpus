package es.angel.wumpus;

public enum Percepcion {
	VACIA("No puedo percebir nada"),
	WUMPUS("En mi casilla se encuentra el wumpus"),
	HEDOR("Puedo percibir el hedor del wumpus"),
	POZO("He entrado en un pozo"),
	REFLEJO("Puedo percibir la brisa de un pozo"),
	ORO("Puedo percibir el brillo del oro"),
	MURO("Puedo percibir el choque de un muro"),
	GRITO("Puedo percibir el grito de un wumpus"),
	EXITO("He encontrado el oro y he salido con vida")
	;
	
	private final String percepcion;
	
	Percepcion(final String percepcion) {
		this.percepcion = percepcion;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return percepcion;
    }
}
