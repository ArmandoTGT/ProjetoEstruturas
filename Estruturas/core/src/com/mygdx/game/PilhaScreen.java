package com.mygdx.game;

/*
 * 
 */

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
	private PilhaHud hud; // Interface de intera��o com o usuu�rio(hud) que fica encima da nossa screen
	
	private static int elementos; //Total de elementos que ser�o mostrados na tela
	private Texture quadValido;
	private Texture quadVazio;
	private Texture cabeca;
	static BitmapFont font[];
	static BitmapFont font2[];
	private static PilhaSeq pilha;
	private static int posi[];
	static int aux = 0;
	static String pesquisa;
	public static boolean exit;
	

	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public PilhaScreen(Executor game){
		exit = false;
		posRabo = 0;
		quadValido = new Texture("coisa/quadradoPilhaPreenchido.png");
		quadVazio = new Texture("coisa/quadradoPilhaVazio.png");
		cabeca = new Texture("coisa/PonteiroTopo.png");
		Gdx.input.getTextInput(this, "Lista Sequencial", "", "Tamanho da estrutura");
		pilha = new PilhaSeq(quadValido, quadVazio);
		posi = new int[21];

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
		
		elementos = 0;
		
	}

	
	public void show() {
		
		
	}

	
	public void render(float delta) {
		moveCamera(delta);
				
		Gdx.gl.glClearColor(64/255.0f, 102/255.0f, 128/255.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.balde.setProjectionMatrix(camera.combined);
		game.balde.begin();
		
			
			for(int i = 0; i < elementos; i++) {
				/*
				 * Como � uma pilha, foi desenhado na vertical, estando inicialmente
				 * tr�s blocos longe do centro e para alinhas com os bot�es superiores,
				 * alinhamos a posi��o horizontal em 64
				 */
				game.balde.draw(pilha.imagem(i), -64, -370 + 129 * (i));
				font2[i].draw(game.balde, String.valueOf((i + 1) +"*"), -50,	-250 + 129 * (i));
			}
			if(posRabo != 0) {
				game.balde.draw(cabeca, -285, -369 + 129 * (posRabo - 1));
				for(int i = 0; i < pilha.tamanho(); i++) { //Vai printar o conte�do at� o tamanho atual da pilha
					try {
											
					font[posi[i]].draw(game.balde, pilha.conteudo(i), -2,	-300 + 129 * (posi[i] - 1));
					
					}catch (Exception e) {
					
					}
				}
			}
		
		if(exit) {
			System.out.println("chegou");	
			this.dispose();
			
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
		cabeca.dispose();
		quadVazio.dispose();
		quadValido.dispose();
			
	}

	@Override
	/*
	 * Aqui o tamanho da lista ser� definido e 
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
			JOptionPane.showMessageDialog(null, "Estrutura Limitada! Apenas seram aceitos n�meros entre 1 e 20!", 
										"Error", 
										ERROR_MESSAGE);	
			Gdx.input.getTextInput(this, "Pilha Sequencial", "", "Tamanho da estrutura");
		}		
	}

	@Override
	public void canceled() {
		
	}
	
	public static void sair() {
		exit = true;		
	}
	
	/*
	 * Os pr�ximos dois m�todos s�o respons�veis por imprimirem
	 * na tela a posi��o que teve alguma altera��o
	 */
	
	public static void insereTela(String valor) {
		try {
			//Gera exce��o caso o usu�rio tente passar o n�mero de elementos da pilha!
			if((pilha.top() == "null") && (aux == elementos)) {
				throw new Exception();
			}
			
			int n = Integer.parseInt(valor);//Gera uam exce��o caso o valor do conte�do n�o for um inteiro
			pilha.push(valor);//Inserimos na posi��o inicial um novo valor
			//Aumentamos a quantidade de quadrados que ser�o mostrados como adicionados ao usu�rio
			posRabo++;
			posi[posRabo - 1] = posRabo;
			
		}
		catch(NumberFormatException nf){
			JOptionPane.showMessageDialog(null, "Conte�do apenas composto por n�meros!", 
										  "Error", ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Posi��o Inv�lida! Digite uma posi��o entre 1 e " + elementos + "!", 
										  "Error", ERROR_MESSAGE);
		}	
	}
	
	public static void removeTela() {
		try {
			String auxS = pilha.pop(); //Manter� o valor daquilo que acabou de ser excluido da pilha
			if((auxS.equals("null")) && (aux == 0)) {
				throw new Exception();
			} 
			else
			{
				System.out.println(auxS); //Removemos o valor salvo na �ltima posi��o
				//Diminuimos a quantidade de quadrados que ser�o mostrados como adicionados ao usuario
				posRabo--;
				aux--;
				posi[posRabo] = posRabo;
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "N�o se pode remover o que n�o existe!", 
										  "Error", ERROR_MESSAGE);
			game.setScreen(new PilhaScreen(game));
		}
	}
	
	public static void Pesquisa(String text){
		pesquisa = text;
		
		for(int i = 0; i <= 20; i++) {
			 
			
			font[i].setColor(Color.valueOf("b7b7b7"));
			  }
		
		
		
		for(int i = pilha.tamanho() -1 ; i >= 0; i--) {
			//A baixo comparamos a string de conteudo com a string que recebemos do metodo de pesquisa,
			//	se for 	igual alteramos a cor da fonte
				try {
					System.out.println(i);
			if(pesquisa.equals(pilha.conteudo(i))) {
				font[i + 1].setColor(Color.valueOf("7fff00"));
				break;
			}else {
				font[i + 1].setColor(Color.valueOf("7fff00"));
				Thread.sleep(1000);
				font[i + 1].setColor(Color.valueOf("b7b7b7"));
			}
				}catch (Exception f){
				System.out.println("catch");
			}
			}
	}
	
	
	/*
	 * M�todo que trata exce��o, apenas aceita a entrada de n�meros entre 1 e 20
	 */
	public boolean isNumber(String text) throws Exception {
		int number = Integer.parseInt(text);
		if((number < 1) || (number > 20)){
			return false;
		}
			return true;
	}
	
	
}
