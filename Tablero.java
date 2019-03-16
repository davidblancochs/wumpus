package wumpus;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math; 
import java.util.Random;

public class Tablero {
    public enum Direction {UP("North"), DOWN("South"), RIGHT("West"), LEFT("East");
        private String dir;      
        Direction(String d) {
            this.dir = d;
        }    
        public String getCardinalDir() {
            return dir;
        }
    }
    private ArrayList<ElementoCasilla> elementos;
    private ArrayList<Casilla> tablero;
    private Casilla current_cell;
    private int well_number; //number of wells
    private int side_size; //number of cells in a row/column
    private int original_size; //size passed as parameter
    private int size; //total number of cells (different from original parameter)
    private int exit_position;
    //The constructor will create a map of size N x N, where N == sqrt(size passed as argument)
    //It will also determine the position of the exit and fill the cells of the squared map with wells, gold, hunter and Wumpus
    public Tablero(int size, int well_number, int arrows) {
        if(size < 4 || size <= well_number + 3) {
            throw new IllegalArgumentException("Map must have at least 4 cells.\n"
                    + "Well number must allow at least for the Wumpus, the Hunter and gold.");
        }
        this.original_size = size;
        this.side_size = (int)Math.sqrt((double)size); //Side of the squared map is the square root (rounded) of the original size
        this.size = this.side_size*this.side_size; //Actual size is side^2, which might be different from original size passed as argument
        this.well_number = well_number;
        
        //Create all Cells of the map (N Casilla objects)
        this.tablero = new ArrayList<Casilla>();
        Casilla casilla_nueva;

        for(int x=0; x < this.side_size; x++) {
            for(int y=0; y < this.side_size; y++) {
                casilla_nueva = new Casilla(x, y);
               this.tablero.add(casilla_nueva);
            }
        }
        //Create all ElementoCasilla objects:
        this.elementos = new ArrayList<ElementoCasilla>();
        ElementoCazador cazador = new ElementoCazador(arrows);
        this.elementos.add(cazador); //hunter will always be element 0 in ArrayList elements
        ElementoWumpus wumpus = new ElementoWumpus(); //Wumpus will always be element 1
        this.elementos.add(wumpus);
        ElementoOro oro = new ElementoOro(); // Gold will always be in position 2 in Array
        this.elementos.add(oro);
        ElementoPozo pozo;
        for(int i=0; i < this.well_number; i++) {
            pozo = new ElementoPozo();
            this.elementos.add(pozo);
        }   
        //Set Exit in one of the sides of the Map
        this.exit_position = setRandomExit();
        //Put hunter in the Exit cell
        this.addObjectToCell(cazador, this.exit_position);
        this.setRandomElements();
    }
    
    // Sets all elements (except Hunter, which is already in the Exit cell) randomly in the map
    // Only 1 element per cell is allowed
    // Note on performance: if number of wells approaches number of cells it can be very slow
    private void setRandomElements() {
        Random ran = new Random();
        Casilla c;
        ElementoCasilla e;
        int casilla_ind;
        for(int i=1; i<this.elementos.size(); i++) {
            e = this.elementos.get(i);
            do {
                casilla_ind = ran.nextInt(this.tablero.size());
                c = this.getCasilla(casilla_ind);
            }while(! this.addObjectToCell(e, casilla_ind));
        }
    }
    
    //Sets Exit randomly in one of the sides of the map. Hunter will be located here
    private int setRandomExit() {
        Direction exit_side;
        int coord;
        Random ran = new Random();
        Casilla c;
        do {
            exit_side = Direction.values()[ran.nextInt(Direction.values().length)];
            coord = ran.nextInt(this.side_size);
            switch(exit_side) {
            case UP:
                c = this.getCasilla(this.side_size - 1, coord);
                break;
            case DOWN:
                c = this.getCasilla(0, coord);
                break;
            case RIGHT:
                c = this.getCasilla(coord, this.side_size - 1);
                break;
            case LEFT:
                c = this.getCasilla(coord, 0);
                break;
            default:
                c = this.getCasilla(0);
                break;
            }
        }while(! c.isEmpty());
        
        c.setExit(exit_side);
        return this.getIndexFromXY(c);
    }

    //METHODS TO TRANSFORM COORDINATES (FROM LIST TO X, Y AND VICE VERSA)
    // get x coordinate from index
    public int getXFromIndex(int i) {
        return i / this.side_size;
    }
    
