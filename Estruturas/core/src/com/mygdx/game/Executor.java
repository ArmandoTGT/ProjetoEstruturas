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
	
	static public MenuScreen menuScreen;
	
	
	@Override
	public void create () {
		
		menuScreen = new MenuScreen(this);
		balde = new SpriteBatch();
		
		setScreen(menuScreen);
		
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
