/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.input;

import java.util.HashSet;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.nav.Search;
import com.divergentthoughtsgames.rts.util.Coords;
import com.divergentthoughtsgames.rts.util.Find;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.command.MoveCommand;

public class UnitControlInputProcessor extends InputAdapter
{

	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (button == Buttons.LEFT && !App.selected.isEmpty())
		{
			final HashSet<Node> goals = new HashSet<>();

			final Vector3 worldCoords = Coords.screenToWorld(screenX, screenY);
			App.selected.forEach((Entity e) -> {
				Node goalNode = Find.node(worldCoords.x, worldCoords.y);
				Node emptyNode = Search.findUnclaimedNodeBfs(goalNode, goals);
				goals.add(emptyNode);

				if (emptyNode.equals(goalNode))
				{
					e.rotateToFace(worldCoords.x, worldCoords.y);
					e.setCommand(new MoveCommand(e, worldCoords.x, worldCoords.y));
				}
				else
				{
					e.rotateToFace(emptyNode.getX(), emptyNode.getY());
					e.setCommand(new MoveCommand(e, emptyNode.getX(), emptyNode.getY()));
				}

				e.logRotation();
			});
		}

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}
}
