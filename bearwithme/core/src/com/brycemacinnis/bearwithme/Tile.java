package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tile {
	
	Texture texture;
	boolean isCollider;

	Color color;
	
	Collider collisionBox;
	
	Vector2 position;
	
	public Tile(String name, Texture texture, Color color, int x, int y, boolean isEnabled){
		this.texture = texture;
		this.color = color;
		
		position = new Vector2(x,y);
		
		collisionBox = new Collider(x, y, 64, 64, isEnabled);
	}
	
	public void render(SpriteBatch batch,  int width, int height) {
		batch.setColor(color);
		batch.draw(texture, position.x * width, position.y * height, width, height);
	}
	
}
