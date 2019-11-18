package codingame;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class SkynetRevolutionE1 {

	private List<Node> nodes;
	private List<Integer> exitGWs = new ArrayList<>();

	SkynetRevolutionE1(int numberOfNodes) {
		nodes = IntStream.rangeClosed(0, numberOfNodes - 1)
				.boxed()
				.map(Node::new)
				.collect(Collectors.toList());
	}

	void addLink(int node1, int node2) {
		nodes.get(node1).addLinkTo(nodes.get(node2));
		nodes.get(node2).addLinkTo(nodes.get(node1));
	}

	void addExitGW(int exitGW) {
		exitGWs.add(exitGW);
	}

	String attack(int agentPosition) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(getNodeForValue(agentPosition));
		Node currentNode;
		Set<Node> alreadyVisited = new HashSet<>();

		while (!queue.isEmpty()) {
			currentNode = queue.remove();
			if (hasALinkToAnyGW(currentNode)) {
				final Node closestExitGW = getExitGWFromNodes(currentNode.getLinks());
				currentNode.getLinks().remove(closestExitGW);
				return currentNode.getValue() + " " + closestExitGW.getValue();
			} else {
				alreadyVisited.add(currentNode);
				queue.addAll(currentNode.getLinks());
				queue.removeAll(alreadyVisited);
			}
		}
		return "-1 -1";
	}

	private boolean hasALinkToAnyGW(Node node) {
		return node.getLinks().stream()
				.map(Node::getValue)
				.anyMatch(this::isExitGW);
	}

	private Node getNodeForValue(int value) {
		return nodes.stream()
				.filter(node -> value == node.getValue())
				.findAny()
				.orElseThrow(() -> new IndexOutOfBoundsException("No Node found with value: " + value));
	}

	private Node getExitGWFromNodes(List<Node> nodes) {
		return nodes.stream()
				.filter(node -> isExitGW(node.getValue()))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("No exit gateway found within nodes: " + nodes));
	}

	private boolean isExitGW(Integer nodeValue) {
		return exitGWs.stream()
				.anyMatch(nodeValue::equals);
	}

	@Getter
	private static class Node {

		private final int value;
		private List<Node> links = new ArrayList<>();

		Node(int value) {
			this.value = value;
		}

		void addLinkTo(Node other) {
			links.add(other);
		}
	}
}