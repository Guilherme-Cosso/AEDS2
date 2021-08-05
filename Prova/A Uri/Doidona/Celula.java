/**
 * Celula (pilha, lista e fila dinamica)
 */
class Celula {
	public int elemento;
	public Celula prox; // Aponta a celula prox.
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
	public Celula(int  p) {
	  this.elemento = p;
      this.prox = null;
	}
}
