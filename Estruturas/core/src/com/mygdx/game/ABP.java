package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

class NoABP {
	private int conteudo;
	private NoABP esq;
	private NoABP dir;
	private Texture quad;
	private int direcao;
	private NoABP pai;
	private int x, y;
	private int profundidade;
	
	
	
	public int getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(int profundidade) {
		this.profundidade = profundidade;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public NoABP(){
		esq = null;
		dir = null;
	}
	
	public int getConteudo() {
		return conteudo;
	}
	public void setConteudo(int conteudo) {
		this.conteudo = conteudo;
	}
	
	
	public NoABP getEsq() {
		return esq;
	}
	public void setEsq(NoABP esq) {
		this.esq = esq;
	}
	
	public NoABP getDir() {
		return dir;
	}
	
	public void setDir(NoABP dir) {
		this.dir = dir;
	}
	
	//Esses dois métodos dará o valor gráfico para cada nó
	public Texture getQuad() {
		return quad;
	}

	public void setQuad(Texture quad) {
		this.quad = quad;
	}
	
	/*
	 * Esse método será muito útil na hora de desenharmos nossa estrutura
	 * 0 - No meio, esse será o caso da raiz
	 * 1 - Na Direita
	 * 2 - Na Esquerda
	 */
	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}
	
	 /*
	  * Esses dois métodos serão responsáveis para irmos imprimindo
	  * os filhos, pegando como referência o pai e vendo se este quer que o próximo seja impresso na esquerda ou na direita
	  */
	public NoABP getPai() {
		return pai;
	}

	public void setPai(NoABP pai) {
		this.pai = pai;
	}
}

public class ABP{
	private NoABP raiz;
	private int tamanho;
	private Texture quadRaiz;
	private Texture quadEsquerda1;
	private Texture quadDireita1;
	private Texture quadEsquerda2;
	private Texture quadDireita2;
	private Texture quadEsquerda3;
	private Texture quadDireita3;
	private Texture quadEsquerda4;
	private Texture quadDireita4;
	public NoABP ultimoNo;
	public NoABP dad;
	public int tent[];
	public int cont;
	public boolean pesquisou;
	
	public ABP(Texture quadRaiz, Texture quadDireita1, Texture quadEsquerda1, Texture quadDireita2, Texture quadEsquerda2,
			Texture quadDireita3, Texture quadEsquerda3, Texture quadDireita4, Texture quadEsquerda4){
		pesquisou = false;
		cont = 0;
		tent = new int[20];
		tamanho = 0;
		raiz = null;
		this.quadRaiz = quadRaiz; //Vai definir o texture quando ele está na raiz
		this.quadEsquerda1 = quadEsquerda1; //Vai definir o texture que ele está a esquerda do no anterior
		this.quadDireita1 = quadDireita1;
		this.quadEsquerda2 = quadEsquerda2; //Vai definir o texture que ele está a esquerda do no anterior
		this.quadDireita2 = quadDireita2;//Vai definir o texture que ele está a direita do no anterior
		this.quadEsquerda3 = quadEsquerda3; //Vai definir o texture que ele está a esquerda do no anterior
		this.quadDireita3 = quadDireita3;
		this.quadEsquerda4 = quadEsquerda4; //Vai definir o texture que ele está a esquerda do no anterior
		this.quadDireita4 = quadDireita4;
		
	}
	
	/** Verifica se a arvore está vazia */
	public boolean vazia (){
		return (raiz == null);
	}
	
	
	private NoABP buscaPesq(NoABP T, int valor) {
	
		if (T == null)
			return null;  // Arvore Vazia
		
		tent[cont] = T.getConteudo();
		cont++;
		
		
		if(T.getConteudo() == valor) {
			
			return T; 	// Ele vem encontrado na raiz
		}
		if (valor < T.getConteudo())
			return buscaPesq(T.getEsq(), valor);
	    else
			return buscaPesq(T.getDir(), valor);
	}
	
	
	/**Buscar recursivamente a partir da raiz.
	    Retorna o endereço se o elemento for
	    encontrado, caso contrario retorna NULL*/
	private NoABP busca(NoABP T, int valor) {
		
		if (T == null)
			return null;  // Arvore Vazia
		
		if(T.getConteudo() == valor) {			
			return T; 	// Ele vem encontrado na raiz
		}
		if (valor < T.getConteudo())
			return busca(T.getEsq(), valor);
	    else
			return busca(T.getDir(), valor);
	}
	
