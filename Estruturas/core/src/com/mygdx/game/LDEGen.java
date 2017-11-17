package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class LDEGen<T> {
	
	class No {
		private No ant;
		private T conteudo;
		private No prox;
		private Texture quad;
		
		public No(){
			setProx(null);
		}

		public T getConteudo() {
			return conteudo;
		}

		public void setConteudo(T conteudo) {
			this.conteudo = conteudo;
		}

		public No getProx() {
			return prox;
		}

		public void setProx(No prox) {
			this.prox = prox;
		}

		public No getAnt() {
			return ant;
		}

		public void setAnt(No ant) {
			this.ant = ant;
		}

		public Texture getQuad() {
			return quad;
		}

		public void setQuad(Texture quad) {
			this.quad = quad;
		}
	}
	
	private No inicio;
	private No fim;
	private int tamanho;
	private Texture quadCheio;
	private Texture quadVazio;
	
	public LDEGen(Texture quadCheio, Texture quadVazio){
		this.quadCheio = quadCheio;
		this.quadVazio = quadVazio;
		inicio = null;
		fim = null;		
		tamanho = 0;
	}
	
	/** Verifica se a Lista está vazia */
	public boolean vazia() {
	    if (tamanho == 0)
	        return true;
	    else
	        return false;
	}

	/**Obtém o tamanho da Lista*/
	public int tamanho() {
	    return tamanho;
	}

	/** Obtém o i-ésimo elemento de uma lista
	    Retorna o valor encontrado. */
	public T elemento (int pos) {
	    
		No aux = inicio;
		 No aux2 = fim;
		 No foi = null;;
		int cont = 1;

	    if (vazia()) {
	        return null; // Consulta falhou 
	    }

	    if ((pos < 1) || (pos > tamanho())){
	        return null; // Posicao invalida 
	    }
	    if(pos > tamanho()/2){
	    	while (cont >= pos){
		        // modifica "aux" para apontar para o proximo elemento da lista 
		        aux2 = aux2.getAnt();
		        cont--;
		        foi = aux2;
		    }
	    }else{
	    // Percorre a lista do 1o elemento até pos 
	    while (cont <= pos){
	        // modifica "aux" para apontar para o proximo elemento da lista 
	        aux = aux.getProx();
	        cont++;
	        foi = aux;
	          }  	    
	    }
	    return foi.getConteudo();
		

	   
	}
	
	public Texture imagem(int pos) {
	    No aux = inicio;
	   
	    int cont = 1;

	    if (vazia()) {
	        return null; // Consulta falhou 
	    }

	    if ((pos < 1) || (pos > tamanho())){
	        return null; // Posicao invalida 
	    }
	    
	   

	    // Percorre a lista do 1o elemento até pos 
	    while (cont < pos){
	        // modifica "aux" para apontar para o proximo elemento da lista 
	        aux = aux.getProx();
	        cont++;
	        
	    }
	        return aux.getQuad();
	    
	    
		

	   
	}

	/**Retorna a posição de um elemento pesquisado.
	    Retorna 0 caso não seja encontrado */
	public int posicao (T dado) {
	    int cont = 1;
	    No aux;

	    /* Lista vazia */
	    if (vazia()) {
	        return -1;
	    }

	    /* Percorre a lista do inicio ao fim até encontrar o elemento*/
	    aux = inicio;
		while (aux != null) {
	        /* Se encontrar o elemento, retorna sua posicao n;*/
	        if (aux.getConteudo() == dado){
	            return cont;
	        }

	        /* modifica "aux" para apontar para o proximo elemento da lista */
	        aux = aux.getProx();
	        cont++;
	    }

	    return -1;
	}

	/** Insere nó em lista vazia */
	private int insereInicioLista(T valor) {
		// Aloca memoria para novo no 
	    No novoNo = new No();
	    
	    // Insere novo elemento na cabeca da lista
	    novoNo.setConteudo(valor);
	    novoNo.setProx(inicio);
	    novoNo.setQuad(quadCheio);
	    novoNo.setAnt(null); // Nova instrucao
	    if (vazia())
    			fim = novoNo; // Nova instrucao
	    else
    			inicio.setAnt(novoNo); // Nova instrucao	    
	    
	    inicio = novoNo;
	    tamanho++;
	    return 1;
	}

	/** Insere nó no meio da lista */
	private int insereMeioLista(int pos, T dado){
	    int cont = 1;

	    // Aloca memoria para novo no
	    No novoNo = new No();
	    novoNo.setConteudo(dado);
	    novoNo.setQuad(quadCheio);
	    // Localiza a pos. onde será inserido o novo nó
	    No aux = inicio;
	    while ((cont < pos-1) && (aux != null)){
	          aux = aux.getProx();
	          cont++;
	    }

	    if (aux == null) {  // pos. inválida 
	    		return -1;
	    }

	    // Insere novo elemento apos aux
	    novoNo.setAnt(aux); // Nova instrucao
	    novoNo.setProx(aux.getProx());
	    
	    aux.getProx().setAnt(novoNo); // Nova instrucao
	    
	    aux.setProx(novoNo);

	    tamanho++;
	    return 2;
	}

	/** Insere nó no fim da lista */
	private int insereFimLista(T dado){
	    // Aloca memoria para novo no 
	    No novoNo = new No();
	    novoNo.setConteudo(dado);
	    novoNo.setQuad(quadCheio);
	    // Procura o final da lista 
	    No aux = inicio;
	    while(aux.getProx() != null){
	        aux = aux.getProx();
	    }

	    novoNo.setProx(null);
	    aux.setProx(novoNo);
	    
	    novoNo.setAnt(fim);  // Nova instrucao
	    fim.setProx(novoNo); // Nova instrucao
	    fim = novoNo; 		// Nova instrucao
	    
	    this.tamanho++;
	    return 3;
	}

	/**Insere um elemento em uma determinada posição
	    Retorna true se conseguir inserir e 
	    false caso contrario */
	public int insere(int pos, T dado) {
		if ((vazia()) && (pos != 1)){
	        return -1; /* lista vazia mas posicao inv*/
	    }

	 	/* inserção no início da lista (ou lista vazia)*/
	    if (pos == 1){
	        return insereInicioLista(dado);
	    }
	    /* inserção no fim da lista */
	    else if (pos == tamanho+1){
	        return insereFimLista(dado);
	   }
	   /* inserção no meio da lista */
	   else{
	        return insereMeioLista(pos, dado);
	   }
	}

	// Remove elemento do início de uma lista unitária
	private T removeInicioListaUnitaria(){          
	    T dado = inicio.getConteudo();
	    inicio = null;
	    fim = null; 
	    tamanho--;
	    return dado;
	}
	
	/** Remove elemento do início da lista */
	private T removeInicioLista(){
	    No p = inicio;

	    // Dado recebe o dado removido
	    T dado = p.getConteudo();

	    // Retira o 1o elemento da lista (p)
	    inicio = p.getProx();
	    p.getProx().setAnt(null);  // Nova instrucao
	    
	    tamanho--;

	    // Sugere ao garbage collector que libere a memoria
	    //  da regiao apontada por p
	    p = null;

	    return dado;
	}

	/** Remove elemento no meio da lista */
	private T removeMeioLista(int pos){
	     No p = inicio;
	     int n = 1;
	     
	     // Localiza o nó que será removido
	     while((n <= pos-1) && (p != null)){ 
	    	 	p = p.getProx();
	        n++;
	     }
	     
	     if (p == null) {
	    	 	return null; // pos. inválida
	     }
	     
	     T dado = p.getConteudo();
	    	 p.getAnt().setProx(p.getProx());
	    	 p.getProx().setAnt(p.getAnt());
			 
	     tamanho--;
	     
	     /* sugere ao garbage collector que libere a memoria
	     *  da regiao apontada por p*/
	    p = null;
	    return dado;
	}
	
	/** Remove elemento do início da lista */
	private T removeFimLista(){          
	     No p = fim;
	     T dado = p.getConteudo();
	     
	     fim.getAnt().setProx(null);
	     fim = fim.getAnt();
	     tamanho--;
	     
	     p = null; 
	     return dado;
	}

	
	/**Remove um elemento de uma determinada posição
	    Retorna o valor a ser removido. 
	    -1 se a posição for inválida ou a lista estiver vazia*/
	public T remove(int pos) {
		// Lista vazia 
	    if (vazia()) {
	    		return null;
	    }

	    // Remoção do elemento da cabeça da lista 
	    if ((pos == 1) && (tamanho() == 1)){ 
	 		 return removeInicioListaUnitaria();
	    }
	    else if (pos == 1){
	        return removeInicioLista();
	    }
	    // Remocao no fim da lista
	    else if (pos == tamanho()){ 
	 		 return removeFimLista();
	    }
	    // Remoção em outro lugar da lista
	    else{
	        return removeMeioLista(pos);
	    }
	}	
}