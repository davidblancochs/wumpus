package wumpus;
import java.util.*;  
import java.io.*;

public final class Game {
	
	//Set of possible events happening during the game
	public enum Event {
		HUNTER_MOVED("You moved to new cell successfully."), 
		HUNTER_HIT_WALL("You hit a wall; can't go there."), 
		HUNTER_KILLED_BY_WELL("Hunter fell into a well."), 
		HUNTER_KILLED_BY_WUMPUS("Hunter was killed by Wumpus."),
		HUNTER_GOT_GOLD("Great! You found GOLD."),
		WUMPUS_KILLED("Wumpus screams painfully. You killed the Wumpus."),
		WUMPUS_ALREADY_DEAD("You hit the Wumpus, but it was already dead."),
		DEAD_WUMPUS_FOUND("Dead Wumpus is here!"),
		ARROW_DIDNT_HIT("...Silence... Apparently you did not hit anything."),
		NO_ARROWS_LEFT("You have no arrows left."),
		EXITED("You exited the cave."),
		CELL_HAS_EXIT("Here it is the exit."),
		EXIT_IS_UP("Exit: go up"),
		EXIT_IS_DOWN("Exit: go down."),
		EXIT_IS_LEFT("Exit: go left."),
		EXIT_IS_RIGHT("Exit: go right."),
		SENSE_WELL("Wind blows... there must be a well nearby."),
		SENSE_WUMPUS("Smells like Wumpus."),
		WON_GAME("Great! I exited the cave with gold (and alive)."),
		NO_EVENT("");
	
	    private String message;		 
	    Event(String m) {
	        this.message = m;
	    }	 
	    public String getMessage() {
	        return message;
	    }
	}
	
	//Possibe actions selected by user. The "key" is the character entered by the User
	private enum UserAction {
		TURN_UP("w"),
		TURN_LEFT("a"),
		TURN_RIGHT("d"),
		TURN_DOWN("s"),
		MOVE("f"),
		SHOOT_ARROW("g"),
		CHEAT("v"),
		QUIT("q");
		
	    private String key;		 
	    UserAction(String k) {
	        this.key = k;
	    }	 
	    public String getKey() {
	        return key;
	    }
	}

	//Print messages from events
	public static void printEvents(ArrayList<Game.Event> events) {
		for(Game.Event e : events) {
			System.out.print(e.getMessage() + "\n");
		}
	}
	
	//Print commands
	public static void printCommands() {
		String s = "";
		for(Game.UserAction a : Game.UserAction.values()) {
			s = s + a + ": " + a.getKey() + "\t";		
		}
		System.out.print(s + "\n");
	}
	
	//Get input from User. Returns action of type selected by User
	public static UserAction getAction() throws IOException {
		//Scanner scanner = new Scanner(System.in);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	    String inputString;
		while(true) {
			//inputString = scanner.next().toLowerCase();
			inputString = input.readLine().toLowerCase();
			for(UserAction a : UserAction.values()) {
				if(inputString.equals(a.getKey())){
					//scanner.nextLine();
				    //scanner.close(); 
				    return a;
				}
			}
 
		}

	}
	
	//print state of the hunter (direction, number of arrows, got gold)
	public static void printHunterState(ElementoCazador hunter) {
		System.out.print(hunter.toString());
	}
	
	//print Board, with position of each object and exit included
	public static void showBoard(Tablero t) {
		System.out.print(t.toString());
	}
	
	//Prints final Game result
	public static void printResult(Tablero t) {
		String s = "\n***\n";
		if(t.getHunter().hasExited() && t.getHunter().isAlive()) {
			s = s + "You exited alive from the cave.\n";
		}
		if(t.getHunter().hasGold()){
			s = s + "You got the gold.\n";
		}else {
			s = s + "But you didn't get the gold.\n";
		}
		if(t.getWumpus().isDead()) {
			s = s + "And you killed the Wumpus!\n";
		}else {
			s = s + "But you did not kill the Wumpus.\n";
		}
		s = s + "\n";
		System.out.print(s);		
	}
	
	//Prints separation between movements (a row of ** and the game instructions)
	public static void printNextRound() {
		System.out.printf("\n******\n");
		printCommands();
	}
	//Contains the game logic
	public static void play(Tablero t, boolean showBoard) {
		ArrayList<Game.Event> events = t.getNeighboringSignals();
		UserAction action;
		ElementoCazador hunter = t.getHunter();
		printNextRound();
		while(true) {
			if(showBoard) {
				showBoard(t);
			}
			printEvents(events);
			if(hunter.isDead() || hunter.hasExited()) {
				break;
			}
			printHunterState(hunter);
			try {
				action = Game.getAction();

				switch(action) {
				case TURN_UP:
					printNextRound();
					events = t.getNeighboringSignals();
					hunter.turn(Tablero.Direction.UP);
					break;
				case TURN_DOWN:
					printNextRound();
					events = t.getNeighboringSignals();
					hunter.turn(Tablero.Direction.DOWN);
					break;
				case TURN_LEFT:
					printNextRound();
					events = t.getNeighboringSignals();
					hunter.turn(Tablero.Direction.LEFT);
					break;
				case TURN_RIGHT:
					printNextRound();
					events = t.getNeighboringSignals();
					hunter.turn(Tablero.Direction.RIGHT);
					break;
				case MOVE:
					printNextRound();
					events.clear();
					events.add(hunter.move(t));
					printEvents(events);
					events = t.activateCurrentCell();
					break;
				case SHOOT_ARROW:
					printNextRound();
					events = t.getNeighboringSignals();
					events.add(hunter.shootArrow(t));
					break;
				case CHEAT:
					if(! showBoard) { //Otherwise it is a little bit repetitive
						showBoard(t);
						events = t.getNeighboringSignals();
					}
					printNextRound();
					break;
				case QUIT:
					break;
				default:
					continue;
				}
				if(hunter.hasExited()) {
					printResult(t);
					break;
				}
			}catch(Exception e){
				System.err.println("Error reading input from user");
			}
		}

	}
	public static void main(String [] args){
		int size = 70; //default values
		int wells = 11;
		int arrows = 7;
		boolean show_map = false; //whether or not to show the map all the time (cheating option is always a choice anyway)
		if (args.length >= 3) {
			//Arguments from command line
		    try {
		        size = Integer.parseInt(args[0]);
		        wells = Integer.parseInt(args[1]);
		        arrows = Integer.parseInt(args[2]);
		        if (args.length >= 4) {
		        	if(Integer.parseInt(args[3]) > 0) {
		        		show_map = true;
		        	}
		        }
		    } catch (NumberFormatException e) {
		        System.err.println("Arguments must be integers.");
		        System.exit(1);
		    }
		}
		
		Tablero t = new Tablero(size, wells, arrows);
		play(t, show_map);
	}
}
