public class Arvore {
    private No raiz; // Raiz da arvore.
    /**
     * Costrutor da Arvore
     */
    public Arvore(){
        raiz = null;
    }

    /**
	 * Metodo publico iterativo para inserir elemento.
	 * @param Jogador a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Player jogador) throws Exception {
		raiz = inserir(jogador, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */

	private No inserir(Player j, No i) throws Exception {
		if (i == null) {
         i = new No(j);

      } else if (j.getNome().compareTo(i.jogador.getNome()) < 0) {
         i.esq = inserir(j, i.esq);

      } else if (j.getNome().compareTo(i.jogador.getNome()) > 0) {
         i.dir = inserir(j, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }
      return i;
    }

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */

	 public void pesquisar(String nome) {
        Boolean resp = pesquisar(nome, raiz);
        if(resp ==true)
            MyIO.print("SIM\n");
        else
            MyIO.print("NAO\n");
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	
	private boolean pesquisar(String nome, No i) {
      boolean resp;
		if (i == null) {
         resp = false;

      } else if (nome.compareTo(i.jogador.getNome()) == 0) {
         resp = true;

      } else if (nome.compareTo(i.jogador.getNome()) < 0) {
         MyIO.print("esq ");
         resp = pesquisar(nome, i.esq);

      } else {
         MyIO.print("dir ");
         resp = pesquisar(nome, i.dir);
      }
      return resp;
	}

    public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
    }

    private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.jogador.getNome() + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
    }


}
