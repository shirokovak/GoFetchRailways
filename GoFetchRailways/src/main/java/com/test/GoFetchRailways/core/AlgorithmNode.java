package com.test.GoFetchRailways.core;


public class AlgorithmNode extends Node {
	public boolean vizited;
	public int cost;
	public AlgorithmNode(String name) {
		super(name);
		vizited = false;
		cost = 0;
	}

}
