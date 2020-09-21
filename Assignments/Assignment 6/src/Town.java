package application;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Town class to be used as a vertex in a graph. Has methods for obtaining adjacent towns and
 * @author Marcus Brooks
 *
 */
public class Town implements Comparable<Town> {
	private String name;
	private LinkedList<Town> adjacentTowns;
	private boolean visited;
	private Town previousTown;
	private int distance;
	/**
	 * Creates a town object with the passed name
	 * @param name Name of the town
	 */
	public Town(String name) {
		this.name = name;
		this.adjacentTowns = new LinkedList<Town>();
		this.visited = false;
		this.previousTown = null;
		this.distance = Integer.MAX_VALUE;
	}
	/**
	 * Performs a shallow copy. A deep copy would result in a chain reaction that creates a deep copy of all of the graph that this vertex can make a path to
	 * @param otherTown The town being copied
	 */
	public Town(Town otherTown) { //currently a shallow copy
		this.name = otherTown.getName();
		this.adjacentTowns = otherTown.getAdjacentTowns();
		this.visited = otherTown.getVisitedStatus();
		this.previousTown = otherTown.getPreviousTown();
		this.distance = otherTown.getDistance();
	}
	/**
	 * Returns the name of the town
	 * @return The name of the town
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Sets the name of the town
	 * @param name The name of the town
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns a linked list of the towns adjacent to this town
	 * @return A linked list of the towns adjacent to this town
	 */
	public LinkedList<Town> getAdjacentTowns() {
		return this.adjacentTowns;
	}
	/**
	 * Returns the previous town in the path
	 * @return The previous town in the path
	 */
	public Town getPreviousTown() {
		return previousTown;
	}
	/**
	 * Sets the previous town in the path
	 * @param previousTown The previous town in the path
	 */
	public void setPreviousTown(Town previousTown) {
		this.previousTown = previousTown;
	}
	/**
	 * Returns the distance from the source vertex in the graph
	 * @return The distance from the source vertex in the graph
	 */
	public int getDistance() {
		return this.distance;
	}
	/**
	 * Sets the distance from the source vertex in the graph
	 * @param distance The distance from the source vertex in the graph
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	/**
	 * Returns true if this town has been visited, false if not
	 * @return True if this town has been visited, false if not
	 */
	public boolean getVisitedStatus() {
		return this.visited;
	}
	/**
	 * Sets this town as visited
	 */
	public void visit() {
		this.visited = true;
	}
	/**
	 * Sets this town as unvisited
	 */
	public void unvisit() {
		this.visited = false;
	}
	/**
	 * Overridden equals method that compares based off of name
	 * @param otherObject The object being compared to
	 */
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		}
		if(!(otherObject instanceof Town)) {
			return false;
		}
		Town otherTown = (Town) otherObject;
		return this.name.toLowerCase().equals(otherTown.getName().toLowerCase());
	}
	/**
	 * Compares this town to another town based off of name. 0 = equals, positive integer = this town's name is greater than the other town, negative integer = this town's name is lesser thant he other town
	 * @param otherTown The town to compare to
	 */
	public int compareTo(Town otherTown) {
		return this.name.toLowerCase().compareTo(otherTown.getName().toLowerCase());
	}
	/**
	 * Overridden toString() method returns the name of the town
	 */
	public String toString() {
		return this.getName();
	}
}