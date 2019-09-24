package com.kiwiland.kiwilandTrains;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class KiwilandTranisTest {
	static Routes routes;
	static City a, b, c, d, e;
 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		routes = new Routes(); 
		a = new City("A");
		b = new City("B");
		c = new City("C");
		d = new City("D");
		e = new City("E");
 
		//Set the routes indicates on the problem
		routes.routeTimeTable.put(a, new Station(a, b, 5).nextStation(new Station(a, d, 5).nextStation(new Station(a, e, 7))));
		routes.routeTimeTable.put(b, new Station(b, c, 4));
		routes.routeTimeTable.put(c, new Station(c, d, 8).nextStation(new Station(c, e, 2)));
		routes.routeTimeTable.put(d, new Station(d, c, 8).nextStation(new Station(d, e, 6)));
		routes.routeTimeTable.put(e, new Station(e, b, 3));
	}
 
	@Test
	public void testOne_ABC() throws Exception {
		ArrayList<City> trip = new ArrayList<City>(); 
		trip.add(a);
		trip.add(b);
		trip.add(c);
		
		int shortestRoute = routes.findShortTrip(a, c);
		assertEquals(9, shortestRoute);
		
		assertEquals(9, routes.distanceBetweenCities(trip));
	}
 
	@Test
	public void testTwo_AD() throws Exception {
		ArrayList<City> trip = new ArrayList<City>(); 
		trip.add(a);
		trip.add(d);
		
		int shortestRoute = routes.findShortTrip(a, c);
		assertEquals(9, shortestRoute);
		
		assertEquals(5, routes.distanceBetweenCities(trip));		
	}
 
	@Test
	public void testThree_ADC() throws Exception  {
		ArrayList<City> trip = new ArrayList<City>(); 
		trip.add(a);
		trip.add(d);
		trip.add(c);
		
		int shortestRoute = routes.findShortTrip(a, c);
		assertEquals(9, shortestRoute);
		
		assertEquals(13, routes.distanceBetweenCities(trip));
	}
 
	@Test
	public void testFour_AEBCD() throws Exception  {
		ArrayList<City> trip = new ArrayList<City>(); 
		trip.add(a);
		trip.add(e);
		trip.add(b);
		trip.add(c);
		trip.add(d);
				
		int shortestRoute = routes.findShortTrip(a, d);
		assertEquals(5, shortestRoute);
		
		assertEquals(22, routes.distanceBetweenCities(trip));
	}

	@Test(expected=Exception.class)
	public void testFive_AED() throws Exception  {
		ArrayList<City> trip = new ArrayList<City>(); 
		trip.add(a);
		trip.add(e);
		trip.add(d);
		assertEquals(-1, routes.distanceBetweenCities(trip));
		
		int shortestRoute = routes.findShortTrip(a, d);
		assertEquals(-1, shortestRoute);
	}
}
