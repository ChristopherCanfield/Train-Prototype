/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.command;

import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.nav.Node;
import com.divergentthoughtsgames.rts.nav.Search;
import com.divergentthoughtsgames.rts.nav.StraightLineHeuristic;
import com.divergentthoughtsgames.rts.util.Find;
import com.divergentthoughtsgames.rts.world.Entity;

public class MoveCommand extends AbstractEntityCommand<Entity>
{
	private Node nextNode;
	private final Vector2 finalTarget;
	private Vector2 nextTarget;
	private Queue<Node> path;

	private final Node startNode;
	private final boolean startNodePassable;

	public MoveCommand(Entity entity, float targetX, float targetY)
	{
		super(entity);

		startNode = Find.node(entity);
		startNodePassable = startNode.isPassable();
		startNode.setPassable(true);

		Node targetNode = Find.node(targetX, targetY);
		if (targetNode.isPassable())
		{
			finalTarget = new Vector2(targetX, targetY);
		}
		else
		{
			targetNode = Search.findPassableNodeBfs(targetNode);
			finalTarget = new Vector2(targetNode.getCenterX(), targetNode.getCenterY());
		}
		nextTarget = new Vector2();

//		startNode.setPassable(true);
//		entity.clearBlockedNodes();
		path = Search.aStar(startNode, targetNode, StraightLineHeuristic.get());

		// Get the next node, and face it.
		setNextNode();
	}

	@Override
	public void update()
	{
		if (isFinished())
		{
			return;
		}

//		if (entity.getNode().equals(nextNode))
		if (entity.contains(nextTarget.x, nextTarget.y))
		{
			if (nextTarget == finalTarget)
			{
				setFinished(true);
			}
			else
			{
				setNextNode();
				Gdx.app.debug("Move Commmand", "Found next node");
			}
		}
		else
		{
			rotateToFace(entity, nextTarget, finalTarget);
			entity.move();
		}

		if (App.debug.isEnabled())
		{
			Node entityNode = entity.getNode();
			Gdx.app.debug("Entity Node", entityNode.toString());
			if (nextNode != null) Gdx.app.debug("Entity Next Node", nextNode.toString());
			App.graphics.drawPath(nextNode, path);
		}
	}

	@Override
	protected void onFinished()
	{
		entity.stopMoving();
		entity.setCommand(NullCommand.get());
		startNode.setPassable(startNodePassable);
		Gdx.app.debug("Move Command", "Move Command finished");
	}

	@Override
	protected void onCancelled()
	{
		entity.stopMoving();
		entity.setCommand(NullCommand.get());
		startNode.setPassable(startNodePassable);
		Gdx.app.debug("Move Command", "Move Command cancelled");
	}

	private void setNextNode()
	{
		nextNode = path.poll();
		if (nextNode != null)
		{
			nextTarget.set(nextNode.getCenterX(), nextNode.getCenterY());
			rotateToFace(entity, nextTarget, finalTarget);
		}
		else
		{
			nextTarget = finalTarget;
			entity.rotateToFace(nextTarget.x, nextTarget.y);
		}

		entity.setSpeedMax();
	}

	private static void rotateToFace(Entity entity, Vector2 nextTarget, Vector2 finalTarget)
	{
		entity.rotateToFace(nextTarget.x, nextTarget.y, nextTarget != finalTarget);
	}
}
