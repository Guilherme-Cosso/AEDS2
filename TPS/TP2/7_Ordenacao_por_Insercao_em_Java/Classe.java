import java.util.*;
class Classe {
    final static int TamArq = 3922;
    static int tamId = 0, tamNomes = 0, comp = 0;

    public static void main(String[] args) {
        long x = new Date().getTime();
        Player[] p = new Player[TamArq];
        p = saveArk();
        int[] id = PubInNumbers();
        id = OrdenarNomes(id, p);
        // System.out.println(Arrays.toString(nomes));
        // System.out.println(Arrays.toString(id));
        // System.out.println(Arrays.toString(nomesId));
        Printar(p, id);
        long y = new Date().getTime();
        x = y - x;
        log(x, comp);
    }

    /*
     * @ method: Ler um arquivo e instaciar os Objetos
     * @ return: Vetor de Objeto
     */
    public static Player[] saveArk() {
        Player[] p = new Player[TamArq];
        Arq.openRead("players.csv");
        String Dado = Arq.readLine();
        int i = 0;
        while (Arq.hasNext() == true) {
            Dado = Arq.readLine();
            p[i] = new Player();
            p[i].Ler(Dado);
            i++;
        }
        Arq.close();
        return p;
    }

    /*
     * @ method: Ler os Ids dos PubIn e salva em um vector
     * @ return: vetor de Ids
     */
    public static int[] PubInNumbers() {
        String dados = "", str = MyIO.readLine();
        while (!str.contains("FIM")) {
            dados += str + ",";
            tamId++;
            str = MyIO.readLine();
        }
        String[] vector = dados.split(",");
        int[] resp = new int[tamId];
        for (int i = 0; i < tamId; i++)
            resp[i] = Integer.parseInt(vector[i]);
        return resp;
    }

    /*
     * @ method: Ler um arquivo e instaciar os Objetos
     * @ paran: Vetor de Objetos
     * @ paran: Vetor de Inteiros(ID)
     */
    public static int[] OrdenarNomes(int[] id, Player[] p) {
        int n = tamId;
        for (int i = 1; i < n; i++) {
            int tmp = p[id[i]].getAnoNascimento(), aux = id[i];
            int j = i - 1;
            String tmp2 = p[id[i]].getNome();
            while ((j >= 0) && ( (p[id[j]].getAnoNascimento() > tmp) ||  p[id[j]].getAnoNascimento() == tmp && 0 <= p[id[j]].getNome().compareTo(tmp2) ) ) {
                comp+=4;
                id[j + 1] = id[j];
                j--;
            }
            id[j + 1] = aux;
        }
        return id;
    }

    /*
     * @ method: Ler um arquivo e instaciar os Objetos
     * @ paran: Vetor de Objetos
     * @ paran: Vetor de Inteiros(ID)
     */
    public static void Printar(Player[] p, int[] id) {
        for (int i = 0; i < id.length; i++)
            p[id[i]].imprimir();
    }

    /*
    * @ method: Escreve em um arquivo
    * @ paran: Long (Time execution)
    * @ paran: Int (Numero de comaracoes)
    */
    public static void log(long timeExecution, int NumCompare) {
        Arq.openWrite("matrıcula_insercao.txt");
        Arq.print("Matricula: 655297 | Tempo de Exec: " + timeExecution + " mls | Numero de Comparacoes: " + NumCompare);
        Arq.close();
    }
}

// ----------------------------|CLASSE+PLAYER|-------------------------------------\\

class Player {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    // Constructor
    Player() {
    }

    Player(int id, int altura, int peso, int anoNascimento, String nome, String universidade, String cidadeNascimento,
            String estadoNascimento) {
        this.id = id;
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

    // Imprimir o Resultado
    public void imprimir() {
        MyIO.println("[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
                + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]");
    }
}