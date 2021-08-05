class Lista {
	public Celula primeiro;
	public Celula ultimo;

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
	public void inserirInicio(Player p, int x) {
		Celula tmp = new Celula(p);
		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			ultimo = tmp;
		} else {
			tmp.prox.ant = tmp;
		}
		tmp = null;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param p int elemento a ser inserido.
	 */
	public void inserirFim(Player p, int x) {
		ultimo.prox = new Celula(p);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}

	/**
	 * Remove um Player da primeira posicao da lista.
	 * 
	 * @return resp Player a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Player removerInicio() throws Exception {
		if (primeiro == ultimo)
			throw new Exception("Erro ao remover (vazia)!");

		Celula tmp = primeiro;
		primeiro = primeiro.prox;
		Player resp = primeiro.p;
		tmp.prox = primeiro.ant = null;
		tmp = null;
		return resp;
	}

	/**
	 * Remove um Player da ultima posicao da lista.
	 * 
	 * @return resp Player a ser removido.
	 * @throws Exception Se a lista nao contiver elementos. 0
	 */
	public Player removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}
		// Caminhar ate a penultima celula:
		Player resp = ultimo.p;
		ultimo = ultimo.ant;
		ultimo.prox.ant = null;
		ultimo.prox = null;
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
	public void inserir(Player p, int pos, int x) throws Exception {
		int tamanho = tamanho();
		if (pos < 0 || pos > tamanho) {
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0) {
			inserirInicio(p, x);
		} else if (pos == tamanho) {
			inserirFim(p, x);
		} else {
			// Caminhar ate a posicao anterior a insercao
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox)
				;
			Celula tmp = new Celula(p);
			tmp.ant = i;
			tmp.prox = i.prox;
			tmp.ant.prox = tmp.prox.ant = tmp;
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
			for (int j = 0; j < pos; j++, i = i.prox)
				;

			i.ant.prox = i.prox;
			i.prox.ant = i.ant;
			resp = i.p;
			i.prox = i.ant = null;
			i = null;
		}
		return resp;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro; i != null; i = i.prox) {
			System.out.print(i.p.getNome() + " ");
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
	public void print() {
		for (Celula i = primeiro; i != ultimo.prox; i = i.prox) {
			i.p.imprimir();
		}
	}

	public void mostrarInverso() {
		System.out.print("[ ");
		for (Celula i = ultimo; i != primeiro; i = i.ant) {
			System.out.print(i.p.getId() + " ");
		}
		System.out.println("] "); // Termina de mostrar.
	}

	public void tirarNo(){
		Celula tem= primeiro;
		primeiro=primeiro.prox;
		tem.prox =null;
	}

	public Player AcharPos(int pos){
		Celula i =primeiro;
		for (int x = 0; x < pos; x++, i = i.prox);
		return i.p;
	}
	
	public void Quick(){
		quicksort(primeiro,ultimo,0,tamanho());
	}

	public void quicksort(Celula esquerda,Celula direita,int esq, int dir){
		MyIO.print(esquerda.p.getId()+"\n"+direita.p.getId()+"\n"+esq+"\n"+dir);
		Celula left = esquerda,
			   right = direita;
		int	   i=esq,
			   j=dir, 
			   tamanho = (dir + esq )/2;
		Player pivo = AcharPos(tamanho);
		MyIO.print("\n 1");
		
		while(i<=j){
			while(left.p.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())<0 || (left.p.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())== 0 && left.p.getNome().compareTo(pivo.getNome())<0)){
				i++;
				left=left.prox;
			}
			while(right.p.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())>0 ||(right.p.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())== 0 && right.p.getNome().compareTo(pivo.getNome())>0)){
				j--;
				right = right.ant;
			}
			if(i<=j){
				swap(left,right);
				i++;
				j--;
				left =left.prox;
				right = right.ant;
			}
		}
		if (esq< j)
			quicksort(esquerda, right, esq, j);
		if (i < dir)
			quicksort(left, direita, i ,dir);
	}

	public void swap(Celula A, Celula B){
		Player C = A.p;
		A.p = B.p;
		B.p = C;
	}

}
