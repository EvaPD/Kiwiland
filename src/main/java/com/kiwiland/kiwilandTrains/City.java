package com.kiwiland.kiwilandTrains;

public class City {
	
	private String name;
	private boolean visited;
 
	public City(String name) {
		this.name = name;
		this.visited = false;
	}
	
	@Override
	public boolean equals(Object b) {
		if (b == null || b.getClass() != getClass()) {
	        return false;
	    }
		City bx = (City)b;
		return this.name.equals(bx.name);
	}
 
	@Override
	public int hashCode() {
		if(this.name == null) return 0;
		return this.name.hashCode();
	}

	/**
	 * Getters & Setters	 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean passed) {
		this.visited = passed;
	}
}


