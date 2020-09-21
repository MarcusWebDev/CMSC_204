package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * A town graph manager class which contains methods for creating a town from data obtained from a file, adding and removing towns and roads to a graph, returning all of the towns and roads in the graph,
 * obtaining the shortest path between two towns, and determining if a town or road exists in the graph.
 * @author Marcus Brooks
 *
 */
public class TownGraphManager implements TownGraphManagerInterface {
	private Graph graph;
	
	/**
	 * Creates a new TownGraphManager object with a new Graph object
	 */
	public TownGraphManager() {
		graph = new Graph(); 
	}
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town startTown = getTown(town1);
		Town destinationTown = getTown(town2);
		 
		try {
			Road addedRoad = graph.addEdge(startTown, destinationTown, weight, roadName);
			if (addedRoad != null) {
				return true;
			}
		} catch (IllegalArgumentException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return false;
	}
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	public String getRoad(String town1, String town2) {
		Town startTown = getTown(town1);
		Town destinationTown = getTown(town2);
		Road desiredRoad = graph.getEdge(startTown, destinationTown);
		if (desiredRoad != null) {
			return desiredRoad.toString();
		} else if (startTown == null && destinationTown == null) {
			return town1 + " and " + town2 + "don't exist in this graph.";
		} else if (startTown == null) {
			return town1 + " doesn't exist in this graph.";
		} else if (destinationTown == null) {
			return town2 + " doesn't exist in this graph.";
		} else {
			return "There is no road which connects " + startTown.getName() + " and " + destinationTown.getName() + ".";
		}
	}
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v) {
		try {
			return graph.addVertex(new Town(v));
		} catch (NullPointerException e) {
			return false;
		}
	}
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name) {
		Set<Town> townSet = graph.vertexSet();
		for (Town town : townSet) {
			if (town.getName().equals(name)) {
				return town;
			}
		}
		return null;
	}
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v) {
		return graph.containsVertex(getTown(v));
	}
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2) {
		Town startTown = getTown(town1);
		Town destinationTown = getTown(town2);
		if (startTown != null && destinationTown != null) {
			return graph.containsEdge(startTown, destinationTown);
		}
		return false;
	}
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads() { 
		Set<Road> roadSet = graph.edgeSet();
		ArrayList<String> result = new ArrayList<String>();
		for (Road road : roadSet) {
			result.add(road.getName());
		}
		return result;
	}
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town startTown = getTown(town1);
		Town destinationTown = getTown(town2);
		Road roadToBeDeleted = null;
		if (startTown != null && destinationTown != null) {
			roadToBeDeleted = graph.getEdge(startTown, destinationTown);
		}
		if (roadToBeDeleted != null && roadToBeDeleted.getName().equals(road)) {
			graph.removeEdge(startTown, destinationTown, roadToBeDeleted.getDistance(), road);
			return true;
		}
		return false;
	}
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v) {
		Town townToDelete = getTown(v);
		if (townToDelete != null) {
			return graph.removeVertex(townToDelete);
		}
		return false;
	}
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	public ArrayList<String> allTowns() { 
		Set<Town> towns = graph.vertexSet();
		ArrayList<String> result = new ArrayList<String>();
		for (Town town : towns) {
			result.add(town.getName());
		}
		return result;
	}
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	public ArrayList<String> getPath(String town1, String town2) {
		Town startTown = getTown(town1);
		Town destinationTown = getTown(town2);
		if (startTown != null && destinationTown != null) {
			return graph.shortestPath(startTown, destinationTown);
		}
		return null;
	}
	/**
	 * Populates the graph with towns and roads read from a file
	 * @param file The file to read towns and roads from
	 * @throws FileNotFoundException Thrown if the file doesn't exist
	 */
	public void populateTownGraph(File file) throws FileNotFoundException{ 
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine(), ",;");
			if (tokenizer.countTokens() != 4) {
				continue;
			}
			String roadName = tokenizer.nextToken();
			String distance = tokenizer.nextToken();
			String startTown = tokenizer.nextToken();
			String destinationTown = tokenizer.nextToken();
			addTown(startTown);
			addTown(destinationTown);
			addRoad(startTown, destinationTown, Integer.parseInt(distance), roadName);
		}
	}
}