package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Graph class which creates a graph of towns and roads connecting them. Contains methods for finding the shortest path between one town and another
 * @author Marcus Brooks
 *
 */
public class Graph implements GraphInterface<Town, Road> {
	private ArrayList<Town> townArray;
	private ArrayList<LinkedList<Road>> edgeList;
	private HashMap<Town, Integer> minPathDistances;
	private HashSet<Town> visited;
	private HashSet<Town> unvisited;
	private TreeSet<Town> townSet;
	private TreeSet<Road> roadSet;
	
	public Graph() {
		townArray = new ArrayList<Town>();
		edgeList = new ArrayList<LinkedList<Road>>();
		minPathDistances = new HashMap<Town, Integer>();
		visited = new HashSet<Town>();
		unvisited = new HashSet<Town>();
		townSet = new TreeSet<Town>();
		roadSet = new TreeSet<Road>();
	}
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (townArray.contains(sourceVertex) && townArray.contains(destinationVertex)) {
			int sourceIndexLocation = townArray.indexOf(sourceVertex);
			ListIterator<Road> roadIterator = edgeList.get(sourceIndexLocation).listIterator();
			while (roadIterator.hasNext()) {
				Road currentRoad = roadIterator.next();
				if ((currentRoad.getStartTown().compareTo(sourceVertex) == 0 && currentRoad.getEndTown().compareTo(destinationVertex) == 0) || (currentRoad.getStartTown().compareTo(destinationVertex) == 0 && currentRoad.getEndTown().compareTo(sourceVertex) == 0)) {
					return currentRoad;
				}
			}
		}
		return null;
	}
	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		if (containsVertex(sourceVertex) && containsVertex(destinationVertex) && weight > -1 && description != null) {
			Road edgeToAdd = new Road(sourceVertex, destinationVertex, weight, description);
			int sourceVertexLocation = townArray.indexOf(sourceVertex);
			int destinationVertexLocation = townArray.indexOf(destinationVertex);
			ListIterator<Road> roadIterator = edgeList.get(sourceVertexLocation).listIterator();
			while (roadIterator.hasNext()) {
				Road currentRoad = roadIterator.next();
				if (currentRoad.equals(edgeToAdd)) {
					return null;
				}
			}
			sourceVertex.getAdjacentTowns().add(destinationVertex);
			destinationVertex.getAdjacentTowns().add(sourceVertex);
			edgeList.get(sourceVertexLocation).add(edgeToAdd);
			edgeList.get(destinationVertexLocation).add(edgeToAdd);
			roadSet.add(edgeToAdd);
			return edgeToAdd;
		} else {
			throw new IllegalArgumentException();
		}
	}
	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	public boolean addVertex(Town townToBeAdded) {
		if (townToBeAdded == null) {
			throw new NullPointerException();
		}
		if (containsVertex(townToBeAdded)) {
			return false;
		}
		boolean added = false;
		for (int i = 0; i < townArray.size(); i++) {
			if (townToBeAdded.compareTo(townArray.get(i)) < 0) {
				townArray.add(i, townToBeAdded);
				edgeList.add(i, new LinkedList<Road>());
				townSet.add(townToBeAdded);
				added = true;
				break;
			}
		}
		if (added == false) {
			townArray.add(townToBeAdded);
			edgeList.add(new LinkedList<Road>());
			townSet.add(townToBeAdded);
		}
		
		return true;
	}
	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	public boolean containsVertex(Town targetTown) {
		return townArray.contains(targetTown);
	}
	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if (townArray.contains(sourceVertex) && townArray.contains(destinationVertex)) {
			int sourceIndexLocation = townArray.indexOf(sourceVertex);
			ListIterator<Road> roadIterator = edgeList.get(sourceIndexLocation).listIterator();
			while (roadIterator.hasNext()) {
				Road currentRoad = roadIterator.next();
				if ((currentRoad.getStartTown().compareTo(sourceVertex) == 0 && currentRoad.getEndTown().compareTo(destinationVertex) == 0) || (currentRoad.getStartTown().compareTo(destinationVertex) == 0 && currentRoad.getEndTown().compareTo(sourceVertex) == 0)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	public Set<Road> edgeSet() {
		return this.roadSet;
	}
	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	public Set<Road> edgesOf(Town targetTown) {
		if (targetTown == null) {
			throw new NullPointerException();
		}
		if (containsVertex(targetTown)) {
			TreeSet<Road> result = new TreeSet<Road>();
			int targetTownLocation = townArray.indexOf(targetTown);
			ListIterator<Road> roadIterator = edgeList.get(targetTownLocation).listIterator();
			while (roadIterator.hasNext()) {
				Road currentRoad = roadIterator.next();
				result.add(currentRoad);
			}
			return result;
		} else {
			throw new IllegalArgumentException();
		}
	}
	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (description == null || weight < 0) {
			return null;
		}
		Road toBeRemovedCopy = new Road(sourceVertex, destinationVertex, weight, description);
		if (containsVertex(sourceVertex) && containsVertex(destinationVertex)) {
			int sourceVertexLocation = townArray.indexOf(sourceVertex);
			int destinationVertexLocation = townArray.indexOf(destinationVertex);
			Road result = null;
			ListIterator<Road> sourceRoadIterator = edgeList.get(sourceVertexLocation).listIterator();
			ListIterator<Road> destinationRoadIterator = edgeList.get(destinationVertexLocation).listIterator();
			while (sourceRoadIterator.hasNext() || destinationRoadIterator.hasNext()) {
				if (sourceRoadIterator.hasNext()) {
					Road currentRoad = sourceRoadIterator.next();
					if (currentRoad.equals(toBeRemovedCopy)) {
						sourceRoadIterator.remove();
						roadSet.remove(currentRoad);
						result = currentRoad;
					}
				}
				if (destinationRoadIterator.hasNext()) {
					Road currentRoad = destinationRoadIterator.next();
					if (currentRoad.equals(toBeRemovedCopy)) {
						destinationRoadIterator.remove();
						roadSet.remove(currentRoad);
						result = currentRoad;
					}
				}
			}
			if (result != null) {
				sourceVertex.getAdjacentTowns().remove(destinationVertex);
				destinationVertex.getAdjacentTowns().remove(sourceVertex);
			}
			return result;
		}
		return null;
	}
	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	public boolean removeVertex(Town targetTown) {
		if (targetTown == null) {
			return false;
		}
		if (containsVertex(targetTown)) {
			ListIterator<Town> adjacentTownIterator = targetTown.getAdjacentTowns().listIterator();
			while (adjacentTownIterator.hasNext()) {
				Town currentTown = adjacentTownIterator.next();
				int currentTownLocation = townArray.indexOf(currentTown);
				ListIterator<Road> roadIterator = edgeList.get(currentTownLocation).listIterator();
				while (roadIterator.hasNext()) {
					Road currentRoad = roadIterator.next();
					if (currentRoad.getStartTown().compareTo(targetTown) == 0 || currentRoad.getEndTown().compareTo(targetTown) == 0) {
						roadIterator.remove();
						roadSet.remove(currentRoad);
						currentTown.getAdjacentTowns().remove(targetTown);
					}
				}
			}
			int toBeRemovedLocation = townArray.indexOf(targetTown);
			townArray.remove(toBeRemovedLocation);
			edgeList.remove(toBeRemovedLocation);
			townSet.remove(targetTown);
			return true;
		}
		return false;
	}
	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	public Set<Town> vertexSet() {
		return this.townSet;	
	}
	/**
	 * unvisits each vertex in the graph and sets their previous town field to null
	 */
	public void unvisitAll() {
		for (int i = 0; i < townArray.size(); i++) {
			townArray.get(i).unvisit();
			townArray.get(i).setPreviousTown(null);
			townArray.get(i).setDistance(Integer.MAX_VALUE);
		}
	}
	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex == null || destinationVertex == null) {
			return new ArrayList<String>();
		}
		dijkstraShortestPath(sourceVertex);
		Town currentTown = destinationVertex;
		Town nextTown;
		ArrayList<String> shortestPath = new ArrayList<String>();
		Stack<Town> myStack = new Stack<Town>();
		while (currentTown != sourceVertex) {
			myStack.push(currentTown);
			if (currentTown.getPreviousTown() == null) {
				return shortestPath;
			}
			currentTown = currentTown.getPreviousTown();
		}
		while (!myStack.isEmpty()) {
			nextTown = myStack.pop();
			currentTown = nextTown.getPreviousTown();
			Road connectingRoad = getEdge(currentTown, nextTown);
			shortestPath.add(currentTown.getName() + " via " + connectingRoad.getName() +  " to " + nextTown.getName() + " " + connectingRoad.getDistance() + " mi");
		}
		unvisitAll();
		return shortestPath;
	}
	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	public void dijkstraShortestPath(Town sourceVertex) {
		minPathDistances = new HashMap<Town, Integer>();
		for (int i = 0; i < townArray.size(); i++) {
			minPathDistances.put(townArray.get(i), Integer.MAX_VALUE);
		}
		if (!containsVertex(sourceVertex)) {
			return;
		}
		minPathDistances.put(sourceVertex, 0);
		sourceVertex.setDistance(0);
		unvisited.add(sourceVertex);
		while (!unvisited.isEmpty()) {
			Iterator<Town> unvisitedIterator= unvisited.iterator();
			Town currentTown = unvisitedIterator.next();
			while (unvisitedIterator.hasNext()) {
				Town townBeingCompared = unvisitedIterator.next();
				if (townBeingCompared.getDistance() < currentTown.getDistance()) {
					currentTown = townBeingCompared;
				}
			}
			currentTown.visit();
			visited.add(currentTown);
			unvisited.remove(currentTown);
			int currentTownLocation = townArray.indexOf(currentTown);
			ListIterator<Road> roadIterator = edgeList.get(currentTownLocation).listIterator();
			while (roadIterator.hasNext()) {
				Road currentRoad = roadIterator.next();				
				Town connectedUnvisitedTown = null;
				if (currentRoad.getStartTown().compareTo(currentTown) != 0 && currentRoad.getStartTown().getVisitedStatus() == false) {
					connectedUnvisitedTown = currentRoad.getStartTown();
					unvisited.add(connectedUnvisitedTown);
				}
				if (currentRoad.getEndTown().compareTo(currentTown) != 0 && currentRoad.getEndTown().getVisitedStatus() == false) {
					connectedUnvisitedTown = currentRoad.getEndTown();
					unvisited.add(connectedUnvisitedTown);

				}
				if (connectedUnvisitedTown != null && minPathDistances.get(connectedUnvisitedTown) > currentRoad.getDistance() + minPathDistances.get(currentTown).intValue()) {
					int newDistance = minPathDistances.get(currentTown) + currentRoad.getDistance();
					minPathDistances.put(connectedUnvisitedTown, newDistance);
					connectedUnvisitedTown.setPreviousTown(currentTown);
					connectedUnvisitedTown.setDistance(newDistance);
				}
			}
		}
	}
}