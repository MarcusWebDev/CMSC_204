package application;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A set of test cases for the Town class
 * @author Marcus Brooks
 *
 */
public class Town_STUDENT_Test {
	Town town;
	  
	@Before
	public void setUp() throws Exception {
		  town = new Town("Test");
	}

	@After
	public void tearDown() throws Exception {
		town = null;
	}

	@Test
	public void testNameMethods() {
		assertEquals("Test", town.getName());
		town.setName("Test?");
		assertEquals("Test?", town.getName());
	}
	@Test
	public void testAdjacentTowns() {
		LinkedList<Town> adjacentTowns = town.getAdjacentTowns();
		adjacentTowns.add(new Town("Beep"));
		adjacentTowns.add(new Town("Boop"));
		adjacentTowns.add(new Town("Bop"));
		assertTrue(adjacentTowns.contains(new Town("Beep")));
		assertTrue(adjacentTowns.contains(new Town("Boop")));
		assertTrue(adjacentTowns.contains(new Town("Bop")));
	}
	@Test
	public void testPreviousTownMethods() {
		town.setPreviousTown(new Town("Snarf"));
		assertEquals("Snarf", town.getPreviousTown().getName());
	}
	@Test
	public void testDistanceMethods() {
		town.setDistance(21);
		assertEquals(21, town.getDistance());
	}
	@Test
	public void testVisitMethods() {
		town.visit();
		assertEquals(true, town.getVisitedStatus());
		town.unvisit();
		assertEquals(false, town.getVisitedStatus());
	}
	@Test
	public void testCompareTo() {
		assertEquals(0, town.compareTo(new Town("Test")));
		assertEquals(-6, town.compareTo(new Town("Zed")));
		assertEquals(19, town.compareTo(new Town("Alpha")));
	}
	@Test
	public void testToString() {
		assertEquals("Test", town.toString());
	}
	@Test
	public void testEquals() {
		assertEquals(false, town.equals(new Road(new Town("beep"), new Town("Boop"), 0, "bop")));
		assertEquals(false, town.equals(new Town("blep")));
		assertEquals(true, town.equals(new Town("Test")));
	}
}