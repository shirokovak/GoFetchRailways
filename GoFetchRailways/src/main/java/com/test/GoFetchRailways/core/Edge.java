package com.test.GoFetchRailways.core;

public class Edge {
	public final Node from;
	public final Node to;
	public final int cost;

	public Edge(Node from, Node to, int c) {
		this.from = from;
		this.to = to;
		this.cost = c;
	}

	@Override
	public boolean equals(Object obj) {
		Edge e = (Edge) obj;
		return e.from == from && e.to == to && e.cost == cost;
	}
}
