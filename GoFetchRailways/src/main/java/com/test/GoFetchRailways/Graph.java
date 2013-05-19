package com.test.GoFetchRailways;

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
