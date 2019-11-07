package codingame;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class AsciiArt {

	private static final Scanner IN = new Scanner(System.in);
	private static final int ABC_SIZE = 26;
	private static int asciiHeight;
	private static int asciiWidth;

	public static void main(String args[]) {
		setAsciiWidth(IN.nextInt());
		setAsciiHeight(IN.nextInt());
		if (IN.hasNextLine()) {
			IN.nextLine();
		}
		List<String> upperCaseChars = getUpperCaseCharacters();
		printAsciiArt(readAsciiLetters(), upperCaseChars);
	}

	public static int getAsciiHeight() {
		return asciiHeight;
	}

	public static void setAsciiHeight(int asciiHeight) {
		AsciiArt.asciiHeight = asciiHeight;
	}

	public static int getAsciiWidth() {
		return asciiWidth;
	}

	public static void setAsciiWidth(int asciiWidth) {
		AsciiArt.asciiWidth = asciiWidth;
	}

	private static Map<Integer, List<String>> readAsciiLetters() {
		return IntStream.rangeClosed(0, getAsciiHeight() - 1)
				.boxed()
				.collect(Collectors.toMap(integer -> integer, integer -> readAsciiRow(AsciiArt.IN.nextLine())));
	}

	private static List<String> readAsciiRow(String row) {
		return IntStream.rangeClosed(0, ABC_SIZE)
				.boxed()
				.map(integer -> row.substring(integer * getAsciiWidth(), integer * getAsciiWidth() + getAsciiWidth()))
				.collect(Collectors.toList());
	}

	private static void printAsciiArt(Map<Integer, List<String>> asciiLetters, List<String> upperCaseChars) {
		IntStream.rangeClosed(0, getAsciiHeight() - 1)
				.boxed()
				.forEach(integer -> printRow(asciiLetters.get(integer), upperCaseChars));
	}

	private static void printRow(List<String> rowOfAbc, List<String> upperCaseChars) {
		System.out.println(upperCaseChars.stream()
				.map(s -> getLetterSliceForRow(s, rowOfAbc))
				.collect(Collectors.joining()));
	}

	private static List<String> getUpperCaseCharacters() {
		return Arrays.stream(IN.nextLine().toUpperCase().split("")).collect(Collectors.toList());
	}

	private static String getLetterSliceForRow(String letter, List<String> rowOfAbc) {
		return rowOfAbc.get(getAlphabetMap().getOrDefault(letter, ABC_SIZE));
	}

	private static Map<String, Integer> getAlphabetMap() {
		return IntStream.rangeClosed(0, ABC_SIZE)
				.boxed()
				.collect(Collectors.toMap(getAlphabetWithQuestionMark()::get, integer -> integer));
	}

	private static List<String> getAlphabetWithQuestionMark() {
		List<String> alphabet = IntStream.rangeClosed('A', 'Z')
				.mapToObj(value -> (char) value)
				.map(String::valueOf)
				.collect(Collectors.toList());
		alphabet.add("?");
		return alphabet;
	}
}
