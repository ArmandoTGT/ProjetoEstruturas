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
	private Texture quadEsquerda;
	private Texture quadDireita;
	public NoABP ultimoNo;
	public NoABP dad;
	
	public ABP(Texture quadRaiz, Texture quadDireita, Texture quadEsquerda){
		tamanho = 0;
		raiz = null;
		this.quadRaiz = quadRaiz; //Vai definir o texture quando ele está na raiz
		this.quadEsquerda = quadEsquerda; //Vai definir o texture que ele está a esquerda do no anterior
		this.quadDireita = quadDireita; //Vai definir o texture que ele está a direita do no anterior
	}
	
	/** Verifica se a arvore está vazia */
	public boolean vazia (){
		return (raiz == null);
	}
	
	/**Buscar recursivamente a partir da raiz.
	    Retorna o endereço se o elemento for
	    encontrado, caso contrario retorna NULL*/
	private NoABP busca(NoABP T, int valor) {
		if (T == null)
			return null;  // Arvore Vazia

		if(T.getConteudo() == valor)
			return T; 	// Ele vem encontrado na raiz
		
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
			System.out.println("Entrou aqui");
			novoNo.setDirecao(2);
			novoNo.setQuad(quadEsquerda); //Se o nó for colocado aqui, significa que ele está a esquerda
			p.setEsq(novoNo);
		}
		else {
			System.out.println("Entrou aqui");
			novoNo.setDirecao(1);
			novoNo.setQuad(quadDireita); //Se o nó for colocado aqui, significa que ele está a direita
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
