package codingame;

import lombok.Getter;
import lombok.extern.java.Log;

import java.util.logging.Level;


@Getter
@Log
class MarsLander {

	private static final int MIN_VSPEED = -40;
	private static final int MAX_POWER = 4;
	private float vSpeed = 0;
	private int power = 0;
	private final float gravity;

	MarsLander(float gravity) {
		this.gravity = gravity;
	}

	void land(int fuel, int height) {
		while (height > 0) {
			if (fuel > 0) {
				if (vSpeed - gravity + power < MIN_VSPEED) {
					increasePower(fuel);
				} else {
					decreasePower(fuel);
				}
			} else {
				power = 0;
				log.log(Level.WARNING, "Fuel tank empty.");
			}
			vSpeed += power - gravity;
			fuel -= power;
			height--;
		}
		if (vSpeed < MIN_VSPEED) {
			log.log(Level.SEVERE, "Marslander chrashed.");
		}
	}

	private void decreasePower(int fuel) {
		if (power > 0) {
			if (fuel >= power - 1) {
				power--;
			} else {
				power = fuel;
			}
		}
	}

	private void increasePower(int fuel) {
		if (power < MAX_POWER) {
			if (fuel > power) {
				power++;
			} else {
				log.log(Level.WARNING, "Not enough fuel to increase power.");
			}
		}
	}
}
