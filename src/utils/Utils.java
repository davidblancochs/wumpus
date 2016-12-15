package utils;

import core.Coordenadas;

public final class Utils 
{
	public static final Coordenadas getCoordenadas(int alto, int ancho)
	{
		int x=-1, y=-1;
		do		
			x=Random(ancho);		
		while(x>=ancho);
		
		do		
			y=Random(alto);		
		while(y>=alto);
		
		
		return new Coordenadas(x, y);		
	}
	
	public static final int Random(int techo)
	{
		return (int)(Math.random()*100);
	}

}
