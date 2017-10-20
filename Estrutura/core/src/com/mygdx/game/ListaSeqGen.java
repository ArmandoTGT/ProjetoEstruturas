package com.mygdx.game;

public class ListaSeqGen<T> {
	private T dados[]; // Vetor que contem os dados da lista 
	private int nElementos; 
	private int tamMax;
    
  
    public ListaSeqGen(int n){
    	tamMax = n;
    	nElementos = 0;
		dados = (T[]) new Object[tamMax];
    }
    
    public int memoria(){
    	return tamMax;
    }

    /** Verifica se a Lista est· vazia */
    public boolean vazia(){
		if (nElementos == 0 )
			return true;
		else
			return false;
	}
	
    /**Verifica se a Lista est√° cheia */
    public boolean cheia(){
		if (nElementos == tamMax)
			return true;
		else
			return false;
	}
	
    /**Obtem o tamanho da Lista*/
    public int tamanho(){
		return nElementos;
	}
    
    /** Obtem o i-nesimo elemento de uma lista.
    		Retorna -1 se a posi√ß√£o for inv√°lida. */
    public T elemento(int pos){
        
    	/* Se posiÁ„o estiver fora dos limites <= 0 
         * ou > tamanho da lista */
        if ((pos > nElementos) || (pos <= 0))
            return null;

       return dados[pos-1];
	}

    /**	Retorna a posiÁ„o de um elemento pesquisado.
    		Retorna -1 caso n„o seja encontrado */
	public int posicao (T valor){
	    /* Procura elemento a elemento, se o dado est· na
	    		lista. Se estiver, retorna a sua posiÁ„o no array+1 */
	    for (int i = 0; i < nElementos; i++){
	        if (dados[i].equals(valor)){
	            return (i + 1);
	        }
	    }

	    return -1;
	}
	
	/**	Retorna a posiÁ„o de um elemento pesquisado.
	Retorna -1 caso n„o seja encontrado */
	public int posicao (T valor, int desloc){
		/* Procura elemento a elemento, se o dado es· na
		lista. Se estiver, retorna a sua posiÁ„o no array+1 */
		for (int i = desloc+1; i < nElementos; i++){
		    if (dados[i].equals(valor)){
		        return (i + 1);
		    }
		}
		
		return -1;
	}
	
	/**Insere um elemento em uma determinada posiÁ„o
    		Retorna false se a lista estiver cheia ou
    		a posiÁ„o for invalida. Caso contrario retorna true */
	public boolean insere (int pos, T dado){
	    /* Verifica se a lista est√° cheia ou se a posicao a ser
	    inserida eh invalida (i.e., > tamanho da lista+1*/
	    if (cheia() || (pos > nElementos+1) || (pos <=0)){
	        return false;
	    }

	    /* Desloca os elementos ap pos, uma posicao a
	    direita. Isso serve para abrir espaÁo para insercao
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
	
	/**Remove um elemento de uma determinada posiÁ„o
    Retorna o valor do elemento removido. -1 caso a remo√ß√£o falhe  */
	public T remove(int pos){
	    T dado;
		/* Verifica se a posicao eh valida */
	    if ((pos > nElementos) || (pos < 1 ))
			   return null;

	    /* Armazena o dado a ser removido na var "dado" */
	    dado = dados[pos-1];

	    /* Desloca todos os elementos ap√≥s 'pos', uma
	    posicao a esquerda */
	    for (int i = pos - 1; i < nElementos - 1; i++){
	 		  dados[i] = dados[i+1];
		 }

	   /* Decrementa o numero de elementos na lista */
	    nElementos--;
	    return dado;
	}

}
