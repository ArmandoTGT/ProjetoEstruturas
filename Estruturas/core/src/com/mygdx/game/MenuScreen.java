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

    TextButtonStyle textButtonStyleABP;
    BitmapFont fontABP;
    Skin skinABP;
    TextureAtlas buttonAtlasABP;
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
	     Button buttonSair = new TextButton(" ", textButtonStyleSair);
	     buttonSair.addListener(new ClickListener() {	    	 
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
				System.exit(0);
			}		    	
		     });
	     buttonSair.setPosition(430, 115);
		 stage.addActor(buttonSair);
		//end Botão Sair
		 
		//Begin Botão Lseq
		 fontLseq = new BitmapFont();
	     skinLseq = new Skin();
	     buttonAtlasLseq = new TextureAtlas("botões/LseqImg.pack");
	     skinLseq.addRegions(buttonAtlasLseq);
	     textButtonStyleLseq = new TextButtonStyle();
	     textButtonStyleLseq.font = fontLseq;
	     textButtonStyleLseq.up = skinLseq.getDrawable("SequencialNormal");
	     textButtonStyleLseq.down = skinLseq.getDrawable("SequencialPressionado");
	     textButtonStyleLseq.checked = skinLseq.getDrawable("SequencialNormal");
	     Button buttonLseq = new TextButton(" ", textButtonStyleLseq);
	     buttonLseq.addListener(new ClickListener() {	    	 
				@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				textButtonStyleLseq.up = skinLseq.getDrawable("SequencialNormal");
				super.exit(event, x, y, pointer, toActor);
			}
			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				textButtonStyleLseq.up = skinLseq.getDrawable("SequencialSelecionado");
				return super.mouseMoved(event, x, y);
			}
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				/*
				 * Através desse método quando apertamos o botão,
				 * vamos chamar uma nova tela, que nesse caso é a janela da fila
				 */
				game.setScreen(new ListaSeqScreen(game));
				MenuScreen.this.dispose();
			}		    	
		     });
	     buttonLseq.setPosition(432, 565);
		 stage.addActor(buttonLseq);
		//end Botão Lseq
		 
		//Begin Botão LSE
		 fontLSE = new BitmapFont();
	     skinLSE = new Skin();
	     buttonAtlasLSE = new TextureAtlas("botões/LSEImg.pack");
	     skinLSE.addRegions(buttonAtlasLSE);
	     textButtonStyleLSE = new TextButtonStyle();
	     textButtonStyleLSE.font = fontLSE;
	     textButtonStyleLSE.up = skinLSE.getDrawable("LSENormal");
	     textButtonStyleLSE.down = skinLSE.getDrawable("LSEPressionado");
	     textButtonStyleLSE.checked = skinLSE.getDrawable("LSENormal");
	     Button buttonLSE = new TextButton(" ", textButtonStyleLSE);
	     buttonLSE.addListener(new ClickListener() {	    	 
				@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				textButtonStyleLSE.up = skinLSE.getDrawable("LSENormal");
				super.exit(event, x, y, pointer, toActor);
			}
			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				textButtonStyleLSE.up = skinLSE.getDrawable("LSESelecionado");
				return super.mouseMoved(event, x, y);
			}
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				/*
				 * Através desse método quando apertamos o botão,
				 * vamos chamar uma nova tela, que nesse caso é a janela da fila
				 */
				game.setScreen(new ListaSEncScreen(game));
				MenuScreen.this.dispose();
			}		    	
		     });
	     buttonLSE.setPosition(150, 415);
		 stage.addActor(buttonLSE);
		//end Botão LSE
		 
		//Begin Botão LDE
		 fontLDE = new BitmapFont();
	     skinLDE = new Skin();
	     buttonAtlasLDE = new TextureAtlas("botões/LDEImg.pack");
	     skinLDE.addRegions(buttonAtlasLDE);
	     textButtonStyleLDE = new TextButtonStyle();
	     textButtonStyleLDE.font = fontLDE;
	     textButtonStyleLDE.up = skinLDE.getDrawable("LDENormal");
	     textButtonStyleLDE.down = skinLDE.getDrawable("LDEPressionado");
	     textButtonStyleLDE.checked = skinLDE.getDrawable("LDENormal");
	     Button buttonLDE = new TextButton(" ", textButtonStyleLDE);
	     buttonLDE.addListener(new ClickListener() {	    	 
				@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				textButtonStyleLDE.up = skinLDE.getDrawable("LDENormal");
				super.exit(event, x, y, pointer, toActor);
			}
			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				textButtonStyleLDE.up = skinLDE.getDrawable("LDESelecionado");
				return super.mouseMoved(event, x, y);
			}
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				/*
				 * Através desse método quando apertamos o botão,
				 * vamos chamar uma nova tela, que nesse caso é a janela da fila
				 */
				game.setScreen(new ListaDEncScreen(game));
				MenuScreen.this.dispose();
			}		    	
		     });
	     buttonLDE.setPosition(150, 265);
		 stage.addActor(buttonLDE);
		//end Botão LDE
		 
		//Begin Botão Pilha
		 fontPilha = new BitmapFont();
	     skinPilha = new Skin();
	     buttonAtlasPilha = new TextureAtlas("botões/PilhaImg.pack");
	     skinPilha.addRegions(buttonAtlasPilha);
	     textButtonStylePilha = new TextButtonStyle();
	     textButtonStylePilha.font = fontPilha;
	     textButtonStylePilha.up = skinPilha.getDrawable("PilhaNormal");
	     textButtonStylePilha.down = skinPilha.getDrawable("PilhaPressionado");
	     textButtonStylePilha.checked = skinPilha.getDrawable("PilhaNormal");
	     Button buttonPilha = new TextButton(" ", textButtonStylePilha);
	     buttonPilha.addListener(new ClickListener() {	    	 
				@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				textButtonStylePilha.up = skinPilha.getDrawable("PilhaNormal");
				super.exit(event, x, y, pointer, toActor);
			}
			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				textButtonStylePilha.up = skinPilha.getDrawable("PilhaSelecionado");
				return super.mouseMoved(event, x, y);
			}
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				/*
				 * Através desse método quando apertamos o botão,
				 * vamos chamar uma nova tela, que nesse caso é a janela da pilha
				 */
				game.setScreen(new PilhaScreen(game));
				MenuScreen.this.dispose();
			}		    	
		     });
	     buttonPilha.setPosition(717, 265);
		 stage.addActor(buttonPilha);
		//end Botão Pilha
		 
		//Begin Botão Fila
		 fontFila = new BitmapFont();
	     skinFila = new Skin();
	     buttonAtlasFila = new TextureAtlas("botões/FilaImg.pack");
	     skinFila.addRegions(buttonAtlasFila);
	     textButtonStyleFila = new TextButtonStyle();
	     textButtonStyleFila.font = fontFila;
	     textButtonStyleFila.up = skinFila.getDrawable("FilaNormal");
	     textButtonStyleFila.down = skinFila.getDrawable("FilaPressionado");
	     textButtonStyleFila.checked = skinFila.getDrawable("FilaNormal");
	     Button buttonFila = new TextButton(" ", textButtonStyleFila);
	     buttonFila.addListener(new ClickListener() {	    	 
				@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				textButtonStyleFila.up = skinFila.getDrawable("FilaNormal");
				super.exit(event, x, y, pointer, toActor);
			}
			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				textButtonStyleFila.up = skinFila.getDrawable("FilaSelecionado");
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
				MenuScreen.this.dispose();
			}		    	
		     });
	     buttonFila.setPosition(715, 415);
		 stage.addActor(buttonFila);
		//end Botão Pilha
		 
		//Begin Botão ABP
		 fontABP = new BitmapFont();
	     skinABP = new Skin();
	     buttonAtlasABP = new TextureAtlas("botões/FilaImg.pack");
	     skinABP.addRegions(buttonAtlasABP);
	     textButtonStyleABP = new TextButtonStyle();
	     textButtonStyleABP.font = fontABP;
	     textButtonStyleABP.up = skinABP.getDrawable("FilaNormal");
	     textButtonStyleABP.down = skinABP.getDrawable("FilaPressionado");
	     textButtonStyleABP.checked = skinABP.getDrawable("FilaNormal");
	     Button buttonABP = new TextButton(" ", textButtonStyleABP);
	     buttonABP.addListener(new ClickListener() {	    	 
				@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				textButtonStyleABP.up = skinABP.getDrawable("FilaNormal");
				super.exit(event, x, y, pointer, toActor);
			}
			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				textButtonStyleABP.up = skinABP.getDrawable("FilaSelecionado");
				return super.mouseMoved(event, x, y);
			}
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				/*
				 * Através desse método quando apertamos o botão,
				 * vamos chamar uma nova tela, que nesse caso é a janela da fila
				 */
				game.setScreen(new ABPScreen(game));
				MenuScreen.this.dispose();
			}		    	
		     });
	     buttonABP.setPosition(0, 0);
		 stage.addActor(buttonABP);
		//end Botão ABP
		
		
	}
	int teste = 0;
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
		fontSair.dispose();
		fontLseq.dispose();
		fontLSE.dispose();
		fontLDE.dispose();
		fontLDE.dispose();
		fontFila.dispose();
		fontPilha.dispose();
		fontABP.dispose();
		
		skinSair.dispose();
		skinLseq.dispose();
		skinLSE.dispose();
		skinLDE.dispose();
		skinFila.dispose();
		skinPilha.dispose();
		skinABP.dispose();
		
		buttonAtlasSair.dispose();
		buttonAtlasLseq.dispose();
		buttonAtlasLSE.dispose();
		buttonAtlasLDE.dispose();
		buttonAtlasFila.dispose();
		buttonAtlasPilha.dispose();
		buttonAtlasABP.dispose();
		
		fundo.dispose();
		stage.dispose();

		
	}
	
	
}
