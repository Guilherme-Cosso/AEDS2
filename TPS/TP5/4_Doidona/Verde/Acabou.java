import java.util.Formatter;
import java.util.Scanner;
import java.io.File;

class Acabou {
    final static int TamArq = 3922;

    public static void main(String[] args) throws Exception {
        Doidona doido = new Doidona();
        Player[] p = new Player[TamArq];
        p = saveArk();
        PubInNumbers(doido,p);
        Ocorrencia(doido,p);        
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
    public static void PubInNumbers(Doidona doi, Player[] p)throws Exception {
        String str = MyIO.readLine();
        while (!str.contains("FIM")) {            
            doi.inserir(p[Integer.parseInt(str)]);   
            str = MyIO.readLine();     
        }       
    }

    public static void Ocorrencia(Doidona doi, Player[] p){
        String str = MyIO.readLine();
        Boolean resp;
        while (!str.contains("FIM")) {
            MyIO.print(str + " ");        
            resp = doi.pesquisar(str);
            SorN(resp);
            str = MyIO.readLine();     
        }
    }
    public static void SorN(Boolean resp){
        if(resp == true)
            MyIO.print("SIM\n");
        else
            MyIO.print("NAO\n");        
    }
}
// Doidona
class Doidona {
    final int TAMT1 = 11;
    final int TAMT3 = 9;
    final String NULO = "";

    String[] t1;
    String[] t3;
    Arvore arvoreBinaria;
    Lista lista;

    public Doidona() {
        t1 = new String[TAMT1];
        t3 = new String[TAMT3];

        for (int i = 0; i < TAMT1; i++) {
            t1[i] = NULO;
        }
        for (int i = 0; i < TAMT3; i++) {
            t3[i] = NULO;
        }

        arvoreBinaria = new Arvore();
        lista = new Lista();
    }

    public int hashT1(int elemento) {
        return elemento % TAMT1;
    }

    public int hashT2(int elemento) {
        return elemento % 3;
    }

    public int hashT3(int elemento) {
        return elemento % TAMT3;
    }

    public int rehashT3(int elemento) {
        return ++elemento % TAMT3;
    }

    public void inserir(Player p) throws Exception {
        inserir(p.getNome(), p.getAltura());
    }

    private void inserir(String nome, int altura) throws Exception {
        int i = hashT1(altura);
        int reserva = hashT2(altura);
        if (nome == NULO) {
            MyIO.println("Nao ha nomes para inserir");
        } else if (t1[i] == NULO) {
            t1[i] = nome;
        } else if (reserva == 0) {
            i = hashT3(altura);
            if (t3[i] == NULO) {
                t3[i] = nome;
            } else {
                i = rehashT3(altura);
                if (t3[i] == NULO) {
                    t3[i] = nome;
                } 
            }
        } else if (reserva == 1) {
            lista.inserirFim(nome);
        } else if (reserva == 2) {
            arvoreBinaria.inserir(nome);
        } else {
            System.out.println("ERRO!!!!");
        }
    }

    public boolean pesquisar(String str) {
        boolean resp = false;

        for (int i = 0; i < TAMT1; i++) {
            if (t1[i].compareTo(str) == 0)
                resp = true;
        }
        if(resp == false){
            for (int i = 0; i < TAMT3; i++) {
                if (t3[i].compareTo(str) == 0)
                    resp = true;
            }
        }
        if(resp == false)
            resp = lista.pesquisar(str);
        if(resp == false){
            MyIO.print("raiz ");        
            resp = arvoreBinaria.pesquisar(str);
        }
        return resp;
    }

    public void mostrar() {
        // t1, t3, arvoreBinaria, lista, arvoreAVL
        for (int i = 0; i < TAMT1; i++) {
            if (t1[i] != NULO) {
                System.out.println(t1[i]);
            }
        }
        for (int i = 0; i < TAMT3; i++) {
            if (t3[i] != NULO) {
                System.out.println(t3[i]);
            }
        }
        arvoreBinaria.caminharPre();
        lista.mostrar();
    }
}

// Arvoree
class Arvore {
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
	public void inserir(String jogador) throws Exception {
		raiz = inserir(jogador, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */

	private No inserir(String j, No i) throws Exception {
		if (i == null) {
         i = new No(j);

      } else if (j.compareTo(i.nome) < 0) {
         i.esq = inserir(j, i.esq);

      } else if (j.compareTo(i.nome) > 0) {
         i.dir = inserir(j, i.dir);

      } else {
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

	 public boolean pesquisar(String nome) {
		Boolean resp = pesquisar(nome, raiz);
		return resp;
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

      } else if (nome.compareTo(i.nome) == 0) {
         resp = true;

      } else if (nome.compareTo(i.nome) < 0) {
         MyIO.print("esq ");
         resp = pesquisar(nome, i.esq);

      } else {
         MyIO.print("dir ");
         resp = pesquisar(nome, i.dir);
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
			System.out.print(i.nome + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
    }
}

// Lista

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
	 * @param P Player elemento a ser inserido.
	 */
	public void inserirInicio(String nome) {
		Celula tmp = new Celula(nome);
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
	 * Insere um elemento em uma posicao especifica considerando que o primeiro
	 * elemento valido esta na posicao 0.
	 * 
	 * @param x   int Player a ser inserido.
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

	public boolean pesquisar(String nome){
		boolean resp =  false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			if(i.nome.compareTo(nome) == 0)
				resp = true;
		}
		return resp;
	}

	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.nome + " ");
		}
		System.out.println("] ");
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
		for (Celula i = primeiro.prox; i != ultimo.prox; i = i.prox){
			MyIO.print("["+ i.nome +"] ");
		}
	}	
}

// No

class No {
	public String nome; // Elemeto da Arvore
	public No esq , dir; // Aponta para seus Filhos.

	/**
	 * Construtor do Nó.
	 */
	public No(String nome) {
		this(nome, null, null);
	}
	/**
	 * Construtor do Nó.
	 * @param elemento int inserido na celula.
	 */
	public No(String nome, No esq, No dir) {
	  this.nome = nome;
	  this.dir = null;
	  this.esq = null;
	}
}

// Celula

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


