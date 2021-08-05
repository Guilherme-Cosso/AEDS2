class Pilha {
	private Celula primeiro;
	private Celula ultimo;
	/**
	 * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
	 */
	public Pilha() {
		primeiro = new Celula();
		ultimo = primeiro;
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
