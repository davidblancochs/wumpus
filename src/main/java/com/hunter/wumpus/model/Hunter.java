package com.hunter.wumpus.model;

/**
 * Representing a Hunter who can move.
 * Also has arrows, can beat a beast, win the game and so on...
 */
public class Hunter implements IAbleToMove {

    private Direction direction = new Direction(DirectionEnum.EAST); // starts game lookin to East
    private boolean hasBeatenWumpus = false;
    private boolean gotMoney = false;
    private boolean isAWinner = false;
    private boolean isAlive = true;

    private Position actualPosition;
    private int arrowsLeft;

    public Hunter (int arrowsCount, int labyrinthSize) {
        this.arrowsLeft = arrowsCount;
        this.actualPosition = new Position(0, labyrinthSize-1);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getArrowsLeft() {
        return arrowsLeft;
    }

    public void setArrowsLeft(int arrowsLeft) {
        this.arrowsLeft = arrowsLeft;
    }

    public boolean hasBeatenWumpus() {
        return hasBeatenWumpus;
    }

    public void setHasBeatenWumpus(boolean beatedWumpus) {
        this.hasBeatenWumpus = beatedWumpus;
    }

    public boolean isAWinner() {
        return isAWinner;
    }

    public void setWinner() {
        this.isAWinner = true;
    }

    public Position getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(Position actualPosition) {
        this.actualPosition = actualPosition;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void killedByWumpus() {
        this.isAlive = false;
    }

    public void fallenIntoDarkness() {
        this.isAlive = false;
    }

    public void grabTheGold() {
        this.gotMoney = true;
    }

    public boolean isGotMoney() {
        return gotMoney;
    }



}
