package core;

import java.util.ArrayList;

import core.Coordenadas;
import utils.PathFinding;
import static utils.Constants.*;
import static utils.Utils.*;

public class MapBuilder 
{
	private int ancho = 1;
	private int alto = 1;
	
	private Coordenadas oro_cord;
	
	private ArrayList<ArrayList<Integer>> mapa;
	private ArrayList<Coordenadas> blocked;
	
	public MapBuilder(int ancho, int alto)
	{
		this.ancho=ancho;
		this.alto=alto;
		
		build();
		//to_string();
	}
	
	public ArrayList<ArrayList<Integer>> getMapa() {
		return mapa;
	}

	
	public void to_string() 
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
						case(PERCEP_NADA):System.out.print("   ");break;
						case(PERCEP_HEDOR):System.out.print("h  ");break;
						case(PERCEP_BRISA):System.out.print("b  ");break;
						case(PERCEP_POZO):System.out.print("O  ");break;
						case(PERCEP_WUMPUS):System.out.print("W  ");break;
						case(PERCEP_ORO):System.out.print("$  ");break;
						case(PERCEP_INICIO):System.out.print("!  ");break;
						
						case(PERCEP_BRISA_HEDOR):System.out.print("bh  ");break;
						case(PERCEP_ORO_HEDOR):System.out.print("$h  ");break;
						case(PERCEP_ORO_BRISA):System.out.print("$b  ");break;
						case(PERCEP_INICIO_HEDOR):System.out.print("!h  ");break;
						case(PERCEP_INICIO_BRISA):System.out.print("!b  ");break;
						
