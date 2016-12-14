package controler;

import java.util.ArrayList;
import java.util.Scanner;

import core.Hunter;
import core.MapBuilder;

public class Main {

	/**
	 * @param args
	 */
	
	private static  ArrayList<ArrayList<Integer>> mapa;
	private static Scanner in;
	private static int n_flechas=1;
	
	public static void main(String[] args) 
	{		
		in = new Scanner(System.in);
		
		System.out.println("BIENVENIDO A WINDU");
		System.out.println("Seleccione modo de juego");
		System.out.println("------------------------\n");
		System.out.println("1 - Normal (10 ancho, 10 alto, 5 pozos, 1 Windu 1 flecha)");
		System.out.println("2 - Personalizado");
		
		while(true)
		{
			try
			{
				switch(in.nextInt())
				{
					case(1): 
						mapa = new MapBuilder(10, 10).getMapa();
						StartGame(n_flechas, false);
						break;
					case(2):
						select_properties();
						break;
				}
				
				
				
			}
			catch(Exception e)
			{
				System.out.println("Solo se aceptan numeros validos");
			}
			
		}
		
	}
	private static void select_properties() 
	{
		int alto=10, ancho=10, n_flechas=1;
		boolean retorno=false;
		
		
		while(true)
		{
			try
			{
				System.out.println("------------------------\n");
				System.out.println("Seleccione que propiedades quieres modificar");
				System.out.println("1 - Ancho del campo");
				System.out.println("2 - Altura del campo");
				System.out.println("3 - Numero de flechas");
				System.out.println("4 - Modo coger el oro y volver a salida -"+ ((retorno)? "Activo":"Desactivado"));
				System.out.println("----------------------");
				System.out.println("9 - Salir y empezar el juego");
				int op=in.nextInt();
				switch(op)
				{
					case 9: 
						mapa = new MapBuilder(alto, ancho).getMapa();
						StartGame(n_flechas, retorno);
						break;
					case 1: 
						System.out.println("Introduce un numero mayor que 2");
						alto = in.nextInt();						
						break;
					case 2: 
						System.out.println("Introduce un numero mayor que 2");
						ancho = in.nextInt();						
						break;
					case 3: 
						System.out.println("Introduce el numero de flechas (-1 para flechas infinitas");
						n_flechas = in.nextInt();
						if(n_flechas<-1) throw new Exception();
						break;
					case 4: retorno=!retorno;System.out.println("Se ha "+((retorno)?"activado":"desactivado")+" la modalidad de buscar y volver");break;
						
				}
			}
			catch(Exception e)
			{
				System.out.println("Solo se aceptan numeros validos");
				select_properties();
			}
		}		
	}
	
	public static void StartGame(int n_flechas, boolean retorno)
	{
		Hunter pj= new Hunter(n_flechas);
		int x=0, y=0;
		
		while(true)
		{
			
		}
		
	}

}
