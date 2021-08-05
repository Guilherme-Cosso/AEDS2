class No {
	public Player jogador; // Elemeto da Arvore
	public No esq , dir; // Aponta para seus Filhos.

	/**
	 * Construtor do Nó.
	 */
	public No(Player jogador) {
		this(jogador, null, null);
	}
	/**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */
	public No(Player jogador, No esq, No dir) {
	  this.jogador = jogador;
	  this.dir = null;
	  this.esq = null;
	}
}
