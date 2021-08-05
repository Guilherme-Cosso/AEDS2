/**
 * @author: Guilherme Cosso
 * @version: 1
 * @since: 10/12/2020
 */

import java.util.*;
import java.io.*;

class Principal {
   final static int TamArq = 3922;
   public static void main(String[] args) throws Exception {
      TimeExecution t = new TimeExecution();
      t.start();
      ArvoreTrie arv1 = new ArvoreTrie();
      ArvoreTrie arv2 = new ArvoreTrie();
      Lista nomes = new Lista();
      ArvoreTrie completa = new ArvoreTrie();
      Player[] p = new Player[TamArq];
      p = saveArk();
      PubInNumbers(arv1,arv2,p);
      PegarDados(arv1,arv2,nomes);
      nomes.pegarNome(completa);
      Ocorrencia(completa);
      t.stop();
      log(t);
   }

   public static void log(TimeExecution t) throws Exception {
      FileWriter arqLogWrite = new FileWriter("655297_ArvoreDeArvore.txt");
      arqLogWrite.write("655297" + "\t" + t.getTempo() + " MiliSec.\t" + t.getcomp() + " Comparacoes\t");
      arqLogWrite.close();
  }

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
    * @method: Ler os Ids dos PubIn e salva em 2 arvores Trie
    * @Param ArvoreTrie arv1
    * @Param ArvoreTrie arv2
    * @Param Player[] p
    */

   public static void PubInNumbers(ArvoreTrie arv1 ,ArvoreTrie arv2 ,Player[] p) throws Exception {
      String str = MyIO.readLine();
      while (!str.contains("FIM")) {
         MyIO.println(str+'\t'+p[Integer.parseInt(str)].getNome());
         arv1.inserir(p[Integer.parseInt(str)].getNome());
         str = MyIO.readLine();
      }
      str = MyIO.readLine();
      while (!str.contains("FIM")) {
         MyIO.println(str+'\t'+p[Integer.parseInt(str)].getNome());
         arv2.inserir(p[Integer.parseInt(str)].getNome());
         str = MyIO.readLine();
      }
   }

   public static void PegarDados(ArvoreTrie arv1, ArvoreTrie arv2,Lista Nomes){
      arv1.PegarElemento(Nomes);
      arv2.PegarElemento(Nomes);
   }


   public static void Ocorrencia(ArvoreTrie n)throws Exception{
      String str = MyIO.readLine();
      while (!str.contains("FIM")){
         if(n.pesquisar(str))
            MyIO.print(str + " SIM\n"); 
         else
            MyIO.print(str + " NAO\n"); 
         str = MyIO.readLine();
      }
   }
}

//Classe No
class No {
   public char elemento;
   public int tamanho = 255;
   public No[] prox;
   public boolean folha;
   
   public No (){
      this(' ');
   }

   public No (char elemento){
      this.elemento = elemento;
      prox = new No [tamanho];
      for (int i = 0; i < tamanho; i++) prox[i] = null;
      folha = false;
   }

   public static int hash (char x){
      return (int)x;
   }
}
//Classe Celula
/**
 * Celula (pilha, lista e fila dinamica)
 */
class Celula {
	public String nome;
	public Celula prox; // Aponta a celula prox.
	/**
	 * Construtor da classe.
	 */
	public Celula() {
		this(null);
	}
	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public Celula(String nome) {
	  this.nome = nome;
      this.prox = null;
	}
}

//Classe Arvore Trie
class ArvoreTrie {
   private No raiz;

   public ArvoreTrie(){
       raiz = new No();
   }

   public void inserir(String s) throws Exception {
       inserir(s, raiz, 0);
   }

   private void inserir(String s, No no, int i) throws Exception {
       //System.out.print("\nEM NO(" + no.elemento + ") (" + i + ")");
       if(no.prox[s.charAt(i)] == null){
         //  System.out.print("--> criando filho(" + s.charAt(i) + ")");
           no.prox[s.charAt(i)] = new No(s.charAt(i));

           if(i == s.length() - 1){
               //System.out.print("(folha)");
               no.prox[s.charAt(i)].folha = true;
           }else{
               inserir(s, no.prox[s.charAt(i)], i + 1);
           }

       } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
           inserir(s, no.prox[s.charAt(i)], i + 1);

       } else {
           throw new Exception("Erro ao inserir!");
       } 
   }


   public boolean pesquisar(String s) throws Exception {
       //return pesquisar(s, raiz.prox[32], 0);
       return pesquisar(s, raiz, 0);
   }

   public boolean pesquisar(String s, No no, int i) throws Exception {
       boolean resp;
       if(no.prox[s.charAt(i)] == null){
           resp = false;
       } else if(i == s.length() - 1){
           resp = (no.prox[s.charAt(i)].folha == true);
       } else if(i < s.length() - 1 ){
           resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
       } else {
           throw new Exception("Erro ao pesquisar!");
       }
       return resp;
   }


   public void mostrar(){
       mostrar("", raiz);
   }

   public void mostrar(String s, No no) {
       if(no.folha == true){
           System.out.println("Palavra: " + (s + no.elemento));
       } else {
           for(int i = 0; i < no.prox.length; i++){
               if(no.prox[i] != null){
                   //System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
                   mostrar(s + no.elemento, no.prox[i]);
               }
           }
       }
   }

   public void PegarElemento(Lista nome){
       PegarElemento("", raiz,nome);
   }

   public void PegarElemento(String s, No no, Lista nome){
       if(no.folha == true){
           nome.inserirFim((s + no.elemento));
           //nome.inserirFim((s + no.elemento).substring(1, (s + no.elemento).length()));
       } else {
           for(int i = 0; i < no.prox.length; i++){
               if(no.prox[i] != null){
                   PegarElemento(s + no.elemento, no.prox[i],nome);
               }
           }
       }
   }

   public int contarAs(){
       int resp = 0;
       if(raiz != null){
           resp = contarAs(raiz);
       }
       return resp;
   }

   public int contarAs(No no) {
       int resp = (no.elemento == 'A') ? 1 : 0;

       if(no.folha == false){
           for(int i = 0; i < no.prox.length; i++){
               if(no.prox[i] != null){
                   resp += contarAs(no.prox[i]);
               }
           }
       }
       return resp;
   }
}

