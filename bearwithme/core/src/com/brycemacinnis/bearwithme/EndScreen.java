package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndScreen implements Screen {
	BitmapFont font;
	SpriteBatch batch;
	
	@Override
	public void show() {
		font = new BitmapFont();
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		font.draw(batch, "Game Over!", Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.5f);
		font.draw(batch, "Press Space to Restart", Gdx.graphics.getWidth() * 0.455f, Gdx.graphics.getHeight() * 0.4f);
		batch.end();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
		
		}
		
		
	}

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
