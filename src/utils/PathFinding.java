package utils;

import java.util.ArrayList;
import java.util.PriorityQueue;


import core.Coordenadas;



//Algoritmo A* adaptado de las webs:
//http://www.codebytes.in/2015/02/a-shortest-path-finding-algorithm.html
//http://www.cokeandcode.com/main/tutorials/path-finding/


public class PathFinding 
{	
	 public static final int V_H_COST = 1;
	 private int timeout=0;
	 private int width, height;
	
	//Clase para almacenar las celdas
	static class Cell implements Comparable<Cell>{  
        int heuristicCost = 0; //Heuristic cost
        int finalCost = 0; //G+H
        int x, y;
        Cell parent; 
        
        Cell(int x, int y){
            this.x = x;
            this.y = y; 
        }
        
        @Override
        public String toString(){
            return "["+this.x+", "+this.y+"]";
        }

		public int compareTo(Cell o) 
		{
			if(finalCost>o.finalCost)
				return 1;
			return -1;
		}
    }
	
	//Cola de prioridad para saber la proxima celda a explorar
	static PriorityQueue<Cell> open;
    
	//celdas exploradas
    static boolean closed[][];    
    
	private Cell[][] grid;
	static int startX=0, startY=0;
    static int endX, endY;
    
    static void checkAndUpdateCost(Cell current, Cell t, int cost){
        if(t == null || closed[t.x][t.y])return;
        
        int t_final_cost = t.heuristicCost+cost;
        
        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.finalCost){
            t.finalCost = t_final_cost;
            t.parent = current;
            
            //System.out.println(inOpen);
            //System.out.println(t.x+":"+t.y+" -- "+t.finalCost);
            if(!inOpen)open.add(t);
        }
    }
    public void AStar()
    {
		open.add(grid[startX][startY]);
		Cell current;
		int aux=0;
		while(true)
		{
			current = open.poll();
			if(current==null) break; //Si no queda ninguno en la cola, se acaba
			
			closed[current.x][current.y]=true;
			
			if(current.equals((grid[endX][endY])))//Si estamos en la casilla final, salimos
					return;
			
			Cell t;
			if(current.x-1>=0){
                t = grid[current.x-1][current.y];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST);
			}
			if(current.y-1>=0){
                t = grid[current.x][current.y-1];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST); 
            }

            if(current.y+1<grid[0].length){
                t = grid[current.x][current.y+1];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST); 
            }

            if(current.x+1<grid.length){
                t = grid[current.x+1][current.y];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST); 
            }
			
            aux++;
            if(aux>=timeout)break;
            //printGrid();
		}
		
    }
    
    public void printGrid()
    {
    	System.out.println("------------------");
		for(int x=0;x<width;x++)
		{
			for(int y=0;y<height;y++)
				{
					if(grid[x][y] != null)
						System.out.print(grid[x][y].finalCost+" ");
					else
						System.out.print("X ");
				}
			System.out.println("");
		}
		System.out.println("------------------");
    }
	
	public boolean start(Coordenadas objetivo, ArrayList<Coordenadas> blocked, int width, int height)
	{
		timeout=width*height*2;
		this.width=width;
		this.height=height;
		
		
		grid = new Cell[width][height];
		closed = new boolean [width][height];
		open = new PriorityQueue<Cell>();
		
		
		endX=objetivo.getX();
		endY=objetivo.getY();
		
		//Introducimos el valor de la distancia manhattan
		for(int i=0;i<width;++i){
            for(int j=0;j<height;++j){
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i-endX)+Math.abs(j-endY);
            }
         }
		
		
		//Bloqueamos las celdas a null que no se pueden acceder
		for (Coordenadas i:blocked)
		{
			grid[i.getX()][i.getY()] = null;
		}
		
		//Coste de la celda inicial
		grid[startX][startY].finalCost=0;
		
		
		/*Cell aux =new Cell(startX+1, endY);
		aux.finalCost=1;
		open.add(aux);
		
		aux =new Cell(startX, endY+1);
		aux.finalCost=1;
		open.add(aux);*/
		
		
		AStar(); 
		
		
		if(!closed[endX][endY])	
		{
			System.out.println("Volviendo a generar mapa por ser inviable");
			return false;
		}			 
		else
		{
			 return true;
		}
		
	}

}
