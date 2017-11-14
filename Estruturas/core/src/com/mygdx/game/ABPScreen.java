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

public class ABPScreen implements Screen{
	
	private static Executor game;
	private static Random posicao; 
	private static int posicaoAux;
	private OrthographicCamera camera;
	private Viewport port;
	private ABPHud hud;
	private Texture fundo;
	//Atributos relacionados a construção gráfica da arvore
	private static ABP arvore;
	static Texture quadValido;
	static Texture setaDireita;
	static Texture setaEsquerda;
	static Texture raiz;
	private static int raizAux, indice, x, y, insereAux[];
	private static NoABP no;
	private static NoABP nos[];
	
	//Atributos relacionados a construção a fonte
	static BitmapFont font;
	static BitmapFont font2;
	static int cont;
	/*
	 * Todos os textures precisam ser construidos
	 * apenas, e somente apenas, no construtor
	 */
	public ABPScreen(Executor game){
		posicao = new Random();
		
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
		  font = new BitmapFont();					 
		  font = generator.generateFont(parameter);	
		  font.setColor(Color.valueOf("b7b7b7"));		  
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
			font.draw(game.balde, String.valueOf(arvore.raiz().getConteudo()), arvore.raiz().getX() + 50,	arvore.raiz().getY() + 70);
			
		}
		for(int i = 0; i < arvore.tamanho(); i++) {
			
			
				try {
					
				if(nos[i].getDirecao() ==  1)	{//Direita
					//System.out.println(arvore.busca(insereAux[i]).getPai().getConteudo());
					game.balde.draw(arvore.busca(insereAux[i]).getQuad(), arvore.busca(insereAux[i]).getPai().getX() +150 ,
							arvore.busca(insereAux[i]).getPai().getY() -150 );
					arvore.busca(insereAux[i]).setX(arvore.busca(insereAux[i]).getPai().getX() +150 );
					arvore.busca(insereAux[i]).setY(arvore.busca(insereAux[i]).getPai().getY() -150 );
					font.draw(game.balde, String.valueOf(arvore.busca(insereAux[i]).getConteudo()), 
							arvore.busca(insereAux[i]).getX() + 50,	arvore.busca(insereAux[i]).getY() + 70);
				}
				else if(nos[i].getDirecao() == 2) {//Esquerda
					//System.out.println(arvore.busca(insereAux[i]).getPai().getConteudo());
					game.balde.draw(arvore.busca(insereAux[i]).getQuad(), arvore.busca(insereAux[i]).getPai().getX() -150,
							arvore.busca(insereAux[i]).getPai().getY() -150);
					arvore.busca(insereAux[i]).setX(arvore.busca(insereAux[i]).getPai().getX() -150 );
					arvore.busca(insereAux[i]).setY(arvore.busca(insereAux[i]).getPai().getY() -150 );
					font.draw(game.balde, String.valueOf(arvore.busca(insereAux[i]).getConteudo()),
							arvore.busca(insereAux[i]).getX() + 50, arvore.busca(insereAux[i]).getY() + 70);
				}
			}catch(Exception e) {
				/*game.balde.draw(lista.imagem(i), -640 + 120 + 320 * (i - 1), 0); //----
				game.balde.draw(setaDireita, -640 + 120 + (128 * i) + (192 * (i -1)), 0);
				font.draw(game.balde, String.valueOf(lista.elemento(i)), -640 + 128 + 45 + (320 * (i - 1)),	70);
				font2.draw(game.balde, String.valueOf(i+"*"), -690 + 128 + 45 + (320 * (i - 1)),	115);*/
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
	
	public static void insereTela(int valor) {
		
		
		arvore.insere(valor);
		nos[cont] = arvore.ultimoNo;
		
		insereAux[cont] = valor;
		
		cont++;
	}
	/*
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

			game.setScreen(new ABPScreen(game));
		}				
	}
	*/
}
