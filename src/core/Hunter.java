package core;

import static utils.Constants.*;

public class Hunter 
{
	private String orientacion="N";
	private int n_flechas;
	private boolean gold=false;
	
	public Hunter(int n_flechas) 
	{
		super();
		this.n_flechas = n_flechas;
	}

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

	public String rotar(int action) 
	{
		if(action==ACTION_TURN_LEFT)
		{
			if(orientacion=="N") setOrientacion("O");
			else if(orientacion=="O") setOrientacion("S");
			else if(orientacion=="S") setOrientacion("E");
			else if(orientacion=="E") setOrientacion("N");
		}
		else if(action==ACTION_TURN_RIGHT)
		{
			if(orientacion=="N") setOrientacion("E");
			else if(orientacion=="O") setOrientacion("N");
			else if(orientacion=="S") setOrientacion("O");
			else if(orientacion=="E") setOrientacion("S");
		}
		
		return getOrientacion();
	}



}
