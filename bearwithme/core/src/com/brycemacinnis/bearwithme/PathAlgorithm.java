package com.brycemacinnis.bearwithme;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

//Implements A* Pathfinding
//http://www.policyalmanac.org/games/aStarTutorial.htm is a good resource.

public class PathAlgorithm {
	public void GetPath(int[][] map, Vector2 origin, Vector2 destination) {
		
		//Nodes that we should consider
		List<Node> openList = new ArrayList<Node>();
		
		//The nodes that we have already checked.
		List<Node> closedList = new ArrayList<Node>();
		
		Node[][] nodes = getNodes(map, (int)destination.x, (int)destination.y);
		
		//Add the start position to the consideration
		Node startNode = nodes[(int)origin.x][(int)origin.y];
		startNode.parent = null;
		
		openList.add(startNode);
		
		//Consider the adjacent nodes
		if (startNode.X + 1 < nodes[0].length)
			if (!nodes[startNode.X + 1][startNode.Y].isCollider()) {
				nodes[startNode.X + 1][startNode.Y].parent = startNode;
				openList.add(nodes[startNode.X + 1][startNode.Y]);
			}
		
		if (startNode.X - 1 > -1)
			if (!nodes[startNode.X - 1][startNode.Y].isCollider()) {
				nodes[startNode.X - 1][startNode.Y].parent = startNode;
				openList.add(nodes[startNode.X - 1][startNode.Y]);
			}
		
		
		if (startNode.Y < nodes[1].length)
			if(!nodes[startNode.X][startNode.Y + 1].isCollider()) {
				nodes[startNode.X][startNode.Y + 1].parent = startNode;
				openList.add(nodes[startNode.X][startNode.Y + 1]);
			}
		 
		if (startNode.Y - 1 > -1)
			if (!nodes[startNode.X][startNode.Y - 1].isCollider()) {
				nodes[startNode.X][startNode.Y - 1].parent = startNode;
				openList.add(nodes[startNode.X][startNode.Y - 1]);
			}

		//Add the start position to the already checked list.
		closedList.add(startNode);
		
		openList.remove(startNode);
		
		//Find the lowest cost in the open list
		//F = G + H, Final Cost = Path Cost + Heuristic
		
		//Get the lowest cost node
		int lowestCost = 999999;
		Node lowestCostNode = null;
		
		for (Node node : openList) {
			System.out.println(node.cost);
			
			if (node.cost < lowestCost)
			{
				lowestCostNode = node;
				lowestCost = lowestCostNode.cost;
			}
		}
		
		//Add it to the closed list
		closedList.add(lowestCostNode);
		openList.remove(lowestCostNode);
		
		//Check adjacent tiles
		if (lowestCostNode.X + 1 < nodes[0].length)
			if (!nodes[lowestCostNode.X + 1][lowestCostNode.Y].isCollider()) {
				if (!closedList.contains(nodes[lowestCostNode.X + 1][lowestCostNode.Y])) {
					nodes[lowestCostNode.X + 1][lowestCostNode.Y].parent = lowestCostNode;
					openList.add(nodes[lowestCostNode.X + 1][lowestCostNode.Y]);
				}
			}
		
		if (lowestCostNode.X - 1 > -1)
			if (!nodes[lowestCostNode.X - 1][lowestCostNode.Y].isCollider()) {
				if (!closedList.contains(nodes[lowestCostNode.X - 1][lowestCostNode.Y])) {
					nodes[lowestCostNode.X - 1][lowestCostNode.Y].parent = lowestCostNode;
					openList.add(nodes[lowestCostNode.X - 1][lowestCostNode.Y]);
				}
			}
		
		
		if (lowestCostNode.Y < nodes[1].length)
			if(!nodes[lowestCostNode.X][lowestCostNode.Y + 1].isCollider()) {
				if (!closedList.contains(nodes[lowestCostNode.X][lowestCostNode.Y + 1])) {
					nodes[lowestCostNode.X][lowestCostNode.Y + 1].parent = lowestCostNode;
					openList.add(nodes[lowestCostNode.X][lowestCostNode.Y + 1]);
				}
			}
		 
		if (lowestCostNode.Y - 1 > -1)
			if (!nodes[lowestCostNode.X][lowestCostNode.Y - 1].isCollider()) {
				if (!closedList.contains(nodes[lowestCostNode.X][lowestCostNode.Y - 1])) {
					nodes[lowestCostNode.X][lowestCostNode.Y - 1].parent = lowestCostNode;
					openList.add(nodes[lowestCostNode.X][lowestCostNode.Y - 1]);
				}
				
			}
		
		//Choose the lowest cost node.
		if (closedList.contains(nodes[(int)destination.x][(int)destination.y]))
			System.out.println("TARGET REACHED");
		
		System.out.println("OPEN: " + openList.size());
		System.out.println("CLOSED: " + closedList.size());
		System.out.println("Lowest cost: " + lowestCostNode.cost);
	}
	
	Node[][] getNodes(int[][] map, int destinationX, int destinationY) {
		Node[][] nodeMap = new Node[map[0].length][map[1].length];
		
		for (int i = 0; i < map[0].length; i++)
			for (int j = 0; j < map[1].length; j++) {
				nodeMap[i][j] = new Node(i, j, destinationX, destinationY);
			}
		
		return nodeMap;
	}
	
	
}
