package com.almanac.loam.Utils;

import com.badlogic.gdx.math.Vector2;

public class IsoHelper {
	
	public static float twoDtoISOX(float x, float y) {
		float tmpX = 0;
		tmpX = x - y;
		
		return tmpX;
	}
	
	public static float twoDtoISOY(float x, float y) {
		float tmpY = 0;
		tmpY = (x + y) / 2;
		
		return tmpY;
	}
}

	
