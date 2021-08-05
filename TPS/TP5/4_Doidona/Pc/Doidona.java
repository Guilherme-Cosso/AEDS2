class Doidona {
    final int TAMT1 = 11;
    final int TAMT3 = 9;
    final String NULO = "";

    String[] t1;
    String[] t3;

    Arvore arvoreBinaria;
    Lista lista;

    public Doidona() {
        t1 = new String[TAMT1];
        t3 = new String[TAMT3];

        for (int i = 0; i < TAMT1; i++) {
            t1[i] = NULO;
        }
        for (int i = 0; i < TAMT3; i++) {
            t3[i] = NULO;
        }

        arvoreBinaria = new Arvore();
        lista = new Lista();
    }

    public int hashT1(int elemento) {
        return elemento % TAMT1;
    }

    public int hashT2(int elemento) {
        return elemento % 3;
    }

    public int hashT3(int elemento) {
        return elemento % TAMT3;
    }

    public int rehashT3(int elemento) {
        return ++elemento % TAMT3;
    }

    public void inserir(Player p) throws Exception {
        inserir(p.getNome(), p.getAltura());
    }

    private void inserir(String nome, int altura) throws Exception {
        int i = hashT1(altura);
        int reserva = hashT2(altura);
        if (nome == NULO) {
            MyIO.println("Nao ha nomes para inserir");
        } else if (t1[i] == NULO) {
            t1[i] = nome;
        } else if (reserva == 0) {
            i = hashT3(altura);
            if (t3[i] == NULO) {
                t3[i] = nome;
            } else {
                i = rehashT3(altura);
                if (t3[i] == NULO) {
                    t3[i] = nome;
                } 
            }
        } else if (reserva == 1) {
            lista.inserirFim(nome);
        } else if (reserva == 2) {
            arvoreBinaria.inserir(nome);
        } else {
            System.out.println("ERRO!!!!");
        }
    }

    public boolean pesquisar(String str) {
        boolean resp = false;

        for (int i = 0; i < TAMT1; i++) {
            if (t1[i].compareTo(str) == 0)
                resp = true;
        }
        if(resp == false){
            for (int i = 0; i < TAMT3; i++) {
                if (t3[i].compareTo(str) == 0)
                    resp = true;
            }
        }
        if(resp == false)
            resp = lista.pesquisar(str);
        if(resp == false){
            MyIO.print("raiz ");        
            resp = arvoreBinaria.pesquisar(str);
        }
        return resp;
    }

    public void mostrar() {
        // t1, t3, arvoreBinaria, lista, arvoreAVL
        for (int i = 0; i < TAMT1; i++) {
            if (t1[i] != NULO) {
                System.out.println(t1[i]);
            }
        }
        for (int i = 0; i < TAMT3; i++) {
            if (t3[i] != NULO) {
                System.out.println(t3[i]);
            }
        }
        arvoreBinaria.caminharPre();
        lista.mostrar();
    }
}