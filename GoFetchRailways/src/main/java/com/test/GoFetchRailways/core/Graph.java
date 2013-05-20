package com.test.GoFetchRailways.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Properties;

public class Graph {
	private HashMap<String,Node> nodes = null;

	public HashMap<String, Node> getNodes() {
		return nodes;
	}

	public void initDefault() {
		String[] node_names = {"A","B","C","D","E","F","G","H","I","J"};
		nodes = new HashMap<String,Node>();
		for(String name:node_names) {
			nodes.put(name, new Node(name));
		}
		nodes.get("A").addEdge(nodes.get("B"), 12);
		nodes.get("A").addEdge(nodes.get("D"), 19);
		nodes.get("A").addEdge(nodes.get("E"), 20);
		nodes.get("A").addEdge(nodes.get("G"), 16);
		nodes.get("B").addEdge(nodes.get("D"), 13);
		nodes.get("B").addEdge(nodes.get("C"), 5);
		nodes.get("B").addEdge(nodes.get("I"), 15);
		nodes.get("C").addEdge(nodes.get("D"), 5);
		nodes.get("D").addEdge(nodes.get("E"), 7);
		nodes.get("E").addEdge(nodes.get("F"), 5);
		nodes.get("F").addEdge(nodes.get("A"), 5);
		nodes.get("G").addEdge(nodes.get("F"), 11);
		nodes.get("H").addEdge(nodes.get("A"), 4);
		nodes.get("H").addEdge(nodes.get("G"), 6);
		nodes.get("I").addEdge(nodes.get("H"), 21);
		nodes.get("I").addEdge(nodes.get("J"), 10);
		nodes.get("J").addEdge(nodes.get("B"), 7);
		nodes.get("J").addEdge(nodes.get("C"), 15);
	}
	
	public void load(String file) throws FileNotFoundException,
			IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(file));
		Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();
		nodes = new HashMap<String,Node>();
		while (keys.hasMoreElements()) {
			String k = keys.nextElement();
			if (k.length() != 2)
				continue;
			String fromName = "" + k.charAt(0);
			Node from = null;
			if(nodes.containsKey(fromName)){ 
				from = nodes.get(fromName);
			} else {
				from = new Node(fromName);
				nodes.put(fromName, from);
			}
			String toName = "" + k.charAt(1);
			Node to = null;
			if(nodes.containsKey(toName)){ 
				to = nodes.get(toName);
			} else {
				to = new Node(toName);
				nodes.put(toName, to);
			}
			int cost = Integer.parseInt(prop.getProperty(k));
			from.addEdge(to, cost);
		}
	}
	
	public Boolean hasNode(String name) {
		if(null!=nodes && nodes.containsKey(name)) return true;
		return false;
	}
	
	public Node getNode(String name) {
		if(null!=nodes) return nodes.get(name);
		return null;
	}
  
}
