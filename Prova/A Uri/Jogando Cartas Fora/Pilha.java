class Pilha {
    Celula primeiro;
    Celula ultimo;

    public Pilha() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inicializar(int x) {

        primeiro.elemento = 1;
        if (x >= 2) {
            for (int i = 2; i <= x; i++) {
                ultimo.prox = new Celula(i);
                ultimo = ultimo.prox;
            }

        }
        ultimo.prox = primeiro;
    }

    public void mostra() {
        System.out.print("[ "); // Comeca a mostrar.
        for (Celula i = primeiro; i != ultimo; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.print(ultimo.elemento + " ");
        System.out.println("] "); // Termina de mostrar.
    }

    public int removerInicio(){
        int resp = primeiro.elemento;
        primeiro = primeiro.prox;
        ultimo.prox = primeiro;
        return resp;
    }

    public void anvt(){
        primeiro = primeiro.prox;
        ultimo = ultimo.prox;
    }

}
