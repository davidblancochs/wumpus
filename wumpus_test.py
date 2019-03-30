##Carlos Mora
##27-03-2019

import wumpus as ww
import numpy as np



#1) Test movement
def compareDir(a, b):
    return a[0] == b[0] and a[1] == b[1]

mat = np.zeros([5, 5])
g = ww.Game(0,0,0, mat)

g.turnPlayerDown() 
a = compareDir(g.player.getDirection(), ww.DOWN) 
g.turnPlayerLeft()  
b = compareDir(g.player.getDirection(), ww.LEFT) 
g.turnPlayerRight() 
c = compareDir(g.player.getDirection(), ww.RIGHT) 
g.turnPlayerUp() 
d = compareDir(g.player.getDirection(), ww.UP) 
if(a and b and c and d):
    print("Directions OK")
else:
    print("Change direction: did not pass test")

x, y = g.player.getPosition()
g.turnPlayerLeft() 
g.movePlayer()
a = compareDir((x, y), g.player.getPosition()) #must be the same (check doesn't move beyond the matrix)
if('HUNTER_HIT_WALL' in g.events):
    sense_hitWall = True
g.turnPlayerUp() 
g.movePlayer()
b = compareDir((x, y), g.player.getPosition()) #must be the same (check doesn't move beyond the matrix)
g.turnPlayerRight() 
g.movePlayer()
c = compareDir((x, y+1), g.player.getPosition()) 
g.movePlayer()
d = compareDir((x, y+2), g.player.getPosition()) 
g.turnPlayerDown() 
g.movePlayer()
e = compareDir((x+1, y+2), g.player.getPosition()) 
g.movePlayer()
f = compareDir((x+2, y+2), g.player.getPosition()) 
g.turnPlayerUp() 
g.movePlayer()
gg = compareDir((x+1, y+2), g.player.getPosition()) 
g.movePlayer()
h = compareDir((x, y+2), g.player.getPosition()) 
g.turnPlayerLeft() 
g.movePlayer()
i = compareDir((x, y+1), g.player.getPosition()) 
g.movePlayer()
j = compareDir((x, y), g.player.getPosition()) 
if(a and b and c and d and e and f and gg and h and i and j):
    print("Movement in all directions OK")
else:
    print("Movements are bad at some point")
if(sense_hitWall):
    print("Hunter senses hit wall: OK")
else:
    print("Problem sensing when hunter hits wall")
g.getNeighbourSignals()
if('CELL_HAS_EXIT' in g.events):
    print("Hunter knows where EXIT is: OK")
else:
    print("Problem sensing EXIT")


#Test if Well works well
mat = np.zeros([5, 5])
mat[0, 1]  = ww.WELL
g = ww.Game(0,0,0, mat)
g.getNeighbourSignals()
#Check if Hunter senses well
if('SENSE_WELL' in g.events):
    a = True
g.turnPlayerRight() 
g.movePlayer()
g.foundWell()
if(not g.player.isAlive() and g.events[0] == 'HUNTER_KILLED_BY_WELL'):
    b = True
if(a and b):
    print('Well can be sensed and kills Hunter: OK')
else:
    print('problem with WELL')
    


#Test if Gold works well
mat = np.zeros([5, 5])
mat[0, 1]  = ww.GOLD
g = ww.Game(0,0,0, mat)
g.getNeighbourSignals()

g.turnPlayerRight()
g.events.clear() 
g.movePlayer()
g.foundGold()
if(g.player.hasGold() and g.events[0] == 'HUNTER_GOT_GOLD'):
    a = True
g.events.clear()
#gold must be sensed only once
g.movePlayer()
g.turnPlayerLeft() 
g.movePlayer()
if(g.player.hasGold() and not 'HUNTER_GOT_GOLD' in g.events):
    b = True
if(a and b):
    print('Gold works OK')
else:
    print('problem with GOLD')


#Test if Wumpus works well
mat = np.zeros([5, 5])
mat[0, 1]  = ww.WUMPUS
g = ww.Game(0,0,0, mat)
g.getNeighbourSignals()
#Check if Hunter senses Wumpus
if('SENSE_WUMPUS' in g.events):
    a = True
g.turnPlayerRight() 
g.movePlayer()
g.foundWell()
if(not g.player.isAlive() and g.events[0] == 'HUNTER_KILLED_BY_WUMPUS'):
    b = True
if(a and b):
    print('WUMPUS can be sensed and kills Hunter: OK')
else:
    print('problem with WUMPUS')
      

#Test if Wumpus can be killed and Arrows work as expected
#Throw arrows in all direction, including Wumpus direction
#Check arrow number and events
mat = np.zeros([5, 5])
mat[0, 4]  = ww.WUMPUS
g = ww.Game(0,0,5, mat)
g.turnPlayerLeft()
g.events.clear()
g.shootArrow()
if(g.player.getArrows() == 4 and 'ARROW_DIDNT_HIT' in g.events):
    a = True
else:
    a = False

g.turnPlayerUp()
g.events.clear()
g.shootArrow()
if(g.player.getArrows() == 3 and 'ARROW_DIDNT_HIT' in g.events):
    b = True
else:
    b = False

g.turnPlayerDown()
g.events.clear()
g.shootArrow()
if(g.player.getArrows() == 2 and 'ARROW_DIDNT_HIT' in g.events):
    c = True
else:
    c = False

g.turnPlayerRight()
g.events.clear()
g.shootArrow()
if(g.player.getArrows() == 1 and 'WUMPUS_KILLED' in g.events):
    d = True
else:
    d = False

g.events.clear()
g.shootArrow()
if(g.player.getArrows() == 0 and 'WUMPUS_ALREADY_DEAD' in g.events):
    e = True
else:
    e = False

g.events.clear()
g.shootArrow()
if(g.player.getArrows() == 0 and 'NO_ARROWS_LEFT' in g.events):
    f = True
else:
    f = False
g.events.clear()
for i in range(4):
    g.movePlayer()
if('DEAD_WUMPUS_FOUND' in g.events):
    gg = True
else:
    gg = False

if(a and b and c and d and e and f and gg):
    print("No problem shooting arrows and killing wumpus: OK")
else:
    print("Problem with ARROWS")

##Finally, try exit:
mat = np.zeros([5, 5])
mat[0, 1]  = ww.GOLD
g = ww.Game(0,0,0, mat)
g.exitCave()
if('EXITED' in g.events):
    print('Exit the cave: OK')
else:
    print('Problem exiting the cave')



