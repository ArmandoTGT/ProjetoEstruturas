package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;

public class LSEGen<T> {
	
	class No {
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

		public Texture getQuad() {
			return quad;
		}

		public void setQuad(Texture quad) {
			this.quad = quad;
		}

	}
	
	private Texture quadCheio;
	private Texture quadVazio;
	private No cabeca;
	private int tamanho;
	
	public LSEGen(Texture quadCheio, Texture quadVazio){
		this.quadCheio = quadCheio;
		this.quadVazio = quadVazio;
		cabeca = null;
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

	/** Obtem o i-nesimo elemento de uma lista
	    Retorna o valor encontrado. */
	public T elemento (int pos) {
	    No aux = cabeca;
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
	    return aux.getConteudo();
	}
	
	/** Obtem a i-nesimo imagem salva de uma lista
    Retorna o valor encontrado. */
	public Texture imagem(int pos) {
	    No aux = cabeca;
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

	/**Retorna a posi��o de um elemento pesquisado.
	    Retorna 0 caso n�o seja encontrado */
	public int posicao (T dado) {
	    int cont = 1;
	    No aux;

	    /* Lista vazia */
	    if (vazia()) {
	        return -1;
	    }

	    /* Percorre a lista do inicio ao fim até encontrar o elemento*/
	    aux = cabeca;
		while (aux != null) {
	        /* Se encontrar o elemento, retorna sua posicao n;*/
	        if (aux.getConteudo().equals(dado)){
	            return cont;
	        }

	        /* modifica "aux" para apontar para o proximo elemento da lista */
	        aux = aux.getProx();
	        cont++;
	    }

	    return -1;
	}

	/** Insere na lista vazia */
	private int insereInicioLista(T valor) {
	    // Aloca memoria para novo no 
	    No novoNo = new No();
	    
	    // Insere novo elemento na cabeca da lista
	    novoNo.setConteudo(valor);
	    novoNo.setProx(cabeca);
	    novoNo.setQuad(quadCheio);
	    cabeca = novoNo;
	    tamanho++;
	    return 1;
	}

	/** Insere no meio da lista, quando a posi��o no meio for invalida, retorna -1(exce��o) */
	private int insereMeioLista(int pos, T dado){
	    int cont = 1;

	    // Aloca memoria para novo no
	    No novoNo = new No();
	    novoNo.setConteudo(dado);
	    novoNo.setQuad(quadCheio);
	    // Localiza a pos. onde será inserido o novo nó
	    No aux = cabeca;
	    while ((cont < pos-1) && (aux != null)){
	          aux = aux.getProx();
	          cont++;
	    }

	    if (aux == null) {  // pos. inválida 
	    		return -1;
	    }

	    // Insere novo elemento apos aux
	    novoNo.setProx(aux.getProx());
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
	    No aux = this.cabeca;
	    while(aux.getProx() != null){
	        aux = aux.getProx();
	    }

	    novoNo.setProx(null);
	    aux.setProx(novoNo);

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

	/** Remove elemento do início da lista */
	private T removeInicioLista(){
	    No p = cabeca;

	    // Dado recebe o dado removido
	    T dado = p.getConteudo();

	    // Retira o 1o elemento da lista (p)
	    cabeca = p.getProx();
	    tamanho--;

	    // Sugere ao garbage collector que libere a memoria
	    //  da regiao apontada por p
	    p = null;

	    return dado;
	}

	/** Remove elemento no meio da lista */
	private T removeNaLista(int pos){
	     No atual = null, antecessor = null;
	     T dado = null;
	     int cont = 1;

	     /* Localiza o no que ser� removido*/
	     atual = cabeca;
	     while((cont < pos) && (atual != null)){
	           antecessor = atual;
	           atual = atual.getProx();
	           cont++;
	     }

	     if (atual == null) { /* pos. inválida */
	        return null;
	     }

	    /* retira o elemento da lista */
	    dado = atual.getConteudo();
	    antecessor.setProx(atual.getProx());
	    tamanho--;

	    /* sugere ao garbage collector que libere a memoria
	     *  da regiao apontada por p*/
	    atual = null;
	    return dado;
	}

	/**Remove um elemento de uma determinada posição
	    Retorna o valor a ser removido. 
	    null se a posição for inválida ou a lista estiver vazia*/
	public T remove(int pos) {
		// Lista vazia 
	    if (vazia()) {
	    		return null;
	    }

	    // Remoção do elemento da cabeça da lista 
	    if (pos == 1){
	        return removeInicioLista();
	    }
	    // Remoção em outro lugar da lista
	    else{
	        return removeNaLista(pos);
	    }
	}	
}

