package controler;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import core.Hunter;
import core.MapBuilder;

import static utils.Constants.*;

public class Main {

	/**
	 * @param args
	 */
	private static boolean salida=false;
	private static boolean resultado=false;
	private static boolean retorno;
	
	
	private static int alto=5, ancho=5;
	private static int x=0, y=0;
	
	private static MapBuilder mb;
	
	
	
	private static Hunter pj;
	
	
	private static  ArrayList<ArrayList<Integer>> mapa;
	private static Scanner in;
	private static int n_flechas=3;
	
	public static void main(String[] args) 
	{	
		System.out.println("BIENVENIDO A WINDU");
		while(true)
		{
			try
			{
				in = new Scanner(System.in);
				
				
				System.out.println("Seleccione modo de juego");
				System.out.println("------------------------\n");
				System.out.println("1 - Normal (5 ancho, 5 alto, 5 pozos, 1 Windu 1 flecha)");
				System.out.println("2 - Personalizado");
				
				
				
				
				int a = in.nextInt();
				switch(a)
				{
					case(1): 
						
						mb = new MapBuilder(ancho, alto);
						mapa=mb.getMapa();
						StartGame(n_flechas, false);
						return;
					case(2):
						select_properties();
						break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Solo se aceptan numeros validos");
			}
			
		}
		
	}
	private static void select_properties() 
	{
		int n_flechas=1;
		retorno=false;
		
		
		while(true)
		{
			try
			{
				System.out.println("------------------------\n");
				System.out.println("Seleccione que propiedades quieres modificar");
				System.out.println("1 - Ancho del campo - Actual: "+ancho);
				System.out.println("2 - Altura del campo - Actual: "+alto);
				System.out.println("3 - Numero de flechas");
				System.out.println("4 - Modo coger el oro y volver a salida -"+ ((retorno)? "Activo":"Desactivado"));
				System.out.println("----------------------");
				System.out.println("9 - Salir y empezar el juego");
				int op=in.nextInt();
				switch(op)
				{
					case 9: 
						mb = new MapBuilder(ancho, alto);
						mapa=mb.getMapa();
						StartGame(n_flechas, retorno);
						return;						
					case 1: 
						System.out.println("Introduce un numero mayor que 2");
						alto = in.nextInt();						
						break;
					case 2: 
						System.out.println("Introduce un numero mayor que 2");
						ancho = in.nextInt();						
						break;
					case 3: 
						System.out.println("Introduce el numero de flechas");
						n_flechas = in.nextInt();
						//if(n_flechas<-1) throw new Exception();
						break;
					case 4: retorno=!retorno;System.out.println("Se ha "+((retorno)?"activado":"desactivado")+" la modalidad de buscar y volver");break;
						
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Solo se aceptan numeros validos");
				in = new Scanner(System.in);				
			}
		}		
	}
	public static int SelectAction(boolean salida)
	{
		int act=-1;
		while(act<0 || act > ((salida)?4:5))
		{
			//mb.to_string(x,y);//SOLO DEBUG
			System.out.println();
			System.out.println("----SELECCIONA ACCIÓN----");
			System.out.println("1 - Avanzar");
			System.out.println("2 - Gira 90º grados hacia la izquierda");
			System.out.println("3 - Gira 90º grados hacia la derecha");
			System.out.println("4 - Dispara");
			if(salida)System.out.println("5 - Salir por la salida");
			try
			{
				act=in.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Solo se aceptan numeros validos");
				in = new Scanner(System.in);
			}
			
		}
		
		return act;
		
	}	
	public static void StartGame(int n_flechas, boolean retorno)
	{
		pj= new Hunter(n_flechas);
		resolveMove();//Sacamos la percepcion de la casilla de salida
		
		while(!salida)
		{
			int action=(x==0 && y==0)?SelectAction(true):SelectAction(false);
			
			switch(action)
			{
				case(ACTION_MOVE):System.out.println("Avanzas a la siguiente celda");move();break;
				case(ACTION_TURN_LEFT):System.out.println("Giras en direccion "+pj.rotar(ACTION_TURN_LEFT));resolveMove();break;
				case(ACTION_TURN_RIGHT):System.out.println("Giras en direccion "+pj.rotar(ACTION_TURN_RIGHT));resolveMove();break;
				case(ACTION_SHOT):System.out.println("Disparas una de tus flechas");shot();break;
				case(ACTION_EXIT):System.out.println("Sales del escenario");salir();break;
			}
		}
		
		gameOver();
		
		
	}
	private static void gameOver() 
	{
		if(resultado)
			System.out.println(GAMEOVER_BUENO);
		else
			System.out.println(GAMEOVER_MALO);
		
	}
	private static void salir() 
	{		
		if(pj.getGold())
		{
			resultado=true;
			salida=true;			
		}
		else
			System.out.println("No puedes salir sin el tesoro");
	}
	private static void shot() 
	{
		if(pj.getN_flechas()>0)
				pj.setN_flechas(pj.getN_flechas()-1);
		else
		{
			System.out.println("No te quedan mas flechas");
			return;
		}
		
		if(pj.getOrientacion().equals("N"))
		{		
			for(int j=x; j<alto;j++)
			{
				int aux=mapa.get(x).get(j);
				if(aux==PERCEP_WUMPUS || aux ==PERCEP_WUMPUS_BRISA)
				{
					mb.removeWumpus();
					return;
				}
			}
		}else
		if(pj.getOrientacion().equals("S"))
		{
			for(int j=y; j>=0;j--)
			{
				int aux=mapa.get(x).get(j);
				if(aux==PERCEP_WUMPUS || aux ==PERCEP_WUMPUS_BRISA)
				{
					mb.removeWumpus();
					return;
				}
			}					
		}else
		if(pj.getOrientacion().equals("E"))
		{
			for(int i=y; i<ancho;i++)
			{
				int aux=mapa.get(i).get(y);
				if(aux==PERCEP_WUMPUS || aux ==PERCEP_WUMPUS_BRISA)
				{
					mb.removeWumpus();
					return;
				}
			}				
		}else
		if(pj.getOrientacion().equals("O"))
		{
			for(int i=y; i>=0;i--)
			{
				int aux=mapa.get(i).get(y);
				if(aux==PERCEP_WUMPUS || aux ==PERCEP_WUMPUS_BRISA)
				{
					mb.removeWumpus();
					return;
				}
			}					
		}
		System.out.println("La flecha se pierde en la espesura del bosque");		
	}
	
	
	private static void move() 
	{
		String ori = pj.getOrientacion();		
		int mov=(ori.equals("N")|| ori.equals("E"))?1:-1;
		
		if(ori.equals("N")||ori.equals("S"))//Eje Y
		{
			if((y+mov)>alto || (y+mov)<0)
			{
				System.out.println(PERCEP_TXT_MURO);
				return;
			}
			y+=mov;
		}
		else//Eje X
		{
			if((x+mov)>ancho || (x+mov)<0)
			{
				System.out.println(PERCEP_TXT_MURO);
				return;
			}
			x+=mov;
		}
		
		resolveMove();
		
		
	}
	
	private static void resolveMove()
	{
		if(x>ancho || x<0 || y >alto || y<0)
		{
			System.out.println(PERCEP_TXT_MURO);
			return;
		}
		int percep = mapa.get(x).get(y);
		
		switch(percep)
		{
			//Percepcion simple
			case (PERCEP_HEDOR):System.out.println(PERCEP_TXT_HEDOR);break;
			case (PERCEP_BRISA):System.out.println(PERCEP_TXT_BRISA);break;
			
			case (PERCEP_POZO):System.out.println(PERCEP_TXT_POZO);resultado=false;salida=true;break;
			case (PERCEP_WUMPUS):System.out.println(PERCEP_TXT_WUMPUS);resultado=false;salida=true;break;
			
			case (PERCEP_ORO):
				System.out.println(PERCEP_TXT_ORO);
				mapa.get(x).set(y, PERCEP_NADA);
				pj.setGold(true);
				if(!retorno)
				{
					resultado=true;
					salida=true;
				}
				break;
			
			case (PERCEP_INICIO):
				System.out.println(PERCEP_TXT_INICIO);
				if(pj.getGold() && retorno)
				{
					resultado=true;
					salida=true;
				}
				break;
			
			//Percepcion doble
			case (PERCEP_BRISA_HEDOR):System.out.println(PERCEP_TXT_BRISA_HEDOR);break;
			
			case (PERCEP_ORO_HEDOR):
				System.out.println(PERCEP_TXT_ORO_HEDOR);
				mapa.get(x).set(y, PERCEP_HEDOR);
				pj.setGold(true);
				if(!retorno)
				{
					resultado=true;
					salida=true;
				}
				break;
			case (PERCEP_ORO_BRISA):
				System.out.println(PERCEP_TXT_ORO_BRISA);
				mapa.get(x).set(y, PERCEP_BRISA);
				pj.setGold(true);
				if(!retorno)
				{
					resultado=true;
					salida=true;
				}
				break;
				
			case (PERCEP_INICIO_HEDOR):
				System.out.println(PERCEP_TXT_INICIO_HEDOR);
				if(pj.getGold() && retorno)
				{
					resultado=true;
					salida=true;
				}
				break;
			case (PERCEP_INICIO_BRISA):
				System.out.println(PERCEP_TXT_INICIO_BRISA);
				if(pj.getGold() && retorno)
				{
					resultado=true;
					salida=true;
				}
				break;
				
			case (PERCEP_WUMPUS_BRISA):System.out.println(PERCEP_TXT_INICIO_BRISA);break;
			
			//Percepcion triple
			case (PERCEP_INICIO_BRISA_HEDOR):
				System.out.println(PERCEP_TXT_INICIO_BRISA_HEDOR);
				if(pj.getGold() && retorno)
				{
					resultado=true;
					salida=true;
				}
				break;
				
			case (PERCEP_ORO_BRISA_HEDOR):
				System.out.println(PERCEP_TXT_ORO_BRISA_HEDOR);
				mapa.get(x).set(y, PERCEP_BRISA_HEDOR);
				pj.setGold(true);
				if(!retorno)
				{
					resultado=true;
					salida=true;
				}
				break;
			
		};
	}
}
