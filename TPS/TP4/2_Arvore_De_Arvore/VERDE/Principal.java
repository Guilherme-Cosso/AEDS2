/**
 * @author: Guilherme Cosso
 * @version: 1
 * @since: 25/11/2020
 */

import java.util.*;
import java.io.*;

class Principal {
    final static int TamArq = 3922;

    public static void main(String[] args) throws Exception {    
        TimeExecution t = new TimeExecution();
        t.start();
        Arvore bin = new Arvore();
        int[] array = { 7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13, 14 };
        bin.inserirMood15(array,t);
        Player[] p = new Player[TamArq];
        p = saveArk();
        PubInNumbers(bin, p, t);
        Ocorrencia(bin, t);
        t.stop();
        log(t);
    }
    public static void log(TimeExecution t) throws Exception {
        FileWriter arqLogWrite = new FileWriter("655297_ArvoreDeArvore.txt");
        arqLogWrite.write("655297" + "\t" + t.getTempo() + " MiliSec.\t" + t.getcomp() + " Comparacoes\t");
        arqLogWrite.close();
    }

    /**
     * @metod: Ler um arquivo e instaciar os Objetos
     * @return: Vetor de Objeto
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
     * @ return: vetor de Ids
     */
    public static void PubInNumbers(Arvore bin, Player[] p, TimeExecution t) throws Exception {
        String str = MyIO.readLine();
        while (!str.contains("FIM")) {
            bin.inserir(p[Integer.parseInt(str)],t);
            str = MyIO.readLine();
        }
    }

    public static void Ocorrencia(Arvore bin, TimeExecution t) {
        String str = MyIO.readLine();
        while (!str.contains("FIM")){
            MyIO.print(str + " raiz ");
            bin.pesquisar(str, t);
            str = MyIO.readLine();
        }
    }
}


// No Altura

class NoAlt {
    public int altura; // Elemeto da Arvore
    public NoNome l;
	public NoAlt esq , dir; // Aponta para seus Filhos.

	/**
	 * Construtor do Nó.
	 */
	public NoAlt(int altura) {
		this(altura, null, null , null);
	}
	/**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */
	public NoAlt(int altura ,NoNome l,NoAlt esq, NoAlt dir) {
	  this.altura = altura;
	  this.dir = null;
      this.esq = null;
      this.l = null;
	}
}

// No Nome
class NoNome {
	public Player jogador; // Elemeto da Arvore
	public NoNome esq , dir; // Aponta para seus Filhos.

	/**
	 * Construtor do Nó.
	 */
	public NoNome(Player jogador) {
		this(jogador, null, null);
	}
	/**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */
	public NoNome(Player jogador, NoNome esq, NoNome dir) {
	  this.jogador = jogador;
	  this.dir = null;
	  this.esq = null;
	}
}

// Arvore

class Arvore {

	private NoAlt raiz; // Raiz da arvore.

	public Arvore() {
		raiz = null;
	}

	public void inserirMood15(int array[], TimeExecution t) {
		for (int i = 0; i < 15; i++)
			raiz = inserirMood15(array[i], raiz, t);
	}

	private NoAlt inserirMood15(int num, NoAlt i, TimeExecution t) {
		if (i == null) {
			i = new NoAlt(num);
			t.add(1);
		} else if (num < i.altura) {
			i.esq = inserirMood15(num, i.esq, t);
			t.add(2);
		} else if (num > i.altura) {
			i.dir = inserirMood15(num, i.dir, t);
			t.add(3);
		}
		return i;
	}

	/**
	 * Metodo publico iterativo para inserir elemento.
	 * 
	 * @param Jogador a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Player jogador, TimeExecution t) {
		int mood = jogador.getAltura() % 15;
		raiz = inserirAltura(jogador, raiz, mood, t);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * 
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 */

	private NoAlt inserirAltura(Player j, NoAlt i, int mood, TimeExecution t) {
		if (mood < i.altura) {
			i.esq = inserirAltura(j, i.esq, mood, t);
			t.add(1);
		} else if (mood > i.altura) {
			i.dir = inserirAltura(j, i.dir, mood, t);
			t.add(2);
		} else {
			i.l = inseriNoNome(j, i.l, t);
			t.add(2);
		}
		return i;
	}

	private static NoNome inseriNoNome(Player j, NoNome n, TimeExecution t) {
		if (n == null) {
			n = new NoNome(j);
			t.add(1);
		} else if (j.getNome().compareTo(n.jogador.getNome()) < 0) {
			n.esq = inseriNoNome(j, n.esq, t);
			t.add(2);
		} else if (j.getNome().compareTo(n.jogador.getNome()) > 0) {
			n.dir = inseriNoNome(j, n.dir, t);
			t.add(3);
		}
		return n;
	}

	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	private void caminharPre(NoAlt i) {
		if (i != null) {
			MyIO.print("-> "+i.altura);
			caminharNome(i.l);
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

	private void caminharNome(NoNome i) {
		if (i != null) {
			caminharNome(i.esq);
			caminharNome(i.dir);
			System.out.print(i.jogador.getNome() + " | "); // Conteudo do no.
		}
	}

	public void pesquisar(String nome, TimeExecution t) {
		boolean resp = false;
		resp = PesquisarMood(raiz, nome, t, resp);
		if (resp == true) {
			MyIO.print("SIM\n");
		} else
			MyIO.print("NAO\n");
		t.add(1);
	}

	private boolean PesquisarMood(NoAlt i, String nome, TimeExecution t, Boolean resp) {
		if (i != null && resp == false) {
			resp = PesquisarNome(nome, i.l, t, resp);
			if (resp == false) {
				MyIO.print("esq ");
				resp = PesquisarMood(i.esq, nome, t, resp); // Elementos da esquerda.
			}
			if (resp == false) {
				MyIO.print("dir ");
				resp = PesquisarMood(i.dir, nome, t, resp);// Elementos da direita
			}
		}
		return resp;
	}

	private boolean PesquisarNome(String nome, NoNome i, TimeExecution t, Boolean resp) {
		if (i == null) {
			resp = false;
			t.add(1);
		} else{
			if (nome.compareTo(i.jogador.getNome()) == 0) {
				resp = true;
				t.add(2);
			} 
			if (resp == false){
				MyIO.print("ESQ ");
				t.add(3);
				resp = PesquisarNome(nome, i.esq, t, resp);
			} 
			if (resp == false){
				MyIO.print("DIR ");
				resp = PesquisarNome(nome, i.dir, t, resp);
				t.add(4);
			}
		}
		return resp;
	}
}

// Player
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

// Time E
// Tempo Execucao + Num de Comps

class TimeExecution{
    private double temporizador;
    private int comp;
    TimeExecution() {
        temporizador = comp = 0;
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