    // get y coordinate from index
    public int getYFromIndex(int i) {
        return i % this.side_size;
    }
    
    // get index from x, y coordinates
    public int getIndexFromXY(int x, int y) {
        return x*this.side_size + y;
    }
    
    // get index from x, y coordinates, but an object of class Casilla is the argument
    public int getIndexFromXY(Casilla c) {
        return this.getIndexFromXY(c.getX(), c.getY());
    }
    
    //MANIPULATE CONTENT OF CELLS
    // Add an object to cell in position (x, y). Only allowed if cell is empty
    public boolean addObjectToCell(ElementoCasilla e, int x, int y) {
        return this.addObjectToCell(e, this.getIndexFromXY(x, y));
    }
    
    // Add an object to cell with index pos. Only allowed if cell is empty
    public boolean addObjectToCell(ElementoCasilla e, int pos) {
        Casilla c = this.tablero.get(pos);
        if(c.isEmpty()) {
            c.addElementoCasilla(e);
            e.setPosition(c.x, c.y);
            if(e instanceof ElementoCazador) {
                this.current_cell = c;
            }
            return true;
        }
        return false;
    }
    
    // Add an object to cell with index pos. Allowed even if cell is full
    public boolean addObjectToAnyCell(ElementoCasilla e, int pos) {
        Casilla c = this.tablero.get(pos);
        c.addElementoCasilla(e);
        e.setPosition(c.x, c.y);
        if(e instanceof ElementoCazador) {
            this.current_cell = c;
        }
        return true;
    }
    
    // Move an object to cell in position (x, y). No needed that cell is empty
    public boolean moveObjectToCell(ElementoMovil e, int x, int y) {
        if(x >= 0 && y >= 0 && x < this.side_size && y < this.side_size) {
            this.removeObjectFromCell(e, this.getIndexFromXY(e.getX(), e.getY()));
            this.addObjectToAnyCell(e, this.getIndexFromXY(x, y));
            return true;
        }else {
            return false;
        }
    }
    
    // Move an object to cell with index pos. No needed that cell is empty
    private void moveObjectToCell(ElementoMovil e, int pos) {
        Casilla c = this.tablero.get(pos);
        c.addElementoCasilla(e);
        e.setPosition(c.x, c.y);
        
    }
    
    // Remove an object to cell in position (x, y)
    public boolean removeObjectFromCell(ElementoCasilla e, int x, int y) {
        return this.removeObjectFromCell(e, this.getIndexFromXY(x, y));
    }
    
    // Remove an object to cell with index pos
    public boolean removeObjectFromCell(ElementoCasilla e, int pos) {
        e.setPosition(-1, -1);
        Casilla c = this.tablero.get(pos);
        return c.removeElementoCasilla(e);
    }
    
    //GETTERS
    //get well number
    public int well_number() {
        return this.well_number;
    }
    
    //get size of the map (side^2)
    public int getSize() {
        return this.size;
    }
    
    //get original size, passed as parameters (different from current size if sqrt(original_side) is not integer
    public int getOriginalSize() {
        return this.original_size;
    }
    
    //get side (sqrt(size))
    public int getSideSize() {
        return this.side_size;
    }
    
    //get all elements in any cell as an arrayList
    public ArrayList<ElementoCasilla> getAllElements(){
        return this.elementos;
    }
    
    //Get any ElementoCasilla by position in ArrayList
    public ElementoCasilla getElementoCasilla(int index) {
        return this.elementos.get(index);
    }
    
    //Get Hunter element
    public ElementoCazador getHunter() {
        return (ElementoCazador)this.getElementoCasilla(0);
    }
    
    //Get Wumpus element
    public ElementoWumpus getWumpus() {
        return (ElementoWumpus)this.getElementoCasilla(1);
    }
    
    //Get Gold element
    public ElementoOro getGold() {
        return (ElementoOro)this.getElementoCasilla(2);
    }
    
    // get exit_position (position in ArrayList of cell that has Exit in one of its sides)
    public int getExitPosition() {
        return this.exit_position;
    }
    
    //get a particular cell (object of type Casilla)
    public Casilla getCasilla(int x, int y) {
        return this.tablero.get(this.getIndexFromXY(x, y));
    }
    
