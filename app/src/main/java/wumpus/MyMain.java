package wumpus;

import static java.lang.Integer.valueOf;

import java.util.InputMismatchException;
import java.util.Scanner;

import utils.Utils;

public class MyMain {

	public static void main(String[] args) {
		if (args.length != 3) {
			Utils.showMessage("Error. You must add 3 parameters! board size, pits number, arrows number");
			System.exit(1);
		}

		Integer boxSize = null;
		Integer numberArrow = null;
		Integer numberPits = null;

		try {
			boxSize = valueOf(args[0]);
			numberArrow = Integer.valueOf(args[1]);
			numberPits = Integer.valueOf(args[2]);
		} catch (NumberFormatException e) {
			Utils.showMessage("Error. The parameters must be numbers");
			System.exit(1);
		}

		Utils.showMessage("Welcome to HUNT THE WUMPUS");

		Wumpus wumpus = new Wumpus(boxSize, numberArrow, numberPits);
		
		Scanner scanner = new Scanner(System.in);
		while (wumpus.isPlaying()) {
			try {
				if (wumpus.isGameOver()) {
					Utils.showMessage("The End");
					Utils.showMessage("--------------------------");
					exitWumpus(scanner,0);
				}
				else {
					showMenu();
				}
				Integer option = scanner.nextInt();
				if (option == 0) {
					exitWumpus(scanner,0);
				}
				else if(option > 0 && option < 5) {
					wumpus.play(MovesPlayer.get(option));
				}else {
					Utils.showMessage("Error. No valid option");
				}
					
			} catch (InputMismatchException e) {
				Utils.showMessage("Error. The options must be a number");
				scanner.nextLine();
			}
		}
	}

	private static void showMenu() {
		Utils.showMessage("\n Select option:");
		Utils.showMessage(" [1]- " + MovesPlayer.MOVE_STRAIGHT);
		Utils.showMessage(" [2]- " + MovesPlayer.TURN_LEFT);
		Utils.showMessage(" [3]- " + MovesPlayer.TURN_RIGHT);
		Utils.showMessage(" [4]- " + MovesPlayer.LAUNCH_ARROW);
		Utils.showMessage("\n [0]- Exit ");
	}
	
	private static void exitWumpus(Scanner scanner, Integer value) {
		scanner.close();
		Utils.showMessage("Leaving the application. \nSee you soon!!!");
		System.exit(value);
	}

}
