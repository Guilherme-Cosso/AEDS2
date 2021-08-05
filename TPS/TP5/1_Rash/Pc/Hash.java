public class Hash {
    String tabela[];
    int m1, m2, m, reserva;
    int NULO = -1;

    // Cria 21 + 9 de Reserva
    public Hash() {
        this(21, 9);
    }

    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new String[this.m];
        for (int i = 0; i < m; i++) {
            tabela[i] = "";
        }
        reserva = 0;
    }

    public int h(int elemento) {
        return elemento % m1;
    }

    public boolean inserir(Player j) {
        boolean resp = false;
        int pos = h(j.getAltura());

        if (tabela[pos] == "") {
            tabela[pos] = j.getNome();
            resp = true;

        } else if (reserva < m2) {
            tabela[m1 + reserva] = j.getNome();
            reserva++;
            resp = true;
        }
        return resp;
    }

    public boolean pesquisar(String elemento) {
        boolean resp = false;
        for (int i = 0; i < 30 ; i++) {
            if (tabela[i].compareTo(elemento) == 0)
                resp = true;
        }
        return resp;
    }
}