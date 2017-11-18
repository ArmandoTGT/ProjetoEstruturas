package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class PilhaSeq {
	private String dados[]; // Vetor que contém os dados da lista 
	private int topo; 
	private int tamMax;
	private Texture[] quads;
	private Texture quadValido, quadVazio;
    
    public PilhaSeq(Texture quadValido, Texture quadVazio){
    		tamMax = 100;
    		dados = new String[tamMax];
    		topo = -1;
    		quads = new Texture[tamMax];
    		this.quadValido = quadValido;
    		this.quadVazio = quadVazio;
			for(int i = 0; i<tamMax ; i++) {
	    		quads[i] = quadVazio;
	    	}
    	}
    
    public PilhaSeq(int n, Texture quadValido, Texture quadVazio){
    		tamMax = n;
    		dados = new String[tamMax];
    		topo = -1;
    		quads = new Texture[tamMax];
    		this.quadValido = quadValido;
    		this.quadVazio = quadVazio;
			for(int i = 0; i<tamMax ; i++) {
	    		quads[i] = quadVazio;
	    	}
    }

    /** Verifica se a Pilha está vazia */
    public boolean vazia(){
    		if (topo == -1)
    			return true;
    	   else 
    	      return false;
	}
	
    /**Verifica se a Pilha está cheia */
    public boolean cheia(){
        if (topo == (tamMax-1))
  		  return true;
      else
  		  return false;
	}
	
    
    
    /**Obtem o tamanho da Pilha*/
    public int tamanho(){
		return topo+1;
	}
    
    public Texture imagem(int pos){
    	return quads[pos];
    }
    /** Consulta o elemento do topo da Pilha.
		Retorna -1 se a pilha estiver vazia, 
		caso contrÃ¡rio retorna o valor que estÃ¡ no topo da pilha. */
 	public String top () {
      if (vazia()) 
         return "null"; // pilha vazia
 	  
      return dados[topo];
 	}
     
	 /** Insere um elemento no topo da pilha.
	  Retorna false se a pilha estiver cheia. 
	  Caso contrÃ¡rio retorna true */
 	public boolean push (String valor) {
 		if (cheia()) 
 			return false;  // err: pilha cheia 
 		topo++;
 		quads[topo] = quadValido;
 		dados[topo] = valor; 
 		return true;
	 }   

	 /** Retira o elemento do topo da pilha.
	  Retorna -1 se a pilha estiver vazia. */
 	public String pop() {          
 		if (vazia()) return "null"; // Pilha vazia
 		
 		String valor = dados[topo]; 
 		quads[topo] = quadVazio;
 		topo--; 
 		return valor;
 	}
 	
 	/*
 	 * Para representar graficamente a nossa pilha, nos acabamos retornando todos os
 	 * elementos na pilha, sem alterar em nada sua estrutura
 	 */
 	
 	public String conteudo(int pos){
 		return dados[pos];
 	}

}
