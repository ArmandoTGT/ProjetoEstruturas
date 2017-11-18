package com.mygdx.game;


import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.util.Random;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ListaSEncScreen implements Screen{
	
	private static Executor game;
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
	static BitmapFont font[];
	static BitmapFont font2;
	static int aux = 1;
	static String pesquisa;
	public static boolean exit;

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
		
		FileHandle caminho = new FileHandle("coisa/font.ttf");
		  FreeTypeFontGenerator generator = new FreeTypeFontGenerator(caminho);
		  FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		  parameter.size = 20;		  
		  font = new BitmapFont[21];					 
		  for(int i = 0; i <= 20; i++) {
			  font[i] = generator.generateFont(parameter);	
			  font[i].setColor(Color.valueOf("b7b7b7"));	
			  }	  
		  generator.dispose();
		  
		  FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(caminho);
		  FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
		  parameter2.size = 20;		  
		  font2 = new BitmapFont();					 
		  font2 = generator2.generateFont(parameter2);	
		  font2.setColor(Color.valueOf("b7b7b7"));		  
		  generator2.dispose();
		  
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
		game.balde.draw(fundo, -1980, -1020);
		game.balde.draw(cabeca, -640, 120);	
		
		if(exit) {
			this.dispose();
		}
		
			for(int i = 1; i <= lista.tamanho(); i++) {
						game.balde.draw(lista.imagem(i), -640 + 120 + 320 * (i - 1), 0); //----
						game.balde.draw(setaDireita, -640 + 120 + (128 * i) + (192 * (i -1)), 0);
						//A baixo comparamos a string de conteudo com a string que recebemos do metodo de pesquisa,
						//se for igual alteramos a cor da fonte
						
						font[i - 1].draw(game.balde, String.valueOf(lista.elemento(i)), -640 + 128 + 45 + (320 * (i - 1)),	70);
						font2.draw(game.balde, String.valueOf(i+"*"), -690 + 128 + 45 + (320 * (i - 1)),	115);
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
			if(camera.zoom > 1.8999991)camera.zoom = (float) 1.8999991;
		}
		if (Gdx.input.isKeyPressed(Keys.Q)) {
			camera.zoom -= 0.02;			
			if(camera.zoom < 0.30000037)camera.zoom = (float) 0.30000037;
		}
		
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) ) {
			camera.position.x -= 1000 * dt;			
			if(camera.position.x < -616.67914)camera.position.x = (float) -616.67914;
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT) ){
			camera.position.x += 1000 * dt;
			if(camera.position.x > 6326.621)camera.position.x = (float) 6326.621;
		}
		else if(Gdx.input.isKeyPressed(Keys.UP) ){
			camera.position.y += 1000 * dt;			
			if(camera.position.y > 2267.0886)camera.position.y = (float) 2267.0886;
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN) ){
			camera.position.y -= 1000 * dt;				
			if(camera.position.y < -183.99722)camera.position.y = (float) -183.99722;
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
	
	public static void sair() {
		exit = true;		
	}
	
	public static void insereTela(int pos, String valor) {
		try {
			//Verifica se o valor está dentro do padrão
			if((pos < 1) || (pos > 20)) {
				throw new Exception();
			}
			if(pos > aux) {
				throw new Exception();
			}
			
			int n = Integer.parseInt(valor);
			
			lista.insere(pos, valor);
			aux++;
			posRabo++;

		}
		catch(NumberFormatException nf){
			JOptionPane.showMessageDialog(null, "Conteúdo apenas composto por números!", 
										  "Error", ERROR_MESSAGE);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Posição Inválida! Próxima posição " + aux + "!", 
					  					  "Error", ERROR_MESSAGE);
		}	
	}
	
	public static void removeTela(int pos) {
		try {
			Object j = lista.remove(pos);
			if(j == null) {
				throw new Exception();
			}
			else
			{
				posRabo--;
				aux--;
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Não se pode remover o que não existe!", 
										  "Error", ERROR_MESSAGE);

			game.setScreen(new ListaSEncScreen(game));
		}				
	}


	public static void Pesquisa(String text) {
		pesquisa = text;
		try{
			for(int i = 1; i <= lista.tamanho(); i++){				
				if(pesquisa.equals(String.valueOf(lista.elemento(i)))){
					
				}
				
				else{				
				font[i - 1].setColor(Color.valueOf("b7b7b7"));
				}				
			}
			}catch(Exception g){				
			}
		
			
	
		for(int j = 1; j <= lista.tamanho(); j++) {
			try {
			if(pesquisa.equals(String.valueOf(lista.elemento(j)))){
				font[j - 1].setColor(Color.valueOf("7fff00"));				
				break;
			}else{							
				font[j - 1].setColor(Color.valueOf("7fff00"));
				Thread.sleep(1000);
				font[j - 1].setColor(Color.valueOf("b7b7b7"));
	}
	}catch(Exception f){
		
	}
		}		
	}
}