//Classe Lista
class Lista {
	private Celula primeiro;
	private Celula ultimo;
	/**
	 * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
	 */
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}
	/**
	 * Insere um elemento na primeira posicao da lista.
	 * 
	 * @param P String elemento a ser inserido.
	 */
	public void inserirInicio(String p) {
		Celula tmp = new Celula(p);
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			ultimo = tmp;
		}
		tmp = null;
	}
	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param p int elemento a ser inserido.
	 */
	public void inserirFim(String nome) {
		ultimo.prox = new Celula(nome);
		ultimo = ultimo.prox;
	}
	/**
	 * Remove um String da primeira posicao da lista.
	 * 
	 * @return resp String a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public String removerInicio() throws Exception {
		if (primeiro == ultimo) throw new Exception("Erro ao remover (vazia)!");
		
		Celula tmp = primeiro;
		primeiro = primeiro.prox;
		String resp = primeiro.nome;
		tmp.prox = null;
		tmp = null;
		return resp;
	}
	/**
	 * Remove um String da ultima posicao da lista.
	 * 
	 * @return resp String a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public String removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}
		// Caminhar ate a penultima celula:
		Celula i;
		for (i = primeiro; i.prox != ultimo; i = i.prox);
		String resp = ultimo.nome;
		ultimo = i;
		i = ultimo.prox = null;
		return resp;
	}
	/**
	 * Insere um elemento em uma posicao especifica considerando que o primeiro
	 * elemento valido esta na posicao 0.
	 * 
	 * @param x   int String a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public void inserir(String nome, int pos) throws Exception {
		int tamanho = tamanho();
		if (pos < 0 || pos > tamanho) {
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0) {
			inserirInicio(nome);
		} else if (pos == tamanho) {
			inserirFim(nome);
		} else {
			// Caminhar ate a posicao anterior a insercao
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox);
			Celula tmp = new Celula(nome);
			tmp.prox = i.prox;
			i.prox = tmp;
			tmp = i = null;
		}
	}
	/**
	 * Remove um elemento de uma posicao especifica da lista considerando que o
	 * primeiro elemento valido esta na posicao 0.
	 * 
	 * @param posicao Meio da remocao.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public String remover(int pos) throws Exception {
		String resp;
		int tamanho = tamanho();
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");

		} else if (pos < 0 || pos >= tamanho) {
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
		} else if (pos == 0) {
			resp = removerInicio();
		} else if (pos == tamanho - 1) {
			resp = removerFim();
		} else {
			// Caminhar ate a posicao anterior a insercao
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox);
			Celula tmp = i.prox;
			resp = tmp.nome;
			i.prox = tmp.prox;
			tmp.prox = null;
			i = tmp = null;
		}
		return resp;
	}
	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.nome + " ");
		}
		System.out.println("] ");
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * 
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir, <code>false</code> em caso
	 *         contrario.
	 */
	public boolean pesquisar(String x) {
		boolean resp = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			if (i.nome.compareTo(x) == 0) {
				resp = true;
				i = ultimo;
			}
		}
		return resp;
	}
	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * 
	 * @return resp int tamanho
	 */
	public int tamanho() {
		int tamanho = 0;
		for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
		return tamanho;
	}
	/**
	 * Mostra todos o Jogadores presentes na Lista. 
	*/
	public void print(){
		int x=0;
		for (Celula i = primeiro.prox; i != ultimo.prox; i = i.prox){
			MyIO.print("["+ x++ +i.nome+"]");
		}
	}
	
	public void pegarNome(ArvoreTrie completa)throws Exception{
		for (Celula i = primeiro.prox; i != ultimo.prox; i = i.prox){
			completa.inserir(i.nome.substring(1,(i.nome).length()));
		}
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
//Class Arq
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

