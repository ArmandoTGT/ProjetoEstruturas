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

public class ListaDEncScreen implements Screen{
	
	private Executor game;
	private static Random posicao; //Como java aloca onde na memória será salvo, o random representará essa aleatoriedade
	private static int posicaoAux;
	private OrthographicCamera camera;
	private Viewport port;
	private ListaDEncHud hud;
	private Texture fundo;
	private static LDEGen lista;
	static Texture quadValido;
	static Texture quadVazio;
	static Texture setaDireita,setaEsquerda;

	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public ListaDEncScreen(Executor game){
		posicao = new Random();
		posicaoAux = 0;
		quadValido = new Texture("coisa/BlocoDuplamenteEncadeado.png");
		quadVazio = new Texture("coisa/quadradoVazio.png");
		setaDireita = new Texture("coisa/SetaDuplaDireita.png");
		setaEsquerda = new Texture("coisa/SetaDuplaEsquerda.png");
		lista = new LDEGen(quadValido, quadVazio);
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new ListaDEncHud(game.balde, game);
		fundo = new Texture("coisa/FundoEstruturas.png");
		//setas = new Texture[50];
		//---instaciação dos quadrados deletado
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
		game.balde.draw(fundo, -640, -360);
		/*
		 * Esse for fará uma seta após o bloco inicial, pois ele será a seta 
		 * voltando e terá seu numero 1 vez a mais que o numero de blocos
		 */
		//A seta para a direita será colocada após o primeiro quadrado de tamanho 128 pixels 640 + 128 = 512
			for(int i = 1; i <= lista.tamanho(); i++) {
				game.balde.draw(lista.imagem(i), -640 +  320 * (i - 1), 0); //----
				game.balde.draw(setaDireita, -512 + (320 * (i-1)), 0);
			}
		/*
		 * Esse for fará uma seta antes do bloco inicial, pois ele será a seta 
		 * voltando e terá seu numero igual ao número de blocos
		 */
		//A seta para a esquerda segue a mesma lógica
			for(int i = 0; i < lista.tamanho(); i++) {
				game.balde.draw(setaEsquerda, -512 + (320 *(i -1)), 0); 
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
	
	/*
	 * Os próximos dois métodos são responsáveis por imprimirem
	 * na tela a posição que teve alguma alteração
	 */
	
	public static void insereTela(int pos, String valor) {
		System.out.println("printou no " + lista.insere(pos, valor)); //Pode lançar null ao tentar remover uma posição invalida
	}
	
	public static void removeTela(int pos) {
		System.out.println(lista.remove(pos)); //Pode lançar null o remove quando a posição é invalida
	}
	
	
}
