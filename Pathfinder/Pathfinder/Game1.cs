#region Using Statements
using System;

using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Storage;
using Microsoft.Xna.Framework.Input;
using System.Collections.Generic;

#endregion

namespace Pathfinder
{

	public class Game1 : Game
	{
		GraphicsDeviceManager graphics;
		SpriteBatch spriteBatch;
		Maze maze;

		//The width and height of the tiles
		int width = 20;
		int height = 20;

		//The width and height of the map.
		public static int mapWidth = 40;
		public static int mapHeight = 40;

		//The path finding algorithm
		PathAlgorithm algorithm;

		public Game1 ()
		{
			graphics = new GraphicsDeviceManager (this);
			Content.RootDirectory = "Content";	    

			//Screen size
			graphics.PreferredBackBufferWidth = 800;
			graphics.PreferredBackBufferHeight = 800;

			//Show the cursor
			IsMouseVisible = true;

			//The title of the application
			this.Window.Title = "Shortest Path Solver";

			//Creates a maze with given width by height.
			maze = new Maze (mapWidth, mapHeight);

			//Create a new path finder given the maze.
			algorithm = new PathAlgorithm (mapWidth, mapHeight);
		}

		protected override void Initialize ()
		{
			base.Initialize ();

			//Generate the maze
			maze.Generate ();
		}

		protected override void LoadContent ()
		{
			spriteBatch = new SpriteBatch (GraphicsDevice);
		}

		protected override void Update (GameTime gameTime)
		{
			base.Update (gameTime);

			//Left Mouse Button changes starting node, Right Mouse button changes end node.
			HandleMouseInput ();
		}
			
		//The starting position of a node, it is null only before the mouse has clicked both a start and an end point.
		Vector2? startPos;
		Vector2? goalPos;

		protected override void Draw (GameTime gameTime)
		{
			graphics.GraphicsDevice.Clear (Color.White);

			//Texture for drawing primitive rectangles
			Color[] colorData = new Color[width * height];
			Texture2D rectangle = new Texture2D (graphics.GraphicsDevice, width, height); 
			for (int i = 0; i < width * height; i++)
				colorData [i] = Color.White;
			rectangle.SetData<Color> (colorData);

			//Draw the maze
			maze.Draw (spriteBatch, rectangle, width, height, Color.DarkSlateGray);

			spriteBatch.Begin ();

			//If there is both a starting position and a goal position, generate a path
			if (startPos.HasValue && goalPos.HasValue) {
				algorithm = new PathAlgorithm (mapWidth, mapHeight);
				List<Vector2> path = algorithm.GetPath ((Vector2)startPos, (Vector2)goalPos);

				//Draw the best path given the coordinates.
				foreach (Vector2 vector in path) {
					spriteBatch.Draw (rectangle, new Vector2 (vector.X * width, vector.Y * height), Color.Yellow);
				}
			}

			//This is rendered last to ensure the starting node and end node overwrites the best path.

			//If the mouse cursor is in bounds and not over a wall and a starting point has been given
			if (startPos.HasValue)
				spriteBatch.Draw (rectangle, new Vector2 (startPos.Value.X * width, startPos.Value.Y * height), Color.Green);

			//If the mouse cursor is in bounds and not over a wall and an end point has been given
			if (goalPos.HasValue)
				spriteBatch.Draw (rectangle, new Vector2 (goalPos.Value.X * width, goalPos.Value.Y * height), Color.Red);

			spriteBatch.End ();

			base.Draw (gameTime);
		}

		public void HandleMouseInput() {
			var mouseState = Mouse.GetState ();

			//Convert Screen Coordinates to Map Coordinates
			int mousePosX = (int)Math.Floor((float)mouseState.Position.X / width);
			int mousePosY = (int)Math.Floor ((float)mouseState.Position.Y / height);

			//If in bounds
			if (mousePosX >= 0 && mousePosY >= 0 &&
				mousePosX < mapWidth && mousePosY < mapHeight) {

				//Set the start position
				if (mouseState.LeftButton == ButtonState.Pressed)
					startPos = new Vector2(mousePosX, mousePosY);

				//Set the end position
				else if (mouseState.RightButton == ButtonState.Pressed)
					goalPos = new Vector2(mousePosX, mousePosY);
			}
		}
	}
}

