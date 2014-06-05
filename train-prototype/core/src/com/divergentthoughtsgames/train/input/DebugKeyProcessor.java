package com.divergentthoughtsgames.train.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.divergentthoughtsgames.train.App;

public class DebugKeyProcessor extends InputAdapter
{
	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == Keys.F1)
		{
			App.debug = !App.debug;
		}
		
		return false;
	}
}
