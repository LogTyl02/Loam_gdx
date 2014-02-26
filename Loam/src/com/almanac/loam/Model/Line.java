package com.almanac.loam.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * 		Bresenham's Line Algorithms
 */

public class Line implements Iterable<Point> {
	private List<Point> points;
	
	// Should all the work be done in the constructor?
	// Why not? :3
	
	public Line(int x0, int y0, int x1, int y1) {
		points = new ArrayList<Point>();
		
		// Distance
		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);
		
		// Get the slope with Ternary Operators!!
		int sx = (x0 < x1) ? 1 : -1;
		int sy = (y0 < y1) ? 1 : -1;
		int err = dx - dy;
		
		while(true) {
			points.add(new Point(x0, y0));
			
			// If the two points are the same, there is no line
			if (x0 == x1 && y0 == y1) {
				break;
			}
				
				int e2 = err * 2;
				
				if (e2 > -dx) {
					err -= dy;
					x0 += sx;
				}
				
				if (e2 < dx) {
					err += dx;
					y0 += sy;
				}
			}
		}
		
	
	public List<Point> getPoints() {
		return points;
	}
	
	public Iterator<Point> iterator() {
		return points.iterator();
	}
	
}
