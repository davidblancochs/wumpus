package com.hunter.model;

public class Position {

    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addToX(int a) {
        this.x += a;
    }

    public void addToY(int a) {
        this.y += a;
    }

    @Override
    public String toString() {
        return "[x:" + x +" ; y:" + y + "]";
    }

    public boolean equals(Position p) {
        return  this.x == p.getX() && this.y == p.getY();
    }
}
