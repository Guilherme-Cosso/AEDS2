public class Pilha {
    int m;
    Pilha baixo;
  

    /**
	 * Construtor do Nó.
	 */
	public Pilha(int m) {
		this(m, null);
    }
    
	/**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */
	public Pilha(int m, Pilha baixo) {
	  this.m = m;
	  this.baixo = null;

	}
}
