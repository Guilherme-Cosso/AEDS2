/**
 * @author: Guilherme Cosso
 * @version: 1
 * @since: 02/12/2020
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
         Alvinegra bin = new Alvinegra();
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

  /*
   * @ method: Ler os Ids dos PubIn e salva em um vector
   * @ return: vetor de Ids
   */      
  public static void PubInNumbers(Alvinegra bin, Player[] p, TimeExecution t)throws Exception {
      String str = MyIO.readLine();
      while (!str.contains("FIM")) {            
          bin.inserir(p[Integer.parseInt(str)],t);   
          str = MyIO.readLine();     
      }       
  }

  public static void Ocorrencia(Alvinegra bin, TimeExecution t){
      String str = MyIO.readLine();
      while (!str.contains("FIM")) {
          MyIO.print(str + " raiz ");            
          bin.pesquisar(str);
          str = MyIO.readLine();     
      }
  }
}

//Classe No Caontendo um Jogador
class No {
	public boolean cor;
	public Player jogador; // Elemeto da Arvore
	public No esq, dir; // Aponta para seus Filhos.

	public No(){
		this(null);
	}

	public No(Player jogador){
		this(jogador, false, null, null);
	}

	public No(Player jogador, boolean cor){
		this(jogador, cor, null, null);
	}

	public No(Player jogador, boolean cor, No esq, No dir){
	this.cor = cor;
	this.jogador = jogador;
	this.esq = esq;
	this.dir = dir;
	}
}
//Classe ARVORE:
class Alvinegra {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public Alvinegra() {
		raiz = null;
	}

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */

   public void pesquisar(String nome) {
      Boolean resp = pesquisar(nome, raiz);
      if(resp ==true)
          MyIO.print("SIM\n");
      else
          MyIO.print("NAO\n");
 }

 /**
  * Metodo privado recursivo para pesquisar elemento.
  * @param x Elemento que sera procurado.
  * @param i No em analise.
  * @return <code>true</code> se o elemento existir,
  * <code>false</code> em caso contrario.
  */
 
