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

public class ListaSeqScreen implements Screen, TextInputListener{
	
	
	private static Executor game;
	private static Random posicao; //Como java aloca onde na mem�ria ser� salvo, o random representar� essa aleatoriedade
	private static int posicaoAux;
	private OrthographicCamera camera;
	private Viewport port;
	private ListaSeqHud hud;
	public static ListaSeqGen lista;
	static Texture quadValido;
	static Texture quadVazio;
	static Texture cabeca;
	static Texture rabo;
	static Texture setaDireita,setaEsquerda;
	private static int posRabo;
	static BitmapFont font[];
	static BitmapFont font2;
	static int aux = 1, count = 0, valorRem;
	static int[] existe;
	static String pesquisa;
	public static boolean exit, existeS = false, posInvalida = false, cheioS = false, elementoExiste = false;

	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public ListaSeqScreen(Executor game){
		posRabo = 0;
		posicaoAux = 0;
		exit = false;
		Gdx.input.getTextInput(this, "Lista Sequencial", "", "Tamanho da estrutura");
		quadValido = new Texture("coisa/quadradoPreenchido.png");
		quadVazio = new Texture("coisa/quadradoVazio.png");
		setaDireita = new Texture("coisa/SetaDuplaDireita.png");
		setaEsquerda = new Texture("coisa/SetaDuplaEsquerda.png");
		cabeca = new Texture("coisa/PonteiroCabe�a.png");
		rabo = new Texture("coisa/PonteiroCauda.png");
		
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
		  
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new ListaSeqHud(game.balde, game);
		
		//setas = new Texture[50];
		//---instacia��o dos quadrados deletado
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
	
		
		if(exit) {
			System.out.println("chegou");	
			this.dispose();
					}
		
		//A seta para a direita ser� colocada ap�s o primeiro quadrado de tamanho 128 pixels 640 + 128 = 512
			for(int i = 1; i <= 20; i++) {
				//A baixo comparamos a string de conteudo com a string que recebemos do metodo de pesquisa,
				//se for igual alteramos a cor da fonte
				try{
					game.balde.draw(lista.imagem(i), -640 + 128 * (i - 1), 0); //----
				}catch(Exception ex){
					break; //Ele vai quebrar o for se a lista ainda n�o tiver sido criada
				}	

				font[i -1].draw(game.balde, lista.elemento(i), -640 + 60 + (128 * (i - 1)),	70);
				
				font2.draw(game.balde, String.valueOf(i+"*"), -690 + 50 + (128 * (i - 1)),	115);
				
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
		quadVazio.dispose();
		quadValido.dispose();
		System.out.println("fechou");
	}
	
	public static void sair() {
		exit = true;
	}
	
	/*
	 * Os pr�ximos dois m�todos s�o respons�veis por imprimirem
	 * na tela a posi��o que teve alguma altera��o
	 */
	
	public static void insereTela(int pos, String valor) {
		tiraPesquisa();
		try {
			//Verifica se o valor est� dentro do padr�o
			if((pos < 1) || (pos > 20)) {
				throw new Exception();
			}
			if(pos > aux) {
				if(count == lista.tamanho()){
					cheioS = true;
					throw new Exception();
				}
				throw new Exception();
			}
			
			int n = Integer.parseInt(valor);
			if(existeP(n)){
				existeS = true;
				throw new Exception();
			}
			
			existe[count] = n;
			if(lista.insere(pos, valor)){
				posRabo++;
				count++;
				aux++;
			}

		}
		catch(NumberFormatException nf){
				JOptionPane.showMessageDialog(null, "Conte�do apenas composto por n�meros!", 
										  "Error", ERROR_MESSAGE);
			
		}
		catch(Exception e) {
			if(existeS == false){
				if(cheioS){
					cheioS = false;
					JOptionPane.showMessageDialog(null, "Pr�xima posi��o " + aux + "!", 
		  					  "Error", ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Estrutura Cheia...Tente excluir algum valor antes!", 
							  "Error", ERROR_MESSAGE);
				}
				
			}
			if(existeS){
				existeS = false;
				JOptionPane.showMessageDialog(null, "Elemento j� existe na estrutura!", 
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
			JOptionPane.showMessageDialog(null, "N�o se pode remover o que n�o existe!", 
										  "Error", ERROR_MESSAGE);
		}		
	}
	
	
	public static void Pesquisa(String text) {
		pesquisa = text;
		try{
			int valorConvertido = Integer.parseInt(text);
			if(valorConvertido < 0) throw new Exception();
			for(int i = 0; i < existe.length; i++) {
				if(existe[i] == valorConvertido) {
					elementoExiste = true;
				}
			}
			
			if(elementoExiste == false) {
				throw new Exception();
			}
			
			elementoExiste = false;
			
			for(int i = 1; i <= lista.tamanho(); i++){				
				if(pesquisa.equals(String.valueOf(lista.elemento(i)))){	
				}else{				
					font[i - 1].setColor(Color.valueOf("b7b7b7"));
				}				
			}
			for(int j = 1; j <= lista.tamanho(); j++) {
				if(pesquisa.equals(lista.elemento(j))){
					font[j - 1].setColor(Color.valueOf("7fff00"));				
					break;
				}else{							
					font[j - 1].setColor(Color.valueOf("7fff00"));
					Thread.sleep(1000);
					font[j - 1].setColor(Color.valueOf("b7b7b7"));
				}
			}		
		}
		catch(NumberFormatException nf){
			JOptionPane.showMessageDialog(null, "A estrutura apenas possui n�meros!", 
					  "Error", ERROR_MESSAGE);
		} 
		
		catch (Exception e) {
			elementoExiste = false;
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel achar o valor inserido!", 
					  "Error", ERROR_MESSAGE);
		}
	}
	
	public static void tiraPesquisa(){//Esse M�todo ser� iniciado a cada a��o da lista, zerando a marca��o da pesquisa
		try{
			for(int i = 1; i <= lista.tamanho(); i++){				
				font[i - 1].setColor(Color.valueOf("b7b7b7"));
					
			}
			pesquisa = null;
			}catch(Exception g){				
			}
	}
	

	@Override
	public void input(String text) {
		try{
			if(isNumber(text)){
				int n = Integer.parseInt(text);
				existe = new int[n];
				
				for(int i = 0; i < existe.length; i++){
					existe[i] = -1;
				}
				
				lista = new ListaSeqGen(n,quadVazio, quadValido);
			}
			else
			{
				throw new Exception();
			}
		}catch(Exception n) {
			JOptionPane.showMessageDialog(null, "Estrutura Limitada! Apenas seram aceitos n�meros entre 1 e 20!", 
					"Error", 
					ERROR_MESSAGE);	
			Gdx.input.getTextInput(this, "Lista Sequencial", "", "Tamanho da estrutura");
		}	
	}

	public static boolean existeP(int valor){
		for(int i = 0; i < existe.length; i++){
			if(existe[i] == valor){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNumber(String text) throws Exception {
		int number = Integer.parseInt(text);
		if((number < 1) || (number > 20)){
			return false;
		}
			return true;
	}
	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
}
