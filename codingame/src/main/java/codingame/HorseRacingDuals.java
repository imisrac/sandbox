package codingame;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class HorseRacingDuals {

	private static final Scanner IN = new Scanner(System.in);

	public static void main(String args[]) {
		int numberOfHorses = IN.nextInt();
		final List<Integer> horsePowers = IntStream.rangeClosed(1, numberOfHorses)
				.mapToObj(value -> IN.nextInt())
				.collect(Collectors.toList());

		System.out.println(getSmallestDifference(horsePowers));
	}

	private static Integer getSmallestDifference(List<Integer> horsePowers) {
		horsePowers.sort(Integer::compareTo);
		return IntStream.rangeClosed(1, horsePowers.size() - 1)
				.mapToObj(operand -> Math.abs(horsePowers.get(operand - 1) - horsePowers.get(operand)))
				.min(Integer::compareTo)
				.orElse(0);
	}

}
