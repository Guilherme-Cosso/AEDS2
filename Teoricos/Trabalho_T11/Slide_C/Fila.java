

/**
 * Fila dinamica
 * 
 * @author Max do Val Machado
 * @version 2 01/2015
 */
public class Fila {
	private Celula primeiro;
	private Celula ultimo;
	static int tamanho = 0;
	/**
	 * Construtor da classe que cria uma fila sem elementos (somente no cabeca).
	 */
	public Fila() {
		primeiro = new Celula();
		ultimo = primeiro;
	}

	/**
	 * Insere elemento na fila (politica FIFO).
	 * 
	 * @param x int elemento a inserir.
	 */
	public void inserir(int x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
		tamanho++;
	}

	/**
	 * Remove elemento da fila (politica FIFO).
	 * 
	 * @return Elemento removido.
	 * @trhows Exception Se a fila nao tiver elementos.
	 */

	/**
	 * Mostra os elementos separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");

		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}

		System.out.println("] ");
	}

	// Questão 1
	public int remover() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover!");
		}

		Celula tmp = primeiro.prox;
		int resp = tmp.elemento;
		primeiro.prox = tmp.prox;
		tmp.prox = null;
		tmp = null;
		tamanho --;
		return resp;
	}

	// Questão 2
	int maior() throws Exception {
		int maior = -1;
		if (primeiro == ultimo) {
			throw new Exception("Erro!");
		} else {
			maior = primeiro.prox.elemento;
			Celula i = primeiro.prox.prox;
			while (i != null) {
				if (i.elemento > maior) {
					maior = i.elemento;
				}
				i = i.prox;
			}
		}
		return maior;
	}

	// Questão 3
	int mostrarTerceiroElemento() {
		return (primeiro.prox.prox.prox.elemento);
	}

	// Questão 4
	int somar() {
		int resp = 0;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			resp += i.elemento;
		}
		return resp;
	}

	// Questão 5
	void inverter() {
		Celula j = ultimo;
		Celula x;
		Celula k = primeiro.prox;
		int l=0;
		while(l<tamanho){
			for (x = primeiro.prox; x.prox != j; x = x.prox);
			int tmp = j.elemento;
			j.elemento = k.elemento; 
			k.elemento = tmp;
			k = k.prox;
			j=x;
			l++; 
			tamanho--;
		}	
	}
	// Questão 6
	int conta(){
		return conta(primeiro.prox);
	}
	int conta(Celula i){
		int resp;
		if(i==null) 
			resp=0;
		else if(i.elemento % 2==0 && i.elemento % 5==0){
			resp = conta(i.prox)+1;
		}else{
			resp = conta(i.prox);
		}
		return resp;
	}


}