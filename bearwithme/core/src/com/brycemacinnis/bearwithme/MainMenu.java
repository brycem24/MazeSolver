package com.brycemacinnis.bearwithme;

import java.util.Arrays;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu implements Screen {
	
	//This is needed to start the game.
	BearWithMe bearWithMe;
	
	SpriteBatch batch;
	
	//The buttons on the main menu, order is kept.
	String[] buttons = { "Play", "Quit" };
	
	//The current selected button, minimum of 1.
	int selectedButton = 1;
	
	//The font, also used for drawing the text.
	BitmapFont font;
	
	public MainMenu(BearWithMe bearWithMe) {
		this.bearWithMe = bearWithMe;
		
		batch = new SpriteBatch();
		
		//Needed to preserve the order in the button array.
		Collections.reverse(Arrays.asList(buttons));
	
		//Generates the font from true type file. Uses third party library.
		font = Helper.generateFont("font.ttf", 64);
	}
	
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		//Draw the title, will be replaced with image (word-art).
		font.draw(batch, "Bear With Me", Gdx.graphics.getWidth() * 0.35f, Gdx.graphics.getHeight() * 0.8f);
		
		//Draw each button from the array. If the button is selected, enclose it in angle brackets.
		//Starting at one so the position doesn't break when multiplying by 0.
		for (int i = 1; i <= buttons.length; i++) {
			if (i == selectedButton)
				font.draw(batch,">" + buttons[i - 1] + "<", Gdx.graphics.getWidth() * 0.45f, i * 100 + 300);
			else
				font.draw(batch,buttons[i - 1], Gdx.graphics.getWidth() * 0.45f, i * 100 + 300);
		}
		
		//Select the button above it, if possible.
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && selectedButton < buttons.length) {
			selectedButton++;
		}
		
		//Select the button below it, if possible.
		else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && selectedButton > 1) {
			selectedButton--;
		}
		
		batch.end();
		
		//Handle the functionality of the buttons
		//the index has to be selectedButton - 1, because the array starts at 1 instead of 0.
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) 
				|| Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			if (buttons[selectedButton - 1] == "Play")
				bearWithMe.setScreen(new GameScreen(bearWithMe));
			else if (buttons[selectedButton - 1] == "Quit")
				Gdx.app.exit();
		}
		
		
	}

	//Necessary interface implementations. Do not remove.
	@Override
	public void show() { }
	
	@Override
	public void resize(int width, int height) { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void hide() { }

	@Override
	public void dispose() {	}
	
}
