class NoAlt {
    public int altura; // Elemeto da Arvore
    public NoNome l;
	public NoAlt esq , dir; // Aponta para seus Filhos.

	/**
	 * Construtor do Nó.
	 */
	public NoAlt(int altura) {
		this(altura, null, null , null);
	}
	/**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */
	public NoAlt(int altura ,NoNome l,NoAlt esq, NoAlt dir) {
	  this.altura = altura;
	  this.dir = null;
      this.esq = null;
      this.l = null;
	}
}
