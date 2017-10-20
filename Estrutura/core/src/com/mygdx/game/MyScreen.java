package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyScreen implements Screen{
	
	private Executor game;
	private OrthographicCamera camera;
	private Viewport port;
	private Texture[] quads;
	private Hud hud;
	private Texture fundo;
	private int elementos; //Total de elementos que serão mostrados na tela
	public static ListaSeqGen lista_seq; 
	public static boolean baux;

	//private Texture[] setas;
	
	public MyScreen(Executor game){
		
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new Hud(game.balde);
		fundo = new Texture("coisa/FundoEstruturas.png");
		elementos = 0;
		baux = false;
		//setas = new Texture[50];
		quads = new Texture[20];
		for(int i = 0; i < 20; i++) {
			quads[i] = new Texture("coisa/quadradoVazio.png");
		}


		/*for(int i = 0; i < 50; i++) {
			setas[i] = new Texture("coisa/seta.png");
		}*/
		
	}

	
	public void show() {
		
		
	}

	
	public void render(float delta) {
		moveCamera(delta);
				
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.balde.setProjectionMatrix(camera.combined);
		
		game.balde.begin();
		game.balde.draw(fundo, -640, -370);
		if(baux) {
			elementos = lista_seq.memoria();
		}
		for(int i = 0; i < elementos; i++) {
			game.balde.draw(quads[i], -640 + 129 * i, 0);
			
		}
		/*for(int i = 0; i < 50; i++) {			
			game.balde.draw(setas[i], -640 + (32 * (i + 1) - 16), 0); 
		}*/
		
		game.balde.end();
		hud.stage.act(delta);
		hud.stage.draw();
		
		
	}

	
	private void moveCamera(float dt) {
		setmoveCamera(dt);
		
		camera.update();
		
	}


	private void setmoveCamera(float dt) {
		
		/*if(Gdx.input.isTouched()){
			
			System.out.println(" " + Gdx.input.getX());
			camera.position.x = -Gdx.input.getX() +  (-640 + 129 * (19/2) + 382);
			camera.position.y = Gdx.input.getY() - 300;
			}*/
		
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) ) {
			camera.position.x -= 1000 * dt;
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT) ){
			camera.position.x += 1000 * dt;
		}
		else if(Gdx.input.isKeyPressed(Keys.UP) ){
			camera.position.y += 1000 * dt;
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN) ){
			camera.position.y -= 1000 * dt;
		}
		
	}


	public void resize(int width, int height) {
		port.update(width, height);
		
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
