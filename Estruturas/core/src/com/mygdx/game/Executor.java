package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Executor extends Game {
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	public static final float PPM = 100;
	
	public SpriteBatch balde;
	
	
	
	
	@Override
	public void create () {
	
		
		balde = new SpriteBatch();
		
		setScreen(new MenuScreen(this));
		
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {

		balde.dispose();
		super.dispose();
	}
	
	
	
}
