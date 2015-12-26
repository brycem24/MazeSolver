package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class BearWithMe extends Game {
	
	SpriteBatch batch;
	SpriteBatch hudBatch;
	
	Player player;
	Map map;
	OrthographicCamera camera;

	QuickBar quickBar;
	
	@Override
	public void create () {
		//Set the name of the window to a hilarious pun. 
		Gdx.graphics.setTitle("Bear With Me");
		
		//The sprite batch used for drawing to keep performance.
		batch = new SpriteBatch();

		//The UI sprite batch
		hudBatch = new SpriteBatch();
		
		//No null reference exceptions here ;)
		map = new Map();
		player = new Player(this);
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		//Generate all of the map's tiles.
		map.createWorld();

		//Create a quick bar separate from the camera to show health and other stats.
		quickBar = new QuickBar();
	}
	
	@Override
	public void render () {
		//A black background (shouldn't ever be seen)
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		//Set to GL_NEAREST for pixel perfect rendering.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		//Render the world
		map.renderWorld(batch);
		
		//Update the camera
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		
		//Draw the player
		player.render(batch);	
		
		//Follow the player
		cameraFollow();
		
		//Render the status bar at the bottom of the screen
		quickBar.render(hudBatch, player);
		
		//DEBUG ONLY
		if (Gdx.input.isKeyJustPressed(Input.Keys.F8))
			player.takeDamage(1);
		else if (Gdx.input.isKeyJustPressed(Input.Keys.F9))
			player.heal(1);
		
		super.render();
		
	}
	
	//Follow the player, but clamp the camera to the map's bounds. 
	private void cameraFollow() {
		float halfWidthOfCamera = camera.viewportWidth / 2;
		float halfHeightOfCamera = camera.viewportHeight / 2;
		
		camera.position.x = MathUtils.clamp(player.position.x, 
				halfWidthOfCamera, map.width - halfWidthOfCamera);
		
		//Leave room for the status bar
		camera.position.y = MathUtils.clamp(player.position.y, 
				halfHeightOfCamera - QuickBar.height, map.height - halfHeightOfCamera);
	}
	
	//After the program exits, free the memory.
	@Override
	public void dispose () {
		batch.dispose();
		hudBatch.dispose();
		quickBar.dispose();
		map.dispose();
	}
	
	public void reset() {
		//player.position = 
	}
	
}
