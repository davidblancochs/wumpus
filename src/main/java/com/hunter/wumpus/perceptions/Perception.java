package com.hunter.wumpus.perceptions;

public abstract class Perception {

    public String feelsLike() {
        return " - it feels like " + this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        return this.getClass().equals(o.getClass());
    }
}
