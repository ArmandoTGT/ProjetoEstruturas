package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

public class Hud implements Disposable, TextInputListener{
	int i, pos; //Nos ajudará na lógica de adicionar e remover
	
	public Stage stage;
	private Viewport port;
	
	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	//Label countdownLabel;
	//Label scoreLabel;
	//Label timeLabel;
	//Label levelLabel;
	//Label worldLabel;
	//Label marioLabel;
	
	TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    
    TextButton button2;
    TextButtonStyle textButtonStyle2;
    BitmapFont font2;
    Skin skin2;
    TextureAtlas buttonAtlas2;
    
	
	public Hud(SpriteBatch sb) {
		i = 0;
		
		worldTimer = 300;
		timeCount = 0;
		score = 0;
		
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(port, sb);
		
		 Gdx.input.setInputProcessor(stage);
		 font = new BitmapFont();
	     skin = new Skin();
	     buttonAtlas = new TextureAtlas("coisa/botões.pack");
	     skin.addRegions(buttonAtlas);
	     textButtonStyle = new TextButtonStyle();
	     textButtonStyle.font = font;
	     textButtonStyle.up = skin.getDrawable("normal");
	     textButtonStyle.down = skin.getDrawable("apertado");
	     textButtonStyle.checked = skin.getDrawable("normal");
	     Button marioLabel = new TextButton(" ", textButtonStyle);
	     
	     marioLabel.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				//Adicionará o tamanho da estrutura
				Gdx.input.getTextInput(Hud.this, "Adicionar", "", "Posição");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Gdx.input.getTextInput(Hud.this, "Adicionar", "", "Conteudo");
			}
	    	
	     });
	     stage.addActor(marioLabel);
	     
	     font2 = new BitmapFont();
	     skin2 = new Skin();
	     buttonAtlas2 = new TextureAtlas("coisa/botões.pack");
	     skin2.addRegions(buttonAtlas2);
	     textButtonStyle2 = new TextButtonStyle();
	     textButtonStyle2.font = font2;
	     textButtonStyle2.up = skin2.getDrawable("apertado");
	     textButtonStyle2.down = skin2.getDrawable("normal");
	     textButtonStyle2.checked = skin2.getDrawable("normal");
	     Button worldLabel = new TextButton(" ", textButtonStyle2);
	     stage.addActor(worldLabel);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		//countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//levelLabel = new Label("1 - 1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(marioLabel).expandX().pad(10);
		table.add(worldLabel).expandX().pad(10);
		//table.add(timeLabel).expandX().pad(10);
		//table.row();
		//table.add(scoreLabel).expandX();
		//table.add(levelLabel).expandX();
		//table.add(countdownLabel).expandX();
		
		stage.addActor(table);
		
	}
	
	/*
	 * Vamos 
	 */
	@Override
	public void input(String text) {
		if(i == 0) { //A primeira vez que é chamada a função é para definir a posicao
			pos = Integer.parseInt(text);
			i++; 
		}if(i == 1) {//A segunda vez que mandar um resultado será com o conteudo
			MyScreen.lista_seq.insere(pos, text);
			i=0;//Reiniciar a contagem
		}
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
	//---
	
	public void dispose() {
		stage.dispose();
		
	}


}
