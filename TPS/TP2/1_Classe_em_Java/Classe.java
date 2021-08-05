class Classe{
    final static int TamArq = 3922 ;
    public static void main(String[] args) {
        Player[] p = new Player[TamArq];
        p = saveArk();
        Printar(p);
    }

    public static void Printar(Player[] p){
        int i=0;
        String str =MyIO.readLine();
        while(!str.contains("FIM")){
            i=Integer.parseInt(str);
            p[i].imprimir(); 
            str =MyIO.readLine();   
        }
    }

/*  @Method:Ler os dados de Arquivo e Salvar em Um Catalogo
    @List: Lista(Catalogo de Jogadores) 
*/
    public static Player[] saveArk(){
        Player[] p = new Player[TamArq];
        Arq.openRead("./players.csv");
        String Dado = Arq.readLine();
        int i=0;
        while(Arq.hasNext() == true){
          
            Dado = Arq.readLine();
            p[i] = new Player(); 
            p[i].Ler(Dado);
            i++;
        }
        Arq.close();         
        return p;
    }
}


//----------------------------|CLASSE+PLAYER|-------------------------------------\\

class Player{
    private int id,
                altura,
                peso,
                anoNascimento;
    private String  nome,
                    universidade,
                    cidadeNascimento,
                    estadoNascimento;

    //Constructor
    Player(){
    }
    Player(int id, int altura, int peso, int anoNascimento, String nome, String universidade, String cidadeNascimento, String estadoNascimento){        this.id = id;
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
  
    //.............GETS...................
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
    //Clone
    public Player Clone(){
        Player Clone = new Player();
        Clone.id = this.id;
        Clone.nome = this.nome;
        Clone.altura = this.altura;
        Clone.peso= this.peso;
        Clone.universidade = this.universidade;
        Clone.anoNascimento = this.anoNascimento;   
        Clone.cidadeNascimento = this.cidadeNascimento;
        Clone.cidadeNascimento = this.estadoNascimento;
        return Clone;
    }

    // Ler
    public void Ler(String Dados){
        //103,Milo Komenich,201,96,University of Wyoming,1920,,,
        Dados = Dados+',';
        Dados = Dados.replace(",,," , ",nao informado,nao informado,");
        Dados = Dados.replace(",," , ",nao informado,");
        
        String[] subDados = Dados.split(",");
        this.id=Integer.parseInt(subDados[0]);
        this.nome=subDados[1];
        this.altura=Integer.parseInt(subDados[2]);
        this.peso=Integer.parseInt(subDados[3]);
        this.universidade=subDados[4];
        this.anoNascimento=Integer.parseInt(subDados[5]);   
        this.cidadeNascimento=subDados[6];
        this.estadoNascimento=subDados[7];          
    }
 
    // Imprimir o Resultado
    public void imprimir(){
        MyIO.println("[" + id + " ## "+ nome + " ## "+ altura + " ## "+ peso +" ## "+ anoNascimento +" ## "+universidade+" ## "+ cidadeNascimento + " ## "+ estadoNascimento+"]");
    }
}
