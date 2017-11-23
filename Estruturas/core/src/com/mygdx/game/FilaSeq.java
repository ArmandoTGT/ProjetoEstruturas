package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class FilaSeq {
	private String dados[];
	//escrito - Vai simbolizar se algo foi escrito naquele quadrado ou não
	//igual - quando tentar excluir na mesma posição em que foi adicionado pela última vez
	private boolean escrito[], igual;
	private int inicio;
	private int fim;
	
	private int nElementos;
	private int tamMax;
	
	private Texture[] quads;
	private Texture quadValido, quadVazio;
	
	public FilaSeq(Texture quadValido, Texture quadVazio) {
		inicio = 0;
		fim = -1;
		nElementos = 0;
		igual = false;
		tamMax = 100;
		dados =  new String[tamMax];
		
		escrito = new boolean[tamMax];
		this.quadValido = quadValido;
		this.quadVazio = quadVazio;
		quads = new Texture[tamMax];
		for(int i = 0; i<tamMax ; i++) {
    		quads[i] = quadVazio;
    	}
	}
	
	public FilaSeq(int n,Texture quadValido, Texture quadVazio) {
		inicio = 0;
		fim = -1;
		nElementos = 0;
		igual = false;
		tamMax = n;
		dados =  new String[tamMax];
		
		escrito = new boolean[tamMax];
		this.quadValido = quadValido;
		this.quadVazio = quadVazio;
		quads = new Texture[tamMax];
		for(int i = 0; i<tamMax ; i++) {
    		quads[i] = quadVazio;
    	}
	}

	public Texture imagem(int pos){
		return quads[pos];
	}
	
	public String conteudo(int pos){
		return dados[pos];
	}
	
	public boolean escrito(int pos){
		return escrito[pos];
	}
	
	/** Verifica se a Fila estÃ¡ vazia */
	public boolean vazia () {
		if (nElementos == 0)
			return true;
		else
			return false;
	}
	
	public int inicio(){ //Dará a posição do ponteiro de inicio
		return inicio;
	}
	
	public int fim(){ //Dará a posição do ponteiro do fim
		return fim;
	}
	
	public int tamMax() {
		return tamMax;
	}
	
	/**Verifica se a Fila estÃ¡ cheia */
	public boolean cheia () {
		if (nElementos == tamMax)
			return true;
		else
			return false;
	}

	/** ObtÃ©m o tamanho da Fila */
	public int tamanho() {
		return nElementos;
	}

	/** Consulta o elemento do inÃ­cio da fila.
	    Retorna -1 se a fila estiver vazia. */
	public String primeiro() {
		if (vazia())
			return "null"; // Erro: Fila vazia 
		
		return dados[inicio];
	}

	/**Insere um elemento no fim de uma fila
    Retorna false se a fila estiver cheia, true caso contrÃ¡rio. */
	public boolean insere(String valor) {
		if (cheia()){
			return false;
		}
		if(igual == true) {
			igual = false;
			fim--;
			fim = (fim + 1) % tamMax; // Circularidade
			escrito[fim] = true; //Simboliza que aquela posição foi apagada
			quads[fim] = quadValido; //Representação gráfica de que foi adicionado
		    dados[fim] = valor;
			nElementos++;
		}
		else {
			fim = (fim + 1) % tamMax; // Circularidade
			escrito[fim] = true; //Simboliza que aquela posição foi apagada
			quads[fim] = quadValido; //Representação gráfica de que foi adicionado
		    dados[fim] = valor;
			nElementos++;
		}
		return true;
	}

	/**Remove o elemento do inicio da fila e retorna o valor removido.
	    Retorna -1 se a fila estiver vazia.*/
	public String remove() {
		if (vazia())
			return null;
	
		String res = primeiro();	
		dados[inicio] = "-1";
		escrito[inicio] = false; //Foi apagado algo naquela posição
		quads[inicio] = quadVazio; //Aqui a representação gráfica de que foi apagado
		//Maritan tava errado aqui
		if(inicio == fim) igual = true; //Indica que ouve uma tentativa de deletar quando o inicio e o fim iguais
		else inicio = (inicio + 1) % tamMax; //Circularidade só deve acontacer quando o inicio é diferente do fim, se não o inicio pula o fim
		nElementos--;
		return res;
	}

}
