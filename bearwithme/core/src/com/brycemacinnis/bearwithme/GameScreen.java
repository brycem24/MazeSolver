package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.brycemacinnis.bearwithme.entities.Zombie;

public class GameScreen implements Screen {

	SpriteBatch batch;
	SpriteBatch uiBatch;
	
	Player player;
	Map map;
	OrthographicCamera camera;
	QuickBar quickBar;
	
	//Used for switching maps
	BearWithMe bearWithMe;
	Zombie zombie;
	
	public GameScreen(BearWithMe bearWithMe) {
		map = new Map();
		
		//Passed to player for switching maps at death.
		player = new Player(bearWithMe);
		
		batch = new SpriteBatch();
		uiBatch = new SpriteBatch();
		
		//Generate all of the map's tiles.
		map.createWorld();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		//Create a quick bar separate from the camera to show health and other stats.
		quickBar = new QuickBar();
		zombie = new Zombie();

	}
	
	@Override
	public void show() { }

	@Override
	public void render(float delta) {
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
		
		//Draw the zombies
		batch.begin();
		zombie.render(batch);
		batch.end();
		
		//Follow the player
		cameraFollow();
				
		//Render the status bar at the bottom of the screen
		quickBar.render(uiBatch, player);
				
		//DEBUG ONLY
		if (Gdx.input.isKeyJustPressed(Input.Keys.F8))
			player.takeDamage(1);
		else if (Gdx.input.isKeyJustPressed(Input.Keys.F9))
			player.heal(1);
				
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
		
	@Override
	public void resize(int width, int height) {	}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void hide() { }

	@Override
	public void dispose() {
		batch.dispose();
		uiBatch.dispose();
		quickBar.dispose();
		map.dispose();
	}

}
