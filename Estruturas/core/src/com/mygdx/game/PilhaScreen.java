package com.mygdx.game;


import static javax.swing.JOptionPane.ERROR_MESSAGE;

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

public class PilhaScreen implements Screen, TextInputListener{
	
	private static int posRabo;
	private static Executor game;
	private OrthographicCamera camera;
	private Viewport port;
	private PilhaHud hud; // Interface de interação com o usuuário(hud) que fica encima da nossa screen
	private Texture fundo;
	private static int elementos; //Total de elementos que serão mostrados na tela
	static Texture quadValido;
	static Texture quadVazio;
	static Texture cabeca;
	private static int posi[];
	private static String[] conteudo;
	static BitmapFont font[];
	static BitmapFont font2[];
	static String[] conteudoInvert;
	static int aux = 0;
	

	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public PilhaScreen(Executor game){
		posRabo = 0;
		quadValido = new Texture("coisa/quadradoPilhaPreenchido.png");
		quadVazio = new Texture("coisa/quadradoPilhaVazio.png");
		cabeca = new Texture("coisa/PonteiroTopo.png");
		Gdx.input.getTextInput(this, "Lista Sequencial", "", "Tamanho da estrutura");
		PilhaSeq();
		conteudo = new String[21];
		posi = new int[21];
		conteudoInvert = new String[21];

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
		
		  
		font2 = new BitmapFont[21];
		for(int i = 0; i <= 20; i++) {
			 
		font2[i] = generator2.generateFont(parameter);
		font2[i].setColor(Color.valueOf("b7b7b7"));
		  }
		generator2.dispose();
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new PilhaHud(game.balde, game);
		fundo = new Texture("coisa/FundoEstruturas.png");
		elementos = 0;
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
		game.balde.draw(fundo, -1980, -1020);
			
			for(int i = 1; i <= elementos; i++) {
				/*
				 * Como é uma pilha, foi desenhado na vertical, estando inicialmente
				 * três blocos longe do centro e para alinhas com os botões superiores,
				 * alinhamos a posição horizontal em 64
				 */
				game.balde.draw(image(i), -64, -370 + 129 * (i - 1)); 
				font2[i].draw(game.balde, String.valueOf(i+"*"), -50,	-250 + 129 * (i - 1));
				
			}
			if(posRabo != 0) {
				game.balde.draw(cabeca, -285, -369 + 129 * (posRabo - 1));
				for(int i = 0; i <= 20; i++) {
					try {
										
				font[posi[i]].draw(game.balde, conteudo[i], -2,	-300 + 129 * (posi[i] - 1));
				
				}
				catch (Exception e) {
				
				}
				}
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

	@Override
	/*
	 * Aqui o tamanho da lista será definido e 
	 * teremos um sinal que podemos desenhar a estrutura
	 */
	public void input(String text) {
		try {
			
			if(isNumber(text)) {
				elementos = Integer.parseInt(text);		
			}
			else 
			{
				throw new Exception();
			}
			
		}
		catch(Exception n) {
			JOptionPane.showMessageDialog(null, "Estrutura Limitada! Apenas seram aceitos números entre 1 e 20!", 
										"Error", 
										ERROR_MESSAGE);	
			Gdx.input.getTextInput(this, "Pilha Sequencial", "", "Tamanho da estrutura");
		}		
	}

	@Override
	public void canceled() {
		
	}
	
	/*
	 * Os próximos dois métodos são responsáveis por imprimirem
	 * na tela a posição que teve alguma alteração
	 */
	
	public static void insereTela(String valor) {
		try {
			//Gera exceção caso o usuário tente passar o número de elementos da pilha!
			if((topo != -1) && (aux == elementos)) {
				throw new Exception();
			}
			
			int n = Integer.parseInt(valor);//Gera uam exceção caso o valor do conteúdo não for um inteiro
			push(valor);//Inserimos na posição inicial um novo valor
			//Aumentamos a quantidade de quadrados que serão mostrados como adicionados ao usuário
			quads[posRabo] = quadValido;
			posRabo++;
			aux++;
			posi[posRabo - 1] = posRabo;
			conteudo[posRabo - 1] = valor;
			
		}
		catch(NumberFormatException nf){
			JOptionPane.showMessageDialog(null, "Conteúdo apenas composto por números!", 
										  "Error", ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Posição Inválida! Digite uma posição entre 1 e " + elementos + "!", 
										  "Error", ERROR_MESSAGE);
		}	
	}
	
	public static void removeTela() {
		try {
			if((pop() == "null") && (aux == 0)) {
				throw new Exception();
			} 
			else
			{
				System.out.println(pop()); //Removemos o valor salvo na última posição
				//Diminuimos a quantidade de quadrados que serão mostrados como adicionados ao usuario
				posRabo--;
				aux--;
				quads[posRabo] = quadVazio;
				posi[posRabo] = posRabo;
				conteudo[posRabo] = null;
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Não se pode remover o que não existe!", 
										  "Error", ERROR_MESSAGE);
			game.setScreen(new PilhaScreen(game));
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
	
	//-------------------------------PILHA SEQUENCIAL-------------------------------------------------
	
	private static String dados[]; // Vetor que contÃ©m os dados da lista 
	private static int topo; 
	private static int tamMax;
	private static Texture[] quads;

    static void PilhaSeq(){
    		tamMax = 20;
    		dados = new String[tamMax];
    		topo = -1;
    		quads = new Texture[20];
			for(int i = 0; i<20 ; i++) {
	    		quads[i] = new Texture("coisa/quadradoPilhaVazio.png");
	    	}
    	}

    /** Verifica se a Pilha estÃ¡ vazia */
    public static boolean vazia(){
    		if (topo == -1)
    			return true;
    	   else 
    	      return false;
	}
	
    /**Verifica se a Pilha estÃ¡ cheia */
    public static boolean cheia(){
        if (topo == (tamMax-1))
  		  return true;
      else
  		  return false;
	}
	
    /**ObtÃ©m o tamanho da Pilha*/
    public static int tamanho(){
		return topo+1;
	}
    
    /** Consulta o elemento do topo da Pilha.
		Retorna -1 se a pilha estiver vazia, 
		caso contrÃ¡rio retorna o valor que estÃ¡ no topo da pilha. */
 	public static String top () {
      if (vazia()) 
         return "null"; // pilha vazia
 	  
      return dados[topo];
 	}
     
	 /** Insere um elemento no topo da pilha.
	  Retorna false se a pilha estiver cheia. 
	  Caso contrÃ¡rio retorna true */
 	public static boolean push (String valor) {
 		if (cheia()) 
 			return false;  // err: pilha cheia 
 		
 		topo++;
 		dados[topo] = valor; 
 		return true;
	 }   

	 /** Retira o elemento do topo da pilha.
	  Retorna -1 se a pilha estiver vazia. */
 	public static String pop() {          
 		if (vazia()) 
 			return "null"; // Pilha vazia
 		
 		String valor = dados[topo]; 
 		topo--; 
 		return valor;
 	}
 	
    /*
     * O método foi implementado para trabalhar graficamente com essa classe
     * basicamente retorna o texture atualmente salvo na posição designada
     */
    public static Texture image(int pos) {
      	return quads[pos - 1];
    }
	
}
