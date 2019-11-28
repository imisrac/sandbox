package codingame;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.Iterator;
import java.util.Map;


@Getter
class MarsLander2 {

	private final int MAX_POWER = 4;
	private final int VSPEED_THRESHOLD = -40;
	private final int MIN_POWER = 0;
	private Integer endOfFlatGround;
	private Integer startOfFlatGround;

	MarsLander2(Map<Integer, Integer> surfacePoints) {
		final Iterator<Map.Entry<Integer, Integer>> iterator = surfacePoints.entrySet().iterator();
		Map.Entry<Integer, Integer> entry = iterator.next();
		if (!iterator.hasNext()) {
			throw new IndexOutOfBoundsException("Surface needs at least two points!");
		}
		while (iterator.hasNext()) {
			Integer height = entry.getValue();
			final Map.Entry<Integer, Integer> next = iterator.next();
			final Integer nextHeight = next.getValue();
			if (height.equals(nextHeight)) {
				startOfFlatGround = entry.getKey();
				endOfFlatGround = next.getKey();
			}
			entry = next;
		}
	}

	Map<String, Integer> land(int posX, int posy, int hSpeed, int vSpeed, int fuel, int angle, int power) {
		final int nextRotate = 0;
		int nextPower = 3;

		if (vSpeed < VSPEED_THRESHOLD) {
			nextPower++;
		}

		return ImmutableMap.of("rotate", nextRotate, "power", nextPower);
	}
}