    public Casilla getCasilla(int pos) {
        return this.tablero.get(pos);
    }
    
    //Know whether the hunter is pointing to the exit
    public boolean pointingExit(int x, int y, Direction dir) {
        Casilla c = getCasilla(x, y);
        if(! c.hasExit()) {
            return false;
        }else {
            if(dir == c.getExit()) {
                return true;
            }else {
                return false;
            }
        }
    }
    
    //get signals from neighboring cells. Diagonal neighboring cells are not considered, since Hunter cannot move there
    public ArrayList<Game.Event> getNeighboringSignals(Casilla c, ArrayList<Game.Event> signals){
        int x = c.getX();
        int y = c.getY();
        if(x>0) {
            this.getCasilla(x-1, y).getSignals(signals);
        }
        if(x< this.side_size-1) {
            this.getCasilla(x+1, y).getSignals(signals);
        }
        if(y>0) {
            this.getCasilla(x, y-1).getSignals(signals);
        }
        if(y< this.side_size-1) {
            this.getCasilla(x, y+1).getSignals(signals);
        }
        if(c.hasExit()) {
            signals.add(c.noticeExit());
        }
        return signals;
    }
    
    public ArrayList<Game.Event> getNeighboringSignals(Casilla c){
        ArrayList<Game.Event> signals = new ArrayList<Game.Event>();
        return getNeighboringSignals(c, signals);
    }
    
    public ArrayList<Game.Event> getNeighboringSignals(){
        ArrayList<Game.Event> signals = new ArrayList<Game.Event>();
        return getNeighboringSignals(this.current_cell, signals);
    }
    
    //Get all information from surroundings and activate all objects in current cell (such as wumpus, well, or gold)
    public ArrayList<Game.Event> activateCurrentCell(){
        ArrayList<Game.Event> events = new ArrayList<Game.Event>();
        this.activateElements(events);
        ElementoCazador h= this.getHunter();
        if(h.isAlive()) {
            return this.getNeighboringSignals(this.current_cell, events);
        }else {
            return events;
        }   
    }
    //Activate all objects in current cell (such as wumpus, well, or gold) (internal logic)
    private void activateElements(ArrayList<Game.Event> signals) {
        if(!this.current_cell.isEmpty()) {
            Game.Event s = Game.Event.NO_EVENT;
            ArrayList<ElementoCasilla> elements = this.current_cell.getElements();
            Iterator<ElementoCasilla> it = elements.iterator();
            while(it.hasNext()) {
                s = it.next().action(this);
                if(s != Game.Event.NO_EVENT) {
                    signals.add(s);
                }
            }                   
        }
    }
    // get signals from neighbouring cells
    public ArrayList<Game.Event> getNeighboringSignals(int pos){
        return this.getNeighboringSignals(this.getCasilla(pos));
    }
    // get signals from neighbouring cells
    public ArrayList<Game.Event> getNeighboringSignals(int x, int y){
        return this.getNeighboringSignals(this.getCasilla(x, y));
    }
    // get Elements in cell
    public ArrayList<ElementoCasilla> getElementsInCell(Casilla c){
        return c.getElements();
    }
    // get Elements in cell from position in ArrayList of cells
    public ArrayList<ElementoCasilla> getElementsInCell(int pos){
        return this.getCasilla(pos).getElements();
    }
    // get Elements in cell from X, Y coordinates
    public ArrayList<ElementoCasilla> getElementsInCell(int x, int y){
        return this.getCasilla(x, y).getElements();
    }
    public String toString() {
        String s = "";
        for(int x = this.side_size - 1; x >= 0; x--) {
            for(int y = 0; y < this.side_size; y++) {
                s = s + "|" + this.getCasilla(x, y).toString() + "|";
            }
            if(x != 0) {
            s = s + "\n";
            }else {
            	s = s + "\t" + "Hunter: " + ElementoCazador.class_symbol + "; Wumpus: " + ElementoWumpus.class_symbol + 
            			 "; Well: " + ElementoPozo.class_symbol + "; Gold: " + ElementoOro.class_symbol + "; Exit: e\n";
            }
        }
        return s;
    }

