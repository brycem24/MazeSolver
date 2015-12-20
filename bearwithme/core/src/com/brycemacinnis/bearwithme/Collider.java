package com.brycemacinnis.bearwithme;

import com.badlogic.gdx.math.Rectangle;

public class Collider 
{
	public Rectangle collisionBox; 
	public boolean isEnabled;
	
	public Collider(int x, int y, int width, int height, boolean isEnabled)
	{
		collisionBox = new Rectangle(x * width, y * height, width, height);
		this.isEnabled = isEnabled;
	}
	
	
}