	/**Buscar um elemento na ABP
    		Retorna o endereço se o elemento for
    		encontrado, caso contrário retorna NULL*/
	public NoABP busca(int valor) {          
		if (raiz != null) 
			return busca(raiz, valor);
		
		return null;
	}
	
	public NoABP buscaPesq(int valor) {          
		if (raiz != null) 
			return buscaPesq(raiz, valor);
		
		return null;
	}
	
	

	/**Exibe o conteudo de uma arvore no formato in-ordem
	    (preserva a ordenação)*/
	private void exibe (NoABP T){
		if (T != null) {
			exibe(T.getEsq());
			System.out.print(" " + T.getConteudo());
			exibe(T.getDir());
		}
	}

	public void exibe() {
		if (raiz == null)
			System.out.println("Arvore vazia");
		else
			exibe(raiz);
	}
	
	/**Insere um no em uma arvore ABP
	    Retorna 1 se a inserção for com sucesso.
	    Caso contrario retorna 0*/
	public void insere(int valor){		
		
		NoABP novoNo = new NoABP();
		novoNo.setConteudo(valor);
		novoNo.setEsq(null);
		novoNo.setDir(null);
		tamanho++;

		if (raiz == null){ // Arvore vazia
			novoNo.setPai(null);
			novoNo.setDirecao(0);
			novoNo.setProfundidade(0);
			novoNo.setQuad(quadRaiz);
	 		raiz = novoNo;
			return;
		}

	    // Procura a posicao correta pra inserir o novo no
	    NoABP aux = raiz;
	    NoABP p = null;
	    while (aux != null) {
	    		p = aux;
			if (valor < aux.getConteudo())
				aux = aux.getEsq();
			else
				aux = aux.getDir();
		}

		novoNo.setPai(p);
		dad = p;
		// Encontrou um no folha para inserir
		if (valor < p.getConteudo()) {
			//Primeiro vamos definir a profundidade em que estará esse filho
			int profundidadePai = p.getProfundidade();
			profundidadePai++;
			novoNo.setProfundidade(profundidadePai);
			//Agora a sua direção e quadrado correspondente
			novoNo.setDirecao(2);
			if(novoNo.getProfundidade() == 0) {
				novoNo.setQuad(quadRaiz);				
				}
			else if(novoNo.getProfundidade() == 1) {
				novoNo.setQuad(quadEsquerda1);				
				}
			else if(novoNo.getProfundidade() == 2) {
				novoNo.setQuad(quadEsquerda2);				
				}
			else if(novoNo.getProfundidade() == 3) {
				novoNo.setQuad(quadEsquerda3);
				}
			else if(novoNo.getProfundidade() == 4) {
				novoNo.setQuad(quadEsquerda4);
				}
			else{
				novoNo.setQuad(quadEsquerda3);
				}//Se o nó for colocado aqui, significa que ele está a esquerda
			//Finalmente o novoNo está preparado para ser inserido na arvore
			p.setEsq(novoNo);
		}
		else {
			//Primeiro vamos definir a profundidade em que estará esse filho
			int profundidadePai = p.getProfundidade();
			profundidadePai++;
			novoNo.setProfundidade(profundidadePai);
			//Agora a sua direção e quadrado correspondente
			novoNo.setDirecao(1);
			if(novoNo.getProfundidade() == 0) {
				novoNo.setQuad(quadRaiz);
				}
			else if(novoNo.getProfundidade() == 1) {
				novoNo.setQuad(quadDireita1);
				}
			else if(novoNo.getProfundidade() == 2) {
				novoNo.setQuad(quadDireita2);
				}
			else if(novoNo.getProfundidade() == 3) {
				novoNo.setQuad(quadDireita3);
				}
			else if(novoNo.getProfundidade() == 4) {
				novoNo.setQuad(quadDireita4);
				}
			else{
				novoNo.setQuad(quadDireita3);
				} //Se o nó for colocado aqui, significa que ele está a direita
			//Finalmente o novoNo está preparado para ser inserido na arvore
			p.setDir(novoNo);
		}
		ultimoNo = novoNo;
		
		return;
	}
	
	public NoABP raiz() {
		return raiz;
	}
	
	//Esse método retornará a quantidade de nós feitos nessa arvore
	public int tamanho() {
		return tamanho;
	}
}
