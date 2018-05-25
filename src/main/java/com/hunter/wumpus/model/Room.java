package com.hunter.wumpus.model;

import java.util.ArrayList;
import java.util.List;

import com.hunter.wumpus.perceptions.Perception;

public class Room {

    private RoomTypeEnum roomType;
    private List<Perception> perceptionsInHere = new ArrayList<>();

    public Room() {
        this.roomType = RoomTypeEnum._EMPTY;
    }
    

    public boolean isEmpty() {
        return this.roomType == RoomTypeEnum._EMPTY;
    }
    
    public boolean isPIT() {
    	return this.roomType == RoomTypeEnum.PIT;
    }
    
    public boolean isWUMPUS() {
    	return this.roomType == RoomTypeEnum.WUMPUS;
    }
    
    public boolean isGOLD() {
    	return this.roomType == RoomTypeEnum.GOLD;
    }
    
    public boolean isEXIT() {
    	return this.roomType == RoomTypeEnum.EXIT;
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
