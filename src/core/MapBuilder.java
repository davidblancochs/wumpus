package core;

import java.util.ArrayList;

import utils.PathFinding;
import static utils.Constants.*;
import static utils.Utils.*;

public class MapBuilder 
{
	private int ancho = 1;
	private int alto = 1;
	
	private Coordenadas oro_cord;
	
	private ArrayList<ArrayList<Integer>> mapa = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Coordenadas> blocked= new ArrayList<Coordenadas>();
	
	public MapBuilder(int ancho, int alto)
	{
		this.ancho=ancho;
		this.alto=alto;
		
		build();
		
		to_string();
	}
	
	private void to_string() 
	{
		for(ArrayList<Integer> x:mapa)
		{
			System.out.print('*');
			for(int y=-1;y<=x.size();y++)
			{
				if(y<0 || y==x.size())
					System.out.print('*');
				else
				{
					switch(x.get(y))
					{
						case(PERCEP_NADA):System.out.print(' ');break;
						case(PERCEP_HEDOR):System.out.print('h');break;
						case(PERCEP_BRISA):System.out.print('b');break;
						case(PERCEP_POZO):System.out.print('O');break;
						case(PERCEP_WINDU):System.out.print('W');break;
						case(PERCEP_ORO):System.out.print('$');break;
						case(PERCEP_INICIO):System.out.print('!');break;
						default:System.out.print('*');break;
					}
				}
			}
			System.out.println('*');
		}
		
	}

	//Construimos el mapa con todos sus elementos
	private void build() 
	{	
		do
		{
			initial_Grid();
			add_elements();			
		}
		while(isReacheble());//Comprobamos si el oro es alcanzable
		
		//Pasamos a crear la brisa y el hedor
	}
	
	
	//Añadimos los pozos y el windu
	private void add_elements()
	{
		Coordenadas aux;
		
		mapa.get(0).set(0, PERCEP_INICIO);
		//Añadimos los pozos
		int n_pozos=(ancho*alto)/5;
		for(int i=0; i<n_pozos;i++)
		{
			to_string();
			System.out.println("------------");
			aux=getCoordenadas(alto, ancho);
			//Si no hay otro POZO y tampoco es el punto de inicio.
			if(mapa.get(aux.getX()).get(aux.getY())!=PERCEP_POZO && mapa.get(aux.getX()).get(aux.getY())!=PERCEP_INICIO)
			{
				mapa.get(aux.getX()).set(aux.getY(), PERCEP_POZO);
				blocked.add(aux);
			}
			else
				i--;//Restamos 1 a I para volver a buscar otra coordenada valida
		}
		to_string();
		System.out.println("------------");
		//Añadimos el WINDU
		do
		{
			aux=getCoordenadas(alto, ancho);
		}
		while(mapa.get(aux.getX()).get(aux.getY())==PERCEP_POZO || mapa.get(aux.getX()).get(aux.getY())==PERCEP_INICIO);
		//Se añade en un punto que no sea un POZO o inicio
		to_string();
		mapa.get(aux.getX()).set(aux.getY(), PERCEP_WINDU);	
		blocked.add(aux);
		
		//Añadimos el ORO
		do
		{
			aux=getCoordenadas(alto, ancho);
		}
		while(mapa.get(aux.getX()).get(aux.getY())==PERCEP_WINDU ||mapa.get(aux.getX()).get(aux.getY())==PERCEP_POZO || mapa.get(aux.getX()).get(aux.getY())==PERCEP_INICIO);
		//Se añade en un punto que no sea un POZO, windu o inicio
		System.out.println("----FINAL------");
		to_string();
		System.out.println("------------");
		mapa.get(aux.getX()).set(aux.getY(), PERCEP_ORO);	
		oro_cord=aux;//Lo necesitamos para saber si es alcanzable
		
	}

	//Comprobamos si el oro es alcanzable y no esta encerrado entre pozos
	private boolean isReacheble() 
	{
		PathFinding aux = new PathFinding();
		return aux.start(oro_cord, blocked, ancho, alto);
	}


	//Construye el mapa inicial totalmente limpio
	private void initial_Grid() 
	{
		for(int x=0;x<ancho;x++)
		{
			mapa.add(new ArrayList<Integer>());
			for(int y=0;y<alto;y++)
			{
				if(x==0 && y==0)
					mapa.get(x).add(PERCEP_INICIO);
				else
					mapa.get(x).add(PERCEP_NADA);
			}
		}
				
		
	}

	
	
	public static void main(String[] args) 
	{
		new MapBuilder(5, 5);
		

	}
	
	
	

}
