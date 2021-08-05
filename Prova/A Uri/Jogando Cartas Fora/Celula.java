class Celula {
	public int elemento;
	public Celula prox;
	/**
	 * Construtor da classe.
	 */
	public Celula() {
		this(0);
	}
	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public Celula(int elemento) {
		this.elemento = elemento;
		this.prox = null;
	}
}
