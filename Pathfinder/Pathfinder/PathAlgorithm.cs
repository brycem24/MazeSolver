using System;
using System.Collections.Generic;
using Microsoft.Xna.Framework;

namespace Pathfinder
{
	public class Node {
		public enum NodeState { Untested, Open, Closed };

		public int X, Y;

		//G = The running cost to the current node.
		//H = The estimated distance from the current node to the goal (Manhattan Distance)
		//F = The total cost to sort by
		public int G, H, F;

		//Has the node been considered, is it a candidate, or has it not been tested yet?
		public NodeState State;

		//The node that came before this node, for tracing back best path.
		public Node ParentNode;

		//Returns false if the node is a wall
		public bool IsWalkable() {
				if (Maze.map [X, Y].Content == 3)
					return false;
				else
					return true;
		}

		public Node(int X, int Y) {
			this.X = X;
			this.Y = Y;
		}
	}
		
	public class PathAlgorithm
	{
		//All of the nodes in the map
		Node[,] nodes;

		//Start Position
		int startX, startY;

		//Goal Position
		int goalX, goalY;

		//Populate the map with nodes
		public PathAlgorithm(int width, int height) {
			nodes = new Node[width, height];

			//Instantiate a new node for every tile in the map
			for (int i = 0; i < nodes.GetLength (0); i++)
				for (int j = 0; j < nodes.GetLength (1); j++) {
					nodes [i, j] = new Node (i, j);
				
					//Gets the path cost of the starting node to avoid null reference exceptions
					if (nodes [i, j].ParentNode == null)
						nodes [i, j].G = 0;
					//The path cost of the node is the parent's cost + the cost to get to this tile. (Running total)
					else
						nodes [i, j].G = nodes [i, j].ParentNode.G + 1;

					//The remaining cost is estimated with the Manhattan Distance heuristic. (Faster than Euclidean Distance)
					nodes [i, j].H = Math.Abs (goalX - nodes [i, j].X) + Math.Abs (goalY - nodes [i, j].Y);

					//The total cost is the G cost + the H cost
					nodes [i, j].F = nodes [i, j].G + nodes [i, j].H;
				}

		}

		//The search parameters
		public List<Vector2> GetPath(Vector2 vectorStart, Vector2 vectorGoal) {
			startX = (int)vectorStart.X;
			startY = (int)vectorStart.Y;
			goalX = (int)vectorGoal.X;
			goalY = (int)vectorGoal.Y;

			//Find the shortest path
			return FindPath ();
		}

		//This method should never be called on it's own.
		//It is recursively called by FindPath()
		public bool Search(Node currentNode) {

			//Mark the current node as considered, to never check again.
			currentNode.State = Node.NodeState.Closed;
		
			//Get all of the neighbouring nodes that aren't walls and are in bounds.
			List<Node> nextNodes = GetAdjacentWalkableNodes(currentNode);

			//Sort the list by the cheapest nodes.
			nextNodes.Sort((node1, node2) => node1.F.CompareTo(node2.F));

			foreach (var nextNode in nextNodes) {
				//Has the path been reached?
				if (nextNode.X == goalX && nextNode.Y == goalY) {
					return true;
				}
					
				else {
					//Have we reached a dead end?
					if (Search (nextNode))
						return true;

					//If this isn't true, we reached a dead end.
				}
			}

			//The starting node is a wall itself.
			return false;
		}

		public List<Node> GetAdjacentWalkableNodes(Node fromNode) {

			//All of the nodes that are in the bounds
			List<Node> validNodes = new List<Node> ();

			//All of the nodes that are not walls
			List<Node> walkableNodes = new List<Node> ();

			//The nodes that aren't closed, and are untested or have a shorter path.
			List<Node> consideredNodes = new List<Node> ();

			//If the nodes are in the map boundaries.
			if (fromNode.X + 1 < Game1.mapWidth)
				validNodes.Add(nodes[fromNode.X + 1, fromNode.Y]);
			if (fromNode.X - 1 > -1)
				validNodes.Add (nodes [fromNode.X - 1, fromNode.Y]);
			if (fromNode.Y + 1 < Game1.mapHeight)
				validNodes.Add (nodes [fromNode.X, fromNode.Y + 1]);
			if (fromNode.Y - 1 > -1)
				validNodes.Add (nodes [fromNode.X, fromNode.Y - 1]);

			//If the nodes are walkable add them to the walkable nodes
			foreach (var node in validNodes) {
				if (node.IsWalkable ())
					walkableNodes.Add (node);
			}

			//ToArray() is necessary to prevent a collections modified enumeration error
			foreach (var node in walkableNodes.ToArray()) {

				//If the node is closed already, don't consider it.
				if (node.State == Node.NodeState.Closed)
					continue;

				else if (node.State == Node.NodeState.Open) {
					//float traversalCost = Node.GetTraversalCost (node.X, node.Y, node.ParentNode.X, node.ParentNode.Y);
					float traversalCost = node.G + node.ParentNode.G;
					float gTemp = fromNode.G + traversalCost;

					//If you have found a better path to an already considered node, update it and consider it.
					if (gTemp < node.G) {
						node.ParentNode = fromNode;
						consideredNodes.Add (node);
					}

					//If it hasn't been tested already consider it.
				} else {
					node.ParentNode = fromNode;
					node.State = Node.NodeState.Open;
					consideredNodes.Add (node);
				}
			}

			return consideredNodes;
		}

		//Find the shortest path possible through the maze
		public List<Vector2> FindPath() {

			//Return a list of points to draw from, or follow given AI.
			List<Vector2> path = new List<Vector2> ();

			//Recursively searches until the goal is reached
			bool success = Search (nodes [startX, startY]);

			//The goal is reached
			if (success) {

				//Trace back the path
				Node node = nodes [goalX, goalY];
				while (node.ParentNode != null) {
					Vector2 nodePos = new Vector2 (node.X, node.Y);
					path.Add (nodePos);
					node = node.ParentNode;
				}

				//Reverse the path
				path.Reverse ();

				//Returns the shortest possible path
				return path;
			}

			//No possible route, returns empty path.
			return path;
		}
	}
}

