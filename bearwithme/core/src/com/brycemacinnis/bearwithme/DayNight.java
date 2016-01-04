package com.brycemacinnis.bearwithme;

import java.util.Calendar;

import com.badlogic.gdx.graphics.Color;

public class DayNight {
	
	public int computerTime;
	public Period period;
	
	public static Color grass;
	public static Color water;
	public static Color dirt;
	public static Color rock;
	
	//Switched according to the system time
	public enum Period { DAY,NIGHT };

	public void Tick() {
		
		//Gets the system time
		Calendar calendar = Calendar.getInstance();
		calendar.getTime();
		
		//Get it in the hour
		computerTime = calendar.get(Calendar.HOUR_OF_DAY);
		
		//If its between 6AM and 6PM
		if (computerTime > 5 && computerTime <= 18)
			period = Period.DAY;
		else
			period = Period.NIGHT;
		
		//Adjust the colours for the map
		adjustColours();
	}
	
	void adjustColours() { 
		if (period == Period.DAY) {
			grass = Color.valueOf("366339");
			water = Color.valueOf("5f95bd");
			dirt = Color.valueOf("673939");
			rock = Color.valueOf("848484");
		}
		
		else {
			grass = Color.valueOf("454f72");
			water = Color.valueOf("151d38");
			dirt = Color.valueOf("291616");
			rock = Color.valueOf("3c3c3f");
			
		}
	}

}
