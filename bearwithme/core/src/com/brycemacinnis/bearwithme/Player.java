package com.brycemacinnis.bearwithme;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	//The texture of the player.
	public Sprite sprite;
	
	//The position the player is at, sprite is drawn here.
	public Vector2 position;
	
	//The speed of the player
	public float speed;
	
	Player() {
		//Get the player's texture from /ass
		sprite = new Sprite(new Texture(Gdx.files.internal("Prototype.png")), 46, 72);
		
		//Bears are brown, and the color should be brown.
		sprite.setColor(Color.BROWN);
		
		//Set the speed of the player
		speed = 1f;
		
		//Center the player
		position = new Vector2(sprite.getWidth() * 5 ,sprite.getHeight());
		sprite.setPosition(position.x, position.y);
		
	}
	
	//Update function
	public void render(SpriteBatch batch) {
  		
  		//Draw our friendly little bear
		batch.begin();
		sprite.draw(batch);
		batch.end();

		//Move the player
		handleMovement();

	}
	
	//Move the player, if the player moves to a collision tile,reverse the movement
	private void handleMovement() {
		float movement = 100 * speed * Gdx.graphics.getDeltaTime();
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			position.y += movement;
			
			if (playerCollides())
				position.y -= movement;
		}
		
		else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			position.y -= movement;
			
			if (playerCollides())
				position.y += movement;
		}
		
		else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			position.x -= movement;
			
			if (playerCollides())
				position.x += movement;
		}
		
		else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			position.x += movement;
			
			if (playerCollides())
				position.x -= movement;
		}
		
		
		//Adjust the sprite to the position
		sprite.setPosition(position.x, position.y);
	}

	//Get the tile position of a value
	private int convertFloatToWorld(float position) {
		return (int)Math.floor(position / Map.tileWidth);
	}

	//Get the tile of a position
	private Tile getTile(float x, float y) {
		return Map.tiles[convertFloatToWorld(x)][convertFloatToWorld(y)];
	}
	
	///If any of the vertices are in a collision tile return true.
	private boolean playerCollides() {
		//Bottom left corner collision
		if (getTile(position.x, position.y).isCollider)
			return true;
		//Top left corner collision
		else if (getTile(position.x, position.y + sprite.getHeight()).isCollider)
			return true;
		//Bottom right corner collision
		else if (getTile(position.x + sprite.getWidth(), position.y).isCollider)
			return true;
		//Top right corner collision
		else if (getTile(position.x + sprite.getWidth(), position.y + sprite.getHeight()).isCollider)
			return true;
		else
			return false;
	}

}	