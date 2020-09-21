package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A set of test cases for the Road class
 * @author Marcus Brooks
 *
 */
public class Road_STUDENT_Test {
	private Town town1;
	private Town town2;
	private Road road;

	@Before
	public void setUp() throws Exception {
		town1 = new Town("Test");
		town2 = new Town("Testerino");
		road = new Road(town1, town2, 4, "Test to Testerino");
	}

	@After
	public void tearDown() throws Exception {
		town1 = null;
		town1 = null;
		road = null;
	}

	@Test
	public void testStartTown() {
		road.setStartTown(new Town("Ahhhh"));
		assertEquals("Ahhhh", road.getStartTown().getName());
	}
	@Test
	public void testEndTown() {
		road.setEndTown(new Town("Help"));
		assertEquals("Help", road.getEndTown().getName());
	}
	@Test
	public void testDistance() {
		road.setDistance(15);
		assertEquals(15, road.getDistance());
	}
	@Test
	public void testName() {
		road.setName("UGH");
		assertEquals("UGH", road.getName());
	}
	@Test
	public void testEquals() {
		assertEquals(false, road.equals(new Town("bsajdhsakda")));
		assertEquals(false, road.equals(new Road(new Town("sdasd"), new Town("dsadsa"), 16, "dsadad")));
		assertEquals(true, road.equals(new Road(new Town("Test"), new Town("Testerino"), 4, "Test to Testerino")));
	}
	@Test
	public void testCompareTo() {
		assertEquals(0, road.compareTo(new Road(new Town("Test"), new Town("Testerino"), 4, "Test to Testerino")));
		assertEquals(-6, road.compareTo(new Road(new Town("zsdadas"), new Town("zdsadadsa"), 4, "Zsadsaedasasdadsadsadwsadssdaws")));
		assertEquals(19, road.compareTo(new Road(new Town("asdsada"), new Town("asdsadasda"), 4, "asdsadada")));
	}
	@Test
	public void testToString() {
		assertEquals("Test to Testerino", road.toString());
	}
}