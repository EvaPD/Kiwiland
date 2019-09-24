package com.kiwiland.kiwilandTrains;

public class Station {
	
	//Destination City
	private City destination;
	//Origin City
	private City origin;
	//Distance of the train route
	private int distance;
	//Next possible station in route
	private Station nextStation;
		
	public Station(City origin, City destination, int weight) {
		this.origin 		= origin;
		this.destination	= destination;
		this.distance 		= weight;
		this.nextStation 		= null;
	}

	public Station nextStation(Station border) {
		this.nextStation = border;
		return this;
	}

	
	/**
	 *Getters & Setters 
	 */
	
	
	public City getDestination() {
		return destination;
	}

	public void setDestination(City destination) {
		this.destination = destination;
	}

	public City getOrigin() {
		return origin;
	}

	public void setOrigin(City origin) {
		this.origin = origin;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Station getNextStation() {
		return nextStation;
	}

	public void setNextStation(Station nextStation) {
		this.nextStation = nextStation;
	}

}