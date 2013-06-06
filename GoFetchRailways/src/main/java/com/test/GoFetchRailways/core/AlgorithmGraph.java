package com.test.GoFetchRailways.core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class AlgorithmGraph {
	private HashMap<String,AlgorithmNode> nodes = null;
	private Graph graph;

	public HashMap<String, AlgorithmNode> getNodes() {
		return nodes;
	}

	public AlgorithmGraph(Graph g) {
		graph = g;
		HashMap<String, Node> gnodes = g.getNodes();
		nodes = new HashMap<String, AlgorithmNode>();
		for(String name:gnodes.keySet()) {
			nodes.put(name, new AlgorithmNode(name));
		}
		for(Node n:gnodes.values()) {
			for(Edge e:n.outEdges){
				getNode(n.name).addEdge(getNode(e.to.name), e.cost);
			}
		}
	}
	/**
	 * Returns a list of routes that exist from <code>start</code> point to <code>end</code> point. 
	 *
	 * @param  start  name of start point
	 * @param  end	name of end point
	 * @param  maxLevel	the maximum number of stops in route
	 * @param  exactly	founded route should have exactly maxLevel stops
	 * @return	the list of possible routes
	 */
	public List<String> getAllRoutes(String start, String end, int maxLevel, boolean exactly) {
		AlgorithmNode from = getAlgorithmNode(start);
		AlgorithmNode to = getAlgorithmNode(end);
		ArrayList<String> routes = new ArrayList<String>();
		findRoute(routes, from, to, from.name, 0, maxLevel, exactly);
		return routes;
	}
	
	private void findRoute(ArrayList<String> routes, AlgorithmNode start, AlgorithmNode end, String route, int level, int maxLevel, boolean exactly) {
		if(start.equals(end)) {
			if(exactly && level!=maxLevel) return;
			routes.add(route);
			return;
		}
		start.vizited = true;
		if(level>maxLevel) return;
		
		for(Edge e:start.outEdges) {
			if(! ((AlgorithmNode)(e.to)).vizited ) {
				findRoute(routes, (AlgorithmNode)(e.to), end, route+"-"+e.to.name, level+1, maxLevel, exactly);
				((AlgorithmNode)(e.to)).vizited = false;
			}
		}
	}
	
	/**
	 * Returns a shortest route that exist from <code>start</code> point to <code>end</code> point. 
	 *
	 * @param  start  name of start point
	 * @param  end	name of end point
	 * @return	the shortest route's details
	 */
	public RouteDetails getShortestRoute(String start, String end) {
		AlgorithmNode from = getAlgorithmNode(start);
		AlgorithmNode to = getAlgorithmNode(end);
		RouteDetails route = new RouteDetails();
		route.addStop(from.name);
		findShortestRoute(route, from, to, from.name);
		return route;
	}
	
	private void findShortestRoute(RouteDetails minRoute, AlgorithmNode start, AlgorithmNode end, String route) {
		if(start.equals(end) && start.cost>0) {
			int d = GraphFactory.calcDistance(graph, route);
			if(minRoute.getDistance() == 0 || d < minRoute.getDistance()) {
				minRoute.setDistance(d);
				minRoute.setPath(route);
			}
			return;
		}
		if(start.cost>0) start.vizited = true;
		
		for(Edge e:start.outEdges) {
			if(! ((AlgorithmNode)(e.to)).vizited ) {
				((AlgorithmNode)(e.to)).cost = start.cost + e.cost;
				findShortestRoute(minRoute, (AlgorithmNode)(e.to), end, route+"-"+e.to.name);
				((AlgorithmNode)(e.to)).vizited = false;
				((AlgorithmNode)(e.to)).cost = 0;
			}
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

	private AlgorithmNode getAlgorithmNode(String name) {
		if(null!=nodes) return nodes.get(name);
		return null;
	}  
}
