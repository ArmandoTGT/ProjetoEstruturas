package com.mygdx.game;


import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.util.Random;

import javax.swing.JOptionPane;

import org.omg.CORBA.portable.ValueOutputStream;

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
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ABPScreen implements Screen{
	
	private static Executor game;	 
	private static int posicaoAux;
	private OrthographicCamera camera;
	private Viewport port;
	private ABPHud hud;
	private Texture fundo;
	//Atributos relacionados a construção gráfica da arvore
	static ABP arvore;
	static Texture quadValido;
	static Texture setaDireita;
	static Texture setaEsquerda;
	static Texture raiz;
	private static int raizAux, indice, x, y, insereAux[];
	private static NoABP no;
	private static NoABP nos[];
	
	//Atributos relacionados a construção a fonte
	static BitmapFont font[];
	static BitmapFont font2;
	static int cont;
	static int menos[];
	static String pesquisa;
	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public ABPScreen(Executor game){
		
		indice = 0;
		menos = new int [20];
		cont = 0;
		nos = new NoABP[20];
		
		insereAux = new int[20];
		posicaoAux = 0;
		quadValido = new Texture("coisa/BlocoEncadeado.png");
		//Precisa arrumar as setas e a raiz
		setaDireita = new Texture("coisa/BlocoEncadeado.png");
		setaEsquerda = new Texture("coisa/BlocoEncadeado.png");
		raiz = new Texture("coisa/PonteiroCabeça.png");
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
		  
		arvore = new ABP(quadValido,setaDireita,setaEsquerda);
		camera = new OrthographicCamera();
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, camera);
		this.game = game;
		hud = new ABPHud(game.balde, game);
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
		if(!arvore.vazia()) {
			
			arvore.raiz().setX(-200);
			arvore.raiz().setY(150);
			
			game.balde.draw(arvore.raiz().getQuad(), arvore.raiz().getX(), arvore.raiz().getY());
			
			font[0].draw(game.balde, String.valueOf(arvore.raiz().getConteudo()), arvore.raiz().getX() + 50,	arvore.raiz().getY() + 70);
			
		}
		for(int i = 1; i <= arvore.tamanho(); i++) {
			
			
				try {
					
				if(nos[i].getDirecao() ==  1)	{//Direita
						
					
					game.balde.draw(arvore.busca(insereAux[i]).getQuad(), arvore.busca(insereAux[i]).getPai().getX() +150 * contaD(arvore.busca(insereAux[i]).getProfundidade(), i) + menos[i],
							arvore.busca(insereAux[i]).getPai().getY() -150 );
					arvore.busca(insereAux[i]).setX(arvore.busca(insereAux[i]).getPai().getX() +150 * contaD(arvore.busca(insereAux[i]).getProfundidade(), i) + menos[i] );
				
					arvore.busca(insereAux[i]).setY(arvore.busca(insereAux[i]).getPai().getY() -150 );
					
					
					font[i].draw(game.balde, String.valueOf(arvore.busca(insereAux[i]).getConteudo()), 
							arvore.busca(insereAux[i]).getX() + 50,	arvore.busca(insereAux[i]).getY() + 70);
					indice = 1;	
				}
				else if(nos[i].getDirecao() == 2) {//Esquerda
					
					game.balde.draw(arvore.busca(insereAux[i]).getQuad(), arvore.busca(insereAux[i]).getPai().getX() -150 * contaD(arvore.busca(insereAux[i]).getProfundidade(), i) - menos[i],
							arvore.busca(insereAux[i]).getPai().getY() -150 );
						arvore.busca(insereAux[i]).setX(arvore.busca(insereAux[i]).getPai().getX() -150 * contaD(arvore.busca(insereAux[i]).getProfundidade(), i) - menos[i]);
					
					arvore.busca(insereAux[i]).setY(arvore.busca(insereAux[i]).getPai().getY() -150 );
					
					
					font[i].draw(game.balde, String.valueOf(arvore.busca(insereAux[i]).getConteudo()),
							arvore.busca(insereAux[i]).getX() + 50, arvore.busca(insereAux[i]).getY() + 70);
					indice = 2;	
				}
			}catch(Exception e) {
				
				} 
		}
		game.balde.end();
		hud.stage.act(delta);
		hud.stage.draw();
	}
	
	private int contaD(int prof, int i) {
		
		if(prof == 0) {
			return 0;
		}
		if(prof == 1) {
			return 4;
		}
		if(prof == 2) {
			return 2;
		}
		if(prof == 3) {
			return 1;
		}
		if(prof == 4) {
			menos[i] = 50;
			return 1;			
		}
		
		else {
			return 1;
		}
		
	}

	
	private void moveCamera(float dt) {
		setmoveCamera(dt);
		
		camera.update();
		
	}


	private void setmoveCamera(float dt) {
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			camera.zoom += 0.02;
			//if(camera.zoom > 1.8999991)camera.zoom = (float) 1.8999991;
		}
		if (Gdx.input.isKeyPressed(Keys.Q)) {
			camera.zoom -= 0.02;			
			if(camera.zoom < 0.30000037)camera.zoom = (float) 0.30000037;
		}
		
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) ) {
			camera.position.x -= 1000 * dt;			
			//if(camera.position.x < -616.67914)camera.position.x = (float) -616.67914;
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT) ){
			camera.position.x += 1000 * dt;
			//if(camera.position.x > 6326.621)camera.position.x = (float) 6326.621;
		}
		else if(Gdx.input.isKeyPressed(Keys.UP) ){
			camera.position.y += 1000 * dt;			
			//if(camera.position.y > 2267.0886)camera.position.y = (float) 2267.0886;
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN) ){
			camera.position.y -= 1000 * dt;				
			//if(camera.position.y < -183.99722)camera.position.y = (float) -183.99722;
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
		setaDireita.dispose();
		setaEsquerda.dispose();
		raiz.dispose();
		quadValido.dispose();
		fundo.dispose();
		for(int i = 0; i <= 20; i++){
		font[i].dispose();
		}
		font2.dispose();
		
		
	}
	
	public static void insereTela(int valor) {
		
		
		arvore.insere(valor);
		nos[cont] = arvore.ultimoNo;
		
		insereAux[cont] = valor;
		
		cont++;
	}
	


	public static void Pesquisa(String text) {
		pesquisa = text;
		arvore.buscaPesq(Integer.parseInt(text));
				
		for(int i = 0; i <= 20; i++) {
			font[i].setColor(Color.valueOf("b7b7b7"));
		}
			
		try {
			for(int i = 0; i < arvore.cont; i++){
			for(int j = 0; j <= arvore.tamanho(); j++) {
			if(arvore.busca(arvore.tent[i]).getConteudo() == arvore.busca(insereAux[j]).getConteudo()) {
				font[j].setColor(Color.valueOf("7fff00"));
				Thread.sleep(1000);
				font[j].setColor(Color.valueOf("b7b7b7"));
				break;
			}
			}
			
			}
			
			}catch(Exception f){
			
			}
		try {
			for(int i = 0; i <= arvore.tamanho(); i++) {
				if(pesquisa.equals(String.valueOf(arvore.busca(insereAux[i]).getConteudo()))){
					font[i].setColor(Color.valueOf("7fff00"));					
					break;
				}
			}
		}catch(Exception g){
			
		}
		arvore.cont = 0;
		for(int i = 0; i < 20; i++){
			arvore.tent[i] = 0;
		}
		
		
		
	}
}
