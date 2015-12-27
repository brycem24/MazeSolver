package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Helper {
	
	public static BitmapFont generateFont(String name, int size) {
		
		//Create the font using a true type file, since libGDX seems to need a bitmap font.
		//We used a third party jar, to fight this.
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		//The font size passed in.
		parameter.size = size;
		
		//The characters that the font uses. They should NOT repeat.
		parameter.characters="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()<>?:";
		
		BitmapFont font = generator.generateFont(parameter);
		
		//Make sure there isn't a memory leak.
		generator.dispose();
		
		return font;
	}
	

}
