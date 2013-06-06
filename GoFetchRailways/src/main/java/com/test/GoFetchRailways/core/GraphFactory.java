package com.test.GoFetchRailways.core;

import java.util.List;



public class GraphFactory {

	/**
	 * Returns a distance for route. 
	 *
	 * @param  g  graph data
	 * @param  stops  route in format '<name_stop1>-<name_stop2>-<name_sop3>'
	 * @return	the positive value with distance for the route if it is valid, negative value otherwise
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
	 * Returns a list of routes that exist from <code>start</code> point to <code>end</code> point. 
	 *
	 * @param  g  graph data
	 * @param  start  name of start point
	 * @param  end	name of end point
	 * @param  maxStops	the maximum number of stops in route
	 * @param  exact	founded route should have exactly maxLevel stops
	 * @return	the list of possible routes
	 */
	public static List<String> journeyPlanner(Graph g, String start, String end, int maxStops, boolean exact) {
		AlgorithmGraph ag = new AlgorithmGraph(g);
		return ag.getAllRoutes(start, end, maxStops, exact);
	}

	/**
	 * Returns a shortest route that exist from <code>start</code> point to <code>end</code> point. 
	 *
	 * @param  g  graph data
	 * @param  start  name of start point
	 * @param  end	name of end point
	 * @return	the shortest route's details
	 */
	public static RouteDetails findShortestRoute(Graph g, String start, String end) {
		AlgorithmGraph ag = new AlgorithmGraph(g);
		return ag.getShortestRoute(start, end);
	}
}
