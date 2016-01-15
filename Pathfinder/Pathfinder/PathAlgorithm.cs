using System;
using System.Collections.Generic;
using Microsoft.Xna.Framework;


namespace Pathfinder
{
	public class Node {
		public enum NodeState { Untested, Open, Closed };

		public int X, Y;
		public int G, H, F;
		public NodeState State;
		public Node ParentNode;

		public bool IsWalkable() {
			if (X < Game1.mapWidth && X >= 0 && Y < Game1.mapHeight && Y >= 0) {
				if (Maze.map [X, Y].Content == 3)
					return false;
				else
					return true;
			}

			else {
				return false;
			}
		}

		public Node(int X, int Y) {
			this.X = X;
			this.Y = Y;
		}
	}
		
	public class PathAlgorithm
	{
		Node[,] nodes;
		int startX, startY; 
		int goalX, goalY;

		public PathAlgorithm(int width, int height) {
			nodes = new Node[width, height];

			for (int i = 0; i < nodes.GetLength (0); i++)
				for (int j = 0; j < nodes.GetLength (1); j++) {
					nodes [i, j] = new Node (i, j);
				
					if (nodes [i, j].ParentNode == null)
						nodes [i, j].G = 0;
					else
						nodes [i, j].G = nodes [i, j].ParentNode.G + 1;
					
					nodes [i, j].H = Math.Abs (goalX - nodes [i, j].X) + Math.Abs (goalY - nodes [i, j].Y);
					nodes [i, j].F = nodes [i, j].G + nodes [i, j].H;
				}

		}

		public List<Vector2> GetPath(Vector2 vectorStart, Vector2 vectorGoal) {
			startX = (int)vectorStart.X;
			startY = (int)vectorStart.Y;
			goalX = (int)vectorGoal.X;
			goalY = (int)vectorGoal.Y;

			return FindPath ();
		}

		public bool Search(Node currentNode) {
			currentNode.State = Node.NodeState.Closed;
		
			List<Node> nextNodes = GetAdjacentWalkableNodes(currentNode);
			nextNodes.Sort((node1, node2) => node1.F.CompareTo(node2.F));

			foreach (var nextNode in nextNodes) {
				if (nextNode.X == goalX && nextNode.Y == goalY) {
					return true;
				}
				else {
					if (Search (nextNode))
						return true;
				}
			}

			return false;
		}

		public List<Node> GetAdjacentWalkableNodes(Node fromNode) {
			List<Node> walkableNodes = new List<Node> ();
			List<Node> validNodes = new List<Node> ();
			List<Node> consideredNodes = new List<Node> ();

			if (fromNode.X + 1 < Game1.mapWidth) //&& fromNode.X + 1 >= 0)
				validNodes.Add(nodes[fromNode.X + 1, fromNode.Y]);
			if (/*fromNode.X - 1 < Game1.mapWidth &&*/ fromNode.X - 1 > -1)
				validNodes.Add (nodes [fromNode.X - 1, fromNode.Y]);
			if (fromNode.Y + 1 < Game1.mapHeight) //&& fromNode.Y + 1 >= 0)
				validNodes.Add (nodes [fromNode.X, fromNode.Y + 1]);
			if (/*fromNode.Y - 1 < Game1.mapHeight &&*/ fromNode.Y - 1 > -1)
				validNodes.Add (nodes [fromNode.X, fromNode.Y - 1]);

			foreach (var node in validNodes) {
				if (node.IsWalkable ())
					walkableNodes.Add (node);
			}
				
			foreach (var node in walkableNodes.ToArray()) {
				if (node.State == Node.NodeState.Closed)
					continue;
				else if (node.State == Node.NodeState.Open) {
					//float traversalCost = Node.GetTraversalCost (node.X, node.Y, node.ParentNode.X, node.ParentNode.Y);
					float traversalCost = node.G + node.ParentNode.G;
					float gTemp = fromNode.G + traversalCost;

					if (gTemp < node.G) {
						node.ParentNode = fromNode;
						consideredNodes.Add (node);
					}
				} else {
					node.ParentNode = fromNode;
					node.State = Node.NodeState.Open;
					consideredNodes.Add (node);
				}
			}

			return consideredNodes;
		}

		public List<Vector2> FindPath() {
			List<Vector2> path = new List<Vector2> ();

			bool success = Search (nodes [startX, startY]);
			if (success) {

				Node node = nodes [goalX, goalY];

				while (node.ParentNode != null) {
					Vector2 nodePos = new Vector2 (node.X, node.Y);
					path.Add (nodePos);
					node = node.ParentNode;
				}

				path.Reverse ();

				return path;
			}

			return path;
		}
	}
}