 private boolean pesquisar(String nome, No i) {
    boolean resp;
    if (i == null) {
       resp = false;

    } else if (nome.compareTo(i.jogador.getNome()) == 0) {
       resp = true;

    } else if (nome.compareTo(i.jogador.getNome()) < 0) {
       MyIO.print("esq ");
       resp = pesquisar(nome, i.esq);

    } else {
       MyIO.print("dir ");
       resp = pesquisar(nome, i.dir);
    }
    return resp;
 }
	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarCentral() {
		System.out.print("[ ");
		mostrarCentral(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarCentral(No i) {
		if (i != null) {
			mostrarCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.jogador.getNome() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
			mostrarCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPre() {
		System.out.print("[ ");
		mostrarPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarPre(No i) {
		if (i != null) {
			System.out.print(i.jogador.getNome() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
			mostrarPre(i.esq); // Elementos da esquerda.
			mostrarPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPos() {
		System.out.print("[ ");
		mostrarPos(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarPos(No i) {
		if (i != null) {
			mostrarPos(i.esq); // Elementos da esquerda.
			mostrarPos(i.dir); // Elementos da direita.
			System.out.print(i.jogador.getNome() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
		}
	}


	/**
	 * Metodo publico iterativo para inserir elemento.
	 * @param elemento Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Player jogador, TimeExecution t) throws Exception {
   
      //Se a arvore estiver vazia
      if(raiz == null){
         raiz = new No(jogador, false);

      //Senao, se a arvore tiver um elemento 
      } else if (raiz.esq == null && raiz.dir == null){
         if (raiz.jogador.getNome().compareTo(jogador.getNome()) > 0 ){
            raiz.esq = new No(jogador, true);
         } else {
            raiz.dir = new No(jogador, true);
         }

      //Senao, se a arvore tiver dois elementos (raiz e dir)
      } else if (raiz.esq == null){

         if(raiz.jogador.getNome().compareTo(jogador.getNome()) > 0){
            raiz.esq = new No(jogador);
         } else if (raiz.dir.jogador.getNome().compareTo(jogador.getNome())>0){
            raiz.esq = new No(raiz.jogador);
            raiz.jogador = jogador;
         } else {
            raiz.esq = new No(raiz.jogador);
            raiz.jogador = raiz.dir.jogador;
            raiz.dir.jogador = jogador;
         }
         raiz.esq.cor = raiz.dir.cor = false;
      //Senao, se a arvore tiver dois elementos (raiz e esq)
      } else if (raiz.dir == null){
         
         if(raiz.jogador.getNome().compareTo(jogador.getNome()) < 0){
            raiz.dir = new No(jogador);
         } else if (raiz.jogador.getNome().compareTo(jogador.getNome()) < 0){
            raiz.dir = new No(raiz.jogador);
            raiz.jogador = jogador;
         } else {
            raiz.dir = new No(raiz.jogador);
            raiz.jogador = raiz.esq.jogador;
            raiz.esq.jogador = jogador;
         }

         raiz.esq.cor = raiz.dir.cor = false;

      //Senao, a arvore tem tres ou mais elementos
      } else {
   	   inserir(jogador, null, null, null, raiz);
      }

      raiz.cor = false;
   }

   private void balancear(No bisavo, No avo, No pai, No i){

      //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
      if(pai.cor == true){

         //4 tipos de reequilibrios e acoplamento
         if(pai.jogador.getNome().compareTo(avo.jogador.getNome()) > 0){ // rotacao a esquerda ou direita-esquerda
            if(i.jogador.getNome().compareTo(pai.jogador.getNome()) > 0){
               avo = rotacaoEsq(avo);
            } else {
               avo = rotacaoDirEsq(avo);
            }

         } else { // rotacao a direita ou esquerda-direita
            if(i.jogador.getNome().compareTo(pai.jogador.getNome()) < 0){
               avo = rotacaoDir(avo);
            } else {
               avo = rotacaoEsqDir(avo);
            }
         }

         if (bisavo == null){
            raiz = avo;
         } else {
            if(avo.jogador.getNome().compareTo(bisavo.jogador.getNome()) < 0){
               bisavo.esq = avo;
            } else {
               bisavo.dir = avo;
            }
         }

         //reestabelecer as cores apos a rotacao
         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
      } //if(pai.cor == true)
   }

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param elemento Elemento a ser inserido.
	 * @param avo No em analise.
	 * @param pai No em analise.
	 * @param i No em analise.
	 * @throws Exception Se o elemento existir.
	 */
	private void inserir(Player jogador, No bisavo, No avo, No pai, No i) throws Exception {
		if (i == null) {

         if(jogador.getNome().compareTo(pai.jogador.getNome()) < 0){
            i = pai.esq = new No(jogador, true);
         } else {
            i = pai.dir = new No(jogador, true);
         }

         if(pai.cor == true){
            balancear(bisavo, avo, pai, i);
         }

      } else {

        //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
         if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if(i == raiz){
               i.cor = false;
            }else if(pai.cor == true){
               balancear(bisavo, avo, pai, i);
            }
         }
         if (jogador.getNome().compareTo(i.jogador.getNome()) <0) {
            inserir(jogador, avo, pai, i, i.esq);
         } else if (jogador.getNome().compareTo(i.jogador.getNome()) > 0) {
            inserir(jogador, avo, pai, i, i.dir);
         } else {
            throw new Exception("Erro inserir (elemento repetido)!");
         }
      }
	}

   private No rotacaoDir(No no) {

      No noEsq = no.esq;
      No noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      return noEsq;
   }

   private No rotacaoEsq(No no) {

      No noDir = no.dir;
      No noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   private No rotacaoDirEsq(No no) {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   private No rotacaoEsqDir(No no) {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
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