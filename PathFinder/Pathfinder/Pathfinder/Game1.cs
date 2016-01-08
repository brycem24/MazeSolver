#region Using Statements
using System;

using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Storage;
using Microsoft.Xna.Framework.Input;

#endregion

namespace Pathfinder
{

	public class Game1 : Game
	{
		GraphicsDeviceManager graphics;
		SpriteBatch spriteBatch;
		Maze maze;

		public Game1 ()
		{
			graphics = new GraphicsDeviceManager (this);
			Content.RootDirectory = "Content";	    

			//Screen size
			graphics.PreferredBackBufferWidth = 800;
			graphics.PreferredBackBufferHeight = 800;

			//The title of the application
			this.Window.Title = "Shortest Path Solver";

			//Creates a maze with given width by height.
			maze = new Maze (40, 40);
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
		}

		protected override void Draw (GameTime gameTime)
		{
			graphics.GraphicsDevice.Clear (Color.White);

			//The width and height of the tiles
			int width = 20;
			int height = 20;

			//Texture for drawing primitive rectangles
			Color[] colorData = new Color[width * height];

			Texture2D rectangle = new Texture2D (graphics.GraphicsDevice, width, height); 

			for (int i = 0; i < width * height; i++)
				colorData [i] = Color.White;
			
			rectangle.SetData<Color> (colorData);

			//Draw the maze
			maze.Draw (spriteBatch, rectangle, width, height, Color.DarkSlateGray);

			base.Draw (gameTime);
		}
	}
}

