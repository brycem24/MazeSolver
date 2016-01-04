package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class BearWithMe extends Game {

	DayNight dayNightSystem;
	
	@Override
	public void create () {
		
		//Set the name of the window to a hilarious pun. 
		Gdx.graphics.setTitle("Bear With Me");
		
		//Start the game in the main menu
		setScreen(new MainMenu(this));
		
		dayNightSystem = new DayNight();
	}
	
	//Method that isn't needed but could be used for something in the future.
	@Override
	public void render () {
		
		//Necessary to see the other screens since the render function is overridden.
		super.render();
		
		dayNightSystem.Tick();
		
	}
	
	
}
