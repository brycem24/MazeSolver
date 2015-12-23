package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile {
	
	//The texture that is drawn (Will eventually be used as a normal map, with the colour as the tint)
	Texture texture;
	
	//Should the player collide with this tile?
	boolean isCollider;

	//The color of the tile, used in conjunction with texture.
	Color color;
	
	//Where the tile is placedd
	Vector2 position;
	
	//Take values, use values.
	public Tile(String name, Texture texture, Color color, int x, int y, boolean isCollider){
		this.texture = texture;
		this.color = color;
		this.isCollider = isCollider;
		position = new Vector2(x,y);
	}
	
	//Uses batch.draw to allow the use of setting batch color.
	public void render(SpriteBatch batch,  int width, int height) {
		batch.setColor(color);
		batch.draw(texture, position.x * width, position.y * height, width, height);
	}
	
}
