class No {
	public boolean cor;
	public Player jogador; // Elemeto da Arvore
	public No esq, dir; // Aponta para seus Filhos.

	public No(){
		this(null);
	}

	public No(Player jogador){
		this(jogador, false, null, null);
	}

	public No(Player jogador, boolean cor){
		this(jogador, cor, null, null);
	}

	public No(Player jogador, boolean cor, No esq, No dir){
	this.cor = cor;
	this.jogador = jogador;
	this.esq = esq;
	this.dir = dir;
	}
}