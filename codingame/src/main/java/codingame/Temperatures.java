package codingame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Temperatures {

	private static final int MAX_TEMPERATURE = 5527;
	public static final int MIN_TEMPERATURE = -274;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // the number of temperatures to analyse
		List<Integer> temperatures = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
			temperatures.add(t);
		}

		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");
		if (temperatures.isEmpty()) {
			System.out.println("0");
		} else {
			int closestPositive = temperatures.stream()
					.filter(temperature -> temperature > 0)
					.mapToInt(Integer::valueOf)
					.min()
					.orElse(MAX_TEMPERATURE);
			int closestNegative = temperatures.stream()
					.filter(temperature -> temperature < 0)
					.mapToInt(Integer::valueOf)
					.max()
					.orElse(MIN_TEMPERATURE);

			if (closestNegative > MIN_TEMPERATURE && Math.abs(closestNegative) < closestPositive) {
				System.out.println(closestNegative);
			} else {
				System.out.println(closestPositive);
			}
		}
	}
}
