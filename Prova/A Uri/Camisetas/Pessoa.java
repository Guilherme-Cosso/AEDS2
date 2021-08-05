class Pessoa {
	private String nome, cor;
	private char tamanho;
    // Constructor
    Pessoa() {
    }

    Pessoa(String nome, String cor, char tamanho) {
        this.nome = nome;
        this.cor = cor;
        this.tamanho = tamanho;
    }

    // ...........SET............

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setTamaho(char tamanho) {
        this.tamanho = tamanho;
    }

    // .............GETS...................
    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public char getTamaho() {
        return tamanho;
    }

    // Ler
    public void Ler() {
		String nome  =  MyIO.readLine();
		String Dados =  MyIO.readLine();
		// Maria Jose
		// branco P
        String[] subDados = Dados.split(" ");
		this.nome = nome;
        this.cor = subDados[0];
        this.tamanho = subDados[1].charAt(0);
	}

	public void imprimir() {
        MyIO.println(cor + " " + tamanho+ " "+nome);
    }
	
}

