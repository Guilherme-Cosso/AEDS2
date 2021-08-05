class No {
	public String nome; // Elemeto da Arvore
	public No esq , dir; // Aponta para seus Filhos.

	/**
	 * Construtor do Nó.
	 */
	public No(String nome) {
		this(nome, null, null);
	}
	/**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */
	public No(String nome, No esq, No dir) {
	  this.nome = nome;
	  this.dir = null;
	  this.esq = null;
	}
}
