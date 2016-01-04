package com.brycemacinnis.bearwithme.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brycemacinnis.bearwithme.Map;
import com.brycemacinnis.bearwithme.Player;

public class Zombie {
	
	Sprite sprite;
	Vector2 position;

	public final int width = 46;
	public final int height = 64;
	
	
	public Zombie() {
	
		//The start position of the zombie.
		position = new Vector2(550, 500);
		
		//Get the sprite
		sprite = new Sprite(new Texture(Gdx.files.internal("Prototype.png")), width, height);
		
	}
	
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
		
		//ALl zombies are green, why would this one be any different?
		sprite.setColor(Color.OLIVE);
		
		//Move the sprite with the player.
		sprite.setPosition(position.x, position.y);
		
	}

	//Prevent memory leaks by disposing values.
	public void dispose() {
		sprite.getTexture().dispose();
	}
	
}
