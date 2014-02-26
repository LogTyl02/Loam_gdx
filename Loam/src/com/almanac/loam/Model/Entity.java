package com.almanac.loam.Model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected int		x;
	protected int		y;
	protected float   	width;
	protected float		height;
	protected Rectangle bounds;
	
	public Entity(int x, int y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height	= height;
		bounds = new Rectangle(x, y, width, height);
	}
	
	/**
	 * @return the position
	 */
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	/**
	 * @param position the position to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	
	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}
	
	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	
	/**
	 * @return the bounds
	 */
	public Rectangle getBounds() {
		return bounds;
	}
	
	/**
	 * @param bounds the bounds to set
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	
	
	
	
}
