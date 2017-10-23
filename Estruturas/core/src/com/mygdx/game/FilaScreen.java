package com.mygdx.game;


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

public class FilaScreen implements Screen, TextInputListener{
	
	private Executor game;
	private OrthographicCamera camera;
	private Viewport port;
	private FilaHud hud;
	private Texture fundo;
	private int elementos; //Total de elementos que serão mostrados na tela
	private static int posRabo;
	static Texture quadValido;
	static Texture quadVazio;
	static Texture cabeca;
	static Texture rabo;
	private static int posi[];
	private static String[] conteudo;
	 static BitmapFont font[];
	 static BitmapFont font2[];
	 static String[] conteudoInvert;

	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public FilaScreen(Executor game){
		posRabo = 0;
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
		font[i].setColor(Color.valueOf("646b6d"));
		  }
		generator.dispose();
		
		 FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(caminho);
		  FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
		  parameter2.size = 20;
			
			  
		  font2 = new BitmapFont[21];
		  for(int i = 0; i <= 20; i++) {
				 
			  font2[i] = generator2.generateFont(parameter);
			  font2[i].setColor(Color.valueOf("646b6d"));
			 }
		  generator2.dispose();
		  
		quadValido = new Texture("coisa/quadradoPreenchido.png");
		quadVazio = new Texture("coisa/quadradoVazio.png");
		cabeca = new Texture("coisa/PonteiroInicio.png");
		rabo = new Texture("coisa/PonteiroFim.png");
		Gdx.input.getTextInput(this, "Fila Sequencial", "", "Tamanho da estrutura");
		FilaSeq();
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new FilaHud(game.balde, game);
		fundo = new Texture("coisa/FundoEstruturas.png");
		elementos = 0;
		
		
	}

	
	public void show() {
		
		
	}

	
	public void render(float delta) {
		moveCamera(delta);
				
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.balde.setProjectionMatrix(camera.combined);
		game.balde.begin();
		game.balde.draw(fundo, -1280, -720);
		game.balde.draw(cabeca, -640, 120);
		for(int i = 1; i <= elementos; i++) {
				game.balde.draw(image(i), -640 + 105 +( 128 * (i - 1)), 0); //----
				font2[i].draw(game.balde, String.valueOf(i +"*"), -640 + 160 + 129 * (i - 1),	150);
			}
		if(posRabo != 0) { 
			game.balde.draw(rabo, -640 + 147 + (128 * (posRabo - 1)), -184);
			for(int i = 0; i <= 20; i++) {
				try {
									
			font[posi[i]].draw(game.balde, conteudoInvert[i -1], -640 + 35 + 129 * (posi[i]),	70);
			
			}
			catch (Exception e) {
			
			}
			}
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

	@Override
	public void input(String text) {
		//Aqui o tamanho da lista será definido e teremos um sinal que podemos desenhar a estrutura
		elementos = Integer.parseInt(text);
	}

	@Override
	public void canceled() {
		
	}
	
	public static void insereTela(String valor) {
		insere(valor); //Inserimos na posição inicial um novo valor
		//Aumentamos a quantidade de quadrados que serão mostrados como adicionados ao usuário
		quads[posRabo] = quadValido;
		posRabo++;
		
		posi[posRabo] = posRabo;
		String[] aux1 = new String[21];
		
		conteudo[posRabo] = valor;
		aux1 = conteudo;
		for(int i = 0; i <= posRabo; i++) {
			conteudoInvert[posRabo -i] = aux1[i];
			
			
			}
		}
	
	public static void removeTela() {
		System.out.println(remove()); //Removemos o valor salvo na última posição
		//Diminuimos a quantidade de quadrados que serão mostrados como adicionados ao usuario
		posRabo--;
		quads[posRabo] = quadVazio;
		posi[posRabo] = posRabo;
				
		String[] aux1 = new String[21];
		
		conteudoInvert[posRabo] = null;
		aux1 = conteudoInvert;
		for(int i = 0; i <= posRabo; i++) {
			conteudo[posRabo -i] = aux1[i];
						
		 }
	  }
	
	//-------------------------------PILHA SEQUENCIAL-------------------------------------------------
		private static String dados[];
		private static int inicio;
		private static int fim;
		private static int nElementos;
		private static int tamMax;
		private static Texture[] quads;
		
		public static void FilaSeq() {
			inicio = 0;
			fim = -1;
			nElementos = 0;
			tamMax = 20;
			dados =  new String[tamMax];
			quads = new Texture[20];
			for(int i = 0; i<20 ; i++) {
	    		quads[i] = new Texture("coisa/quadradoVazio.png");
	    	}
		}

		/** Verifica se a Fila estÃ¡ vazia */
		public static boolean vazia () {
			if (nElementos == 0)
				return true;
			else
				return false;
		}

		/**Verifica se a Fila estÃ¡ cheia */
		public static boolean cheia () {
			if (nElementos == tamMax)
				return true;
			else
				return false;
		}

		/** ObtÃ©m o tamanho da Fila */
		public static int tamanho() {
			return nElementos;
		}

		/** Consulta o elemento do inÃ­cio da fila.
		    Retorna -1 se a fila estiver vazia. */
		public static String primeiro() {
			if (vazia())
				return "null"; // Erro: Fila vazia 
			
			return dados[inicio];
		}

		/**Insere um elemento no fim de uma fila
	    Retorna false se a fila estiver cheia, true caso contrÃ¡rio. */
		public static boolean insere(String valor) {
			if (cheia()){
				return false;
			}
		
			fim = (fim + 1) % tamMax; // Circularidade 
		    dados[fim] = valor;
			nElementos++;
			return true;
		}

		/**Remove o elemento do inÃ­cio da fila e retorna o valor removido.
		    Retorna -1 se a fila estiver vazia.*/
		public static String remove() {
			if (vazia())
				return "null";
		
			String res = primeiro();
			inicio = (inicio + 1) % tamMax; //Circularidade 
			nElementos--;
			return res;
		}
		
	    /*
	     * O método foi implementado para trabalhar graficamente com essa classe
	     * basicamente retorna o texture atualmente salvo na posição designada
	     */
	    public static Texture image(int pos) {
	      	return quads[pos - 1];
	    }
}
