package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen, TextInputListener{
	
	private Executor game;
	TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    private Texture fundo;
    
    
    public Stage stage;
    private Viewport port;
	
    public MenuScreen(final Executor game){
    	
    	this.game = game;
    	port = new FitViewport(1280, 720, new OrthographicCamera());
    	stage = new Stage(port);
    	fundo = new Texture("coisa/FundoMenu.png");
    	
    	//linhas botões
		 font = new BitmapFont();
	     skin = new Skin();
	     buttonAtlas = new TextureAtlas("coisa/botões.pack");
	     skin.addRegions(buttonAtlas);
	     textButtonStyle = new TextButtonStyle();
	     textButtonStyle.font = font;
	     textButtonStyle.up = skin.getDrawable("normal");
	     textButtonStyle.down = skin.getDrawable("apertado");
	     textButtonStyle.checked = skin.getDrawable("normal");
	     Button button = new TextButton(" ", textButtonStyle);
	     button.addListener(new ClickListener() {
	    	 	
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					game.setScreen(new MyScreen(game));
				}
		    	
		     });
	     button.setPosition(640, 370);
		  stage.addActor(button);
		
	}
	
    public void show() {
    	Gdx.input.setInputProcessor(stage);
		
	}

	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.balde.begin();
		game.balde.draw(fundo, 0, 0);
		game.balde.end();
		
		stage.draw();
		
	}
	
	public void resize(int width, int height) {
		
		
	}
	
	public void pause() {
		
		
	}
	
	public void resume() {
		
		
	}
	
	public void hide() {
		
		
	}
	
	public void dispose() {
		
		
	}

	@Override
	public void input(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
	
	
}
