class Classe {
    final static int TamArq = 3922;
    static int tamId = 0, tamNomes = 0, comp = 0;

    public static void main(String[] args) throws Exception {
        Pilha pilha = new Pilha();
        Player[] p = new Player[TamArq];
        p = saveArk();
        PubInNumbers(pilha);
        int acoes = MyIO.readInt();
        Ocorrencia(acoes, pilha, p);
        Printar(pilha,p);
        
    }

    /*
     * @ method: Ler um arquivo e instaciar os Objetos
     * @ return: Vetor de Objeto
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
        pilha.mostrar();
        for(int i = 0;i < pilha.getN();i++){
            MyIO.print("["+ i +"] ");
            p[pilha.dados(i)].imprimir();
        }
    }


}
