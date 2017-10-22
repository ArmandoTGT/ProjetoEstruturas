package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ListaSeqScreen implements Screen, TextInputListener{
	
	private Executor game;
	 private OrthographicCamera camera;
	 private Viewport port;
	 private ListaSeqHud hud;
	 private Texture fundo;
	 private int elementos; //Total de elementos que serão mostrados na tela
	 static Texture quadValido;
	 static Texture quadVazio;
	 static BitmapFont font[];
	 /*
	  * Todos os textures precisam ser construidos
	  * apenas, e somente apenas, no construtor
	  */
	 public ListaSeqScreen(Executor game){
	  FileHandle caminho = new FileHandle("coisa/font.ttf");
	  FreeTypeFontGenerator generator = new FreeTypeFontGenerator(caminho);
	  FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	  parameter.size = 20;
	  
	  font = new BitmapFont[20];
	  for(int i = 0; i < 20; i++) {
		 
	  font[i] = generator.generateFont(parameter);
	  font[i].setColor(Color.valueOf("646b6d"));
	  }
	  generator.dispose();
	  
	  quadValido = new Texture("coisa/quadradoPreenchido.png");
	  quadVazio = new Texture("coisa/quadradoVazio.png");
	  Gdx.input.getTextInput(this, "Lista Sequencial", "", "Tamanho da estrutura");
	  ListaSeqGen();
	  camera = new OrthographicCamera();
	  port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
	  this.game = game;
	  hud = new ListaSeqHud(game.balde, game);
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
		game.balde.draw(fundo, -1280, -720);
			
			for(int i = 1; i <= elementos; i++) {
				game.balde.draw(image(i), -640 + 129 * (i - 1), 0); //----
				font[i].draw(game.balde, "teste", -640 + 45 + 129 * (i - 1),	70);
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
	
	/*
	 * Os próximos dois métodos são responsáveis por imprimirem
	 * na tela a posição que teve alguma alteração
	 */
	
	public static void insereTela(int pos, String valor) {
		insere(pos, valor);
		quads[pos - 1] = quadValido;
	}
	
	public static void removeTela(int pos) {
		remove(pos);
		quads[pos - 1] = quadVazio;
	}
	
	//-------------------------------LISTA SEQUENCIAL-------------------------------------------------
	
		private static String dados[]; // Vetor que contem os dados da lista 
		private static int nElementos; 
		private static int tamMax;
		private static Texture[] quads; // -----------
		
	  
	    public void ListaSeqGen(){
	    	tamMax = 20;
	    	nElementos = 0;
			dados = new String [tamMax];
	    	quads = new Texture[20];
	    	for(int i = 0; i<20 ; i++) {
	    		quads[i] = new Texture("coisa/quadradoVazio.png");
	    	}
	    }

	    /** Verifica se a Lista está vazia */
	    public boolean vazia(){
			if (nElementos == 0 )
				return true;
			else
				return false;
		}
		
	    /**Verifica se a Lista estÃ¡ cheia */
	    public static boolean cheia(){
			if (nElementos == tamMax)
				return true;
			else
				return false;
		}
		
	    /**Obtem o tamanho da Lista*/
	    public static int tamanho(){
			return nElementos;
		}
	    
	    /** Obtem o i-nesimo elemento de uma lista.
	    		Retorna -1 se a posiÃ§Ã£o for invÃ¡lida. */
	    public static String elemento(int pos){
	        
	    	/* Se posição estiver fora dos limites <= 0 
	         * ou > tamanho da lista */
	        if ((pos > nElementos) || (pos <= 0))
	            return null;

	       return dados[pos-1];
		}
	    
	    /*
	     * O método foi implementado para trabalhar graficamente com essa classe
	     * basicamente retorna o texture atualmente salvo na posição designada
	     */
	    public static Texture image(int pos) {
	      	return quads[pos - 1];
	    }
	    
	    /**	Retorna a posição de um elemento pesquisado.
	    		Retorna -1 caso não seja encontrado */
		public static int posicao (String valor){
		    /* Procura elemento a elemento, se o dado está na
		    		lista. Se estiver, retorna a sua posição no array+1 */
		    for (int i = 0; i < nElementos; i++){
		        if (dados[i].equals(valor)){
		            return (i + 1);
		        }
		    }

		    return -1;
		}
		
		/**	Retorna a posição de um elemento pesquisado.
		Retorna -1 caso não seja encontrado */
		public static int posicao (String valor, int desloc){
			/* Procura elemento a elemento, se o dado esá na
			lista. Se estiver, retorna a sua posição no array+1 */
			for (int i = desloc+1; i < nElementos; i++){
			    if (dados[i].equals(valor)){
			        return (i + 1);
			    }
			}
			
			return -1;
		}
		
		/**Insere um elemento em uma determinada posição
	    		Retorna false se a lista estiver cheia ou
	    		a posição for invalida. Caso contrario retorna true */
		public static boolean insere (int pos, String dado){
		    /* Verifica se a lista estÃ¡ cheia ou se a posicao a ser
		    inserida eh invalida (i.e., > tamanho da lista+1*/
		    if (cheia() || (pos > nElementos+1) || (pos <=0)){
		        return false;
		    }

		    /* Desloca os elementos ap pos, uma posicao a
		    direita. Isso serve para abrir espaço para insercao
		    do novo elemento */
		    for (int i = nElementos; i >= pos; i--){
		 		 dados[i] = dados[i-1];
		    }

		    /* Insere o dado na posicao correta */
		    dados[pos - 1] = dado;
		 	/* Incrementa o numero de elementos na lista */
		    nElementos++;
		    return true;
		}
		
		/**Remove um elemento de uma determinada posição
	    Retorna o valor do elemento removido. -1 caso a remoÃ§Ã£o falhe  */
		public static String remove(int pos){
			String dado;
			/* Verifica se a posicao eh valida */
		    if ((pos > nElementos) || (pos < 1 ))
				   return null;

		    /* Armazena o dado a ser removido na var "dado" */
		    dado = dados[pos-1];

		    /* Desloca todos os elementos apÃ³s 'pos', uma
		    posicao a esquerda */
		    for (int i = pos - 1; i < nElementos - 1; i++){
		 		  dados[i] = dados[i+1];
			 }

		   /* Decrementa o numero de elementos na lista */
		    nElementos--;
		    return dado;
		}

	
}
