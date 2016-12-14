package core;

public class Hunter 
{
	//private int posX=0, posY=0;
	private String orientacion="N";
	private int n_flechas;
	private boolean gold=false;
	
	public Hunter(int n_flechas) 
	{
		super();
		this.n_flechas = n_flechas;
	}
	
	/*public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}*/
	public String getOrientacion() {
		return orientacion;
	}
	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}
	public int getN_flechas() {
		return n_flechas;
	}
	public void setN_flechas(int n_flechas) {
		this.n_flechas = n_flechas;
	}

	public boolean getGold() {
		return gold;
	}

	public void setGold(boolean gold) {
		this.gold = gold;
	}



}
