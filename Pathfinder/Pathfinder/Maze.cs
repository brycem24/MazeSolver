using System;
using System.Collections.Generic;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;
using System.Linq;
using System.Threading;

namespace Pathfinder
{
	public class Cell {
		//The type of cell it is, 3 = wall.
		public int Content;

		//The position of a cell
		public int X, Y;

		public Cell(int x, int y, int content) {
			this.X = x;
			this.Y = y;
			this.Content = content;
		}
	}

	public class Maze
	{
		//The node hasn't been checked yet
		const int undetermined = 0;

		//The node has been checked
		const int exposed = 1;

		//The node can be passed
		const int empty = 2;

		//The node is blocked
		const int wall = 3;

		//The maze
		public static Cell[,] map;

		//Random is instantiated once for true randomness.
		Random random = new Random();

		public Maze (int width, int height)  {
			map = new Cell[width, height];

			//Generate all the cells, and set them as undetermined.
			for (int i = 0; i < map.GetLength (0); i++)
				for (int j = 0; j < map.GetLength (1); j++)
					map [i, j] = new Cell (i, j, 0);
		}
	
		public void Generate() {

			//Choose a random starting cell.
			var initialCell = map[random.Next(0, map.GetLength(0)), random.Next(0, map.GetLength(1))];

			//The list of cells to be considered
			var workingList = new List<Cell> ();
			workingList.Add (initialCell);

			//Still nodes to consider
			while (workingList.Count > 0) {

				//Choose randomly for Prim's Algorithm, choose newest for recursive backtracker.
				var cell = workingList[workingList.Count - 1];

				//Don't revisit the cell.
				cell.Content = exposed;

				//Get all of the undetermined neighbours of the cell
				var neighbours = GetNeighbours (cell).Where (n => n.Content == undetermined).ToList ();

				//If there are undetermined neighbours
				if (neighbours.Count != 0) {

					//Carve a passage to it.
					cell.Content = empty;

					//Shuffle the neighbours for the random style.
					neighbours = Shuffle (neighbours);

					//Set all neighbours as exposed
					neighbours.ForEach (n => n.Content = exposed);

					//Branch off and repeat for the other neighbours
					workingList.AddRange (neighbours);
				} 

				//There are no unvisited neighbours
				else {

					//Make it a wall
					cell.Content = wall;
				}

				//Remove the cell from consideration
				workingList.Remove (cell);
			}
		}

		List<Cell> GetNeighbours(Cell cell) {
			var neighbours = new List<Cell> ();

			//If the left neighbour isn't null
			if (cell.X > 0)
				neighbours.Add (map [cell.X - 1, cell.Y]);

			//If the bottom neighbour isn't null
			if (cell.Y > 0)
				neighbours.Add (map [cell.X, cell.Y - 1]);

			//If the right neighbour isn't null
			if (cell.X < map.GetLength (0) - 1)
				neighbours.Add (map [cell.X + 1, cell.Y]);

			//If the top neighbour isn't null
			if (cell.Y < map.GetLength (1) - 1)
				neighbours.Add (map [cell.X, cell.Y + 1]);

			return neighbours;
		}

		//This is the truly random element of the maze.
		List<Cell> Shuffle(List<Cell> list) {
			return list = list.OrderBy (c => random.Next ()).ToList ();
		}

		//Draw each tile, if it's a wall colour it in.
		public void Draw(SpriteBatch batch, Texture2D rectangle, int width, int height, Color colour) {
			batch.Begin ();
			for (int i = 0; i < map.GetLength (0); i++)
				for (int j = 0; j < map.GetLength (1); j++) {
					if (map [i, j].Content == 3)
						batch.Draw (rectangle, new Vector2 (i * width, j * height), colour);
					else
						batch.Draw (rectangle, new Vector2 (i * width, j * height), Color.White);
				}
					
			batch.End ();

		}
	}


}

