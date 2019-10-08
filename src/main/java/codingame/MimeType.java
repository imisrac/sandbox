package codingame;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MimeType {

	private static final Scanner IN = new Scanner(System.in);

	public static void main(String args[]) {
		final int lookupTableSize = IN.nextInt();
		final int filenameListSize = IN.nextInt();

		final Map<String, String> lookupTable = readLookupTable(lookupTableSize);
		IN.nextLine();
		final List<String> filenames = readFilenames(filenameListSize);

		final String output = filenames.stream()
				.map(filename -> filename.contains(".") ? filename : "")
				.map(filename -> filename.substring(filename.lastIndexOf('.') + 1))
				.map(filename -> lookupTable.getOrDefault(filename, "UNKNOWN"))
				.collect(Collectors.joining("\n"));
		System.out.println(output);
	}

	private static List<String> readFilenames(int filenameListSize) {
		return IntStream.rangeClosed(1, filenameListSize)
				.mapToObj(value -> IN.nextLine())
				.map(String::toLowerCase)
				.collect(Collectors.toList());
	}

	private static Map<String, String> readLookupTable(int lookupTableSize) {
		return IntStream.rangeClosed(1, lookupTableSize)
				.mapToObj(String::valueOf)
				.collect(Collectors.toMap(o -> IN.next().toLowerCase(), o -> IN.next()));
	}
}
