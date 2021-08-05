class Player {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    // Constructor
    Player() {
    }

    Player(int id, int altura, int peso, int anoNascimento, String nome, String universidade, String cidadeNascimento,
            String estadoNascimento) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
        this.universidade = universidade;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // ...........SET............
    public void setId(int id) {
        this.id = id;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUniversidade(String univeridade) {
        this.universidade = univeridade;
    }

    public void setCidadeNascimento(String cidadeNascimeto) {
        this.cidadeNascimento = cidadeNascimeto;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    // .............GETS...................
    public int getId() {
        return id;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getUniversidade() {
        return universidade;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    // Clone
    public Player Clone() {
        Player Clone = new Player();
        Clone.id = this.id;
        Clone.nome = this.nome;
        Clone.altura = this.altura;
        Clone.peso = this.peso;
        Clone.universidade = this.universidade;
        Clone.anoNascimento = this.anoNascimento;
        Clone.cidadeNascimento = this.cidadeNascimento;
        Clone.cidadeNascimento = this.estadoNascimento;
        return Clone;
    }

    // Ler
    public void Ler(String Dados) {
        // 103,Milo Komenich,201,96,University of Wyoming,1920,,,
        Dados = Dados + ',';
        Dados = Dados.replace(",,,", ",nao informado,nao informado,");
        Dados = Dados.replace(",,", ",nao informado,");

        String[] subDados = Dados.split(",");
        this.id = Integer.parseInt(subDados[0]);
        this.nome = subDados[1];
        this.altura = Integer.parseInt(subDados[2]);
        this.peso = Integer.parseInt(subDados[3]);
        this.universidade = subDados[4];
        this.anoNascimento = Integer.parseInt(subDados[5]);
        this.cidadeNascimento = subDados[6];
        this.estadoNascimento = subDados[7];
    }

    public static void Quick(Celula esq, Celula dir,int e, int d, Lista lista) {
		int l = e, r = d;
		Celula left=esq,
			   pivo = esq,
			   right=dir;
		int tamanho = (e + d-2)/2;
			for (int j = 0; j < tamanho; j++, pivo = pivo.prox);
		Player Pi = pivo.prox.p;
		while (l <= r) {
			while (left.p.getEstadoNascimento().compareTo(Pi.getEstadoNascimento()) < 0 ||( left.p.getEstadoNascimento().compareTo(Pi.getEstadoNascimento()) == 0 && left.p.getNome().compareTo(Pi.getNome()) < 0) ){
				 l++;
				 left=left.prox;
			}
            while (right.p.getEstadoNascimento().compareTo(Pi.getEstadoNascimento()) > 0||( right.p.getEstadoNascimento().compareTo(Pi.getCidadeNascimento()) == 0 && right.p.getNome().compareTo(Pi.getNome()) > 0)){
				r--;
				right = right.ant;
			}
            if (l <= r ) {
                swap(left, right);
                l++;
				r--;
				left=left.prox;
				right = right.ant;
			}
			MyIO.println(Pi.getNome());
		}
		MyIO.println("Vazari");
		MyIO.println(l+"  "+d);
		if (e < r)  Quick(esq, right,e,r,lista);
        if (l < d)  Quick(left, dir,l,d,lista);
    }

    public static void swap(Celula a, Celula b) {
		Player tmp = a.p;
		a.p   = b.p;
		b.p   = tmp;
	}

    // Imprimir o Resultado
    public void imprimir() {
        MyIO.println("["+ id +" ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
                + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]");
    }
}

