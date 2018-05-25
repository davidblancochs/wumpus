package com.hunter.wumpus.model;

/**
 * Its a square board defined by Width and consist of Rooms of different types
 */
public class Labyrinth {

    private Room[][] rooms;

    public Labyrinth(int width) {
        rooms = new Room[width][width];
        for (int i = 0; i<width; i++) {
            for (int j = 0; j<width; j++) {
                rooms[i][j] = new Room();
            }
        }
        rooms[0][width-1].setRoomType(RoomTypeEnum.EXIT);
    }

    public int getWidth() {
        return rooms.length;
    }

    public Room getRoom(Position p) {
        return rooms[p.getX()][p.getY()];
    }

    public Room getRoom(int x, int y) {
        if (0 <= x && x < this.getWidth() && 0 <= y && y < this.getWidth()) {
            return rooms[x][y];
        } else {
            return null;
        }
    }

    public void setXYRoomTo(RoomTypeEnum type, Position pos) {
        rooms[pos.getX()][pos.getY()].setRoomType(type);
    }

}
