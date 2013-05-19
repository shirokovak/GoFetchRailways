package com.test.GoFetchRailways;

import java.util.HashSet;

public class Node {
	public final String name;
	public final HashSet<Edge> inEdges;
	public final HashSet<Edge> outEdges;

	public Node(String name) {
		this.name = name;
		inEdges = new HashSet<Edge>();
		outEdges = new HashSet<Edge>();
	}

	public Node addEdge(Node node, int cost) {
		Edge e = new Edge(this, node, cost);
		outEdges.add(e);
		node.inEdges.add(e);
		return this;
	}

	@Override
	public String toString() {
		return name;
	}
}
