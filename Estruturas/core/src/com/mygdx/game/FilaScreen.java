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

public class FilaScreen implements Screen, TextInputListener{
	
	private static Executor game;
	private OrthographicCamera camera;
	private Viewport port;
	private FilaHud hud;
	private Texture fundo;
	private static int elementos; //Total de elementos que serão mostrados na tela
	private static int posRabo;
	static Texture quadValido;
	static Texture quadVazio;
	static Texture inicio;
	static Texture fim;
	static FilaSeq fila;
	private static int posi[];
	 static BitmapFont font[];
	 static BitmapFont font2[];
	 static String pesquisa;
	 public static boolean exit;

	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public FilaScreen(Executor game){
		
		quadValido = new Texture("coisa/quadradoPreenchido.png");
		quadVazio = new Texture("coisa/quadradoVazio.png");
		inicio = new Texture("coisa/PonteiroInicio.png");
		fim = new Texture("coisa/PonteiroFim.png");
		Gdx.input.getTextInput(this, "Fila Sequencial", "", "Tamanho da estrutura");
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new FilaHud(game.balde, game);
		fundo = new Texture("coisa/FundoEstruturas.png");
		
		elementos = 0;
		posRabo = 0;
		fila = new FilaSeq(quadValido, quadVazio);
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
		
		if(exit) {
			this.dispose();
		}
		
		if(fila.fim() == -1) game.balde.draw(fim, -640, -180 ); //
		else game.balde.draw(fim, -640 + (128 * (fila.fim())), -180 ); //Fim irá ir circulando a lista conforme a posição retornada da fila
		for(int i = 0; i < elementos; i++) {
			game.balde.draw(fila.imagem(i), -640 + 35 +( 128 * i), 0); //Imprime na tela os quadrados associados a pilha
			font2[i].draw(game.balde, String.valueOf(i +"*"), -605 + 128 * i,	110); //Posições do array
			}
		game.balde.draw(inicio, -640 + (128 * fila.inicio()), 120);
		for(int i = 0; i < elementos; i++) {
			try {	
				if(fila.escrito(i)){ //Só mostra o que tem escrito, se aquele espaço já não tiver sido apagado
					font[posi[i]].draw(game.balde, fila.conteudo(i), -547  + 129 * (posi[i]), 70); 
				}
			
			}catch (Exception e) {
				
			}
		}
		for(int i = 0; i <= 21; i++) {
		//A baixo comparamos a string de conteudo com a string que recebemos do metodo de pesquisa,
		//	se for 	igual alteramos a cor da fonte
			try {
		if(pesquisa.equals(fila.conteudo(i))) {
			font[i + 1].setColor(Color.valueOf("7fff00"));
		}else {
			font[i + 1].setColor(Color.valueOf("b7b7b7"));
		}
			}catch (Exception f) {
			
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
		inicio.dispose();
		fim.dispose();
		quadVazio.dispose();
		quadValido.dispose();
		fundo.dispose();
		for(int i = 0; i <= 20; i++){
		font[i].dispose();		
		font2[i].dispose();
		}
	}
	
	public static void sair() {
		exit = true;		
	}

	@Override
	public void input(String text) {
		try {
			//Caso o resultado do isNumber() = true, ele confirma que o valor digitado está entre o padrão
			if(isNumber(text)) {
				elementos = Integer.parseInt(text);
				fila = new FilaSeq(elementos,quadValido, quadVazio);
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
			
			Gdx.input.getTextInput(this, "Fila Sequencial", "", "Tamanho da estrutura");
		}
	}

	@Override
	public void canceled() {
		
	}
	
	public static void insereTela(String valor) {
		
		try		
		{	
			//Gera exceção caso o usuário tente passar o número de elementos da pilha!
			//if((nElementos != 0) && (aux == elementos)) {
				//throw new Exception();
			//}
			int n = Integer.parseInt(valor); //Caso não for um número gera a Exceção NumberFormatException
			
			fila.insere(valor); //Inserimos na posição inicial um novo valor
			//Aumentamos a quantidade de quadrados que serão mostrados como adicionados ao usuário
			
			posRabo++;
			posi[posRabo] = posRabo;
		}
		catch(NumberFormatException nf) {
			JOptionPane.showMessageDialog(null, "Conteúdo apenas composto por números!", "Error", ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Posição Inválida! Digite uma posição entre 1 e " + elementos + "!", 
										  "Error", ERROR_MESSAGE);
		}
	}
	
	public static void removeTela() {
		
		try {
			String auxS = fila.remove();
			if(auxS.equals("null")) {
				throw new Exception(); //Essa exceção será lançada quando o usuário tentar excluir quando a fila ainda estiver vazia
			} 
			else
			{			
				posRabo--;
				posi[posRabo] = posRabo;
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Não se pode remover o que não existe!", 
										  "Error", ERROR_MESSAGE);
			game.setScreen(new FilaScreen(game));
		}
	
	  }
		
	//Esse metodo recebe um String de pesquisa
	public static void Pesquisa(String text){
		pesquisa = text;		
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
		
}
