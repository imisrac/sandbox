package bowling;

import java.util.ArrayList;
import java.util.List;


public class Game {

	private List<Integer> rolls = new ArrayList<>();

	public void roll(int rollNumber) {
		rolls.add(rollNumber);
	}

	public int score() {

		//		return Arrays.stream(rolls).reduce(Integer::sum).orElse(0);
	}
}
