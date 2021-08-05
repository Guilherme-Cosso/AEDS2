public class Arvore {

	private NoAlt raiz; // Raiz da arvore.

	public Arvore() {
		raiz = null;
	}

	public void inserirMood15(int array[], TimeExecution t) {
		for (int i = 0; i < 15; i++)
			raiz = inserirMood15(array[i], raiz, t);
	}

	private NoAlt inserirMood15(int num, NoAlt i, TimeExecution t) {
		if (i == null) {
			i = new NoAlt(num);
			t.add(1);
		} else if (num < i.altura) {
			i.esq = inserirMood15(num, i.esq, t);
			t.add(2);
		} else if (num > i.altura) {
			i.dir = inserirMood15(num, i.dir, t);
			t.add(3);
		}
		return i;
	}

	/**
	 * Metodo publico iterativo para inserir elemento.
	 * 
	 * @param Jogador a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Player jogador, TimeExecution t) {
		int mood = jogador.getAltura() % 15;
		raiz = inserirAltura(jogador, raiz, mood, t);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * 
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 */

	private NoAlt inserirAltura(Player j, NoAlt i, int mood, TimeExecution t) {
		if (mood < i.altura) {
			i.esq = inserirAltura(j, i.esq, mood, t);
			t.add(1);
		} else if (mood > i.altura) {
			i.dir = inserirAltura(j, i.dir, mood, t);
			t.add(2);
		} else {
			i.l = inseriNoNome(j, i.l, t);
			t.add(2);
		}
		return i;
	}

	private static NoNome inseriNoNome(Player j, NoNome n, TimeExecution t) {
		if (n == null) {
			n = new NoNome(j);
			t.add(1);
		} else if (j.getNome().compareTo(n.jogador.getNome()) < 0) {
			n.esq = inseriNoNome(j, n.esq, t);
			t.add(2);
		} else if (j.getNome().compareTo(n.jogador.getNome()) > 0) {
			n.dir = inseriNoNome(j, n.dir, t);
			t.add(3);
		}
		return n;
	}

	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	private void caminharPre(NoAlt i) {
		if (i != null) {
			MyIO.print("-> "+i.altura);
			caminharNome(i.l);
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

	private void caminharNome(NoNome i) {
		if (i != null) {
			caminharNome(i.esq);
			caminharNome(i.dir);
			System.out.print(i.jogador.getNome() + " | "); // Conteudo do no.
		}
	}

	public void pesquisar(String nome, TimeExecution t) {
		boolean resp = false;
		resp = PesquisarMood(raiz, nome, t, resp);
		if (resp == true) {
			MyIO.print("SIM\n");
		} else
			MyIO.print("NAO\n");
		t.add(1);
	}

	private boolean PesquisarMood(NoAlt i, String nome, TimeExecution t, Boolean resp) {
		if (i != null && resp == false) {
			resp = PesquisarNome(nome, i.l, t, resp);
			if (resp == false) {
				MyIO.print("esq ");
				resp = PesquisarMood(i.esq, nome, t, resp); // Elementos da esquerda.
			}
			if (resp == false) {
				MyIO.print("dir ");
				resp = PesquisarMood(i.dir, nome, t, resp);// Elementos da direita
			}
		}
		return resp;
	}

	private boolean PesquisarNome(String nome, NoNome i, TimeExecution t, Boolean resp) {
		if (i == null) {
			resp = false;
			t.add(1);
		} else{
			if (nome.compareTo(i.jogador.getNome()) == 0) {
				resp = true;
				t.add(2);
			} 
			if (resp == false){
				MyIO.print("ESQ ");
				t.add(3);
				resp = PesquisarNome(nome, i.esq, t, resp);
			} 
			if (resp == false){
				MyIO.print("DIR ");
				resp = PesquisarNome(nome, i.dir, t, resp);
				t.add(4);
			}
		}
		return resp;
	}

}
