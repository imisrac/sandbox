package codingame;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;


@Getter
class SkynetRevolutionE1 {

	//	private List<List<Integer>> links = new ArrayList<>();
	private Set<Integer> exitGWs = new HashSet<>();
	private List<Node> nodes;

	SkynetRevolutionE1(int numberOfNodes) {
		//		IntStream.rangeClosed(1, numberOfNodes)
		//				.forEach(value -> links.add(new ArrayList<>()));
		nodes = new ArrayList<>(numberOfNodes);
	}

	void addLink(int node1, int node2) {
		//		links.get(node1).add(node2);
		//		links.get(node2).add(node1);
		final Node node = new Node(node1);
		final Node other = new Node(node2);
		node.connect(other);
		nodes.add(node);
		nodes.add(other);
	}

	void addExitGW(int node) {
		exitGWs.add(node);
	}

	String attack(Integer agentPosition) {
		//		links.get(agentPosition).remove(targetNode);
		//		links.get(targetNode).remove(agentPosition);
		return agentPosition + " " + search(nodes.get(agentPosition)).getValue();
	}

	private Node search(Node start) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(start);
		List<Node> alreadyVisited = new ArrayList<>();
		Node currentNode;

		while (!queue.isEmpty()) {
			currentNode = queue.remove();

			if (exitGWs.contains(currentNode.getValue())) {
				return alreadyVisited.get(0);
			} else {
				alreadyVisited.add(currentNode);
				queue.addAll(currentNode.getNeighbors());
				queue.removeAll(alreadyVisited);
			}
		}

		return new Node(0);
	}

	@Getter
	private class Node {

		private final int value;
		private Set<Node> neighbors;

		private Node(int value) {
			this.value = value;
			neighbors = new HashSet<>();
		}

		void connect(Node other) {
			this.neighbors.add(other);
			other.neighbors.add(this);
		}
	}
}