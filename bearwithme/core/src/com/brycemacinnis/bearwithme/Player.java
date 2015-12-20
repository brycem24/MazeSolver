package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	//The texture of the player.
	public Sprite sprite;
	
	//The position the player is at, sprite is drawn here.
	public Vector2 position;
	
	//The speed of the player
	public float speed;

	//The collision box of the player
	public Rectangle boundingBox;

	Player() {
		//Get the player's texture from /ass
		sprite = new Sprite(new Texture(Gdx.files.internal("Prototype.png")), 50, 75);
		
		//Bears are brown, and the color should be brown.
		sprite.setColor(Color.BROWN);
		
		//Set the speed of the player
		speed = 1.5f;
		
		//Center the player
		position = new Vector2(350,300);

		boundingBox = new Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight());
	}
	
	//Update function
	public void render(SpriteBatch batch) {
  		handleMovement();

		batch.begin();
		batch.setColor(Color.BROWN);
		sprite.draw(batch);
		batch.end();

	}

	//Move the player given input, check if colliding
	private void handleMovement() {
		float movement = speed;

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			boundingBox.setY(position.y + movement);

			Vector2 tilePosition = convertScreenToWorld(new Vector2(boundingBox.x, boundingBox.y));
			
			//if (!getTile(tilePosition).isCollider)
				position.y += movement;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			boundingBox.setY(position.y - movement);
			
			Vector2 tilePosition = convertScreenToWorld(new Vector2(boundingBox.x, boundingBox.y));
			
			//if (!getTile(tilePosition).isCollider)
				position.y -= movement;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			boundingBox.setX(position.x - movement);
			
			Vector2 tilePosition = convertScreenToWorld(new Vector2(boundingBox.x, boundingBox.y));
			
		//	if (!getTile(tilePosition).isCollider)				
				position.x -= movement;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			boundingBox.setX(position.x + movement);
			
			Vector2 tilePosition = convertScreenToWorld(new Vector2(boundingBox.x, boundingBox.y));
		//	if (!getTile(tilePosition).isCollider)
				position.x += movement;
		}
			
		else
			boundingBox.setPosition(position);
		////////////////////////////////////////////////////////////////////
		
		//Update the player's position
		sprite.setPosition(position.x, position.y);

		System.out.println(convertScreenToWorld(position));
		System.out.println(getTile(position));
	}
	
	private Vector2 convertScreenToWorld(Vector2 position) {
		float tilePositionX = (float)Math.floor(position.x / Map.tileWidth);
		float tilePositionY = (float)Math.floor(position.y / Map.tileHeight);
		
		return new Vector2(tilePositionX, tilePositionY);
	}
	
	private void getTile(Vector2 worldPos) {
		for (Tile tile : Map.tiles)
		{
			System.out.println(tile.position);
		}

		
		//return null;
	}


}	