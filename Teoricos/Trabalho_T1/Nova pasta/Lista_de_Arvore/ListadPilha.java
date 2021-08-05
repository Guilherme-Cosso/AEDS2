class ListaDupla {
	private Fila primeiro;
	private Fila ultimo;

 	/**
	 * Construtor da classe que cria uma lista dupla sem pesos (somente no cabeca).
	 */
	public ListaDupla() {
		primeiro = new Fila();
		ultimo = primeiro;
    }

    public void separar(Fila C1, Fila C2){
        Fila tmp1 = C1;
        Fila tmp2 = C2;
        for(Fila i = primeiro.prox; i !=primeiro; i= i.prox){
            // Caso Par insiro o numero par na Lista C2
            if(i.num % 2 ==0){
                tmp2 = new Fila(i.num);
                tmp2 = tmp2.prox;
            }

            // Caso Impar insiro o numero impar na Lista C1
            else {
                tmp1 = new Fila(i.num);
                tmp1 = tmp1.prox;
            }
        }
    }
    
    public ListaDupla(int n, int m){
        /*
        Criar Fila Circ Tam N
        */
        primeiro = ultimo = new Fila(1);
        for(int j=0; j<m ;j++){
            Pilha tmp = ultimo.baixo;
            tmp.baixo = new Pilha(1); 
            tmp = ultimo.baixo;
        }
        for(int i =1;i<n;i++){
            ultimo.prox = new Fila(1);
            ultimo = ultimo.prox;
            // Cria as Pilhas em Cada Fila
            for(int j=0; j<m ;j++){
                Pilha tmp = ultimo.baixo;
                tmp.baixo = new Pilha(1); 
                tmp = ultimo.baixo;
            }
        }
        // Fazendo A fila ficar circular
        ultimo.prox = primeiro;
    }    
}

