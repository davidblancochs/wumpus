package wumpus;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author robtub
 *
 */
public enum MovesPlayer {
	MOVE_STRAIGHT(1), TURN_LEFT(2), TURN_RIGHT(3), LAUNCH_ARROW(4);

	private Integer value;

	MovesPlayer(Integer movesValue) {
		this.value = movesValue;
	}

	public Integer getValue() {
		return value;
	}

	// ****** Reverse Lookup Implementation************//

	// Lookup table
	private static final Map<Integer, MovesPlayer> lookup = new HashMap<>();

	// Populate the lookup table on loading time
	static {
		for (MovesPlayer move : MovesPlayer.values()) {
			lookup.put(move.getValue(), move);
		}
	}

	public static MovesPlayer get(Integer value) {
		return lookup.get(value);
	}

}
