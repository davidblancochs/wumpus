package wumpus.data;

public final class Direccion {
	
	public static final String ID_ARRIBA = "ARRIBA";
	public static final String ID_IZDA = "IZDA";
	public static final String ID_DRCHA = "DRCHA";
	public static final String ID_ABAJO = "ABAJO";
	
	public static final Direccion ARRIBA = new Direccion(ID_ARRIBA);
	public static final Direccion DRCHA = new Direccion(ID_DRCHA);
	public static final Direccion IZDA = new Direccion(ID_IZDA);
	public static final Direccion ABAJO = new Direccion(ID_ABAJO);
	
	// Sentido de las ajujas del reloj
	static {
		ARRIBA.izda = IZDA;
		ARRIBA.drcha = DRCHA;
		DRCHA.izda = ARRIBA;
		DRCHA.drcha = ABAJO;
		ABAJO.izda = DRCHA;
		ABAJO.drcha = IZDA;
		IZDA.izda = ABAJO;
		IZDA.drcha = ARRIBA;
	}
	
	private String id;
	private Direccion izda;
	private Direccion drcha;
	
	
	private Direccion(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
	public Direccion getIzda() {
		return izda;
	}
	public void setIzda(Direccion izda) {
		this.izda = izda;
	}

	public Direccion getDrcha() {
		return drcha;
	}
	public void setDrcha(Direccion drcha) {
		this.drcha = drcha;
	}

	
}
