package application;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A set of test cases for the TownGraphManager class.
 * @author Marcus Brooks
 *
 */
public class TownGraphManager_STUDENT_Test {
	private File inputFile;
	private TownGraphManager graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[5];
		  
		  town[0] = "A";
		  graph.addTown("A");
		  town[1] = "C";
		  graph.addTown("C");
		  town[2] = "B";
		  graph.addTown("B");
		  town[3] = "E";
		  graph.addTown("E");
		  town[4] = "D";
		  graph.addTown("D");
		  
		  
		  graph.addRoad(town[0], town[2], 6, "A-B");
		  graph.addRoad(town[4], town[2], 2, "D-B");
		  graph.addRoad(town[4], town[3], 1, "D-E");
		  graph.addRoad(town[3], town[2], 2, "E-B");
		  graph.addRoad(town[2], town[1], 5, "B-C");
		  graph.addRoad(town[3], town[1], 5, "E-C");
		  graph.addRoad(town[0], town[4], 1, "A-D");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("A-B", roads.get(0));
		assertEquals("A-D", roads.get(1));
		assertEquals("B-C", roads.get(2));
		assertEquals("D-B", roads.get(3));
		assertEquals("D-E", roads.get(4));
		assertEquals("E-B", roads.get(5));
		assertEquals("E-C", roads.get(6));
		graph.addRoad(town[0], town[3], 1,"A-E");
		roads = graph.allRoads();
		assertEquals("A-B", roads.get(0));
		assertEquals("A-D", roads.get(1));
		assertEquals("A-E", roads.get(2));
		assertEquals("B-C", roads.get(3));
		assertEquals("D-B", roads.get(4));
		assertEquals("D-E", roads.get(5));
		assertEquals("E-B", roads.get(6));
		assertEquals("E-C", roads.get(7));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("A-B", graph.getRoad(town[0], town[2]));
		assertEquals("E-C", graph.getRoad(town[3], town[1]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Z"));
		graph.addTown("Z");
		assertEquals(true, graph.containsTown("Z"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Z"));
		graph.addTown("Z");
		ArrayList<String> path = graph.getPath(town[1],"Z");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("E"));
		assertEquals(false, graph.containsTown("Y"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[0], town[2]));
		assertEquals(false, graph.containsRoadConnection(town[0], town[3]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("A-B", roads.get(0));
		assertEquals("A-D", roads.get(1));
		assertEquals("B-C", roads.get(2));
		assertEquals("D-B", roads.get(3));
		assertEquals("D-E", roads.get(4));
		assertEquals("E-B", roads.get(5));
		assertEquals("E-C", roads.get(6));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[0], town[2]));
		graph.deleteRoadConnection(town[0], town[2], "A-B");
		assertEquals(false, graph.containsRoadConnection(town[0], town[2]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("E"));
		graph.deleteTown(town[3]);
		assertEquals(false, graph.containsTown("E"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("A", roads.get(0));
		assertEquals("B", roads.get(1));
		assertEquals("C", roads.get(2));
		assertEquals("D", roads.get(3));
		assertEquals("E", roads.get(4));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[0],town[1]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("A via A-D to D 1 mi",path.get(0).trim());
		  assertEquals("D via D-E to E 1 mi",path.get(1).trim());
		  assertEquals("E via E-C to C 5 mi",path.get(2).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[3],town[0]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("E via D-E to D 1 mi",path.get(0).trim());
		  assertEquals("D via A-D to A 1 mi",path.get(1).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[1],town[4]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("C via E-C to E 5 mi",path.get(0).trim());
		  assertEquals("E via D-E to D 1 mi",path.get(1).trim());

	}
	@Test
	public void testPopulateGraph() throws FileNotFoundException {
		//Using JFileChooser here because I lost points on the last assignment for not letting the person running the test pick the file manually. Even though
		//This test is only meant to work with the Test.txt file
		JFileChooser fileChooser = new JFileChooser();
		
		graph = new TownGraphManager();
		System.out.println(fileChooser.getCurrentDirectory().toString() +"Test.txt");
		fileChooser.setDialogTitle("Select Input File - Text.txt");
		int status = fileChooser.showOpenDialog(null);

		if(status == JFileChooser.APPROVE_OPTION) {
			try {
				inputFile = fileChooser.getSelectedFile();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "There is a problem with this file", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		graph.populateTownGraph(inputFile);
		assertEquals(true, graph.containsTown("Alpha"));
		assertEquals(true, graph.containsTown("Beta"));
		assertEquals(true, graph.containsTown("Charlie"));
		assertEquals(true, graph.containsTown("Delta"));
		assertEquals(true, graph.containsRoadConnection("Alpha", "Beta"));
		assertEquals(true, graph.containsRoadConnection("Beta", "Charlie"));
		assertEquals(true, graph.containsRoadConnection("Charlie", "Delta"));
	}

}