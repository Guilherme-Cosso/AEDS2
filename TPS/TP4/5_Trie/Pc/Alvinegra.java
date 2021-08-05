class Alvinegra {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public Alvinegra() {
		raiz = null;
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
	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarCentral() {
		//System.out.print("[ ");
		mostrarCentral(raiz);
		//System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarCentral(No i) {
		if (i != null) {
			mostrarCentral(i.esq); // Elementos da esquerda.
			System.out.println(i.jogador.getNome()+" "); // Conteudo do no.
			mostrarCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPre() {
		System.out.print("[ ");
		mostrarPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarPre(No i) {
		if (i != null) {
			System.out.print(i.jogador.getNome() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
			mostrarPre(i.esq); // Elementos da esquerda.
			mostrarPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPos() {
		System.out.print("[ ");
		mostrarPos(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarPos(No i) {
		if (i != null) {
			mostrarPos(i.esq); // Elementos da esquerda.
			mostrarPos(i.dir); // Elementos da direita.
			System.out.print(i.jogador.getNome() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
		}
	}


	/**
	 * Metodo publico iterativo para inserir elemento.
	 * @param elemento Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Player jogador) throws Exception {
   
      //Se a arvore estiver vazia
      if(raiz == null){
         raiz = new No(jogador, false);

      //Senao, se a arvore tiver um elemento 
      } else if (raiz.esq == null && raiz.dir == null){
         if (raiz.jogador.getNome().compareTo(jogador.getNome()) > 0 ){
            raiz.esq = new No(jogador, true);
         } else {
            raiz.dir = new No(jogador, true);
         }

      //Senao, se a arvore tiver dois elementos (raiz e dir)
      } else if (raiz.esq == null){

         if(raiz.jogador.getNome().compareTo(jogador.getNome()) > 0){
            raiz.esq = new No(jogador);
         } else if (raiz.dir.jogador.getNome().compareTo(jogador.getNome())>0){
            raiz.esq = new No(raiz.jogador);
            raiz.jogador = jogador;
         } else {
            raiz.esq = new No(raiz.jogador);
            raiz.jogador = raiz.dir.jogador;
            raiz.dir.jogador = jogador;
         }
         raiz.esq.cor = raiz.dir.cor = false;
      //Senao, se a arvore tiver dois elementos (raiz e esq)
      } else if (raiz.dir == null){
         
         if(raiz.jogador.getNome().compareTo(jogador.getNome()) < 0){
            raiz.dir = new No(jogador);
         } else if (raiz.jogador.getNome().compareTo(jogador.getNome()) < 0){
            raiz.dir = new No(raiz.jogador);
            raiz.jogador = jogador;
         } else {
            raiz.dir = new No(raiz.jogador);
            raiz.jogador = raiz.esq.jogador;
            raiz.esq.jogador = jogador;
         }

         raiz.esq.cor = raiz.dir.cor = false;

      //Senao, a arvore tem tres ou mais elementos
      } else {
   	   inserir(jogador, null, null, null, raiz);
      }

      raiz.cor = false;
   }

   private void balancear(No bisavo, No avo, No pai, No i){

      //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
      if(pai.cor == true){

         //4 tipos de reequilibrios e acoplamento
         if(pai.jogador.getNome().compareTo(avo.jogador.getNome()) > 0){ // rotacao a esquerda ou direita-esquerda
            if(i.jogador.getNome().compareTo(pai.jogador.getNome()) > 0){
               avo = rotacaoEsq(avo);
            } else {
               avo = rotacaoDirEsq(avo);
            }

         } else { // rotacao a direita ou esquerda-direita
            if(i.jogador.getNome().compareTo(pai.jogador.getNome()) < 0){
               avo = rotacaoDir(avo);
            } else {
               avo = rotacaoEsqDir(avo);
            }
         }

         if (bisavo == null){
            raiz = avo;
         } else {
            if(avo.jogador.getNome().compareTo(bisavo.jogador.getNome()) < 0){
               bisavo.esq = avo;
            } else {
               bisavo.dir = avo;
            }
         }

         //reestabelecer as cores apos a rotacao
         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
      } //if(pai.cor == true)
   }

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param elemento Elemento a ser inserido.
	 * @param avo No em analise.
	 * @param pai No em analise.
	 * @param i No em analise.
	 * @throws Exception Se o elemento existir.
	 */
	private void inserir(Player jogador, No bisavo, No avo, No pai, No i) throws Exception {
		if (i == null) {

         if(jogador.getNome().compareTo(pai.jogador.getNome()) < 0){
            i = pai.esq = new No(jogador, true);
         } else {
            i = pai.dir = new No(jogador, true);
         }

         if(pai.cor == true){
            balancear(bisavo, avo, pai, i);
         }

      } else {

        //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
         if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if(i == raiz){
               i.cor = false;
            }else if(pai.cor == true){
               balancear(bisavo, avo, pai, i);
            }
         }
         if (jogador.getNome().compareTo(i.jogador.getNome()) <0) {
            inserir(jogador, avo, pai, i, i.esq);
         } else if (jogador.getNome().compareTo(i.jogador.getNome()) > 0) {
            inserir(jogador, avo, pai, i, i.dir);
         } else {
            throw new Exception("Erro inserir (elemento repetido)!");
         }
      }
	}

   private No rotacaoDir(No no) {

      No noEsq = no.esq;
      No noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      return noEsq;
   }

   private No rotacaoEsq(No no) {

      No noDir = no.dir;
      No noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   private No rotacaoDirEsq(No no) {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   private No rotacaoEsqDir(No no) {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
   }
}