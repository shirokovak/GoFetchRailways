package com.test.GoFetchRailways;

import java.util.List;


public class GraphFactory {

	/**
	 * @param args
	 */
	public static int calcDistance(Graph g, String stops) {
		String[] stop_points = stops.split("-");
		Node from = null;
		Node to = null;
		int route_cost = 0;
		for(String stop:stop_points){
			if(1!=stop.length()) return -2;
			if(!Character.isUpperCase(stop.charAt(0))) return -3;
			String name = "" + stop.charAt(0);
			if(!g.hasNode(name)) return -4;
			Node tmp = g.getNode(name);
			if(null==from) {
				from = tmp;
			} else {
				to = tmp;
				boolean found = false;
				for(Edge e:from.outEdges){
					if(e.to.equals(to)) { 
						route_cost += e.cost;
						found = true;
						break;
					}
				}
				if(!found) return -1;//route is invalid
				from = to;
			}
		}
		
		return route_cost;
	}

	/**
	 * @param args
	 */
	public static List journeyPlanner(Graph g, String start, String end, int maxStops, boolean exact) {
		AlgorithmGraph ag = new AlgorithmGraph(g);
		return ag.getAllRoutes(start, end, maxStops, exact);
	}

	/**
	 * @param args
	 */
	public static RouteDetails findShortestRoute(Graph g, String start, String end) {
		AlgorithmGraph ag = new AlgorithmGraph(g);
		return ag.getShortestRoute(start, end);
	}
}
