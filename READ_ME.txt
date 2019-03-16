Carlos Mora Martinez 16/03/2019

WUMPUS game

USE:

java -jar Game.jar

or:
java -jar Game.jar 60 5 9

or:

java -jar Game.jar 60 5 9 1


ARGUMENTS:
	- Number of cells in the cave. It will round down if it is not a perfect square,
	because the cave is always squared.
	- Number of wells: The more wells, the more difficult is the game.
	- Number of arrows: chances to kill the wumpus:
	- Show Cave (optional): if != 0, utomatically show the cave and its contents. If == 0, you can always use the Cheat option.
	
	Default values are provided for all parameters.
	The program can be run passing either:
		- No argument
		- The first three arguments
		- All the arguments
		
GAME INSTRUCTIONS:

The goal of the game is to find the gold in a dark cave, and exit alive.

You can move from one room to another inside of the cave.

If you move into a room where there is a well, you fall into it and die.

If you move into the room where the Wumpus is, it will eat you.

If you are in a room adjacent to a well or to the Wumpus, you can sense it,
but you cannot know the direction where the sensation comes from.

You can shoot arrows at any time; if the Wumpus happens to be in a straight line
in the direction you are facing, you kill it (it will scream painfully).

COMMANDS:
	Turns will change the direction wou are currently facing:
		TURN_UP: w
		TURN_LEFT: a
		TURN_RIGHT: d
		TURN_DOWN: s
	Move to the next room (in the direction you are facing)
		MOVE: f
	Shoot an arrow in order to try to kill the Wumpus:
		SHOOT_ARROW: g
	Display all the rooms in the Cave and their contents:
		CHEAT: v
	Quit game:
		QUIT: q