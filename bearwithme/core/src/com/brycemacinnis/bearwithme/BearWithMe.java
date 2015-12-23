package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class BearWithMe extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;
	Map map;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		
		//The sprite batch used for drawing to keep performance.
		batch = new SpriteBatch();
		
		//Set the name of the window to a hilarious pun. 
		Gdx.graphics.setTitle("Bear With Me");
		
		//No null reference exceptions here ;)
		map = new Map();
		player = new Player();
		camera = new OrthographicCamera(800,800);

		//Generate all of the map's tiles.
		map.createWorld();
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
	}
	
	//Follow the player, but clamp the camera to the map's bounds. 
	private void cameraFollow() {
		float halfWidthOfCamera = camera.viewportWidth / 2;
		float halfHeightOfCamera = camera.viewportHeight / 2;
		
		camera.position.x = MathUtils.clamp(player.position.x, 
				halfWidthOfCamera, map.width - halfWidthOfCamera);
		camera.position.y = MathUtils.clamp(player.position.y, 
				halfHeightOfCamera, map.height - halfHeightOfCamera);
	}
	
	//After the progrdam exits, free the memory.
	@Override
	public void dispose () {
		batch.dispose();
		map.dispose();
	}
	
}
