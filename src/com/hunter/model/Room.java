package com.hunter.model;

import com.hunter.perceptions.Perception;
import com.hunter.perceptions.WumpusStink;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private RoomTypeEnum roomType;
    private List<Perception> perceptionsInHere = new ArrayList<>();

    public Room() {
        this.roomType = RoomTypeEnum._EMPTY;
    }

    public boolean isEmpty() {
        return this.roomType == RoomTypeEnum._EMPTY;
    }

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeEnum type) {
        this.roomType = type;
    }

    public void addPerception(Perception p) {
        perceptionsInHere.add(p);
    }

    public List<Perception> getPerceptionsInHere() {
        return perceptionsInHere;
    }

}
