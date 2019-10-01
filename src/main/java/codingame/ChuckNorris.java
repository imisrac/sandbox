package codingame;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ChuckNorris {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String message = in.nextLine();

		String binaryMessage = message.chars()
				.boxed()
				.map(Integer::toBinaryString)
				.map(ChuckNorris::getWithPadding)
				.collect(Collectors.joining());

		StringBuilder output = new StringBuilder();
		for (int i = 0; i < binaryMessage.length(); i++) {
			int count = 1;
			String binaryChar = binaryMessage.split("")[i];
			while (i < binaryMessage.length() - 1 && binaryChar.equals(binaryMessage.split("")[i + 1])) {
				count++;
				i++;
			}
			if (binaryChar.equals("1")) {
				output.append("0 ");
			} else {
				output.append("00 ");
			}
			output.append(IntStream.generate(() -> 0).limit(count).mapToObj(String::valueOf).collect(Collectors.joining())).append(" ");
		}

		System.out.println(output.toString().trim());
	}

	private static String getWithPadding(String binaryChar) {
		if (binaryChar.length() < 7) {
			binaryChar =
					IntStream.generate(() -> 0).limit(7L - binaryChar.length()).mapToObj(String::valueOf).collect(Collectors.joining())
							+ binaryChar;
		}
		return binaryChar;
	}
}
