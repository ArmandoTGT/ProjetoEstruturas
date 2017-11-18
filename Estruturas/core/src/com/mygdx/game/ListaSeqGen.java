package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;

public class ListaSeqGen<T> {
	private T dados[]; // Vetor que contem os dados da lista 
	private Texture quadrado[]; //Representar� os blocos de nossa estrutura, possuir� o mesmo n�mero que elementos
	private Texture quadVazio; //D�ra o preenchimento vazio aos blocos de nossa estrutura
	private Texture quadPreenchido; //Quando um bloco tiver conte�do, ele ser� preenchido com essa textura
	private int nElementos;
	private int tamMax;
    
    public ListaSeqGen(Texture quadVazio, Texture quadPreenchido){
    		tamMax = 100;
    		nElementos = 0;
    		dados = (T[]) new Object[tamMax];
    		this.quadVazio = quadVazio;
    		this.quadPreenchido = quadPreenchido;
    		quadrado = new Texture[tamMax];
    		for(int i = 0; i < quadrado.length; i++){
    			quadrado[i] = quadVazio;
    		}
    }
    
    public ListaSeqGen(int n,Texture quadVazio, Texture quadPreenchido){
    	tamMax = n;
    	nElementos = 0;
		dados = (T[]) new Object[tamMax];
		this.quadVazio = quadVazio;
		this.quadPreenchido = quadPreenchido;
		quadrado = new Texture[tamMax];
		for(int i = 0; i < quadrado.length; i++){
			quadrado[i] = quadVazio;
		}
    }

    /** Verifica se a Lista está vazia */
    public boolean vazia(){
		if (nElementos == 0 )
			return true;
		else
			return false;
	}
	
    /**Verifica se a Lista está cheia */
    public boolean cheia(){
		if (nElementos == tamMax)
			return true;
		else
			return false;
	}
	
    /**Obtém o tamanho da Lista*/
    public int tamanho(){
		return nElementos;
	}
    
    /** Obtém o i-ésimo elemento de uma lista.
    		Retorna -1 se a posição for inválida. */
    public String elemento(int pos){
        
    	/* Se posição estiver fora dos limites <= 0 
         * ou > tamanho da lista */
        if ((pos > nElementos) || (pos <= 0))
            return "";

       return (String)dados[pos-1];
	}

    public Texture imagem(int pos){
    	return quadrado[pos - 1];
    }
    /**	Retorna a posição de um elemento pesquisado.
    		Retorna -1 caso não seja encontrado */
	public int posicao (T valor){
	    /* Procura elemento a elemento, se o dado está na
	    		lista. Se estiver, retorna a sua posição no array+1 */
	    for (int i = 0; i < nElementos; i++){
	        if (dados[i].equals(valor)){
	            return (i + 1);
	        }
	    }

	    return -1;
	}
	
	/**	Retorna a posição de um elemento pesquisado.
	Retorna -1 caso não seja encontrado */
	public int posicao (T valor, int desloc){
		/* Procura elemento a elemento, se o dado está na
		lista. Se estiver, retorna a sua posição no array+1 */
		for (int i = desloc+1; i < nElementos; i++){
		    if (dados[i].equals(valor)){
		        return (i + 1);
		    }
		}
		
		return -1;
	}
	
	/**Insere um elemento em uma determinada posição
    		Retorna false se a lista estiver cheia ou
    		a posição for inválida. Caso contrário retorna true */
	public boolean insere (int pos, T dado){
	    /* Verifica se a lista está cheia ou se a posicao a ser
	    inserida eh invalida (i.e., > tamanho da lista+1*/
	    if (cheia() || (pos > nElementos+1) || (pos <=0)){
	        return false;
	    }

	    /* Desloca os elementos ap�s a pos, uma posicao a
	    direita. Isso serve para abrir espa�o para a insercao
	    do novo elemento */
	    for (int i = nElementos; i >= pos; i--){
	 		 dados[i] = dados[i-1];
	 		 if(i==nElementos) quadrado[i] = quadPreenchido; // Pois � dada uma posi��o de mem�ria par ao �ltimo elemento
	    }

	    /* Insere o dado na posicao correta */
	    dados[pos - 1] = dado;
	    quadrado[pos - 1] = quadPreenchido;//O respectivo bloco foi preenchido

	 	/* Incrementa o numero de elementos na lista */
	    nElementos++;
	    return true;
	}
	
	/**Remove um elemento de uma determinada posição
    Retorna o valor do elemento removido. -1 caso a remoção falhe  */
	public T remove(int pos){
	    T dado;
		/* Verifica se a posicao eh valida */
	    if ((pos > nElementos) || (pos < 1 ))
			   return null;

	    /* Armazena o dado a ser removido na var "dado" */
	    dado = dados[pos-1];

	    /* Desloca todos os elementos apos 'pos', uma
	    posicao a esquerda */
	    for (int i = pos - 1; i < nElementos - 1; i++){
	 		  dados[i] = dados[i+1];
	 		  if(i + 1 == nElementos - 1) quadrado[i + 1] = quadVazio; // Pois � dada uma posi��o de mem�ria par ao �ltimo elemento
		 }

	   /* Decrementa o numero de elementos na lista */
	    nElementos--;
	    return dado;
	}

}
