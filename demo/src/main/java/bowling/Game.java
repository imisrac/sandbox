package bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Game {

	private List<Integer> rolls = new ArrayList<>();

	public void roll(int rollNumber) {
		rolls.add(rollNumber);
	}

	public int score() {

				return rolls.stream().reduce(Integer::sum).orElse(0);
	}
}
