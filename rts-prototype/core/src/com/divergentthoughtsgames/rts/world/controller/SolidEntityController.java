/*
 * Christopher D. Canfield
 * Divergent Thoughts Games
 *           2014
 */
package com.divergentthoughtsgames.rts.world.controller;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.rts.util.EntityIntersection;
import com.divergentthoughtsgames.rts.util.Find;
import com.divergentthoughtsgames.rts.world.Controller;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.World;

/**
 * Prevents solid entities from moving through each other.
 * @author Christopher D. Canfield
 */
public class SolidEntityController implements Controller
{
	private static SolidEntityController instance;
	
	public static SolidEntityController get()
	{
		if (instance == null)
		{
			instance = new SolidEntityController();
		}
		return instance;
	}
	
	private SolidEntityController()
	{
	}
	
	@Override
	public void update(Entity entity, World world)
	{
		// TODO (6/17/2014): this isn't currently working properly...
		return;
		
//		List<EntityIntersection> overlapping = Find.overlappingEntities(entity, world.getEntities());
//		for (final EntityIntersection intersection : overlapping)
//		{
//			// TODO: Perform polygon bounding box tests once the intersection between the AABB's have been confirmed?
//			boolean otherEntIsMoveable = intersection.entity.isMoveable();
//			if (intersection.entity.isSolid())
//			{
//				Rectangle entityRect = entity.getRect();
//				// Right
//				if (intersection.rect.x > entityRect.x)
//				{
//					entity.move(intersection.rect.width / 2.f, 0.f);
//					if (otherEntIsMoveable) intersection.entity.move(intersection.rect.width / -2.f, 0.f);
//				}
//				// Left
//				if (intersection.rect.x + intersection.rect.width < entityRect.x + entityRect.width)
//				{
//					entity.move(intersection.rect.width / -2.f, 0.f);
//					if (otherEntIsMoveable) intersection.entity.move(intersection.rect.width / 2.f, 0.f);
//				}
//				// Top
//				if (intersection.rect.y > entityRect.y)
//				{
//					entity.move(0.f, intersection.rect.height / -2.f);
//					if (otherEntIsMoveable) intersection.entity.move(0.f, intersection.rect.height / 2.f);
//				}
//				// Bottom
//				if (intersection.rect.y + intersection.rect.height < entityRect.y + entityRect.height)
//				{
//					entity.move(0.f, intersection.rect.height / 2.f);
//					if (otherEntIsMoveable) intersection.entity.move(0.f, intersection.rect.height / -2.f);
//				}
//			}
//		}
	}
}
