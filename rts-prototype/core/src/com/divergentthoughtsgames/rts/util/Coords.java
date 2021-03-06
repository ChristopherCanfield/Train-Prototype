/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.util;

import com.badlogic.gdx.math.Vector3;
import com.divergentthoughtsgames.rts.App;

public abstract class Coords
{
	/**
	 * Shortcut for the App.graphics.getCamera().unproject method. The x- and y-coordinates are 
	 * assumed to be in screen coordinates (origin is the top left corner, y pointing down, x 
	 * pointing to the right) as reported by the touch methods in Input.
	 * @param x
	 * @param y
	 * @return Vector3 containing the converted world coordinates.
	 */
	public static Vector3 screenToWorld(int x, int y)
	{
		return App.graphics.getCamera().unproject(new Vector3(x, y, 0));
	}
	
	/**
	 * Shortcut for the App.graphics.getCamera().project method. The viewport is assumed to span 
	 * the whole screen. The screen coordinate system has its origin in the bottom left, with the 
	 * y-axis pointing upwards and the x-axis pointing to the right.
	 * @param x
	 * @param y
	 * @return Vector3 containing the converted world coordinates.
	 */
	public static Vector3 worldToScreen(int x, int y)
	{
		return App.graphics.getCamera().project(new Vector3(x, y, 0));
	}
}
