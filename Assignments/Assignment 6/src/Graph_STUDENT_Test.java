package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A set of test cases for the Graph class
 * @author Marcus Brooks
 *
 */
public class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		  town = new Town[15];
		  
		  town[0] = new Town("Acacia City");
		  graph.addVertex(town[0]); // 1
		  town[1] = new Town("Driftwood Falls");
		  graph.addVertex(town[1]); // 2
		  town[2] = new Town("Oaksburg");
		  graph.addVertex(town[2]); // 3
		  town[3] = new Town("Willow-Wood");
		  graph.addVertex(town[3]); // 4
		  town[4] = new Town("Kingston");
		  graph.addVertex(town[4]); // 5
		  town[5] = new Town("Ghost Town");
		  graph.addVertex(town[5]); // 6
		  town[6] = new Town("Reaperville");
		  graph.addVertex(town[6]); // 7
		  town[7] = new Town("Ghoulston");
		  graph.addVertex(town[7]); // 8
		  town[8] = new Town("Squierestown");
		  graph.addVertex(town[8]); // 9
		  town[9] = new Town("Queensville");
		  graph.addVertex(town[9]); // 10
		  town[10] = new Town("Dukerton");
		  graph.addVertex(town[10]); // 11
		  town[11] = new Town("Nouveau River");
		  graph.addVertex(town[11]); // 12
		  town[12] = new Town("Crimson");
		  graph.addVertex(town[12]); // 13
		  town[13] = new Town("Rodeo City");
		  graph.addVertex(town[13]); // 14
		  town[14] = new Town("Bullstown");
		  graph.addVertex(town[14]); // 15
		  
		  graph.addEdge(town[0], town[1], 5, "Vine Road"); // 1
		  graph.addEdge(town[1], town[3], 2, "Juice Street"); // 2
		  graph.addEdge(town[1], town[2], 6, "Moore Drive"); // 3
		  graph.addEdge(town[0], town[2], 2, "Gain Road"); // 4
		  graph.addEdge(town[3], town[2], 8, "Hydra Drive"); // 5
		  graph.addEdge(town[3], town[5], 40, "Longman Highway"); // 6
		  graph.addEdge(town[3], town[4], 30, "Waterlog Parkway"); // 7
		  graph.addEdge(town[5], town[4], 5, "Perfume Street"); // 8
		  graph.addEdge(town[5], town[6], 7, "Dragon's Lair Road"); // 9
		  graph.addEdge(town[6], town[7], 3, "Bark Road"); // 10
		  graph.addEdge(town[7], town[5], 5, "Dusty Street"); // 11
		  graph.addEdge(town[8], town[7], 6, "Ethereal Drive"); // 12
		  graph.addEdge(town[8], town[5], 2, "Eraser Road"); // 13
		  graph.addEdge(town[8], town[4], 7, "Candle Light Street"); // 14
		  graph.addEdge(town[4], town[9], 3, "Serf Drive"); // 15
		  graph.addEdge(town[10], town[9], 2, "Pleasant Road"); // 16
		  graph.addEdge(town[4], town[10], 6, "Peasant Road"); // 17
		  graph.addEdge(town[9], town[2], 17, "Noble Parkway"); // 18
		  graph.addEdge(town[2], town[13], 20, "Daytona Highway"); // 19
		  graph.addEdge(town[9], town[13], 15, "Rising Highway"); // 20
		  graph.addEdge(town[11], town[9], 4, "Sharp Lane"); // 21
		  graph.addEdge(town[13], town[11], 2, "Quartley Street"); // 22
		  graph.addEdge(town[12], town[11], 5, "Operator Drive"); // 23
		  graph.addEdge(town[12], town[10], 19, "Sniper's Lane"); // 24
		  graph.addEdge(town[11], town[10], 6, "Chase Road"); // 25
		  graph.addEdge(town[12], town[13], 10, "Marksman Road"); // 26
		  graph.addEdge(town[12], town[14], 10, "Muddywood Lane"); // 27
		  graph.addEdge(town[13], town[14], 8, "Running Road"); // 28
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(town[13], town[2],20, "Daytona Highway"), graph.getEdge(town[13], town[2]));
		assertEquals(new Road(town[12], town[13],10, "Marksman Road"), graph.getEdge(town[13], town[12]));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[0], town[14]));
		graph.addEdge(town[0], town[14], 99, "Bepp Ave");
		assertEquals(true, graph.containsEdge(town[0], town[14]));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Silverton");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[5], town[3]));
		assertEquals(false, graph.containsEdge(town[10], town[8]));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Reaperville")));
		assertEquals(false, graph.containsVertex(new Town("Some place")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		
		assertEquals("Bark Road", roadArrayList.get(0));
		assertEquals("Candle Light Street", roadArrayList.get(1));
		assertEquals("Chase Road", roadArrayList.get(2));
		assertEquals("Daytona Highway", roadArrayList.get(3));
		assertEquals("Dragon's Lair Road", roadArrayList.get(4));
		assertEquals("Dusty Street", roadArrayList.get(5));
		assertEquals("Eraser Road", roadArrayList.get(6));
		assertEquals("Ethereal Drive", roadArrayList.get(7));
		assertEquals("Gain Road", roadArrayList.get(8));
		assertEquals("Hydra Drive", roadArrayList.get(9));
		assertEquals("Juice Street", roadArrayList.get(10));
		assertEquals("Longman Highway", roadArrayList.get(11));
		assertEquals("Marksman Road", roadArrayList.get(12));
		assertEquals("Moore Drive", roadArrayList.get(13));
		assertEquals("Muddywood Lane", roadArrayList.get(14));
		assertEquals("Noble Parkway", roadArrayList.get(15));
		assertEquals("Operator Drive", roadArrayList.get(16));
		assertEquals("Peasant Road", roadArrayList.get(17));
		assertEquals("Perfume Street", roadArrayList.get(18));
		assertEquals("Pleasant Road", roadArrayList.get(19));
		assertEquals("Quartley Street", roadArrayList.get(20));
		assertEquals("Rising Highway", roadArrayList.get(21));
		assertEquals("Running Road", roadArrayList.get(22));
		assertEquals("Serf Drive", roadArrayList.get(23));
		assertEquals("Sharp Lane", roadArrayList.get(24));
		assertEquals("Sniper's Lane", roadArrayList.get(25));
		assertEquals("Vine Road", roadArrayList.get(26));
		assertEquals("Waterlog Parkway", roadArrayList.get(27));
		
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(town[3]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		assertEquals("Hydra Drive", roadArrayList.get(0));
		assertEquals("Juice Street", roadArrayList.get(1));
		assertEquals("Longman Highway", roadArrayList.get(2));
		assertEquals("Waterlog Parkway", roadArrayList.get(3));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[0], town[1]));
		graph.removeEdge(town[0], town[1], 5, "Vine Road");
		assertEquals(false, graph.containsEdge(town[0], town[1]));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[4]));
		graph.removeVertex(town[4]);
		assertEquals(false, graph.containsVertex(town[4]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> townSet = graph.vertexSet();
		ArrayList<String> towns = new ArrayList<String>();
		for (Town road : townSet) {
			towns.add(road.getName());
		}
		assertEquals("Acacia City", towns.get(0));
		assertEquals("Bullstown", towns.get(1));
		assertEquals("Crimson", towns.get(2));
		assertEquals("Driftwood Falls", towns.get(3));
		assertEquals("Dukerton", towns.get(4));
		assertEquals("Ghost Town", towns.get(5));
		assertEquals("Ghoulston", towns.get(6));
		assertEquals("Kingston", towns.get(7));
		assertEquals("Nouveau River", towns.get(8));
		assertEquals("Oaksburg", towns.get(9));
		assertEquals("Queensville", towns.get(10));
		assertEquals("Reaperville", towns.get(11));
		assertEquals("Rodeo City", towns.get(12));
		assertEquals("Squierestown", towns.get(13));
		assertEquals("Willow-Wood", towns.get(14));
	}

	 @Test
	  public void acaciaCityToGhostTown() {
		  String beginTown = "Acacia City", endTown = "Ghost Town";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {
			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  for (String road : path) 
				  System.out.println(road);
			  assertEquals("Acacia City via Gain Road to Oaksburg 2 mi",path.get(0).trim());
			  assertEquals("Oaksburg via Noble Parkway to Queensville 17 mi",path.get(1).trim());
			  assertEquals("Queensville via Serf Drive to Kingston 3 mi",path.get(2).trim());
			  assertEquals("Kingston via Perfume Street to Ghost Town 5 mi",path.get(3).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void nouveauRiverToKingston() {
		  String beginTown = "Nouveau River", endTown = "Kingston";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Nouveau River via Sharp Lane to Queensville 4 mi",path.get(0).trim());
			  assertEquals("Queensville via Serf Drive to Kingston 3 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  @Test
	  public void dukertonToReaperville() {
		  String beginTown = "Dukerton", endTown = "Reaperville";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Dukerton via Pleasant Road to Queensville 2 mi",path.get(0).trim());
			  assertEquals("Queensville via Serf Drive to Kingston 3 mi",path.get(1).trim());
			  assertEquals("Kingston via Perfume Street to Ghost Town 5 mi",path.get(2).trim());
			  assertEquals("Ghost Town via Dragon's Lair Road to Reaperville 7 mi",path.get(3).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
}