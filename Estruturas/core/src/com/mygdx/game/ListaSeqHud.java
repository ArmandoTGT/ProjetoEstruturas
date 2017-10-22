package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ListaSeqHud implements Disposable, TextInputListener{
	int opcao, pos; //Nos ajudará na lógica de adicionar e remover
	
	public Stage stage;
	private Viewport port;
	
	private Executor game;	
	
	
    TextButtonStyle textButtonStyleAdd;
    BitmapFont fontAdd;
    Skin skinAdd;
    TextureAtlas buttonAtlasAdd;
    
    TextButtonStyle textButtonStyleRemove;
    BitmapFont fontRemove;
    Skin skinRemove;
    TextureAtlas buttonAtlasRemove;
    
    TextButtonStyle textButtonStyleMenu;
    BitmapFont fontMenu;
    Skin skinMenu;
    TextureAtlas buttonAtlasMenu;
    
	
	public ListaSeqHud(SpriteBatch sb, final Executor game) {
		opcao = 0;
		
		this.game = game;
		
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(port, sb);
		
		 Gdx.input.setInputProcessor(stage);
		 fontAdd = new BitmapFont();
	     skinAdd = new Skin();
	     buttonAtlasAdd = new TextureAtlas("Botões/AddImg.pack");
	     skinAdd.addRegions(buttonAtlasAdd);
	     textButtonStyleAdd = new TextButtonStyle();
	     textButtonStyleAdd.font = fontAdd;
	     textButtonStyleAdd.up = skinAdd.getDrawable("AdicionarNormal");
	     textButtonStyleAdd.down = skinAdd.getDrawable("AdicionarPressionado");
	     textButtonStyleAdd.checked = skinAdd.getDrawable("AdicionarNormal");
	     Button buttonAdd = new TextButton(" ", textButtonStyleAdd);
	     
	     buttonAdd.addListener(new ClickListener() {
	    	 
	    	 @Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
	    		 textButtonStyleAdd.up = skinAdd.getDrawable("AdicionarNormal");
					super.exit(event, x, y, pointer, toActor);
				}
				@Override
				public boolean mouseMoved(InputEvent event, float x, float y) {
					textButtonStyleAdd.up = skinAdd.getDrawable("AdicionarSelecionado");
					return super.mouseMoved(event, x, y);
				}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				//Adicionará o tamanho da estrutura
				opcao = 0; //Permitirá adicionar
				Gdx.input.getTextInput(ListaSeqHud.this, "Adicionar", "", "Posição-Conteudo");				
				}	    	
	     	});
	     stage.addActor(buttonAdd);
	     
	     fontRemove = new BitmapFont();
	     skinRemove = new Skin();
	     buttonAtlasRemove = new TextureAtlas("Botões/RemoveImg.pack");
	     skinRemove.addRegions(buttonAtlasRemove);
	     textButtonStyleRemove = new TextButtonStyle();
	     textButtonStyleRemove.font = fontRemove;
	     textButtonStyleRemove.up = skinRemove.getDrawable("RemoverNormal");
	     textButtonStyleRemove.down = skinRemove.getDrawable("RemoverPressionado");
	     textButtonStyleRemove.checked = skinRemove.getDrawable("RemoverNormal");
	     Button buttonRemove = new TextButton(" ", textButtonStyleRemove);
	     buttonRemove.addListener(new ClickListener() {	    	 
	    	 	@Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
	    		 textButtonStyleRemove.up = skinRemove.getDrawable("RemoverNormal");
					super.exit(event, x, y, pointer, toActor);
				}
				@Override
				public boolean mouseMoved(InputEvent event, float x, float y) {
					textButtonStyleRemove.up = skinRemove.getDrawable("RemoverSelecionado");
					return super.mouseMoved(event, x, y);
				}
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					opcao = 1; //Permitirá excluir
					Gdx.input.getTextInput(ListaSeqHud.this, "Remoção", "", "Posição");
				}    	 
	     	});
	     stage.addActor(buttonRemove);
	     
	     fontMenu = new BitmapFont();
	     skinMenu = new Skin();
	     buttonAtlasMenu = new TextureAtlas("Botões/MenuImg.pack");
	     skinMenu.addRegions(buttonAtlasMenu);
	     textButtonStyleMenu = new TextButtonStyle();
	     textButtonStyleMenu.font = fontMenu;
	     textButtonStyleMenu.up = skinMenu.getDrawable("MenuNormal");
	     textButtonStyleMenu.down = skinMenu.getDrawable("MenuPressionado");
	     textButtonStyleMenu.checked = skinMenu.getDrawable("MenuNormal");
	     Button buttonMenu = new TextButton(" ", textButtonStyleMenu);
	     buttonMenu.addListener(new ClickListener() {	    	 
	    	 	@Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
	    		 textButtonStyleMenu.up = skinMenu.getDrawable("MenuNormal");
					super.exit(event, x, y, pointer, toActor);
				}
				@Override
				public boolean mouseMoved(InputEvent event, float x, float y) {
					textButtonStyleMenu.up = skinMenu.getDrawable("MenuSelecionado");
					return super.mouseMoved(event, x, y);
				}
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					game.setScreen(new MenuScreen(game));
				}    	 
	     	});
	     stage.addActor(buttonMenu);
	     
	     
		Table table = new Table();
		table.top();
		table.setFillParent(true);
						
		table.add(buttonAdd).expandX().pad(10);
		table.add(buttonRemove).expandX().pad(10);
		table.add(buttonMenu).expandX().pad(10);
				
		
		stage.addActor(table);
		
	}
	
	/*
	 * Vamos 
	 */
	@Override
	public void input(String text) {
		int pos, valor;
		if(opcao ==0) {
			String entrada [] = text.split("-");
			pos = Integer.parseInt(entrada[0]);
			ListaSeqScreen.insereTela(pos, entrada[1]);
		}
		else if(opcao == 1) {
			pos = Integer.parseInt(text);
			ListaSeqScreen.removeTela(pos);
		}
	}

	@Override
	public void canceled() {
				
	}
	
	public void dispose() {
		stage.dispose();
		
	}


}