						case(PERCEP_INICIO_BRISA_HEDOR):System.out.print("!bs");break;
						case(PERCEP_ORO_BRISA_HEDOR):System.out.print("$bh");break;

						
						default:System.out.print("***");break;
					}
				}
			}
			System.out.println('*');
		}
		
	}
	
	//Solo para debugar
	public void to_string(int i, int j) 
	{
		int aux=0;
		for(ArrayList<Integer> x:mapa)
		{
			System.out.print('*');
			for(int y=-1;y<=x.size();y++)
			{
				if(aux==i && j==y) System.out.print("Y   ");
					else
					{
					if(y<0 || y==x.size())
						System.out.print('*');
					else
					{
						switch(x.get(y))
						{
							case(PERCEP_NADA):System.out.print("   ");break;
							case(PERCEP_HEDOR):System.out.print("h  ");break;
							case(PERCEP_BRISA):System.out.print("b  ");break;
							case(PERCEP_POZO):System.out.print("O  ");break;
							case(PERCEP_WUMPUS):System.out.print("W  ");break;
							case(PERCEP_ORO):System.out.print("$  ");break;
							case(PERCEP_INICIO):System.out.print("!  ");break;
							
							case(PERCEP_BRISA_HEDOR):System.out.print("bh  ");break;
							case(PERCEP_ORO_HEDOR):System.out.print("$h  ");break;
							case(PERCEP_ORO_BRISA):System.out.print("$b  ");break;
							case(PERCEP_INICIO_HEDOR):System.out.print("!h  ");break;
							case(PERCEP_INICIO_BRISA):System.out.print("!b  ");break;
							case(PERCEP_WUMPUS_BRISA):System.out.print("Wb  ");break;
							
							case(PERCEP_INICIO_BRISA_HEDOR):System.out.print("!bs");break;
							case(PERCEP_ORO_BRISA_HEDOR):System.out.print("$bh");break;
	
							
							default:System.out.print("***");break;
						}
					}
				}
			}
			System.out.println('*');
			aux++;
		}
		
	}
	//Construimos el mapa con todos sus elementos
	private void build() 
	{	
		do
		{
			initial_Grid();
			add_elements();	
			//to_string();
		}
		while(!isReacheble());//Comprobamos si el oro es alcanzable
		
		ambient();//Añadimos brisas y hedor
		
	}
	
	
	private void ambient() 
	{
		for(int x =0; x<ancho;x++)
		{
			for(int y=0;y<alto;y++)
			{
				int current= mapa.get(x).get(y);
				if(current==PERCEP_POZO)
				{
					for(int i=-1;i<=1;i+=2)
					{
						if(x+i>=0 &&x+i<ancho)
							put_ambient(x+i,y,PERCEP_BRISA);
					}
					for(int j=-1;j<=1;j+=2)
					{
						if(y+j>=0 &&y+j<ancho)
							put_ambient(x,y+j,PERCEP_BRISA);
					}
				}
				else if (current==PERCEP_WUMPUS)
				{
					for(int i=-1;i<=1;i+=2)
					{
						if(x+i>0 &&x+i<ancho)
							put_ambient(x+i,y,PERCEP_HEDOR);
					}
					for(int j=-1;j<=1;j+=2)
					{
						if(y+j>0 &&y+j<ancho)
							put_ambient(x,y+j,PERCEP_HEDOR);
					}
				}
			}
		}		
	}

	private void put_ambient(int x, int y, int percep) 
	{
		if(percep==PERCEP_BRISA)//Se debe añadir brisa
		{
			switch(mapa.get(x).get(y))
			{
				case PERCEP_NADA: mapa.get(x).set(y, PERCEP_BRISA);break;
				case PERCEP_HEDOR: mapa.get(x).set(y, PERCEP_BRISA_HEDOR);break;
				case PERCEP_ORO: mapa.get(x).set(y, PERCEP_ORO_BRISA);break;
				case PERCEP_INICIO: mapa.get(x).set(y, PERCEP_INICIO_BRISA);break;
				case PERCEP_INICIO_HEDOR: mapa.get(x).set(y, PERCEP_INICIO_BRISA_HEDOR);break;
				case PERCEP_ORO_HEDOR: mapa.get(x).set(y, PERCEP_ORO_BRISA_HEDOR);break;
				case PERCEP_WUMPUS: mapa.get(x).set(y, PERCEP_WUMPUS_BRISA);break;
			}
		}
		else//Se debe añadir hedor
		{
			switch(mapa.get(x).get(y))
			{
				case PERCEP_NADA: mapa.get(x).set(y, PERCEP_HEDOR);break;
				case PERCEP_BRISA: mapa.get(x).set(y, PERCEP_BRISA_HEDOR);break;
				case PERCEP_ORO: mapa.get(x).set(y, PERCEP_ORO_HEDOR);break;
				case PERCEP_INICIO: mapa.get(x).set(y, PERCEP_INICIO_HEDOR);break;
				case PERCEP_INICIO_BRISA: mapa.get(x).set(y, PERCEP_INICIO_BRISA_HEDOR);break;
				case PERCEP_ORO_BRISA: mapa.get(x).set(y, PERCEP_ORO_BRISA_HEDOR);break;
			}
		}
		
	}

	//Añadimos los pozos y el WUMPUS
	private void add_elements()
	{
		Coordenadas aux;
		
		mapa.get(0).set(0, PERCEP_INICIO);
		//Añadimos los pozos
		int n_pozos=(ancho*alto)/5;
		for(int i=0; i<n_pozos;i++)
		{			
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
		
		
		//Añadimos el WUMPUS
		do
		{
			aux=getCoordenadas(alto, ancho);
		}
		while(mapa.get(aux.getX()).get(aux.getY())==PERCEP_POZO || mapa.get(aux.getX()).get(aux.getY())==PERCEP_INICIO);
		//Se añade en un punto que no sea un POZO o inicio
		mapa.get(aux.getX()).set(aux.getY(), PERCEP_WUMPUS);	
		//blocked.add(aux);//Descomentar si no se puede matar al WUMPUS
		
		//Añadimos el ORO
		do
		{
			aux=getCoordenadas(alto, ancho);
		}
		while(mapa.get(aux.getX()).get(aux.getY())==PERCEP_WUMPUS ||mapa.get(aux.getX()).get(aux.getY())==PERCEP_POZO || mapa.get(aux.getX()).get(aux.getY())==PERCEP_INICIO);
		//Se añade en un punto que no sea un POZO, WUMPUS o inicio

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
		mapa = new ArrayList<ArrayList<Integer>>();
		blocked= new ArrayList<Coordenadas>();
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

	//Metodo para eliminar del mapa el wumpus y su hedor
	public void removeWumpus(int x, int y) 
	{		
		for(int i=-1;i<=i+1;i=i+2)
		{
			for(int j=-1;j<=j+1;j++)
			{
				switch(mapa.get(i).get(j))
				{
					case(PERCEP_HEDOR):mapa.get(i).set(j, PERCEP_NADA);break;
					case(PERCEP_BRISA_HEDOR):mapa.get(i).set(j, PERCEP_BRISA);break;
					case(PERCEP_ORO_HEDOR):mapa.get(i).set(j, PERCEP_ORO);break;
					case(PERCEP_INICIO_HEDOR):mapa.get(i).set(j, PERCEP_INICIO);break;
					
					case(PERCEP_INICIO_BRISA_HEDOR):mapa.get(i).set(j, PERCEP_INICIO_BRISA);break;
					case(PERCEP_ORO_BRISA_HEDOR):mapa.get(i).set(j, PERCEP_ORO_BRISA);break;
					
					case(PERCEP_WUMPUS_BRISA):mapa.get(i).set(j, PERCEP_BRISA);break;
					
					case(PERCEP_WUMPUS):mapa.get(i).set(j, PERCEP_NADA);break;
				}
			}
		}
		System.out.println(PERCEP_TXT_GRITO);
	}
	
	
	
	/*public static void main(String[] args) 
	{
		new MapBuilder(5, 5);
	}*/
}
