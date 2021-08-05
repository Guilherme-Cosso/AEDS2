public class Fila {
    int num;
    Fila prox;
	Pilha baixo;
	
    public Fila() {
		this(0);
	}

    /**
	 * Construtor do Nó.
	 */
	public Fila(int num) {
		this(num, null, null);
	}

    /**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */

	public Fila(int num ,Fila ant, Pilha baixo) {
	  this.num = num;
	  this.prox = null;
      this.baixo = null;
	}
}






























void 
