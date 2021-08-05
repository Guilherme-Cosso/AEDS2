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
	public void inserirInicio(Player p) {
		Celula tmp = new Celula(p);
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
	public void inserirFim(Player p) {
		ultimo.prox = new Celula(p);
		ultimo = ultimo.prox;
	}
	/**
	 * Remove um Player da primeira posicao da lista.
	 * 
	 * @return resp Player a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Player removerInicio() throws Exception {
		if (primeiro == ultimo) throw new Exception("Erro ao remover (vazia)!");
		
		Celula tmp = primeiro;
		primeiro = primeiro.prox;
		Player resp = primeiro.p;
		tmp.prox = null;
		tmp = null;
		return resp;
	}
	/**
	 * Remove um Player da ultima posicao da lista.
	 * 
	 * @return resp Player a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Player removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}
		// Caminhar ate a penultima celula:
		Celula i;
		for (i = primeiro; i.prox != ultimo; i = i.prox);
		Player resp = ultimo.p;
		ultimo = i;
		i = ultimo.prox = null;
		return resp;
	}
	/**
	 * Insere um elemento em uma posicao especifica considerando que o primeiro
	 * elemento valido esta na posicao 0.
	 * 
	 * @param x   int Player a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public void inserir(Player p, int pos) throws Exception {
		int tamanho = tamanho();
		if (pos < 0 || pos > tamanho) {
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0) {
			inserirInicio(p);
		} else if (pos == tamanho) {
			inserirFim(p);
		} else {
			// Caminhar ate a posicao anterior a insercao
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox);
			Celula tmp = new Celula(p);
			tmp.prox = i.prox;
			i.prox = tmp;
			tmp = i = null;
		}
	}
	/**
	 * Remove um elemento de uma posicao especifica da lista considerando que o
	 * primeiro elemento valido esta na posicao 0.
	 * 
	 * @param posicao Meio da remocao.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Player remover(int pos) throws Exception {
		Player resp;
		int tamanho = tamanho();
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");

		} else if (pos < 0 || pos >= tamanho) {
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
		} else if (pos == 0) {
			resp = removerInicio();
		} else if (pos == tamanho - 1) {
			resp = removerFim();
		} else {
			// Caminhar ate a posicao anterior a insercao
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox);
			Celula tmp = i.prox;
			resp = tmp.p;
			i.prox = tmp.prox;
			tmp.prox = null;
			i = tmp = null;
		}
		return resp;
	}
	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.p.getId() + " ");
		}
		System.out.println("] ");
	}
	/**
	 * Procura um elemento e retorna se ele existe.
	 * 
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir, <code>false</code> em caso
	 *         contrario.
	 */
	public boolean pesquisar(int x) {
		boolean resp = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			if (i.p.getId() == x) {
				resp = true;
				i = ultimo;
			}
		}
		return resp;
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
		int x=0;
		for (Celula i = primeiro.prox; i != ultimo.prox; i = i.prox){
			MyIO.print("["+ x++ +"] ");
			i.p.imprimir();
		}
	}	
}
