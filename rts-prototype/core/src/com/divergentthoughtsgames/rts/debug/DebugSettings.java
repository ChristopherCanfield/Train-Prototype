package com.divergentthoughtsgames.rts.debug;

import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.world.controller.KeyboardEntityMoveController;


public class DebugSettings
{
	// Whether debugging is enabled.
	private boolean debugEnabled;

	// Whether bounding boxes are always visible.
	private boolean boundingBoxesAlwaysVisible;

	// Whether the navigation graph is visible.
	private boolean navGraphVisible;

	// Whether unit paths are visible.
	private boolean pathVisible;

	// Whether to possess a unit when clicked.
	private boolean possessUnit;

	// Whether to center the camera on the possessed unit.
	private boolean centerCameraOnPossessedUnit;

	/**
	 * Whether debugging is currently enabled. Debugging adds additional logging and visualizations.
	 * @return true if debugging is enabled.
	 */
	public boolean isEnabled()
	{
		return debugEnabled;
	}

	/**
	 * Sets whether debugging is enabled.
	 * @param val true to enable debugging, or false otherwise.
	 */
	public void setEnabled(boolean val)
	{
		debugEnabled = val;
	}

	/**
	 * Sets whether debugging is enabled. If debugging was previously enabled, it will now be disabled,
	 * while if it was previously disabled, it will now be enabled.
	 */
	public void setEnabled()
	{
		debugEnabled = !debugEnabled;
	}

	public boolean areBoundingBoxesAlwaysVisible()
	{
		return boundingBoxesAlwaysVisible;
	}

	public void setBoundingBoxesAlwaysVisible()
	{
		boundingBoxesAlwaysVisible = !boundingBoxesAlwaysVisible;
	}

	/**
	 * Returns true if the navigation graph is visible, or false otherwise.
	 * @return true if the navigation graph is visible, or false otherwise.
	 */
	public boolean isNavGraphVisible()
	{
		return navGraphVisible;
	}

	/**
	 * Sets the navigation graph to visible if it was not previously visible, or not visible if it
	 * was previously visible.
	 */
	public void setNavGraphVisible()
	{
		navGraphVisible = !navGraphVisible;
	}

	/**
	 * Specifies whether unit paths are visible.
	 * @return true if unit paths are visible.
	 */
	public boolean isPathVisible()
	{
		return pathVisible;
	}

	/**
	 * Sets whether unit paths are visible. If the paths were previously visible, they will now be
	 * hidden, and vice-versa.
	 */
	public void setPathVisible()
	{
		pathVisible = !pathVisible;
	}

	public boolean possessUnitOnClick()
	{
		return possessUnit;
	}

	public void possessUnitOnClick(boolean value)
	{
		possessUnit = value;
		if (!possessUnit && App.possessedEntity != null)
		{
			App.possessedEntity.removeController(KeyboardEntityMoveController.class);
			App.possessedEntity = null;
		}
	}

	public boolean centerCameraOnPossessedUnit()
	{
		return centerCameraOnPossessedUnit;
	}

	public void centerCameraOnPossessedUnit(boolean value)
	{
		centerCameraOnPossessedUnit = value;
	}
}
