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

public class PilhaScreen implements Screen, TextInputListener{
	
	private static int posRabo;
	private Executor game;
	private OrthographicCamera camera;
	private Viewport port;
	private PilhaHud hud; // Interface de intera��o com o usuu�rio(hud) que fica encima da nossa screen
	private Texture fundo;
	private int elementos; //Total de elementos que ser�o mostrados na tela
	static Texture quadValido;
	static Texture quadVazio;
	static Texture cabeca;
	private static int posi[];
	private static String[] conteudo;
	static BitmapFont font[];
	static String[] conteudoInvert;
	

	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public PilhaScreen(Executor game){
		posRabo = 0;
		quadValido = new Texture("coisa/quadradoPreenchido.png");
		quadVazio = new Texture("coisa/quadradoVazio.png");
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
		font[i].setColor(Color.valueOf("646b6d"));
		  }
		generator.dispose();
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new PilhaHud(game.balde, game);
		fundo = new Texture("coisa/FundoEstruturas.png");
		elementos = 0;
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
				
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.balde.setProjectionMatrix(camera.combined);
		game.balde.begin();
		game.balde.draw(fundo, -1280, -720);
			
			for(int i = 1; i <= elementos; i++) {
				/*
				 * Como � uma pilha, foi desenhado na vertical, estando inicialmente
				 * tr�s blocos longe do centro e para alinhas com os bot�es superiores,
				 * alinhamos a posi��o horizontal em 64
				 */
				game.balde.draw(image(i), -64, -370 + 129 * (i - 1)); 
			}
			if(posRabo != 0) {
				game.balde.draw(cabeca, -291, -369 + 129 * (posRabo - 1));
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
		//Aqui o tamanho da lista ser� definido e teremos um sinal que podemos desenhar a estrutura
		elementos = Integer.parseInt(text);
		
		
		
	}

	@Override
	public void canceled() {
		
	}
	
	/*
	 * Os pr�ximos dois m�todos s�o respons�veis por imprimirem
	 * na tela a posi��o que teve alguma altera��o
	 */
	
	public static void insereTela( String valor) {
		push(valor);//Inserimos na posi��o inicial um novo valor
		//Aumentamos a quantidade de quadrados que ser�o mostrados como adicionados ao usu�rio
		quads[posRabo] = quadValido;
		posRabo++;
		
		posi[posRabo - 1] = posRabo;
		
		
		conteudo[posRabo - 1] = valor;
		
		
	}
	
	public static void removeTela() {
		System.out.println(pop()); //Removemos o valor salvo na �ltima posi��o
		//Diminuimos a quantidade de quadrados que ser�o mostrados como adicionados ao usuario
		posRabo--;
		quads[posRabo] = quadVazio;
		posi[posRabo] = posRabo;
		
		
		
		conteudo[posRabo] = null;
		
	}
	
	//-------------------------------PILHA SEQUENCIAL-------------------------------------------------
	
	private static String dados[]; // Vetor que contém os dados da lista 
	private static int topo; 
	private static int tamMax;
	private static Texture[] quads;

    static void PilhaSeq(){
    		tamMax = 20;
    		dados = new String[tamMax];
    		topo = -1;
    		quads = new Texture[20];
			for(int i = 0; i<20 ; i++) {
	    		quads[i] = new Texture("coisa/quadradoVazio.png");
	    	}
    	}

    /** Verifica se a Pilha está vazia */
    public static boolean vazia(){
    		if (topo == -1)
    			return true;
    	   else 
    	      return false;
	}
	
    /**Verifica se a Pilha está cheia */
    public static boolean cheia(){
        if (topo == (tamMax-1))
  		  return true;
      else
  		  return false;
	}
	
    /**Obtém o tamanho da Pilha*/
    public static int tamanho(){
		return topo+1;
	}
    
    /** Consulta o elemento do topo da Pilha.
		Retorna -1 se a pilha estiver vazia, 
		caso contrário retorna o valor que está no topo da pilha. */
 	public static String top () {
      if (vazia()) 
         return "null"; // pilha vazia
 	  
      return dados[topo];
 	}
     
	 /** Insere um elemento no topo da pilha.
	  Retorna false se a pilha estiver cheia. 
	  Caso contrário retorna true */
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
     * O m�todo foi implementado para trabalhar graficamente com essa classe
     * basicamente retorna o texture atualmente salvo na posi��o designada
     */
    public static Texture image(int pos) {
      	return quads[pos - 1];
    }
	
}
