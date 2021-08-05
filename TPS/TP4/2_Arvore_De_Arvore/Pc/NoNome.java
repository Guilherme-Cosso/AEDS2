class NoNome {
	public Player jogador; // Elemeto da Arvore
	public NoNome esq , dir; // Aponta para seus Filhos.

	/**
	 * Construtor do Nó.
	 */
	public NoNome(Player jogador) {
		this(jogador, null, null);
	}
	/**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */
	public NoNome(Player jogador, NoNome esq, NoNome dir) {
	  this.jogador = jogador;
	  this.dir = null;
	  this.esq = null;
	}
}
