package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	
	//The actual map being drawn, filled by replacing the int[][] map.
	public static Tile[][] tiles;
	
	//Width and height of the tile used for spacing and drawing
	public static int tileWidth = 64;
	public static int tileHeight = 64;
	
	//The texture of each tile, optimizes code by not calling new Texture all the time.
	Texture tileTexture;
	
	//The map that will be replaced by tiles
	public static int[][] map = { { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },
								  { 0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0 },
								  { 0,2,1,1,1,1,1,1,1,1,1,1,1,1,2,0 },
								  { 0,2,1,1,1,1,1,1,1,1,1,1,1,1,2,0 },
								  { 0,2,1,1,0,0,0,0,1,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,0,0,0,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,0,0,0,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,0,0,0,0,0,1,1,1,1,1,2,0 },
						   	      { 0,2,1,1,0,0,3,0,0,1,1,1,1,1,2,0 },
						   	      { 0,2,1,1,0,0,0,0,0,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,1,1,1,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,1,1,1,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,1,1,1,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,3,3,3,3,3,1,1,1,2,0 },
						   	  	  { 0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0 },
						   	  	  { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }, };
	
	//The width and height of the map in pixels, for use in clamping the camera
	public final int width = map[0].length * tileWidth;
	public final int height = map[1].length * tileHeight;
   
	//Generate the world, called once at start.
	public void createWorld() {
		
		//Tile Textures should be initialized here, they are used as normal maps now.
		tileTexture = new Texture(Gdx.files.internal("Prototype.png"));
		
		//Create an empty array of tiles the same size as the map
		tiles = new Tile[map[0].length][map[1].length];
		
		//Draw each tile in the Map, can't use a foreach loop on a 2D array.
		for (int i = 0; i < map[0].length; i++)
			for (int j = 0; j < map[1].length; j++)
			{
				//Initalized here so it won't be null
				Tile tile = null;
				
				if (map[i][j] == 0)
					tile = new Tile("Water", tileTexture, Color.SKY, i, j, true);
				else if (map[i][j] == 1)
					tile = new Tile("Grass", tileTexture, Color.WHITE, i, j, false);
				else if (map[i][j] == 2)
					tile = new Tile("Sand", tileTexture, Color.TAN, i, j, false);
				else if (map[i][j] == 3)
					tile = new Tile("Rock", tileTexture, Color.GRAY, i, j, true);

				//The map is effectively replaced with the tiles generated above
				tiles[i][j] = tile;
			}
		
	}
	
	//Render each tile as squares with tileWidth, and tileHeight
	public void renderWorld(SpriteBatch batch) {
		batch.begin();
			for (int i = 0; i < tiles[0].length; i++)
				for (int j = 0; j < tiles[1].length; j++)
					tiles[i][j].render(batch, tileWidth, tileHeight);
		batch.end();
	}

	//Free up the memory of all of the Tile normal maps.
	public void dispose() {
		tileTexture.dispose();
	}
	
}