    //Inner class representing a cell in the map
    private class Casilla{
        //This class represents a CELL in the whole map
        private int x; //coordinate x
        private int y; //coordinate y
        private ArrayList<ElementoCasilla> ocupando; //Elements inside the cell, such as Hunter or gold
        //Note that there can be more than one element
        private boolean exit;
        private Direction exit_dir;
        private static final int string_len = 5;
        public Casilla(int x, int y) {
            this.x = x;
            this.y = y;
            this.exit = false;
            this.ocupando = new ArrayList<ElementoCasilla>();
        }
        
        //set direction of exit
        public void setExit(Direction e) {
            this.exit = true;
            this.exit_dir = e;
        }
        
        // Add element, such as Wumpus, Well, etc
        public void addElementoCasilla(ElementoCasilla e) {
            this.ocupando.add(e);
        }
        
        // Remove an element from cell
        public boolean removeElementoCasilla(ElementoCasilla e) {
            int index = 0;
            boolean removed = false;
            Iterator<ElementoCasilla> it = this.ocupando.iterator();
            while(it.hasNext()) {
                if(e.equals(it.next())) {
                	removed = true;
                	break;
                }else {
                    index++;
                }
            }
            this.ocupando.remove(index);
            return removed;
        }
        
        public int getX() {
            return this.x;
        }
        
        public int getY() {
            return this.y;
        }
        
        // A SERIES OF METHODS TO INTERROGATE THE CONTENT OF THE CELL
        // Check whether the cell is empty or not
        public boolean isEmpty(){
            if(this.ocupando.size() == 0) {
                return true;
            }else {
                return false;
            }
        }  
        
        // General method to check whether the cell has a particular kind of object in it
        private boolean hasSomething(java.lang.Class nombreclase) {
            Iterator<ElementoCasilla> it = this.ocupando.iterator();
            while(it.hasNext()) {
                if(it.next().getClass().equals(nombreclase)) {
                    return true;
                }
            }
            return false;
        }
        
        //Check whether there is a hunter (Cazador)
        public boolean hasHunter() {
            return this.hasSomething(ElementoCazador.class);
        }
        
        //Check whether there is a well (Pozo)
        public boolean hasWell(){
            return this.hasSomething(ElementoPozo.class);
        }
        
        // Check whether there is Gold (Oro)
        public boolean hasGold() {
            return this.hasSomething(ElementoOro.class);
        }
        
        // Check whether there is the Wumpus
        public boolean hasWumpus() {
            return this.hasSomething(ElementoWumpus.class);
        }
        
        //returns true if the cell has an exit
        public boolean hasExit() {
            return this.exit;
        }
        
        //get direction of exit. Always check hasExit() before using getExit().
        public Direction getExit() {
            if(this.hasExit()) {
                return this.exit_dir;
            }else {
                return Direction.DOWN; //A default value
            }
        }
        
        //When the Hunter is in a cell which has an Exit, the direction in which the Exit is will be notified
        public Game.Event noticeExit() {
            Direction d = this.getExit();
            switch(d) {
            case UP:
                return Game.Event.EXIT_IS_UP;
            case DOWN:
                return Game.Event.EXIT_IS_DOWN;
            case LEFT:
                return Game.Event.EXIT_IS_LEFT;
            case RIGHT:
                return Game.Event.EXIT_IS_RIGHT;
            default:
                return Game.Event.NO_EVENT;
            }
        }
        
        //get list of ElementoCasilla ocupying this cell
        public ArrayList<ElementoCasilla> getElements(){
            return this.ocupando;
        }
        
        //Add signals of objects present in this cell, so that Hunter in neighboring cells can sense
        public void getSignals(ArrayList<Game.Event> signals) {
            if(!this.isEmpty()) {
                Game.Event s = Game.Event.NO_EVENT;
                Iterator<ElementoCasilla> it = this.ocupando.iterator();
                while(it.hasNext()) {
                    s = it.next().indicio();
                    if(s != Game.Event.NO_EVENT) {
                        signals.add(s);
                    }
                }                   
            }
        }
        public int getStringLength() {
            return Casilla.string_len;
        }
        public String toString() {
            String s = "";
            Iterator<ElementoCasilla> it = this.ocupando.iterator();
            while(it.hasNext()) {
                s = s + it.next().getClassSymbol();
            }
            for(int i = 0; i< this.getStringLength() - this.ocupando.size() - 1; i++) {
                s = s + ' ';
            }
            if(this.hasExit()) {
            	s = s + "e";
            }else {
            	s = s + ' ';
            }
            return s;
        }
        
        
    }
}
