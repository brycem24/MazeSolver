package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QuickBar {
	
	//The texture of the black bar
	public Texture backgroundTexture;
	public Texture heartTexture;
	
	//The width should be equal to the game's screen.
	public static final int width = 800;
	public static final int height = 125;
	
	public QuickBar() {
		backgroundTexture = new Texture(Gdx.files.internal("Prototype.png"));
		heartTexture = new Texture(Gdx.files.internal("Prototype.png"));
	}

	public void render(SpriteBatch batch, Player player) {
		batch.begin();
		
		//Draw the black background
		batch.setColor(Color.BLACK);
		batch.draw(backgroundTexture, 0, 0, width, height);
		
		//Draw each full heart as red, and empty hearts are gray.
		for (int i = 1; i < 11; i++) {
			if (i > player.health)
				batch.setColor(Color.DARK_GRAY);
			else
				batch.setColor(Color.RED);
					
			//Some scaling that should be sorted out with the final texturing.
			batch.draw(heartTexture, i * heartTexture.getWidth() / 2
					,80, heartTexture.getWidth(), heartTexture.getHeight() / 2);
		}

		batch.end();
	}
	
	//Free up the memory when done.
	public void dispose() {
		backgroundTexture.dispose();
		heartTexture.dispose();
	}
}
