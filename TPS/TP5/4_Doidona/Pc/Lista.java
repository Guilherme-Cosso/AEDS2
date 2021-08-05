class Lista {
	private Celula primeiro;
	private Celula ultimo;
	/**
	 * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
	 */
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}
	/**
	 * Insere um elemento na primeira posicao da lista.
	 * 
	 * @param P Player elemento a ser inserido.
	 */
	public void inserirInicio(String nome) {
		Celula tmp = new Celula(nome);
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			ultimo = tmp;
		}
		tmp = null;
	}
	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param p int elemento a ser inserido.
	 */
	public void inserirFim(String nome) {
		ultimo.prox = new Celula(nome);
		ultimo = ultimo.prox;
	}

	/**
	 * Insere um elemento em uma posicao especifica considerando que o primeiro
	 * elemento valido esta na posicao 0.
	 * 
	 * @param x   int Player a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public void inserir(String nome, int pos) throws Exception {
		int tamanho = tamanho();
		if (pos < 0 || pos > tamanho) {
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0) {
			inserirInicio(nome);
		} else if (pos == tamanho) {
			inserirFim(nome);
		} else {
			// Caminhar ate a posicao anterior a insercao
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox);
			Celula tmp = new Celula(nome);
			tmp.prox = i.prox;
			i.prox = tmp;
			tmp = i = null;
		}
	}

	public boolean pesquisar(String nome){
		boolean resp =  false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			if(i.nome.compareTo(nome) == 0)
				resp = true;
		}
		return resp;
	}

	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.nome + " ");
		}
		System.out.println("] ");
	}


	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * 
	 * @return resp int tamanho
	 */
	public int tamanho() {
		int tamanho = 0;
		for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
		return tamanho;
	}
	/**
	 * Mostra todos o Jogadores presentes na Lista. 
	*/
	public void print(){
		for (Celula i = primeiro.prox; i != ultimo.prox; i = i.prox){
			MyIO.print("["+ i.nome +"] ");
		}
	}	
}
