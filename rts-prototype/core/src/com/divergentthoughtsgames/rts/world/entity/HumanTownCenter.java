package com.divergentthoughtsgames.rts.world.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.graphics.Textures;
import com.divergentthoughtsgames.rts.world.Entity;
import com.divergentthoughtsgames.rts.world.World;
import com.divergentthoughtsgames.rts.world.controller.SolidEntityController;

public class HumanTownCenter extends Entity
{
	private TextureRegion built;
	private TextureRegion building;
	
	private Animation buildingAnimation;
	
	private float buildAnimationTime;

	public HumanTownCenter(World world)
	{
		super(world);
		
		rect = new Rectangle(0, 0, 120, 104);
		spriteOffsetX = 0;
		spriteOffsetY = 0;
		selectable = false;
		
		setAnimations();
	}
	
	@Override
	protected void setControllers()
	{
	}
	
	private void setAnimations()
	{
		built = new TextureRegion(App.graphics.getTexture(Textures.HumanBuildingsSummer), 269, 17, 120, 104);
		building = new TextureRegion(App.graphics.getTexture(Textures.HumanBuildingsSummer), 269, 145, 120, 104);
		
		sprite = new Sprite(built);
		initializeSprite(sprite);
		
		buildingAnimation = new Animation(6.f, 
				building,
				built);
		buildingAnimation.setPlayMode(PlayMode.NORMAL);		
	}

	@Override
	protected void onDraw()
	{
//		sprite.setRegion(buildingAnimation.getKeyFrame(animationTime));
	}

	@Override
	protected void onUpdate()
	{
		buildAnimationTime += Gdx.graphics.getRawDeltaTime();
	}

	@Override
	protected void onDispose()
	{
	}
}
