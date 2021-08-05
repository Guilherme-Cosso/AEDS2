class CelulaDupla {
	public int elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;
	/**
	 * Construtor da classe.
	 */
	public CelulaDupla() {
		this(0);
	}
	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public CelulaDupla(int elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}
