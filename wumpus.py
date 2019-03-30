##Carlos Mora
##27-03-2019

import sys
from collections import namedtuple

import numpy as np


#CONSTANTS
#Game elements
WUMPUS = 1
WELL = 2
GOLD = 3
EMPTY = 0
EXIT_LOC = (0,0)
#Directions:
Direction = namedtuple("Direction", "x y")
UP = Direction(-1, 0)
DOWN = Direction(1, 0)
LEFT = Direction(0, -1)
RIGHT = Direction(0, 1)

#Store output messages
event_sentences  = {
		'HUNTER_MOVED' : "You moved to new cell successfully.", 
		'HUNTER_HIT_WALL' : "You hit a wall; can't go there.", 
		'HUNTER_KILLED_BY_WELL' : "Hunter fell into a well.", 
		'HUNTER_KILLED_BY_WUMPUS' : "Hunter was killed by Wumpus.",
		'HUNTER_GOT_GOLD' : "Great! You found GOLD.",
		'WUMPUS_KILLED' : "Wumpus screams painfully. You killed the Wumpus.",
		'WUMPUS_ALREADY_DEAD' : "You hit the Wumpus, but it was already dead.",
		'DEAD_WUMPUS_FOUND' : "Dead Wumpus is here!",
		'ARROW_DIDNT_HIT' : "...Silence... Apparently you did not hit anything.",
		'NO_ARROWS_LEFT' : "You have no arrows left.",
		'EXITED' : "You exited the cave.",
		'CELL_HAS_EXIT' : "Here it is the exit.",
		'SENSE_WELL' : "Wind blows... there must be a well nearby.",
		'SENSE_WUMPUS':"Smells like Wumpus.",
		'WON_GAME' : "Great! I exited the cave with gold (and alive).",
	}

#Store commands		
userAction  = {
		'w' : 'TURN_UP',
		'a' : 'TURN_LEFT',
		'd' : 'TURN_RIGHT',
		's' : 'TURN_DOWN',
		'f' : 'MOVE',
		'g' : 'SHOOT_ARROW',
		'v' : 'CHEAT',
		'q' : 'QUIT',
                'e' : 'EXIT_CAVE'		
	}	

	
class Player():
    def __init__(self, x, y, arrows):
        self.alive = True
        self.inside = True
        self.arrows = arrows
        self.gold = False
        self.x = x
        self.y = y
        self.direction = UP        
    def isAlive(self):
        return self.alive
    def isInside(self):
        return self.inside
    def getPosition(self):
        return (self.x, self.y)
    def getDirection(self):
        return self.direction
    def hasGold(self):
        return self.gold
    def getArrows(self):
        return self.arrows
    def setPosition(self, x, y):
        self.x = x
        self.y = y
    def setGold(self):
        self.gold = True
    def throwArrow(self):
        if(self.arrows>0):
            self.arrows-=1
            return True
        else:
            return False
    def kill(self):
        self.alive = False
    def exit(self):
        self.inside = False
    def turn(self, newdir):
        self.direction = newdir
    def calcNextStep(self):
        return (self.x + self.direction[0], self.y + self.direction[1])


