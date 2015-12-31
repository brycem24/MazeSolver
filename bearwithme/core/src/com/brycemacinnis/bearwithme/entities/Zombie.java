package com.brycemacinnis.bearwithme.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brycemacinnis.bearwithme.AstarAlgorithm;
import com.brycemacinnis.bearwithme.Node;

public class Zombie {
	
	Sprite sprite;
	Vector2 position;

	public final int width = 46;
	public final int height = 64;
	
	AstarAlgorithm algorithm;
	
	public Zombie() {
	
		//The start position of the zombie.
		position = new Vector2(700, 500);
		
		//Get the sprite
		sprite = new Sprite(new Texture(Gdx.files.internal("Prototype.png")), width, height);
		
		algorithm = new AstarAlgorithm();
		algorithm.getPath(1,1,6,1);
	}
	
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
		
		//ALl zombies are green, why would this one be any different?
		sprite.setColor(Color.OLIVE);
		
		//Move the sprite with the player.
		sprite.setPosition(position.x, position.y);

		
		//System.out.println(path);
	}

	
	//Prevent memory leaks by disposing values.
	public void dispose() {
		sprite.getTexture().dispose();
	}
}
