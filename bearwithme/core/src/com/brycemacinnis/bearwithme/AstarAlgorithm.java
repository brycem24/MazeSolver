



package com.brycemacinnis.bearwithme;

import java.util.ArrayList;

public class AstarAlgorithm {
	
	//The nodes already considered
	private ArrayList<Node> closedList = new ArrayList<Node>();
	
	//The nodes that have been considered
	private ArrayList<Node> openList = new ArrayList<Node>();
	
	//The source of the dijkstra algorithm
	Node start;
	
	//The destination to check if the target was reached
	Node end;
	
	Node[][] nodes = new Node[Map.map[0].length][Map.map[1].length];
	
	public AstarAlgorithm() {
	
		//Generate the nodes for each tile
		for (int i = 0; i < Map.map[0].length; i++)
			for (int j = 0; j < Map.map[1].length; j++)
				nodes[i][j] = new Node(i,j);
		
	}
	
	public ArrayList<Node> getPath(int startX, int startY, int endX, int endY) {
		
		//The end is defined before the start for the heuristic to take place
		end = nodes[endX][endY];
		
		start = nodes[startX][startY];
		start.gValue = 0;
		start.fValue = start.gValue + heuristic(start, end);
		
		//Add the start to the consideration list
		openList.add(start);
		
		//Empty the considered list
		closedList.clear();
		
		//While there are still tiles to consider
		while (openList.size() > 0) {
			
			//Choose the node with the lowest f cost
			Node currentNode = getLowest(openList);
			
			//If the destination has been reached
			if (currentNode.x == end.x && currentNode.y == end.y) {
				for (Node node : closedList)
					System.out.println("NODE IN CLOSED PATH: (" + node.x + "," + node.y + ")");
				return null;
			}
			
			//Remove the node from consideration
			openList.remove(currentNode);
			closedList.add(currentNode);

			//Check all of the adjacent tiles
			for (Node neighbour : getNeighbours(currentNode)) {
				if (!closedList.contains(neighbour)) {
					
					//If it was never considered, consider it
					if (!openList.contains(neighbour))
						openList.add(neighbour);
					
					//If it was considered, but this is a better path.
					else {
						if (neighbour.gValue < nodes[neighbour.x][neighbour.y].gValue) {
							nodes[neighbour.x][neighbour.y].gValue = neighbour.gValue;
							nodes[neighbour.x][neighbour.y].parent = neighbour.parent;
						}
							
					}
				}
			}
		}
		
		//NO PATH WAS FOUND
		System.out.println("NO PATH WAS FOUND");
		return null;
	}
	
	Node getLowest(ArrayList<Node> nodes) {
		
		//Highest value which will never reach
		int lowestCost = 9999999;
		Node lowestCostNode = null;
		
		for (Node node : nodes) {
			//If the cost is lower
			if (node.fValue < lowestCost) {
				//Make it the new lowest cost.
				lowestCostNode = node;
				lowestCost = node.fValue;
			}
		}
		
		return lowestCostNode;
	}
	
	//The Manhattan Distance to judge the remaining distance
	int heuristic(Node start, Node goal) {
		return Math.abs(goal.x - start.x) + Math.abs(goal.y - start.y);
	}
	
	//Get the path recursively currently broken
	ArrayList<Node> constructPath(Node node) {
		ArrayList<Node> path = new ArrayList<Node>();
		
		while (node.parent != null)
		{
			node = node.parent;
			path.add(node);
		}
		
		return path;
	}
	
	
	ArrayList<Node> getNeighbours(Node node) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		
		//Get the adjacent tiles if they are not null or colliders
		if (node.x + 1 < nodes[0].length)
			if (!nodes[node.x + 1][node.y].isCollider())
				neighbours.add(nodes[node.x + 1][node.y]);
		if (node.x - 1 > -1)
			if (!nodes[node.x - 1][node.y].isCollider())
				neighbours.add(nodes[node.x - 1][node.y]);
		if (node.y + 1 < nodes[1].length)
			if (!nodes[node.x][node.y + 1].isCollider())
				neighbours.add(nodes[node.x][node.y + 1]);
		if (node.y - 1 > -1)
			if (!nodes[node.x][node.y - 1].isCollider())
				neighbours.add(nodes[node.x][node.y - 1]);
		
		
		for (Node neighbour : neighbours) {
			//Increase the cost by one for getting the path cost
			neighbour.gValue = node.gValue + 1;
			//Make the node the parent of the neighbour to allow for backtracing
			neighbour.parent = node;
		}
		
		return neighbours;
	}
	
}
