class Principal {
 
    public static void main(String[] args) throws Exception {
        Matriz matriz1 = new Matriz();
        Matriz matriz2 = new Matriz();
        int cont = MyIO.readInt();
        int n;
        while(cont != 0){
          //Ler e Salvar as Matrizes
          n = PubInNumbers(matriz1, matriz2);
          //Metodos ...
          matriz1.DiagonalP(n);
          matriz1.DiagonalS(n);
          matriz1.soma(matriz2);
          matriz1.multiplicacao(matriz2,n);
 
          //Prints...
          cont --;
        }
    }
 
    /*
     * @ method: Ler os Ids dos PubIn e salva em um vector
     * 
     * @ return: vetor de Ids
     */      
    public static int PubInNumbers(Matriz matriz1, Matriz matriz2)throws Exception {
       MyIO.readInt();
       int num = MyIO.readInt();
       matriz1.CriarM(num);
       matriz1.inserir(num);
       MyIO.readInt();
       MyIO.readInt();
       matriz2.CriarM(num);
       matriz2.inserir(num);
       return  num;
    }  
 }

 //Celula
 class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;
 
    public Celula(){
       this(0, null, null, null, null);
    }
 
    public Celula(int elemento){
       this(elemento, null, null, null, null);
    }
 
    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
       this.elemento = elemento;
       this.inf = inf;
       this.sup = sup;
       this.esq = esq;
       this.dir = dir;
    }
 }
 
 class Matriz {
    private Celula inicio;
    private Celula fim;
 
    public Matriz() {
       inicio = new Celula();
       fim = inicio;
    }
 
    public void CriarM(int n) {
 
       Celula tmp = fim = inicio;
       for (int j = 0; j < n; j++) {
          for (int i = 1; i < n; i++) {
             fim.dir = new Celula();
             fim.dir.esq = fim;
             fim = fim.dir;
          }
          if (j != (n - 1)) {
             tmp.inf = new Celula();
             tmp.inf.sup = tmp;
             tmp = fim = tmp.inf;
          }
       }
 
       Celula linha = tmp = inicio;
       for (int i = 1; i < n; i++) {
          fim = linha;
          linha = tmp = linha.dir;
          fim = fim.inf.dir;
          for (int j = 1; j < n; j++) {
             tmp.inf = fim;
             fim.sup = tmp;
             tmp = fim;
             if (j != (n - 1)) {
                fim = fim.esq.inf.dir;
             }
          }
       }
       fim = inicio;
    }
 
    public void inserir(int n) {
       Celula tmp = fim = inicio;
       for (int j = 0; j < n; j++) {
          for (int i = 0; i < n; i++) {
             tmp.elemento = MyIO.readInt();
             tmp = tmp.dir;
          }
          fim = tmp = fim.inf;
       }
       fim = inicio;
    }
 
    public void mostrar() {
       Celula tmp = inicio;
 
       for (Celula j = inicio; j != null; j = j.inf) {
          for (Celula i = tmp; i != null; i = i.dir) {
             System.out.print("| " + i.elemento + " ");
          }
          System.out.print("|\n");
          tmp = j.inf;
       }
 
    }
 
    public void DiagonalP(int n) {
       fim = inicio;
       for (int i = 0; i < n; i++) {
          MyIO.print(fim.elemento + " ");
          if (i != n - 1)
             fim = fim.inf.dir;
       }
       MyIO.println("");
       fim = inicio;
    }
 
    public void DiagonalS(int n) {
 
       fim = inicio;
       for (int i = 1; i < n; i++) {
          fim = fim.dir;
       }
       for (int i = 0; i < n; i++) {
          MyIO.print(fim.elemento + " ");
          if (i != n - 1)
             fim = fim.inf.esq;
       }
       MyIO.println("");
       fim = inicio;
    }
 
    public void soma(Matriz m) {
       Celula tmp = inicio;
       Celula tmp2 = m.inicio;
 
       for (Celula j = inicio, jj = m.inicio; j != null; j = j.inf, jj = jj.inf) {
          for (Celula i = tmp, ii = tmp2; i != null; i = i.dir, ii = ii.dir) {
             System.out.print((i.elemento + ii.elemento) + " ");
          }
          System.out.print("\n");
          tmp = j.inf;
          tmp2 = jj.inf;
       }
    }
 
    public void multiplicacao(Matriz m, int n) {
 
       Celula tmp = fim = inicio;
       Celula tmp2 = m.inicio;
       int total = 0;
       int l = 0;
       while (l !=n) {
          for (Celula j =  m.inicio; j != null; j = j.dir) {
             for (Celula i = tmp, ii = tmp2; i != null; i = i.dir, ii = ii.inf) {
                total += ii.elemento * i.elemento;
             }
             MyIO.print(total + " ");
             tmp2 = j.dir;
             total = 0;
          }
          MyIO.println("");
          l++;
          tmp2 = m.inicio;
          tmp = fim = fim.inf;
       }
       fim =inicio;
       m.fim =m.inicio;
    }
 }
 