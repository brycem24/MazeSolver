package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndScreen implements Screen {
	
	//The font, and class for drawing text to screen.
	BitmapFont font;
	
	SpriteBatch batch;
	
	//Used for resetting the screen.
	BearWithMe game;
	
	public EndScreen(BearWithMe game) {
		
		//NO NULL REFERENCE EXCEPTIONS FOR YOU!
		this.game = game;
		
		//Generate the font from true type file.
		font = Helper.generateFont("font.ttf", 64);
		
		batch = new SpriteBatch();
	}

	

	@Override
	public void render(float delta) {
		
		//The black background of the game over screen.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		font.draw(batch, "Game Over!", Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.8f);
		font.draw(batch, "Press Space to Restart", Gdx.graphics.getWidth() * 0.20f, Gdx.graphics.getHeight() * 0.5f);
		batch.end();
		
		//Reset the game
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			game.setScreen(new GameScreen(game));
		}
		
		
	}

	@Override
	public void show() { }
	
	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}
	
}
