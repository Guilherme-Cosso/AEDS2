import java.util.*;
import java.io.File;

class Classe {
    final static int TamArq = 3922;
    static int tamId = 0, tamNomes = 0, comp = 0;
    
    public static void main(String[] args) {
        long x = new Date().getTime();
        Player[] p = new Player[TamArq];
        p = saveArk();
        int[] id = PubInNumbers();
        OrdenarAltura(id, p);
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
        Arq.openRead("/players.csv");
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
     * @ method: Ordena as Alturas
     * @ paran: Vetor de Objetos
     * @ paran: Vetor de Inteiros(ID)
     */
    public static void OrdenarAltura(int[] id, Player[] p) {
        int moreelemet=-1;
        int l=0;
        String menor="";

        //For que busca o maior elemento do Array de Altura 
        for(int i=0; i<id.length;i++){
            if(moreelemet<p[id[i]].getAltura())
                moreelemet=p[id[i]].getAltura();
        }

        //Array para contar o numero de ocorrencias de cada elemento
        int[][] count = new int[moreelemet+1][id.length];
       
        //For que 0 a matriz
        for(int i=0; i<moreelemet+1;i++)
            for(int j=0;j<id.length;j++)    
                count[i][j]=0;
         
        //For que Add +1 para pos n && id em frente as alturas
        for(int i=0; i<id.length;i++){
            count[p[id[i]].getAltura()][0]++;
            count[p[id[i]].getAltura()][count[p[id[i]].getAltura()][0]] = id[i];     
        }

        // For que  Ordena por Nomes
        for(int i=0; i<moreelemet+1;i++){
            for(int j=0;j<id.length;j++){  
                if(count[i][j]==0)   
                    j=id.length+1;
                else{
                    for(int x = 1; x < count[i][0]; x++){
                        int z=0;
                        int indice = x;
                        menor = p[count[i][x]].getNome();
                        for (z = (x + 1); z < count[i][0]+1; z++){
                            if (menor.compareTo(p[count[i][z]].getNome())>0){
                                menor = p[count[i][z]].getNome();
                                indice=z;
                            }
                        }
                        swap(i,x,indice,count);
                    }
                }   
            }
        }
        l=0;
        for(int i=0; i<moreelemet+1;i++){
              if(count[i][0]!=0){
                    for(int j=1;j<=count[i][0];j++){
                        id[l]=count[i][j];
                        l++;
                    }
              }   
        }
        //System.out.println(Arrays.toString(id));
    }
    
    /*
     * @ method Troca duas posiçoes de lugar na Matriz
     * @ paran: inteiro i
     * @ paran: inteiro j
     * @ paran: inteiro l
     * @ paran: Matriz de array
     */
    public static void swap(int i, int j, int l,int[][] array) {
        int temp = array[i][j];
        array[i][j] = array[i][l];
        array[i][l] = temp;
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
// tio Sukita
