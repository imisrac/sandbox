import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int numberOfNodes = in.nextInt(); // the total number of nodes in the level, including the gateways
		int numberOfLinks = in.nextInt(); // the number of links
		int numberOfExitGateways = in.nextInt(); // the number of exit gateways
		List<List<Integer>> links = new ArrayList<>();
		List<Integer> exitGWs;

		IntStream.rangeClosed(1, numberOfLinks)
				.forEach(value -> links.add(new ArrayList<>()));

		for (int i = 0; i < numberOfLinks; i++) {
			int node1 = in.nextInt();
			int node2 = in.nextInt();
			if (links.size() <= node1 || links.get(node1) == null) {
				links.add(node1, new ArrayList<>());
			}
			links.get(node1).add(node2);
		}
		exitGWs = IntStream.rangeClosed(1, numberOfExitGateways)
				.mapToObj(operand -> in.nextInt())
				.collect(Collectors.toList());

		// game loop
		while (true) {
			Integer skynetAgentIndex = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

			System.out.println(skynetAgentIndex + " " + links.get(skynetAgentIndex).get(0));
		}
	}
}