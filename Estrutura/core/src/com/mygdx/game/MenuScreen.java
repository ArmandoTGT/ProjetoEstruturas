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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen{
	
	private Executor game;
	
	
    TextButtonStyle textButtonStyleSair;
    BitmapFont fontSair;
    Skin skinSair;
    TextureAtlas buttonAtlasSair;
    
    
    TextButtonStyle textButtonStyleLseq;
    BitmapFont fontLseq;
    Skin skinLseq;
    TextureAtlas buttonAtlasLseq;
   
    
    TextButtonStyle textButtonStyleLSE;
    BitmapFont fontLSE;
    Skin skinLSE;
    TextureAtlas buttonAtlasLSE;
    
    
    TextButtonStyle textButtonStyleLDE;
    BitmapFont fontLDE;
    Skin skinLDE;
    TextureAtlas buttonAtlasLDE;
    
    
    TextButtonStyle textButtonStylePilha;
    BitmapFont fontPilha;
    Skin skinPilha;
    TextureAtlas buttonAtlasPilha;
    
    
    TextButtonStyle textButtonStyleFila;
    BitmapFont fontFila;
    Skin skinFila;
    TextureAtlas buttonAtlasFila;

    private Texture fundo;
    
    
    public Stage stage;
    private Viewport port;
	
    public MenuScreen(final Executor game){
    	
    	this.game = game;
    	port = new FitViewport(1280, 720, new OrthographicCamera());
    	stage = new Stage(port);
    	fundo = new Texture("coisa/FundoMenu.png");
    	
    	//Begin Botão Sair
		 fontSair = new BitmapFont();
	     skinSair = new Skin();
	     buttonAtlasSair = new TextureAtlas("botões/SairImg.pack");
	     skinSair.addRegions(buttonAtlasSair);
	     textButtonStyleSair = new TextButtonStyle();
	     textButtonStyleSair.font = fontSair;
	     textButtonStyleSair.up = skinSair.getDrawable("SairNormal");
	     textButtonStyleSair.down = skinSair.getDrawable("SairPressionado");
	     textButtonStyleSair.checked = skinSair.getDrawable("SairNormal");
	     Button button = new TextButton(" ", textButtonStyleSair);
	     button.addListener(new ClickListener() {	    	 
				@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				textButtonStyleSair.up = skinSair.getDrawable("SairNormal");
				super.exit(event, x, y, pointer, toActor);
			}
			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				textButtonStyleSair.up = skinSair.getDrawable("SairSelecionado");
				return super.mouseMoved(event, x, y);
			}
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				/*
				 * Através desse método quando apertamos o botão,
				 * vamos chamar uma nova tela, que nesse caso é a janela da fila
				 */
				game.setScreen(new FilaScreen(game));
			}		    	
		     });
	     button.setPosition(640, 370);
		 stage.addActor(button);
		//end Botão Sair

		
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
		
		stage.act(delta);
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
	
	
}
