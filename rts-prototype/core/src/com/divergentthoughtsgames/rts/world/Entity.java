package com.divergentthoughtsgames.rts.world;

import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.util.GameMath;

/**
 * Base class for objects in the game world.
 * @author Christopher D. Canfield
 */
public abstract class Entity
{
	// The entity's unique id.
	private UUID id;
	
	// The list of controllers.
	private Array<Controller> controllers;
	
	/** The current sprite. **/
	protected Sprite sprite;
	/** The bounding rect's x offset from the sprite. **/
	protected float spriteOffsetX;
	/** The bounding rect's y offset from the sprite. **/
	protected float spriteOffsetY;
	
	/** The entity's bounding rectangle. **/
	protected Rectangle rect;
	
	/** The entity's max speed. **/
	protected float maxSpeed;
	
	// The entity's current speed.
	private float speed;
	
	protected final Vector2 movementVector = new Vector2();
	
	/** Whether the entity is selectable by the user. **/
	protected boolean selectable;
	
	// Whether the entity has been disposed.
	private boolean disposed;
	
	/** The game world. **/
	protected final World world;
	
	protected Entity(World world)
	{
//		this.components = new ArrayMap<>(false, 4);
		this.controllers = new Array<>(false, 1);
		this.id = UUID.randomUUID();
		this.world = world;
		
//		setControllers();
	}
	
	/**
	 * Sets the entity's controllers. Called once, on construction.
	 */
//	protected abstract void setControllers();
	
	public final UUID getId()
	{
		return id;
	}
	
	public final Rectangle getRect()
	{
		return rect;
	}
	
	public void setPosition(float x, float y)
	{
		rect.x = x;
		rect.y = y;
	}
	
	public int getX()
	{
		return (int)rect.x;
	}
	
	public int getY()
	{
		return (int)rect.y;
	}
	
	public float getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(float change)
	{
		speed += change;
		if (speed < 0)
		{
			speed = 0;
		}
		else if (speed > maxSpeed)
		{
			speed = maxSpeed;
		}
	}
	
	public void rotateToFace(int x, int y)
	{
		float angle = GameMath.angleToFace((int)rect.x, (int)rect.y, x, y) * MathUtils.radiansToDegrees;
		sprite.setRotation(0);
		sprite.rotate(angle + (MathUtils.PI / 2.f * MathUtils.radiansToDegrees));
		
		onRotate();
	}
	
	/**
	 * Provides a hook into the rotateToFace method. Override this to receive notification when
	 * the entity is rotated.
	 */
	protected void onRotate()
	{
	}
	
	public void logRotation()
	{
		Gdx.app.debug("Entity Rotation", toString() + ": " + sprite.getRotation());
	}
	
	protected void addController(Controller controller)
	{
		controllers.add(controller);
	}
	
	protected static void initializeSprite(Sprite sprite)
	{
		sprite.setOriginCenter();
	}
	
	public final void draw(SpriteBatch batch)
	{
		onDraw();
		
		if (sprite != null)
		{
			sprite.setPosition(rect.x + spriteOffsetX, rect.y + spriteOffsetY);
			sprite.draw(batch);
		}
	}
	
	public final void drawRect(ShapeRenderer batch)
	{
		if (rect != null)
		{
			boolean isSelected = App.selected.contains(this);
			Color originalColor = null;
			if (isSelected)
			{
				originalColor = batch.getColor().cpy();
				batch.setColor(Color.BLUE);
			}
			
			if (isSelected || App.debugEnabled())
			{
				batch.rect(rect.x, rect.y, rect.width, rect.height);
			}
			
			if (isSelected)
			{
				batch.setColor(originalColor);
			}
		}
	}
	
	protected abstract void onDraw();
	
	public final void update()
	{
		onUpdate();
		
		for (final Controller c : controllers)
		{
			c.update(world);
		}
	}
	
	protected abstract void onUpdate();
	
	/**
	 * Disposes the entity.
	 */
	public final void dispose()
	{
		disposed = true;
		onDispose();
	}
	
	/**
	 * Called when the entity is disposed.
	 */
	protected abstract void onDispose();
	
	/**
	 * Specifies whether the entity has been disposed.
	 * @return true if the entity has been disposed.
	 */
	public final boolean isDisposed()
	{
		return disposed;
	}
}
