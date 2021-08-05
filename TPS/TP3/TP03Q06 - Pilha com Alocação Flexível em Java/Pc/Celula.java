/**
 * Celula (pilha, lista e fila dinamica)
 */
class Celula {
	public Player p;
	public Celula prox; // Aponta a celula prox.
	/**
	 * Construtor da classe.
	 */
	public Celula() {
		this(null);
	}
	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public Celula(Player p) {
	  this.p = p;
      this.prox = null;
	}
}
