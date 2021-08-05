/**
 * Celula (pilha, lista e fila dinamica)
 */
class Celula {
	public String nome;
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
	public Celula(String nome) {
	  this.nome = nome;
      this.prox = null;
	}
}
