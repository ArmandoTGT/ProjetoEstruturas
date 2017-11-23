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

public class ListaDEncScreen implements Screen{
	

	private static Executor game;
	private static Random posicao; //Como java aloca onde na memória será salvo, o random representará essa aleatoriedade
	private static int posicaoAux;
	private OrthographicCamera camera;
	private Viewport port;
	private ListaDEncHud hud;
	private static LDEGen lista;
	static Texture quadValido;
	static Texture quadVazio;
	static Texture cabeca;
	static Texture rabo;
	static Texture setaDireita,setaEsquerda;
	private static int posRabo;
	static BitmapFont font[];
	static BitmapFont font2;
	static int aux = 1, count = 0, valorRem;
	static int[] existe = new int[20];
	static String pesquisa;
	public static boolean exit, existeS = false, negativo = false, elementoExiste = false, cheioS = false;

	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public ListaDEncScreen(Executor game){
		posRabo = 0;
		posicao = new Random();
		posicaoAux = 0;
		exit = false;
		quadValido = new Texture("coisa/BlocoDuplamenteEncadeado.png");
		quadVazio = new Texture("coisa/quadradoVazio.png");
		setaDireita = new Texture("coisa/SetaDuplaDireita.png");
		setaEsquerda = new Texture("coisa/SetaDuplaEsquerda.png");
		cabeca = new Texture("coisa/PonteiroCabeça.png");
		rabo = new Texture("coisa/PonteiroCauda.png");
		
		for(int i = 0; i < existe.length; i++){
			existe[i] = -1;
		}
		
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
		  
		lista = new LDEGen(quadValido, quadVazio);
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new ListaDEncHud(game.balde, game);
	
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
				
		Gdx.gl.glClearColor(64/255.0f, 102/255.0f, 128/255.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.balde.setProjectionMatrix(camera.combined);
		game.balde.begin();
		
		game.balde.draw(cabeca, -640, 120);	
		
		if(exit) {
				
			this.dispose();
			
		}
		/*
		 * Esse for fará uma seta após o bloco inicial, pois ele será a seta 
		 * voltando e terá seu numero 1 vez a mais que o numero de blocos
		 */
		//A seta para a direita será colocada após o primeiro quadrado de tamanho 128 pixels 640 + 128 = 512
			for(int i = 1; i <= lista.tamanho(); i++) {
				game.balde.draw(lista.imagem(i), -640 + 120 + 320 * (i - 1), 0); //----
				game.balde.draw(setaDireita, -512 + 120 + (320 * (i-1)), 0);
				
				font[i - 1].draw(game.balde, String.valueOf(lista.elemento(i)), -640 + 128 + 45 + (320 * (i - 1)),	70);
				font2.draw(game.balde, String.valueOf(i+"*"), -690 + 128 + 45 + (320 * (i - 1)),	115);
				
			}
			if(posRabo != 0) game.balde.draw(rabo, -640 + 160 + (320 * (posRabo - 1)), -195);
			else game.balde.draw(rabo, -640 + 160 + (320 * (posRabo)), -195);
		/*
		 * Esse for fará uma seta antes do bloco inicial, pois ele será a seta 
		 * voltando e terá seu numero igual ao número de blocos
		 */
		//A seta para a esquerda segue a mesma lógica
			for(int i = 0; i < lista.tamanho(); i++) {
				game.balde.draw(setaEsquerda, -512 + 120 + (320 *(i -1)), 0); 
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
		
		setaEsquerda.dispose();
		setaDireita.dispose();
		cabeca.dispose();
		rabo.dispose();
		quadVazio.dispose();
		quadValido.dispose();
		
		
	}
	
	public static void sair() {
		for(int i = 0; i < existe.length; i++) {
			existe[i] = -1;
		}
		exit = true;		
	}
	
	/*
	 * Os próximos dois métodos são responsáveis por imprimirem
	 * na tela a posição que teve alguma alteração
	 */
	
	public static void insereTela(int pos, String valor) {
		tiraPesquisa();
		try {
			if(count == 20){
				cheioS = true;
				throw new Exception();
			}
			//Verifica se o valor está dentro do padrão
			if((pos < 1) || (pos > 20)) {
				throw new Exception();
			}
			if(pos > aux) {
				throw new Exception();
			}

			int n = Integer.parseInt(valor);
			if(n < 0){
				negativo = true;
				throw new Exception();
			}
			if(existeP(n)){
				existeS = true;
				throw new Exception();
			}
			
			lista.insere(pos, valor);
			existe[count] = n;
			posRabo++;
			count++;
			aux++;

		}
		catch(NumberFormatException nf){
			JOptionPane.showMessageDialog(null, "Conteúdo apenas composto por números!", 
										  "Error", ERROR_MESSAGE);
		}
		catch(Exception e) {
				if((existeS == false) && (negativo == false) && (cheioS == false)){
					JOptionPane.showMessageDialog(null, "Posição Inválida! Próxima posição " + aux + "!", 
	  					  "Error", ERROR_MESSAGE);
				}
				if(cheioS) {
					cheioS = false;
					JOptionPane.showMessageDialog(null, "Lista Cheia...Tente excluir algum valor antes!", 
											  "Error", ERROR_MESSAGE);
				}
				if(negativo) {
					negativo = false;
					JOptionPane.showMessageDialog(null, "Lista composta apenas por números maiores que Zero!", 
											  "Error", ERROR_MESSAGE);
				}
				if(existeS){
					existeS = false;
					JOptionPane.showMessageDialog(null, "Elemento Já existe na estrutura!", 
												  "Error", ERROR_MESSAGE);
				}
		}
	}
	
	public static void removeTela(int pos) {
		tiraPesquisa();
		try {
			Object j = lista.remove(pos);
			if(j == null) {
				throw new Exception();
			}
			else
			{
				valorRem = Integer.parseInt((String) j);
				for(int i = 0; i < existe.length; i++){
					if(existe[i] == valorRem){
						existe[i] = -1;
						break;
					}
				}
				posRabo--;
				count--;
				aux--;
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Não se pode remover o que não existe!");
		}		
	}
	public static void Pesquisa(String text) {
		pesquisa = text;		
		int aux = 0;
		try{
			int valorConvertido = Integer.parseInt(text);
			if(valorConvertido < 0) throw new Exception();
			for(int i = 0; i < existe.length; ) {
				if(existe[i] == valorConvertido) {
					elementoExiste = true;
				}
				i++;
			}
			if(elementoExiste == false) {
				throw new Exception();
			}
			elementoExiste = false;
			for(int i = 1; i <= lista.tamanho(); i++){				
				if(pesquisa.equals(String.valueOf(lista.elemento(i)))){
					aux = i;
				}
				
				else{				
				font[i - 1].setColor(Color.valueOf("b7b7b7"));
				}				
			}
			
		
		
		if(aux > lista.tamanho()/2){
			for(int j = lista.tamanho(); j >= 1; j--) {
				if(pesquisa.equals(String.valueOf(lista.elemento(j)))){
					font[j - 1].setColor(Color.valueOf("7fff00"));				
					break;
				}else{							
					font[j - 1].setColor(Color.valueOf("7fff00"));
					Thread.sleep(1000);
					font[j - 1].setColor(Color.valueOf("b7b7b7"));
		}
		
			}
		}	
		
			
		if(aux <= lista.tamanho()/2){
			for(int j = 1; j <= lista.tamanho(); j++) {
					if(pesquisa.equals(String.valueOf(lista.elemento(j)))){
						font[j - 1].setColor(Color.valueOf("7fff00"));				
						break;
					}else{							
						font[j - 1].setColor(Color.valueOf("7fff00"));
						Thread.sleep(1000);
						font[j - 1].setColor(Color.valueOf("b7b7b7"));
					}
				
			}
		}
	}catch(NumberFormatException nf){
		tiraPesquisa();
		JOptionPane.showMessageDialog(null, "A estrutura apenas possui números!", 
				  "Error", ERROR_MESSAGE);
	} 
	catch (Exception e) {
		elementoExiste = false;
		tiraPesquisa();
		JOptionPane.showMessageDialog(null, "Não foi possível achar o valor inserido!", 
				  "Error", ERROR_MESSAGE);
	}
	}
	
	
	public static void tiraPesquisa(){//Esse Método será iniciado a cada ação da lista, zerando a marcação da pesquisa
		try{
			for(int i = 1; i <= lista.tamanho(); i++){				
				font[i - 1].setColor(Color.valueOf("b7b7b7"));
					
			}
			pesquisa = null;
			}catch(Exception g){				
			}
	}
	
	/*
	 * Método que trata exceção, apenas aceita a entrada de números entre 1 e 20
	 */
	public boolean isNumber(String text) throws Exception {
		int number = Integer.parseInt(text);
		if((number < 1) || (number > 20)){
			return false;
		}
			return true;
	}
	
	public static boolean existeP(int valor){
		for(int i = 0; i < existe.length; i++){
			if(existe[i] == valor){
				return true;
			}
		}
		return false;
	}
	
}
