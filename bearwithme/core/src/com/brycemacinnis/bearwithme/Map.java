package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Map {
	
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();
	public static ArrayList<Collider> colliders = new ArrayList<Collider>();
	
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
						   	  	  { 0,2,1,1,1,1,1,0,0,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,1,0,0,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,0,0,0,0,0,1,1,1,1,1,2,0 },
						   	      { 0,2,1,1,0,3,1,0,0,1,1,1,1,1,2,0 },
						   	      { 0,2,1,1,0,0,0,0,0,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,1,1,1,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,1,1,1,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,1,1,1,1,1,1,1,1,2,0 },
						   	  	  { 0,2,1,1,1,1,3,3,3,3,3,1,1,1,2,0 },
						   	  	  { 0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0 },
						   	  	  { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }, };
	
	public final int width = map[0].length * tileWidth;
	public final int height = map[1].length * tileHeight;

	public void createWorld() {
		tileTexture = new Texture(Gdx.files.internal("Prototype.png"));
		
		for (int i = 0; i < map[0].length; i++)
			for (int j = 0; j < map[1].length; j++)
			{
				Tile tile;
				
				if (map[i][j] == 0)
					tile = new Tile("Water", tileTexture, Color.BLUE, i, j, true);
				else if (map[i][j] == 1)
					tile = new Tile("Grass", tileTexture, Color.FOREST, i, j, false);
				else if (map[i][j] == 2)
					tile = new Tile("Sand", tileTexture, Color.TAN, i, j, false);
				else if (map[i][j] == 3)
					tile = new Tile("Rock", tileTexture, Color.GRAY, i, j, true);
				
				else {
					System.out.println(i + j);
					tile = null;
				}
				tiles.add(tile);
			}

		generateColliders();
	}
	
	public void generateColliders() {
		for (Tile tile : tiles) {
			colliders.add(tile.collisionBox);
		}
		
	}
	
	public void renderWorld(SpriteBatch batch) {
		batch.begin();
		for (Tile tile : tiles)
			tile.render(batch, tileWidth, tileHeight);
		batch.end();
	}

	//Free up the memory
	public void dispose() {
		tileTexture.dispose();
	}
	
}