class Game():
    takeActionMap  = {
		'TURN_UP' : 'turnPlayerUp',
		'TURN_LEFT': 'turnPlayerLeft',
		'TURN_RIGHT' : 'turnPlayerRight',
                'TURN_DOWN': 'turnPlayerDown',
		'MOVE' : 'movePlayer',
		'SHOOT_ARROW' : 'shootArrow',
		'CHEAT': 'cheat',
                'EXIT_CAVE': 'exitCave'		
	}    
    def __init__(self, size, nwells, narrows, mat=None):
        self.exit = EXIT_LOC
        if(mat is None):
            self.original_size = size
            self.side = int(np.sqrt(size))
            self.size = self.side*self.side
            assert self.size - 3 > nwells, "Use a lower number of wells! Otherwise it will be impossible to set up the game"
            self.board = np.zeros([self.side, self.side])
            self.wells = nwells
            self.fillBoard(nwells)
        else: #This can be used for tests; initialize a Game instance with a predefined matrix so you can then check whether you get the expected output
            assert mat.shape[0] == mat.shape[1], "Board matrix needs to be squared"
            self.board=mat
            self.original_size = mat.size
            self.size = mat.size
            self.side = mat.shape[0]
            self.wells = np.where(mat == WELL)[0].size
        self.wumpus_dead = False
        self.player = Player(*self.exit, narrows)
        self.events = []
    def fillBoard(self, newlls):
        aux = self.board.reshape([self.size])
        aux[0] = WUMPUS
        aux[1] = GOLD
        for i in range(3, self.wells + 3):
            aux[i] = WELL
        while True:
            np.random.shuffle(aux)
            if(aux[0] == EMPTY):
                break
        self.board = aux.reshape([self.side, self.side])
    def getNeighbours(self, pos):
        x, y = pos
        neigh = []
        if(x > 0):
            neigh.append(self.board[(x-1, y)])
        if(y > 0):
            neigh.append(self.board[(x, y-1)])
        if(x < self.side - 1) :
            neigh.append(self.board[(x+1, y)])
        if(y < self.side - 1):
            neigh.append(self.board[(x, y+1)])
        return neigh
    def getNeighbourSignals(self):
        ppos = self.player.getPosition()
        neigh = self.getNeighbours(ppos)       
        if(WUMPUS in neigh and not self.wumpus_dead):
            self.events.append('SENSE_WUMPUS')
        if(WELL in neigh):
            self.events.append('SENSE_WELL')
        if(self.exit[0] == ppos[0] and self.exit[1] == ppos[1]):
            self.events.append('CELL_HAS_EXIT')
    def movePlayer(self):
        newx, newy = self.player.calcNextStep()
        if(newx < 0 or newy < 0 or newx >= self.side or newy >= self.side):
            self.getNeighbourSignals()
            self.events.append('HUNTER_HIT_WALL')
        else:
            self.player.setPosition(newx, newy)
            self.foundWumpus()
            self.foundWell()
            if(self.player.isAlive()):
                self.foundGold()
                self.getNeighbourSignals()
    def foundWumpus(self):
        if(self.board[self.player.getPosition()] == WUMPUS):
            if(self.wumpus_dead):
                self.events.append('DEAD_WUMPUS_FOUND')
            else:
                self.player.kill()
                self.events.append('HUNTER_KILLED_BY_WUMPUS')          
    def foundWell(self):
       if(self.board[self.player.getPosition()] == WELL):
            self.player.kill()
            self.events.append('HUNTER_KILLED_BY_WELL')
    def foundGold(self):
       if(self.board[self.player.getPosition()] == GOLD and not self.player.hasGold()):
            self.player.setGold()
            self.events.append('HUNTER_GOT_GOLD')
    def shootArrow(self):
        if(not self.player.throwArrow()):
            self.events.append('NO_ARROWS_LEFT')
        else:
            dx, dy = self.player.getDirection()
            x, y = self.player.getPosition()
            wumpuskiller = False
            if(dx == 1): #down
                if(np.any(self.board[(x+1):, y] == WUMPUS)):
                    self.killWumpus()
                else:
                    self.events.append('ARROW_DIDNT_HIT')
            elif(dx == -1): #up
                if(np.any(self.board[:x, y] == WUMPUS)):
                    self.killWumpus()
                else:
                    self.events.append('ARROW_DIDNT_HIT')
            elif(dy == 1):  #right
                if(np.any(self.board[x, (y+1):] == WUMPUS)):
                    self.killWumpus()
                else:
                    self.events.append('ARROW_DIDNT_HIT')   
            else: # dy == -1; left
                if(np.any(self.board[x, :y] == WUMPUS)):
                    self.killWumpus()
                else:
                    self.events.append('ARROW_DIDNT_HIT')
    def killWumpus(self):
        if(self.wumpus_dead):
            self.events.append('WUMPUS_ALREADY_DEAD')
        else:
            self.wumpus_dead = True
            self.events.append('WUMPUS_KILLED')
    def exitCave(self):
        x, y = self.player.getPosition()
        if(x == self.exit[0] and y == self.exit[1]):
            self.player.exit()
            self.events.append('EXITED')
            if(self.player.hasGold()):
                self.events.append('WON_GAME')
    def cheat(self):
        print("CODE: WUMPUS", WUMPUS, "; WELL: ", WELL, "; GOLD: ", GOLD)
        print("EXIT", self.exit, "; YOU: 8", self.player.getPosition())
        pos = self.player.getPosition()
        aux = self.board[pos]
        self.board[pos] = 8
        print(self.board)
        self.board[pos] = aux
        print("\n****\n")
    def turnPlayerUp(self):
        self.player.turn(UP)
    def turnPlayerDown(self):
        self.player.turn(DOWN)
    def turnPlayerLeft(self):
        self.player.turn(LEFT)
    def turnPlayerRight(self):
        self.player.turn(RIGHT)
    def getAction(self):
        ch = sys.stdin.read(1).lower()
        while(not ch in userAction.keys()):
            ch = sys.stdin.read(1).lower()
        return userAction[ch]
    def takeAction(self, action):
        getattr(self, self.takeActionMap[action])()       
    def play(self):
        self.getNeighbourSignals()
        self.printSignals()
        while(self.player.isAlive() and self.player.isInside()):
            self.events.clear()
            action = self.getAction()
            if(action == 'QUIT'):
                break
            self.takeAction(action)
            self.printSignals()
    def printSignals(self):
        for s in self.events:
            print(event_sentences[s])
        if(self.player.isAlive() and self.player.isInside()):
            print('; '.join([k + ': ' + v for k, v in zip(userAction.keys(), userAction.values())]))
            if(self.player.hasGold()):
                print('You have the gold and ', self.player.getArrows(), ' arrows left')
            else:
                print('You have ', self.player.getArrows(), ' arrows left')
            print("\n***\n")


def main():
    if(len(sys.argv) == 4):
        size = int(sys.argv[1])
        wells = int(sys.argv[2])
        arrows = int(sys.argv[3])
    else:
        size, wells, arrows = (64, 10, 10)
    g = Game(size, wells, arrows)
    g.play()


if __name__== "__main__":
    main()
            
