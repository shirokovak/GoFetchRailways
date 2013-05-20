package com.test.GoFetchRailways.core;

public class RouteDetails {
	private int distance;
	private StringBuilder path;
	public RouteDetails() {
		distance = 0;
		path = new StringBuilder();
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public void addStop(String s) {
		if(path.length() == 0) 
			path.append(s);
		else
			path.append("-"+s);
	}
	public String getPath() {
		return path.toString();
	}
	public void setPath(String p) {
		path = new StringBuilder(p);
	}
	
}
