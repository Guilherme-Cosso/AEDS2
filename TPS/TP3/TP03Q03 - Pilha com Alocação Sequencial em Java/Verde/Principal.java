import java.util.Formatter;
import java.util.Scanner;
import java.io.File;


/*-----------------|Classe Main|--------------*/
class Principal {
      final static int TamArq = 3922;
      static int tamId = 0, tamNomes = 0, comp = 0;
  
      public static void main(String[] args) throws Exception {
          Pilha pilha = new Pilha();
          Player[] p = new Player[TamArq];
          p = saveArk();
          PubInNumbers(pilha);
          int acoes = MyIO.readInt();
          Ocorrencia(acoes, pilha, p);
          Printar( pilha,p);
          
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
  
      /**
       * @metod: Ler os Ids dos PubIn e salva em uma Pilha
       * @param Pilha
       */
      public static void PubInNumbers(Pilha pilha)throws Exception {
          String dados = "", str = MyIO.readLine();
          while (!str.contains("FIM")) {
              dados += str + ",";
              tamId++;
              str = MyIO.readLine();
          }
          String[] vector = dados.split(",");
          for (int i = 0; i < tamId; i++){
              pilha.inserir(Integer.parseInt(vector[i]));
          }       
      }
  
      /**
      * Construtor da classe.
      * @param Pilha A pilha.
      * @param cont Tamanho da pilha.
      * @param Player Vetor de jogadores.
     */
      public static void Ocorrencia(int cont,Pilha pilha,Player[] p)throws Exception {
          for(int i=0;i<cont;i++){
              String linha = MyIO.readLine();
              String parte[] = linha.split(" ");
              if(parte[0].charAt(0) =='R'){
                 MyIO.println("(R) "+ p[pilha.remover()].getNome());
              }
              else{
                      pilha.inserir(Integer.parseInt(parte[1]));   
                  }
          }
      }
  
      public static void Printar(Pilha pilha, Player[] p){
         for(int i = 0;i < pilha.getN();i++){
             MyIO.print("["+ i +"] ");
             p[pilha.dados(i)].imprimir();
         }
     }
}
  

/*-----------------|Classe  P  Estatica|--------------*/
class Pilha {
	private int[] array;
	private int n;

   /**
    * Construtor da classe.
    */
	public Pilha() {
		this(200);
	}


	/**
    * Construtor da classe.
    * @param tamanho Tamanho da lista.
	*/
	
	public Pilha (int tamanho){
		array = new int[tamanho];
		n = 0;
	 }



	/**
	 * Insere elemento na pilha (politica FILO).
	 * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
	 */

	public void inserir(int x)throws Exception {

		//validar insercao
      	if(n >= array.length){
       	  throw new Exception("Erro ao inserir!");
     	}
      array[n] = x;
      n++;
   }

	/**
	 * Remove elemento da pilha (politica FILO)
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
	 */

	public int remover() throws Exception {
		//validar remocao
		if (n == 0) {
			throw new Exception("Erro ao remover!");
		 }
   
		 return array[--n];
	}
	

	/**
	 * Mostra os elementos separados por espacos, comecando do topo.
	 */

	public void mostrar (){
		System.out.print("[ ");
		for(int i = n-1; i >= 0; i--){
		   System.out.print(array[i] + " ");
		}
		System.out.println("]");
	 }

	 public int dados (int pos){
		int resp = array[pos];
		return resp;
	 }

	public int getN() {
		return n;
	}

}
 // ----------------------------|CLASSE PLAYER|-------------------------------------\\

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
        MyIO.println("## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
                + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + " ##");
    }
}

// ----------------------------|CLASSE ARQ|-------------------------------------\\


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