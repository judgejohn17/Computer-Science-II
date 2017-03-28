/*
 * Graph.java
 *
 * Using stacks and queues for DFS and BFS.
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


/**
 * Graph class.  Holds representation of a graph as well as functions to 
 * interact with the graph.
 * 
 * @author atd Aaron T Deever
 * @author sps Sean Strout
 * @author lol John Judge (jjj4008)
 */
public class Graph {

	/*
	 * graph is represented using a map (dictionary).
	 */
	private Map<String, Node> graph;


	/**
	 * Constructor.  Loads graph from a given filename.  Assumes that each line
	 * in the input file contains the names of two nodes.  Creates nodes
	 * as necessary as well as undirected edges between the nodes.
	 * Returns the graph in the form of a map having the names of the
	 * nodes as keys, and the nodes themselves as values.
	 * 
	 * @param filename name of the input graph specification file
	 * @throws FileNotFoundException if file not found
	 */
	public Graph(String filename) throws FileNotFoundException { 

		// open the file for scanning
		File file = new File(filename);
		Scanner in = new Scanner(file);

		// create the graph
		graph = new HashMap<String, Node>();

		// loop over and parse each line in the input file
		while (in.hasNextLine()) {
			// read and split the line into an array of strings
			// where each string is separated by a space.
			Node n1, n2;
			String line = in.nextLine();
			String[] fields = line.split(" ");
			if(fields.length>1){
				Node current;
				if (graph.containsKey(fields[0])) {
					n1 = graph.get(fields[0]);
				}
				else {
					n1 = new Node(fields[0]);
					graph.put(fields[0],  n1);
				}
				if(fields[1].equals("None")){
					continue;
				}
				for(int i = 1; i < fields.length; i++){
					String field = fields [i];
					if(graph.containsKey(field)){
						current = graph.get(field);

					}
					else{
						current = new Node(field);
						graph.put(field, current);
					}
					n1.addNeighbor(current);
				}

			}
			// creates new nodes as necessary
			if (graph.containsKey(fields[0])) { 
				n1 = graph.get(fields[0]);
			}
			else { 
				n1 = new Node(fields[0]);
				graph.put(fields[0],  n1);
			}
			if (graph.containsKey(fields[1])) { 
				n2 = graph.get(fields[1]);
			}
			else { 
				n2 = new Node(fields[1]);
				graph.put(fields[1],  n2);
			}

			n1.addNeighbor(n2);
			n2.addNeighbor(n1);
		}
		in.close();
	}

	/**
	 * Method to generate a string associated with the graph.  The string
	 * comprises one line for each node in the graph. Overrides
	 * Object toString method.
	 * 
	 * @return string associated with the graph.
	 */
	public String toString() { 
		String result = "";
		for (String name : graph.keySet()) { 
			result = result + graph.get(name) + "\n";
		}
		return result;
	}

	/**
	 * Method to check if a given String node is in the graph.
	 * @param nodeName: string name of a node
	 * @return boolean true if the graph contains that key; false otherwise
	 */
	public boolean isInGraph(String nodeName) { 
		return graph.containsKey(nodeName);
	}

	/**
	 * For a given start and finish node, we simply want to know whether
	 * a path exists, or not, between them. This is the precursor to 
	 * searchDFS().
	 * @param start the name associated with the node from which to start the search
	 * @param finish the name associated with the destination node	 
	 * @return boolean true if a path exists; false otherwise
	 */
	public boolean canReachDFS(String start, String finish) {
		// assumes input check occurs previously
		Node startNode, finishNode;
		startNode = graph.get(start);
		finishNode = graph.get(finish);

		// prime the stack with the starting node
		Stack<Node> stack = new Stack<Node>();
		stack.push(startNode);

		// create a visited set to prevent cycles
		Set<Node> visited = new HashSet<Node>();
		// add start node to it
		visited.add(startNode);

		// loop until either the finish node is found (path exists), or the 
		// dispenser is empty (no path)
		while (!stack.isEmpty()) { 
			Node current = stack.pop();
			if (current == finishNode) {
				return true;    
			}
			// loop over all neighbors of current
			for (Node nbr : current.getNeighbors()) { 
				// process unvisited neighbors
				if (!visited.contains(nbr)) {
					visited.add(nbr);
					stack.push(nbr);
				}
			}
		}
		return false;
	}

