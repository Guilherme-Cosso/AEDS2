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
        // System.out.println(Arrays.toString(id));
        id = OrdenarAltura(id, p);
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
        Arq.openRead("/tmp/players.csv");
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
     * 
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

    public static int[] OrdenarAltura(int[] id, Player[] p) {
        // Vetor chegando
        //Printf(id, 0, p);
        // Alterar o vetor ignorando a posicao zero
        int[] novoId = tirarPosZero(id);
        int n = novoId.length;
        //Printf(novoId, 1, p); 
        // Contrucao do heap
        for (int tamHeap = 2; tamHeap < n; tamHeap++) {
            constroi(tamHeap, p, novoId);
        }
        //Printf(novoId, 1, p);
        // Ordenacao propriamente dita 
        int tamHeap = n-1;
        while(tamHeap > 1){
           swap(1, tamHeap--,novoId);
           reconstruir(tamHeap,novoId,p);
        }
        //Voltar para o 0. 
        id = porPosZero(novoId); 
        return id;
    }

    public static int[] tirarPosZero(int[] array) {
        int n = array.length;
        int[] tmp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            tmp[i + 1] = array[i];
        }
        array = tmp;
        return array;
    }

    public static void constroi(int tamHeap, Player[] p, int[] array) {
        for (int i = tamHeap; i > 1 && ( p[array[i]].getAltura() > p[array[i / 2]].getAltura() || ( p[array[i]].getAltura() == p[array[i / 2]].getAltura() && p[array[i]].getNome().compareTo(p[array[i / 2]].getNome())>0)); i /= 2) {
            swap(i, i / 2, array);
        }
    }

    public static void ordenarHeap(int[] array, Player[] p) {
        int tamHeap = array.length - 1;
        while (tamHeap > 1) {
            swap(1, tamHeap--, array);
            reconstruir(tamHeap, array, p);
        }
    }

    public static int[] reconstruir(int tamHeap, int[] array, Player[] p) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(i, tamHeap, array, p);
            if (p[array[i]].getAltura() < p[array[filho]].getAltura() ||(p[array[i]].getAltura() == p[array[filho]].getAltura() && p[array[i]].getNome().compareTo(p[array[filho]].getNome())<0)){
                swap(i, filho, array);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
        return array;
    }

    public static int getMaiorFilho(int i, int tamHeap, int[] array, Player[] p) {
        int filho;
        if (2 * i == tamHeap ||( p[array[2 * i]].getAltura() > p[array[2 * i + 1]].getAltura() || ( p[array[2 * i]].getAltura() == p[array[2 * i + 1]].getAltura() && p[array[2 * i]].getNome().compareTo(p[array[2 * i + 1]].getNome())>0))){
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] porPosZero(int[] id) {
        int[] tmp = id;
        id = new int[id.length - 1];
        for (int i = 0; i < id.length; i++) {
            id[i] = tmp[i + 1];
        }
        return id;
    }

    public static void Printf(int[] id, int n, Player[] p) {
        for (int i = n; i < id.length; i++) {
            MyIO.print(p[id[i]].getAltura() + ", ");
        }
        MyIO.print("\n");
    }

    public static void Printar(Player[] p, int[] id) {
        for (int i = 0; i < id.length; i++)
            p[id[i]].imprimir();
    }

    public static void log(long timeExecution, int NumCompare) {
        Arq.openWrite("matrıcula sequencial.txt");
        Arq.print("Matricula: 655297 | Tempo de Exec: " + timeExecution + "| Numero de Comparacoes" + NumCompare);
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
// Tio sukita

class Arq
{
   private static String nomeArquivo = "";
   private static String charsetArquivo = "ISO-8859-1";
   private static boolean write = false, read = false;
   private static Formatter saida = null;
   private static Scanner entrada = null;

	public static boolean openWrite(String nomeArq, String charset) {
      boolean resp = false;
      close();
		try{
		   saida = new Formatter(nomeArq, charset);
         nomeArquivo = nomeArq;
         resp = write = true;
		}  catch (Exception e) {}
      return resp;
   }

	public static boolean openWrite(String nomeArq) {
      return openWrite(nomeArq, charsetArquivo);
   }

	public static boolean openWriteClose(String nomeArq, String charset, String conteudo) {
      boolean resp = openWrite(nomeArq, charset);
      if(resp == true){
         println(conteudo);
         close();
      }
      return resp;
   }

	public static boolean openWriteClose(String nomeArq, String conteudo) {
      return openWriteClose(nomeArq, charsetArquivo, conteudo);
   }

	public static boolean openRead(String nomeArq) {
      return openRead(nomeArq, charsetArquivo);
   }

	public static boolean openRead(String nomeArq, String charset) {
      boolean resp = false;
      close();
		try{
         entrada = new Scanner(new File(nomeArq), charset);
         nomeArquivo = nomeArq;
         resp = read = true;
		}  catch (Exception e) {}
      return resp;
   }

   public static String openReadClose(String nomeArq){
      openRead(nomeArq);
      String resp = readAll();
      close();
      return resp;
   }

	public static void close() {
      if(write == true){
         saida.close();
      }
      if(read == true){
         entrada.close();
      }
      write = read = false;
      nomeArquivo = "";
      charsetArquivo = "ISO-8859-1";
	}

   public static long length(){
      long resp = -1;
      if(read != write){
         File file = new File(nomeArquivo);
         resp = file.length();
      }
      return resp;
   }

   public static void print(int x){
      if(write == true){
		   saida.format( "%d", x);
      }
   }

   public static void print(double x){
      if(write == true){
	   	saida.format( "%f", x);
      }
   }

   public static void print(String x){
      if(write == true){
   		saida.format( "%s", x);
      }
   }

   public static void print(boolean x){
      if(write == true){
		   saida.format( "%s", ((x) ? "true" : "false"));
      }
   }

   public static void print(char x){
      if(write == true){
	   	saida.format( "%c", x);
      }
   }

   public static void println(int x){
      if(write == true){
   		saida.format( "%d\n", x);
      }
   }

   public static void println(double x){
      if(write == true){
		   saida.format( "%f\n", x);
      }
   }

   public static void println(String x){
      if(write == true){
	   	saida.format( "%s\n", x);
      }
   }

   public static void println(boolean x){
      if(write == true){
   		saida.format( "%s\n", ((x) ? "true" : "false"));
      }
   }

   public static void println(char x){
      if(write == true){
		   saida.format( "%c\n", x);
      }
   }

   public static int readInt(){
      int resp = -1;
		try{
         resp = entrada.nextInt();
		}  catch (Exception e) {}
      return resp;
   }

   public static char readChar(){
      char resp = ' ';
		try{
         resp = (char)entrada.nextByte();
		}  catch (Exception e) {}
      return resp;
   }

   public static double readDouble(){
      double resp = -1;
		try{
         resp = Double.parseDouble(readString().replace(",","."));
		}  catch (Exception e) {}
      return resp;
   }

   public static String readString(){
      String resp = "";
		try{
         resp = entrada.next();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
      return resp;
	}

   public static boolean readBoolean(){
      boolean resp = false;
		try{
         resp = (entrada.next().equals("true")) ? true : false;
		}  catch (Exception e) {}
      return resp;
	}

   public static String readLine(){
      String resp = "";
		try{
         resp = entrada.nextLine();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
      return resp;
	}


   public static boolean hasNext(){
      return entrada.hasNext();
   }

   public static String readAll(){
      String resp = "";
      while(hasNext()){
         resp += (readLine() + "\n");
      }
      return resp;
   }
}
