package com.mygdx.game;


import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ListaSEncScreen implements Screen{
	
	private Executor game;
	private static Random posicao; //Como java aloca onde na memória será salvo, o random representará essa aleatoriedade
	private static int posicaoAux;
	private OrthographicCamera camera;
	private Viewport port;
	private ListaSEncHud hud;
	private Texture fundo;
	private static LSEGen lista;
	static Texture quadValido;
	static Texture quadVazio;
	static Texture setaDireita;
	static Texture cabeca;
	private static int posRabo;

	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public ListaSEncScreen(Executor game){
		posicao = new Random();
		posRabo = 0;
		posicaoAux = 0;
		quadValido = new Texture("coisa/BlocoEncadeado.png");
		quadVazio = new Texture("coisa/quadradoVazio.png");
		setaDireita = new Texture("coisa/setaDireita.png");
		cabeca = new Texture("coisa/PonteiroCabeça.png");		
		lista = new LSEGen(quadValido, quadVazio);
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new ListaSEncHud(game.balde, game);
		fundo = new Texture("coisa/FundoEstruturas.png");
	
		
	}

	
	public void show() {
		
		
	}

	
	public void render(float delta) {
		moveCamera(delta);
				
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.balde.setProjectionMatrix(camera.combined);
		game.balde.begin();
		game.balde.draw(fundo, -640, -360);
		game.balde.draw(cabeca, -640, 50);	
		
			for(int i = 1; i <= lista.tamanho(); i++) {
						game.balde.draw(lista.imagem(i), -640 + 229 + 320 * (i - 1), 0); //----
						game.balde.draw(setaDireita, -640 + 229 + (128 * i) + (192 * (i -1)), 0);
				}
		game.balde.end();
		hud.stage.act(delta);
		hud.stage.draw();
		
		
	}

	
	private void moveCamera(float dt) {
		setmoveCamera(dt);
		
		camera.update();
		
	}


	private void setmoveCamera(float dt) {
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			camera.zoom += 0.02;
		}
		if (Gdx.input.isKeyPressed(Keys.Q)) {
			camera.zoom -= 0.02;
		}
		
		
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
	
	/*
	 * Os próximos dois métodos são responsáveis por imprimirem
	 * na tela a posição que teve alguma alteração
	 */
	
	public static void insereTela(int pos, String valor) {
		System.out.println("printou no " + lista.insere(pos, valor));		
	}
	
	public static void removeTela(int pos) {
		System.out.println(lista.remove(pos));		
	}
	
	
}