	/**
	 * Method that visits all nodes reachable from the given starting node
	 * in depth-first search fashion using a stack, stopping only if the finishing
	 * node is reached or the search is exhausted.  A predecessors map
	 * keeps track of which nodes have been visited and along what path
	 * they were first reached.
	 * 
	 * @param start the name associated with the node from which to start the search
	 *
	 * @return path the path from start to finish.  Empty if there is no such path.
	 * 
	 * Precondition: the inputs correspond to nodes in the graph. 
	 */
	public List<Node> searchDFS(String start) {

		// assumes input check occurs previously
		Node startNode;
		startNode = graph.get(start);


		// prime the dispenser (stack) with the starting node
		List<Node> dispenser = new LinkedList<Node>();
		dispenser.add(0, startNode);

		// construct the predecessors data structure
		Map<Node, Node> predecessors = new HashMap<Node,Node>();
		// put the starting node in, and just assign itself as predecessor
		predecessors.put(startNode, startNode);

		// loop until either the finish node is found, or the 
		// dispenser is empty (no path)
		while (!dispenser.isEmpty()) { 
			Node current = dispenser.remove(0); 

			// loop over all neighbors of current
			for (Node nbr : current.getNeighbors()) { 
				// process unvisited neighbors
				if(!predecessors.containsKey(nbr)) { 
					predecessors.put(nbr, current);
					dispenser.add(0, nbr);
				}
			}
		}
		predecessors.remove(startNode);

		return new LinkedList<Node>(predecessors.keySet());
	}

	/**
	 * Method that visits all nodes reachable from the given starting node
	 * in breadth-first search fashion using a queue, stopping only if the finishing
	 * node is reached or the search is exhausted.  A predecessors map
	 * keeps track of which nodes have been visited and along what path
	 * they were first reached.
	 * 
	 * @param start the name associated with the node from which to start the search
	 *
	 * @return path the path from start to finish.  Empty if there is no such path.
	 * 
	 * Precondition: the inputs correspond to nodes in the graph. 
	 */
	public List<Node> searchBFS(String start) {

		// assumes input check occurs previously
		Node startNode, current;
		startNode = graph.get(start);
		current = null;

		// prime the dispenser (queue) with the starting node
		List<Node> dispenser = new LinkedList<Node>();
		dispenser.add(startNode);

		// construct the predecessors data structure
		Map<Node, Node> predecessors = new HashMap<Node,Node>();
		// put the starting node in, and just assign itself as predecessor
		predecessors.put(startNode, startNode);

		// loop until either the finish node is found, or the 
		// dispenser is empty (no path)
		while (!dispenser.isEmpty()) { 
			current = dispenser.remove(0);
			// loop over all neighbors of current
			for (Node nbr : current.getNeighbors()) { 
				// process unvisited neighbors
				predecessors.put(nbr, current);
				dispenser.add(nbr);
				}
			}

		return constructPath(predecessors, startNode, current);
	}


	/**
	 * Method to return a path from the starting to finishing node.
	 * 
	 * @param predecessors Map used to reconstruct the path
	 * @param startNode starting node
	 * @param current finishing node
	 * @return a list containing the sequence of nodes comprising the path.
	 * Empty if no path exists.
	 */
	private List<Node> constructPath(Map<Node,Node> predecessors,
			Node startNode, Node current) {

		// use predecessors to work backwards from finish to start, 
		// all the while dumping everything into a linked list
		List<Node> path = new LinkedList<Node>();

		if(predecessors.containsKey(current)) {
			Node currNode = current;
			while (currNode != startNode) { 
				path.add(0, currNode);
				currNode = predecessors.get(currNode);
			}	
			path.add(0, startNode);
		}

		return path;
	}

	/**
	 *Method to compute all prerequisites required before a certain course can be taken.
	 * This includes not only direct prerequisites,but also prerequisites of the prerequisites, etc.
	 *
	 * @param CourseName
	 * @return a list of Nodes corresponding to courses that must be taken before the course of interest.
	 * Does not include the course of interest itself
	 */
	public List<Node> computeAllPrereqs(String CourseName){
		if (isInGraph(CourseName)) {
			return searchDFS(CourseName);
		}
		else{
			System.out.println("Course not in graph");
			return null;
		}

	}
	/**
	 *Method to compute the longest chain of prerequisites in the graph.
	 *
	 * Precondition - graph must be acyclic, or this may not terminate.
	 * @return List of Nodes corresponding to the longest chain of nodes.
	 */
	public List<Node> longestChainOfPrereqs(){

		int length = 0;
		List<Node> answer = new LinkedList<Node>();
		for(String name: getAllCourseNames()){
			List<Node> allPrereqs = searchBFS(name);
			if(allPrereqs.size() > length){
				length = allPrereqs.size();
				answer = allPrereqs;
			}
		}
		return answer;

	}

	/**
	 * Method to return a set of all keys (course names) in the graph.
	 *
	 * @return Set of course names
	 */
	public Set<String> getAllCourseNames(){
		return graph.keySet();
	}
}
