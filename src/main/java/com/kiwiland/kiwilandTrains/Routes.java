package com.kiwiland.kiwilandTrains;

import java.util.ArrayList;
import java.util.Hashtable;

public class Routes {
	
	/**
	 * In this case, i prefer to use a Hashtable though is a bit slower than a HashMap
	 * because is synchronized and cannot accept null values (i can't have a City without a Station)
	 */	
	public Hashtable<City, Station> routeTimeTable;
	public static String ERROR_MSG = "Sorry, the route doesn't exists! :(";

	public Routes() {
		this.routeTimeTable = new Hashtable<City, Station>();
	}

	/**
	 * To get the distance between to cities 
	 * @param cities
	 * @return
	 * @throws Exception
	 */
	public int distanceBetweenCities(ArrayList<City> cities) throws Exception {	
		int distance = 0;
		int stops = 0; 
		
		 for(int i = 0; i < cities.size() - 1 ;  i++){
			//Check if the city is on the route
			 
			 if(this.routeTimeTable.containsKey(cities.get(i))) {
				Station route = this.routeTimeTable.get(cities.get(i));
				
				//we travel counting the distance between cities 
				while(route != null) {
					if(route.getDestination().equals(cities.get(i + 1))) {
						distance += route.getDistance();
						stops++;
						break;
					}
					route = route.getNextStation();
				}
			}else {
				//if the route doesn't have cities where we are traveling there is no route
				throw new Exception(ERROR_MSG);
			}
		 }
		 
		//if we don't have any stops between cities there is no route!
		if(stops != cities.size() - 1) {
			throw new Exception(ERROR_MSG);
		}

		return distance;
	}

	
	/**
	 * Finds the more eficient route to travel
	 * @param cityOrigin
	 * @param cityDestination
	 * @param distance
	 * @param shortestRoute
	 * @return
	 * @throws Exception
	 */
	public int findShortTrip(City cityOrigin, City cityDestination, int distance, int shortestRoute) throws Exception{
		
		checkValidRoute(this.routeTimeTable, cityOrigin, cityDestination );		
		cityOrigin.setVisited(true);		
		Station Station = this.routeTimeTable.get(cityOrigin);
		
		
		while(Station != null) {
			//Is we havn't visited the city we pass by it and count the distance
			if(!Station.getDestination().isVisited()) {
				distance += Station.getDistance();
			}

			//If we found the destiny we check the shortest distance between 
			if(Station.getDestination().equals(cityDestination)) {
				if(shortestRoute == 0 || distance < shortestRoute) {
					shortestRoute = distance;
				}
				cityOrigin.setVisited(false);
				return shortestRoute; 	
				
			}else if(!Station.getDestination().isVisited()) {
				// if we haven't found the destiny city and it hasn't been visited 
				// we search again adding the distance of the trip
				shortestRoute = findShortTrip(Station.getDestination(), cityDestination, distance, shortestRoute);
				distance -= Station.getDistance();
			}
			Station = Station.getNextStation();
		}
		cityOrigin.setVisited(false);
		return shortestRoute;

	}

	/**
	 * Finds the more eficient route to travel
	 * @param cityOrigin
	 * @param cityDestination
	 * @return
	 * @throws Exception
	 */
	public int findShortTrip(City cityOrigin, City cityDestination) throws Exception {		
		return findShortTrip(cityOrigin, cityDestination, 0, 0);
	}

	

	/**
	 * Check if my route is Valid, the timetable contains origin and destination cities
	 * @param routeTimeTable
	 * @param cityOrigin
	 * @param cityDestination
	 * @throws Exception 
	 */
	private void checkValidRoute(Hashtable<City, Station> routeTimeTable, City cityOrigin, City cityDestination) throws Exception {
		
		//The city of origin can't be the same as the the destiny
		if(cityOrigin.equals(cityDestination)) {
			throw new Exception(ERROR_MSG);
		}
		
		if(!(this.routeTimeTable.containsKey(cityOrigin) && this.routeTimeTable.containsKey(cityDestination))) {
			throw new Exception(ERROR_MSG);
		}
		
	}

	/**
	 * Getters and setters	 * 
	 */
	public Hashtable<City, Station> getRouteTable() {
		return routeTimeTable;
	}

	public void setRouteTable(Hashtable<City, Station> routeTable) {
		this.routeTimeTable = routeTable;
	}	
}
