/**
 * @author: Guilherme Cosso
 * @version: 1
 * @since: 02/11/2020
 */

import java.io.*;
import java.util.*;
import java.nio.charset.*;

//Main onde Sao executadas as Principais Chamadas e comandos;
class Principal {
   final static int TamArq = 3922;
    public static void main(String[] args) throws Exception {
         TimeExecution t = new TimeExecution();  
         t.start();
         Arvore bin = new Arvore();
         Player[] p = new Player[TamArq];
         p = saveArk();
         PubInNumbers(bin,p,t);
         Ocorrencia(bin,t);
         t.stop();  
         log(t); 
    }

   public static void log(TimeExecution t) throws Exception {
      FileWriter arqLogWrite = new FileWriter("655297_ArvoreBinaria.txt");
      arqLogWrite.write("655297" + "\t" + t.getTempo() + " MiliSec.\t" + t.getcomp() + " Comparacoes\t");
      arqLogWrite.close();
  }
    /** 
     * @metod: Ler um arquivo e instaciar os Objetos
     * @return: Vetor de Objeto
     */
    public static Player[] saveArk() {
        Player[] p = new Player[TamArq];
        Arq.openRead("./players.csv");
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
     * @ method: Ler os Ids dos PubIn e salva em um vector
     * @param: Arvore Bin;
     * @param: Player P;
     */      
    public static void PubInNumbers(Arvore bin, Player[] p, TimeExecution t)throws Exception {
        String str = MyIO.readLine();
        while (!str.contains("FIM")) {            
            bin.inserir(p[Integer.parseInt(str)],t);   
            str = MyIO.readLine();     
        }       
    }
   /** 
     * @ method: Ler os Nomes a Serem Pesquisados
     * @param: Arvore Bin;
     */
    public static void Ocorrencia(Arvore bin, TimeExecution t){
        String str = MyIO.readLine();
        while (!str.contains("FIM")) {
            MyIO.print(str + " raiz ");            
            bin.pesquisar(str,t);
            str = MyIO.readLine();     
        }
    }
}

//Classe No Caontendo um Jogador
class No {
	public Player jogador; // Elemeto da Arvore
	public No esq , dir; // Aponta para seus Filhos.

	/**
	 * Construtor do Nó.
	 */
	public No(Player jogador) {
		this(jogador, null, null);
	}
	/**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */
	public No(Player jogador, No esq, No dir) {
	  this.jogador = jogador;
	  this.dir = null;
	  this.esq = null;
	}
}
//Classe ARVORE:
class Arvore{
    private No raiz; // Raiz da arvore.
    /**
     * Costrutor da Arvore
     */
    public Arvore(){
        raiz = null;
    }

    /**
	 * Metodo publico iterativo para inserir elemento.
	 * @param Jogador a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Player jogador, TimeExecution t) throws Exception {
		raiz = inserir(jogador, raiz,t);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */

	private No inserir(Player j, No i,TimeExecution t) throws Exception {
		if (i == null) {
         i = new No(j);
         t.add(1);

      } else if (j.getNome().compareTo(i.jogador.getNome()) < 0) {
         i.esq = inserir(j, i.esq,t);
         t.add(2);
      } else if (j.getNome().compareTo(i.jogador.getNome()) > 0) {
         i.dir = inserir(j, i.dir,t);
         t.add(3);
      } else {
         t.add(3);
         throw new Exception("Erro ao inserir!");
      }
      return i;
    }

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */

	 public void pesquisar(String nome, TimeExecution t) {
        Boolean resp = pesquisar(nome, raiz,t);
        if(resp ==true){
           MyIO.print("SIM\n");
           t.add(1);
        }
        else{
           MyIO.print("NAO\n");
           t.add(1);
        }
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	
	private boolean pesquisar(String nome, No i, TimeExecution t) {
      boolean resp;
		if (i == null) {
         resp = false;
         t.add(1);

      } else if (nome.compareTo(i.jogador.getNome()) == 0) {
         resp = true;
         t.add(2);

      } else if (nome.compareTo(i.jogador.getNome()) < 0) {
         MyIO.print("esq ");
         t.add(3);
         resp = pesquisar(nome, i.esq,t);

      } else {
         MyIO.print("dir ");
         resp = pesquisar(nome, i.dir,t);
         t.add(3);
      }
      return resp;
	}

    public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
    }

    private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.jogador.getNome() + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
    }
}

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

    // Imprimir o Resultado
    public void imprimir() {
        MyIO.println("## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
                + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + " ##");
    }
}
// Tempo Execucao
class TimeExecution{
   private double temporizador;
   private int comp;
   TimeExecution() {
       temporizador = comp= 0;
   }
   public void start() {
       temporizador = new Date().getTime();
   }
   public void stop() {
       temporizador = ((new Date().getTime()) - temporizador) / 1000;
   }
   public void add(int comp){
      this.comp+=comp;
   }
   public double getTempo() {
       return temporizador;
   }
   public int getcomp() {
      return comp;
  }
}
// Classe Arq
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